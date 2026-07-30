# レビュー指摘一覧

**Document Version** : 1.0

**更新日** : 2026/07/30

本ドキュメントは、レビューで見つかった指摘の **要約と最終処理先を追跡する索引** です。指摘の本文・仕様・判断理由は記載せず、関連ドキュメントへリンクします。

---

## 1. 目的

- どのような問題・改善点が見つかったかを一覧で把握する
- 修正・バックログ化・ルール化・判断記録化など、処理先を辿れるようにする
- 未対応・保留・対応済みを区別する
- 同様の指摘が再発していないかを確認できるようにする

---

## 2. 記載範囲

情報の種類ごとの管理先は [`docs/README.md`](../README.md) §5 を正とします。本ドキュメントは **指摘の要約と処理先の索引** のみを持ちます。

**記載する**: 指摘の短い要約（何が問題・懸念か）、対応方針・状態、処理先リンク、関連 PRG-ID

**記載しない**: 会話ログ・検討経緯、未決定の本文（→ backlog）、判断・ルール本文、作業の現在地（→ progress）、問題なしのみ・設計判断を伴わない軽微修正

昇格先の線引きは [`review_findings_escalation_criteria.md`](../07_decisions/review_findings_escalation_criteria.md)、配置判断は [`review_findings_index.md`](../07_decisions/review_findings_index.md) を正とします。REV-ID は用いません。

---

## 3. 記載ルール

### ID

- ID は `FND-{プレフィックス}-{連番3桁}` とする（例：`FND-EMP-001`）。
- プレフィックスは [`backlog.md`](backlog.md) §3 と同様（`PRJ` / `CMN` / 機能ID 等）。
- 完了後も ID は変更・再利用しない。
- **REV-ID は用いない**。関連レビューは関連列の PRG-ID で示す。

### カテゴリ

短い語で記載する（例：設計 / 動作 / UI / DB / コーディング / ドキュメント）。

### 対応方針

|対応方針|意味|
|---|---|
|修正|ソース・設計書等を修正してクローズする|
|バックログ化|未決定・将来検討として [`backlog.md`](backlog.md) へ移す|
|ルール化|[`docs/02_rules/`](../02_rules/) 等へ共通ルールとして反映する|
|判断記録化|[`docs/07_decisions/`](../07_decisions/) へ判断を記録する|
|対応不要|指摘を検討したが、現状維持または対象外とする|

複数に該当する場合は、主たる方針を1つ書き、関連列に副次的な反映先もリンクする。

### 状態

|状態|意味|
|---|---|
|未対応|指摘は登録済みだが、まだ着手していない|
|対応中|修正または関連資料への反映を進めている|
|対応済み|意図した処理（修正・昇格・対応不要の確定）が完了した|
|保留|方針は決めたが、意図的に先送りしている（多くは backlog の保留と対になる）|

### 関連列

次を1列にまとめる。未設定は `-` とする。

|記載する内容|例|
|---|---|
|関連 PRG|PRG-EMP-002|
|バックログ|BLG-CMN-007|
|判断記録|`docs/07_decisions/` 配下の該当ファイルへの Markdown リンク|
|ルール|[`coding.md`](../02_rules/coding.md) §9|
|再発時の前回指摘|前回 FND-EMP-001|
|修正コミット|commit `b3ba576`|

### 登録・更新タイミング

- **登録**：レビューで、修正・バックログ化・ルール化・判断記録化・意図的な対応不要のいずれかが発生したとき
- **更新**：対応方針や状態が変わったとき。PRG 完了時にまとめて反映してよい
- **再発**：同じ論点が再度見つかった場合は **新行** を追加し、関連列で前回 FND / BLG を参照する（既存行を上書きしない）
- progress の完了要約に、学びがある場合のみ主な FND へのリンクを付けてよい（必須ではない）

### 運用

- 未対応・対応中・保留は §4.1、対応済みは §4.2 へ移し、行は原則削除しない。
- 本文の正本は backlog / decisions / rules / 設計書側に置き、本ドキュメントは索引に徹する。
- backlog の全項目を機械転記しない。レビュー由来で追跡価値があるものだけ登録する。

---

## 4. 指摘一覧

### 4.1 未対応・対応中・保留

|ID|対象|カテゴリ|指摘内容|対応方針|状態|関連|
|---|---|---|---|---|---|---|
|FND-EMP-005|`EmployeeService`|設計|Service が Web 層の `EmployeeSearchForm` を直接受け取っており、再利用・API 化時に層の分離ができていない懸念がある|バックログ化|保留|PRG-EMP-002、[`BLG-EMP-008`](backlog.md)、[`employee_service_emp001_review.md`](../07_decisions/employee_service_emp001_review.md)|
|FND-EMP-006|`EmployeeService`|設計|プルダウン返却が Entity のままのため、画面に不要な項目まで公開されている懸念がある|バックログ化|保留|PRG-EMP-002、[`BLG-EMP-009`](backlog.md)、[`employee_service_emp001_review.md`](../07_decisions/employee_service_emp001_review.md)|
|FND-CMN-001|Service 層|設計|参照系への `@Transactional(readOnly = true)` 付与方針が決まっておらず、書き込み追加時に揃えられない懸念がある|バックログ化|保留|PRG-EMP-002、[`BLG-CMN-004`](backlog.md)、[`employee_service_emp001_review.md`](../07_decisions/employee_service_emp001_review.md)|
|FND-CMN-002|`EmployeeService`|コーディング|コード種別 `EMPLOYEE_STATUS` が Service 内 private のため、他クラスで同じ定数を共有できていない|バックログ化|保留|PRG-EMP-002、[`BLG-CMN-005`](backlog.md)、[`employee_service_emp001_review.md`](../07_decisions/employee_service_emp001_review.md)|
|FND-CMN-003|Form|コーディング|`trimToNull` が各 Form に重複しており、共通化されていない|バックログ化|保留|PRG-EMP-002、[`BLG-CMN-006`](backlog.md)、[`coding.md`](../02_rules/coding.md) §5|
|FND-CMN-004|Mapper XML / 検索|DB|LIKE ワイルドカードエスケープの共通方針が決まっておらず、画面ごとに実装がばらつく懸念がある（EMP001 は設計書どおり実装済み）|バックログ化|保留|PRG-EMP-002、[`BLG-CMN-007`](backlog.md)、[`EMP001_社員一覧.md`](../05_screen/EMP001_社員一覧.md) §8.5|
|FND-CMN-006|`EmployeeService`|設計|UNIQUE 制約違反の判定が `PSQLException` と物理制約名に依存しており、Service 層の責務として適切でない懸念がある|バックログ化|保留|PRG-EMP-008、[`BLG-CMN-017`](backlog.md)、[`PRG-EMP-010`](progress.md)|
|FND-CMN-007|EMP002 マスタ参照|設計|論理削除済みマスタの現在値補完・妥当性検証が EMP002 固有のままで、他画面へ共通化されていない|バックログ化|保留|PRG-EMP-007 / 008、[`BLG-CMN-018`](backlog.md)、[`emp002_soft_deleted_master_reference.md`](../07_decisions/emp002_soft_deleted_master_reference.md)|

