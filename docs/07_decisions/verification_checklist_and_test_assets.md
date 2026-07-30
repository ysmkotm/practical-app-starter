# 動作確認チェックリストとテスト資料の役割分担

**Document Version** : 1.0

**更新日** : 2026/07/30

**ステータス** : 決定（2026/07/26 更新。チェックリスト本体は公開しない）

---

## 1. 目的

PRG 単位の動作確認チェックリストと、将来のテスト資産（テストケース等）の役割・運用・置き場を定め、仕様正本との関係および AI 協調での使い方を明確にする。

---

## 2. 背景

EMP002 CRUD の通し確認（[`PRG-EMP-009`](../01_project/progress.md)）で、AI が設計書・backlog・判断記録を踏まえた動作確認チェックリストを下書きし、人間が実施する運用を試した。抜け漏れ防止に有効だった一方、次の論点が残った。

- 毎回必須にすると Tier による工程省略と衝突する
- 「テスト」と呼ぶと単体テスト・QA 計画と混同しやすい
- チェックリスト本体を仕様や恒久成果物と混同しやすい
- 正式なテスト資料用フォルダ（`docs/08_test` 等）をいつ作るか

ChatGPT・Cursor との検討を経て、条件付き標準化とし、テストフォルダ新設は後回しとする方針で合意した。

---

## 3. 検討した案

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. 毎回必須のルール化|動作確認のたびにチェックリスト作成を義務化|抜け漏れを最大化|Tier S/M の速度方針と衝突。個人開発で継続困難|
|B. 条件付き標準化（採用）|Tier に応じて作成。AI 下書き・人間実施|実績に合う。SSOT を汚さない|判断が必要な境界が残る|
|C. ルール化せず試行のまま|ドキュメントに書かない|変更コストなし|セッションをまたぐと運用が曖昧になる|
|D. 動作確認リストを `docs/08_test` に混在|同じフォルダに作業補助とテスト資産を置く|場所が一つ|役割が再び混ざる|

---

## 4. 判断基準

- [`development.md`](../02_rules/development.md) §3.1 の Tier 省略と両立すること
- 仕様の正本は設計書であること（チェックリスト・テストケースを仕様にしない）
- `docs` に置くのはシステム説明・品質説明に足る正式成果物に限ること（[`contents_and_ai_logs_separation.md`](contents_and_ai_logs_separation.md) の考え方）
- `07_decisions` の番号繰り下げを避けること
- 個人開発で継続可能な薄さであること

---

## 5. 判断結果

**案 B（条件付き標準化）を採用**する。テストフォルダ新設は行わず、将来の第一候補を `docs/08_test` とする。

### 5.1 名称と役割

|名称|役割|
|---|---|
|動作確認チェックリスト|その PRG を完了してよいかを判断する作業補助（工程名は「動作確認」）|
|テストケース|仕様を条件・操作・期待結果で再現可能に検証する派生成果物（将来）|
|回帰観点|改修時の既存影響確認用の観点（将来。初期は独立カテゴリ必須ではない）|

### 5.2 動作確認チェックリストの運用

- **作成条件**：Tier **L** で動作確認に入るとき原則作成。Tier **M** は通し観点が増える・意図的保留が多い等のとき条件付き。Tier **S** / **D** は原則省略
- **作成・実施**：AI が設計書・backlog・判断記録・progress 等から下書き。人間がブラウザで実施・判定する
- **正本にしない**：仕様は設計書。チェックリストは作業補助
- **progress**：リンクのみ。本文は置かない（[`docs/README.md`](../README.md) §5、[`progress.md`](../01_project/progress.md) §2）
- **発見の還元**：学び・不具合・方針は progress 要約 / backlog / decisions / rules へ。全部のチェック済み表を長く残す必要はない
- **本体の寿命**：恒久庫にはしない。完了後は削除・しばらく残す・学びが多い回だけ残す、のいずれでもよい（すぐ削除を義務化しない）。EMP002 分は標準化・観点抽出の材料として当面残す
- **置き場**：作成・実施・記入は **`private/verification/`**（開発正本・非公開）。Version 1.0 では公開しない（[`public_offering_strategy.md`](public_offering_strategy.md) §5.2、[`public_prompts_at_repo_root.md`](public_prompts_at_repo_root.md)、[`knowledge_publish_inventory.md`](knowledge_publish_inventory.md)）。依頼文の正本は `private/prompts`、公開代表は [`prompts/`](../../prompts/)
- **公開 docs からの参照**：チェックリスト本体へのクリック可能な Markdown リンクは付けない。progress 等ではパス表記のみとする

### 5.3 テスト資料（将来）

- 正式なテスト方針・再利用可能なテストケースは、最終的に **`docs/08_test`** を第一候補とする
- **`07_decisions` は移動しない**
- 新設は、粒度・手動／自動分担・昇格基準等が見えてから（[`BLG-PRJ-005`](../01_project/backlog.md)）
- テストケースは設計書の派生。不一致時は設計書／decisions を先に直し、テストを追従させる

### 5.4 進め方の順序

1. 本判断および `ai.md` / `development.md` への薄い反映（本決定）
2. 同型の動作確認を継続
3. 再利用観点が溜まったらテスト方針を決め、必要なら `docs/08_test` を新設

---

## 6. 判断理由

- EMP002 での実利用により、条件付き作成・必須／任意／見なくてよい、の型が有効と分かった
- 毎回必須化は Tier 方針と衝突し、継続可能性を損なう
- 動作確認リストをテスト資産と同一フォルダに置くと、再び役割が混ざる
- `08_test` を先に作ると中身の未決（粒度・網羅範囲等）のまま箱だけが増える
- `07` と `08` の入れ替えはリンク破壊コストが大きく、分類番号としては末尾追加で足りる
- 公開／非公開分離後は、作業本体を公開ツリーに置くと「代表のみ公開」と矛盾するため、正本は `private/verification` とし、Version 1.0 では公開しない（[`public_private_repo_topology.md`](public_private_repo_topology.md)、[`public_prompts_at_repo_root.md`](public_prompts_at_repo_root.md)）

---

## 7. 今後の対応

- 動作確認チェックリスト作成時は `private/prompts/verification_checklist_create.md`（非公開・ローカル）を利用してよい。公開代表の依頼文は [`prompts/verification_checklist_create.md`](../../prompts/verification_checklist_create.md)
- 本体の保存先（作業正本）は `private/verification/`（非公開・ローカル）。公開しない
- EMP002 チェックリストから再利用観点を抽出する作業は任意（[`BLG-PRJ-005`](../01_project/backlog.md)）
- 自動テスト化は反復性の高い項目が明確になってから検討する

### 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`docs/02_rules/ai.md`](../02_rules/ai.md)|動作確認時の AI 補助|
|[`docs/02_rules/development.md`](../02_rules/development.md) §3.1|Tier と作成要否|
|[`docs/01_project/progress.md`](../01_project/progress.md)|PRG・パス表記のみ|
|[`public_offering_strategy.md`](public_offering_strategy.md)|公開範囲（verification は非公開）|
|[`knowledge_publish_inventory.md`](knowledge_publish_inventory.md)|公開セット|
|[`public_prompts_at_repo_root.md`](public_prompts_at_repo_root.md)|prompts 直下配置|
|`private/verification/`（非公開・ローカル）|作業正本（全量）|
|非公開のAI協調開発ログ（ローカル）|試行の経緯|
|[`BLG-PRJ-005`](../01_project/backlog.md)|正式なテスト資料の管理方針（未検討）|
|`private/verification/prg_emp_009_crud_checklist.md`（パス表記）|EMP002 通し確認|
