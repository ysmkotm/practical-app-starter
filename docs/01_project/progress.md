# 進捗管理

**Document Version** : 1.3

**更新日** : 2026/07/30

本ドキュメントは、Version 単位の **作業管理の正本（SSOT）** です。作業の全量・現在地・次にやることだけを管理します。仕様・判断理由・レビューコメント全文は記載せず、関連ドキュメントへリンクします。

---

## 1. 目的

- 作業の全量が分かること
- 今どこまで進んでいるかが分かること
- 次に何をやるかが分かること
- AI セッション再開時の引き継ぎ起点とすること

レビューは独立した管理対象ではなく、**作業（PRG）の一工程** として扱います。詳細は backlog（未決定）・設計書（仕様）・判断記録・Git へ委譲します。

---

## 2. 記載範囲

情報の種類ごとの管理先は [`docs/README.md`](../README.md) §5 を正とします。本ドキュメントは **作業の現在地・PRG・次タスク** のみを持ちます。

**記載する**: フォーカス、PRG 状態、次タスク、ブロック（BLG リンク）、完了要約（学びがあるとき）、チェックリストへのリンク、**Version 1.0 作業全量**（§6）、Version 2 以降で着手しない一覧（§7・詳細は [`project.md`](project.md) §4）

**記載しない**: 仕様本文、指摘台帳、未決定一覧、チェックリスト本文、旧 REV 履歴、判断理由の詳細、中長期ロードマップ本文（→ [`project.md`](project.md) §4）

[`screens.md`](../03_system/screens.md) / [`features.md`](../03_system/features.md) / [`tables.md`](../03_system/tables.md) はシステムの俯瞰一覧であり、「状態」列は設けない。作業進捗は本ドキュメントを正とする。一覧は実装することが決まった時点で掲載する（設計・実装の完了は待たない。目安は該当 PRG を切ったとき、または Version スコープに含めたとき。判断記録：[`system_overview_lists_without_status.md`](../07_decisions/system_overview_lists_without_status.md)）。

---

## 3. 記載ルール

### ID

- `PRG-{プレフィックス}-{連番3桁}`（例：`PRG-EMP-002`）。プレフィックスは [`backlog.md`](backlog.md) §3 と同様。
- 完了後も ID は変更・再利用しない。REV-ID は採番しない。

### 状態

|状態|意味|
|---|---|
|未着手|未着手（優先順位は §4）|
|実装中|設計〜実装中|
|レビュー中|ソース／設計レビュー中|
|動作確認中|通し動作確認など|
|完了|実装・レビュー・動作確認まで一区切り|
|取消|方針変更などで不要|

工程は省略可。優先順位は状態と別（§4）。判断待ちは BLG（PRG 行にしない）。

### 完了要約・運用

- 学びがあるときだけ、完了 PRG の下に短い要約（概要・判断・反映リンク等）。問題なしのみは省略可（[`development.md`](../02_rules/development.md) §3.1）
- 未完了は §5.1、完了は §5.2。状態・次タスク・ブロックが変わったら更新する

---

## 4. 現在地サマリー

|項目|内容|
|---|---|
|マイルストーン|Version 1.0 — 公開可能な最小完成物（[`project.md`](project.md) §4、[`version1_publish_scope.md`](../07_decisions/version1_publish_scope.md)）。**必須作業は完了（公開可）**|
|現在のフォーカス|Version 1.0 必須完了後。公開後の認知仕込み（BLG-PRJ-012 等）または Version 1.x／任意（PRG-EMP-010）|
|直近の完了|PRG-PRJ-003（初見ウォークスルー）（2026/07/30）|
|次のタスク|1. 公開後の発信チャネル選定（[`BLG-PRJ-012`](backlog.md)、推奨） 2. 任意なら PRG-EMP-010（構造見直し）|
|着手しない（今）|UI 本格共通化（BLG-CMN-015）、AI 標準化（BLG-PRJ-006）、`docs/08_test`（BLG-PRJ-005）→ §7 / Version 2 以降|
|着手待ち・ブロック|404 表示の最終方針は BLG-CMN-002 確定後（[`BLG-EMP-004`](backlog.md)）。成功メッセージの**共通方針完全版**は BLG-CMN-001（保留）— V1.0 暫定は PRG-CMN-003 完了|
|最終更新|2026/07/30|

### 作業メモ

