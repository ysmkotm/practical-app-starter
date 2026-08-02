# 共通コード一覧

**Document Version** : 1.1

**更新日** : 2026/08/02

本ドキュメントは、システム全体で使用する共通コード（`common_code`）のコード種別・コード値を俯瞰するための一覧です。
共通コードの仕組み自体は [`docs/02_rules/db.md`](../02_rules/db.md) §4、テーブル構造は [`docs/04_db/table/common_code.md`](../04_db/table/common_code.md) を参照してください。

---

## 1. 目的

- コード種別（`code_type`）ごとの用途・利用箇所を把握する
- コード種別を新規追加する前に、既存の種別と重複していないかを確認する
- コード種別ごとの実際のコード値（`code`）を確認する

---

## 2. コード種別一覧

|code_type|概要|利用箇所|
|---|---|---|
|EMPLOYEE_STATUS|社員の在籍状況|`employee.status_code`|

---

## 3. コード値一覧

|code_type|code|code_name|display_order|
|---|---|---|---|
|EMPLOYEE_STATUS|ACTIVE|在籍|10|
|EMPLOYEE_STATUS|LEAVE|休職|20|
|EMPLOYEE_STATUS|RETIRED|退職|30|

---

## 4. 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`docs/02_rules/db.md`](../02_rules/db.md) §4|共通コードの方針|
|[`docs/04_db/table/common_code.md`](../04_db/table/common_code.md)|共通コードマスタのテーブル定義書|

---

## 5. 備考

- コード種別・コード値の実データは [`src/main/resources/db/migration/V002__insert_common_code.sql`](../../src/main/resources/db/migration/V002__insert_common_code.sql) を正とし、本一覧は追加・変更のタイミングで追従します。
