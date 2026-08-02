# employee 社員マスタ

**Document Version** : 1.1

**更新日** : 2026/08/02

## 1. 基本情報

|項目|内容|
|---|---|
|物理名|employee|
|論理名|社員マスタ|
|概要|社員情報を管理する|
|作成者||
|作成日|2026/07/09|
|更新日||

---

## 2. カラム定義

|No|物理名|論理名|データ型|PK|FK|NOT NULL|初期値|備考|
|---|---|---|---|---|---|---|---|---|
|1|employee_id|社員ID|BIGSERIAL|○||○||自動採番|
|2|employee_code|社員番号|VARCHAR(20)|||○||登録時は手入力（自動採番しない）。文字種制限なし|
|3|employee_name|氏名|VARCHAR(100)|||○|||
|4|employee_name_kana|氏名カナ|VARCHAR(100)||||||
|5|email|メールアドレス|VARCHAR(255)|||○|||
|6|phone_number|電話番号|VARCHAR(20)||||||
|7|department_id|部署ID|BIGINT||○|○|||
|8|position|役職|VARCHAR(100)||||||
|9|hire_date|入社日|DATE|||○|||
|10|status_code|在籍区分コード|VARCHAR(20)|||○||コード種別：`EMPLOYEE_STATUS`|
|11|remote_work_flg|リモートワークフラグ|BOOLEAN|||○|FALSE|リモートワークの可否（`true` = 可）。実施中などの状態は表さない|
|12|remarks|備考|TEXT||||||
|13|deleted_flg|削除フラグ|BOOLEAN|||○|FALSE||
|14|created_at|作成日時|TIMESTAMP|||○|CURRENT_TIMESTAMP||
|15|updated_at|更新日時|TIMESTAMP|||○|CURRENT_TIMESTAMP||

---

## 3. 外部キー

|外部キー名|対象カラム|参照先テーブル|参照先カラム|備考|
|---|---|---|---|---|
|fk_employee_department|department_id|department|department_id||

---

## 4. 制約

|制約名（論理）|物理制約名|種別|対象カラム|内容|
|---|---|---|---|---|
|社員番号一意制約|`employee_employee_code_key`|UNIQUE|employee_code|重複不可（論理削除済み行も含む）|
|メールアドレス一意制約|`employee_email_key`|UNIQUE|email|重複不可（論理削除済み行も含む）|

- 物理制約名は、[`V005__create_employee.sql`](../../../src/main/resources/db/migration/V005__create_employee.sql) の列定義 `UNIQUE` に対し、PostgreSQL が付与した自動命名である（DDL 上の明示名ではない）。
- アプリケーションは、登録・更新時の `DataIntegrityViolationException` 判定で上記物理制約名を使用する（`EmployeeService`）。一致する場合のみ重複エラーとして画面へ戻し、それ以外（外部キー違反・制約名不明など）は再スローする。
- 論理削除済みの社員番号・メールアドレスは再利用できない（本制約が論理削除済み行も含むため）。

---

## 5. インデックス

なし

---

## 6. 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`docs/04_db/table/department.md`](department.md)|部署マスタ（外部キー参照先）|
|[`docs/03_system/common_codes.md`](../../03_system/common_codes.md)|共通コード一覧（`status_code`が参照する`EMPLOYEE_STATUS`）|
|[`docs/05_screen/EMP002_社員登録・編集.md`](../../05_screen/EMP002_社員登録・編集.md)|社員登録・編集|
