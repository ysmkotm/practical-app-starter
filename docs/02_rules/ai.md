# AI協調開発の方針

**Document Version** : 1.1

**更新日** : 2026/08/05

---

## 1. 基本方針

本プロジェクトでは、AIを単なる**コード生成ツール**ではなく、**システム開発・プロジェクト管理・ナレッジ管理を横断して支える開発パートナー**として利用します。

ChatGPT・Cursor・Claude 等の AI を**複数の専門役**として使い分け、一人でも小規模チームに近い開発体制を目指します。

なお、要件・優先順位・最終判断は人間が担います。AIが生成した内容は、そのまま採用せず、人間が採用判断を行います。

本ドキュメントでは、役割分担（§2）、品質確認（§3）、日常運用（§4）、開発プロセスとの接点（§5）を定めます。

### AI利用時の基本ルール

- 開発ドキュメント・既存コードを優先する。
- 生成・修正は、人間が確認可能な単位で行う。
- 判断基準については、[`docs/02_rules/development.md`](development.md) §7 を参照する。
- 実装方針や設計方針に関わる変更は、実装前に確認を行う。
- 判断に迷う場合は、人間が最終判断を行う。
- 新しいルールや継続的に利用する内容が決まった場合は、必要に応じて `docs` 配下へ反映する。

---

## 2. 役割分担

人間・ChatGPT・Cursor・Claude Code の役割分担は次のとおりです。

|主体|主な役割（期待すること）|備考|
|---|---|---|
|人間|要件・仕様の決定<br>優先順位の決定<br>設計内容の最終判断<br>AI生成物の品質確認・採用判断（§3）<br>動作確認<br>Git管理||
|ChatGPT|開発方針・設計方針の相談<br>技術調査<br>命名提案<br>設計レビュー<br>コードレビュー|コード生成も可能だが、設計・レビュー・意思決定の支援を主な役割とする|
|Cursor|プロジェクト全体を考慮した実装支援<br>コードの実装・修正<br>ビルド・エラー修正の補助<br>記録は必要に応じて提案|入口: [`.cursor/rules/project.mdc`](../../.cursor/rules/project.mdc)<br>正本: `docs` 側<br>設計書・開発ドキュメントの作成・更新の主担当は 2026-08-05 より Claude Code へ変更（[`claude_code_role_expansion_documentation.md`](../07_decisions/claude_code_role_expansion_documentation.md)）。コード変更に伴うコミット準備案の提示は引き続き Cursor|
|Claude Code|ローカルの成果物を確認しながらの相談・レビュー<br>設計書・開発ドキュメントの作成・更新（`progress.md`／`backlog.md`／画面設計書／判断記録等）<br>作業管理・保留事項の更新支援<br>記録係（記録の提案・反映）|入口: [`CLAUDE.md`](../../CLAUDE.md)<br>正本: `docs` 側<br>コードの実装・修正・ビルド確認は引き続き Cursor（2026-08-05 更新。以前は「当面：相談・レビュー中心」だったが、ドキュメント作成・更新の主担当を追加。詳細は [`claude_code_role_expansion_documentation.md`](../07_decisions/claude_code_role_expansion_documentation.md)）|

---

## 3. 品質確認の方針

AIが生成したコード・ドキュメント・設計内容について、人間がすべてを詳細に確認することには限界があります。

そのため、本プロジェクトでは、AI生成物を無制限に採用せず、以下の方針で品質を確認します。

### 3.1 基本方針

- 人間がすべてを精読して確認する前提は置かない。
- それでも、採用判断は人間が行う。
- AIによる確認は補助であり、人間の確認を完全に代替しない。
- 生成・修正は、人間が一度に確認・採用判断できる単位で行う。
- すべてを精読することを前提にせず、**重要箇所・変更差分・整合性**を重点的に確認する。

### 3.2 人間が重点的に確認する領域

影響が大きく、後から修正コストが高くなりやすい領域は、人間が重点的に確認します。

- 設計方針
- 命名
- 責務分担
- セキュリティ
- データ整合性
- 変更範囲・影響範囲

実装前後の確認チェックリストは、[`docs/02_rules/development.md`](development.md) §5・§6 を参照してください。

---

## 4. 日常運用

### 4.1 共有ドキュメント（AI 連携基盤）

以下は、**セッションをまたいだ引き継ぎ**に特に使うドキュメントの例です。まだ読んでいない場合は、名称と役割だけ先に押さえれば十分です。詳しい使い方は [`docs/README.md`](../README.md) §3「初めて読む場合」の順で読み進めると把握しやすくなります。
必要に応じて人間が AI 間の橋渡し（報告文の作成など）を行います。

