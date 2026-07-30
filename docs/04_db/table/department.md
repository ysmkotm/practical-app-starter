# department 部署マスタ

**Document Version** : 1.0

**更新日** : 2026/07/30

## 1. 基本情報

|項目|内容|
|---|---|
|物理名|department|
|論理名|部署マスタ|
|概要|部署情報を管理する|
|作成者||
|作成日|2026/07/10|
|更新日||

---

## 2. カラム定義

|No|物理名|論理名|データ型|PK|FK|NOT NULL|初期値|備考|
|---|---|---|---|---|---|---|---|---|
|1|department_id|部署ID|BIGSERIAL|○||○||自動採番|
|2|department_code|部署コード|VARCHAR(20)|||○|||
|3|department_name|部署名|VARCHAR(100)|||○|||
|4|display_order|表示順|INTEGER|||○|0||
|5|deleted_flg|削除フラグ|BOOLEAN|||○|FALSE||
|6|created_at|作成日時|TIMESTAMP|||○|CURRENT_TIMESTAMP||
|7|updated_at|更新日時|TIMESTAMP|||○|CURRENT_TIMESTAMP||

---

## 3. 外部キー

なし

---

## 4. 制約

|制約名|種別|対象カラム|内容|
|---|---|---|---|
|部署コード一意制約|UNIQUE|department_code|重複不可|

---

## 5. インデックス

なし

---

## 6. 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`docs/04_db/table/employee.md`](employee.md)|社員マスタ（本テーブルを外部キー参照）|
