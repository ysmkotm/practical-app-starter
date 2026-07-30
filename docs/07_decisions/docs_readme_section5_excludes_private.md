# docs/README §5（情報の置き場所）には非公開の管理先を載せない

**Document Version** : 1.0

**更新日** : 2026/07/30

**ステータス** : 決定

---

## 1. 目的

公開の開発ドキュメント索引である [`docs/README.md`](../README.md) §5「情報の置き場所」に、非公開（`private/`）の管理先を載せるかどうかを定める。

---

## 2. 背景

[`info_placement_matrix_in_docs_readme.md`](info_placement_matrix_in_docs_readme.md) により、種類ごとの管理先マトリクスの正本は `docs/README.md` §5 に集約した。

その後 §5 には、公開の開発ドキュメント（progress / backlog / decisions 等）に加え、次のような **非公開の管理先行** も載っていた。

- 動作確認チェックリスト → `private/verification/`
- 再利用プロンプト → `private/prompts/`（公開代表は `prompts/`）

また、AI協調開発ログ（`private/ai_logs/`）や教材化候補（`private/contents/`）を §5 に足すべきか、という議論も出た。

一方で `docs/README.md` は Version 1.0 公開時に公開される索引であり、`private/` は公開リポジトリに載せない（[`public_private_repo_topology.md`](public_private_repo_topology.md)）。§5 に非公開正本のパスを並べると次のずれが起きる。

- 公開クローンには `private/` が存在しないため、正本一覧が存在しないパスを指すことになる
- 公開読者には辿れない・使う前提のない管理先が並ぶ
- 非公開の配置正本は既に [`recording.md`](../02_rules/recording.md)、[`public_private_repo_topology.md`](public_private_repo_topology.md)、[`verification_checklist_and_test_assets.md`](verification_checklist_and_test_assets.md)、[`public_prompts_at_repo_root.md`](public_prompts_at_repo_root.md) 等にある

---

## 3. 検討した案

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. §5 から非公開の管理先行を外す|§5 は公開の開発ドキュメントの振り分けに限定する|公開索引の役割が明確。private 配置の正本と二重化しない|非公開の行き先は §5 以外を見る必要がある|
|B. §5 に非公開も含めて一覧する|verification / prompts / ai_logs / contents も行として載せる|開発者・AI が1表で全部見られる|公開索引に辿れないパスが並ぶ。既存の非公開配置判断と重複しやすい|
|C. §5 に「非公開は recording 等へ」とだけ書く|管理先行は載せず、委譲一文のみ残す|境界は明示できる|索引にメタ説明が増える。委譲先は関連判断・ルールで足りる|

---

## 4. 判断基準

- `docs/README.md` は公開される開発ドキュメント索引であること
- 非公開の配置は、既存の公開ルール・判断記録を正とすること（SSOT を増やさない）
- §5 の対象を「公開の開発ドキュメントにおける種類 → 管理先」に限定し、役割をぼかさないこと

---

## 5. 判断結果

- [`docs/README.md`](../README.md) §5 には **非公開（`private/`）の管理先行を載せない**（案 A）
- 対象例：`private/verification/`、`private/prompts/`、`private/ai_logs/`、`private/contents/`
- 非公開の振り分け・配置の正本は既存ドキュメントに委ねる
  - 記録種別・ai_logs / contents → [`recording.md`](../02_rules/recording.md)
  - 公開代表と `private/` の分離 → [`public_private_repo_topology.md`](public_private_repo_topology.md)
  - 動作確認チェックリスト → [`verification_checklist_and_test_assets.md`](verification_checklist_and_test_assets.md)
  - プロンプトの公開代表 → [`public_prompts_at_repo_root.md`](public_prompts_at_repo_root.md)
- §5 に [`recording.md`](../02_rules/recording.md) への行を残すことは妨げない（公開ルールとしての振り分け案内であり、`private/` を管理先列に置く行ではない）
- 案 C の委譲一文は §5 に必須としない

---

## 6. 判断理由

- 公開リポジトリに `private/` は含まれない。§5 に非公開の管理先を書くと、公開後の索引が存在しないパスを正として示すことになる
- 公開索引に非公開パスを並べても、公開後の読者には実用性が薄い
- 非公開の置き場所は既にルール・判断記録側に正本があり、§5 へ足すと同期コストと役割の混在が増える
- [`info_placement_matrix_in_docs_readme.md`](info_placement_matrix_in_docs_readme.md) の趣旨（公開ドキュメント間の振り分けを1か所に集約）とも整合する

---

## 7. 今後の対応

- [`docs/README.md`](../README.md) §5 から、非公開の管理先行（検証・プロンプト等）を削除する
- 「README §5」を非公開配置の根拠として指している箇所があれば、本判断および上記の正本へ参照を見直す（例：[`verification_checklist_and_test_assets.md`](verification_checklist_and_test_assets.md)、[`public_quality_walkthrough_gate.md`](public_quality_walkthrough_gate.md)）
- progress 等の柵でチェックリスト本文を「記載しない」と述べる場合は、管理先として `private/verification/` や個別判断へのリンクで足り、§5 経由に依存しない

### 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`docs/README.md`](../README.md) §5|情報の置き場所（公開ドキュメント間）|
|[`docs/07_decisions/info_placement_matrix_in_docs_readme.md`](info_placement_matrix_in_docs_readme.md)|マトリクスを README §5 に集約した判断|
|[`docs/02_rules/recording.md`](../02_rules/recording.md)|記録種別・公開／非公開の振り分け|
|[`docs/07_decisions/public_private_repo_topology.md`](public_private_repo_topology.md)|公開代表と `private/` の分離|
|[`docs/07_decisions/verification_checklist_and_test_assets.md`](verification_checklist_and_test_assets.md)|動作確認チェックリストの配置|
|[`docs/07_decisions/public_prompts_at_repo_root.md`](public_prompts_at_repo_root.md)|プロンプトの公開代表|
|[`docs/07_decisions/contents_and_ai_logs_separation.md`](contents_and_ai_logs_separation.md)|ai_logs / contents の分離|
