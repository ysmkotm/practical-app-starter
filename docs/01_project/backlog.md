# バックログ

**Document Version** : 1.5

**更新日** : 2026/08/05

本ドキュメントは、設計・実装を進める上で **今後検討が必要な事項** を管理します。決定済みの内容そのものは記載しませんが、検討の経緯は履歴として残します。

---

## 1. 目的

- 保留中の設計事項・技術判断・改善案を一覧で把握する
- いつ・どのタイミングで検討するかを記録し、実装時の見落としを防ぐ
- 判断が完了した事項の履歴を残し、関連する判断記録・設計書・実装へたどれるようにする

---

## 2. 記載範囲

情報の種類ごとの管理先は [`docs/README.md`](../README.md) §5 を正とします。本ドキュメントは **今後検討が必要な事項** のみを持ちます。

**記載する**: 設計上の保留、未決定の技術判断、実装中に見つかった課題、検討タイミング

**記載しない**: 決定済みの詳細（→ `07_decisions`）、機能・画面・テーブルの一覧そのもの（→ features / screens / tables）、作業の現在地（→ progress）、指摘索引（→ review_findings）、確定仕様（→ 各設計書）

`project.md` §4 の項目が具体化し設計判断が必要になったら、本ドキュメントへ個別追加します（両方へ同じ内容は書かない）。

---

## 3. 記載ルール

### ID の付け方

|プレフィックス|セクション|意味|
|---|---|---|
|`PRJ`|プロジェクト全体|スターターキット・プロジェクト横断の設計・運用（プロジェクト名等）|
|`CMN`|共通機能|アプリケーション内の横断的な設計（メッセージ表示、例外処理、共通 UI 等）|
|`EMP` 等|各機能|機能固有の設計判断（機能 ID に合わせる）|

- ID は `BLG-{プレフィックス}-{連番3桁}` とする（例：`BLG-CMN-001`）。
- 完了後も ID は変更・再利用しない（履歴・会話ログとの対応のため）。

### 状態の定義

|状態|意味|
|---|---|
|未検討|検討が必要だが、まだ着手していない|
|検討中|設計・実装の検討を進めている|
|保留|検討は済んでいるが、現時点では着手しない（Version 後回し、今回見送り等）。理由や再検討条件を関連情報へ記載する|
|完了|判断または対応が完了した。関連情報へ判断記録・設計書・実装箇所等を記載する|

「完了」と「保留」の違いは、前者が結論に至ってクローズした項目、後者が意図的に先送りした項目である点です。

### 検討タイミングの書き方

イベントベースで記載し、いつ判断するかが分かるようにします。Version 区切りは [`project.md`](project.md) §4・[`version1_publish_scope.md`](../07_decisions/version1_publish_scope.md) に合わせます。

|書き方の例|用途|
|---|---|
|Version 1.0 公開前（必須）|公開ブロッカーとなる判断|
|Version 1.0 公開前（推奨）|公開品質向上。無くても公開可|
|Version 1.0 任意 / 公開後でも可|Version 1.0 でやらなくてもよい|
|Version 1.x|公開後の増分（Security・UI 段階共通化等）|
|Version 2.x 以降|共通基盤・AI 標準・テスト体系など再利用資産化|
|EMP002 画面設計・実装時|特定画面の設計・実装に紐づく判断|
|性能問題が発生した場合|条件が揃ったときに再検討する判断|

### 関連情報列

§4 の表にある **関連情報** 列へ、次のようなリンクやメモを記載します。未完了時は `-` とし、状態の変化に応じて追記します。

|記載する内容|例|
|---|---|
|判断記録|`docs/07_decisions/` 配下の該当ファイルへの Markdown リンク|
|画面設計書|[`docs/05_screen/EMP002_….md`](../05_screen/)|
|画面一覧・機能一覧|[`docs/03_system/screens.md`](../03_system/screens.md) の EMP002 行|
|関連する backlog 項目|BLG-CMN-001（親方針への参照）|
|実装箇所|`EmployeeController#showCreate` 等|
|保留・見送りの理由|Version 2 で対応予定、今回は見送り（理由）|

関連ドキュメント・判断記録・実装箇所を個別の列に分けず、1列にまとめます。項目ごとに必要な情報の種類が異なるため、空列が増えない構成とします。

### 内容列

- 内容列は簡潔に保つ（目的と対象の確定が分かる程度。1〜3文を目安とする）
- 既に決定済みの作業計画レベルの詳細（着手内容の例・完了の目安等）は [`progress.md`](progress.md) 側の該当 PRG に記載し、本ドキュメントには書かない
- 判断理由・比較検討の詳細は [`docs/07_decisions/`](../07_decisions/) へ委譲し、関連情報列からリンクする

### 運用

