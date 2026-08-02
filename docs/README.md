# 開発ドキュメント索引

**Document Version** : 1.2

**更新日** : 2026/08/02

---

## 1. 概要

開発ドキュメントの索引です。

開発ドキュメントは Markdown で管理しています。Cursor / VS Code では Markdown Preview を使うと、見出し・表・リンクが見やすくなります。

---

## 2. 開発ドキュメントの全体像

本プロジェクトでは `docs` 配下の文書を **開発ドキュメント** と総称しています。

開発ドキュメントは以下のカテゴリへ分類し、正本（SSOT）として管理しています。

|カテゴリ|フォルダ|役割|主な参照タイミング|
|---|---|---|---|
|プロジェクト概要|[`docs/01_project`](01_project/)|概要・環境構築手順・作業管理を扱う|プロジェクトを理解するとき<br>環境構築するとき<br>作業再開するとき<br>作業・保留事項を整理・確認するとき<br>指摘事項を記載・確認するとき|
|開発ルール|[`docs/02_rules`](02_rules/)|開発方針やコーディング等の共通ルールを定める|ソースコードやドキュメントの更新前|
|システム全体設計|[`docs/03_system`](03_system/)|機能一覧やER図等、システム全体を俯瞰する|全体構成の確認・変更を行うとき|
|DB設計書|[`docs/04_db`](04_db/)|テーブル定義書・VIEW定義書等を整理する|DBを設計・実装するとき|
|画面設計書|[`docs/05_screen`](05_screen/)|画面単位の設計内容を整理する|画面を設計・実装するとき|
|API設計書|[`docs/06_api`](06_api/)|API単位の設計内容を整理する|APIを設計・実装するとき|
|判断記録|[`docs/07_decisions`](07_decisions/)|技術選定や設計変更等、後から理由を確認したい判断を記録する|判断の背景を残すとき<br>過去の経緯を確認するとき|

---

## 3. 初めて読む場合

プロジェクトに初めて触れる場合は、以下の順序で読むことを推奨します。

1. [`docs/01_project/project.md`](01_project/project.md) — 目的・コンセプト・ロードマップ
2. [`docs/01_project/setup.md`](01_project/setup.md) — 開発環境構築（ローカルで動かす場合）
3. [`docs/02_rules/development.md`](02_rules/development.md) — 開発ルール（全体方針）
4. [`docs/02_rules/ai.md`](02_rules/ai.md) — AI協調開発の方針
5. [`docs/03_system/features.md`](03_system/features.md) — 機能一覧（システム全体の俯瞰）
6. [`docs/01_project/backlog.md`](01_project/backlog.md) — 未決定・保留事項の確認
7. [`docs/01_project/progress.md`](01_project/progress.md) — 作業再開時の現在地確認
8. [`docs/01_project/review_findings.md`](01_project/review_findings.md) — レビュー指摘の処理先・対応状況

---

## 4. タスク別の参照先

