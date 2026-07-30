# UI設計ルール

**Document Version** : 1.0

**更新日** : 2026/07/30

---

## 1. 基本方針

本ドキュメントは、画面 UI に関する共通ルールの正本（SSOT）です。

- 画面種別ごとの UI 方針、使用ライブラリの利用範囲、共通レイアウトの考え方を定めます。
- 個別画面の項目・遷移・バリデーション等の詳細は、各画面設計書（[`docs/05_screen/`](../05_screen/)）を正とします。
- 同じ情報を複数箇所へ記載せず、本ドキュメントと画面設計書の役割を分離します。
- 技術選定の背景は [`docs/07_decisions/`](../07_decisions/) の判断記録を参照してください。

|ドキュメント|役割|
|---|---|
|本ドキュメント|画面種別共通の UI 方針・利用ルール|
|画面設計書|画面固有の仕様（検索条件・表示項目・遷移等）|
|[`docs/02_rules/coding.md`](coding.md)|Controller 等のサーバー側実装ルール|
|[`docs/02_rules/directory.md`](directory.md)|テンプレート・共通部品の配置|

---

## 2. 共通レイアウト

Version 1 では、画面テンプレートは Thymeleaf を使用し、Header・Sidebar・Main の3領域で構成します。

### 2.1 画面全体の基本構成

各画面は、以下の3領域で構成します。

```text
+--------------------------------------------------+
| Header                                           |
+----------+---------------------------------------+
| Sidebar  | Main（画面固有コンテンツ）              |
|          |                                       |
+----------+---------------------------------------+
```

- 画面テンプレート（`list.html` 等）は、この基本構成に従います。
- 画面ごとに変わるのは Main 内のコンテンツです。

### 2.2 Header・Sidebar・Main の役割

|領域|役割|
|---|---|
|Header|アプリ名・ログインユーザー表示等、全画面共通のヘッダー|
|Sidebar|機能メニュー等、全画面共通のナビゲーション|
|Main|各画面固有のタイトル・検索・一覧・フォーム等|

- Header・Sidebar・Main は、複数画面で共通利用する領域です。
- 画面固有の業務コンテンツは Main に配置します。

### 2.3 画面固有コンテンツの配置

- 画面固有の内容（タイトル、検索エリア、一覧、フォーム等）は **Main 領域** に配置します。
- Header・Sidebar には、画面固有の業務コンテンツを直接記述しません。
- Main 領域は HTML の `main` 要素で表現します。
- Main には共通クラス `app-main` を付与します（`flex-grow-1` とコンパクトな余白。定義は [`common.css`](../../src/main/resources/static/css/common.css)）。
- 業務画面の Main 内余白は、情報量を確保するためコンパクトを基本とします。
- ルート画面（TOP）ではパンくずを表示しません。現在地は visible な見出しで示します（業務一覧のページ上部方針は §3.7）。

### 2.4 共通部品（fragments）

- Header・Sidebar・CSS / JavaScript 読込等の共通部品は、`templates/fragments` 配下の fragment を利用します。
- 共通部品の読み込みは `th:fragment` と `th:replace` を使用します（[`docs/02_rules/coding.md`](coding.md) §1）。
- ファイル配置・ファイル名は [`docs/02_rules/directory.md`](directory.md) §4 を参照してください。
- 画面レイアウトの fragment 化は、**複数画面で同じ構造の再利用実績ができてから** 行います。Version 1 では、単一画面の HTML の読みやすさを優先し、先行共通化は行いません。

### 2.5 Bootstrap 利用方針

Version 1 では、UI のレイアウト・部品に Bootstrap を利用します。

- レイアウトには Bootstrap を利用する。
- 独自 CSS は必要最小限とする。
- UI 部品は Bootstrap コンポーネントを優先して利用する。
- 余白（spacing）は Bootstrap Utility Class を利用する。
- Bootstrap 標準で表現できない **外部ライブラリ（DataTables 等）の表示ずれ** は、[`static/css/common.css`](../../src/main/resources/static/css/common.css) で最小限補正します。補正内容は同ファイルを正とし、本ドキュメントへ重複記載しません。

