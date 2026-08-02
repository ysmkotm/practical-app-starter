# 公開代表プロンプトの直下配置と `knowledge/` 廃止

**Document Version** : 1.1

**更新日** : 2026/08/02

**ステータス** : 決定（2026/08/02：実例は `prompts/cases/` へ）

---

## 1. 目的

Version 1.0 の公開面を単純化し、セットアップ後にすぐ使える代表プロンプトだけを分かりやすい場所へ置く。あわせて、公開代表の入れ物としての `knowledge/` を廃止する。

---

## 2. 背景

- これまで公開代表は `knowledge/prompts`・`knowledge/verification` に置いていた（[`public_private_repo_topology.md`](public_private_repo_topology.md)、[`knowledge_publish_inventory.md`](knowledge_publish_inventory.md)）
- README 表現レビューの過程で、動作確認チェックリストは特徴として弱く、公開も必須ではないと判断した
- 代表プロンプトは開発継続の入口として有用であり、`knowledge/` 配下よりリポジトリ直下の方が見つけやすい
- `knowledge/` はブログ／販売用ではなく公開代表の入れ物だったが、公開物がプロンプトだけなら入れ物自体が不要になる
- ブログ・販売原資はもともと `private/contents/` で足りる

---

## 3. 検討した案

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. `knowledge/` を維持し中身だけ削減|変更が小さい|導線が残る|公開物がプロンプトだけなのに入れ物が過剰|
|B. 公開代表プロンプトを直下 `prompts/` にし `knowledge/` を廃止|発見しやすい。公開面が単純|既存リンク・判断記録の更新が広い|
|C. プロンプトも非公開にする|公開面が最小|セットアップ後の再利用導線が弱くなる|

---

## 4. 判断基準

- Version 1.0 の公開面を単純に保つこと
- セットアップ後に開発を進めやすい入口があること
- 型は公開・深さは非公開（[`public_offering_strategy.md`](public_offering_strategy.md) §5.2）を維持すること
- ブログ／販売原資は `private/contents/` に閉じること

---

## 5. 判断結果

**案 B を採用**する。

1. 公開代表プロンプトはリポジトリ直下の **`prompts/`** に置く（正本は引き続き `private/prompts/`）
2. **`knowledge/` は廃止**する（公開代表の入れ物として使わない）
3. **動作確認チェックリスト本体は公開しない**（正本は `private/verification/` のみ。公開 docs からはパス表記）
4. チェックリスト作成用の依頼文は、代表プロンプトとして `prompts/` に残してよい
5. **汎用テンプレート**は `prompts/` 直下、**記事・題材向けの実例**（実際に使った依頼文のスナップショット）は **`prompts/cases/`** に置く。既存テンプレートのパスは動かさない

### 5.1 公開後の想定構成（抜粋）

```text
project/
├ prompts/                 ← 公開代表（テンプレートは直下）
│   └ cases/               ← 記事・題材向け実例
├ docs/
├ src/
├ private/                 ← 非公開の正本（Public では .gitignore）
│   ├ prompts/
│   ├ verification/
│   ├ ai_logs/
│   └ contents/
```

---

## 6. 判断理由

- 公開したいものがプロンプトだけなら、`knowledge/` という中間フォルダは説明コストになる
- 直下の `prompts/` は「セットアップ後に開く場所」として直感的
- チェックリストは案件・PRG 固有で、公開代表としての再利用価値がプロンプトより低い
- 販売・教材は `private/contents/` で足り、公開側に knowledge ブランドは不要
- 実例は汎用テンプレートと性質が異なるため、直下を汚さず `cases/` に1段だけ掘る（既存テンプレートのリンクは維持）

---

## 7. 今後の対応

- [`public_private_repo_topology.md`](public_private_repo_topology.md)、[`public_offering_strategy.md`](public_offering_strategy.md)、[`knowledge_publish_inventory.md`](knowledge_publish_inventory.md)、[`verification_checklist_and_test_assets.md`](verification_checklist_and_test_assets.md) を本判断へ整合する
- README・`docs`・`private` 内の `knowledge/` 参照を `prompts/` または `private/verification`（パス表記）へ更新する
- `knowledge/` ディレクトリを削除する

### 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`public_private_repo_topology.md`](public_private_repo_topology.md)|公開／非公開の配置|
|[`public_offering_strategy.md`](public_offering_strategy.md)|公開範囲|
|[`knowledge_publish_inventory.md`](knowledge_publish_inventory.md)|公開セット（本判断後は prompts 中心）|
|[`verification_checklist_and_test_assets.md`](verification_checklist_and_test_assets.md)|チェックリスト運用|
|[`progress.md`](../01_project/progress.md)|PRG-PRJ-001／002|
