# 公開／非公開の配置（Public 正本＋ローカル private）

**Document Version** : 1.1

**更新日** : 2026/08/02

**ステータス** : 決定（2026/07/26 更新。公開代表は直下 `prompts/`。`knowledge/` は廃止。2026/08/02：実例は `prompts/cases/`）

---

## 1. 目的

Version 1.0 公開にあたり、非公開の教材原資を守りつつ、（1）Cursor の単一ワークスペース運用（2）公開利用者への単純な導線（3）公開後の開発正本の一本化、を両立する配置を定める。

公開範囲の内容面は [`public_offering_strategy.md`](public_offering_strategy.md) §5.2・[`knowledge_publish_inventory.md`](knowledge_publish_inventory.md) を正とする。初回公開時のリポジトリと Git 履歴は [`public_release_new_repo_snapshot.md`](public_release_new_repo_snapshot.md) を正とし、本判断は **公開後のリポジトリ内のフォルダ配置と Git 追跡** を正とする。

---

## 2. 背景

- 当初は Public + Private の 2リポ（本ファイル v1.0）を採用候補とした
- その後、開発中は単一リポジトリ内で `knowledge` と `private` を分け、`private` も Git 追跡する構成を採用した（本ファイル v2.0）
- 公開方法の再検討により、現リポジトリは Private アーカイブとして保持し、公開対象だけを新規 Public リポジトリへコピーする方式に変更した（[`public_release_new_repo_snapshot.md`](public_release_new_repo_snapshot.md)）
- 公開後は Public リポジトリをアプリ開発の正本とし、`private` は同じワークスペース内に置くが `.gitignore` で追跡しない

---

## 3. 検討した案

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. Public 1本 + `.gitignore` のみ（追跡しない）|非公開を Git に載せない|公開は単純|開発中の複数PC同期ができない|
|B. Public + Private の 2リポ（旧採用）|深さを別リポへ|公開と資産の分離が明確|Cursor／ワークスペースが複雑。運用負荷|
|C. 単一リポ・フォルダ分離（開発中に採用）|`knowledge/`＝公開用、`private/`＝開発正本。開発中は両方を追跡|Cursor 単一WS。同期容易。将来の別リポ切出しも容易|現リポジトリをそのまま Public 化する場合は履歴掃除が必要|
|D. Submodule|Private を submodule 化|依存明示|一人運用では過剰|

初回公開方式は本比較後に再検討し、新規 Public スナップショット方式を採用した。比較と判断理由は [`public_release_new_repo_snapshot.md`](public_release_new_repo_snapshot.md) を参照する。

---

## 4. 判断基準

- 型は公開・深さは非公開（[`public_offering_strategy.md`](public_offering_strategy.md) §5.2）
- Version 1 の運用単純さ（Cursor・公開利用者）を優先する
- 開発中に蓄積した非公開資料と履歴を失わないこと
- 将来 Private リポや AI Research Institute へ切り出しても困らないこと

---

## 5. 判断結果

**公開後も公開代表と `private`（非公開の正本）の分離を維持する。** 公開代表はリポジトリ直下の **`prompts/`** とする（旧 `knowledge/` は廃止。経緯は [`public_prompts_at_repo_root.md`](public_prompts_at_repo_root.md)）。Public リポジトリをアプリ開発の正本とし、`private` はローカルに配置したうえで `.gitignore` により Git 追跡しない。

初回公開は新規 Public スナップショット方式とし、現行 Private リポジトリの履歴書き換えは行わない（[`public_release_new_repo_snapshot.md`](public_release_new_repo_snapshot.md)）。

### 5.1 ディレクトリ構成

```text
project/
├ src/
├ docs/
├ prompts/                   ← 公開用（テンプレートは直下、実例は cases/）
│   └ cases/
├ private/                   ← 開発用・非公開の正本（日常の蓄積先）
│   ├ README.md
│   ├ prompts/               ← 実験・下書き含む全量
│   ├ verification/          ← 作業中含む全量可（公開しない）
│   ├ ai_logs/
│   └ contents/
└ .gitignore                 ← Public では private/ を追跡対象外にする
```