Bootstrap クラスの詳細な使い分け、ボタン色、アイコン、レスポンシブ対応、アクセシビリティ等は、Version 1 時点では個別クラス名まで固定しません。必要になった段階で本章を追記します。

### 2.6 JavaScript読込方針

- 共通で利用する外部ライブラリ（jQuery、DataTables 等）は、`templates/fragments` の共通フラグメントから読み込む。
- 共通フラグメントにはライブラリの読込のみを記載し、画面固有の初期化処理は記述しない。
- 画面固有の JavaScript は `src/main/resources/static/js` 配下に配置し、当該画面のテンプレートからのみ読み込む（[`docs/02_rules/directory.md`](directory.md) §6 参照）。

---

## 3. 一覧画面

Version 1 では、一覧画面の表形式 UI に DataTables を使用します。

採用理由・検討経緯は [`docs/07_decisions/datatables_list_screen.md`](../07_decisions/datatables_list_screen.md) を参照してください。

### 3.1 適用範囲

- 画面設計書の「画面種類」が **一覧** の画面に適用します。
- 登録・編集・詳細画面には適用しません。

### 3.2 責務分担

|処理|担当|Version 1|
|---|---|---|
|検索（画面上部の条件）|Spring Boot / MyBatis|サーバー側で実行|
|一覧データ取得|Spring Boot / MyBatis|検索条件に合致する全件を取得|
|ページング|DataTables|クライアントサイド|
|列ソート|DataTables|クライアントサイド|
|DataTables 標準の全文検索|使用しない|`searching: false`|

### 3.3 検索

- 検索条件は画面上部のフォームで入力します。
- 検索実行は GET（クエリパラメータ）とします。
- DataTables 付属の検索ボックスは使用しません。
- 検索条件の項目・検索方式（部分一致等）は各画面設計書 §5.2 で定義します。

### 3.4 ページング・ソート

- ページング・列ソートは DataTables のクライアントサイド機能を利用します。
- MyBatis の SQL には Version 1 では `LIMIT` / `OFFSET` を付けません。
- 初期ソート列・昇降順は各画面設計書 §5.3 で定義します。
- 列ヘッダクリックによるソート変更は DataTables の標準動作に任せます。

### 3.5 DataTables 共通設定（Version 1 デフォルト）

|設定|値|備考|
|---|---|---|
|`searching`|`false`|DataTables 標準検索を無効化|
|`paging`|`true`||
|`ordering`|`true`||
|ページサイズ初期値|`10`|変更時は本節を更新|

画面固有の設定（初期ソート列、操作列のソート不可等）は、各画面の JavaScript 初期化で指定します。

### 3.6 実装構成

- 一覧テンプレートは `list.html` とします（[`docs/02_rules/directory.md`](directory.md) §5）。
- 一覧表は `<table>` 要素とし、DataTables 初期化対象の id を付与します。
- jQuery・DataTables は `fragments/scripts.html` から読み込みます（§2.6 参照）。
- 画面固有の DataTables 初期化処理は、当該画面の JavaScript から行います（§2.6 参照）。
- 操作列はソート対象外とします（`orderable: false` 等）。
- 検索実行時は GET により画面を再描画し、返却 HTML 上のテーブルを DataTables で初期化します。
- DataTables と Bootstrap 組み合わせ時の表示補正は、§2.5 に従い `common.css` を利用します。

### 3.7 一覧画面のレイアウト

業務一覧画面では、1 画面あたりの情報量と操作性を優先します。

#### ページ上部

- 左側にパンくず、右側に当画面の主要操作（例：新規登録）を **同一行** に配置します。
- パンくず最終項目で現在地を示し、画面上に独立した画面タイトル見出しを **重複表示しません**。
- アクセシビリティのため、必要に応じて `h1` を `visually-hidden` で保持します。
- パンくずは Main 内に配置します（§2.3）。

#### 検索エリア

