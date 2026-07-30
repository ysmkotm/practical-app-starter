# 機能・画面・テーブル一覧から状態列を外し進捗管理に寄せる

**Document Version** : 1.0

**更新日** : 2026/07/30

**ステータス** : 決定

---

## 1. 目的

[`features.md`](../03_system/features.md)・[`screens.md`](../03_system/screens.md)・[`tables.md`](../03_system/tables.md) における「状態」列の要否と、一覧への掲載タイミングを決め、[`progress.md`](../01_project/progress.md)（進捗管理）との役割分担を明確にする。

---

## 2. 背景

一覧系ドキュメントには `予定` / `設計中` / `実装済` / `保留` などの状態列があり、作業の現在地を [`progress.md`](../01_project/progress.md) でも管理していた。その結果、次の課題が顕在化した。

- 一覧の状態と progress の PRG 状態が二重管理になり、ずれる（[`BLG-PRJ-003`](../01_project/backlog.md)）
- スライス単位の進捗（例：GET のみ完了）を一覧の粗い状態では表現しにくい
- `保留` で ID を予約した社員詳細（EMP003）は、将来必須でもなくカタログ上のノイズになっていた（のち廃止。[`employee_list_navigation_pattern.md`](employee_list_navigation_pattern.md)）

一般的なプロジェクトでも、システムの俯瞰（カタログ）と作業進捗（課題管理）は分けることが多い。本プロジェクトでも同様に整理する必要があった。

---

## 3. 検討した案

### 3.1 状態列

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. 状態列を維持・精緻化|中間状態の語彙を増やす、または備考で補う|一覧だけで完成度が分かる|progress との二重管理が残る。更新漏れしやすい|
|B. 状態列を廃止（採用）|一覧はカタログに徹し、進捗は progress のみ|SSOT が明確。腐りにくい|設計済み／未実装の区別を一覧だけでは示せない|

### 3.2 掲載タイミング（状態列廃止後）

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. 認識した時点|アイデア段階から載せる|抜け漏れ防止|検討のみの案が残りやすい|
|B. 実装することが決まった時点（採用）|PRG 作成や Version スコープ投入を目安に載せる|カタログが実装意思と揃う|判断の目安を運用で守る必要がある|
|C. 実装完了後|完成したものだけ載せる|実装済みと誤解しにくい|設計中の俯瞰に使いにくい|

---

## 4. 判断基準

- SSOT — 同じ進捗情報を一覧と progress に書かないこと
- 役割の明確さ — `03_system` は俯瞰、`01_project/progress` は作業管理であること
- カタログの健全性 — 予約・見送り・アイデアだけの行を増やしすぎないこと
- 設計〜実装の一連の作業で、一覧を入口として使えること

---

## 5. 判断結果

次を採用する。

- [`features.md`](../03_system/features.md)・[`screens.md`](../03_system/screens.md)・[`tables.md`](../03_system/tables.md) から **「状態」列と状態定義を廃止**する
- 作業の進捗・完了状態の正本は [`progress.md`](../01_project/progress.md)（進捗管理）とする
- 一覧への掲載は **実装することが決まった時点** とする（設計・実装の完了は待たない）
  - 目安：progress に該当 PRG を切ったとき、または Version スコープに含めたとき
  - 順序：機能一覧 → 画面一覧・テーブル一覧
  - 設計書／定義書が未作成のときはリンク列を `-` とする
- 一覧の関連ドキュメントは同層の俯瞰・設計成果物に寄せ、progress / backlog や個別判断記録は原則載せない（各設計書側へ委譲）

---

## 6. 判断理由

- progress 新設時（[`progress_document_placement.md`](progress_document_placement.md)）に指摘した「screens との二重管理」を、状態列廃止で解消できる
- 一覧に状態を残して精緻化する案は、BLG-PRJ-003 の問題を温存しやすい
- 「認識した時点」は EMP003 のような先送り行を生みやすい。「実装完了後」は設計中の俯瞰に弱い。その中間として「実装すると決めた時点」が妥当
- 業界慣行としても、カタログと課題ボードの分離は一般的である

---

## 7. 今後の対応

- ルールの正本は各一覧の備考および本判断とする。backlog の [`BLG-PRJ-003`](../01_project/backlog.md) / [`BLG-PRJ-014`](../01_project/backlog.md) は完了済み
- 次の機能・画面追加時（例：[`BLG-PRJ-011`](../01_project/backlog.md)）に、掲載タイミングの目安が運用できるか確認する
- 廃止した対象を一覧に戻さない（必要なら新規画面として設計する）

### 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`docs/03_system/features.md`](../03_system/features.md)|機能一覧（状態列なし・掲載タイミング）|
|[`docs/03_system/screens.md`](../03_system/screens.md)|画面一覧（同上）|
|[`docs/03_system/tables.md`](../03_system/tables.md)|テーブル一覧（同上）|
|[`docs/01_project/progress.md`](../01_project/progress.md)|進捗管理（作業進捗の正本）|
|[`docs/01_project/backlog.md`](../01_project/backlog.md)|BLG-PRJ-003 / BLG-PRJ-014（完了）|
|[`docs/07_decisions/progress_document_placement.md`](progress_document_placement.md)|progress 新設時の判断（本判断の前提）|
|[`docs/07_decisions/employee_list_navigation_pattern.md`](employee_list_navigation_pattern.md)|EMP003 廃止（関連）|
