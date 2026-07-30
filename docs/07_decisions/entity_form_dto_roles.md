# Entity・Form・DTO の役割分担

**Document Version** : 1.0

**更新日** : 2026/07/30

**ステータス** : 決定

---

## 1. 目的

Thymeleaf 画面を前提としたアプリケーションにおいて、Entity・Form・DTO の役割分担を決定し、今後の画面・機能実装で共通して適用できる方針を記録する。

---

## 2. 背景

EMP001 社員一覧の実装にあたり、以下の論点が生じた。

- 一覧表示には、社員マスタに加えて部署名・在籍区分名称（JOIN 結果）が必要である。
- 従来の [`docs/02_rules/directory.md`](../02_rules/directory.md) には「DTO は原則として作成せず、画面とのデータ受け渡しには `Form` クラスを利用する」と記載されていた。
- 一方、Entity をテーブル定義どおりに保つ方針と、JOIN 結果を一覧表示用データとして扱う必要が両立しにくかった。
- ChatGPT との設計相談を経て、Entity / Form / DTO の三層に分ける案が整理された。

本判断は、EMP001 実装および開発ルール更新（2026/07/12）の契機として行った。

---

## 3. 検討した案

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. Entity に一覧表示用項目を追加|JOIN で取得する `department_name` 等を `Employee` Entity に持たせる|クラス数が少ない|Entity がテーブル定義から逸脱する。登録・更新・詳細でも不要な項目を持つ|
|B. Form で一覧表示データも保持|検索 Form または別 Form に JOIN 結果を格納|Form クラスが集約される|Form は入力受け取り用という責務と混在する。一覧1行分のデータ表現として不自然|
|C. DTO パッケージを導入（採用）|Entity はテーブル対応、Form は入力、DTO は JOIN 結果・一覧表示用|責務が明確。MyBatis から DTO へ直接マッピング可能|クラス数が増える。命名・配置ルールの整備が必要|
|D. `model` パッケージを使用|Spring MVC の `Model` と混同しやすい `model` パッケージに表示用クラスを配置|—|役割が曖昧になりやすい。Spring MVC の `Model` と混同する|

---

## 4. 判断基準

- テーブル定義書を Entity の正本として維持できること
- Thymeleaf 画面の入力（検索・登録・更新）と、DB 取得結果（JOIN 含む）の責務を分離できること
- 初学者・AI 協調開発の双方が理解しやすいこと
- Version 1（Thymeleaf）と将来の REST API 導入の両方を見据えられること
- 開発ドキュメント間で SSOT を維持できること

---

## 5. 判断結果

**案Cを採用**する。あわせて **`model` パッケージは使用しない**（案D は不採用）。

Version 1 におけるデータクラスの役割分担は以下とする。

|種類|役割|例（EMP001）|
|---|---|---|
|Entity|1テーブルに対応。テーブル定義書を正とする|`Employee`、`Department`、`CommonCode`|
|Form|画面からの入力値。Bean Validation を定義|`EmployeeSearchForm`|
|DTO|Entity 単体では表現できないデータ（JOIN 結果、一覧表示用等）|`EmployeeListItemDto`|
|Request DTO / Response DTO|Version 1 では作成しない|REST API 実装段階で導入を検討|

その他の関連方針：

- Form から Entity への変換は、原則 Service で行う。
- MyBatis の JOIN 結果は DTO へ直接マッピングしてよい。
- 検索 Form と Mapper の検索条件が一致している間は、Form を Mapper の引数として使用してよい。
- Java クラスの配置先として `model` パッケージは作成しない（Spring MVC の `Model` は画面へデータを渡す仕組みとして使用する）。

---

## 6. 判断理由

- 案Aでは、Entity に JOIN 専用項目や画面表示専用項目が混在し、テーブル定義書との対応関係が曖昧になる。登録・更新処理の実装時にも不要な項目を持ち続けることになる。
- 案Bでは、Form が「入力受け取り」と「取得結果の保持」の二重の責務を持ち、検索 Form・登録 Form・一覧1行分のデータの区別がつきにくくなる。
- 案Cでは、Entity（永続化単位）・Form（入力）・DTO（取得結果・表示用）の責務が明確になり、MyBatis の `resultMap` による JOIN 結果のマッピングとも整合する。
- Request DTO / Response DTO は REST API 向けの概念であり、Version 1 の Thymeleaf 画面では Form で足りる。API 実装段階まで導入を見送ることで、過剰な抽象化を避けられる。
- `model` パッケージ（案D）は Spring MVC の `Model` と名称が近く、初学者にとって混乱の原因になりやすい。

---

## 7. 今後の対応

- データクラスの役割分担および変換ルールは [`docs/02_rules/coding.md`](../02_rules/coding.md) §4 を正本（SSOT）として管理する。
- パッケージ構成・静的リソース配置は [`docs/02_rules/directory.md`](../02_rules/directory.md) を参照する。
- 新規画面・機能を実装する際は、本判断記録と上記ルールを参照し、Entity / Form / DTO のいずれに該当するかを判断する。
- REST API を実装する段階で、Request DTO / Response DTO の導入要否を再検討する。導入する場合は本判断記録と `coding.md` §4 を更新する。
- Form と Mapper の検索条件の責務が分離された場合は、検索条件クラスへの分離を検討する。

### 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`docs/02_rules/coding.md`](../02_rules/coding.md) §4|データクラスの使い分け（SSOT）|
|[`docs/02_rules/directory.md`](../02_rules/directory.md) §2|Java パッケージ構成|
|[`docs/02_rules/naming.md`](../02_rules/naming.md) §4・§5|クラス名・パッケージ名|
|[`docs/05_screen/EMP001_社員一覧.md`](../05_screen/EMP001_社員一覧.md)|初適用対象の画面設計書|
