# プロンプト索引

**Document Version** : 1.0

**更新日** : 2026/07/30

---

## 1. 概要

本フォルダは、ChatGPT・Cursor 等へ **繰り返し利用する依頼文（プロンプト）** の入口です。クローン・セットアップ直後の理解から、開発の区切りまで使えます。

AI協調開発の方針については [`docs/02_rules/ai.md`](../docs/02_rules/ai.md) を参照してください。

---

## 2. プロンプト一覧

### セットアップ直後・学習向け

|ファイル|想定AI|用途|
|---|---|---|
|[`project_explain.md`](project_explain.md)|Cursor|プロジェクトの説明依頼|
|[`docs_reading_guide.md`](docs_reading_guide.md)|Cursor|開発ドキュメントの読み方ガイド|
|[`ai_collaboration_start.md`](ai_collaboration_start.md)|Cursor|AI協調の始め方|
|[`first_hands_on.md`](first_hands_on.md)|Cursor|最初の一歩（触ってみる）|
|[`practice_change_ideas.md`](practice_change_ideas.md)|Cursor|小さな改修お題の提案|
|[`project_learner_review.md`](project_learner_review.md)|Cursor|学習者向けプロジェクトレビュー|

### 開発の区切り・作業向け

|ファイル|想定AI|用途|
|---|---|---|
|[`next_task.md`](next_task.md)|Cursor|区切り後・再開時の次タスク提案|
|[`project_strategy_meeting.md`](project_strategy_meeting.md)|Cursor|プロジェクト全体の戦略会議|
|[`verification_checklist_create.md`](verification_checklist_create.md)|Cursor|動作確認チェックリストの作成依頼|

---

## 3. 使い方

1. 一覧から目的に合うプロンプトを開く
2. 依頼文をコピーし、想定 AI へ貼り付ける
3. 必要に応じて追記する（[`ai.md`](../docs/02_rules/ai.md) §4.3）
