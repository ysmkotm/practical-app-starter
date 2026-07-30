# レビュー結果の昇格基準（coding.md / 判断記録 / backlog の線引き）

**Document Version** : 1.0

**更新日** : 2026/07/30

**ステータス** : 決定

---

## 1. 目的

成果物レビューで得られた知見を、開発ドキュメントのどこへ昇格させるかの判断基準を決定し、個別画面の設計判断とプロジェクト全体のコーディングルールが混在しないようにする。

---

## 2. 背景

EMP001 社員一覧の Mapper XML レビュー（REV-EMP-012）において、次の 5 項目についてレビュー・方針整理を行った。

1. コード種別（`EMPLOYEE_STATUS`）の重複
2. 共通コード・部署マスタの JOIN 方針（INNER / LEFT）
3. `resultMap` の `type` の書き方
4. LIKE 検索のワイルドカードエスケープ
5. `resultMap` の `<id>`、ORDER BY の第2ソートキー

当初、レビュー結果を [`docs/02_rules/coding.md`](../02_rules/coding.md) §9 へ一括反映したが、**個別画面の業務判断**（例：LEFT JOIN 採用）と **プロジェクト全体で成立する技術的慣習**（例：`<id>` の使用）が同じ章に並び、保存先の役割が曖昧になった。

あわせて、`resultMap type` については「DTO を完全修飾名とする理由」が、**意図的な設計判断ではなく設定の結果を後付けで説明していた** 点も判明した（後に [`mybatis_resultmap_type_aliases.md`](mybatis_resultmap_type_aliases.md) で再判断）。

本プロジェクトでは、レビュー結果の保存先として次の層を運用している。

|保存先|役割|
|---|---|
|[`docs/02_rules/coding.md`](../02_rules/coding.md) 等|プロジェクト全体のコーディングルール（正本）|
|[`docs/07_decisions/`](../07_decisions/)|決定済みの設計判断とその理由|
|[`docs/01_project/backlog.md`](../01_project/backlog.md)|未決定・保留中の再検討事項|
|[`docs/01_project/review_findings.md`](../01_project/review_findings.md)|指摘の要約と処理先の索引（本文の代わりにはしない）|

レビュー結果をどこへ昇格させるかの基準を、Mapper XML レビューを具体例として明文化する必要があった。昇格後は、追跡価値がある指摘を [`review_findings.md`](../01_project/review_findings.md) へ1行登録する（導入判断は [`review_findings_index.md`](review_findings_index.md)）。

---

## 3. 検討した案

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. レビュー結果は原則 `coding.md` へ昇格|ルールへ集約し、AI・開発者が参照しやすい|個別画面の判断まで一般化され、適用条件が不明瞭になりうる|
|B. 保存先ごとに昇格基準を定める（採用）|普遍 / 画面依存 / 将来検討を分離できる|判断に1段階の整理が必要|
|C. レビューごとに判断記録のみ作成し、ルールへは昇格しない|画面文脈が残りやすい|共通ルールとして再利用しにくい|

---

## 4. 判断基準

レビュー結果を昇格させる際、次の観点で保存先を決める。

### 4.1 共通ルール（`coding.md` 等）へ昇格する条件

- **複数の画面・機能・Mapper XML で前提が変わらない** 内容である
- **技術的慣習** または **プロジェクト構成に基づく実装規約** として説明できる
- 「なぜその運用か」を、設定や命名規則・レイヤー構成と結びつけて説明できる
- 個別画面の業務仕様を **前提条件として書かずに** 成立する

### 4.2 判断記録（`07_decisions/`）へ残す条件

- **特定画面・機能の業務仕様** に依存する設計判断である
- INNER JOIN / LEFT JOIN の選択など、**参照先欠損時の表示方針** が業務要件に左右される
- 将来、同種の画面で **本判断を参照してよい** 類似ケースがある（ただし無条件の共通ルールにはしない）
- 判断理由・検討した案・不採用理由を残す価値がある

### 4.3 バックログ（`backlog.md`）へ回す条件

- 方針は妥当だが、**現時点の事例が少なく** 共通ルール化が早い
- **2 件目以降の実装** または **重複パターンの発生** を待ってから共通化した方がよい
- 実装方式（SQL / ユーティリティ / Form 等）が未確定

