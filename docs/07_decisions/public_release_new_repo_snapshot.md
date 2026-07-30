# Version 1.0 公開方式（新規 Public リポジトリへのスナップショット）

**Document Version** : 1.0

**更新日** : 2026/07/30

**ステータス** : 決定

---

## 1. 目的

Version 1.0 を GitHub へ公開する際の **リポジトリと Git 履歴の扱い** を定める。あわせて、公開後の開発正本（SSOT）と、非公開資料（`private`）の運用先を明確にする。

公開範囲の内容面は [`public_offering_strategy.md`](public_offering_strategy.md) §5.2 および [`knowledge_publish_inventory.md`](knowledge_publish_inventory.md) を正とする。本判断は **どのリポジトリへ、どの履歴で公開するか** を正とする。

---

## 2. 背景

[`public_private_repo_topology.md`](public_private_repo_topology.md) v2.0 では、単一リポジトリのフォルダ分離を採用し、公開時は `private/` を `.gitignore` したうえで **公開用履歴からも除外する** 方針としていた。この前提では、`git filter-repo` 等による履歴書き換えと force push が必要になる。

一方、公開準備（[`PRG-PRJ-002`](../01_project/progress.md)）の着手前に次の点を再検討した。

- 現リポジトリは公開実績がなく、URL・Star・Issue を維持する必要がない
- 正式名称の確定（[`project_name_candidates.md`](project_name_candidates.md)）に伴い、リポジトリ名自体も変更する見込みである
- 履歴書き換えは不可逆であり、除去漏れの確認コストと運用リスクが一人開発では大きい
- 開発履歴（試行錯誤）は資産だが、**公開する必要はない**（教材原資は非公開が方針）

以上より、Version 1 では履歴を書き換えるメリットよりも、運用リスクと作業コストが上回ると判断し、公開方式を再検討した。

---

## 3. 検討した案

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. 現リポジトリを Public 化し、`git filter-repo` で履歴から `private/` を除去（旧方針）|単一リポジトリを維持|履歴・URL が継続する。開発経緯を公開履歴で辿れる|履歴書き換えと force push が必要。除去漏れのリスク。全クローンの再取得が必要|
|B. 新規 Public リポジトリへ公開対象のみをコピー（採用）|Public は Version 1.0 起点の新規履歴。現リポジトリは公開前アーカイブとして Private 保持|履歴書き換え・force push が不要。除去漏れが原理的に起きにくい。公開履歴が明快|公開前の開発履歴は公開されない。リポジトリが 2 つになる|
|C. `.gitignore` のみを追加して現リポジトリを Public 化|作業が最小|過去コミットに `private/` が残り公開漏れとなる。**採用不可**|
|D. 同一リポジトリ内に orphan ブランチで公開履歴を作る|リポジトリは 1 つ|同一リポジトリに非公開履歴が残るため Public 化できない。実質成立しない|

---

## 4. 判断基準

- 非公開資料（教材原資）が公開されないことを最優先とする
- Version 1 では不可逆・破壊的な Git 操作（履歴書き換え・force push）を避ける
- 一人運用で手順を再現・検証できること
- 公開利用者にとって構成と履歴が分かりやすいこと（[`public_quality_walkthrough_gate.md`](public_quality_walkthrough_gate.md)）
- 公開後の開発正本が一本化され、二重管理にならないこと
- 開発過程の記録を失わないこと（[`recording.md`](../02_rules/recording.md)）

---

## 5. 判断結果

**案 B を採用する。** Version 1.0 は新規 Public リポジトリを作成し、公開対象のファイルのみをコピーして初回コミットとする。既存リポジトリの履歴書き換え（`git filter-repo` 等）および force push は行わない。

### 5.1 リポジトリの役割

|リポジトリ|役割|可視性|Git 履歴|
|---|---|---|---|
|現行リポジトリ|Version 1.0 公開前の開発履歴アーカイブ|Private のまま|保持する。公開後はアプリ開発を行わない|
|新 Public リポジトリ|Version 1.0 以降のアプリ・`docs`・`prompts/` の **正本（SSOT）**|Public|Version 1.0 起点の新規履歴|

公開後、日常の開発・コミット・リリースはすべて Public リポジトリで行う。現行リポジトリは参照専用のアーカイブとし、誤って改修を加えないよう README 冒頭へその旨を明記する。

### 5.2 公開対象と除外

公開セットのファイル単位の内訳は [`knowledge_publish_inventory.md`](knowledge_publish_inventory.md) §5.1 を正とする。

|区分|対象|
|---|---|
|公開する|`src`、`docs`、`prompts/`、`README.md`、`LICENSE`、`pom.xml`、Maven Wrapper（`mvnw`・`mvnw.cmd`・`.mvn`）、`.gitignore`、`.cursor/rules`|
|公開しない|`private` 全体、ビルド成果物（`target` 等）、IDE の個人設定、秘密情報・個人環境の痕跡|

### 5.3 移行手順（概要）

実施手順の詳細と実行は [`PRG-PRJ-002`](../01_project/progress.md) で扱う。

