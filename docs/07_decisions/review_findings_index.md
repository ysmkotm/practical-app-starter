# レビュー指摘一覧（`review_findings.md`）の導入と配置

**Document Version** : 1.0

**更新日** : 2026/07/30

**ステータス** : 決定

> **追記（2026/07/24）**：一時的に `findings.md` へ短縮したが、日本語話者にとって直感的でないため `review_findings.md` に戻した。

---

## 1. 目的

レビューで見つかった指摘を、修正・バックログ・判断記録・ルールへどう処理したかを追跡できる **軽量な品質改善の索引** を導入し、保存先・項目・運用を定める。

---

## 2. 背景

レビューセッション管理（`review.md` / REV-ID）は、作業管理との二重化を解消するため progress へ統合したあと、PRG 中心の作業管理へ再編し廃止した（[`review_into_progress_merge.md`](review_into_progress_merge.md)、[`progress_prg_centric_work_management.md`](progress_prg_centric_work_management.md)）。

その結果、次の役割分担は明確になった。

- [`progress.md`](../01_project/progress.md) — どの作業（PRG）がレビュー中・完了か、次に何をするか
- [`backlog.md`](../01_project/backlog.md) — 未決定・保留の検討事項本体
- [`07_decisions/`](./) / [`02_rules/`](../02_rules/) — 判断・ルール本文
- [`review_findings_escalation_criteria.md`](review_findings_escalation_criteria.md) — レビュー結果の昇格先の線引き

一方、**指摘単位**で「何が見つかり、どこへ処理され、未対応か対応済みか」を横断して確認する場所がなくなった。progress の完了要約は PRG 単位の要約であり、指摘の索引ではない。backlog は未決定事項の正本であり、既に判断記録・ルールへ反映済みの指摘や再発の追跡には向かない。

レビューセッション一覧の復活ではなく、指摘単位の索引が必要になった。

---

## 3. 検討した案

### 3.1 保存先

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. `docs/01_project/review_findings.md`（採用）|progress / backlog と同じプロジェクト管理層に置く|入口が揃う。番号体系を増やさない|`01_project` のドキュメント種が増える|
|B. `docs/06_reviews/` 新設|レビュー専用カテゴリ|レビュー関連を分離できる|`06_api` と番号衝突。カテゴリ追加コストが高い。軽量索引には過大|
|C. progress / backlog 内の章として同居|新ファイルを増やさない|ファイル数は増えない|役割が再び混在し、肥大化しやすい|

### 3.2 管理粒度

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. 指摘単位の索引（採用）|1行＝1指摘。処理先へのリンク中心|再発・対応状況を辿れる。セッション一覧は復活しない|登録判断が必要|
|B. レビューセッション一覧の復活|REV-ID を戻す|セッション履歴が残る|progress の PRG 中心方針と再衝突|
|C. backlog にレビュー由来フラグだけ足す|索引を作らない|ファイルが増えない|対応済み・ルール化済みの指摘を追跡しにくい|

---

## 4. 判断基準

- progress / backlog / decisions / rules と役割を重複させないこと
- 巨大な管理表にせず、処理先を辿る索引にとどめること
- REV-ID・レビューセッション一覧を復活させないこと
- 更新負荷が高くなりすぎないこと（問題なしのみ・軽微修正は載せない）
- 既存の番号体系（PRG / BLG）と整合すること

---

## 5. 判断結果

**保存先は案 A（`docs/01_project/review_findings.md`）**、**管理粒度は指摘単位の索引** を採用する。

|項目|内容|
|---|---|
|目的|レビュー指摘の要約と最終処理先を追跡する品質改善の索引|
|ID|`FND-{プレフィックス}-{連番3桁}`（例：`FND-EMP-001`）。完了後も再利用しない|
|関連レビュー|REV-ID は使わない。関連列に PRG-ID を記載する|
|列構成|ID / 対象 / カテゴリ / 指摘内容 / 対応方針 / 状態 / 関連（7列）|
|対応方針|修正 / バックログ化 / ルール化 / 判断記録化 / 対応不要|
|状態|未対応 / 対応中 / 対応済み / 保留|
|登録基準|修正した、backlog 化した、ルール・判断へ昇格した、意図的に対応不要とした指摘。問題なしのみ・表記ゆれ級は載せない|
|再発|同一論点の再発は新行を追加し、関連列で前回 FND / BLG を参照する（上書きしない）|
|progress|完了要約は維持。学びがある場合のみ FND への任意リンクを付けてよい|
|本文の正本|未決定は backlog、判断は decisions、ルールは `02_rules`。findings は本文の代わりにしない|

---

## 6. 判断理由

- 欠けていたのは「セッション進捗」ではなく「指摘 → 処理先」の追跡である。progress の PRG 中心方針を維持したまま補完できる
- `06_reviews` は API 設計カテゴリと衝突し、独立カテゴリは現規模では過剰（progress 配置判断と同様）
- backlog に寄せると、既にクローズ・昇格済みの指摘や再発情報が未決定一覧を汚す
- 7列・関連1列に集約し、重要度や会話ログを持たせないことで、運用負荷を抑えられる
- 既存の昇格基準（[`review_findings_escalation_criteria.md`](review_findings_escalation_criteria.md)）の「どこへ昇格するか」に、「昇格後に索引へ1行残す」を足す形で整合する

---

## 7. 今後の対応

- [`docs/01_project/review_findings.md`](../01_project/review_findings.md) を新設し、過去の重要指摘を厳選して初期登録する
- [`progress.md`](../01_project/progress.md)、[`backlog.md`](../01_project/backlog.md)、[`docs/README.md`](../README.md)、[`development.md`](../02_rules/development.md)、[`ai.md`](../02_rules/ai.md)、[`review_findings_escalation_criteria.md`](review_findings_escalation_criteria.md) を更新する
- レビュー完了時は、登録基準に該当する指摘のみ findings へ追加し、処理先（BLG / Decision / ルール / 修正）を関連列に書く
- findings が肥大化した場合は、Version 単位の要約や完了行の整理を検討する（最初から分割しない）

### 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`docs/01_project/review_findings.md`](../01_project/review_findings.md)|レビュー指摘一覧（正本）|
|[`docs/01_project/progress.md`](../01_project/progress.md)|作業管理（PRG）|
|[`docs/01_project/backlog.md`](../01_project/backlog.md)|未決定・保留事項|
|[`docs/07_decisions/review_findings_escalation_criteria.md`](review_findings_escalation_criteria.md)|昇格先の線引き|
|[`docs/07_decisions/progress_prg_centric_work_management.md`](progress_prg_centric_work_management.md)|PRG 中心・REV 廃止|
|[`docs/07_decisions/review_into_progress_merge.md`](review_into_progress_merge.md)|review.md 統合・廃止|
