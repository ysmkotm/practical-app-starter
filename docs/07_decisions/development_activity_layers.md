# 開発活動の3レイヤー分離と変更規模別工程省略

**Document Version** : 1.0

**更新日** : 2026/07/30

**ステータス** : 決定

---

## 1. 目的

システム開発・AI協調・プロジェクト管理の3種類の活動を、独立した「標準開発フロー」に混在させず整理し、個人開発として継続可能な運用（工程の抜け漏れ防止と開発速度の両立）を確立する。

---

## 2. 背景

本プロジェクトでは、次の3種類の活動が並行して進んでいた。

1. **システム開発** — 対象整理、設計、レビュー、実装、テスト
2. **AI協調・知識化** — ChatGPT / Cursor との相談、判断整理、ルール更新、記録
3. **プロジェクト管理** — Version、backlog、progress、review、機能・画面一覧

これらを1本の「標準開発フロー」として [`docs/02_rules/development.md`](../02_rules/development.md) §3 と [`docs/02_rules/ai.md`](../02_rules/ai.md) §7 に記載していたため、工程とドキュメントの役割が分かりにくくなっていた。

特に [`ai.md`](../02_rules/ai.md) §7 のステップ 8（ルール・判断記録・backlog・progress・review の一括更新）は、個人開発の小さな区切りごとには負荷が高く、更新忘れや形式だけの更新を招きやすかった。

一方、[`progress.md`](../01_project/progress.md)・[`backlog.md`](../01_project/backlog.md) では、情報の種類ごとの SSOT が既に整理されており（[`progress_document_placement.md`](progress_document_placement.md)、のち [`review_into_progress_merge.md`](review_into_progress_merge.md) でレビューも progress へ統合）、プロジェクト管理レイヤー自体は機能していた。

---

## 3. 検討した案

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. 3つを独立したフローとして定義|システム開発・AI協調・PJ管理をそれぞれ工程列として明文化|活動の独立性が明確|個人開発で毎回3フローを意識するコストが高い。実態は常に①が主|
|B. ①主軸＋②③横断レイヤー|システム開発を中心に、AI協調とPJ管理を横断的支援として表現|実態に合う。既存 doc の役割分担と整合|初見時に「レイヤー」概念の説明が必要|
|C. 現状維持（ai.md §7 を正本フロー）|変更コスト最小|—|フロー二重定義と更新負荷の問題が残る|
|D. B + 変更規模 Tier（L/M/S/D）|B に加え、新規機能・軽微修正・doc のみで工程を可変化|速度と品質のバランスを明示できる|Tier 判定のブレ。試行運用で調整が必要|

---

## 4. 判断基準

- 個人開発として **継続可能** であること（厳格すぎて速度を落とさない）
- **工程の抜け漏れ**（設計書未更新、backlog 未確認等）を防げること
- 既存ドキュメント（progress / backlog / review / ai.md）との **SSOT 整合**
- AI セッション再開時に **参照先が明確** であること
- スターターキットとして **他開発者にも説明可能** な構造であること

---

## 5. 判断結果

次を採用する。

### 5.1 活動モデル

- **案 B** を採用する。3分類は **3つの独立フローではなく、①主軸＋②③横断レイヤー** として定義する。
- 全体像・Tier ルールの正本は [`docs/02_rules/development.md`](../02_rules/development.md) §2・§3.1 とする。
- [`docs/02_rules/ai.md`](../02_rules/ai.md) §5 は **AI 協調での接点表** にスリム化し、フローの正本としない。

### 5.2 変更規模（Tier）

4段階（**L / M / S / D**）で工程・記録の省略可否を定める。

|Tier|意味|
|---|---|
|L|新規機能・画面・API 追加|
|M|既存機能への処理追加・拡張|
|S|軽微修正（typo、表示調整、小バグ）|
|D|ドキュメントのみの変更|

### 5.3 レビュー記録

[`progress.md`](../01_project/progress.md) の完了 PRG へのレビュー要約は、**ルール反映・保留（backlog）の発見・設計書修正など、後から参照する価値がある結果があった場合** に行う。問題なしの確認のみでは省略可。REV-ID は用いない。

### 5.4 試行運用に留める項目

以下はルール化せず、運用しながら効果を見る。

- PRG-ID の **S Tier 以下での必須化**
- ChatGPT チャット命名規則（[`ai.md`](../02_rules/ai.md) §4.4）の厳格運用
- Tier ごとの詳細チェックリスト
- `private/ai_logs/` への定期記録

---

## 6. 判断理由

- 成果物は常に **システム（コード＋設計書）** であり、AI 協調は手段、progress / backlog / review は **状態管理** である。3本のフローとして定義すると [`progress_document_placement.md`](progress_document_placement.md) で確立した SSOT 分離と矛盾する。
- [`development.md`](../02_rules/development.md) §3（技術実装順）と [`ai.md`](../02_rules/ai.md) §7（AI 込み9ステップ）の **二重定義** が混乱の主因だった。主軸を development に一本化することで参照先が明確になる。
- Tier 導入により、EMP001 削除 POST（M）と typo 修正（S）で同じ工程を要求しない。迷った場合は M 扱いとすることで、S への過剰省略を防ぐ。
- review 記録を「学びがあるときのみ必須」とすることで、progress §6 の形式更新だけの負荷を減らしつつ、ルール反映や backlog 昇格の経路は維持する。

---

## 7. 今後の対応

- [`development.md`](../02_rules/development.md) §2（3レイヤー）・§3.1（Tier）・[`ai.md`](../02_rules/ai.md) §5（接点表）・[`docs/README.md`](../README.md) §2 に反映する（本判断と同時に実施）
- 数回の実装サイクル（L / M / S を含む）で Tier 判定のブレを確認し、必要なら §3.1 を微修正する
- 試行運用項目（§5.4）について、効果が確認できたものだけをルールへ昇格する
- 過去の判断記録で [`ai.md`](../02_rules/ai.md) §7 ステップ 8 を参照している箇所は、履歴として残し、新規参照は [`development.md`](../02_rules/development.md) §3.1 を正とする

### 7.1 用語の補足（2026/07/13）

§5.1 で採用した「3レイヤー」表現のうち、②「AI協調・知識化」は **AI（手段）** と **ナレッジ（領域）** が同居しており、概念上の混在が残っていた。運用原則（Tier、review 基準、SSOT 配置）は変更せず、次のように **用語を精密化** する。

- **3領域**：①システム開発、②プロジェクト管理、③ナレッジ管理（Knowledge Management。以降「ナレッジ管理」と表記）
- **AI協調**：3領域を横断する支援手段（独立した領域ではない）
- **実務の中心**：引き続き ① システム開発

反映先：[`development.md`](../02_rules/development.md) §2、[`ai.md`](../02_rules/ai.md) §5.1、[`docs/README.md`](../README.md) §2。

### 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`docs/02_rules/development.md`](../02_rules/development.md)|3領域・機能実装の流れ・Tier ルール（正本）|
|[`docs/02_rules/ai.md`](../02_rules/ai.md)|AI 協調・領域別接点表|
|[`docs/01_project/progress.md`](../01_project/progress.md)|作業管理の正本|
|[`docs/01_project/backlog.md`](../01_project/backlog.md)|保留・未決定事項|
|[`docs/07_decisions/progress_document_placement.md`](progress_document_placement.md)|progress 新設の判断記録|
|[`docs/07_decisions/progress_prg_centric_work_management.md`](progress_prg_centric_work_management.md)|PRG 中心再編・REV 廃止|
|[`docs/README.md`](../README.md)|開発ドキュメント索引|
