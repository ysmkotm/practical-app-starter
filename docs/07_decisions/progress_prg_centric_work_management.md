# progress を PRG 中心の作業管理に再編し REV-ID を廃止

**Document Version** : 1.0

**更新日** : 2026/07/30

**ステータス** : 決定

---

## 1. 目的

[`progress.md`](../01_project/progress.md) を「作業管理の正本」として整理し、レビューセッション（REV）を独立管理しない構成にする。

---

## 2. 背景

`review.md` を progress へ統合した直後（[`review_into_progress_merge.md`](review_into_progress_merge.md)）、§5（PRG）と §6（REV）の二重管理が残った。運用を考えると管理したいのはレビュー単位ではなく **作業全体** であり、重視するのは次の3点である。

- 作業の全量が分かること
- 今どこまで進んでいるかが分かること
- 次に何をやるかが分かること

レビューは独立対象ではなく、作業の一工程（実装 → ソースレビュー → 動作確認 → 完了）として扱いたい。

---

## 3. 検討した案

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. PRG 中心・REV 廃止|作業は PRG のみ。レビュー成果は完了 PRG の要約|俯瞰しやすい、二重管理解消、依頼単位が1つ|クラス単位の細かいレビュー履歴は progress から消える|
|B. PRG + REV 併存（現状）|§5 と §6 を維持|細かいセッション履歴が残る|「次に何を」が二重、フォーカスが REV に寄りやすい|
|C. REV を履歴専用に残す|予定だけ PRG、履歴は REV 章|履歴索引は残る|章が残り、長期で肥大化しやすい|

---

## 4. 判断基準

- 「progress だけ見れば今・完了・次が分かる」こと
- ドキュメント数を増やさないこと
- レビュー成果（学び・反映先）は追跡できること
- 長期運用のシンプルさ

---

## 5. 判断結果

**案 A を採用**する。

- progress は **作業（PRG）管理の正本** とする
- レビュー専用章・**REV-ID の新規採番は廃止**する
- 依頼・再開の単位は **PRG-ID**（必要なら作業内容を添える）
- 完了 PRG には、学びがある場合のみ短いレビュー要約（概要・確認内容・修正判断・Decision/Backlog 反映）を付ける
- 過去の細かい REV 履歴（001〜017 等）は PRG 完了要約へ畳み、progress の REV 行としては残さない（詳細は decisions / ai_logs / Git）

状態は次を用いる。

`未着手 → 実装中 → レビュー中 → 動作確認中 → 完了`（および `取消`）

---

## 6. 判断理由

- 管理したい単位が「レビューセッション」ではなく「作業」であることが運用上はっきりした
- REV を残す主目的だった依頼スコープ固定は、PRG-ID で代替できる
- 細かい REV 行は俯瞰を妨げ、完了 PRG の要約＋decisions リンクで十分辿れる
- [`review_into_progress_merge.md`](review_into_progress_merge.md) の「1ファイル化」は維持しつつ、軸を作業に一本化する

---

## 7. 今後の対応

- [`progress.md`](../01_project/progress.md) を PRG 中心構成へ書き換え、§6 REV 章を削除する
- 旧 REV-EMP-020 / 021 を PRG-EMP-009 / 010 へ移す
- [`ai.md`](../02_rules/ai.md)、[`development.md`](../02_rules/development.md)、[`backlog.md`](../01_project/backlog.md)、[`docs/README.md`](../README.md) の REV 参照を更新する
- 過去文書内の REV-ID 表記は履歴として残してよい（新規運用では使わない）

### 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`docs/01_project/progress.md`](../01_project/progress.md)|作業管理の正本|
|[`docs/07_decisions/review_into_progress_merge.md`](review_into_progress_merge.md)|review.md 統合（本判断の前提）|
|[`docs/07_decisions/progress_document_placement.md`](progress_document_placement.md)|progress 新設|
|[`docs/02_rules/ai.md`](../02_rules/ai.md)|セッション再開・依頼単位|
|[`docs/02_rules/development.md`](../02_rules/development.md)|変更規模・記録基準|