### 5.2 役割分担

|フォルダ|役割|現行 Private（公開前）|新 Public（公開後）|
|---|---|---|---|
|`prompts/`|公開代表プロンプト。OSS 利用者がすぐ使える依頼文|追跡する|追跡する|
|`private/`|日常開発の正本。生ログ・実験・教材原資・チェックリスト|追跡する（公開前履歴としてアーカイブに保持）|ローカルには配置するが **追跡しない**（`.gitignore`）|

### 5.3 記録・公開の流れ

1. まず `private/` に蓄積する（正本）
2. 公開可能と判断した代表プロンプトだけ、一般化・整理して `prompts/` へコピー／昇格する（記事・題材向けの実例は `prompts/cases/`。方針は [`public_prompts_at_repo_root.md`](public_prompts_at_repo_root.md)）
3. 動作確認チェックリストは `private/verification/` に置き、Version 1.0 では公開しない
4. 初回公開時は `private/` を新 Public リポジトリへコピーしない
5. 公開後は Public のローカルワークスペースに `private/` を置き、`.gitignore` で追跡対象外にする

### 5.4 Cursor

- プロジェクトルートを開けば、公開用・非公開用の両方を参照・記録できる（マルチルート不要）
- 記録係の保存先は `private/ai_logs/`・`private/contents/` とする（[`recording.md`](../02_rules/recording.md)）

### 5.5 2つのリポジトリの関係

- 現行 Private リポジトリは Version 1.0 公開前の履歴アーカイブとし、公開後のアプリ開発は行わない
- 新 Public リポジトリを Version 1.0 以降のアプリ・`docs`・`prompts/` の正本とする
- 2つのリポジトリでアプリを並行開発しないため、旧案 B の二重管理とは異なる
- 複数PCで非公開資料を Git 同期する必要が生じた場合は、`private` 専用 Private リポジトリへの切り出しを再検討する
---

## 6. 判断理由

- 公開代表を `prompts/` に限定すれば、セットアップ後の入口が単純になる
- Public 側でも `private` をローカル配置できるため、Cursor の単一ワークスペース運用を維持できる
- `.gitignore` により Public へ非公開資料を誤ってコミットするリスクを抑えられる
- 公開前の完全な開発履歴は現行 Private アーカイブに残る
- アプリ開発の正本を Public に一本化することで二重管理を避けられる

---

## 7. 今後の対応

- [`public_offering_strategy.md`](public_offering_strategy.md) §5.5、[`knowledge_publish_inventory.md`](knowledge_publish_inventory.md)、[`public_prompts_at_repo_root.md`](public_prompts_at_repo_root.md)、[`recording.md`](../02_rules/recording.md)、[`progress.md`](../01_project/progress.md) を本判断へ整合する
- 新 Public リポジトリの `.gitignore` へ `/private/` を追加する（PRG-PRJ-002）
- 公開後のローカル `private` の複数PC同期は Git 以外の手段を用いる。当該運用が負担になった場合は専用 Private リポジトリを再検討する

### 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`public_offering_strategy.md`](public_offering_strategy.md)|公開範囲・収益方針|
|[`public_release_new_repo_snapshot.md`](public_release_new_repo_snapshot.md)|Version 1.0 のリポジトリ・Git 履歴の公開方式|
|[`public_prompts_at_repo_root.md`](public_prompts_at_repo_root.md)|直下 `prompts/` と knowledge 廃止|
|[`knowledge_publish_inventory.md`](knowledge_publish_inventory.md)|ファイル単位の公開セット|
|[`progress.md`](../01_project/progress.md)|`PRG-PRJ-002`|
|[`recording.md`](../02_rules/recording.md)|記録の保存先|
|[`project.md`](../01_project/project.md) §4|AI Research Institute（Version 3.x）|
