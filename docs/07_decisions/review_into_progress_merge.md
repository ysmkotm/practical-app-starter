# レビュー記録の `progress.md` 統合と `review.md` 廃止

**Document Version** : 1.0

**更新日** : 2026/07/30

**ステータス** : 決定（レビュー章・REV-ID は [`progress_prg_centric_work_management.md`](progress_prg_centric_work_management.md) により再定義）

> **追記（2026/07/23）**：progress への統合後、さらに PRG 中心の作業管理へ再編し、REV-ID・レビュー専用章を廃止した。

---

## 1. 目的

作業の現在地（progress）とレビュー予定・履歴（review）の役割重複を解消し、ドキュメント数を減らしたうえで、長期運用しやすい SSOT を定める。

---

## 2. 背景

[`progress.md`](../01_project/progress.md) 新設時（[`progress_document_placement.md`](progress_document_placement.md)）は、「未決定」（backlog）「レビューした」（review）「今何をしているか」（progress）を分離した。

その後、progress §4（現在のフォーカス・直近の完了・次のタスク）が REV-ID を中心に記載されるようになり、当時の `review.md` §4.1（レビュー予定）とライブ情報が二重管理になった。再開時も progress と review の両方参照が必要になり、更新漏れ・食い違いのリスクが増えた。

本質的に分かれていたのは **完了済み REV の詳細索引** であり、「次に何をするか」を別ファイルで持つ必然は薄くなっていた。

---

## 3. 検討した案

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. progress へ統合し review を廃止|PRG・REV・現在地を1ファイルに|ドキュメント削減、再開起点が1つ、二重更新解消|progress 肥大化のリスク|
|B. review は完了履歴のみに縮小|予定は progress、履歴は review|ライブ情報の SSOT は明確|ファイル数は減らない|
|C. 現状維持＋記載規律|progress から REV 詳細を削る|理論上の役割分離を維持|再開時2ファイル必須のまま。実態と逆行|

---

## 4. 判断基準

- SSOT — 同じライブ情報を複数ドキュメントに書かないこと
- ドキュメント数の削減と長期運用のしやすさ
- AI セッション再開時に、1 ドキュメントで現在地を把握できること
- REV 履歴の追跡可能性は維持すること
- 肥大化時の逃げ道（Version 要約・分割検討）を残すこと

---

## 5. 判断結果

**案 A を採用**する。

- [`docs/01_project/progress.md`](../01_project/progress.md) を **作業の現在地 + レビュー予定/履歴** の正本とする
- 専用の `review.md` は **廃止**する
- REV-ID の採番・履歴運用・「学びがあるときのみ記録」の基準は progress 内へ移す
- 完了履歴が過大になった場合は、Version 単位の要約または分割を検討する（最初から別ファイルは設けない）

---

## 6. 判断理由

- 重複の本体はライブ情報（フォーカス・次タスク・レビュー予定）であり、これを1ファイルにすると SSOT が最も明確になる
- progress 新設時に却下したのは「review に実装進捗を足す」案であり、今回は「progress にレビュー索引を足す」逆方向の再設計である。再開起点を progress に寄せる方針とは整合する
- REV 履歴の価値は progress の章として維持できる。記録基準（問題なしのみは省略可）を徹底すれば肥大化を抑えられる
- 案 B は二重更新は解消するが、ドキュメント数削減の目的に届かない

---

## 7. 今後の対応

- [`progress.md`](../01_project/progress.md) へ REV 予定・履歴を移し、`review.md` を削除する
- [`docs/README.md`](../README.md)、[`ai.md`](../02_rules/ai.md)、[`development.md`](../02_rules/development.md)、[`backlog.md`](../01_project/backlog.md) 等の参照を更新する
- 過去の判断記録・ai_logs 内の `review.md` リンクは、運用上参照される箇所を `progress.md` へ張り替える（履歴文書の文脈説明は必要最小限の注記にとどめる）
- Version 完了時や progress が過大になったときは、§6.2 の要約・分割を再検討する

### 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`docs/01_project/progress.md`](../01_project/progress.md)|統合後の正本|
|[`docs/07_decisions/progress_document_placement.md`](progress_document_placement.md)|progress 新設時の判断（本判断で一部再定義）|
|[`docs/07_decisions/development_activity_layers.md`](development_activity_layers.md)|レビュー記録基準（progress へ移管）|
|[`docs/01_project/backlog.md`](../01_project/backlog.md)|未決定事項|
|[`docs/README.md`](../README.md)|開発ドキュメント索引|