### 4.2 対応済み

|ID|対象|カテゴリ|指摘内容|対応方針|状態|関連|
|---|---|---|---|---|---|---|
|FND-EMP-001|EMP001 Mapper XML|設計|参照マスタが欠損・削除済みのとき INNER JOIN だと社員行ごと消えてしまい、一覧に残らない懸念があった|判断記録化|対応済み|PRG-EMP-002、[`employee_list_master_join_left_join.md`](../07_decisions/employee_list_master_join_left_join.md)|
|FND-EMP-002|MyBatis `resultMap`|コーディング|`resultMap` の `type` の書き方（完全修飾名 / エイリアス）の方針がはっきりしておらず、設定結果を設計判断のように書いてしまう懸念があった|判断記録化|対応済み|PRG-EMP-002、[`mybatis_resultmap_type_aliases.md`](../07_decisions/mybatis_resultmap_type_aliases.md)、[`coding.md`](../02_rules/coding.md) §9|
|FND-EMP-003|プロジェクト全体|コーディング|Lombok をどこまで使うかの方針が決まっておらず、クラスごとに採用がばらつく懸念があった|判断記録化|対応済み|PRG-EMP-002、[`lombok_limited_adoption.md`](../07_decisions/lombok_limited_adoption.md)|
|FND-EMP-004|レビュー運用|ドキュメント|レビュー結果を coding / decisions / backlog のどこへ上げるかの基準がなく、個別判断と共通ルールが混ざる懸念があった|判断記録化|対応済み|PRG-EMP-002、[`review_findings_escalation_criteria.md`](../07_decisions/review_findings_escalation_criteria.md)|
|FND-EMP-007|Entity / Form / DTO|設計|Entity / Form / DTO の役割分担が文書化されておらず、レイヤー境界が曖昧になる懸念があった|判断記録化|対応済み|PRG-EMP-002、[`entity_form_dto_roles.md`](../07_decisions/entity_form_dto_roles.md)、[`coding.md`](../02_rules/coding.md) §4|
|FND-CMN-005|Mapper XML|コーディング|`resultMap` の `<id>` や ORDER BY の第2ソートキーが揃っておらず、一覧 SQL の安定性に懸念があった|ルール化|対応済み|PRG-EMP-002、[`coding.md`](../02_rules/coding.md) §9、[`review_findings_escalation_criteria.md`](../07_decisions/review_findings_escalation_criteria.md)|
|FND-EMP-008|EMP001 / EMP002 遷移|設計|一覧の操作列・削除導線が画面遷移方針と合っておらず、編集・削除の導線が分かりにくい懸念があった|判断記録化|対応済み|PRG-EMP-004 / 005、[`employee_list_navigation_pattern.md`](../07_decisions/employee_list_navigation_pattern.md)|
|FND-EMP-009|EMP002 POST|設計|登録と更新でバリデーション条件が異なるのに、Validation Groups などで分けられていない懸念があった|判断記録化|対応済み|PRG-EMP-008、[`emp002_post_validation_groups.md`](../07_decisions/emp002_post_validation_groups.md)、BLG-EMP-001 / 002 / 006（完了）|
|FND-EMP-010|EMP002 GET / POST|設計|編集時に論理削除済みマスタが選択肢に無く、現在値の表示・更新ができなくなる懸念があった|判断記録化|対応済み|PRG-EMP-007 / 008、[`emp002_soft_deleted_master_reference.md`](../07_decisions/emp002_soft_deleted_master_reference.md)。共通化は FND-CMN-007|

---

## 5. 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`docs/01_project/progress.md`](progress.md)|作業管理（PRG）・レビュー工程の進捗|
|[`docs/01_project/backlog.md`](backlog.md)|未決定・保留事項|
|[`docs/07_decisions/review_findings_index.md`](../07_decisions/review_findings_index.md)|本ドキュメント導入の判断記録|
|[`docs/07_decisions/review_findings_escalation_criteria.md`](../07_decisions/review_findings_escalation_criteria.md)|昇格先の線引き|
|[`docs/07_decisions/progress_prg_centric_work_management.md`](../07_decisions/progress_prg_centric_work_management.md)|PRG 中心・REV 廃止|
|[`docs/02_rules/development.md`](../02_rules/development.md)|開発活動と記録|
