# 画面一覧

**Document Version** : 1.0

**更新日** : 2026/07/30

本ドキュメントは、システム全体の画面を俯瞰するための一覧です。
各画面の詳細は、画面設計書を参照してください。

---

## 1. 目的

- 画面ID・画面名・URLを一覧で把握する
- 画面設計書への入口とする
- 機能追加時に、既存画面との重複や抜けを確認する

---

## 2. 画面一覧

|画面ID|画面名|画面種類|URL|設計書|
|---|---|---|---|---|
|CMN001|TOP|その他|`/`|[`docs/05_screen/CMN001_TOP.md`](../05_screen/CMN001_TOP.md)|
|EMP001|社員一覧|一覧|`/employee`|[`docs/05_screen/EMP001_社員一覧.md`](../05_screen/EMP001_社員一覧.md)|
|EMP002|社員登録・編集|登録・編集|`/employee/new`、`/employee/{employeeId}/edit`|[`docs/05_screen/EMP002_社員登録・編集.md`](../05_screen/EMP002_社員登録・編集.md)|

---

## 3. 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`docs/03_system/features.md`](features.md)|機能一覧|
|[`docs/05_screen/`](../05_screen/)|画面設計書|

---

## 4. 備考

- 画面IDは `{機能ID}{連番3桁}` とします（例：`EMP001`）。先頭3文字が機能IDと一致するため、機能ID列は設けません（機能の定義は [`features.md`](features.md)）。
- 詳細な画面項目・遷移・バリデーションは、各画面設計書に記載します。
- 同一画面内の操作（検索・ページングなど）は画面一覧には記載せず、画面設計書に記載します。
- 作業の進捗・完了状態は [`progress.md`](../01_project/progress.md) を正とします。本一覧に「状態」列は設けません。
- **掲載タイミング**: 実装することが決まった時点で本一覧へ追加する（設計・実装の完了は待たない）。目安は [`progress.md`](../01_project/progress.md) に該当 PRG を切ったとき、または Version スコープに含めたとき。機能は先に [`features.md`](features.md) へ載せる。設計書が未作成のときは設計書列を `-` とする。
- 社員の参照・更新・削除は EMP002 編集画面で行う。独立した社員詳細画面は設けない（[`docs/07_decisions/employee_list_navigation_pattern.md`](../07_decisions/employee_list_navigation_pattern.md) 参照）。