|ドキュメント|役割|
|---|---|
|[`docs/01_project/progress.md`](../01_project/progress.md)|作業管理の正本。現在地・全量・次タスク|
|[`docs/01_project/backlog.md`](../01_project/backlog.md)|保留・未決定事項|
|[`docs/01_project/review_findings.md`](../01_project/review_findings.md)|レビュー指摘の索引（要約・処理先）|
|[`docs/02_rules/`](../02_rules/)|開発ルール。AI が実装・レビュー時に参照する前提|
|[`docs/04_db/`](../04_db/)・[`05_screen/`](../05_screen/)・[`06_api/`](../06_api/)|設計書（DB・画面・API）|
|[`docs/07_decisions/`](../07_decisions/)|決定済み判断と理由|

置き場所の詳細は [`docs/README.md`](../README.md) §5 を正とします。

### 4.2 セッション再開と依頼の単位

- 作業再開時は、[`progress.md`](../01_project/progress.md) などを正本に現在地を揃える。整理・次タスクの提案は [`prompts/next_task.md`](../../prompts/next_task.md) を使って AI に依頼してよい。着手単位の最終判断は人間が行う。
- 実装・レビューを依頼する場合は、可能な範囲で **PRG-ID**（[`progress.md`](../01_project/progress.md) が管理する作業単位のID。例：`PRG-EMP-009`）と対象名・工程（実装／ソースレビュー／動作確認）を依頼文に含める。
- AI は自発的な振り返りが弱い場合がある。必要に応じて人間が進捗・記録の有無を確認する。

### 4.3 AIへの依頼方法

AIへ依頼する際は、可能な範囲で以下の情報を伝えます。

- 背景
- 目的
- 現在の状況
- 完成イメージ
- 制約事項
- 関連する設計書・開発ルール
- レビュー依頼時：PRG-ID、スコープ、レビュー対象・観点（[`progress.md`](../01_project/progress.md) を参照）
- 実装依頼時：PRG-ID、スコープ、関連設計書（[`progress.md`](../01_project/progress.md) を参照）
- 動作確認依頼時：PRG-ID、動作確認である旨。条件を満たす場合はチェックリスト作成を依頼してよい（[`verification_checklist_create.md`](../../prompts/verification_checklist_create.md)、[`development.md`](development.md) §3.1）

必要な情報を整理して伝えることで、より適切で品質の高い回答を得やすくなります。

なお、すべての情報を毎回伝える必要はありません。内容に応じて必要な情報を共有します。

ChatGPT はリポジトリを直接参照しないため、新しいチャットでは必要な前提を共有します。
共有するとよいものの例：

- 関連する開発ルール・設計書（`docs` 配下の該当箇所）
- 相談・レビューの対象と観点（画面・機能・ファイル範囲など）
- 完成イメージや制約（ある場合）
- 継続テーマなら、過去チャットの要約や結論（必要な範囲だけ）

### 4.4 チャット運用

#### ChatGPT

ChatGPT のチャットは、テーマが混ざらないように分けて使います。同じテーマは継続し、テーマが変わったら新しいチャットを作成します。

新しいチャットでは、必要に応じて関連する設計書や開発ルールを共有し、前提を揃えます（§4.3）。

命名や新規作成の細かいタイミングは必須の規則ではなく、次の例を参考にして構いません。

##### 命名例

チャットタイトルは、例えば次の形式です。カテゴリごとに番号を振ります。

- 【共通①】開発ルール・命名規則
- 【画面①】EMP001 社員一覧画面
- 【DB①】社員テーブル
- 【実装①】EMP001 一覧画面
- 【検討①】Spring Security

同じテーマで長くなった場合は、新しいチャットを作成し、番号を繰り上げても構いません。

##### 新しいチャットを作成するタイミングの例

- テーマが変わる場合
- チャットが長くなった場合
- 内容を整理したい場合
- 独立した議論として残したい場合

#### Cursor

Cursorのチャットは、実装単位または作業単位で利用します。

新しい実装・作業を始める際は、必要に応じて新しいチャットを作成します。

作業完了後は、必要に応じてそのまま継続利用しても、新しいチャットを作成しても構いません。

#### Claude Code

Claude Code のセッションは、相談・レビューのテーマ単位で利用します。ローカルの成果物を直接参照できるため、新しいセッションでも前提の共有は最小限で済みます。