- プロジェクト名：説明路線で確定（Practical App Starter — Spring Boot Edition）。パッケージ／Maven は [`java_package_and_maven_coordinates.md`](../07_decisions/java_package_and_maven_coordinates.md)（`io.github.ysmkotm.practicalappstarter` / `practical-app-starter`）。名称は [`project_name_candidates.md`](../07_decisions/project_name_candidates.md)、BLG-PRJ-001 完了。**座標・起動クラス・README／LICENSE／setup への反映は 2026/07/25 完了**。新 Public 作成・正本切替は PRG-PRJ-002 完了（2026/07/30）。
- 公開・提供方針：再検討を経て **再確定（BLG-PRJ-008 完了）**。配置は **`prompts/`（公開代表）と `private/`（開発正本）の分離**（[`public_private_repo_topology.md`](../07_decisions/public_private_repo_topology.md)、[`public_prompts_at_repo_root.md`](../07_decisions/public_prompts_at_repo_root.md)）。物理分離は実施済み。**初回公開は新規 Public へ公開対象のみコピー**（[`public_release_new_repo_snapshot.md`](../07_decisions/public_release_new_repo_snapshot.md)）。履歴書き換えは行わない。LICENSE／名称／Maven・パッケージ反映は完了。**新 Public（https://github.com/ysmkotm/practical-app-starter）作成・push・正本切替は PRG-PRJ-002 完了**。`private` は Public ローカル＋`.gitignore`。ライセンスは Apache-2.0（BLG-PRJ-007）。棚卸し BLG-PRJ-009、記録ルール BLG-PRJ-010 完了。`knowledge/` 廃止・直下 `prompts/` 化は 2026/07/26 反映。
- Version 区切り：必須／推奨／任意は §6。Version 2 以降は §7（PRG 化しない）。
- 公開品質（初見視点）：PRG-PRJ-001／002（改修）・**PRG-PRJ-003（最終ゲート）は完了**（[`public_quality_walkthrough_gate.md`](../07_decisions/public_quality_walkthrough_gate.md)）。必須・推奨とも OK。実施記録は `private/verification/prg_prj_003_public_walkthrough.md`（公開しない）。
- **PRG-PRJ-003（2026/07/30）**: コールドスタートで setup の PowerShell 起動例（`.\mvnw.cmd`）と `java -version` 確認を追記。README 特徴節のスクショ TODO コメントは削除（既存 3 枚で十分）。GitHub About・Topics 整備済み。
- **PRG-PRJ-001 差別化メッセージ（2026/07/27）**: 戦略会議で、Version 1.0 公開時は「認知獲得の仕込み」に限定し、収益化本体は Version 3.x 予約を維持することを再確認。差別化メッセージ（「AI と人が同じ開発ドキュメントを見て開発する運用一式」）の `README.md` 冒頭への反映を **PRG-PRJ-001 の公開向け表現レビューに統合**（新規作業は増やさない）。最小 KPI・記事切り口・ターゲット仮説は [`public_offering_strategy.md`](../07_decisions/public_offering_strategy.md) §5.6、発信チャネル選定・ターゲット検証は BLG-PRJ-012 / BLG-PRJ-013。
- **PRG-PRJ-001 差別化メッセージ反映（2026/07/29）**: Claude Code レビューを経て `README.md` 冒頭を更新。タグライン「業務システムの再現可能な型 ― 開発ドキュメントを正本に、人と AI で進める」。概要2文目は「正本（SSOT）とし」に変更。自己定義（スターターキット兼リファレンス）は概要1文目に委譲。依頼文は `private/prompts/claude_review_differentiation_message.md`。
- **PRG-PRJ-001 Document Version 初版揃え（2026/07/30）**: 公開対象（`docs/`・`prompts/README.md`。`private/` 除外。README アプリ Version は対象外）の Document Version を `1.0`、更新日を `2026/07/30` に揃えた（82件）。テンプレヘッダも対象。テンプレ本文の記載例（`1.0` / `YYYY/MM/DD`）と判断記録本文の旧版番号言及は維持。実際の公開日が異なる場合は更新日のみ再揃えする。
- **PRG-PRJ-001 認知ターゲット（2026/07/27）**: Version 1.0 公開向け認知ターゲットを仮確定（[`audience_target.md`](../07_decisions/audience_target.md)）。`README.md` 対象者を更新。§5.6・BLG-PRJ-012／013 から参照。
- **PRG-PRJ-001 公開面の簡素化（2026/07/26）**: 公開代表をリポジトリ直下 `prompts/` のみとし、`knowledge/` を廃止。動作確認チェックリスト本体は公開しない（[`public_prompts_at_repo_root.md`](../07_decisions/public_prompts_at_repo_root.md)）。

