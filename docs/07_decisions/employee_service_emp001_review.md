# EmployeeService 一覧 GET の設計判断（EMP001 レビュー）

**Document Version** : 1.0

**更新日** : 2026/07/30

**ステータス** : 決定

---

## 1. 目的

EMP001 社員一覧の GET 処理実装に伴う `EmployeeService` のコードレビュー結果を記録し、現時点で採用する設計と、将来再検討する事項を明確にする。

---

## 2. 背景

EMP001 社員一覧（GET）の実装および `EmployeeService` のレビューを実施した。レビューでは、以下の論点が挙がった。

- 在籍区分取得で使用するコード種別 `EMPLOYEE_STATUS` の定数配置
- `EmployeeService#searchEmployees` が `EmployeeSearchForm` を直接受け取る構成
- プルダウン選択肢として `Department`・`CommonCode` の Entity をそのまま返す構成
- 参照系処理への `@Transactional(readOnly = true)` 付与の要否

一覧 GET のみが実装済みであり、登録・更新処理や Service の再利用範囲は未確定である。過剰な抽象化を避けつつ、判断の経緯を残す必要があった。

---

## 3. 検討した案

### 3.1 コード種別定数 `EMPLOYEE_STATUS` の配置

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. `EmployeeService` 内の private 定数（採用）|使用箇所の近くに定義する|現状の利用範囲に合う。クラス数が増えない|複数 Service から参照する際に重複しうる|
|B. 共通定数クラスへ切り出す|コード種別を一箇所で管理する|横断参照時に重複を防げる|現時点では `EmployeeService` のみが使用しており、早すぎる共通化になりうる|
|C. enum へ切り出す|型安全にコード種別を表現する|IDE 支援・typo 防止|DB の `code_type` 文字列との変換が必要。利用箇所が少ない段階では過剰|

### 3.2 Service が `EmployeeSearchForm` を直接受け取る構成

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. 現状維持（Form を Service 引数として渡す）|Controller でバインドした Form をそのまま Service へ渡す|[`entity_form_dto_roles.md`](entity_form_dto_roles.md) および [`coding.md`](../02_rules/coding.md) §4 と整合。Form と Mapper 条件が一致する間はシンプル|Service が Web 層の Form に依存する。再利用・API 化時に分離が必要になりうる|
|B. 検索条件クラスへ分離|Form とは別の条件オブジェクトを Service 引数とする|Service の依存を画面入力から切り離せる|クラス数が増える。現時点では Form と Mapper 条件が同一|

### 3.3 プルダウン用に Entity をそのまま返す構成

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. 現状維持（`Department`・`CommonCode` Entity を返す）|マスタ1テーブル対応の Entity をそのまま Model へ渡す|実装が単純。テーブル定義と Entity の対応が明確|画面表示に不要な項目も含む。将来の表示要件変更で DTO 化が必要になりうる|
|B. 選択肢用 DTO を導入|コード・名称等のみを持つ DTO を返す|画面に必要な項目だけを公開できる|現時点ではクラスが増える。プルダウン用途に Entity で足りる|

### 3.4 `@Transactional(readOnly = true)` の付与

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. 現状維持（付与しない）|一覧 GET のみの段階ではトランザクション宣言を省略する|実装が最小限|参照系のトランザクション方針が未整理のまま|
|B. クラスまたはメソッドに `@Transactional(readOnly = true)` を付与|参照系を read-only トランザクションで統一する|意図が明示される。将来の書き込み処理追加時に方針を揃えやすい|POST 実装前の段階では方針確定が早い可能性|

---

## 4. 判断基準

- 現時点（EMP001 一覧 GET のみ）で必要十分なシンプルさ
- [`entity_form_dto_roles.md`](entity_form_dto_roles.md) および [`coding.md`](../02_rules/coding.md) §4 との整合
- YAGNI（登録・更新・Service 再利用が未確定のうちは抽象化しない）
- 将来の再検討条件を明確にし、判断の経緯を残せること

---

## 5. 判断結果

|論点|判断|
|---|---|
|コード種別定数 `EMPLOYEE_STATUS`|**案Aを採用**。`EmployeeService` 内の `private static final` 定数として維持する|
|Service が `EmployeeSearchForm` を直接受け取る構成|**現状維持**。再検討はバックログ（BLG-EMP-008）で管理する|
|プルダウン用に Entity をそのまま返す構成|**現状維持**。再検討はバックログ（BLG-EMP-009）で管理する|
|`@Transactional(readOnly = true)`|**現状維持（付与しない）**。再検討はバックログ（BLG-CMN-004）で管理する|

コード種別定数の共通化（共通定数クラスまたは enum への切り出し）は、複数の Service やクラスから同じコード種別を参照する必要が生じた時点で改めて検討する（BLG-CMN-005）。

---

## 6. 判断理由

- `EMPLOYEE_STATUS` は現時点で `EmployeeService#findEmployeeStatuses` のみが参照しており、共通定数クラスや enum への切り出しは利用箇所に対して過剰である。
- `EmployeeSearchForm` を Service へ渡す構成は、Form と Mapper の検索条件が一致している間、[`entity_form_dto_roles.md`](entity_form_dto_roles.md) §5 で許容されている方針と一致する。
- 部署・在籍区分のプルダウンは、1テーブル対応の `Department`・`CommonCode` で必要な項目（コード・名称等）を満たしており、一覧表示用 DTO（`EmployeeListItemDto`）とは用途が異なる。現段階で選択肢専用 DTO を追加する必要性は低い。
- 登録・更新（POST）が未実装のため、read-only / read-write のトランザクション境界を含む全体方針は、書き込み処理の実装タイミングでまとめて決める方が一貫性がある。

---

## 7. 今後の対応

- 登録・更新機能（POST）の実装、または Service の再利用範囲が明確になった段階で、§3.2〜§3.4 の再検討を行う。着手タイミングは [`docs/01_project/backlog.md`](../01_project/backlog.md) §4.1 の保留項目を参照する。
- 複数 Service から同一コード種別を参照するようになった時点で、BLG-CMN-005 に従い共通定数クラスまたは enum への切り出しを検討する。
- 再検討の結果、方針が確定した場合は本判断記録および必要に応じて [`coding.md`](../02_rules/coding.md) を更新する。

### 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`docs/01_project/backlog.md`](../01_project/backlog.md) §4.1|保留中の再検討項目（BLG-EMP-008、BLG-EMP-009、BLG-CMN-004、BLG-CMN-005）|
|[`docs/07_decisions/entity_form_dto_roles.md`](entity_form_dto_roles.md)|Entity・Form・DTO の役割分担|
|[`docs/02_rules/coding.md`](../02_rules/coding.md) §3・§4|レイヤー構成・データクラスの使い分け|
|[`docs/05_screen/EMP001_社員一覧.md`](../05_screen/EMP001_社員一覧.md)|社員一覧画面設計書|
|[`src/main/java/io/github/ysmkotm/practicalappstarter/service/EmployeeService.java`](../../src/main/java/io/github/ysmkotm/practicalappstarter/service/EmployeeService.java)|判断対象の Service 実装|
