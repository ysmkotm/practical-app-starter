# 開発ドキュメントではない資料（contents・ai_logs）の分離

**Document Version** : 1.0

**更新日** : 2026/07/30

**ステータス** : 決定

---

## 1. 目的

`docs` 配下に置いていた `contents`（販売・教材化候補）と `ai_logs`（AI協調開発ログ）について、「開発ドキュメント」に含めるべきかを判断し、フォルダ構成を決める。

---

## 2. 背景

`docs/09_contents` と `docs/08_ai_logs` は、`docs/07_decisions`（判断記録）と同じ並びの番号付きフォルダとして追加していた。

その後、「`ai_logs` や `contents` を開発ドキュメントと呼ぶのは違和感がある」という気付きがあり、まず `contents` を `docs` から切り出し、ルート直下の `contents/` へ移動した。

`ai_logs` についてはその時点では判断を保留していたが、ChatGPTとの相談を経て、次の観点が明確になった。

- 実務では珍しい存在である
- 他の設計書（画面・DB・API・ルール・判断記録）はないとシステムを作れないが、AI協調開発ログはなくても開発を進められる
- AI協調開発の知見を蓄積する資料であり、システムそのものの説明ではない

この基準に基づき、`ai_logs` も `docs` から切り出すことにした。

---

## 3. 検討した案

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. `docs` に残す|現状維持|変更コストがない|「開発ドキュメント」の定義（プロジェクト概要・開発ルール・設計書・判断記録）と実際の内容がズレたままになる|
|B. `contents/` に統合する|販売・教材化候補と同じ場所にまとめる|フォルダが増えない|`ai_logs` は社内向けの生の知見であり、対外発信用の `contents` とは性質が違う|
|C. ルート直下に新しい `ai_logs/` を作る|`contents/` と並ぶ独立フォルダとする|性質の違う資料を区別できる。将来 `contents` の素材として引用しやすい|フォルダ数が増える|

---

## 4. 判断基準

- その資料がないと、システムを作れない・仕様が成立しないかどうか
- 対外発信（販売・教材化）向けか、社内の知見蓄積向けかを区別できること
- 既存の `contents` 分離の判断と整合すること
- 将来 `ai_logs` の内容を `contents` の素材として引用しやすいこと

---

## 5. 判断結果

**案Cを採用**する。

- `docs` 配下は「プロジェクト概要・開発ルール・設計書・判断記録」など、システムの成立に必要な資料のみを置く
- `ai_logs` と `contents` は、どちらも `docs` の外に置くが、役割は分ける
  - `ai_logs` … AI協調開発の知見を蓄積する生の記録（どう進めたか）
  - `contents` … 発信・教材化メモとしてまとめた資料（何を伝えるか）

> **追記（配置の変遷）**：当初はリポジトリルート直下の `ai_logs/`・`contents/` としたあと、項目数削減のためいったん `knowledge/` 配下へまとめ、現在は非公開正本として **`private/ai_logs/`**・**`private/contents/`** に置いている。役割分担（本章）自体は変更していない。公開代表は [`prompts/`](../../prompts/)、配置方針は [`public_private_repo_topology.md`](public_private_repo_topology.md)。
>
> **追記（公開向け呼称）**：公開ドキュメント上の記録種別名は [`recording.md`](../02_rules/recording.md) に合わせ「発信・教材化メモ」とする（背景・検討案に残る「販売・教材化候補」、途中呼称の「種」は当時の用語。フォルダ `seeds/` は制作側の内部配置）。

---

## 6. 判断理由

- 「システムの成立に必要か」という基準が、`docs`（開発ドキュメント）と他の資料を区別する上で最も分かりやすい
- `ai_logs` は判断記録（`07_decisions`）の根拠として参照されることはあるが、削除してもシステムや既存の判断自体は成立する
- `contents` と同じ場所に統合すると、社内向けの生の知見と対外発信用の素材が混在し、後から整理しにくくなる
- `ai_logs` は将来 `contents` の素材になり得る関係にあるため、独立したフォルダに分けておくと引用しやすい

---

## 7. 今後の対応

- 新しい AI 協調開発ログは `private/ai_logs/` へ作成する
- 発信・教材化メモや原稿は `private/contents/` へ作成する
- 制作フローの詳細（種・piece 等）は非公開の制作ワークスペース方針（ローカル）で管理する（公開 docs には置かない。線引きは [`contents_workspace_decision_publish_scope.md`](contents_workspace_decision_publish_scope.md)）
- `.cursor/rules/recording.mdc` の保存先は更新済み
- 今後、同様に「開発ドキュメントかどうか」の判断が必要になった場合は、本判断記録の基準（§4）を参照する
- 公開時のファイル単位棚卸しは [`knowledge_publish_inventory.md`](knowledge_publish_inventory.md) を正とする

### 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`contents_workspace_decision_publish_scope.md`](contents_workspace_decision_publish_scope.md)|制作まわり判断の公開／非公開線引き|
|[`public_private_repo_topology.md`](public_private_repo_topology.md)|公開代表／`private` 分離|
|[`knowledge_publish_inventory.md`](knowledge_publish_inventory.md)|公開セット|
|[`public_prompts_at_repo_root.md`](public_prompts_at_repo_root.md)|直下 `prompts/` と knowledge 廃止|
|[`recording.md`](../02_rules/recording.md)|記録種別と保存先|
|[`.cursor/rules/recording.mdc`](../../.cursor/rules/recording.mdc)|記録係の行動トリガー|
