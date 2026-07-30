# Lombok 限定導入方針

**Document Version** : 1.0

**更新日** : 2026/07/30

**ステータス** : 決定

---

## 1. 目的

業務システム向けスターターキットにおいて、Lombok を導入するかどうかを判断し、詳細・編集画面の実装に入る前に、データクラスまわりの基盤方針を整理する。

---

## 2. 背景

- 実務では Lombok を採用するプロジェクトが多く、getter / setter 等の定型コード削減のメリットがある。
- 一方、本プロジェクトは初学者の理解しやすさ、AI 協調開発、Form における入力変換（`trimToNull` 等）の明示を重視している。
- 現時点では Lombok は未導入であり、Entity・DTO・Form は手書きの getter / setter で実装されている。
- EMP001 社員一覧の実装を経て Entity / Form / DTO の役割分担は整理済み（[`entity_form_dto_roles.md`](entity_form_dto_roles.md)）だが、Entity のボイラープレートが増え、フィールド定義の可読性が低下しやすい課題が見えてきた。
- 詳細・編集画面の実装前に、Lombok の利用範囲を決め、今後の Form 増加と Entity 保守のバランスを取りたい。

---

## 3. 検討した案

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. 完全導入|`@Data` 等を広く利用|コード量が最も少ない|Form の setter ロジック喪失リスク。`@Data` の副作用。初学者・教材向きでない|
|B. 完全非導入|現状の手書き getter / setter を維持|見える Java のみ。環境構築が単純|Entity のフィールドが埋もれやすい。実務とのギャップ|
|C. Entity / 単純 DTO 限定（採用）|Entity と MyBatis マッピング用の単純 DTO に `@Getter` `@Setter` を適用|Entity・DTO の可読性向上。Form の業務ロジックを維持|スタイルが二系統になる。IDE 設定が必要|

---

## 4. 判断基準

- 初学者・AI 協調開発の双方が理解しやすいこと
- Form における入力変換・業務ロジックを明示できること
- Entity のテーブル定義書との対応関係を把握しやすいこと
- MyBatis が要求する no-args コンストラクタを維持できること
- 実務で採用されやすい保守的な Lombok 利用にとどまること
- 詳細・編集画面実装前に基盤として整理できること
- 開発ドキュメント（SSOT）で AI が参照できるルールに落とし込めること
- Entity / Form / DTO の責務分担が曖昧にならないこと

---

## 5. 判断結果

**Lombok を限定導入する。** 詳細・編集画面の実装前に基盤として整理する。

### 利用方針

|対象|方針|
|---|---|
|Entity|`@Getter`・`@Setter` のみ使用可|
|DTO（MyBatis の検索結果などを保持する単純な DTO）|Entity と同様に `@Getter`・`@Setter` を使用してよい|
|Form|手書きの getter / setter を維持（入力変換や業務ロジックを明示するため）|

### DTO の判断基準

責務が曖昧にならないよう、次の考え方を基本とする。詳細ルールの正本は [`docs/02_rules/coding.md`](../02_rules/coding.md) §6 とする。

- MyBatis の検索結果などを保持する単純な DTO では、`@Getter`・`@Setter` を使用してよい。
- 画面入力の受け取りや、trim・空文字の `null` 化などの入力変換は Form の責務とし、Form では Lombok を使用しない。
- 不変 DTO や Java `record` については、Version 1 では見送り、必要になった段階で別途判断する。
- DTO に入力変換や業務ロジックを持たせる構成は、現時点では原則として想定しない。

### Phase 1 の移行対象

基盤整理として、以下のクラスを Lombok（`@Getter` `@Setter`）へ移行する。

|クラス|種別|
|---|---|
|`Employee`|Entity|
|`Department`|Entity|
|`CommonCode`|Entity|
|`EmployeeListItemDto`|DTO（MyBatis JOIN 結果の一覧表示用）|

### 禁止事項（現時点）

- `@Data` は使用しない（[`docs/02_rules/coding.md`](../02_rules/coding.md) §6 基本方針・禁止事項を参照）
- `@Builder`・`@EqualsAndHashCode`・`@ToString`・`@AllArgsConstructor` は使用しない
- Form への Lombok 適用は行わない

### 補足

- `@Getter` `@Setter` のみであれば、MyBatis が要求する **暗黙の no-args コンストラクタは維持される**。
- `@Slf4j` 等、その他の Lombok アノテーションは本判断の対象外とし、必要になった段階で別途検討する。

---

## 6. 判断理由

- 完全導入（案 A）は、[`docs/02_rules/coding.md`](../02_rules/coding.md) §5 で定めた Form の setter による入力変換と衝突しやすく、AI が `@Data` を乱用するリスクが高い。
- 完全非導入（案 B）はシンプルだが、`Employee` 等の Entity でフィールド定義が getter / setter に埋もれ、テーブル定義書との照合やレビュー効率が低下する。
- Entity と単純な MyBatis マッピング用 DTO に `@Getter` `@Setter` のみを適用する限定導入（案 C）は、データクラスの責務分担（Entity＝テーブル対応、Form＝入力＋変換、DTO＝取得結果の保持）と整合し、実務でも一般的な保守的な使い方にとどまる。
- 既存の `EmployeeListItemDto` は MyBatis の JOIN 結果を保持する単純な DTO であり、Entity と同様に `@Getter` `@Setter` を適用して統一する。
- 入力変換が必要な処理は Form に集約することで、DTO に業務ロジックが混入する構成を避けられる。

---

## 7. 今後の対応

- Lombok 利用ルールの正本は [`docs/02_rules/coding.md`](../02_rules/coding.md) §6 とする。本判断記録は採用背景・検討経緯の記録とする。
- 新規 DTO 追加時（詳細・編集画面等）は、[`docs/02_rules/coding.md`](../02_rules/coding.md) §6 の DTO 判断基準に従う。
- `@Slf4j` の Service 層への適用要否は、本判断とは切り離して将来検討する。

### 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`docs/02_rules/coding.md`](../02_rules/coding.md)|Lombok 利用ルール（SSOT）・データクラスの使い分け|
|[`docs/01_project/setup.md`](../01_project/setup.md)|開発環境構築（IDE 設定）|
|[`docs/07_decisions/entity_form_dto_roles.md`](entity_form_dto_roles.md)|Entity・Form・DTO の役割分担|
|[`docs/02_rules/ai.md`](../02_rules/ai.md)|AI 協調開発の方針|
