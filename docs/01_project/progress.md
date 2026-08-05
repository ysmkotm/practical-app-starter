# 進捗管理

**Document Version** : 2.0

**更新日** : 2026/08/05

本ドキュメントは、Version 単位の **作業管理の正本（SSOT）** です。作業の全量・現在地・次にやることだけを管理します。仕様・判断理由・レビューコメント全文は記載せず、関連ドキュメントへリンクします。

---

## 1. 現在地サマリー

|項目|内容|
|---|---|
|マイルストーン|Version 1.0 — 公開可能な最小完成物（[`project.md`](project.md) §4、[`version1_publish_scope.md`](../07_decisions/version1_publish_scope.md)）。**必須作業は完了（公開可）**|
|現在のフォーカス|**部署管理 CRUD**（[`BLG-PRJ-011`](backlog.md)：AI 協調開発フローの再現性検証）。UI 共通化（BLG-CMN-015）はスコープ外|
|直近の完了|PRG-DEP-005（DEP002 POST実装、部署コード編集可能化を含む。動作確認済み）（2026/08/05）|
|次のタスク|1. `PRG-DEP-006`（部署管理CRUD通し動作確認） 2. 任意なら PRG-EMP-010（構造見直し） 3. Version 1.x の残り（Spring Security／Docker 等。[`project.md`](project.md) §4）|
|着手しない（今）|UI 本格共通化（BLG-CMN-015）、インライン編集 UI 検証（BLG-CMN-021・保留）、AI 標準化（BLG-PRJ-006）、`docs/08_test`（BLG-PRJ-005）→ [`project.md`](project.md) §4 / [`backlog.md`](backlog.md)|
|着手待ち・ブロック|404 表示の最終方針は BLG-CMN-002 確定後（[`BLG-EMP-004`](backlog.md)）。成功メッセージの**共通方針完全版**は BLG-CMN-001（保留）— V1.0 暫定は PRG-CMN-003 完了|
|最終更新|2026/08/05|

### 作業メモ

- プロジェクト名：説明路線で確定（Practical App Starter — Spring Boot Edition）。名称は [`project_name_candidates.md`](../07_decisions/project_name_candidates.md)、パッケージ／Maven は [`java_package_and_maven_coordinates.md`](../07_decisions/java_package_and_maven_coordinates.md)。BLG-PRJ-001 完了。反映は PRG-PRJ-002
- 公開・提供方針：再確定済み（BLG-PRJ-008）。配置は [`public_private_repo_topology.md`](../07_decisions/public_private_repo_topology.md)、[`public_prompts_at_repo_root.md`](../07_decisions/public_prompts_at_repo_root.md)、[`public_release_new_repo_snapshot.md`](../07_decisions/public_release_new_repo_snapshot.md)。ライセンスは Apache-2.0（BLG-PRJ-007）
- Version 区切り：V1.0 スコープは [`version1_publish_scope.md`](../07_decisions/version1_publish_scope.md)。ロードマップは [`project.md`](project.md) §4。個別の後回し項目は [`backlog.md`](backlog.md)。本ドキュメントは §8／§3 で参照先のみ示す
- F-01 の `PSQLException` 依存は EMP002 最小実装として当面維持。将来の責務見直しは [`BLG-CMN-017`](backlog.md)（Version 1.x 以降）

---

## 2. 作業ボード（未完了）

**優先度**: 必須 = Version 1.0 公開ブロッカー／推奨 = 公開品質向上／任意 = 無くても公開可（[`version1_publish_scope.md`](../07_decisions/version1_publish_scope.md)）。Version 1.x 以降の作業は優先度列に `Version 1.x` と記載する。

部署管理（DEP）は [`BLG-PRJ-011`](backlog.md)（AI 協調開発フローの再現性検証）。UI 共通化・標準化（BLG-CMN-015 系）は本シリーズのスコープ外。

