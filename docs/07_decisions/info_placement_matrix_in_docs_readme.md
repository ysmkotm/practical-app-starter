# 情報の置き場所マトリクスを docs/README に集約し各ファイルの柵を薄くする

**Document Version** : 1.0

**更新日** : 2026/07/30

**ステータス** : 決定

---

## 1. 目的

プロジェクト管理系ドキュメント（progress / backlog / review_findings）における「情報の種類 → 管理先」の正本の置き場所と、各ファイルに残す境界説明（柵）の厚さを定める。

---

## 2. 背景

[`progress.md`](../01_project/progress.md)・[`backlog.md`](../01_project/backlog.md)・[`review_findings.md`](../01_project/review_findings.md) の各 §2「本ドキュメントの位置づけ」に、ほぼ同内容の横断マトリクスが重複していた。

- 3ファイルで表を同期するメンテコストが高い
- セッション再開時に毎回読む必要は薄い（実務では §4・台帳側が先）
- 一方で「このファイルに何を書いてよいか／いけないか」の柵は、AI の誤記載防止に有用

また正本候補として [`documentation.md`](../02_rules/documentation.md)（記法ルール）と [`docs/README.md`](../README.md)（索引・全体像）が挙がり、置き場所の選定が必要になった。

---

## 3. 検討した案

### 3.1 横断マトリクスの正本

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. `docs/README.md` に集約|索引に「情報の置き場所」節を新設|カテゴリ案内と同層。既に progress / backlog / findings の役割にも触れている|README がやや増える|
|B. `documentation.md` に集約|記法ルールへ「置き場所」も載せる|ルール系に寄せられる|記法と配置が同居し、冒頭の目的が広がる|
|C. 現状維持（各ファイルに表）|変更なし|参照がその場で完結|三重管理が残る|

### 3.2 各ファイル §2 の厚さ

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. 薄い柵のみ|正本へのリンク＋記載する／しない数行|再開時のノイズ減、重複解消|詳細は README へ1ホップ|
|B. 現状の位置づけ章を維持|マトリクス＋記載する／しないを各所に残す|単独で完結|同期漏れリスク|

---

## 4. 判断基準

- 同じ情報は一か所（SSOT）
- 再開時に毎回読まない説明は薄くする
- 「記法」と「情報の置き場所」の役割を混在させない
- AI が誤った種類の情報を書き込まない程度の柵は残す

---

## 5. 判断結果

- 横断マトリクスの正本は **[`docs/README.md`](../README.md) §5「情報の置き場所」** とする（案 A）
- progress / backlog / review_findings の §2 は **「記載範囲」** に改め、正本リンク＋薄い柵のみとする（案 A）
- `documentation.md` への集約は採用しない（記法の正本に留める）

---

## 6. 判断理由

- README は既に開発ドキュメントの索引であり、「どこを見るか」の案内と「何をどこに書くか」は同層が自然
- `documentation.md` は記法 SSOT であり、配置ルールを足すと目的がぼやける
- 各ファイルに残す柵は、誤記載防止に足りる最小限でよい。詳細な役割分担表は README 1か所で足りる

---

## 7. 今後の対応

- 情報の種類を増やす・管理先を変えるときは **README §5 を先に更新**し、各ファイルの柵は必要なら追随する
- backlog / review_findings の §3「記載ルール」も、運用で長いと感じたら progress と同様に薄くする（ID・状態語彙は残す）
- progress の §3 は本判断の直後にスキーマ中心へ圧縮済み

### 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`docs/README.md`](../README.md) §5|情報の置き場所（正本）|
|[`docs/01_project/progress.md`](../01_project/progress.md) §2|作業管理の記載範囲（柵）|
|[`docs/01_project/backlog.md`](../01_project/backlog.md) §2|バックログの記載範囲（柵）|
|[`docs/01_project/review_findings.md`](../01_project/review_findings.md) §2|指摘索引の記載範囲（柵）|
|[`docs/07_decisions/progress_document_placement.md`](progress_document_placement.md)|progress 新設|
|[`docs/07_decisions/backlog_document_placement.md`](backlog_document_placement.md)|backlog 配置|
|[`docs/07_decisions/review_findings_index.md`](review_findings_index.md)|review_findings 導入|
|[`docs/07_decisions/readme_docs_role_split.md`](readme_docs_role_split.md)|ルート README と docs/README の役割|