- **PRG-PRJ-001 に含める公開前作業（2026/07/26）**:（1）`knowledge/` 内の `private/` へのクリック可能リンク解消（Public に `private` を載せない前提。BLG-PRJ-009 残作業）**完了（2026/07/26）**（2）公開向け表現レビュー（A／B。初見導線の文書が対象。チェックリストは `private/verification/prg_prj_001_editorial_review_checklist.md`）（3）公開対象ドキュメントの Document Version を初版 `1.0` に揃え、更新日を公開日にそろえる（[`documentation.md`](../02_rules/documentation.md) §12。README のアプリ Version とは別）。**（2）→（3）の順**に実施する（文章を直してから版を揃える）。いずれも **新 Public コピー前**に現行リポで実施する。
- **PRG-PRJ-001 公開向けレビュー（2026/07/26）**: `docs`／`knowledge`／`README.md` 全82ファイルの相対リンクを機械チェックし、リンク切れ 9 件を是正（旧パッケージパス 7、未存在ファイル 1、記載例プレースホルダ 2）。`docs/06_api` は空テンプレートを廃し [`docs/06_api/README.md`](../06_api/README.md) で「API 追加時に整備」と明記。`screens.md`／`features.md` の状態表記は実装実態と一致を確認。
- **PRG-PRJ-001 追記（2026/07/26）**: 公開 docs 内の個別 `private` ファイル参照を一般化。制作ワークスペース判断（`contents_as_content_workspace`）を非公開へ移し、公開側は分離基準＋線引き判断（[`contents_workspace_decision_publish_scope.md`](../07_decisions/contents_workspace_decision_publish_scope.md)）に整理。`setup.md` の WIP 表現を緩和。公開向け表現レビュー（A／B）の文書単位チェックリストは `private/verification/prg_prj_001_editorial_review_checklist.md`（非公開・作業補助）。
- PRG-EMP-009 通し確認用チェックリスト：`private/verification/`（パス表記）（完了。当面材料として残す。運用方針は [`verification_checklist_and_test_assets.md`](../07_decisions/verification_checklist_and_test_assets.md)）
- 画面遷移方針を変更し、操作列廃止・社員番号リンク・削除の EMP002 移管を決定（[`employee_list_navigation_pattern.md`](../07_decisions/employee_list_navigation_pattern.md)）。
- EMP002 POST：Validation Groups 採用等は [`emp002_post_validation_groups.md`](../07_decisions/emp002_post_validation_groups.md)。削除済みマスタの現在値維持は [`emp002_soft_deleted_master_reference.md`](../07_decisions/emp002_soft_deleted_master_reference.md)。
- F-01 の `PSQLException` 依存は EMP002 最小実装として当面維持。将来の責務見直しは [`BLG-CMN-017`](backlog.md)（Version 1.x 以降）。
- 2026/07/23：作業管理を PRG 中心に再編。REV-ID・レビュー専用章を廃止（[`progress_prg_centric_work_management.md`](../07_decisions/progress_prg_centric_work_management.md)）。
- **PRG-CMN-004 Flyway優先度引き上げ（2026/07/30）**：初見視点レビューで `setup.md` の手動SQL実行（6ファイル番号順）が離脱リスクとして指摘されたことを受け、`project.md` §4「Version 1.x」技術対応のうちFlywayを前倒しで着手対象に格上げ（一覧の並びも先頭へ変更）。実装はCursor（[`ai.md`](../02_rules/ai.md) §2 の役割分担どおり）。実装依頼は `private/prompts/cursor_flyway_migration_request.md`。

---

## 5. 作業ボード（PRG）

### 5.1 未完了

**優先度**: 必須 = Version 1.0 公開ブロッカー／推奨 = 公開品質向上／任意 = 無くても公開可（[`version1_publish_scope.md`](../07_decisions/version1_publish_scope.md)）

#### 共通（CMN）

|ID|スコープ|優先度|作業内容|状態|関連|
|---|---|---|---|---|---|
|PRG-CMN-004|DBマイグレーション基盤|高（優先度引き上げ）|Flyway導入（依存追加・既存SQLのFlyway化・設定・setup.md更新）|未着手|Version 1.x技術対応（[`project.md`](project.md) §4）。実装依頼は `private/prompts/cursor_flyway_migration_request.md`（実装はCursor、[`ai.md`](../02_rules/ai.md) §2）|

#### 社員管理（EMP）

|ID|スコープ|優先度|作業内容|状態|関連|
|---|---|---|---|---|---|
|PRG-EMP-010|社員管理 CRUD|任意|構造・可読性・簡潔性の見直し（Controller / Service / Mapper / Form 等。必須の共通化・分割ではない）|未着手|PRG-EMP-009 完了後。Version 1.0 公開を待たない作業にしてよいが必須ではない|

#### プロジェクト全体（PRJ）

（未完了なし）

### 5.2 完了

#### プロジェクト全体（PRJ）

