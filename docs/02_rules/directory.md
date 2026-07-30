# ディレクトリ構成ルール

**Document Version** : 1.0

**更新日** : 2026/07/30

---

## 1. 基本方針

本プロジェクトでは、Javaソースはレイヤー単位、HTMLテンプレートは機能単位で管理します。

- Javaソースは役割ごとにパッケージを分けます。
- HTMLテンプレートは画面・機能単位でフォルダを分けます。
- 共通部品は専用フォルダへ配置します。
- シンプルで理解しやすい構成を優先します。

---

## 2. Javaパッケージ構成

Javaソースは、以下のレイヤー構成を基本とします。

```text
io.github.ysmkotm.practicalappstarter
├── controller
├── service
├── mapper
├── entity
├── form
├── dto
├── config
├── exception
└── PracticalAppStarterApplication.java
```

ベースパッケージ・Maven 座標の決定値は [`docs/07_decisions/java_package_and_maven_coordinates.md`](../07_decisions/java_package_and_maven_coordinates.md) を正とします。

|パッケージ|内容|
|---|---|
|`controller`|リクエスト受付・画面遷移|
|`service`|業務処理|
|`mapper`|データベースアクセス|
|`entity`|テーブルに対応する Entity|
|`form`|画面入力の受け渡し（検索・登録・更新）|
|`dto`|Entity 単体では表現できないデータ（JOIN 結果・一覧表示用等）|
|`config`|設定クラス|
|`exception`|例外処理|

`model` パッケージは作成しません。Spring MVC の `Model` は画面へデータを渡す仕組みとして使用しますが、Java クラスの配置先としては使用しません。

Entity・Form・DTO の役割分担は [`docs/02_rules/coding.md`](coding.md) §4 を正本（SSOT）として参照してください。採用背景は [`docs/07_decisions/entity_form_dto_roles.md`](../07_decisions/entity_form_dto_roles.md) を参照してください。

---

## 3. HTMLテンプレート構成

HTMLテンプレートは、機能単位でフォルダを作成します。

```text
templates
├── fragments
├── top
│   └── index.html
└── employee
    ├── list.html
    ├── detail.html
    └── form.html
```

|フォルダ|内容|
|---|---|
|`templates/fragments`|共通部品|
|`templates/top`|TOP画面|
|`templates/employee`|社員管理画面|

---

## 4. 共通部品

共通部品は複数画面から利用するテンプレートを配置し、 `templates/fragments` 配下へ配置します。

ファイル名は役割が分かる名称とします。

|用途|ファイル|
|---|---|
|CSS読込|`css.html`|
|JavaScript読込|`scripts.html`|
|ヘッダー|`header.html`|
|サイドバー|`sidebar.html`|

`css.html` は Bootstrap・DataTables に加え、[`static/css/common.css`](../../src/main/resources/static/css/common.css) を読み込みます（[`docs/02_rules/ui.md`](ui.md) §2.5 参照）。

共通フラグメントには、外部ライブラリの読込のみを記載し、画面固有の JavaScript 処理は記述しません。詳細は [`docs/02_rules/ui.md`](ui.md) §2.6 を参照してください。

---

## 5. 画面テンプレート

画面テンプレートは機能単位で管理します。

社員管理画面は `templates/employee` 配下へ配置します。

```text
templates/employee
├── list.html
├── form.html
└── detail.html
```

|ファイル|内容|
|---|---|
|`list.html`|一覧画面|
|`form.html`|登録・編集画面|
|`detail.html`|詳細画面|

登録画面と編集画面は `form.html` を共通利用します。

---

## 6. 静的リソース（CSS）

アプリ全体で利用する共通 CSS は `src/main/resources/static/css` 配下に配置します。

|ファイル|内容|
|---|---|
|`common.css`|外部ライブラリ（DataTables 等）の表示補正（[`docs/02_rules/ui.md`](ui.md) §2.5 参照）|

読込は `templates/fragments/css.html` から行います。

---

## 7. 静的リソース（JavaScript）

画面固有の JavaScript は `src/main/resources/static/js` 配下に、機能単位のフォルダを作成して配置します。

```text
static/js
└── employee
    └── list.js
```

|配置|内容|
|---|---|
|`static/js/{機能名}/`|画面固有の JavaScript|
|画面テンプレート|当該画面からのみ読み込む|

---

## 8. 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`docs/02_rules/coding.md`](coding.md)|コーディングルール（データクラス・レイヤー・MyBatis 等）|
|[`docs/02_rules/naming.md`](naming.md)|命名規則|
|[`docs/02_rules/ui.md`](ui.md)|UI設計ルール（JavaScript 読込方針）|