テーマが変わる場合や、内容を整理したい場合は、新しいセッションを検討します。

---

## 5. 開発プロセスとの接点

AI協調は [`docs/02_rules/development.md`](development.md) §2 で述べる **3領域すべてを横断する支援手段** です。本セクションでは領域ごとの接点を示します。① システム開発の技術フローは [`development.md`](development.md) §3、工程の省略可否は §3.1（変更規模 Tier）に従います。

### 5.1 領域別の AI 接点（概要）

|領域|AI の使い方|参照|
|---|---|---|
|① システム開発|要件整理、設計相談、実装、コードレビュー、テスト支援|§5.2、§2|
|② プロジェクト管理|現在地整理、次タスク検討、[`backlog.md`](../01_project/backlog.md) 分類、[`progress.md`](../01_project/progress.md) / [`review_findings.md`](../01_project/review_findings.md) 更新支援|[`progress.md`](../01_project/progress.md) §4、[`review_findings.md`](../01_project/review_findings.md)、[§4.2](#42-セッション再開と依頼の単位)|
|③ ナレッジ管理|判断整理、ルール化、レビュー知見の整理、記録下書き|[`recording.md`](recording.md)、[`development.md`](development.md) §8、[`.cursor/rules/recording.mdc`](../../.cursor/rules/recording.mdc)、[`CLAUDE.md`](../../CLAUDE.md)|

### 5.2 ① システム開発での接点

|段階|主担当|AI の使い方|
|---|---|---|
|対象整理・設計|人間 + ChatGPT、Claude Code|方針相談（§2）。設計書の作成・更新は Claude Code（§2、2026-08-05 更新）|
|設計レビュー|ChatGPT（L で推奨、M 以下は任意）|設計書レビュー|
|実装|Cursor|設計書を参照した実装（§2）|
|実装レビュー|AI + 人間|コード・ドキュメント確認。記録基準は [`development.md`](development.md) §3.1|
|動作確認|人間|AI は補助。条件を満たす場合は **動作確認チェックリスト** を下書きし、人間が実施・判定する（[`verification_checklist_and_test_assets.md`](../07_decisions/verification_checklist_and_test_assets.md)、[`development.md`](development.md) §3.1）。本体は `private/verification/` に置く。依頼文の例：[`verification_checklist_create.md`](../../prompts/verification_checklist_create.md)|
|区切り|人間 + Claude Code（補助）|[`progress.md`](../01_project/progress.md) / [`backlog.md`](../01_project/backlog.md) / [`review_findings.md`](../01_project/review_findings.md) / [`docs/07_decisions/`](../07_decisions/) を Tier に応じて更新（2026-08-05 更新、以前は Cursor が補助）。ドキュメントのみの変更は Claude Code が、コード変更を伴う場合は Cursor が [`git.md`](git.md) §7 のコミット準備案を提示し、人間が Git 操作を実行|

レビューで保留が見つかった場合は [`backlog.md`](../01_project/backlog.md)（②）へ、方針が確定した場合は [`07_decisions/`](../07_decisions/)（③）や開発ルールへ反映します。追跡価値のある指摘は [`review_findings.md`](../01_project/review_findings.md) に索引として残します。Git 操作の実行範囲とコミット準備案は [`docs/02_rules/git.md`](git.md) §7 を正とします。

---

## 6. 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`docs/02_rules/development.md`](development.md)|開発ルール（3領域・機能実装の流れ・変更規模）|
|[`docs/02_rules/recording.md`](recording.md)|記録ルール（種別・公開／非公開・価値判定）|
|[`docs/07_decisions/development_activity_layers.md`](../07_decisions/development_activity_layers.md)|開発活動の3領域分離の判断記録|
|[`docs/02_rules/git.md`](git.md)|Git運用ルール（実行範囲・コミット準備案の正本）|
|[`docs/07_decisions/commit_prep_proposal_on_ai_file_change.md`](../07_decisions/commit_prep_proposal_on_ai_file_change.md)|ファイル変更完了時のコミット準備案提示の判断|
|[`docs/02_rules/documentation.md`](documentation.md)|ドキュメント記法ルール|
|[`docs/01_project/progress.md`](../01_project/progress.md)|進捗管理（作業管理の正本）|
|[`docs/01_project/backlog.md`](../01_project/backlog.md)|保留事項|
|[`docs/07_decisions/verification_checklist_and_test_assets.md`](../07_decisions/verification_checklist_and_test_assets.md)|動作確認チェックリストとテスト資料の役割分担|