|ID|スコープ|作業内容|完了日|関連|
|---|---|---|---|---|
|PRG-PRJ-003|公開品質|GitHub 初見視点の公開品質ウォークスルー（必須項目の実施・必須 NG の是正確認）（**検証**）|2026/07/30|[`public_quality_walkthrough_gate.md`](../07_decisions/public_quality_walkthrough_gate.md)。実施は `private/verification/prg_prj_003_public_walkthrough.md`（公開しない）|
|PRG-PRJ-002|公開準備|README／setup 通し、正式名称・Maven／パッケージ・ライセンス反映、**公開対象の確定・新 Public 作成・開発正本の切替**（**改修**）|2026/07/30|[`public_release_new_repo_snapshot.md`](../07_decisions/public_release_new_repo_snapshot.md) §5.3、[`public_private_repo_topology.md`](../07_decisions/public_private_repo_topology.md)、[`knowledge_publish_inventory.md`](../07_decisions/knowledge_publish_inventory.md)|
|PRG-PRJ-001|横断|開発ドキュメントの公開向けレビュー・整理（リンク解消・表現レビュー A／B・Document Version 初版揃え）|2026/07/30|BLG-PRJ-002、BLG-PRJ-009、[`version1_publish_scope.md`](../07_decisions/version1_publish_scope.md)|

##### PRG-PRJ-003（公開品質ウォークスルー）

- レビュー概要：GitHub 初見視点で必須（§3〜§6）・推奨（§7）を実施。いずれも OK。Version 1.0 公開可
- 修正・判断：setup に PowerShell の `.\mvnw.cmd` と `java -version` を追記。README 特徴節のスクショ TODO は削除（既存 TOP／一覧／編集で十分）。About・Topics は整備済み
- 反映：[`setup.md`](setup.md)、[`README.md`](../../README.md)、`private/verification/prg_prj_003_public_walkthrough.md`

##### PRG-PRJ-002（公開準備）

- レビュー概要：新 Public リポジトリ作成、公開対象コピー、初回コミット／push、開発正本の切替まで完了
- 修正・判断：公開漏れチェック（`private` 混入・秘密情報・非公開リンク・旧 `knowledge/` 残存）は問題なし。旧リポジトリへのアーカイブ注記は検討のうえ見送り（対応不要）
- 反映：https://github.com/ysmkotm/practical-app-starter 。[`setup.md`](setup.md) の clone URL 確定。`.gitignore` に `/private/` 追加。作業正本を新 Public クローンへ切替。初回コミット `add: Version 1.0 初版公開`

##### PRG-PRJ-001（公開向け整理）

- レビュー概要：公開前の矛盾・導線・表現・Document Version を整え、新 Public コピー可能な状態にした
- 修正・判断：テンプレヘッダも Document Version 初版揃えの対象。`private/` と README アプリ Version は除外。更新日は作業日 `2026/07/30`（実公開日が異なれば再揃え）
- 反映：公開対象 82 件の Document Version `1.0`／更新日揃え。表現レビューは `private/verification/prg_prj_001_editorial_review_checklist.md`

#### 共通（CMN）

|ID|スコープ|作業内容|完了日|関連|
|---|---|---|---|---|
|PRG-CMN-003|メッセージ表示|登録・更新・削除後の成功メッセージ暫定実装|2026/07/24|[`BLG-EMP-005`](backlog.md)、[`BLG-CMN-001`](backlog.md)、[`ui.md`](../02_rules/ui.md) §7|
|PRG-CMN-002|CMN001|TOP 画面の完成度確認・整備（パンくず要否を含む）|2026/07/24|[`CMN001_TOP.md`](../05_screen/CMN001_TOP.md)|
|PRG-CMN-001|共通レイアウト|全画面での fragment 利用・表示確認|2026/07/24|[`ui.md`](../02_rules/ui.md) §2.3・§2.4|

##### PRG-CMN-003（成功メッセージ暫定）

- レビュー概要：Flash 属性 + EMP001 の `alert-success` による暫定実装
- 修正・判断：共通方針の完全版は BLG-CMN-001 のまま。文面は登録／更新／削除の固定文言
- 反映：[`EMP001_社員一覧.md`](../05_screen/EMP001_社員一覧.md)、[`EMP002_社員登録・編集.md`](../05_screen/EMP002_社員登録・編集.md)、[`ui.md`](../02_rules/ui.md) §7、[`emp002_post_validation_groups.md`](../07_decisions/emp002_post_validation_groups.md)

##### PRG-CMN-002（CMN001）

