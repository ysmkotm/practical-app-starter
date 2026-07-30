# Version 1.0 公開スコープとロードマップ区切り

**Document Version** : 1.0

**更新日** : 2026/07/30

**ステータス** : 決定

---

## 1. 目的

Version 1.0 を「いつ公開してよいか」で区切るためのスコープ・優先度・後回し方針を定め、改善欲求が公開を遅らせないようにする。

---

## 2. 背景

社員管理 CRUD・共通レイアウト・TOP・開発ルール・AI 協調の運用基盤は一通り揃いつつある一方、AI 協調標準・共通基盤抽出・テスト体系・UI 本格共通化など「もっと良くできる」案が増え、Version 1.0 の区切りが曖昧になりつつあった。

本プロジェクトは継続改善が前提であり、Version 1.0 は完成の終点ではなく、**公開可能な最小完成物の区切り**とする。

---

## 3. 検討した案

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. CRUD 完了時点で即公開|機能完了を最速で区切る|早い|メッセージ・名称・ライセンス・README が未整備だと商品・公開物として弱い|
|B. 改善案を一通り入れてから公開|品質・標準を最大化|理想形に近い|終わりが見えず公開が遠のく|
|C. 公開ブロッカーのみ V1.0、改善は V1.x / V2 以降|必須だけ閉じる|公開日が見える。継続改善と両立|暫定実装・意図的な未完了が残る|

---

## 4. 判断基準

- 「公開日を前倒しするか／後ろにずらすか」で優先度を決める
- `project.md` §4（Version 1.0）の公開スコープと矛盾しないこと
- 2 画面目が無いと回収できない共通化は Version 1.0 に入れない
- 運用を回した結果でしか決まらない標準（AI フロー標準・テスト体系）は Version 1.0 後
- SSOT：ロードマップは `project.md` §4、作業全量は `progress.md`、未決定は `backlog.md`

---

## 5. 判断結果

**案 C を採用**する。

### Version 1.0（必須）

- 社員 CRUD・入力チェック・成功メッセージ（暫定）
- README → setup の通し、公開前 docs の矛盾解消
- 正式名称（公開名）、ライセンス
- 現行 `ai.md` / prompts で AI 協調が回ること（標準化はしない）
- **公開品質ウォークスルー（GitHub 初見視点）**：README 価値・WIP 表記排除・コールドスタート・docs 導線等。改修は PRG-PRJ-001／002、検証は PRG-PRJ-003（手順は `private/verification/prg_prj_003_public_walkthrough.md`）

### Version 1.0（推奨・公開品質向上）

- 表示確認（致命的崩れなし）
- 社員番号・リモートワーク・削除済みコード再利用などの**当面方針の明記**（BLG-EMP-007／011／013。007／013 の本検討・実装は Version 1.x。011 は明記後は原則維持）
- README スクリーンショット（または短い GIF）、GitHub About・Topics、AI 協調への短いポインタ（無くても公開可）

### Version 1.0（任意・無くても公開可）

- 構造リファクタ（PRG-EMP-010）
- UI fragment 化・DataTables 共通化などの本格共通化

### Version 1.x 以降 / Version 2 以降

- Security / Flyway / Docker / CSV 等 → Version 1.x（[`project.md`](../01_project/project.md) §4）
- 社員番号の採番・形式・文字種の本決め、論理削除済み社員番号・メールの再利用可への変更検討 → Version 1.x（[`BLG-EMP-007`](../01_project/backlog.md)、[`BLG-EMP-013`](../01_project/backlog.md)。リモートワークは当面明記後は原則維持）
- 別 CRUD（部署管理・マスタ管理等）の完成は **Version 1.0 公開条件にしない**。公開後の AI 協調フロー再現性検証は [`BLG-PRJ-011`](../01_project/backlog.md)（Version 1.x〜2.x）
- AI 協調標準・共通基盤・テスト体系・テンプレ化 → Version 2.x
- デモサイト・販売導線・AI 研究所連携の本格化 → Version 3.x

---

## 6. 判断理由

- CRUD はほぼ達成済みで、残作業の多くは「公開体裁」と「完成目標に残るメッセージ表示」である
- 標準化・共通基盤は 1 題材が固まってから抽出した方が資産価値が高い
- backlog / progress に Version 区分を明示することで、セッション再開時に「今やるべきか」を取り違えにくくなる

---

## 7. 今後の対応

- [`project.md`](../01_project/project.md) §4 を本判断に合わせて更新する
- [`progress.md`](../01_project/progress.md) §6 に Version 1.0 作業全量、§7 に Version 2 以降（着手しない一覧）を置く
- [`backlog.md`](../01_project/backlog.md) の検討タイミングを Version 区分で揃える
- 公開直前に PRG-PRJ-003（初見視点ウォークスルー）を最終ゲートとする
- Version 1.0 公開後、本判断の「推奨」残りは Version 1.x の通常作業として消化する
- 公開形態・収益化の優先は [`public_offering_strategy.md`](public_offering_strategy.md) を正とする（本判断は「何を必須にして公開日を切るか」のスコープ）
- 公開後の AI 協調フロー再現性検証（小規模 CRUD）は [`BLG-PRJ-011`](../01_project/backlog.md) を正とする

### 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`project.md`](../01_project/project.md)|完成条件・ロードマップ|
|[`progress.md`](../01_project/progress.md)|Version 1.0 作業全量|
|[`backlog.md`](../01_project/backlog.md)|未決定・保留|
|[`public_offering_strategy.md`](public_offering_strategy.md)|公開・提供方針（OSS／収益化／ポートフォリオ）|
|[`verification_checklist_and_test_assets.md`](verification_checklist_and_test_assets.md)|動作確認とテスト資料の役割|
|`private/verification/prg_prj_003_public_walkthrough.md`|公開品質ウォークスルー（初見視点）チェックリスト（非公開）|
|[`public_quality_walkthrough_gate.md`](public_quality_walkthrough_gate.md)|初見ウォークスルーを最終ゲートとする判断|
|[`BLG-PRJ-011`](../01_project/backlog.md)|公開後の AI 協調フロー再現性検証（小規模 CRUD）|