- フォーム部品・ボタンはコンパクトサイズ（Bootstrap の sm サイズ相当）を基本とします。
- PC 幅ではラベルと入力欄を同一行に近い配置とし、狭い幅では Bootstrap グリッドで折り返します。
- 検索条件の項目・検索方式は各画面設計書 §5.2 を正とします（§3.3）。

#### 一覧表

- テーブルはコンパクト表示を基本とします。
- 操作列は折り返さないようにします。

#### DataTables コントロール配置

- 表示件数選択・件数表示・ページングは **一覧表の上部のみ** に配置します。下部には同じコントロールを表示しません。
- 表示件数選択と件数表示は左側、ページングは右側にまとめます。
- `dom` 等の画面固有設定は、当該画面の JavaScript 初期化で指定します。共通化は複数一覧画面の実績後に検討します（[`docs/01_project/backlog.md`](../01_project/backlog.md) BLG-CMN-010 参照）。

### 3.8 画面設計書との関係

|記載場所|内容|
|---|---|
|本ドキュメント §3|一覧画面共通の UI・DataTables 利用方針|
|本ドキュメント §3.7|一覧画面のレイアウト（ページ上部・検索・コントロール配置）|
|本ドキュメント §4|入力画面共通の UI 方針（レイアウト・操作ボタン行・`btn-form-action`）|
|本ドキュメント §4.2|操作ボタン行の配置・サイズ（初版。EMP002 由来）|
|画面設計書 §5.2|検索条件・検索方式|
|画面設計書 §5.3|表示列・初期ソート列・操作ボタン|
|画面設計書 §8|URL・Controller・Form・Mapper・画面固有レイアウト等|
|[`docs/02_rules/coding.md`](coding.md) §7|一覧 GET 時のサーバー側実装ルール|

### 3.9 将来拡張

大量データ対応が必要になった場合、DataTables サーバーサイド処理への移行を検討します。

移行時は、本ドキュメント・判断記録・対象画面設計書の §8 を更新します。

---

## 4. 入力画面

本章では、登録・編集画面（`form.html`）に共通する UI 方針を定義します。Version 1 では EMP002 を初適用例とし、以下を暫定の標準とします。確定版は BLG-CMN-003 整備時に見直します。

画面固有の入力項目・バリデーションは各画面設計書 §5・§6 を正とします。EMP002 固有の実装詳細は [`EMP002_社員登録・編集.md`](../05_screen/EMP002_社員登録・編集.md) §8.6 を参照してください。

### 4.1 入力フォームのレイアウト

- 入力項目は Bootstrap の2列グリッド（`col-md-6`）を基本とします。
- 各項目はラベル左・入力右の横並びとします。ラベル幅は [`common.css`](../../src/main/resources/static/css/common.css) の `form-label-fixed`（`min-width: 7rem`）で揃えます。
- 備考など全幅項目は `col-12` とし、他項目と同様にラベル左・入力右の横並びとします。
- フォーム外枠は `card` とし、内側余白は `common.css` のフォームカード用スタイル（例：`employee-form-card`）で調整します。

### 4.2 操作ボタン行

フォーム下部の操作ボタンは、**フォーム外枠（カード内側）の左右余白を基準** に配置します。入力欄の左端には揃えません。

|区分|配置|ボタンサイズ|備考|
|---|---|---|---|
|危険操作（削除等）|左端|`btn-sm`|主要操作より小さく表示し、役割を区別する|
|主要操作（一覧へ戻る・登録・更新等）|右端|`btn-form-action`|同一サイズで統一する|

#### レイアウト（Bootstrap）

- 操作ボタン行のラッパーに `form-actions` を付与します（[`common.css`](../../src/main/resources/static/css/common.css)）。
- 区切り線・上余白は、ボタン行の直上に `border-top` 等のラッパーで確保します（EMP002 `form.html` 参照）。
- **編集モード**（削除あり）：`d-flex flex-wrap justify-content-between align-items-center gap-2` で、左に削除・右に主要操作を配置します。
- **登録モード**（削除なし）：`d-flex flex-wrap justify-content-end gap-2` で、主要操作を右端に配置します。
- 主要操作が複数ある場合は、右側グループを `d-flex gap-2` でまとめます。

