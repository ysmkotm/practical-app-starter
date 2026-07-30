# 記録ルール

**Document Version** : 1.0

**更新日** : 2026/07/30

---

## 1. 目的

本ドキュメントは、開発過程で得た判断・知見・発信や教材化のメモを、どの種別でどこに記録するかの正本（SSOT）です。

- 記録種別の分類と保存先
- 公開／非公開の前提（書き分け）
- 記録価値の判定
- 記録提案の形式（AI 協調時）

行動トリガー（いつ提案するか）は Cursor 向けに [`.cursor/rules/recording.mdc`](../../.cursor/rules/recording.mdc)、Claude Code 向けに [`CLAUDE.md`](../../CLAUDE.md) に置き、詳細は本ドキュメントへ委譲します。

---

## 2. 基本方針

- 開発過程そのものを資産として扱う。ただしすべてを記録せず、再利用価値があるものだけ残す。
- 記録を強制しない。小さな修正や単純な変更は記録不要。
- 同じ内容を複数箇所へ重複記載しない。既存の開発ドキュメント・判断記録と矛盾する内容は提案・作成しない。
- 公開範囲の前提は [`public_offering_strategy.md`](../07_decisions/public_offering_strategy.md) §5.2 に従う。

---

## 3. 記録種別と公開／非公開

記録候補は、次のいずれかに分類します。

|種別|該当する内容|保存先|公開|テンプレート|
|---|---|---|---|---|
|判断記録|技術選定、設計方針、命名規則、実装中の設計変更、ディレクトリ構成、ドキュメント構成・テンプレートの変更、開発ルールの新設・大幅な変更、運用方針の決定、複数案の比較検討|`docs/07_decisions/`|**公開**（Version 1.0 公開時）|[`docs/07_decisions/_template.md`](../07_decisions/_template.md)|
|AI協調開発ログ|AI との役割分担・依頼方法・レビュー方法、依頼文・プロンプトの改善知見、AI との相談による結論の整理・変更、AI に任せる範囲・人間が確認する範囲の決定、AI の得意・苦手・失敗傾向、AI が自身の挙動やミスの原因を説明した場合|`private/ai_logs/`|**非公開**（教材原資・生の知見）|`private/ai_logs/_template.md`（生ログは残さず要約）|
|発信・教材化メモ|記事・解説・教材など対外発信用の気付き・ノウハウ、実例として記事化・教材化できそうな開発事例、他プロジェクトでも再利用できそうなテンプレート・仕組み、プロジェクトや AI 協調開発の差別化ポイント、将来価値になりそうなアイデア|`private/contents/seeds/`|**非公開**（教材原資）|`private/contents/_template.md`|

### 3.1 書き分けの目安

|前提|書く内容の目安|
|---|---|
|公開（判断記録）|第三者が読んでもよい結論・比較・採用理由。個人の未整理メモや発信・教材向けの深掘りは書かない|
|非公開（ai_logs）|進め方の試行錯誤・失敗傾向・依頼文の改善など、生に近い要約。教材の素材になり得る深さでよい|
|非公開（contents）|対外発信・教材を想定した気付き。未製品・非公開であることを前提に書いてよい|

迷った場合の振り分け：

- **システムの成立や今後の実装に必要な決定** → 判断記録
- **AI との進め方・失敗から得た運用知見** → AI協調開発ログ
- **将来の記事・教材向けの気付き** → `private/contents/seeds/`（発信・教材化メモ）

同一テーマが複数種別にまたがる場合は、結論を判断記録（または開発ルール）へ置き、経緯・学びは ai_logs／contents へ要約してよい。

執筆着手後の構成・下書き・公開控え（piece）は記録ルールの対象外とし、`private/contents/` 側の運用に従う（`private/contents/README.md`）。フォルダ名 `seeds/` は制作ワークスペース側の内部配置であり、公開文書上の種別名は「発信・教材化メモ」とする。