|ID|スコープ|優先度|作業内容|状態|関連|
|---|---|---|---|---|---|
|PRG-DEP-006|DEP|Version 1.x|部署管理 CRUD — 通し動作確認（登録・更新・削除・バリデーション）|未着手|PRG-DEP-005 後|
|PRG-EMP-010|EMP|任意|社員管理 CRUD — 構造・可読性・簡潔性の見直し（Controller / Service / Mapper / Form 等。必須の共通化・分割ではない）|未着手|PRG-EMP-009 完了後。Version 1.0 公開を待たない作業にしてよいが必須ではない|

---

## 3. Version 2 以降

Version 2 以降のテーマ・目指す状態は [`project.md`](project.md) §4 を参照する。

着手しない／後回しの個別項目は [`backlog.md`](backlog.md) の検討タイミング列（Version 1.x／2.x／3.x 等）で管理する。原則として、着手するまで本ドキュメントに PRG 行は設けない。

---

## 4. 目的

- 作業の全量が分かること
- 今どこまで進んでいるかが分かること
- 次に何をやるかが分かること
- AI セッション再開時の引き継ぎ起点とすること

レビューは独立した管理対象ではなく、**作業（PRG）の一工程** として扱います。詳細は backlog（未決定）・設計書（仕様）・判断記録・Git へ委譲します。

---

## 5. 記載範囲

情報の種類ごとの管理先は [`docs/README.md`](../README.md) §5 を正とします。本ドキュメントは **作業の現在地・PRG・次タスク** のみを持ちます。

**記載する**: フォーカス、PRG 状態、次タスク、ブロック（BLG リンク）、完了要約（学びがあるとき）、チェックリストへのリンク、Version 1.0 完了のポインタ（§8）、Version 2 以降の参照先ポインタ（§3）

**記載しない**: 仕様本文、指摘台帳、未決定一覧、チェックリスト本文、旧 REV 履歴、判断理由の詳細、中長期ロードマップ本文（→ [`project.md`](project.md) §4）、完了済み Version スコープの全量再掲（→ [`version1_publish_scope.md`](../07_decisions/version1_publish_scope.md)）、Version 2 以降のテーマ一覧の再掲（→ [`project.md`](project.md) §4・[`backlog.md`](backlog.md)）

[`screens.md`](../03_system/screens.md) / [`features.md`](../03_system/features.md) / [`tables.md`](../03_system/tables.md) はシステムの俯瞰一覧であり、「状態」列は設けない。作業進捗は本ドキュメントを正とする。一覧は実装することが決まった時点で掲載する（設計・実装の完了は待たない。目安は該当 PRG を切ったとき、または Version スコープに含めたとき。判断記録：[`system_overview_lists_without_status.md`](../07_decisions/system_overview_lists_without_status.md)）。

---

## 6. 記載ルール

### ID

- `PRG-{プレフィックス}-{連番3桁}`（例：`PRG-EMP-002`）。プレフィックスは [`backlog.md`](backlog.md) §3 と同様。
- 完了後も ID は変更・再利用しない。REV-ID は採番しない。

### 状態

|状態|意味|
|---|---|
|未着手|未着手（優先順位は §2）|
|実装中|設計〜実装中|
|レビュー中|ソース／設計レビュー中|
|動作確認中|通し動作確認など|
|完了|実装・レビュー・動作確認まで一区切り|
|取消|方針変更などで不要|

工程は省略可。優先順位は状態と別（§2）。判断待ちは BLG（PRG 行にしない）。

### 完了要約・運用

- 学びがあるときだけ、完了 PRG の下に短い要約（概要・判断・反映リンク等）。問題なしのみは省略可（[`development.md`](../02_rules/development.md) §3.1）
- 未完了は §2、完了は §7。状態・次タスク・ブロックが変わったら更新する
- §2／§7 はカテゴリ別の表に分けず、未完了・完了を各1表とする。カテゴリは「スコープ」列（`PRJ`／`CMN`／`EMP`／`DEP` 等）で示す

### 作業メモ