- レビュー概要：薄い画面設計書作成・テンプレ軽い整備・表示確認
- 修正・判断：ルート画面はパンくずなし。現在地は visible 見出し（[`ui.md`](../02_rules/ui.md) §2.3）
- 反映：[`CMN001_TOP.md`](../05_screen/CMN001_TOP.md)、[`screens.md`](../03_system/screens.md) 実装済

##### PRG-CMN-001（共通レイアウト）

- レビュー概要：TOP / EMP001 / EMP002 で css・header・sidebar・scripts の共通利用を確認
- 修正・判断：TOP の include を `th:block`、Main を `app-main` に揃え。深い共通化は BLG-CMN-015。TOP パンくず要否は PRG-CMN-002 で検討
- 反映：[`ui.md`](../02_rules/ui.md) §2.3、[`top/index.html`](../../src/main/resources/templates/top/index.html)

#### 社員管理（EMP）

|ID|スコープ|作業内容|完了日|関連|
|---|---|---|---|---|
|PRG-EMP-009|EMP002 CRUD|通し動作確認（登録・更新・削除・バリデーション・削除済みマスタ維持）|2026/07/24|`private/verification/prg_emp_009_crud_checklist.md`|
|PRG-EMP-008|EMP002 社員登録・編集|POST 実装（登録・更新・削除）およびソースレビュー|2026/07/23|[`emp002_post_validation_groups.md`](../07_decisions/emp002_post_validation_groups.md)、[`emp002_soft_deleted_master_reference.md`](../07_decisions/emp002_soft_deleted_master_reference.md)|
|PRG-EMP-007|EMP002 社員登録・編集|GET 実装（削除済みマスタ補完含む）およびソースレビュー|2026/07/23|[`emp002_soft_deleted_master_reference.md`](../07_decisions/emp002_soft_deleted_master_reference.md)|
|PRG-EMP-004|EMP001 社員一覧|操作列廃止・社員番号リンク表示およびソースレビュー|2026/07/23|[`employee_list_navigation_pattern.md`](../07_decisions/employee_list_navigation_pattern.md)|
|PRG-EMP-005|EMP002 社員登録・編集|画面設計書作成・設計レビュー|2026/07/13|[`EMP002_社員登録・編集.md`](../05_screen/EMP002_社員登録・編集.md)、[`employee_list_navigation_pattern.md`](../07_decisions/employee_list_navigation_pattern.md)|
|PRG-EMP-006|EMP003 社員詳細|画面設計書作成|2026/07/13|廃止（[`employee_list_navigation_pattern.md`](../07_decisions/employee_list_navigation_pattern.md)。画面一覧から削除）|
|PRG-EMP-003|EMP001 社員一覧|削除 POST 実装（論理削除）|2026/07/13|取消。削除は EMP002 へ移管（[`employee_list_navigation_pattern.md`](../07_decisions/employee_list_navigation_pattern.md)）|
|PRG-EMP-002|EMP001 社員一覧|検索・一覧 GET 実装およびレビュー一式|2026/07/13|commit `b3ba576` 等|
|PRG-EMP-001|EMP001 社員一覧|画面設計書整備|2026/07/12|[`EMP001_社員一覧.md`](../05_screen/EMP001_社員一覧.md)|

##### PRG-EMP-009（EMP002 CRUD 通し動作確認）

- レビュー概要：動作確認チェックリストによる通し確認（必須すべて OK。任意 7.3.2 のみ NG）
- 主な確認：遷移・登録・更新・削除・必須バリデーション・削除済みマスタ維持。任意で異常系・文字数等
- 修正・判断：ブロッカーなし。任意 NG（全角空白・社員番号文字種）は BLG-CMN-020 / BLG-EMP-007 へ。更新／削除 form 構造は BLG-CMN-019
- 反映：[`screens.md`](../03_system/screens.md) / [`features.md`](../03_system/features.md) を実装済へ。チェックリスト運用は [`verification_checklist_and_test_assets.md`](../07_decisions/verification_checklist_and_test_assets.md)

##### PRG-EMP-008（EMP002 POST）

- レビュー概要：POST（登録・更新・削除）のソースレビュー
- 主な確認：`EmployeeController` POST、`EmployeeService`（一意性・マスタ妥当性・DIV）、`EmployeeForm` / `ValidationGroups`、`EmployeeMapper`、関連テスト
- 修正・判断：F-01 UNIQUE 制約名判定、F-02 マスタ妥当性・削除済み現在値維持
- 反映：[`emp002_soft_deleted_master_reference.md`](../07_decisions/emp002_soft_deleted_master_reference.md)、[`employee.md`](../04_db/table/employee.md) v1.8、BLG-CMN-017 / BLG-CMN-018。指摘索引：[`review_findings.md`](review_findings.md) FND-EMP-009 / 010、FND-CMN-006 / 007