|タスク|参照するドキュメント|
|---|---|
|環境構築|[`docs/01_project/setup.md`](01_project/setup.md)|
|機能実装（全体）|[`docs/03_system/features.md`](03_system/features.md)、[`docs/02_rules/development.md`](02_rules/development.md) §2・§3・§3.1、[`docs/02_rules/ai.md`](02_rules/ai.md) §5・§5.1|
|システム全体の確認|[`docs/03_system/features.md`](03_system/features.md)、[`docs/03_system/screens.md`](03_system/screens.md)、[`docs/03_system/tables.md`](03_system/tables.md)、[`docs/03_system/er.md`](03_system/er.md)、[`docs/03_system/common_codes.md`](03_system/common_codes.md)|
|Cursor での実装|[`docs/02_rules/ai.md`](02_rules/ai.md) §2.3、[`.cursor/rules/project.mdc`](../.cursor/rules/project.mdc)|
|ChatGPT での設計相談|[`docs/02_rules/ai.md`](02_rules/ai.md) §2.2|
|コーディング|[`docs/02_rules/coding.md`](02_rules/coding.md)（§3〜§9：レイヤー・データクラス・入力・Lombok・Controller / Service / Mapper）、[`docs/02_rules/naming.md`](02_rules/naming.md)、[`docs/02_rules/directory.md`](02_rules/directory.md)|
|ディレクトリ構成|[`docs/02_rules/directory.md`](02_rules/directory.md)|
|画面 UI 実装|[`docs/02_rules/ui.md`](02_rules/ui.md)、[`docs/05_screen/`](05_screen/)|
|DB設計|[`docs/02_rules/db.md`](02_rules/db.md)、[`docs/04_db/`](04_db/)、[`docs/03_system/tables.md`](03_system/tables.md)、[`docs/03_system/er.md`](03_system/er.md)|
|画面設計|[`docs/05_screen/`](05_screen/)、[`docs/03_system/screens.md`](03_system/screens.md)、[`docs/02_rules/ui.md`](02_rules/ui.md)|
|API設計|[`docs/06_api/`](06_api/)|
|ドキュメントの作成・修正|[`docs/02_rules/documentation.md`](02_rules/documentation.md)|
|記録の振り分け|[`docs/02_rules/recording.md`](02_rules/recording.md)、[`docs/02_rules/development.md`](02_rules/development.md) §8|
|Git 操作|[`docs/02_rules/git.md`](02_rules/git.md)|
|作業再開・現在地整理・次タスク|[`docs/01_project/progress.md`](01_project/progress.md)、[`docs/01_project/backlog.md`](01_project/backlog.md)、[`docs/01_project/review_findings.md`](01_project/review_findings.md)、[`docs/01_project/project.md`](01_project/project.md) §4（ロードマップ。未着手テーマの正本。）|
|保留事項・未決定事項|[`docs/01_project/backlog.md`](01_project/backlog.md)|
|レビュー指摘の追跡|[`docs/01_project/review_findings.md`](01_project/review_findings.md)|
|判断事項の記録（決定済み）|[`docs/07_decisions/`](07_decisions/)、[`docs/02_rules/recording.md`](02_rules/recording.md)、[`docs/02_rules/development.md`](02_rules/development.md) §8|

---

## 5. 情報の置き場所

|種類|管理先|内容の例|
|---|---|---|
|作業管理（現在地・全量・次）|[`docs/01_project/progress.md`](01_project/progress.md)|フォーカス、PRG の状態、次タスク、完了時の短い要約、**Version 1.0 作業全量（§6）**、Version 2 以降で着手しない一覧（§7）|
|今後検討する事項|[`docs/01_project/backlog.md`](01_project/backlog.md)|設計上の保留、実装前の未決定、実装中に見つかった課題（検討タイミングに Version 区分）|
|レビュー指摘の索引|[`docs/01_project/review_findings.md`](01_project/review_findings.md)|指摘の要約、対応方針・状態、処理先への参照|
|決定済み事項|[`docs/07_decisions/`](07_decisions/)|技術選定・設計方針の採用理由、却下した案|
|記録の分類・公開／非公開|[`docs/02_rules/recording.md`](02_rules/recording.md)|判断記録／ai_logs／contents の振り分け、価値判定、提案形式|
|仕様・ルールの正本|設計書、[`docs/02_rules/`](02_rules/) 等|画面仕様、コーディングルール|
|システムの機能一覧|[`docs/03_system/features.md`](03_system/features.md)|機能ID・概要（作業進捗は progress）|
|画面一覧|[`docs/03_system/screens.md`](03_system/screens.md)|画面ID・URL・設計書への入口（作業進捗は progress）|
|Version 単位の開発予定（ロードマップ）|[`docs/01_project/project.md`](01_project/project.md) §4|V1.0 / V1.x / V2.x / V3.x。公開スコープは [`version1_publish_scope.md`](07_decisions/version1_publish_scope.md)。公開・提供方針は [`public_offering_strategy.md`](07_decisions/public_offering_strategy.md)|
|再利用プロンプト|[`prompts/`](../prompts/)|汎用テンプレートと、記事・題材向け実例（[`prompts/cases/`](../prompts/cases/)）|