- 作業メモには、特定の PRG-ID に紐づく内容を書かない
- PRG に紐づく詳細は §7 の完了サマリー（`##### PRG-XXX`）に記載する
- 作業メモに残すのは、どの PRG にも属さない横断的な経緯のみとする
- 判断記録（`docs/07_decisions`）や backlog に既にある内容は、作業メモへ再掲せずリンクで足りる場合はリンクのみとする

---

## 7. 作業ボード（完了・履歴）

|ID|スコープ|作業内容|完了日|関連|
|---|---|---|---|---|
|PRG-DEP-005|DEP|DEP002 部署登録・編集 — POST 実装（登録・更新・削除）およびレビュー。部署コード編集可能化を含む（**実装**）|2026/08/05|[`DEP002_部署登録・編集.md`](../05_screen/DEP002_部署登録・編集.md) §10、[`BLG-EMP-007`](backlog.md)|
|PRG-DEP-004|DEP|DEP002 部署登録・編集 — GET 実装およびレビュー（**実装**）|2026/08/05|[`DEP002_部署登録・編集.md`](../05_screen/DEP002_部署登録・編集.md)、[`BLG-PRJ-011`](backlog.md)|
|PRG-DEP-003|DEP|DEP002 部署登録・編集 — 画面設計書作成・設計レビュー|2026/08/05|[`DEP002_部署登録・編集.md`](../05_screen/DEP002_部署登録・編集.md)、[`EMP002_社員登録・編集.md`](../05_screen/EMP002_社員登録・編集.md)（踏襲元）|
|PRG-DEP-002|DEP|DEP001 部署一覧 — 検索・一覧 GET 実装およびレビュー（**実装**）|2026/08/05|[`DEP001_部署一覧.md`](../05_screen/DEP001_部署一覧.md)、[`BLG-PRJ-011`](backlog.md)|
|PRG-DEP-001|DEP|DEP001 部署一覧 — 画面設計書作成・設計レビュー|2026/08/05|[`DEP001_部署一覧.md`](../05_screen/DEP001_部署一覧.md)、[`EMP001_社員一覧.md`](../05_screen/EMP001_社員一覧.md)（踏襲元）|
|PRG-CMN-004|CMN|DBマイグレーション基盤 — Flyway導入（依存追加・既存SQLの標準配置移動・setup.md／db.md更新・動作確認）|2026/08/02|[`flyway_adoption.md`](../07_decisions/flyway_adoption.md)、[`setup.md`](setup.md) §5、[`db.md`](../02_rules/db.md) §6|
|PRG-PRJ-003|PRJ|公開品質 — GitHub 初見視点の公開品質ウォークスルー（必須項目の実施・必須 NG の是正確認）（**検証**）|2026/07/30|[`public_quality_walkthrough_gate.md`](../07_decisions/public_quality_walkthrough_gate.md)。実施は `private/verification/prg_prj_003_public_walkthrough.md`（公開しない）|
|PRG-PRJ-002|PRJ|公開準備 — README／setup 通し、正式名称・Maven／パッケージ・ライセンス反映、**公開対象の確定・新 Public 作成・開発正本の切替**（**改修**）|2026/07/30|[`public_release_new_repo_snapshot.md`](../07_decisions/public_release_new_repo_snapshot.md) §5.3、[`public_private_repo_topology.md`](../07_decisions/public_private_repo_topology.md)、[`knowledge_publish_inventory.md`](../07_decisions/knowledge_publish_inventory.md)|
|PRG-PRJ-001|PRJ|横断 — 開発ドキュメントの公開向けレビュー・整理（リンク解消・表現レビュー A／B・Document Version 初版揃え）|2026/07/30|BLG-PRJ-002、BLG-PRJ-009、[`version1_publish_scope.md`](../07_decisions/version1_publish_scope.md)|
|PRG-CMN-003|CMN|メッセージ表示 — 登録・更新・削除後の成功メッセージ暫定実装|2026/07/24|[`BLG-EMP-005`](backlog.md)、[`BLG-CMN-001`](backlog.md)、[`ui.md`](../02_rules/ui.md) §7|
|PRG-CMN-002|CMN|CMN001 — TOP 画面の完成度確認・整備（パンくず要否を含む）|2026/07/24|[`CMN001_TOP.md`](../05_screen/CMN001_TOP.md)|
|PRG-CMN-001|CMN|共通レイアウト — 全画面での fragment 利用・表示確認|2026/07/24|[`ui.md`](../02_rules/ui.md) §2.3・§2.4|
|PRG-EMP-009|EMP|EMP002 CRUD — 通し動作確認（登録・更新・削除・バリデーション・削除済みマスタ維持）|2026/07/24|`private/verification/prg_emp_009_crud_checklist.md`|
|PRG-EMP-008|EMP|EMP002 社員登録・編集 — POST 実装（登録・更新・削除）およびソースレビュー|2026/07/23|[`emp002_post_validation_groups.md`](../07_decisions/emp002_post_validation_groups.md)、[`emp002_soft_deleted_master_reference.md`](../07_decisions/emp002_soft_deleted_master_reference.md)|
|PRG-EMP-007|EMP|EMP002 社員登録・編集 — GET 実装（削除済みマスタ補完含む）およびソースレビュー|2026/07/23|[`emp002_soft_deleted_master_reference.md`](../07_decisions/emp002_soft_deleted_master_reference.md)|
|PRG-EMP-004|EMP|EMP001 社員一覧 — 操作列廃止・社員番号リンク表示およびソースレビュー|2026/07/23|[`employee_list_navigation_pattern.md`](../07_decisions/employee_list_navigation_pattern.md)|
|PRG-EMP-005|EMP|EMP002 社員登録・編集 — 画面設計書作成・設計レビュー|2026/07/13|[`EMP002_社員登録・編集.md`](../05_screen/EMP002_社員登録・編集.md)、[`employee_list_navigation_pattern.md`](../07_decisions/employee_list_navigation_pattern.md)|
|PRG-EMP-006|EMP|EMP003 社員詳細 — 画面設計書作成|2026/07/13|廃止（[`employee_list_navigation_pattern.md`](../07_decisions/employee_list_navigation_pattern.md)。画面一覧から削除）|
|PRG-EMP-003|EMP|EMP001 社員一覧 — 削除 POST 実装（論理削除）|2026/07/13|取消。削除は EMP002 へ移管（[`employee_list_navigation_pattern.md`](../07_decisions/employee_list_navigation_pattern.md)）|
|PRG-EMP-002|EMP|EMP001 社員一覧 — 検索・一覧 GET 実装およびレビュー一式|2026/07/13|commit `b3ba576` 等|
|PRG-EMP-001|EMP|EMP001 社員一覧 — 画面設計書整備|2026/07/12|[`EMP001_社員一覧.md`](../05_screen/EMP001_社員一覧.md)|