- 新しい保留事項が発生したら §4.1 へ追加する。
- §4.1／§4.2 はカテゴリ別の表に分けず、未完了・完了を各1表とする。カテゴリは「スコープ」列（`PRJ`／`CMN`／`EMP` 等）で示す
- 追加前に、次を確認し重複を避ける。
  - [`project.md`](project.md) §4 のロードマップに留めるべき中長期項目ではないか
  - [`docs/07_decisions/`](../07_decisions/) で既に決定済みではないか
  - [`features.md`](../03_system/features.md)・[`screens.md`](../03_system/screens.md) で管理すべき未実装作業ではないか
  - [`progress.md`](progress.md) で管理すべき作業スライスではないか
- [`docs/07_decisions/`](../07_decisions/) で **ステータス：検討中** の判断がある場合は、原則として本ドキュメントにも1行登録し、関連情報列から判断記録へリンクする。
- 判断記録の §7「今後の対応」に将来検討がある場合、着手タイミングが見えているものだけ本ドキュメントへ昇格する。条件付きの再検討（例：性能問題発生時）は判断記録に残し、無理に登録しない。
- 判断または対応が完了したら、**原則として行を削除せず**、状態を「完了」に更新し §4.2 へ移動する。決定内容は [`docs/07_decisions/`](../07_decisions/) 等へ記録し、関連情報列から参照できるようにする。
- 検討は済んだが現時点では着手しない場合は、状態を「保留」に更新し **§4.1 に残す**。理由や再検討条件を関連情報へ記載する（完了と混ぜない）。
- [`project.md`](project.md) §4（Version 1.0）と本ドキュメント §4 を定期的に照合し、Version 1.0 完成に必要な未決定が揃っているか確認する。

---

## 4. バックログ一覧

### 4.1 未完了

未検討・検討中・保留の項目です。状態列で区別します。

