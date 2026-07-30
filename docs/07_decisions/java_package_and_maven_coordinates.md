# Java パッケージ／Maven 座標

**Document Version** : 1.0

**更新日** : 2026/07/30

**ステータス** : 決定

---

## 1. 目的

GitHub への OSS 公開を前提に、本プロジェクトの Maven 座標（`groupId` / `artifactId`）と Java ベースパッケージの標準を定める。スターターキットとして利用者がコピーする場合の扱い、将来独自ドメインを取得した場合の移行方針も含める。

---

## 2. 背景

現行は次のとおりである。

|種類|現行値|
|---|---|
|`groupId`|`com.ysmkotm`|
|`artifactId`|`businessstarter`|
|ベースパッケージ|`com.ysmkotm.businessstarter`|

`com.ysmkotm` は `ysmkotm.com` 所有を暗示する reverse-DNS だが、独自ドメインは未取得である。一方 Version 1.0 は GitHub フル公開（OSS 寄り）を方針としている（[`public_offering_strategy.md`](public_offering_strategy.md)）。対外名称は Practical App Starter に確定済み（[`project_name_candidates.md`](project_name_candidates.md)）。

パッケージ命名は「作者の公開座標」と「利用者が自分のアプリへ置換する前提」の両方を満たす必要がある。

---

## 3. 検討した案

### 3.1 発行者ネームスペース（`groupId` の根）

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. `com.ysmkotm`（現状維持）|既存のまま|変更コストなし|ドメイン未所有。Maven Central 非対応。お手本として弱い|
|B. `io.github.ysmkotm`（採用）|GitHub ユーザー配下|公開形態と一致。Central でも定石。ドメイン不要|パッケージ移転が一度必要|
|C. `com.example...`|テンプレ定番の仮名|置換前提が明確|公開リポが未完成に見えやすい。ポートフォリオ向きでない|

### 3.2 `groupId` の粒度

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. `io.github.ysmkotm`（採用）|発行者のみ|シリーズ追加しやすい。冗長でない|—|
|B. `io.github.ysmkotm.practicalappstarter`|発行者＋プロジェクト|プロジェクト単位で閉じる|冗長。Edition 追加時も重い|

### 3.3 独自ドメイン取得後

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. 取得次第すべてリネーム|`com.` / 独自 TLD へ移行|見た目の統一|公開後は破壊的。非推奨|
|B. 本リポは `io.github` を維持（採用）|ドメインは別プロダクト／将来用|安定。正規座標のまま|発行者が GitHub 依存に見える（許容）|
|C. メジャー更新時のみ新座標|ライブラリ依存向け|破壊を版で区切れる|現状はアプリ／テンプレ中心で優先度低|

### 3.4 テンプレート利用者向けの見せ方

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. 作者の実座標のまま公開（採用）|`io.github.ysmkotm...`|本物の OSS 作法。実績として自然|利用者が置換する必要あり|
|B. `com.example` で公開|書き換え前提を名前で示す|教材っぽい導線|公開品質・ポートフォリオと弱い|

---

## 4. 判断基準

- 所有していないドメインを reverse-DNS に使わないこと
- Version 1.0 の GitHub OSS 公開方針と矛盾しないこと（[`public_offering_strategy.md`](public_offering_strategy.md)）
- 対外ブランド名と機械可読識別子の役割を分けること（[`project_name_candidates.md`](project_name_candidates.md)）
- 一人運用で維持できること（公開後の安易な再リネームを避ける）
- スターター利用者が自分の座標へ置換しやすいこと
- 技術名（Spring Boot 等）をパッケージに固定しないこと

---

## 5. 判断結果

**発行者ネームスペースは案 B（`io.github.ysmkotm`）を採用する。**  
**`groupId` の粒度は案 A（発行者のみ）。**  
**ドメイン取得後は案 B（本リポは `io.github` 維持）。**  
**テンプレート見せ方は案 A（作者実座標＋置換手順の文書化）。**

### 5.1 決定値（Version 1.0）