##### PRG-DEP-005（DEP002 POST 実装）

- レビュー概要：Controller（create／update／delete）、Service（一意性チェック・DB制約違反ハンドリング）、Mapper／XML（insert／update／論理削除）を Cursor が実装。あわせて `PRG-DEP-004` 時点で readonly だった部署コードを編集可能に修正（`th:field` 化、`ValidationGroups.Update` 追加）。Claude Code のソースレビューで設計書（`DEP002_部署登録・編集.md`）との齟齬なしを確認。人間による実DB動作確認も完了
- 修正・判断：部署コードの UNIQUE 制約違反は EMP002（F-01）と同じ `PSQLException` 物理制約名判定方式を踏襲（`department_department_code_key`）。削除確認ダイアログの部署コード・部署名は編集画面表示時点（DB値）を使用し、未保存の入力値は使わない設計を実装で維持
- 反映：`DepartmentController`（POST 部分）、`DepartmentService`、`DepartmentMapper`／XML、`department/form.html`。部署コード編集可否の判断は [`BLG-EMP-007`](backlog.md)・本設計書 §10 参照

##### PRG-DEP-004（DEP002 GET 実装）

- レビュー概要：Controller（showCreate／showEdit）、DepartmentForm、form.html を Cursor が設計書通りに実装。Claude Code のソースレビューで問題なしを確認
- 修正・判断：実装当時は部署コードを EMP002 の社員番号と同様 readonly で実装。人間の動作確認を経て「部署コードは編集可能にすべき」という指摘が出て、`DEP002_部署登録・編集.md` を改訂（§10 参照）。実際のコード修正（readonly解除、`ValidationGroups.Update` 追加）は `PRG-DEP-005` に含めて対応する
- 反映：`DepartmentController`（GET 部分）、`DepartmentForm`、`department/form.html`

