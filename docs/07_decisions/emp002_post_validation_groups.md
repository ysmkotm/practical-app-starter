# EMP002 POST 実装における Validation Groups 採用と登録・更新バリデーション分離

**Document Version** : 1.0

**更新日** : 2026/07/30

**ステータス** : 決定

---

## 1. 目的

EMP002 社員登録・編集の POST 処理（登録・更新・削除）実装において、登録・更新で共用する `EmployeeForm` のバリデーション方針、および関連する暫定判断（`mode` 管理、存在しない社員のエラー処理）を記録する。

---

## 2. 背景

EMP002 の GET 実装完了後、POST 処理の実装に着手した。画面設計書（[`EMP002_社員登録・編集.md`](../05_screen/EMP002_社員登録・編集.md)）では、登録・編集で同一テンプレート・同一 Form を共用し、登録時のみ社員番号を必須とする。バックログ（BLG-EMP-006）では Validation Groups の利用可否が保留されていた。

あわせて、BLG-EMP-001（`mode` の管理方式）・BLG-EMP-002（`EmployeeForm` 共用）・BLG-EMP-004（存在しない社員のエラー処理）は、POST 実装時に暫定方針の確定が必要だった。

---

## 3. 検討した案

### 3.1 登録・更新のバリデーション分離

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. Validation Groups（採用）|Bean Validation のグループ（`Create` / `Update`）で登録・更新を切り替える|Form を1つ維持できる。標準的な Bean Validation の機能|全制約に `groups` を明示する必要がある。`@Validated(Create.class)` 使用時、Default グループは評価されない|
|B. Form を登録・更新で分離|`EmployeeCreateForm` / `EmployeeUpdateForm` を用意する|各 Form に必要な制約だけを書ける|クラス数が増える。画面設計書の共用方針と整合確認が必要|
|C. Controller / Service で手動チェック|Bean Validation は共通のみとし、社員番号必須等はコードで分岐する|グループ指定が不要|分岐が散在し、バリデーションルールの所在が不明瞭になりやすい|

### 3.2 `mode` の管理方式（BLG-EMP-001）

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. 文字列（採用）|Model に `"create"` / `"edit"` を設定する|画面設計書 §8.1 および GET 実装と整合。シンプル|typo のリスク（定数化・Enum 化は将来検討可）|
|B. 定数クラス|`EmployeeFormMode.CREATE` 等|文字列リテラルの散在を防げる|現時点ではクラスが増える|
|C. Enum|型安全|IDE 支援|Thymeleaf との比較・Model 受け渡しで追加の配慮が必要|

### 3.3 存在しない社員のエラー処理（BLG-EMP-004）

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. `ResponseStatusException`（404）（暫定採用）|編集 GET・更新 POST・削除 POST で対象不在時に HTTP 404|実装が最小限。Spring Boot 標準のエラー応答|業務画面向けのエラー表示方針（BLG-CMN-002）は未確定|
|B. 一覧へリダイレクト＋エラーメッセージ|存在しない ID 指定時に EMP001 へ戻す|業務画面として自然な場合がある|メッセージ表示方針（BLG-CMN-001）確定が前提|
|C. 専用エラー画面|404 用の Thymeleaf テンプレートを用意する|表示を制御できる|共通例外方針（BLG-CMN-002）確定が前提|

### 3.4 編集時の社員番号と POST 送信（2026/07/14 追記）

|観点|内容|
|---|---|
|HTML の属性|編集時は `readonly` を使用。**`disabled` は使用しない**|
|POST 送信|`th:field`（=`name` 属性）を付けず `th:value` のみとするため、**通常の POST では社員番号は送信されない**。`readonly` でも `name` があれば送信される点に注意|
|サーバー側の防御|更新 SQL は `employee_code` を更新対象に含めない。Controller は DB 取得値で Form の社員番号を上書きし、クライアント送信値を信用しない（`applyEmployeeCodeFromDatabase`）|
|論理削除と UNIQUE|DB の UNIQUE 制約は論理削除済み行も含む。アプリの事前チェックは未削除のみ。論理削除済みの社員番号・メールは再利用できない（[`EMP002_社員登録・編集.md`](../05_screen/EMP002_社員登録・編集.md) §6・[`employee.md`](../04_db/table/employee.md) §4）。再利用可への変更検討は BLG-EMP-013|

---

## 4. 判断基準