##### PRG-EMP-007 / PRG-EMP-004（GET・画面遷移）

- レビュー概要：EMP002 GET、EMP001 画面遷移変更、削除済みマスタ補完のソースレビュー
- 主な確認：操作列廃止・社員番号リンク、登録／編集 GET、`findDepartmentsForForm` / `findEmployeeStatusesForForm`
- 修正・判断：問題なし（設計どおり）。物理削除されたマスタ参照は補完対象外（論理削除前提）
- 反映：[`emp002_soft_deleted_master_reference.md`](../07_decisions/emp002_soft_deleted_master_reference.md)、[`employee_list_navigation_pattern.md`](../07_decisions/employee_list_navigation_pattern.md)

##### PRG-EMP-005（EMP002 設計）

- レビュー概要：画面設計書および画面遷移方針の設計レビュー
- 主な確認：操作列廃止・社員番号リンク・削除の EMP002 移管、`EMP002` 設計書
- 修正・判断：設計書・判断記録を更新。`EmployeeController#showDetail` 削除
- 反映：[`employee_list_navigation_pattern.md`](../07_decisions/employee_list_navigation_pattern.md)、[`EMP002_社員登録・編集.md`](../05_screen/EMP002_社員登録・編集.md)、BLG-EMP-010。指摘索引：[`review_findings.md`](review_findings.md) FND-EMP-008

##### PRG-EMP-002（EMP001 GET）

- レビュー概要：検索・一覧 GET 周辺のソース／設計レビュー一式（旧クラス単位セッションは畳み込み）
- 主な確認：Service / Mapper / Entity・Form・DTO / Controller / Mapper XML / UI（DataTables）等
- 修正・判断：LEFT JOIN・ResultMap・UI ルール反映など。詳細は各判断記録へ
- 反映：[`employee_service_emp001_review.md`](../07_decisions/employee_service_emp001_review.md)、[`entity_form_dto_roles.md`](../07_decisions/entity_form_dto_roles.md)、[`employee_list_master_join_left_join.md`](../07_decisions/employee_list_master_join_left_join.md)、[`mybatis_resultmap_type_aliases.md`](../07_decisions/mybatis_resultmap_type_aliases.md)、[`review_findings_escalation_criteria.md`](../07_decisions/review_findings_escalation_criteria.md)、[`lombok_limited_adoption.md`](../07_decisions/lombok_limited_adoption.md)、[`coding.md`](../02_rules/coding.md)、[`ui.md`](../02_rules/ui.md)、BLG-CMN-004〜012 等。指摘索引：[`review_findings.md`](review_findings.md) FND-EMP-001〜007、FND-CMN-001〜005

##### PRG-EMP-001（EMP001 設計）

- レビュー概要：画面設計書レビュー（実装方針確定）
- 反映：[`EMP001_社員一覧.md`](../05_screen/EMP001_社員一覧.md)、非公開のAI協調開発ログ（ローカル）

---

## 6. Version 1.0 作業全量

[`project.md`](project.md) §4（Version 1.0）および [`version1_publish_scope.md`](../07_decisions/version1_publish_scope.md) に対応する **Version 1.0 の作業・判断の全量**です。

**優先度**: 必須 / 推奨 / 任意  
**状態**: 完了 / 未着手 / 判断待ち（BLG）/ 対象外

### 6.1 必須（公開ブロッカー）

