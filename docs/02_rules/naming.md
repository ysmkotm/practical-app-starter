# 命名規則

**Document Version** : 1.0

**更新日** : 2026/07/30

---

## 1. 基本方針

本プロジェクトでは、クラス名・URL・ファイル名・ドキュメント名など、同じ意味を持つ名称はプロジェクト全体で統一します。

命名は以下を基本方針とします。

- 同じ対象に複数の呼称を使用しない。
- 実務で採用しやすい名称を優先する。
- 人とAIの双方が理解しやすい名称を採用する。
- 判断に迷った場合は、シンプルで分かりやすい名称を優先する。

---

## 2. 用語

本プロジェクトでは、以下の用語で統一します。

|用途|名称|補足|
|---|---|---|
|`docs` 配下のドキュメント全体|開発ドキュメント|プロジェクト概要・開発ルール・設計書・判断記録等の総称|
|開発ドキュメントの管理場所|`docs` 配下|開発ドキュメントを配置するディレクトリ|
|Single Source of Truth|正本（SSOT）|`docs` 配下の開発ドキュメントを正本として管理する|
|AIとの開発|AI協調開発|ChatGPT・Cursor等と協調して開発すること|
|`docs/03_system` 配下の設計書カテゴリ|システム全体設計|機能一覧・画面一覧・テーブル一覧・ER図など、システム全体を俯瞰する設計書カテゴリ|
|`docs/01_project/backlog.md`|バックログ|設計・実装時の保留事項・未決定事項を管理するドキュメント。ID は `BLG-{PRJ\|CMN\|機能ID}-{連番}`。完了後も履歴として残す|
|`docs/01_project/progress.md`|進捗管理|Version 単位の作業管理の正本。作業の全量・現在地・次タスクを管理する。ID は `PRG-{プレフィックス}-{連番}`|
|`docs/03_system/features.md`|機能一覧|システムに含まれる機能を俯瞰するドキュメント。作業進捗は [`progress.md`](../01_project/progress.md)|
|`docs/03_system/screens.md`|画面一覧|画面ID・画面名・URLを俯瞰するドキュメント。画面IDの先頭3文字は機能IDと一致させる。作業進捗は [`progress.md`](../01_project/progress.md)|
|`docs/03_system/tables.md`|テーブル一覧|テーブル物理名・論理名・機能の対応を俯瞰するドキュメント。作業進捗は [`progress.md`](../01_project/progress.md)|
|`docs/03_system/er.md`|ER図|テーブル構成と関連を示すドキュメント|
|`docs/04_db` 配下の設計書カテゴリ|DB設計書|テーブル定義書・VIEW定義書など、DB関連の詳細設計書をまとめるカテゴリ。工程名は「DB設計」とする|
|`docs/04_db/table` 配下の設計書|テーブル定義書|1テーブルごとの定義を記載するドキュメント|
|`docs/04_db/view` 配下の設計書|VIEW定義書|1VIEWごとの定義を記載するドキュメント|
|`docs/05_screen` 配下の設計書|画面設計書|画面単位の設計内容を記載するドキュメント|
|`docs/06_api` 配下の設計書|API設計書|API単位の設計内容を記載するドキュメント|
|`docs/02_rules/db.md`|DB設計ルール|テーブル命名・共通カラム・SQL管理などの共通ルール。設計書名称とは区別する|
|`docs/02_rules/documentation.md`|ドキュメント記法ルール|開発ドキュメントの Markdown 共通記法を定めるドキュメント|
|`docs/07_decisions` 配下のドキュメント|判断記録|技術選定・設計変更などの判断理由を記録するドキュメント|
|成果物・管理対象として扱うアプリケーション実装|ソースコード|`src` 配下のアプリケーション実装。設計書・ソースコードなど管理対象として使用する|
|AI協調開発・一般的な技術用語として扱う実装|コード|コード生成・コードレビュー・既存コードなど、一般的な技術用語として使用する|

---

## 3. URL

URLはリソース名を単数形で統一します。

|画面|URL|
|---|---|
|一覧|`/employee`|
|登録|`/employee/new`|
|詳細|`/employee/{employeeId}`|
|編集|`/employee/{employeeId}/edit`|

