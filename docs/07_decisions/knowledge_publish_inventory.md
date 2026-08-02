# `prompts/` 公開棚卸しと docs リンク方針

**Document Version** : 1.1

**更新日** : 2026/08/02

**ステータス** : 決定（BLG-PRJ-009。2026/07/26 更新：`knowledge/` 廃止・直下 `prompts/` へ。2026/08/02：実例は `prompts/cases/`）

---

## 1. 目的

Version 1.0 公開にあたり、[`public_offering_strategy.md`](public_offering_strategy.md) §5.2 の公開範囲をファイル単位へ落とし込み、公開する `docs` から非公開資料へのリンク切れを防ぐ。

新 Public リポジトリへの公開対象コピーと Git 除外設定は [`PRG-PRJ-002`](../01_project/progress.md) に委ねる。本判断は **何を公開／非公開とするか** と **docs 側の参照の書き方** を正とする。

配置変更の判断経緯は [`public_prompts_at_repo_root.md`](public_prompts_at_repo_root.md) を正とする。

---

## 2. 背景

- 当初は `knowledge/prompts`・`knowledge/verification` を公開代表としていた
- 2026/07/26 に、公開代表は直下 `prompts/` のみとし、`knowledge/` とチェックリスト公開をやめる方針へ更新した
- 公開 `docs` から非公開 `private/` へのクリック可能リンクは付けない

---

## 3. 検討した案

配置案の比較は [`public_prompts_at_repo_root.md`](public_prompts_at_repo_root.md) §3 を正とする。本ファイルは公開セットとリンク方針の棚卸しに限定する。

---

## 4. 判断基準

- [`public_offering_strategy.md`](public_offering_strategy.md) §5.2 と矛盾しないこと
- 公開 docs にクリック可能なリンク切れを残さないこと
- 結論・ルールは `docs` に置き、経緯の深さは非公開に残すこと（[`recording.md`](../02_rules/recording.md)）
- 一人運用で再判定しやすい粒度であること

---

## 5. 判断結果

### 5.1 公開セット（Version 1.0）

|フォルダ|公開|非公開|
|---|---|---|
|`prompts/`（公開用・リポジトリ直下）|テンプレート（`README.md`、学習向け・作業向け各ファイル）と実例（`cases/`。例：Flyway 導入の依頼文2本）|—|
|`private/prompts/`|全量（上記の正本＋`employee_code_review.md` 等）|下書き・実験は公開へ昇格しない。実例の公開先は `prompts/cases/`（[`public_prompts_at_repo_root.md`](public_prompts_at_repo_root.md)）|
|`private/verification/`|全量|**公開しない**（作業正本のみ）|
|`private/ai_logs/`|なし|フォルダ全体|
|`private/contents/`|なし|フォルダ全体|
|`knowledge/`|—|**廃止**（公開代表の入れ物として使わない）|

### 5.2 docs リンク方針

- 公開 `docs` から `private/ai_logs/`・`private/contents/`・`private/verification/`・`private/prompts/` への **Markdown リンクは付けない**（パス表記は可）
- 経緯の所在を残す場合は、個別ファイル名を挙げず、記録種別の用語（[`recording.md`](../02_rules/recording.md) §3）に合わせて一般化する（仕組み説明としての `private/` フォルダ言及は可）
- 公開文書での標準表現：

|記録種別|公開文書での標準表現|
|---|---|
|AI協調開発ログ|非公開のAI協調開発ログ（ローカル）|
|発信・教材化メモ|非公開の発信・教材化メモ（ローカル）|
|制作ワークスペース方針など|非公開の制作ワークスペース方針（ローカル）|
|動作確認チェックリスト本体|`private/verification/`（パス表記。クリック可能なリンクは付けない）|

- `recording.md` のテンプレート列など、公開／非公開の仕組みを説明するパス表記は残してよい
- 公開代表の `prompts/` へのリンクは維持する
- 判断の主題が非公開運用の深さ（教材制作パイプライン等）である場合は、判断記録自体を `private/` に置いてよい（[`contents_workspace_decision_publish_scope.md`](contents_workspace_decision_publish_scope.md)）

### 5.3 PRG-PRJ-002 への引き継ぎ（新 Public へのコピー）

公開方式の正本は [`public_release_new_repo_snapshot.md`](public_release_new_repo_snapshot.md)、フォルダ構成の正本は [`public_private_repo_topology.md`](public_private_repo_topology.md)。

新 Public へコピーする knowledge 相当は **`prompts/`** とする（旧 `knowledge/` はコピーしない）。

---

## 6. 判断理由

- 代表プロンプトは手法デモとして足り、直下配置の方が発見しやすい
- チェックリスト本体は PRG 固有で、公開代表としての再利用価値が限定的
- `knowledge/` は公開物がプロンプトだけなら過剰な入れ物になる

---

## 7. 今後の対応

- 新 Public 作成時は `prompts/` を含め、`knowledge/` は含めない（PRG-PRJ-002）
- 公開 docs 内の旧 `knowledge/` リンクが残っていないかを PRG-PRJ-001／003 で確認する

### 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`public_prompts_at_repo_root.md`](public_prompts_at_repo_root.md)|直下配置と knowledge 廃止の判断|
|[`public_offering_strategy.md`](public_offering_strategy.md)|公開範囲|
|[`public_private_repo_topology.md`](public_private_repo_topology.md)|配置|
|[`prompts/README.md`](../../prompts/README.md)|公開代表プロンプトの索引|
|`private/README.md`（非公開・ローカル）|開発正本|
