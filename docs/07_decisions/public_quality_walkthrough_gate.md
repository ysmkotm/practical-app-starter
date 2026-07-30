# 公開品質ウォークスルー（初見視点）を Version 1.0 最終ゲートとする

**Document Version** : 1.0

**更新日** : 2026/07/30

**ステータス** : 決定

---

## 1. 目的

Version 1.0 公開前に、技術完成度とは別に「GitHub で初めて本リポジトリを見る人」の視点で公開品質を確認する工程の置き場所・必須／推奨区分・実施単位を定める。

---

## 2. 背景

Version 1.0 の作業全量は [`progress.md`](../01_project/progress.md) §6 の必須／推奨／任意で管理している。公開体裁の改修として PRG-PRJ-002（README・setup・名称・ライセンス）と PRG-PRJ-001（docs 公開向け整理）はある一方、[`project.md`](../01_project/project.md) §4（Version 1.0）の公開体裁は抽象的で、第三者視点の確認項目が未分解だった。

技術的な CRUD 通し（PRG-EMP-009）だけでは、「README だけで価値が伝わるか」「セットアップで迷わないか」「公開リポとして WIP に見えないか」が保証されない。一方、改善欲求で公開を遅らせない方針（[`version1_publish_scope.md`](version1_publish_scope.md) 案 C）とも両立させる必要がある。

---

## 3. 検討した案

### 3.1 管理場所

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. backlog に項目追加|未決定・保留として扱う|既存の BLG 運用に乗る|確認ゲートであり未決定事項ではない。役割がずれる|
|B. progress §6 のみ（PRG なし）|公開条件の行だけ増やす|軽い|実施単位・チェックリストとの接続が弱い|
|C. §6 の公開条件 + 最終ゲート PRG 1 本 + knowledge チェックリスト（採用）|条件・実施・手順を分離|PRG-EMP-009 と同型。SSOT を汚さない|PRG が 1 本増える|
|D. 必須項目ごとに PRG を分割|スクショ・About 等を個別 PRG 化|粒度が細かい|V1 の「必須だけ閉じる」思想と衝突しやすい|

### 3.2 改修と検証の単位

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. PRG-PRJ-002 にウォークスルーを内包|PRG が増えない|単純|改修と検証が混ざる。001 完了後の最終確認が曖昧|
|B. PRG-PRJ-003 を最終ゲートとして新設（採用）|001／002＝改修、003＝検証|EMP 実装群のあとに PRG-EMP-009 を置いた型と一致|前提完了の待ちが発生する|

---

## 4. 判断基準

- Version 1.0 は公開可能な最小完成物であり、改善欲求で公開を遅らせないこと（[`version1_publish_scope.md`](version1_publish_scope.md)）
- backlog は未決定・保留、progress は作業の現在地・全量、チェックリスト本文は `private/verification`（作業正本・公開しない）（[`docs/README.md`](../README.md) §5、[`verification_checklist_and_test_assets.md`](verification_checklist_and_test_assets.md)、[`public_prompts_at_repo_root.md`](public_prompts_at_repo_root.md)）
- 「これが無いと公開を後ろにずらすか」で必須／推奨を切ること
- 必須項目ごとに PRG を増やさないこと

---

## 5. 判断結果

**案 C（管理場所）＋案 B（最終ゲート PRG）を採用**する。

|置き場|役割|
|---|---|
|[`progress.md`](../01_project/progress.md) §6.1／§6.2|公開条件としての必須／推奨行|
|PRG-PRJ-001／002|docs・README／setup／名称・ライセンスの**改修**|
|PRG-PRJ-003|公開直前の初見視点ウォークスルー（**検証**・最終ゲート）|
|`private/verification/prg_prj_003_public_walkthrough.md`|チェックリスト本体（非公開。progress にはパス表記のみ）|
|backlog|置かない（未決定事項ではない）|

### 必須（公開ブロッカーの例）

- README で何のプロジェクトか・誰向けかが伝わる
- 完成済み範囲の「予定」「今後決定」表記の解消、LICENSE・正式名称の反映
- README → setup 通し起動、TOP → 社員 CRUD 主要操作への到達
- 秘密情報・個人痕跡の不在、意図的未実装が「後続」と分かること
- README と主要 docs の公開向け矛盾がないこと

### 推奨（無くても公開可）

- スクリーンショット／GIF、GitHub About・Topics、表示確認、当面方針の明記、動作確認の公開向け一言、AI 協調への短いポインタ

### 運用

- 必須がすべて完了したら Version 1.0 公開可
- 推奨残りは公開後に Version 1.x へ落とす
- ウォークスルーで見つかった表記・手順の不備は 001／002 へ戻して直し、観点追加はチェックリストを薄く更新する

---

## 6. 判断理由

- 第三者視点チェックは「公開してよいかの確認工程」であり、backlog（未決定）には載せない方が SSOT の役割分担と一致する
- §6 に条件を置けば「公開条件に入った」ことが見え、実施は PRG 1 本に閉じるため改善項目の PRG 増殖を防げる
- チェックリスト本文を progress に書かない方針は、既存の verification 判断と同じ
- PRG-PRJ-002 に検証まで載せるより、001／002 完了後に初見として通す最終ゲートの方が、PRG-EMP-009 の実績型と揃う
- スクショ等を推奨に置くことで、V1 で公開し V1.x／V2 以降で改善する思想と両立する

---

## 7. 今後の対応

- Version 1.0 公開時は PRG-PRJ-003 の必須判定を完了条件とする
- 公開後、推奨未完了は [`progress.md`](../01_project/progress.md) §6.2 から Version 1.x の通常作業へ移す
- ウォークスルー観点の追加はチェックリストを薄く更新し、必須への昇格は「公開を後ろにずらすか」で都度判断する

### 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`version1_publish_scope.md`](version1_publish_scope.md)|Version 1.0 公開スコープ（案 C）|
|[`progress.md`](../01_project/progress.md)|PRG-PRJ-003・§6 必須／推奨|
|[`project.md`](../01_project/project.md)|完成条件|
|`private/verification/prg_prj_003_public_walkthrough.md`|初見視点チェックリスト|
|[`verification_checklist_and_test_assets.md`](verification_checklist_and_test_assets.md)|チェックリスト運用方針|
|[`docs/README.md`](../README.md) §5|情報の置き場所|
