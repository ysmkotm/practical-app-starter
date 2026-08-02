# DB設計ルール

**Document Version** : 1.1

**更新日** : 2026/08/02

---

## 1. 基本方針

- データベースの設計・命名は、本ドキュメントのルールに従う。
- テーブル間の関連付けは、原則として主キー（ID）を使用する。
- 識別コードと区分コードは、用途に応じて使い分ける。
- 共通カラムは、各テーブルで統一して使用する。
- 削除は原則として論理削除を使用する。
- DDL・初期データはGitで管理する。
- テーブル定義書などの具体的な記載方法・記載例は、各テンプレート（[`docs/04_db/table/_template.md`](../04_db/table/_template.md)等）を参照する。

各項目の詳細は、以降の章を参照してください。

---

## 2. 主キー・識別コード・外部キー

主キー（ID）と識別コードは役割が異なります。混同しないようにします。

### 主キー（ID）

- システム内部でレコードを一意に特定するための項目です。
- カラム名は `{テーブル名}_id` とします。
- 原則として値は変更しません。

### 識別コード

- 人が識別・検索したり、外部システムと連携したりするための項目です。
- 主キーとは別に持ちます。
- 業務上の都合で値が変更される可能性があります。
- 論理名や画面上の項目名には、物理名をそのまま直訳せず、業務で一般的に使用される名称を採用します。
  - 例：`employee_code` は「社員番号」、`department_code` は「部署コード」とする

### 外部キー

- テーブル間の関連付けは、原則として参照先の主キー（ID）を使います。
- カラム名は、参照先の主キー名と同じ名称を使用します。
- 識別コードは値が変更される可能性があるため、外部キーの参照先としては使用しません。
- 例：`employee.department_id` は `department.department_id` を参照する（`department_code` は参照しない）

---

## 3. 共通カラム

業務データを持つテーブルには、原則として以下の共通カラムを持たせます。

- `created_at`
- `updated_at`

論理削除を行うテーブルには、以下のカラムを持たせます。

- `deleted_flg`

---

## 4. 共通コード

区分コード（在籍区分など）を管理するための共通の仕組みです。§2 の識別コード（`employee_code` など）とは別の概念です。

### 共通コードマスタ

- コードの定義は、共通コードマスタ（`common_code`）で一元管理する。
- `code_type` でコードの種別（グループ）を分け、`code` 列にコード値、`code_name` 列に表示名を持つ。
- 現在利用しているコード種別・コード値の一覧は [`docs/03_system/common_codes.md`](../03_system/common_codes.md) を参照する。

### 共通コードの利用

- 共通コードを使用するカラムには、表示名ではなくコード値を保存する。
  - 例：`employee.status_code` に `ACTIVE` を保存する
- 画面表示時は、共通コードマスタから表示名を取得して変換する。
- 共通コードマスタへの外部キーは、原則として設けず、`code_type` と `code` の組み合わせにより対象のコードを識別する。
- SQL で共通コードマスタを結合する場合は、コード値（`code`）だけでなくコード種別（`code_type`）も条件に含める。

---

## 5. DB命名

- テーブル名・カラム名はスネークケースで統一する。
- テーブル名は単数形で統一する。
  - 例：`employee`、`department`
- IDは `_id`
- コード値は `_code`（識別コード・区分コードの双方に用いる）
- 名称は `_name`
- フラグは `_flg`
- 日付は `_date`
- 日時は `_at`

---

## 6. SQLファイル管理

- DDL・初期データ投入SQLはGitで管理する。
- SQLファイルは Flyway の標準配置である `src/main/resources/db/migration` 配下に配置する。
- アプリケーション起動時に Flyway が未適用のマイグレーションを自動適用する。
- ファイル名は `V<連番>__<内容>.sql` とする。
  - 例：[`src/main/resources/db/migration/V001__create_common_code.sql`](../../src/main/resources/db/migration/V001__create_common_code.sql)
  - 例：[`src/main/resources/db/migration/V002__insert_common_code.sql`](../../src/main/resources/db/migration/V002__insert_common_code.sql)
  - 例：[`src/main/resources/db/migration/V003__create_department.sql`](../../src/main/resources/db/migration/V003__create_department.sql)
  - 例：[`src/main/resources/db/migration/V004__insert_department.sql`](../../src/main/resources/db/migration/V004__insert_department.sql)
  - 例：[`src/main/resources/db/migration/V005__create_employee.sql`](../../src/main/resources/db/migration/V005__create_employee.sql)
  - 例：[`src/main/resources/db/migration/V006__insert_employee.sql`](../../src/main/resources/db/migration/V006__insert_employee.sql)
- テーブル作成DDLと初期データ投入SQLは分けて管理する。
- SQLファイルはバージョン順に追加し、適用済みファイルは原則として変更しない。
- 既存 DB の初回移行方針（再作成を標準とする等）は [`docs/07_decisions/flyway_adoption.md`](../07_decisions/flyway_adoption.md) および [`docs/01_project/setup.md`](../01_project/setup.md) §5 を参照する。

現時点の構成は次のとおりです。

```text
src/main/resources/db/migration
├── V001__create_common_code.sql
├── V002__insert_common_code.sql
├── V003__create_department.sql
├── V004__insert_department.sql
├── V005__create_employee.sql
└── V006__insert_employee.sql
```

---

## 7. 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`docs/04_db/table/_template.md`](../04_db/table/_template.md)|テーブル定義書テンプレート|
|[`docs/04_db/view/_template.md`](../04_db/view/_template.md)|VIEW定義書テンプレート|
|[`docs/03_system/tables.md`](../03_system/tables.md)|テーブル一覧|
|[`docs/03_system/common_codes.md`](../03_system/common_codes.md)|共通コード一覧|
|[`docs/03_system/er.md`](../03_system/er.md)|ER図|
