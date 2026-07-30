# 画面設計書・DB設計書のフォルダ番号の入れ替え

**Document Version** : 1.0

**更新日** : 2026/07/30

**ステータス** : 決定

---

## 1. 目的

`docs` 配下の画面設計書（旧 `04_screen`）とDB設計書（旧 `05_db`）のフォルダ番号の並び順を見直し、開発ドキュメント間の矛盾を解消する。

---

## 2. 背景

`docs/README.md` §2 のカテゴリ表では、`04_screen`（画面設計書）→ `05_db`（DB設計書）の順にフォルダ番号が振られていた。

一方で、以下の箇所ではDB設計を画面設計より先に行う順序が示されていた。

- `docs/02_rules/development.md` §3「開発の流れ」：`1. 仕様整理 → 2. DB設計 → 3. 画面設計 → ...`
- `docs/README.md` §4「タスク別の参照先」：行の並びが「DB設計」→「画面設計」の順

さらに実態としても、`EMP001_社員一覧.md`（画面設計書）は既存のテーブル定義書（`department`・`employee`）を前提として作成されており、テーブル定義（マイグレーションを含む）が画面設計より先に整備されていた。

フォルダ番号の並びだけが実際の開発順序・他の開発ドキュメントの記載と逆になっており、矛盾していた。

---

## 3. 検討した案

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. フォルダ番号を入れ替える（`04_db`→`05_screen`）|DB設計書を画面設計書より前の番号にする|開発フロー・実態と整合する。`README.md`内の矛盾も解消する|フォルダ名変更に伴い、複数ドキュメントのリンク修正が必要|
|B. フォルダ番号は変えず、「番号は開発順ではなく読みやすい順」と明記する|現状維持|変更コストがない|`development.md`・`README.md`§4との矛盾が残る。読み手・AIが混乱する可能性がある|
|C. `development.md`・`README.md`§4側を画面設計→DB設計の順に修正する|フォルダ番号側に合わせる|フォルダ変更が不要|実際の開発順序（DB設計が先）と矛盾したドキュメントになり、実態と合わない|

---

## 4. 判断基準

- 開発ドキュメント間で矛盾がないこと
- 実際の開発順序（`development.md`の開発フロー）と整合すること
- 人間・AIともに、フォルダ番号から開発順序を誤解しないこと

---

## 5. 判断結果

**案Aを採用**する。

- `docs/04_screen` を `docs/05_screen` へ、`docs/05_db` を `docs/04_db` へ変更する。
- 変更後のカテゴリ順：`01_project → 02_rules → 03_system → 04_db → 05_screen → 06_api → 07_decisions`
- `docs/README.md`・`docs/02_rules/naming.md`・`docs/02_rules/documentation.md`・`docs/02_rules/db.md`・`docs/03_system/tables.md`・`docs/03_system/common_codes.md`・`docs/03_system/screens.md`・`docs/07_decisions/docs_rules_vs_templates.md`・`docs/07_decisions/screen_doc_as_detail_design.md`・移動対象ファイル自身のリンクを、新しいパスに更新する。
- `docs/07_decisions/system_overview_folder.md` は、当時決定した内容の記録として**変更しない**（当時は`04_screen`・`05_db`という並びで決定されたことが事実であり、後から書き換えると判断記録としての価値が失われるため）。

---

## 6. 判断理由

- フォルダ番号は「開発順序」と「読みやすい順序」という2つの軸を持ちうるが、今回は`development.md`の開発フローという既存の明文化されたルールと矛盾していたため、実態のない独自の解釈（案B）を新設するより、既存ルールとの整合を優先した。
- 案Cは、フォルダ構成を変えずに済むが、DB設計が先という実際の開発順序・既存のテーブル定義書の整備実績と矛盾するドキュメントになってしまう。
- プロジェクトが初期段階（画面設計書1件、テーブル定義書3件程度）であり、フォルダ番号変更に伴うリンク修正のコストが小さいうちに直した方が、今後ドキュメントが増えてからの修正よりも低コストである。

---

## 7. 今後の対応

- 新しい画面設計書・テーブル定義書を作成する際は、更新後のパス（`docs/05_screen`・`docs/04_db`）に従う。
- 今後さらにカテゴリを追加する場合は、開発順序・読みやすさの両観点から番号を検討し、矛盾が生じないか`docs/02_rules/development.md`の開発フローと突き合わせて確認する。

### 関連ドキュメント

- [`docs/README.md`](../README.md) §2・§4
- [`docs/02_rules/development.md`](../02_rules/development.md) §3
- [`docs/07_decisions/system_overview_folder.md`](system_overview_folder.md)
- [`docs/07_decisions/screen_doc_as_detail_design.md`](screen_doc_as_detail_design.md)
