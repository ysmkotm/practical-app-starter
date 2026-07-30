# MyBatis resultMap type の型エイリアス方針

**Document Version** : 1.0

**更新日** : 2026/07/30

**ステータス** : 決定

---

## 1. 目的

Mapper XML の `resultMap` における `type` 属性の表記方針（型エイリアス / 完全修飾名）を決定し、Entity と DTO の扱いを将来見返しても納得できる形で記録する。

---

## 2. 背景

本プロジェクトでは、MyBatis の型エイリアス設定として `mybatis.type-aliases-package=com.ysmkotm.businessstarter.entity` のみが登録されていた。

この結果、Mapper XML では次の使い分けが生じていた。

|種別|表記|例|
|---|---|---|
|Entity|型エイリアス|`Department`|
|DTO|完全修飾名|`com.ysmkotm.businessstarter.dto.EmployeeListItemDto`|

当初、DTO を完全修飾名とする理由として「画面・クエリごとにクラスが増える」「XML 上で用途を明示する」等が [`coding.md`](../02_rules/coding.md) §9 に記載されていたが、これは **意図的な設計判断というより、設定に合わせた結果** に近かった。

EMP001 Mapper XML レビューの整理において、ルールの根拠を明確にする必要が生じた。

---

## 3. 検討した案

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. Entity のみ型エイリアス、DTO は完全修飾名（現状）|Entity パッケージのみ `type-aliases-package` に登録|Mapper XML 上で DTO の完全修飾名により、クエリ結果型が即座に分かる|Mapper XML 間で表記が不統一。設定の結果を後付けで説明しがち|
|B. Entity・DTO とも型エイリアス（採用）|`entity` と `dto` の両パッケージを `type-aliases-package` に登録|Mapper XML の表記が統一される。命名規約（`*Dto`）と整合|XML 単体では DTO のパッケージが alias からは分からない（`namespace`・`resultMap id` で特定）|
|C. すべて完全修飾名|型エイリアスを使用しない|最も明示的|Entity マッピングが冗長。MyBatis の一般的な慣習から外れる|

---

## 4. 判断基準

- Mapper XML の表記をプロジェクト内で統一できること
- 本プロジェクトの命名規約（Entity は 1 テーブル 1 クラス、DTO は `{用途}Dto`）と整合すること
- 将来見返したときに「なぜその運用か」を説明できること
- 設定・実装の変更コストが小さいこと

---

## 5. 判断結果

**案Bを採用** する。

- `mybatis.type-aliases-package` に `com.ysmkotm.businessstarter.entity` と `com.ysmkotm.businessstarter.dto` を登録する
- Mapper XML の `resultMap type` では、登録対象パッケージのクラスは **型エイリアス**（短いクラス名）を使用する
- 登録対象外のクラスは **完全修飾名** を使用する

本記録のパッケージ名は判断当時（`com.ysmkotm.businessstarter`）のものです。パッケージはその後 `io.github.ysmkotm.practicalappstarter` へ移転しており（[`java_package_and_maven_coordinates.md`](java_package_and_maven_coordinates.md)）、現行の設定値は [`src/main/resources/application.properties`](../../src/main/resources/application.properties) を正とします。方針そのもの（Entity・DTO とも型エイリアス）は移転後も変わりません。

---

## 6. 判断理由

- 当初の Entity のみ alias・DTO は完全修飾名という運用は、DTO パッケージ追加前の設定に起因しており、強い設計思想に基づくものではなかった
- 本プロジェクトでは DTO は `dto` パッケージに集約し、`EmployeeListItemDto` のように用途を含む名称で命名するため、型エイリアス使用時の名前衝突リスクは低い
- Entity・DTO とも MyBatis のマッピング先であり、Mapper XML の表記を統一する方が読みやすい
- どの DTO かは `namespace`（Mapper インタフェース）と `resultMap id` の組み合わせで十分特定できる
- 案A を維持する場合は「クエリ結果型を XML 上で完全修飾名表示する」という別の意図的設計として再定義が必要だが、本プロジェクトの規模・命名規約ではその必然性は低い

---

## 7. 今後の対応

- 新規 DTO を追加する場合も、`dto` パッケージに配置し、型エイリアスを使用する
- `entity` / `dto` 以外のパッケージのクラスを `resultMap` にマッピングする場合は、完全修飾名を使用するか、必要に応じて `type-aliases-package` への追加を検討する

### 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`docs/02_rules/coding.md`](../02_rules/coding.md) §9|Mapper 実装ルール|
|[`src/main/resources/application.properties`](../../src/main/resources/application.properties)|MyBatis 型エイリアス設定|
|[`src/main/resources/mapper/EmployeeMapper.xml`](../../src/main/resources/mapper/EmployeeMapper.xml)|DTO 型エイリアス適用例|