|内容|管理|状態|メモ|
|---|---|---|---|
|共通レイアウト|PRG-CMN-001|完了|全画面で fragment 利用確認済|
|TOP 画面|PRG-CMN-002|完了|設計書・表示確認済|
|社員一覧・検索|PRG-EMP-002|完了||
|EMP001 画面遷移変更|PRG-EMP-004|完了||
|EMP002 GET|PRG-EMP-007|完了||
|社員登録・編集・削除 POST|PRG-EMP-008|完了||
|入力チェック|PRG-EMP-008 / 009|完了|EMP001 検索 + EMP002 Form。通し確認済|
|CRUD 通し動作確認|PRG-EMP-009|完了|2026/07/24|
|成功メッセージ（暫定）|PRG-CMN-003|完了|共通方針完全版は BLG-CMN-001（保留）のまま|
|正式名称（公開名）|[`BLG-PRJ-001`](backlog.md)|完了|Practical App Starter — Spring Boot Edition。パッケージ等の反映は PRG-PRJ-002 完了|
|公開・提供方針（公開形態・公開範囲・商用可否）|[`BLG-PRJ-008`](backlog.md)|完了|コード OSS／docs 公開／knowledge 一部非公開で再確定（2026/07/25）。[`public_offering_strategy.md`](../07_decisions/public_offering_strategy.md) §5|
|ライセンス|[`BLG-PRJ-007`](backlog.md)|完了|Apache License 2.0（[`license_selection.md`](../07_decisions/license_selection.md)）。`LICENSE`／README 反映は PRG-PRJ-002 完了|
|knowledge 整理・docs→knowledge リンク解消|[`BLG-PRJ-009`](backlog.md)|完了|公開セット・リンク方針は [`knowledge_publish_inventory.md`](../07_decisions/knowledge_publish_inventory.md)。`knowledge`→`private` の残リンク解消は PRG-PRJ-001 で完了（2026/07/26）。新 Public へのコピーは PRG-PRJ-002 完了|
|公開方式（新規 Public スナップショット）|[`public_release_new_repo_snapshot.md`](../07_decisions/public_release_new_repo_snapshot.md)|完了|履歴書き換えは行わない。公開後の SSOT は Public。`private` はローカル＋`.gitignore`|
|公開準備（README / setup 通し / 名称・ライセンス反映 / 新 Public 作成）|PRG-PRJ-002|完了|2026/07/30。新 Public push・正本切替済み。検証は PRG-PRJ-003|
|docs 公開向け整理（リンク解消・表現レビュー・Document Version 初版揃え含む）|PRG-PRJ-001|完了|2026/07/30。全文完璧化は求めない。表現レビュー A／B・Document Version `1.0`／更新日揃え済み。検証は PRG-PRJ-003|
|公開品質ウォークスルー（初見視点）|PRG-PRJ-003|完了|2026/07/30。必須・推奨とも OK。実施は `private/verification/prg_prj_003_public_walkthrough.md`（公開しない）|

### 6.2 推奨（公開品質向上・無くても公開可だが望ましい）

|内容|管理|状態|メモ|
|---|---|---|---|
|業務画面の表示確認（1366×768 等）|[`BLG-CMN-009`](backlog.md)|完了（V1.0）|PRG-PRJ-003 で致命的崩れなしを確認。レイアウト本調整が必要になった場合は Version 1.x で再検討|
|社員番号の当面方針の明記|[`BLG-EMP-007`](backlog.md)|完了（当面）|設計書へ明記済み。採番・文字種等の本検討・実装は Version 1.x（§7）。BLG は保留|
|リモートワーク項目の定義・表示名|[`BLG-EMP-011`](backlog.md)|完了|可否フラグとして設計書へ明記。以降は原則維持|
|論理削除済み社員番号・メール再利用の当面方針|[`BLG-EMP-013`](backlog.md)|完了（当面）|再利用不可を設計書へ明記済み。再利用可への変更検討は Version 1.x（§7）。BLG は保留|
|動作確認結果の公開向け要約|—|完了（代替）|公開向け要約は置かず、EMP-009 完了＋ README の V1.0 機能記載で代替（PRG-PRJ-003）|
|README スクリーンショット（または短い GIF）|PRG-PRJ-003（推奨）|完了|`assets/readme/` に TOP／一覧／編集。特徴節 TODO は削除（追加撮影なし）|
|GitHub About・Topics の整備|PRG-PRJ-003（推奨）|完了|リポジトリ About で設定済み|
|AI 協調の使い方への短いポインタ|PRG-PRJ-003（推奨）|完了|README 特徴から [`ai.md`](../02_rules/ai.md) へリンク。標準化は Version 2.x|

### 6.3 任意（Version 1.0 でやらなくてもよい）

|内容|管理|状態|メモ|
|---|---|---|---|
|構造・可読性の見直し|PRG-EMP-010|未着手|公開後でも可|
|入力項目の意味グループ並び替え|[`BLG-EMP-012`](backlog.md)|未検討||
|visible 画面タイトル要否|[`BLG-CMN-014`](backlog.md)|未検討||
|サイドバー折りたたみ|[`BLG-CMN-013`](backlog.md)|未検討||
|全角空白 trim 方針|[`BLG-CMN-020`](backlog.md)|未検討||

### 6.4 Version 1.0 対象外（意図的）

|内容|メモ|
|---|---|
|社員詳細画面（旧 EMP003）|廃止済（PRG-EMP-006）。編集画面で参照・更新・削除を完結|
|UI 本格共通化（BLG-CMN-015 系）|Version 1.x（2 画面目以降）|
|例外処理共通方針の本格整備（BLG-CMN-002）|暫定 404 のまま。Version 1.x|
|メッセージ共通方針の完全版（BLG-CMN-001）|暫定実装で V1.0 完了可。完全版は Version 1.x|
|楽観ロック（BLG-EMP-010）|保留のまま|

---

## 7. Version 2 以降（いま着手しない）

