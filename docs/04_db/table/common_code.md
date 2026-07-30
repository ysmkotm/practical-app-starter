# common_code 共通コードマスタ

**Document Version** : 1.0

**更新日** : 2026/07/30

## 1. 基本情報

|項目|内容|
|---|---|
|物理名|common_code|
|論理名|共通コードマスタ|
|概要|共通コードを管理する|
|作成者||
|作成日|2026/07/10|
|更新日||

---

## 2. カラム定義

|No|物理名|論理名|データ型|PK|FK|NOT NULL|初期値|備考|
|---|---|---|---|---|---|---|---|---|
|1|common_code_id|共通コードID|BIGSERIAL|○||○||自動採番|
|2|code_type|コード種別|VARCHAR(50)|||○|||
|3|code|コード値|VARCHAR(50)|||○|||
|4|code_name|コード名称|VARCHAR(100)|||○|||
|5|display_order|表示順|INTEGER|||○|0||
|6|deleted_flg|削除フラグ|BOOLEAN|||○|FALSE||
|7|created_at|作成日時|TIMESTAMP|||○|CURRENT_TIMESTAMP||
|8|updated_at|更新日時|TIMESTAMP|||○|CURRENT_TIMESTAMP||

---

## 3. 外部キー

なし

---

## 4. 制約

|制約名|種別|対象カラム|内容|
|---|---|---|---|
|uk_common_code_type_code|UNIQUE|code_type, code|重複不可（`code_type`と`code`の組み合わせで一意）|

---

## 5. インデックス

なし

---

## 6. 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`docs/03_system/common_codes.md`](../../03_system/common_codes.md)|共通コード一覧（コード種別・コード値）|
|[`docs/02_rules/db.md`](../../02_rules/db.md) §4|共通コードの方針|