|種類|値|
|---|---|
|Maven `groupId`|`io.github.ysmkotm`|
|Maven `artifactId`|`practical-app-starter`|
|Java ベースパッケージ|`io.github.ysmkotm.practicalappstarter`|
|レイヤー配下|現行どおり（`controller` / `service` / `mapper` / `entity` / `form` / `dto` / `config` / `exception`）|

```text
io.github.ysmkotm.practicalappstarter
├── controller
├── service
├── mapper
├── entity
├── form
├── dto
├── config
├── exception
└── （起動クラス）
```

### 5.2 スターターキットとしての標準ルール

本リポジトリおよび同系統のスターターへ横展開する場合の標準とする。

1. `groupId` は検証可能なネームスペースだけを使う（ドメインありならその reverse-DNS、なければ `io.github.<GitHubユーザー>`）
2. `artifactId` は対外ブランドの kebab-case とする
3. ベースパッケージは `groupId` ＋ プロジェクト識別子（小文字・ハイフンなし）。技術名や Edition 名は含めない
4. 対外表示名（README の正式名称）と Maven／パッケージ識別子は役割を分ける
5. 公開後の座標は安易に変えない。ドメイン取得は本リポのリネーム理由にしない

### 5.3 テンプレート利用者向け方針

- 公開リポは作者座標のまま配布する
- 自分のアプリにする場合は、利用者自身の `groupId`／パッケージへ置換する
- 置換対象と手順は README / setup に記載する（反映は PRG-PRJ-002）
- プロジェクト固有トークン（`practicalappstarter`）を一意にし、検索置換しやすくする

### 5.4 対象外

- ローカル DB 名は本判断の対象外とした（判断時点の値は `businessstarter`）
- Organization 名、独自ドメインの取得そのものは本判断の対象外

（追記 2026/07/27）ローカル DB 名は `practical_app_starter` へ変更した（Maven `artifactId` の snake_case。現行値は [`naming.md`](../02_rules/naming.md) §5.1、[`setup.md`](../01_project/setup.md)、[`application.properties`](../../src/main/resources/application.properties)）。

---

## 6. 判断理由

- ドメイン未所有の `com.ysmkotm` は reverse-DNS として不正確で、Maven Central にも載せられない
- GitHub OSS 公開では `io.github.<user>` が現行の定石である
- `groupId` にプロジェクト名までネストすると冗長で、シリーズ展開時の扱いが重い
- 本成果物は「依存ライブラリ」より「コピーして使うリファレンス」が主であるため、公開後のドメイン移行リネームより座標の安定を優先する
- `com.example` 公開は置換導線には優しいが、ポートフォリオ／リファレンス方針と合わない
- ブランド（Practical App Starter）は `artifactId`／パッケージ末尾へ機械可読化すれば足り、Edition（Spring Boot）はパッケージに入れない

---

## 7. 今後の対応

|タイミング|内容|
|---|---|
|PRG-PRJ-002|`pom.xml`、Java パッケージ移転、Mapper XML、`application.properties`、起動クラス名の反映|
|PRG-PRJ-002|[`naming.md`](../02_rules/naming.md) / [`directory.md`](../02_rules/directory.md) へ決定値と使い分けを正式記載|
|PRG-PRJ-002|README / setup に「コピー時のパッケージ置換」手順を追記|
|将来|独自ドメイン取得時も本リポは原則維持。新規プロダクト／Edition でドメイン座標を検討してよい|
|Version 3.x|短いブランド化に伴い `artifactId`／パッケージ末尾だけ見直す場合は別判断とする|

### 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`project_name_candidates.md`](project_name_candidates.md)|対外名称・ブランド・リポジトリ名（座標の前提）|
|[`public_offering_strategy.md`](public_offering_strategy.md)|GitHub OSS 公開方針|
|[`docs/02_rules/directory.md`](../02_rules/directory.md)|パッケージレイヤー構成（反映先）|
|[`docs/02_rules/naming.md`](../02_rules/naming.md)|命名ルール（反映先）|
|[`docs/01_project/progress.md`](../01_project/progress.md)|PRG-PRJ-002（反映作業）|
|[`docs/01_project/backlog.md`](../01_project/backlog.md)|BLG-PRJ-001（名称決定・完了）|