- 画面設計書（[`EMP002_社員登録・編集.md`](../05_screen/EMP002_社員登録・編集.md)）を正（SSOT）とする
- [`docs/02_rules/coding.md`](../02_rules/coding.md) §5（Bean Validation は Form に定義）との整合
- Version 1.0 完成に必要十分なシンプルさ（過剰な抽象化を避ける）
- ソースコードレビュー前の暫定実装として、後から見直し可能なこと

---

## 5. 判断結果

|論点|判断|
|---|---|
|登録・更新のバリデーション分離|**案A（Validation Groups）を採用**。`ValidationGroups.Create` / `ValidationGroups.Update` を定義し、Controller の `@Validated` で切り替える|
|Form 共用（BLG-EMP-002）|**共用を採用**。登録・更新とも `EmployeeForm` を使用する（画面設計書どおり）|
|`mode` 管理（BLG-EMP-001）|**案A（文字列）を採用**。`"create"` / `"edit"` を Model 属性として使用する|
|存在しない社員（BLG-EMP-004）|**案A（404）を暫定採用**。最終方針は BLG-CMN-002 確定後に見直す|
|一意性チェック|Service の `validateUniqueConstraints` で未削除レコードを事前チェック。DB 制約違反時は `DataIntegrityViolationException` を catch しグローバルエラー表示（詳細メッセージは BLG-CMN-001 待ち）。論理削除済みとの競合は再利用不可（設計書・定義書に記載）|
|編集時の社員番号|編集 input は `name` なしで POST 対象外。更新は SQL・Service とも `employee_code` 非更新。Controller で DB 値を Form に設定|
|処理成功メッセージ|Version 1.0 暫定として実装（[`PRG-CMN-003`](../01_project/progress.md)）。成功時は EMP001 へリダイレクトし Flash 属性 `successMessage` を渡す。共通方針の完全版は BLG-CMN-001 保留|

---

## 6. 判断理由

- 画面設計書が登録・編集での Form 共用を前提としており、Validation Groups により Form 分離なしで登録時のみ社員番号必須を実現できる。
- `@Validated(ValidationGroups.Create.class)` 使用時、**groups 未指定の制約（Default グループ）は評価されない**。そのため、共用項目の `@NotBlank` 等にも `{Create.class, Update.class}` を明示した（実装時のハマりどころとして本記録に残す）。
- `mode` の文字列管理は GET 実装および画面設計書 §8.1 と一致しており、Version 1 では定数化・Enum 化より変更コストが小さい。
- 存在しない社員への対応は、共通例外方針（BLG-CMN-002）未確定のため、Spring Boot 標準の 404 で暫定対応とした。業務画面としての表示は今後 BLG-CMN-002 と合わせて再検討する。

---

## 7. 今後の対応

- EMP002 POST 実装全体のソースコードレビュー（REV-EMP-018 以降、または POST 専用 REV）を実施する。
- 処理成功メッセージの暫定実装は PRG-CMN-003 で対応済み。共通方針の完全版・DB 制約違反時の詳細表示は BLG-CMN-001 確定後に見直す。
- BLG-CMN-002 確定後、BLG-EMP-004 の暫定 404 方針を見直す。
- BLG-EMP-013：再利用不可は設計書・定義書の現行仕様。再利用可（部分 UNIQUE 等）への変更検討は Version 1.x。
- Validation Groups の運用を他画面へ展開する場合、[`docs/02_rules/coding.md`](../02_rules/coding.md) §5 への追記を検討する。
- `mode` の定数化・Enum 化が必要になった段階で BLG-EMP-001 相当の再検討を行う。

### 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`docs/05_screen/EMP002_社員登録・編集.md`](../05_screen/EMP002_社員登録・編集.md)|EMP002 画面設計書|
|[`docs/02_rules/coding.md`](../02_rules/coding.md)|Form バリデーション・Controller 実装ルール|
|[`docs/01_project/backlog.md`](../01_project/backlog.md)|BLG-EMP-001 / 002 / 004 / 006、BLG-CMN-001 / 002|
|[`docs/07_decisions/employee_list_navigation_pattern.md`](employee_list_navigation_pattern.md)|一覧・編集画面の責務分担|
|[`src/main/java/io/github/ysmkotm/practicalappstarter/form/ValidationGroups.java`](../../src/main/java/io/github/ysmkotm/practicalappstarter/form/ValidationGroups.java)|Validation Groups 定義|
|[`src/main/java/io/github/ysmkotm/practicalappstarter/controller/EmployeeController.java`](../../src/main/java/io/github/ysmkotm/practicalappstarter/controller/EmployeeController.java)|POST エントリポイント|