詳細・目指す状態は [`project.md`](project.md) §4。ここには **誤って Version 1.0 の次タスクにしないための一覧** のみを置く。原則として PRG 行は設けない（着手時に昇格）。

|テーマ|目安 Version|関連|
|---|---|---|
|CSV / ファイルアップロード / メール|1.x|[`project.md`](project.md) §4|
|Spring Security / Flyway / Docker|1.x|[`project.md`](project.md) §4|
|UI パターン共通化・fragment 化|1.x|[`BLG-CMN-015`](backlog.md) および子項目|
|メッセージ・例外方針の本格整備|1.x|BLG-CMN-001 / 002|
|DB 例外判定・論理削除マスタ補完の共通化|1.x（2 画面目以降）|BLG-CMN-017 / 018|
|社員番号の採番・形式／削除済みコード再利用の本検討|1.x|[`BLG-EMP-007`](backlog.md)、[`BLG-EMP-013`](backlog.md)（V1.0 は当面方針の明記のみ。§6.2）|
|AI 協調フロー再現性検証（小規模 CRUD）|1.x〜2.x|[`BLG-PRJ-011`](backlog.md)（公開条件外。BLG-PRJ-006・BLG-CMN-015 と接続）|
|正式なテスト資料（`docs/08_test`）|2.x|[`BLG-PRJ-005`](backlog.md)|
|AI 協調開発フロー標準化・工程別標準プロンプト|2.x|[`BLG-PRJ-006`](backlog.md)|
|共通基盤の抽出・共通テンプレート化|2.x|[`project.md`](project.md) §4|
|AI コンテキスト設計実験（Claude Code 等）|3.x または余力|[`BLG-PRJ-004`](backlog.md)|
|デモサイト・販売導線の強化（隣接課金）|3.x|[`project.md`](project.md) §4、[`public_offering_strategy.md`](../07_decisions/public_offering_strategy.md)|

---

## 8. 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`docs/01_project/project.md`](project.md)|プロジェクト概要・ロードマップ|
|[`docs/07_decisions/version1_publish_scope.md`](../07_decisions/version1_publish_scope.md)|Version 1.0 公開スコープ|
|[`docs/07_decisions/public_offering_strategy.md`](../07_decisions/public_offering_strategy.md)|公開・提供方針（OSS／収益化／ポートフォリオ）|
|[`docs/07_decisions/public_release_new_repo_snapshot.md`](../07_decisions/public_release_new_repo_snapshot.md)|Version 1.0 のリポジトリ・Git 履歴の公開方式|
|[`docs/07_decisions/public_private_repo_topology.md`](../07_decisions/public_private_repo_topology.md)|Public 正本とローカル `private` の分離|
|[`docs/07_decisions/knowledge_publish_inventory.md`](../07_decisions/knowledge_publish_inventory.md)|公開セット（prompts 中心）|
|[`docs/07_decisions/public_prompts_at_repo_root.md`](../07_decisions/public_prompts_at_repo_root.md)|直下 `prompts/` と knowledge 廃止|
|[`docs/07_decisions/public_quality_walkthrough_gate.md`](../07_decisions/public_quality_walkthrough_gate.md)|公開品質ウォークスルーを最終ゲートとする判断|
|`private/verification/prg_prj_003_public_walkthrough.md`|公開品質ウォークスルー（初見視点）|
|[`docs/01_project/backlog.md`](backlog.md)|保留事項・未決定事項|
|[`docs/01_project/review_findings.md`](review_findings.md)|レビュー指摘の索引|
|[`docs/03_system/features.md`](../03_system/features.md)|機能一覧|
|[`docs/03_system/screens.md`](../03_system/screens.md)|画面一覧|
|[`docs/02_rules/ai.md`](../02_rules/ai.md)|AI 協調開発・セッション再開|
|[`docs/07_decisions/progress_document_placement.md`](../07_decisions/progress_document_placement.md)|progress 新設の判断記録|
|[`docs/07_decisions/review_into_progress_merge.md`](../07_decisions/review_into_progress_merge.md)|review.md 統合・廃止|
|[`docs/07_decisions/progress_prg_centric_work_management.md`](../07_decisions/progress_prg_centric_work_management.md)|PRG 中心の作業管理・REV 廃止|
|[`docs/07_decisions/review_findings_index.md`](../07_decisions/review_findings_index.md)|レビュー指摘一覧の導入|
|[`docs/07_decisions/employee_list_navigation_pattern.md`](../07_decisions/employee_list_navigation_pattern.md)|社員一覧の画面遷移方針|
|[`docs/07_decisions/verification_checklist_and_test_assets.md`](../07_decisions/verification_checklist_and_test_assets.md)|動作確認チェックリストとテスト資料の役割分担|