### 4.4 昇格しない（既存 SSOT で足りる）条件

- 既存の判断記録・コーディングルール・画面設計書で説明可能
- 単一ファイルの技術的修正（改行コード、空行等）で、設計判断を伴わない

---

## 5. 判断結果

**案Bを採用** する。レビュー結果は §4.1〜§4.4 の基準に従い、保存先を決める。

Mapper XML レビュー（REV-EMP-012）における整理結果は次のとおり。

|#|論点|保存先|理由|
|---|---|---|---|
|1|`EMPLOYEE_STATUS` 定数の重複|既存判断・backlog|[`employee_service_emp001_review.md`](employee_service_emp001_review.md) と BLG-CMN-005 で足りる|
|2|LEFT JOIN 採用|判断記録|[`employee_list_master_join_left_join.md`](employee_list_master_join_left_join.md)。EMP001 の業務仕様に依存|
|2'|JOIN 方式の選び方（メタルール）|共通ルール §9|業務仕様に従い画面設計書・判断記録で決める旨のみ|
|3|`resultMap type`|判断記録＋共通ルール|[`mybatis_resultmap_type_aliases.md`](mybatis_resultmap_type_aliases.md) で採用理由を記録。§9 へ実装ルールを反映|
|4|`<id>` の使用|共通ルール §9|MyBatis の技術的慣習。Mapper XML 全体に適用可能|
|5|ORDER BY 第2ソートキー|共通ルール §9|マスタ一覧全般の SQL 安定性。DB 設計（UNIQUE 制約）と整合|
|6|LIKE エスケープ|画面設計書＋backlog|EMP001 §8.5 に仕様。共通化は BLG-CMN-007 で保留|

---

## 6. 判断理由

- 個別画面の判断（LEFT JOIN で社員行を残す等）をそのまま `coding.md` の「基本とする」表現で一般化すると、業務仕様が異なる画面へ誤適用されうる
- 一方、`<id>` や ORDER BY の tiebreaker、型エイリアス登録パッケージの扱いは、画面を問わず説明できるため共通ルールが適切
- LIKE エスケープは方針は妥当だが、Version 1 時点では検索画面が EMP001 のみ。`trimToNull` 共通化（BLG-CMN-006）と同様、保留から昇格する方が YAGNI と整合する
- 「設定の結果」を「設計判断」のように記載しない。理由を説明できないルールは、判断記録で経緯を整理するか、設定・命名規約と結びつけて書き直す。手順の詳細は非公開のAI協調開発ログ（ローカル）で管理する

---

## 7. 今後の対応

- EMP002 以降のレビューでも、本基準に従い保存先を決める
- レビュー完了時は [`progress.md`](../01_project/progress.md) の完了 PRG 要約に判断記録・backlog・ルール反映へのリンクを残す
- 追跡価値がある指摘は [`review_findings.md`](../01_project/review_findings.md) へ登録し、関連列から処理先を辿れるようにする（登録基準は同ドキュメント §3）
- ドキュメント全体の見直しは BLG-PRJ-002 のタイミングで、本基準を具体例として参照する

### 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`docs/01_project/progress.md`](../01_project/progress.md)|作業管理（PRG-EMP-002 完了要約。旧 REV-EMP-012）|
|[`docs/01_project/review_findings.md`](../01_project/review_findings.md)|レビュー指摘の索引|
|[`docs/07_decisions/review_findings_index.md`](review_findings_index.md)|指摘一覧導入の判断記録|
|[`docs/02_rules/coding.md`](../02_rules/coding.md) §9|Mapper 実装ルール（正本）|
|[`docs/07_decisions/employee_list_master_join_left_join.md`](employee_list_master_join_left_join.md)|LEFT JOIN 採用（EMP001）|
|[`docs/07_decisions/mybatis_resultmap_type_aliases.md`](mybatis_resultmap_type_aliases.md)|型エイリアス方針|
|[`docs/01_project/backlog.md`](../01_project/backlog.md)|BLG-CMN-007（LIKE エスケープ）|
|非公開のAI協調開発ログ（ローカル）|本レビューの AI 協調開発ログ|
|非公開のAI協調開発ログ（ローカル）|ルール・設定・実装の一貫性確認（AI 設計レビューの進め方）|