|ID|スコープ|項目|内容|検討タイミング|状態|関連情報|
|---|---|---|---|---|---|---|
|BLG-PRJ-002|PRJ|開発ドキュメント全体のレビュー・整理（ブラッシュアップ）|実運用の結果に合わせ、docs／rules／progress／backlog／索引などの章構成・役割分担・SSOT を見直す。Version 1.0 向けの公開整理は PRG-PRJ-001 で実施済み。残作業は Version 1.x（UI 標準化は BLG-CMN-015）|Version 1.0 公開前（必須：公開向け縮小）／残作業は Version 1.x|未検討|BLG-CMN-015（UI 標準化の実装側）、[`PRG-PRJ-001`](progress.md)、[`version1_publish_scope.md`](../07_decisions/version1_publish_scope.md)、[`docs/README.md`](../README.md)|
|BLG-PRJ-004|PRJ|AI コンテキスト設計の検証（Claude Code）|Claude Code を用い、README・progress・backlog・knowledge・docs 構成など **ドキュメント種別ごと** に AI のプロジェクト理解・レビュー品質がどう変わるかを L1 ミニ実験で検証する。目的は「AIが理解しやすいプロジェクト設計（コンテキスト設計）」の明確化。実験計画・EXP-ID・手順の正本は ai_log 側に置き、本項目はリマインダーのみ|Version 3.x または余力（Version 1.0 では着手しない）|未検討|非公開のAI協調開発ログ（ローカル）、[`project.md`](project.md) §4|
|BLG-PRJ-005|PRJ|正式なテスト資料の管理方針|動作確認チェックリストとは別に、再利用するテスト方針・ケース・回帰観点の管理方針を決める。候補置き場は `docs/08_test`。当面は `private/verification`（公開しない）|Version 2.x 以降（Version 1.0 では着手しない。当面は `private/verification`。公開しない）|未検討|[`verification_checklist_and_test_assets.md`](../07_decisions/verification_checklist_and_test_assets.md)、`private/verification/prg_emp_009_crud_checklist.md`、[`progress.md`](progress.md) §3|
|BLG-PRJ-006|PRJ|AI協調開発フローの標準化（工程別標準プロンプト）|工程ごとに迷わず使える標準プロンプトをおおむね1つ用意する形で、AI協調開発フローを標準化する。現行の `prompts/` と [`ai.md`](../02_rules/ai.md) 運用を維持し、本項目は Version 2.x 向けリマインダーとする|Version 2.x 以降（共通基盤・AI協調開発標準の抽出時。Version 1.0 では着手しない）|未検討|[`project.md`](project.md) §4、[`docs/02_rules/ai.md`](../02_rules/ai.md)、[`prompts/README.md`](../../prompts/README.md)、BLG-PRJ-004、BLG-PRJ-011、[`progress.md`](progress.md) §3|
|BLG-PRJ-011|PRJ|AI協調開発フローの再現性検証（小規模 CRUD）|現在の AI 協調開発フローだけで、社員管理と同等品質の小規模 CRUD を再現できることを実証する。対象は **部署管理（department）**（DEP001／DEP002。テーブル定義済み）。UI 共通化（BLG-CMN-015）・インライン編集（BLG-CMN-021）はスコープ外。作業スライスは [`progress.md`](progress.md) §2|Version 1.x（着手中）〜 Version 2.x（BLG-PRJ-006 標準化の実証と接続）|検討中|[`project.md`](project.md) §4、[`version1_publish_scope.md`](../07_decisions/version1_publish_scope.md)、BLG-PRJ-006、BLG-CMN-015（スコープ外・後続）、BLG-CMN-021（インライン編集は保留）、[`progress.md`](progress.md) §2（PRG-DEP-001〜006）、[`features.md`](../03_system/features.md)、[`screens.md`](../03_system/screens.md)|
|BLG-PRJ-013|PRJ|課金ターゲット像の仮説検証|隣接課金向けの課金ターゲット像を検証する。仮説は [`public_offering_strategy.md`](../07_decisions/public_offering_strategy.md) §5.6。認知ターゲットは [`audience_target.md`](../07_decisions/audience_target.md) で別管理。収益化本体の着手は Version 3.x を維持し、本項目は仮説検証のみ|Version 1.x 以降（公開後のフィードバックで検証。Version 1.0 では着手しない）|未検討|[`public_offering_strategy.md`](../07_decisions/public_offering_strategy.md) §5.3・§5.6、[`audience_target.md`](../07_decisions/audience_target.md)、BLG-PRJ-006、BLG-PRJ-011、[`project.md`](project.md) §4|
|BLG-CMN-002|CMN|例外処理の共通方針|`exception` パッケージの使い方、業務例外とシステム例外の分け方、404・存在しないリソース時の HTTP ステータスと画面表示の基本方針|Version 1.x（V1.0 は暫定 404 のまま）|未検討|[`docs/02_rules/directory.md`](../02_rules/directory.md) §2 `exception`、[`progress.md`](progress.md) §8|
|BLG-CMN-003|CMN|入力・詳細・ボタン遷移の共通 UI 方針|[`ui.md`](../02_rules/ui.md) §4〜§6 の共通ルールを整備する。§4 には EMP002 で確定した初版を反映済み。確定版では必須表示・確認ダイアログ・詳細／ボタン遷移等を追加する|Version 1.x（BLG-CMN-015 と同時期）|未検討|BLG-CMN-015（親項目）、[`docs/02_rules/ui.md`](../02_rules/ui.md) §4〜§6、[`EMP002_社員登録・編集.md`](../05_screen/EMP002_社員登録・編集.md) §8.6|
|BLG-CMN-013|CMN|サイドバーのハンバーガーメニュー・折りたたみ化|狭い画面向けにサイドバーを折りたたみ可能にするか。現時点は固定幅（150px）+ `flex-shrink: 0` で対応|Version 1.0 任意|未検討|[`fragments/sidebar.html`](../../src/main/resources/templates/fragments/sidebar.html)、[`common.css`](../../src/main/resources/static/css/common.css)|
|BLG-CMN-014|CMN|業務画面の表示タイトル（visible h1）の要否|パンくず最終項目のみで足りるか、Main 内に `h2` 等の画面タイトルを表示するか|Version 1.0 任意|未検討|[`docs/02_rules/ui.md`](../02_rules/ui.md) §3.7、EMP001・EMP002 テンプレート|
|BLG-CMN-008|CMN|一覧画面の縦スクロール・ヘッダー固定|`scrollY`・テーブルヘッダー固定・一覧内部スクロールの採用可否。画面全体とテーブル内の二重スクロール、件数が少ない場合の空白、表示崩れを確認する|Version 1.x または実画面で問題になったとき|未検討|[`docs/02_rules/ui.md`](../02_rules/ui.md) §3.7、[`EMP001_社員一覧.md`](../05_screen/EMP001_社員一覧.md) §8.6|
|BLG-CMN-010|CMN|DataTables 初期化の共通化|`dom` 等の一覧画面共通設定を共通 JS へ切り出すか。BLG-CMN-015 の棚卸し結果に基づき、2 件目以降の一覧画面実装前または同時に着手する|Version 1.x（2 件目以降の一覧、または BLG-CMN-015）|未検討|BLG-CMN-015（親項目）、[`docs/02_rules/ui.md`](../02_rules/ui.md) §3.7、[`static/js/employee/list.js`](../../src/main/resources/static/js/employee/list.js)|
|BLG-CMN-011|CMN|ページ上部レイアウトの fragment 化|パンくず＋主要操作の 1 行構成を fragment 化するか。EMP001／EMP002 で構造は確定済み。汎用 fragment 化は BLG-CMN-015 の整理後|Version 1.x（BLG-CMN-015 と同時期）|未検討|BLG-CMN-015（親項目）、[`docs/02_rules/ui.md`](../02_rules/ui.md) §2.4・§3.7、[`breadcrumb-employee.html`](../../src/main/resources/templates/fragments/breadcrumb-employee.html)|
|BLG-CMN-012|CMN|検索項目が多い画面でのレイアウト|検索条件が EMP001 より多い画面での横並び・折り返し方針|該当する検索画面の設計・実装時（Version 1.x 以降）|未検討|[`docs/02_rules/ui.md`](../02_rules/ui.md) §3.7|
|BLG-CMN-015|CMN|EMP001・EMP002 UI パターンの共通化・標準化|EMP001／EMP002 で確定した一覧・登録編集の UI パターンを、他画面でも再利用できるよう共通化・標準化する。Version 1.0 では着手しない|Version 1.x（2 画面目以降で回収。Version 1.0 では着手しない）|未検討|[`progress.md`](progress.md) §3、[`EMP001_社員一覧.md`](../05_screen/EMP001_社員一覧.md) §8.6、[`EMP002_社員登録・編集.md`](../05_screen/EMP002_社員登録・編集.md) §8.6、[`common.css`](../../src/main/resources/static/css/common.css)、[`ui.md`](../02_rules/ui.md) §2.4・§4.2。子項目：BLG-CMN-003、BLG-CMN-010、BLG-CMN-011、BLG-CMN-016、BLG-CMN-019|
|BLG-CMN-016|CMN|入力フォーム操作ボタン行の fragment 化|EMP002 の操作ボタン行を Thymeleaf fragment 化するか（登録：主要操作のみ右寄せ／編集：削除左・主要操作右）|Version 1.x（BLG-CMN-015 と同時期）|未検討|BLG-CMN-015（親項目）、[`ui.md`](../02_rules/ui.md) §4.2、[`form.html`](../../src/main/resources/templates/employee/form.html)、[`common.css`](../../src/main/resources/static/css/common.css)|
|BLG-CMN-017|CMN|DB 制約違反判定の責務・配置・共通化|EMP002 の UNIQUE 違反判定（`PSQLException`・物理制約名）について、責務・配置・共通化を見直す。当面は `EmployeeService` 内 private として維持し、共通基盤は先行して作らない|Version 1.x（2 画面目で同種処理が必要になったとき）|未検討|[`employee.md`](../04_db/table/employee.md) §4、[`EmployeeService`](../../src/main/java/io/github/ysmkotm/practicalappstarter/service/EmployeeService.java)、BLG-CMN-001、[`progress.md`](progress.md) §3|
|BLG-CMN-018|CMN|論理削除済みマスタの現在値補完・妥当性検証の共通化|編集画面での（1）論理削除済みマスタの現在値補完表示（2）マスタ参照値の妥当性検証（登録は未削除のみ、更新は現在値維持可）（3）複数画面への共通化を検討する。EMP002 では画面固有の最小実装済み|Version 1.x（2 画面目以降の登録・編集、または BLG-CMN-015）|未検討|[`emp002_soft_deleted_master_reference.md`](../07_decisions/emp002_soft_deleted_master_reference.md)、[`EMP002_社員登録・編集.md`](../05_screen/EMP002_社員登録・編集.md)|
|BLG-CMN-019|CMN|登録・編集画面の更新／削除 form 標準構造|EMP002 で採用した「更新用 form と削除専用 form の分離」を、入力画面の共通方針として標準化する|Version 1.x（BLG-CMN-015 / BLG-CMN-003 整備時）|未検討|BLG-CMN-015（親項目）、BLG-CMN-003、BLG-CMN-016、[`form.html`](../../src/main/resources/templates/employee/form.html)、[`form.js`](../../src/main/resources/static/js/employee/form.js)、[`EMP002_社員登録・編集.md`](../05_screen/EMP002_社員登録・編集.md) §5.3・§8.1・§8.6、[`ui.md`](../02_rules/ui.md) §4・§6|
|BLG-CMN-020|CMN|Form 前後空白除去の対象文字（半角／全角・Unicode）|Form の前後空白除去について、半角のみか全角・Unicode 空白も含めるかを決める。現状の `trimToNull` は `String#trim()` 依存。共通化（配置）は BLG-CMN-006|Version 1.0 任意|未検討|[`EmployeeForm`](../../src/main/java/io/github/ysmkotm/practicalappstarter/form/EmployeeForm.java) `trimToNull`、[`coding.md`](../02_rules/coding.md) §5、[`EMP002_社員登録・編集.md`](../05_screen/EMP002_社員登録・編集.md) §6、BLG-CMN-006（保留）、`private/verification/prg_emp_009_crud_checklist.md` 7.3.2|
|BLG-CMN-021|CMN|マスタ管理画面でのインライン編集 UI パターンの検証|一覧上での更新・削除など、マスタ管理向けのインライン編集 UI パターンを検証するかどうか。現状の社員管理・部署管理は一覧＋登録編集の別画面パターン|マスタ管理画面が複数揃った段階、または任意のタイミング|保留|今回の部署管理 CRUD（[`BLG-PRJ-011`](backlog.md)）では、再現性検証という目的と [`BLG-CMN-015`](backlog.md) の前提（2 画面目以降での回収）に合わせ、EMP001／EMP002 と同じ一覧＋登録編集の別画面パターンを採用し、インライン編集は意図的に対象外とした|
|BLG-CMN-001|CMN|メッセージ・バリデーション表示の共通方針|フィールドエラー、処理成功・失敗メッセージの表示位置・形式・ライブラリ利用方針を [`ui.md`](../02_rules/ui.md) §7 として確定する。Version 1.0 の成功メッセージ暫定は PRG-CMN-003／BLG-EMP-005 で完了。本項目は完全版の方針整備|Version 1.x（完全版）。V1.0 は暫定実装で可|保留|成功メッセージ暫定は PRG-CMN-003 完了。フィールドエラー・グローバルエラーは EMP002 で暫定実装（Bootstrap `is-invalid`）。[`emp002_post_validation_groups.md`](../07_decisions/emp002_post_validation_groups.md)、[`docs/02_rules/ui.md`](../02_rules/ui.md) §7、[`version1_publish_scope.md`](../07_decisions/version1_publish_scope.md)|
|BLG-CMN-004|CMN|Service レイヤーの `@Transactional` 方針|参照系処理への `@Transactional(readOnly = true)` 付与、クラス／メソッド単位の適用範囲、書き込み処理実装時との整合|EMP002 登録・更新処理（POST）実装時、または Service の再利用範囲が明確になった段階|保留|現状は付与しない（EMP001 レビュー）。[`employee_service_emp001_review.md`](../07_decisions/employee_service_emp001_review.md)|
|BLG-CMN-005|CMN|共通コード種別定数の共通化|コード種別文字列（例：`EMPLOYEE_STATUS`）を共通定数クラスまたは enum へ切り出すか|複数の Service やクラスから同一コード種別を参照する必要が生じた段階|保留|現状は `EmployeeService` 内 private 定数（EMP001 レビュー）。[`employee_service_emp001_review.md`](../07_decisions/employee_service_emp001_review.md)|
|BLG-CMN-006|CMN|Form 文字列正規化（`trimToNull`）の共通化|各 Form の `private static trimToNull` を共通ユーティリティ等へ切り出すか。除去対象文字種（半角／全角等）は [`BLG-CMN-020`](backlog.md)|EMP002 Form 実装時、または同一ロジックの重複が発生した段階|保留|現状は各 Form 内に配置（PRG-EMP-002 レビュー時）。[`coding.md`](../02_rules/coding.md) §5。除去対象は BLG-CMN-020|
|BLG-CMN-007|CMN|LIKE 検索のワイルドカードエスケープ共通方針|ユーザー入力を LIKE 条件に使用する際の `%`・`_`・`\` の扱い（許容するか、エスケープするか）および実装方式（SQL / ユーティリティ / Form）を [`coding.md`](../02_rules/coding.md) §9 として共通化するか|2 件目以降の検索画面実装時、または LIKE 検索の実装パターンが複数に増えた段階|保留|現状は EMP001 の画面設計書 §8.5 に従い Mapper XML 内でエスケープ（PRG-EMP-002）。[`EMP001_社員一覧.md`](../05_screen/EMP001_社員一覧.md) §8.5|
|BLG-EMP-003|EMP|選択肢設定処理の共通化|部署・在籍状態などのプルダウン選択肢を、各 Controller で個別に取得するか、共通メソッドへまとめるか|Version 1.x（EMP001 以外の画面実装時）|未検討|-|
|BLG-EMP-012|EMP|EMP002 入力項目の意味グループ並び替え|氏名・連絡先・所属・勤務情報など意味単位での並び替えを行うか。現状は画面設計書 §3 の 2 列配置を維持|Version 1.0 任意|未検討|[`EMP002_社員登録・編集.md`](../05_screen/EMP002_社員登録・編集.md) §3・§5|
|BLG-EMP-007|EMP|社員番号の採番・形式・一意性ルール|社員番号の採番・形式・一意性の本検討・必要なら実装を行う。当面方針は設計書へ明記済み。編集画面での編集可否も検討事項に追加（2026-08-05）。判断軸は安全性（FK非参照のため編集しても整合性は壊れない。部署コードと同じ）と必要性（自動採番が候補に挙がっていること自体が値へのこだわりの薄さを示し、修正需要は低いと推定）の2つに分けられる。DEP002は部署コード（常に手入力、こだわりの可能性が高い）を編集可能にしたが、社員番号は必要性が低いと判断し据え置き。詳細は関連情報参照|Version 1.0 公開前（推奨：当面方針の明記）／本検討・実装は Version 1.x|保留|当面方針：[`EMP002_社員登録・編集.md`](../05_screen/EMP002_社員登録・編集.md) §5.2・§6、[`employee.md`](../04_db/table/employee.md) §2。再検討条件：Version 1.x。[`employee_code_logical_name.md`](../07_decisions/employee_code_logical_name.md)、[`progress.md`](progress.md) §8・§3。編集可否の比較検討（安全性／必要性の軸）は[`DEP002_部署登録・編集.md`](../05_screen/DEP002_部署登録・編集.md) §10参照|
|BLG-EMP-013|EMP|論理削除済み社員番号・メールの再利用可否|論理削除済み社員番号・メールの再利用可への変更を検討・必要なら実装する。当面方針（再利用不可）は設計書へ明記済み|Version 1.0 公開前（推奨：当面方針の明記）／再利用可への変更検討は Version 1.x|保留|当面方針：[`EMP002_社員登録・編集.md`](../05_screen/EMP002_社員登録・編集.md) §6、[`employee.md`](../04_db/table/employee.md) §4、[`emp002_post_validation_groups.md`](../07_decisions/emp002_post_validation_groups.md) §3.4。再検討条件：Version 1.x。BLG-CMN-018、[`progress.md`](progress.md) §8・§3|
|BLG-EMP-008|EMP|Service が `EmployeeSearchForm` を直接受け取る構成|`EmployeeService` が Web 層の Form を引数として受け取る現構成を維持するか、検索条件クラス等へ分離するか|EMP002 登録・更新処理（POST）実装時、または Service の再利用範囲が明確になった段階|保留|現状維持（EMP001 レビュー）。[`employee_service_emp001_review.md`](../07_decisions/employee_service_emp001_review.md)|
|BLG-EMP-009|EMP|プルダウン用に Entity をそのまま返す構成|部署・在籍区分の選択肢として `Department`・`CommonCode` Entity をそのまま返すか、選択肢用 DTO 等へ分離するか|EMP002 登録・更新処理（POST）実装時、または Service の再利用範囲が明確になった段階|保留|現状維持（EMP001 レビュー）。[`employee_service_emp001_review.md`](../07_decisions/employee_service_emp001_review.md)|
|BLG-EMP-004|EMP|存在しない社員のエラー処理|編集で対象社員が存在しない場合の HTTP ステータス、画面表示、メッセージの扱い|EMP002 編集画面実装時|保留|暫定：`ResponseStatusException`（404）。最終方針は BLG-CMN-002 確定後に見直し。[`emp002_post_validation_groups.md`](../07_decisions/emp002_post_validation_groups.md)|
|BLG-EMP-010|EMP|社員マスタの排他制御|楽観的ロック（`updated_at` または version カラム）の要否、競合時の画面表示・メッセージ|Version 1.0 完成後、または複数ユーザー同時更新の要件発生時|保留|Version 1.0 では実装対象外。[`EMP002_社員登録・編集.md`](../05_screen/EMP002_社員登録・編集.md) §10|

### 4.2 完了（履歴）

状態が「完了」となった項目をここへ移動し、履歴として残します。

|ID|スコープ|項目|内容|検討タイミング|状態|関連情報|
|---|---|---|---|---|---|---|
|BLG-PRJ-001|PRJ|プロジェクト名の決定|正式名称・ブランド名・Edition・タグラインを決定する|Version 1.0 公開前（必須）|完了|説明路線で確定。正式名称は Practical App Starter — Spring Boot Edition。パッケージ／Maven は [`java_package_and_maven_coordinates.md`](../07_decisions/java_package_and_maven_coordinates.md)。名称は [`project_name_candidates.md`](../07_decisions/project_name_candidates.md)。反映は [`PRG-PRJ-002`](progress.md)|
|BLG-PRJ-003|PRJ|機能・画面一覧の中間状態表現ルール|[`screens.md`](../03_system/screens.md)・[`features.md`](../03_system/features.md) の「状態」列でスライス単位の進捗をどう表すかを検討する項目だった|Version 1.0 任意|完了|**状態列を廃止**し、作業進捗は [`progress.md`](progress.md) に寄せる方針で決着。`tables.md` も同様。判断記録：[`system_overview_lists_without_status.md`](../07_decisions/system_overview_lists_without_status.md)。掲載タイミングは [`BLG-PRJ-014`](backlog.md)|
|BLG-PRJ-014|PRJ|機能・画面・テーブル一覧への掲載タイミング|状態列廃止後、一覧へいつ載せるかを決める|—|完了|**実装することが決まった時点で掲載**する（設計・実装の完了は待たない）。目安は [`progress.md`](progress.md) に該当 PRG を切ったとき、または Version スコープに含めたとき。順序は機能一覧 → 画面・テーブル一覧。設計書／定義書が未作成のときはリンク列を `-`。判断記録：[`system_overview_lists_without_status.md`](../07_decisions/system_overview_lists_without_status.md)|
|BLG-PRJ-007|PRJ|公開ライセンスの決定|コードを OSS で公開することは確定（[`public_offering_strategy.md`](../07_decisions/public_offering_strategy.md) §5.4）。具体ライセンス（Apache-2.0 / MIT 等）を選定し、README・`LICENSE` へ反映する。強いコピーレフト・Source Available のみには閉じない|Version 1.0 公開前（必須）|完了|**Apache License 2.0 を採用**。[`license_selection.md`](../07_decisions/license_selection.md)。`LICENSE` / README / 必要なら `pom.xml` への反映は [`PRG-PRJ-002`](progress.md)|
|BLG-PRJ-008|PRJ|公開・提供方針（公開形態・公開範囲・商用可否・収益化）の決定|主目的の優先順位、公開形態、公開範囲、収益化の時期と形態、ライセンス制約を決める。一度「V1 フル公開・OSS 寄り」で決定後、商用の扱い・公開範囲を絞りたい意向により再検討へ差し戻し、再確定した|Version 1.0 公開前（必須）|完了|**コードは OSS 公開**、`docs` は公開、`private/ai_logs`・`private/contents`・`private/verification` は非公開、`prompts/` は代表のみ公開。進め方は将来の有料教材候補。[`public_offering_strategy.md`](../07_decisions/public_offering_strategy.md) §5。下流：BLG-PRJ-007（完了）、BLG-PRJ-009（完了）、BLG-PRJ-010、PRG-PRJ-002|
|BLG-PRJ-009|PRJ|公開範囲確定に伴う `knowledge/` 配下の整理とリンク切れ解消|公開範囲の確定（[`public_offering_strategy.md`](../07_decisions/public_offering_strategy.md) §5.2）を受け、(1) `knowledge/` を公開・非公開の観点で棚卸し・整理する (2) 公開する `docs` から非公開予定の `knowledge/` へのリンク切れを解消する|Version 1.0 公開前（必須）|完了|公開セット・リンク方針は [`knowledge_publish_inventory.md`](../07_decisions/knowledge_publish_inventory.md)。Git 除外は [`PRG-PRJ-002`](progress.md)。`knowledge/` 内の `private/` へのクリック可能リンク解消は [`PRG-PRJ-001`](progress.md) で完了（2026/07/26）。関連：`prompts/README.md`（公開代表は prompts/）、BLG-PRJ-010|
|BLG-PRJ-010|PRJ|記録係ルールの公開/非公開整理と docs 正本化|公開範囲の確定を受け、記録種別ごとに公開/非公開を明示し、書き分け基準をルールへ反映する。詳細は `.cursor/rules` ではなく docs を正本とする|Version 1.0 公開前（推奨）|完了|[`recording.md`](../02_rules/recording.md) を新設。[`recording.mdc`](../../.cursor/rules/recording.mdc) は行動トリガーにスリム化。[`cursor_rules_slimming.md`](../07_decisions/cursor_rules_slimming.md) 追記。関連：[`public_offering_strategy.md`](../07_decisions/public_offering_strategy.md) §5.2、[`development.md`](../02_rules/development.md) §8|
|BLG-PRJ-012|PRJ|公開時の発信チャネル選定|Version 1.0 公開時の認知獲得のため、解説記事・告知の発信チャネルを選定し、公開直後に出す記事の掲載先を決める。記事は差別化メッセージ（[`public_offering_strategy.md`](../07_decisions/public_offering_strategy.md) §5.6）と認知ターゲット（[`audience_target.md`](../07_decisions/audience_target.md)）を前提とする。**【方針】** Version 1.0 公開のブロッカーにはしない。素材は公開 `docs` とし、生ログ（`private`）は出さない|Version 1.0 公開前（推奨）〜公開時|完了|**発信チャネル: Zenn + X（@ysmkotm）**（2026-07-31 決定）。1本目（2026-07-31）「AI開発の前提は、チャットではなくdocsに永続化する」https://zenn.dev/ysmkotm/articles/docs-as-ssot-ai-pairing（§5.6 の差別化メッセージを採用）。2本目（2026-08-02）「使ったことのない技術を、AIと一緒に取り入れてみた話」https://zenn.dev/ysmkotm/articles/flyway-docs-driven-request（PRG-CMN-004 を題材）。KPI 観測起点は 2026-07-31。リポジトリ内の判断記録は未作成（作成要否は人間が判断）|
|BLG-CMN-009|CMN|業務画面の表示確認（1366×768 等）|想定解像度での情報量・折り返し・操作性を確認し、必要ならレイアウトを調整する|Version 1.0 公開前（推奨）|完了|PRG-PRJ-003 で致命的崩れなしを確認（2026/07/30）。レイアウト本調整は不要と判断。再発時や解像度要件の厳格化は Version 1.x。[`ui.md`](../02_rules/ui.md) §3.7、[`progress.md`](progress.md) §8|
|BLG-EMP-011|EMP|リモートワーク項目の業務定義と表示名称|リモートワークの可否（可であればチェック）。「実施中」等は表さない。表示名は「リモートワーク」のまま。設計書・定義書へ明記済み。以降は原則維持|Version 1.0 公開前（推奨：当面方針の明記）／以降は原則維持（必要なら Version 1.x 任意）|完了|[`EMP002_社員登録・編集.md`](../05_screen/EMP002_社員登録・編集.md) §5.2、[`employee.md`](../04_db/table/employee.md) §2、[`progress.md`](progress.md) §8|
|BLG-EMP-001|EMP|`mode` の管理方式|登録・編集画面の表示制御に用いる `mode` を、文字列・定数・Enum のどれで管理するか|EMP002 画面設計・実装時|完了|文字列（`"create"` / `"edit"`）を採用。[`emp002_post_validation_groups.md`](../07_decisions/emp002_post_validation_groups.md)|
|BLG-EMP-002|EMP|`EmployeeForm` 共用|登録画面と編集画面で同一の `EmployeeForm` を使用するか、画面ごとに Form を分けるか|EMP002 画面設計・実装時|完了|共用を採用（画面設計書どおり）。[`emp002_post_validation_groups.md`](../07_decisions/emp002_post_validation_groups.md)|
|BLG-EMP-005|EMP|登録・更新後のメッセージ表示|登録・更新・削除成功時のフラッシュメッセージ等の表示方式。Version 1.0 暫定実装は PRG-CMN-003 で完了。共通方針の完全版は BLG-CMN-001|Version 1.0 公開前（必須：暫定実装）／完全版は Version 1.x|完了|Flash 属性 `successMessage` + EMP001 `alert-success`。[`PRG-CMN-003`](progress.md)、[`ui.md`](../02_rules/ui.md) §7、BLG-CMN-001|
|BLG-EMP-006|EMP|Validation Groups の利用|登録と更新で異なるバリデーションを、Bean Validation の Validation Groups で分けるか|EMP002 登録・更新処理（POST）実装時|完了|採用。[`ValidationGroups`](../../src/main/java/io/github/ysmkotm/practicalappstarter/form/ValidationGroups.java)、[`emp002_post_validation_groups.md`](../07_decisions/emp002_post_validation_groups.md)|

---

## 5. 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`docs/01_project/project.md`](project.md)|プロジェクト概要・Version 単位の開発予定・完成条件|
|[`docs/01_project/progress.md`](progress.md)|進捗管理（現在地・PRG・V1.0／V2 以降の参照ポインタ）|
|[`docs/07_decisions/version1_publish_scope.md`](../07_decisions/version1_publish_scope.md)|Version 1.0 公開スコープ|
|[`docs/07_decisions/public_offering_strategy.md`](../07_decisions/public_offering_strategy.md)|公開・提供方針（OSS／収益化／ポートフォリオ）|
|[`docs/01_project/review_findings.md`](review_findings.md)|レビュー指摘の索引|
|[`docs/03_system/features.md`](../03_system/features.md)|機能一覧|
|[`docs/03_system/screens.md`](../03_system/screens.md)|画面一覧|
|[`docs/02_rules/ui.md`](../02_rules/ui.md)|UI 設計ルール|
|[`docs/05_screen/EMP001_社員一覧.md`](../05_screen/EMP001_社員一覧.md)|社員一覧画面設計書|
|[`docs/02_rules/coding.md`](../02_rules/coding.md)|コーディングルール|
|[`docs/02_rules/development.md`](../02_rules/development.md)|開発ルール（判断記録 §8）|
|[`docs/02_rules/recording.md`](../02_rules/recording.md)|記録ルール（種別・公開／非公開）|
|[`docs/07_decisions/backlog_document_placement.md`](../07_decisions/backlog_document_placement.md)|バックログ新設・配置の判断記録|
|[`docs/07_decisions/progress_backlog_single_table_ssot.md`](../07_decisions/progress_backlog_single_table_ssot.md)|progress／backlog の単一表・参照ポインタ化と人間向け読み順|
|[`docs/07_decisions/`](../07_decisions/)|判断記録|