#### 主要操作ボタンのサイズ（`btn-form-action`）

- クラス定義は [`common.css`](../../src/main/resources/static/css/common.css) の `btn-form-action` を正とします。
- Bootstrap の `btn-sm` とデフォルトの中間サイズとし、`min-width` で短いラベル（登録・更新等）の幅を揃えます。
- 色は Bootstrap の `btn-primary`・`btn-outline-secondary` 等と組み合わせます。

#### 標準化・共通化（検討中）

- Thymeleaf fragment 化、必須表示、確認ダイアログ要否等は [`backlog.md`](../01_project/backlog.md) の BLG-CMN-003・BLG-CMN-016・BLG-CMN-015 を参照してください。

---

## 5. 詳細画面

Version 1 では整備予定です。

本章では、詳細画面（`detail.html`）に共通する UI 方針を定義します。想定する内容は以下のとおりです。

- 参照項目の表示形式（ラベル・値の並び）
- 編集・戻る等の操作ボタン配置

画面固有の表示項目・遷移は各画面設計書 §5・§7 を正とします。

---

## 6. ボタン・画面遷移

Version 1 では整備予定です。

本章では、ボタン名称・配置・画面間遷移の共通ルールを定義します。想定する内容は以下のとおりです。

- 主要操作ボタン（検索・クリア・新規登録・保存・削除等）の命名
- 一覧・入力・詳細画面間の遷移パターン
- 同一画面内操作（検索・ページング等）と画面遷移の記載分担（画面設計書 §5 / §7）

Controller の HTTP メソッド・メソッド命名は [`docs/02_rules/coding.md`](coding.md) §7 を参照してください。

---

## 7. メッセージ・バリデーション表示

本章の共通方針の完全版は Version 1.x（[`BLG-CMN-001`](../01_project/backlog.md)）で整備する。

**Version 1.0 暫定**（[`PRG-CMN-003`](../01_project/progress.md)）: 登録・更新・削除成功時は `RedirectAttributes` の Flash 属性 `successMessage` を渡し、遷移先の EMP001 で Bootstrap `alert-success`（閉じるボタン付き）を表示する。フィールドエラー・グローバルエラーは EMP002 の既存実装（`is-invalid` / `alert-danger`）を継続する。画面固有の文面・配置は各画面設計書を正とする。

本章で今後確定する想定内容は以下のとおりです。

- フィールドエラーの表示位置・形式
- 処理成功・失敗メッセージの表示方法
- Bootstrap 等を利用した表示スタイル（§2.5 Bootstrap 利用方針を参照）

バリデーション内容そのものは各画面設計書 §6 を正とします。サーバー側の実装ルールは [`docs/02_rules/coding.md`](coding.md) §5 を参照してください。

---

## 8. 使用ライブラリ

UI 関連ライブラリの一覧は [`docs/01_project/project.md`](../01_project/project.md) §3 も参照してください。

|ライブラリ|用途|本プロジェクトでの利用方針|
|---|---|---|
|Bootstrap|レイアウト・UI コンポーネント|§2.5 Bootstrap 利用方針を参照|
|jQuery|JavaScript 基盤|DataTables の依存ライブラリとして利用|
|DataTables|表形式 UI|一覧画面のページング・列ソート（§3）|

Thymeleaf・MyBatis 等、サーバー側の技術スタックは [`docs/01_project/project.md`](../01_project/project.md) §3 を正とします。

---

## 9. 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`docs/07_decisions/datatables_list_screen.md`](../07_decisions/datatables_list_screen.md)|一覧画面への DataTables 採用判断|
|[`docs/02_rules/coding.md`](coding.md)|Controller 実装ルール|
|[`docs/02_rules/directory.md`](directory.md)|テンプレート・共通部品の配置|
|[`docs/05_screen/_template.md`](../05_screen/_template.md)|画面設計書テンプレート|
|[`docs/01_project/project.md`](../01_project/project.md)|使用技術一覧|