##### PRG-DEP-003（DEP002 画面設計書）

- レビュー概要：`EMP002_社員登録・編集.md` の構成をそのまま踏襲して作成。人間レビューで問題なしと確認
- 修正・判断：部署コードの編集可否は当初 EMP002 と同じ読み取り専用としたが、`PRG-DEP-004` の動作確認後に編集可能へ改訂（詳細は本設計書 §10）。理由は `department_code` を外部キー参照するテーブルが無くデータ整合性上の制約が無いこと、EMP002 の社員番号は将来の自動採番を想定し編集の必要性が低いこととの対比
- 反映：[`screens.md`](../03_system/screens.md)・[`features.md`](../03_system/features.md) へ掲載。[`BLG-EMP-007`](backlog.md) へ社員番号の編集可否を検討事項として追記

##### PRG-DEP-002（DEP001 検索・一覧 GET 実装）

- レビュー概要：Controller／Service／Form／Mapper／XML／HTML／JS を Cursor が設計書通りに実装。Claude Code のソースレビューでエスケープ処理・並び順・DataTables 初期化・画面遷移方針のいずれも設計書との齟齬なしを確認。人間による実DB動作確認も完了
- 修正・判断：パンくずは EMP001（`breadcrumb-employee.html`）と同様に `breadcrumb-department.html` を専用新設（実装時の軽微な判断として処理し、専用の判断記録は作成せず）
- 反映：`DepartmentController`／`DepartmentService`／`DepartmentSearchForm`／`DepartmentMapper`（検索メソッド追加）／`department/list.html`／`department/list.js`。`BLG-PRJ-011`（AI協調開発フローの再現性検証）の途中経過として、EMP001実装時と同じ手順・粒度で初回から齟齬なく完了

##### PRG-DEP-001（DEP001 画面設計書）

- レビュー概要：`EMP001_社員一覧.md` の構成・画面遷移方針（操作列廃止・部署コードリンク・削除は編集画面へ移管）をそのまま踏襲して作成。Claude Code が主担当として作成し、人間レビューで問題なしと確認
- 修正・判断：要件定義書は独立して作らず、画面設計書 §1〜§2 に要件レベルの内容を含める既存方式（EMP002 と同じ）を継続する方針を確認
- 反映：[`screens.md`](../03_system/screens.md)・[`features.md`](../03_system/features.md) へ掲載

##### PRG-PRJ-003（公開品質ウォークスルー）

- レビュー概要：GitHub 初見視点で必須（§3〜§6）・推奨（§7）を実施。いずれも OK。Version 1.0 公開可
- 修正・判断：setup に PowerShell の `.\mvnw.cmd` と `java -version` を追記。README 特徴節のスクショ TODO は削除（既存 TOP／一覧／編集で十分）。About・Topics は整備済み
- 反映：[`setup.md`](setup.md)、[`README.md`](../../README.md)、`private/verification/prg_prj_003_public_walkthrough.md`

##### PRG-PRJ-002（公開準備）

