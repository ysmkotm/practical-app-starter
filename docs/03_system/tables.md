# テーブル一覧

**Document Version** : 1.0

**更新日** : 2026/07/30

本ドキュメントは、システム全体のテーブルを俯瞰するための一覧です。
各テーブルの詳細は、テーブル定義書および ER図を参照してください。

---

## 1. 目的

- テーブル物理名・論理名・機能の対応を一覧で把握する
- テーブル定義書・ER図への入口とする
- 機能追加時に、既存テーブルとの関係を確認する

---

## 2. テーブル一覧

|物理名|論理名|機能ID|概要|定義書|
|---|---|---|---|---|
|common_code|共通コード|CMN|区分コードなどの共通コードを管理する|[`docs/04_db/table/common_code.md`](../04_db/table/common_code.md)|
|department|部署マスタ|CMN|部署情報を管理する|[`docs/04_db/table/department.md`](../04_db/table/department.md)|
|employee|社員マスタ|EMP|社員情報を管理する|[`docs/04_db/table/employee.md`](../04_db/table/employee.md)|

---

## 3. 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`docs/03_system/features.md`](features.md)|機能一覧|
|[`docs/03_system/er.md`](er.md)|ER図|
|[`docs/03_system/common_codes.md`](common_codes.md)|共通コード一覧|
|[`docs/04_db/table/`](../04_db/table/)|テーブル定義書|

---

## 4. 備考

- テーブルのカラム定義・制約・インデックスは、各テーブル定義書に記載します。
- テーブル間の関連は [`docs/03_system/er.md`](er.md) を正とします。
- VIEW が増えた場合は、必要に応じて VIEW一覧を別途追加します。
- 作業の進捗・完了状態は [`progress.md`](../01_project/progress.md) を正とします。本一覧に「状態」列は設けません。
- **掲載タイミング**: 実装することが決まった時点で本一覧へ追加する（定義書・DDL の完了は待たない）。目安は [`progress.md`](../01_project/progress.md) に該当 PRG を切ったとき、または Version スコープに含めたとき。機能は先に [`features.md`](features.md) へ載せる。定義書が未作成のときは定義書列を `-` とする。
