# プロジェクト概要（`project.md`）と README／戦略文書の役割分担

**Document Version** : 1.0

**更新日** : 2026/07/30

**ステータス** : 決定

---

## 1. 目的

公開向け表現レビュー（`PRG-PRJ-001`）の過程で、[`project.md`](../01_project/project.md) に何を残し、何を README・判断記録・作業ボードへ委ねるかを定める。

---

## 2. 背景

[`project.md`](../01_project/project.md) は目的・コンセプト・完成条件・特徴・対象者・使用技術・構成・ロードマップ・将来展開を一文書に抱えていた。公開前の初見導線としては次の問題があった。

- 「目的」節に公開・収益化の優先順位や教材温存の説明があり、内部メモ感が強い
- 「完成目標」と「完成条件」が見出し上ほぼ同義に読め、ロードマップの Version 1.0 と重複する
- 「特徴」「対象者」はルート [`README.md`](../../README.md) の方が具体で、転送または重複になる
- 「プロジェクト構成」は抽象的で、トップレベルフォルダ一覧としては README 向き
- 「将来的な展開」はロードマップと公開・提供方針の言い直しが多い

表現レビューで節ごとに削る／寄せる判断が続いたため、役割分担を判断記録として残す。

---

## 3. 検討した案

### 3.1 `project.md` の厚さ

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. 厚い概要を維持|完成目標・完成条件・特徴・構成・将来展開を残す|単独で完結|重複・見出しの類似・内部メモ感|
|B. 薄い概要＋正本へ委譲|目的・コンセプト・技術・ロードマップ・関連リンクに絞る|初見と運用の導線がはっきりする|詳細は1ホップ必要|
|C. `project.md` を廃し README のみ|概要をルートに集約|入口が一つ|docs 内の Version ロードマップ正本が消える|

### 3.2 個別テーマの置き場所（採用結果の要約）

|テーマ|置き場所|却下／薄くした案|
|---|---|---|
|公開・収益化の優先順位|[`public_offering_strategy.md`](public_offering_strategy.md)|`project.md` §1 に置く|
|特徴・対象者|[`README.md`](../../README.md)|`project.md` に同趣旨を残す／転送節だけ残す|
|Version 1.0 の機能・公開条件|[`project.md`](../01_project/project.md) §4 Version 1.0、詳細は [`progress.md`](../01_project/progress.md) §6・[`version1_publish_scope.md`](version1_publish_scope.md)|独立した「完成目標」「完成条件」章|
|リポジトリのトップレベル構成|[`README.md`](../../README.md)「リポジトリ構成」|`project.md` の抽象的な三本柱|
|アプリ内の配置ルール|[`directory.md`](../02_rules/directory.md)、索引は [`docs/README.md`](../README.md) §4|概要に詳細表を置く|
|公開形態の要約（OSS／一部非公開／直販しない）|[`project.md`](../01_project/project.md) §4 Version 1.0 に一文|独立した「将来的な展開」章、または概要から完全削除|

---

## 4. 判断基準

- 初見（GitHub）向けの具体案内は README を正とする
- Version 単位の中長期ロードマップの正本は `project.md` に残す
- 同じ説明を複数文書に置かない（SSOT）
- 「目的」にはなぜ作るかだけを書き、どう公開・収益化するかは戦略文書へ
- 公開範囲の境界（一部非公開）は概要から完全に消さず、短い要約を残す

---

## 5. 判断結果

**案 B を採用**する。

[`project.md`](../01_project/project.md) の構成は次を正とする。

1. プロジェクトの目的
2. コンセプト
3. 使用技術（薄い表）
4. 今後の開発予定（ロードマップ。Version 1.0 に公開形態の要約を含む）
5. 関連ドキュメント

ルート README に「リポジトリ構成」表を置き、`docs/README.md` §4 に「ディレクトリ構成」行を置く。

---

## 6. 判断理由

- 概要は「何のプロジェクトか・どこへ向かうか」に絞ると、一般読者と AI の双方が読みやすい
- 完成目標と完成条件をロードマップの Version 1.0 に寄せると、見出しの類似と三重記述が解消する
- 特徴・対象者・フォルダ一覧は入口（README）の方が役割に合う
- 公開形態の一文は、戦略文書へ完全委譲すると「全部公開」と誤読しやすいため、Version 1.0 に要約を残す
- `project.md` 廃止（案 C）は、docs 内のロードマップ正本を失うため不採用

---

## 7. 今後の対応

- 表現レビュー（A3）では、本判断後の `project.md` を前提に読みやすさを確認する
- 公開形態の要約を変えるときは [`public_offering_strategy.md`](public_offering_strategy.md) を先に更新し、`project.md` §4 の一文を追随する
- README「リポジトリ構成」に載せない補助フォルダ（例：`assets/`）は、主要柱をぼかさない限り表に入れない

### 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`docs/01_project/project.md`](../01_project/project.md)|プロジェクト概要（本判断後の構成）|
|[`README.md`](../../README.md)|入口。特徴・対象者・リポジトリ構成|
|[`docs/README.md`](../README.md)|開発ドキュメント索引|
|[`docs/07_decisions/public_offering_strategy.md`](public_offering_strategy.md)|公開・提供方針|
|[`docs/07_decisions/version1_publish_scope.md`](version1_publish_scope.md)|Version 1.0 公開スコープ|
|[`docs/07_decisions/readme_docs_role_split.md`](readme_docs_role_split.md)|ルート README と docs/README の役割|
|[`docs/07_decisions/info_placement_matrix_in_docs_readme.md`](info_placement_matrix_in_docs_readme.md)|情報の置き場所マトリクス|
|`private/verification/prg_prj_001_editorial_review_checklist.md`|公開向け表現レビュー（A／B）|