1. 現リポジトリで公開体裁を整える（正式名称・`LICENSE`・README・setup・Maven／パッケージ・`docs` 整合）
2. 空の Public リポジトリを作成する
3. 公開対象のみを新しい作業ディレクトリへコピーする（`.git` はコピーしない）
4. `git init` し、Version 1.0 として初回コミットする
5. 公開漏れを確認する（`private` 由来のパス、秘密情報、非公開資料への Markdown リンク）
6. Public リポジトリへ push する
7. [`setup.md`](../01_project/setup.md) の `git clone` 例を、確定した Public のリポジトリ URL（または分かりやすい例）へ差し替える（表現レビュー時点では `<Repository URL>` プレースホルダのまま）
8. [`PRG-PRJ-003`](../01_project/progress.md) の初見ウォークスルーを Public リポジトリ側で実施する
9. ローカルの作業対象を Public リポジトリのクローンへ切り替える
10. 現リポジトリをアーカイブとして扱う

### 5.4 公開後の `private` 運用

- `private` は **Public リポジトリ内へ配置し、`.gitignore` で追跡しない**（ローカル専用）
- 記録種別ごとの保存先は変更しない（[`recording.md`](../02_rules/recording.md)）
- 公開可能と判断した代表プロンプトだけ `prompts/` へ昇格コピーする流れとする（[`public_prompts_at_repo_root.md`](public_prompts_at_repo_root.md)）
- Git 追跡しないため、複数PC間の同期は Git 以外の手段による

複数PCで非公開資料を Git 同期する必要が生じた場合は、`private` を別の Private リポジトリへ切り出す構成を再検討する（§7）。

### 5.5 旧方針との関係

- [`public_private_repo_topology.md`](public_private_repo_topology.md) v2.0 のうち、**公開時の「履歴からも除外」は本判断で置き換える**
- 公開代表（`prompts/`）と `private`（開発正本）を分けるフォルダ構成、および Cursor の単一ワークスペース運用は維持する（旧 `knowledge/` は廃止）
- リポジトリは結果として 2 つになるが、アプリ開発を並行させないため、旧案「Public + Private の 2リポ」（同 §3 案 B）とは異なる

---

## 6. 判断理由

- `.gitignore` だけでは過去コミットに残った内容が消えないため、案 C は成立しない
- 案 A は目的を達成できるが、履歴書き換えと force push を伴い、除去漏れの確認まで含めると一人運用でのリスクが大きい
- 現リポジトリは公開実績がなく、リポジトリ名も変更する見込みであるため、履歴・URL を継続する利点が小さい
- 「含めるものだけを載せる」方式は、非公開資料の混入が原理的に起きにくく、公開品質ゲート（[`public_quality_walkthrough_gate.md`](public_quality_walkthrough_gate.md)）とも整合する
- 公開履歴が Version 1.0 起点であることは、リファレンスとしての読みやすさに寄与する
- 開発履歴は Private アーカイブに完全な形で残るため、経緯の追跡性は失われない
- 公開後の正本を Public へ一本化することで、アプリケーションの二重管理を避けられる

---

## 7. 今後の対応

- [`PRG-PRJ-002`](../01_project/progress.md) の作業内容を、「`private` の ignore・履歴掃除」から「公開対象の確定・新 Public リポジトリ作成・開発正本の切替」へ更新する
- [`public_private_repo_topology.md`](public_private_repo_topology.md)、[`public_offering_strategy.md`](public_offering_strategy.md) §5.5、[`knowledge_publish_inventory.md`](knowledge_publish_inventory.md) §5.3、[`public_prompts_at_repo_root.md`](public_prompts_at_repo_root.md) を本判断へ整合する
- 公開される資料から `private` への Markdown リンクは、公開後にリンク切れとなるため付けない（パス表記は可）
- 公開手順のチェックリストを独立させるか [`PRG-PRJ-003`](../01_project/progress.md) のウォークスルーへ統合するかは、PRG-PRJ-002 で判断する
- 複数PCで非公開資料を同期する必要が生じた場合、`private` の別 Private リポジトリ切り出しを再検討する
- Version 3.x で教材・隣接課金へ展開する際は、本判断とあわせて構成を見直す

### 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`docs/07_decisions/public_offering_strategy.md`](public_offering_strategy.md)|公開・提供方針（公開範囲の上流判断）|
|[`docs/07_decisions/public_private_repo_topology.md`](public_private_repo_topology.md)|公開代表／`private` のフォルダ分離|
|[`docs/07_decisions/knowledge_publish_inventory.md`](knowledge_publish_inventory.md)|公開セット（ファイル単位）|
|[`docs/07_decisions/public_prompts_at_repo_root.md`](public_prompts_at_repo_root.md)|直下 `prompts/` と knowledge 廃止|
|[`docs/07_decisions/version1_publish_scope.md`](version1_publish_scope.md)|Version 1.0 公開スコープ|
|[`docs/07_decisions/public_quality_walkthrough_gate.md`](public_quality_walkthrough_gate.md)|公開品質ウォークスルー（最終ゲート）|
|[`docs/07_decisions/license_selection.md`](license_selection.md)|ライセンス（Apache-2.0）|
|[`docs/07_decisions/java_package_and_maven_coordinates.md`](java_package_and_maven_coordinates.md)|Maven 座標・パッケージ（公開前に反映）|
|[`docs/02_rules/recording.md`](../02_rules/recording.md)|記録種別と保存先|
|[`docs/01_project/progress.md`](../01_project/progress.md)|`PRG-PRJ-002`・`PRG-PRJ-003`|