パス変数には、汎用的な `id` ではなく、対象が分かる名称（`employeeId`、`departmentId` 等）を使用します。

---

## 4. Javaクラス

Javaクラスは単数形で統一します。

|用途|例|
|---|---|
|Entity|`Employee`|
|DTO|`EmployeeListItemDto`|
|Controller|`EmployeeController`|
|Service|`EmployeeService`|
|Mapper|`EmployeeMapper`|
|Form|`EmployeeSearchForm`|

---

## 5. パッケージ・プロジェクト識別子

### 5.1 表示名と機械可読識別子の使い分け

|用途|値|使う場所|
|---|---|---|
|正式名称（表示名）|Practical App Starter — Spring Boot Edition|README、対外説明|
|リポジトリ名|`practical-app-starter`|GitHub、クローン URL|
|Maven `groupId`|`io.github.ysmkotm`|`pom.xml`|
|Maven `artifactId`|`practical-app-starter`|`pom.xml`|
|Java ベースパッケージ|`io.github.ysmkotm.practicalappstarter`|ソースの `package`|
|ローカル DB 名|`practical_app_starter`|開発用 PostgreSQL（`artifactId` の snake_case。ハイフン不可のため）|

採用理由は [`docs/07_decisions/java_package_and_maven_coordinates.md`](../07_decisions/java_package_and_maven_coordinates.md)、名称の運用は [`docs/07_decisions/project_name_candidates.md`](../07_decisions/project_name_candidates.md) を参照してください。

### 5.2 レイヤーパッケージ

パッケージ名は小文字で統一し、レイヤー単位で構成します。

例

```text
controller
service
mapper
form
entity
dto
config
exception
```

---

## 6. HTMLテンプレート

テンプレートのフォルダ名は単数形で統一します。

例

```text
employee
department
```

登録画面と編集画面は `form.html` を共通利用します。

---

## 7. DB

テーブル名・カラム名は別途 DB設計ルールに従います。

詳細は、[`docs/02_rules/db.md`](db.md) を参照してください。

---

## 8. Javaメソッドの prefix

Service および Mapper の public メソッドでは、処理の性質に応じて次の prefix を使い分けます。
Controller のメソッド命名は [`docs/02_rules/coding.md`](coding.md) §7 を参照してください。
Service・Mapper のメソッド命名は [`docs/02_rules/coding.md`](coding.md) §8・§9 を参照してください。

|prefix|用途|例|
|---|---|---|
|`search`|検索画面など、複数の検索条件を組み合わせて一覧を取得する|`searchEmployees`|
|`find`|ID・コード・状態などの条件を指定してデータを取得する|`findByCodeType`、`findDepartments`|

- メソッド名だけでは対象や条件が分かりにくい場合は、対象名や条件を名前に含めます（例：`search` ではなく `searchEmployees`）。
- 論理削除を除外する通常の取得では、メソッド名に削除条件を含めない（`deleted_flg = false` は SQL で明示する）。本プロジェクトではこれをデフォルトとする（[`docs/02_rules/db.md`](db.md) §1・§3 参照）。
- 削除済みレコードを含めて取得する場合のみ、メソッド名で明示する（例：`findDepartmentsIncludingDeleted`）。
- `Active` を論理削除除外の意味に使用しない（在籍区分 `ACTIVE` 等の業務ステータスと混同しやすいため）。

### 単数形・複数形（Service / Mapper）

- メソッド名に取得対象を含める場合、複数件を返すメソッドは対象名を複数形とする。
  - 例：`searchEmployees`、`findDepartments`、`findEmployeeStatuses`
- 1件を返すメソッドは、対象名を単数形とする。
  - 例：`findEmployeeById`
- Mapperでは、対象がインタフェース名から明らかな場合、対象名を省略して条件を表す名前としてよい。
  - 例：`CommonCodeMapper#findByCodeType`

---

## 9. 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`docs/02_rules/coding.md`](coding.md)|コーディングルール（Controller・Service・Mapper のメソッド命名）|
|[`docs/02_rules/documentation.md`](documentation.md)|ドキュメント記法ルール|
|[`docs/02_rules/db.md`](db.md)|DB設計ルール|
|[`docs/README.md`](../README.md)|開発ドキュメント索引|
