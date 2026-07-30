# 進捗管理ドキュメント（`docs/01_project/progress.md`）の新設

**Document Version** : 1.0

**更新日** : 2026/07/30

**ステータス** : 決定（レビュー管理の分離は [`review_into_progress_merge.md`](review_into_progress_merge.md) により再定義）

> **追記（2026/07/23）**：レビュー予定・履歴はいったん [`progress.md`](../01_project/progress.md) へ統合したのち、[`progress_prg_centric_work_management.md`](progress_prg_centric_work_management.md) により **PRG 中心の作業管理** へ再編し、REV-ID を廃止した。本判断のうち「作業の現在地を progress で管理する」方針は維持。

> **追記（2026/07/29）**：表示名を「開発進捗」から「進捗管理」へ変更した（ファイル名 `progress.md` は維持）。

> **追記（2026/07/29）**：`screens` / `features` / `tables` の「状態」列を廃止し、進捗は progress のみとした。一覧は実装することが決まった時点で掲載する。詳細は [`system_overview_lists_without_status.md`](system_overview_lists_without_status.md)（[`BLG-PRJ-003`](../01_project/backlog.md) / [`BLG-PRJ-014`](../01_project/backlog.md) 完了）。

---

## 1. 目的

作業の現在地（完了・実装中・次タスク）を、開発ドキュメントのどこで管理するかを決め、SSOT の観点で既存ドキュメント（backlog・review・画面一覧等）との役割分担を明確にする。

---

## 2. 背景

社員一覧（EMP001）の検索・一覧 GET 実装および関連レビューが一段落したが、次に EMP001 削除 POST を進めるか EMP002 設計へ移るか、画面単位で何が完了しているかが、複数ドキュメントを横断しないと把握しづらくなった。

既存ドキュメントの役割は次のとおりであり、**「今どこまで・次に何を」** を担う層が不足していた。

- 当時の `review.md` — レビューセッションのイベント履歴（最新状態の台帳ではない。のち progress へ統合）
- [`backlog.md`](../01_project/backlog.md) — 未決定・保留の設計判断
- [`screens.md`](../03_system/screens.md) — 画面の粗いライフサイクル状態（設計中 / 実装済 等）
- [`project.md`](../01_project/project.md) §4 — Version 単位の中長期ロードマップ

AI 協調開発では、セッション再開時に現在地を短時間で共有する必要があり（[`ai.md`](../02_rules/ai.md) §4.2）、人間が更新する「朝会ボード」相当のドキュメントが有効と判断した（背景メモは非公開の発信・教材化メモ（ローカル）で管理）。

---

## 3. 検討した案

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. `screens.md` / `features.md` を細分化|画面一覧の状態列を細かくする（例：GET 完了 / POST 未）|新ファイル不要|システム俯瞰層（`03_system`）と作業管理の更新頻度が異なり、一覧が肥大化しやすい|
|B. `review.md` に実装進捗を追加|レビュー予定と実装予定を同居|1ファイルで見える|review はイベント履歴が正本。実装軸とレビュー軸が混在する|
|C. `docs/01_project/progress.md` を新設|作業の現在地ダッシュボードを単一ファイルで管理|backlog / review と同じ運用層。AI 引き継ぎ起点にしやすい|更新忘れで陳腐化するリスク。screens との二重管理に注意が必要|
|D. `08_progress/` カテゴリ新設|独立カテゴリで管理|将来の分割に備えやすい|現規模では番号追加・リンク修正コストが高い|

---

## 4. 判断基準

- SSOT — 同じ情報を複数ドキュメントに書かないこと
- 役割の明確さ — 未決定事項・レビュー履歴・システム俯瞰と混同しないこと
- AI セッション再開時に、1 ドキュメントで現在地を把握できること
- 現プロジェクト規模でのコスト対効果
- 既存の `docs` 番号体系（`01_project` に backlog / review が置かれている）との整合

---

## 5. 判断結果

次を採用する。

- **作業の現在地の正本**として [`docs/01_project/progress.md`](../01_project/progress.md) を新設する
- **配置**は `docs/01_project/progress.md` の **単一ファイル** とする（案 C）
- 作業単位は **スライス**（例：EMP001 削除 POST）で管理し、クラス単位のチェックリスト化はしない
- ID は `PRG-{プレフィックス}-{連番3桁}` とする
- [`screens.md`](../03_system/screens.md) / [`features.md`](../03_system/features.md) は **画面・機能が一通り完成したマイルストーン時** に状態を更新する。日々の細かい進捗は progress で管理する
- 独立カテゴリ `08_progress/` の新設は **見送る**（案 D は採用しない）

（追記 2026/07/29）上記の「状態を更新する」前提は、のちに一覧から「状態」列を廃止する方針へ更新した。詳細は [`system_overview_lists_without_status.md`](system_overview_lists_without_status.md)。

---

## 6. 判断理由

- 「未決定」（backlog）「レビューした」（review）「システムに何がある」（screens）とは、更新頻度・粒度・時間軸が異なる **第四の情報** として「今何をしているか」が必要
- `03_system` は索引・俯瞰を主とする層（[`system_overview_folder.md`](system_overview_folder.md)）であり、日次更新の progress と混在させない方がよい
- `01_project` は backlog・review と同じ **プロジェクト参入・運用層** であり、配置の一貫性が高い
- backlog 新設時（[`backlog_document_placement.md`](backlog_document_placement.md)）と同様、現規模では単一ファイルが運用負荷と効果のバランスがよい

---

## 7. 今後の対応

- 作業スライスが一区切りついたら [`progress.md`](../01_project/progress.md) を更新する（[`ai.md`](../02_rules/ai.md) §7 ステップ 8 に含める）
- 新しい保留事項は引き続き [`backlog.md`](../01_project/backlog.md) へ。progress からはリンクのみ
- レビュー完了は [`progress.md`](../01_project/progress.md) §6 へ。progress の PRG 状態を「完了」に更新（※ 旧 `review.md`。統合後は同一ファイル）
- §5.2 の完了行が読みにくくなった場合、またはファイルが 200 行を超えた場合は `01_project/progress/` への分割を検討する
- Version 1.0 完成後は、§6 マイルストーン対応表を要約し、Version 1.1 用に §4・§5.1 をリセットする運用を検討する

### 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`docs/01_project/progress.md`](../01_project/progress.md)|進捗管理（作業管理の正本）|
|[`docs/07_decisions/system_overview_lists_without_status.md`](system_overview_lists_without_status.md)|一覧の状態列廃止・掲載タイミング|
|[`docs/01_project/backlog.md`](../01_project/backlog.md)|バックログ|
|[`docs/07_decisions/review_into_progress_merge.md`](review_into_progress_merge.md)|review 統合・廃止|
|[`docs/07_decisions/progress_prg_centric_work_management.md`](progress_prg_centric_work_management.md)|PRG 中心再編・REV 廃止|
|[`docs/README.md`](../README.md)|開発ドキュメント索引|
|[`docs/02_rules/ai.md`](../02_rules/ai.md)|AI 協調開発・セッション再開|
|[`docs/07_decisions/backlog_document_placement.md`](backlog_document_placement.md)|バックログ配置の判断記録|