判断記録と backlog／progress／review_findings の関係は [`development.md`](development.md) §8 を参照してください。ai_logs と contents の役割分担の経緯は [`contents_and_ai_logs_separation.md`](../07_decisions/contents_and_ai_logs_separation.md) を参照してください。制作ワークスペースの詳細は非公開の運用資料（ローカル）で管理します（線引きは [`contents_workspace_decision_publish_scope.md`](../07_decisions/contents_workspace_decision_publish_scope.md)）。

---

## 4. 記録価値の判定

|評価|基準|
|---|---|
|★★★★★|発信・教材化できる可能性が高い|
|★★★★☆|他の開発者の参考になる|
|★★★☆☆|判断理由として残す価値がある|
|★★☆☆☆|必要なら残す|
|★☆☆☆☆|記録不要|

原則として、★★★☆☆以上の場合に記録（または記録提案）を行います。記録タイミングの規模目安は [`development.md`](development.md) §3.1 にも従います。

---

## 5. 記録提案の形式（AI 協調時）

AI が記録を提案する場合は、次の形式とします。**下書き本文はチャット上に表示せず**、提案のみ行い、承認後に該当フォルダへファイルを作成します。

```markdown
## 記録提案

記録種別：
保存先：
記録価値：
タイトル案：

### 記録した方がよい理由

```

人間が自ら記録する場合は、本形式は不要です。該当テンプレートに従って作成してください。

---

## 6. AIツールのルールとの関係

|置き場|役割|
|---|---|
|[`.cursor/rules/recording.mdc`](../../.cursor/rules/recording.mdc)|Cursor 向けの行動トリガー（記録係として振る舞う、価値を検知したら提案する）|
|[`CLAUDE.md`](../../CLAUDE.md)|Claude Code 向けの行動トリガー（同上）|
|本ドキュメント|分類・保存先・公開／非公開・価値基準・提案形式の正本|

`.cursor/rules` および `CLAUDE.md` に詳細を重複記載しません（[`cursor_rules_slimming.md`](../07_decisions/cursor_rules_slimming.md)）。

---

## 7. 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`.cursor/rules/recording.mdc`](../../.cursor/rules/recording.mdc)|Cursor の記録係行動トリガー|
|[`CLAUDE.md`](../../CLAUDE.md)|Claude Code の記録係行動トリガー|
|[`docs/02_rules/development.md`](development.md)|開発ルール（判断記録 §8、変更規模 Tier）|
|[`docs/02_rules/ai.md`](ai.md)|AI協調開発の方針|
|[`docs/07_decisions/public_offering_strategy.md`](../07_decisions/public_offering_strategy.md)|公開範囲（§5.2）|
|[`docs/07_decisions/contents_and_ai_logs_separation.md`](../07_decisions/contents_and_ai_logs_separation.md)|ai_logs / contents の分離|
|[`docs/07_decisions/contents_workspace_decision_publish_scope.md`](../07_decisions/contents_workspace_decision_publish_scope.md)|制作まわり判断の公開／非公開線引き|
|[`docs/07_decisions/cursor_rules_slimming.md`](../07_decisions/cursor_rules_slimming.md)|Cursor ルールは入口のみ|
|[`docs/07_decisions/_template.md`](../07_decisions/_template.md)|判断記録テンプレート|
|`private/ai_logs/_template.md`|AI協調開発ログテンプレート|
|`private/contents/_template.md`|発信・教材化メモのテンプレート|
|`private/contents/README.md`|contents の運用（メモの置き場・執筆着手後の piece 等）|
|`private/contents/contents_as_content_workspace.md`（非公開・ローカル）|制作ワークスペース化の判断正本|
|[`docs/07_decisions/knowledge_publish_inventory.md`](../07_decisions/knowledge_publish_inventory.md)|公開セット（prompts 中心）|
|[`docs/07_decisions/public_private_repo_topology.md`](../07_decisions/public_private_repo_topology.md)|公開代表／`private` 分離|
|[`docs/07_decisions/public_prompts_at_repo_root.md`](../07_decisions/public_prompts_at_repo_root.md)|直下 `prompts/` と knowledge 廃止|