- レビュー概要：新 Public リポジトリ作成、公開対象コピー、初回コミット／push、開発正本の切替まで完了
- 修正・判断：公開漏れチェック（`private` 混入・秘密情報・非公開リンク・旧 `knowledge/` 残存）は問題なし。旧リポジトリへのアーカイブ注記は検討のうえ見送り（対応不要）
- 反映：https://github.com/ysmkotm/practical-app-starter 。[`setup.md`](setup.md) の clone URL 確定。`.gitignore` に `/private/` 追加。作業正本を新 Public クローンへ切替。初回コミット `add: Version 1.0 初版公開`

##### PRG-PRJ-001（公開向け整理）

- レビュー概要：公開前の矛盾・導線・表現・Document Version を整え、新 Public コピー可能な状態にした。相対リンクを機械チェックしリンク切れ 9 件を是正。`docs/06_api` は空テンプレートを廃し [`docs/06_api/README.md`](../06_api/README.md) で「API 追加時に整備」と明記。公開代表を直下 `prompts/` のみとし `knowledge/` を廃止（[`public_prompts_at_repo_root.md`](../07_decisions/public_prompts_at_repo_root.md)）
- 修正・判断：差別化メッセージを README 冒頭へ反映（タグライン「業務システムの再現可能な型 ― 開発ドキュメントを正本に、人と AI で進める」）。認知ターゲットを仮確定（[`audience_target.md`](../07_decisions/audience_target.md)）。Document Version 初版揃えはテンプレヘッダも対象、`private/` と README アプリ Version は除外。更新日は作業日 `2026/07/30`（実公開日が異なれば再揃え）。実施順は表現レビュー（A／B）→ Document Version 揃え。公開 docs 内の個別 `private` 参照は一般化（[`contents_workspace_decision_publish_scope.md`](../07_decisions/contents_workspace_decision_publish_scope.md)）
- 反映：公開対象 82 件の Document Version `1.0`／更新日揃え。表現レビューは `private/verification/prg_prj_001_editorial_review_checklist.md`

##### PRG-CMN-004（Flyway 導入）

- レビュー概要：起動時自動マイグレーションに置換。空 DB への適用・2 回目起動・既存 CRUD を動作確認済み
- 修正・判断：既存ローカル DB は再作成を標準。`baseline-on-migrate` は既定で有効にしない（[`flyway_adoption.md`](../07_decisions/flyway_adoption.md)）。初見視点レビューを受け Version 1.x 技術対応から前倒し着手
- 反映：`pom.xml`、`src/main/resources/db/migration/`、[`setup.md`](setup.md)、[`db.md`](../02_rules/db.md)

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

## 8. Version 1.0（履歴）

Version 1.0 は完成している（必須・推奨はすべて完了。公開可）。

- スコープの詳細：[`version1_publish_scope.md`](../07_decisions/version1_publish_scope.md) §5
- 完了した作業の記録：本ドキュメント §7
- 任意項目・V1.0 対象外（意図的に先送りした項目）：[`backlog.md`](backlog.md) および本ドキュメント §2 で個別管理

---

## 9. 関連ドキュメント

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
|[`docs/07_decisions/progress_backlog_single_table_ssot.md`](../07_decisions/progress_backlog_single_table_ssot.md)|progress／backlog の単一表・参照ポインタ化と人間向け読み順|
|[`docs/07_decisions/review_findings_index.md`](../07_decisions/review_findings_index.md)|レビュー指摘一覧の導入|
|[`docs/07_decisions/employee_list_navigation_pattern.md`](../07_decisions/employee_list_navigation_pattern.md)|社員一覧の画面遷移方針|
|[`docs/07_decisions/verification_checklist_and_test_assets.md`](../07_decisions/verification_checklist_and_test_assets.md)|動作確認チェックリストとテスト資料の役割分担|

---

## 10. 更新履歴

|Document Version|日付|更新内容|
|---|---|---|
|2.0|2026/08/05|アクション向け章（現在地・未完了・V2）と履歴章（完了・V1.0）を分離。単一表・SSOT ポインタ化を含む構成整理|
|1.0|2026/07/30|初版公開（Document Version 初版揃え）|
