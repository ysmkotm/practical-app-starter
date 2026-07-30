# プロジェクト名検討

**Document Version** : 1.0

**更新日** : 2026/07/30

**ステータス** : 決定

---

## 1. 目的

本ドキュメントは、プロジェクト名の決定にあたり、候補案・判断基準・運用方針（暫定）・論点を一箇所に集約するための判断記録です。

- 複数の AI（ChatGPT・Cursor 等）や関係者と相談する際の**共通の参照資料**とします。
- 候補の比較・絞り込み・最終決定の経緯を残し、後から「なぜこの名前にしたか」を振り返れるようにします。
- 名前が決まったあとは、§7 を参照して各所を更新します。

---

## 2. 背景

### 現行名

**Spring Boot Business Starter**

### 名前を見直すきっかけ

現行名はプロジェクトの技術スタック（Spring Boot）と用途（業務システム）を端的に表していますが、以下の懸念があります。

|懸念|内容|
|---|---|
|技術名の先行|プロジェクト名に `Spring Boot` が入っているため、将来の他言語版・他フレームワーク版を展開しにくい|
|Business の印象|`Business` が「仕事・業務で使う人向け」と受け取られ、学習者や興味本位の方が遠慮しやすい|
|役割の不足|「スターターキット」であることは伝わるが、「リファレンス実装」「AI協調開発」といった独自性が名前からは読み取りにくい|

### 現行名の分析

上記の懸念を踏まえ、現行名を次の観点で整理します。

|観点|評価|コメント|
|---|---|---|
|学習者への開かれ|△|`Business` が堅い印象を与えやすい|
|実務者への信頼感|◎|業務システム向けであることが明確|
|シリーズ展開|×|`Spring Boot` が名前に含まれている|
|独自性|△|スターターであることは伝わるが、AI協調・設計書重視は伝わらない|
|覚えやすさ|○|やや長いが意味は明確|
|GitHub 向き|○|リポジトリ名としてはそのまま使える|

### プロジェクトの本質（名前に反映したいこと）

以上の整理と [`docs/01_project/project.md`](../01_project/project.md) を踏まえ、新しい名前に反映したい要件は次のとおりです。

- **AI（ChatGPT・Cursor）との協調開発**を前提としたスターターキット
- **設計書・開発ルール・ソースコードを一体管理**する（`docs` を正本とする）
- **業務システム**（CRUD・管理画面など）でよく使われる構成の参照実装
- **学習用途**と**実案件での流用**の両方を想定した品質
- Version 1.x では Spring Boot を採用するが、**技術は版（Edition）として切り出せる**こと

候補の比較方法（命名の構成要素・要件・採点）は §4、名称の運用方針（暫定）は §7 を参照してください。

---

## 3. 検討した案

検討した案や比較結果を整理します。候補は **60 案** です。まず §3.1 の比較優先案（10 案）で §4 の採点を行い、必要に応じて全候補一覧から追加比較してください。候補一覧（§3.1〜3.8）は GitHub 上の可読性のため、折りたたみ表示にしています。

---

<details>
<summary>3.1 比較優先案（ショートリスト・10 案）</summary>

|#|案|概要|メリット|デメリット|
|---|---|---|---|---|
|2|Practical App Starter & Reference|実践的なアプリのスターター＋参照実装|ChatGPT 提案。スターターと参照の両方が明示。学習者にも開かれた印象|やや長い。リポジトリ名には短縮が必要|
|1|Practical App Starter|#2 を短くした版|バランスがよい。`Business` より柔らかい。リポジトリ名にしやすい|リファレンス実装であることが名前からは弱い|
|11|Blueprint Starter|設計の型・参照実装|短くシリーズ展開しやすい。リファレンス感が明確|学習者にはやや抽象的に聞こえる可能性|
|23|AI-Ready Starter & Reference|AI協調開発向けスターター＋参照|最大の差別化（AI）を名前に入れられる|AI という言葉が時代とともに陳腐化する懸念。やや長い|
|41|DocCraft Starter|設計書（docs）＋ craft|`docs` を正本とする SSOT 方針と一致。独自性が高い|造語感がある。初見では意味が伝わりにくい|
|47|Docs & Code Starter|設計書とコードの一体管理|プロジェクトの実態を端的に表現|やや説明的。ブランド名としての個性は中程度|
|31|CraftKit|作りながら学ぶキット|短くブランド化しやすい。Edition 方式と相性がよい|「何のキットか」はタグラインで補う必要がある|
|44|SpecKit Starter|仕様書（Spec）＋ Kit|短く覚えやすい。設計書駆動の思想と合う|Spec の意味が開発者以外には伝わりにくい|
|6|Real-World App Starter|現実的なアプリのスターター|学習者フレンドリー。「実践を学ぶ」ニュアンス|実務・リファレンス感は他案より弱い|
|52|Launchpad|スタート地点のブランド名|短い。スターターであることが直感的|汎用的で他プロジェクトと被る可能性|

</details>

<details>
<summary>3.2 Practical / Real-World 系（#1〜10）</summary>

|#|案|概要|メリット|デメリット|
|---|---|---|---|---|
|1|Practical App Starter|実践的なアプリのスターター|バランス型。`Business` より柔らかい|リファレンス感はやや弱い|
|2|Practical App Starter & Reference|スターター＋参照実装|両方の役割が明示される|やや長い|
|3|Practical App Kit|実践的なアプリのキット|軽い印象。学習者に入りやすい|Starter よりキット感が強い|
|4|Practical Web App Starter|実践的な Web アプリのスターター|Web であることが明確|やや長い|
|5|Practical System Starter|実践的なシステムのスターター|業務系システム感を残せる|System がやや堅い|
|6|Real-World App Starter|現実的なアプリのスターター|「本物を学ぶ」感が強い|実務感は中程度|
|7|Real-World Starter Kit|現実的なスターターキット|#6 の Kit 版。シリーズ向き|Kit 感が強い|
|8|Hands-On App Starter|手を動かすアプリスターター|触って学ぶニュアンス|やや教材寄りに聞こえる|
|9|Applied App Starter|応用的なアプリスターター|学術的な「応用」の響き|やや堅い|
|10|Production-Ready App Starter|本番品質のアプリスターター|実務品質を前面に出せる|学習者には堅すぎる印象|

</details>

<details>
<summary>3.3 Blueprint / Template / Reference 系（#11〜20）</summary>

|#|案|概要|メリット|デメリット|
|---|---|---|---|---|
|11|Blueprint Starter|設計の型・参照実装|短くシリーズ展開しやすい|やや抽象的|
|12|Blueprint App Starter|アプリの設計図スターター|#11 より具体的|やや長い|
|13|Blueprint Starter & Reference|設計図＋参照実装|参照実装であることが明示|やや長い|
|14|App Blueprint|アプリの設計図|ブランド名として短い|Starter 感が弱い|
|15|System Blueprint Starter|システムの設計図スターター|業務系の設計図感|やや堅い|
|16|Reference App Starter|参照アプリのスターター|リファレンス寄り|Starter 感がやや弱い|
|17|Starter & Reference Kit|スターター＋参照キット|汎用的|個性が薄い|
|18|Template App Starter|テンプレートアプリスターター|テンプレート感が明確|実装の独自性が弱く見える|
|19|Canonical App Starter|標準的な型のアプリスターター|「正しい型」の意味|やや専門的|
|20|Pattern App Starter|パターン集スターター|デザインパターン的な響き|具体性に欠ける|

</details>

<details>
<summary>3.4 AI協調開発 系（#21〜30）</summary>

|#|案|概要|メリット|デメリット|
|---|---|---|---|---|
|21|AI-Ready App Starter|AI対応アプリスターター|今っぽい。差別化が明確|時代とともに陳腐化の懸念|
|22|AI-Ready Starter Kit|AI対応スターターキット|#21 より汎用|同上|
|23|AI-Ready Starter & Reference|AI対応スターター＋参照|差別化と参照の両立|やや長い|
|24|AI Pair Starter|AIペア開発スターター|Pair programming 連想|やや造語的|
|25|AI-Assisted App Starter|AI支援アプリスターター|意味が明確|やや長い|
|26|Spec-Driven App Starter|仕様書駆動アプリスターター|設計書駆動の思想に合う|やや堅い|
|27|Docs-to-Code Starter|設計書からコードへ|docs → コードの流れが伝わる|説明的すぎる|
|28|Prompt-Ready App Starter|プロンプト対応アプリスターター|AI 向けであることが直感的|ややニッチ|
|29|Co-Dev App Starter|協調開発アプリスターター|Co-development の響き|造語感がある|
|30|Human-AI App Starter|人とAIのアプリスターター|特徴が明確|長い|

</details>

<details>
<summary>3.5 Craft / Build / Forge 系（#31〜40）</summary>

|#|案|概要|メリット|デメリット|
|---|---|---|---|---|
|31|CraftKit|作りながら学ぶキット|短くブランド化しやすい|何のキットかは補足が必要|
|32|App Craft Starter|アプリを作るスターター|CraftKit より説明的|やや長い|
|33|Craft App Starter & Reference|制作＋参照スターター|#2 の Craft 版|個性は中程度|
|34|BuildKit Starter|組み立てるスターター|組み立てるイメージ|汎用的|
|35|App Forge Starter|アプリを鍛えるスターター|作る・学ぶイメージ|ややゲーム的|
|36|Maker App Starter|メイカー向けアプリスターター|メイカー文化に馴染む|実務感は中程度|
|37|Workshop App Starter|ワークショップ型スターター|工房・勉強会のイメージ|ややイベント的|
|38|Studio App Starter|スタジオ型アプリスターター|制作スタジオ感|クリエイティブ寄りに聞こえる|
|39|Foundry App Starter|鋳造工房スターター|個性的|ややニッチ|
|40|Assembly App Starter|組み立て型スターター|部品を組み立てる印象|具体性に欠ける|

</details>

<details>
<summary>3.6 Docs / Design 系（#41〜48）</summary>

|#|案|概要|メリット|デメリット|
|---|---|---|---|---|
|41|DocCraft Starter|設計書＋制作のスターター|SSOT 方針と一致。独自性が高い|造語感がある|
|42|Doc-Driven App Starter|設計書駆動アプリスターター|設計書駆動が明確|やや堅い|
|43|Design-First App Starter|設計先行アプリスターター|設計先行の思想|やや堅い|
|44|SpecKit Starter|仕様書キットスターター|短く覚えやすい|Spec が伝わりにくい場合がある|
|45|Blueprint Docs Starter|設計図＋設計書スターター|設計書＋型の両方|やや長い|
|46|Living Docs Starter|生きた設計書スターター|継続的改善のコンセプト|やや抽象的|
|47|Docs & Code Starter|設計書とコードのスターター|実態を端的に表現|説明的|
|48|Guided App Starter|ガイド付きアプリスターター|ルール・設計書で導く印象|個性は中程度|

</details>

<details>
<summary>3.7 短いブランド名＋版名方式（#49〜54）</summary>

技術名は版として付ける前提の、短いブランド名候補です。

|#|案|概要|メリット|デメリット|
|---|---|---|---|---|
|49|Starterbase|スターターの基盤|シリーズ展開向き|造語感がある|
|50|Appbase|アプリの基盤|短い|汎用的すぎる|
|51|Refbase|リファレンスの基盤|参照実装感|やや堅い|
|52|Launchpad|打ち上げ台・起点|直感的で覚えやすい|他と被りやすい|
|53|Groundwork|基礎・土台|堅実な印象|やや地味|
|54|Cornerstone|要石・基盤|信頼感がある|やや堅い|

</details>

<details>
<summary>3.8 その他（#55〜60）</summary>

|#|案|概要|メリット|デメリット|
|---|---|---|---|---|
|55|Sensible App Starter|分別のあるアプリスターター|堅実な印象|学習者には堅い|
|56|Solid App Starter|堅実なアプリスターター|信頼感|個性は中程度|
|57|Clearpath App Starter|道筋が見えるスターター|学習の道筋が伝わる|やや長い|
|58|Open App Starter|オープンなアプリスターター|開かれた印象|汎用すぎる|
|59|Everyday App Starter|日常使いのアプリスターター|親しみやすい|実務感は弱い|
|60|Common App Starter|よくある構成のスターター|親しみやすい|平凡に聞こえる可能性|

</details>

---

## 4. 判断基準

候補名を比較する際に重視する観点を記載します。名称の運用ルール（正式名称・リポジトリ名の使い分けなど）は §7 に記載しています。

### 命名の構造方針（比較の前提）

将来の多言語版や他フレームワーク版への展開を見据え、採用する名称は次の **3 つの構成要素** に沿うことを前提とします。候補の比較時は、ブランド名として機能するか、版（Edition）を切り出せるかを確認してください。

```text
構成要素:
  ブランド名      … シリーズ全体の名称（技術名を含めない）
  版・エディション … 技術スタックを示す
  タグライン      … 補足説明・訴求文
```

|要素|役割|例|
|---|---|---|
|ブランド名|シリーズ全体の名称。技術名を含めない|Practical App Starter & Reference|
|版・エディション|技術スタックを示す|Spring Boot Edition|
|タグライン|補足説明・訴求文|AIと一緒に学べる、実践的なWebアプリのスターターキット兼リファレンス|

**正式名称**は上記とは別の第 4 の層ではなく、**ブランド名と版・エディションを組み合わせた対外表記**です（タグラインは含みません）。

```text
正式名称 ＝ ブランド名 ＋ 版・エディション
例: Practical App Starter & Reference — Spring Boot Edition
```

リポジトリ名など、場面ごとの使い分けは §7「名称の運用方針（暫定）」を参照してください。

### 命名に求める要件

|#|要件|説明|
|---|---|---|
|R1|学習者にも開かれた印象|「仕事の人だけ向け」と思われにくいこと|
|R2|実務者にも信頼感がある|実案件の土台・参照実装として使える品質感があること|
|R3|将来のシリーズ展開に対応できる|他言語版・他フレームワーク版を出しても違和感がないこと|
|R4|プロジェクトの独自性が伝わる|AI協調開発、設計書重視など、差別化要素を名前またはタグラインで表現できること|
|R5|覚えやすく、呼びやすい|英語名・日本語での呼び方が自然であること|
|R6|GitHub で使いやすい|リポジトリ名・Organization 名として短く使える派生形があること|

#### 望ましいこと（必須ではない）

- 「スターターキット」と「リファレンス実装」の両方であることが伝わること
- 現行名からの変更理由を説明しやすいこと

### 評価軸（採点用）

候補を比較する際は、以下の 5 項目を **5 段階（1〜5）** で採点し、合計点で比較することを推奨します。

|#|評価軸|1（低）|5（高）のイメージ|
|---|---|---|---|
|E1|学習者が触りたくなるか|堅くて敷居が高い|気軽に試せそう|
|E2|実務で使う人に信頼感があるか|おもちゃ・教材っぽい|そのまま土台にできそう|
|E3|将来の他言語版と相性がよいか|技術名が固定されている|ブランド＋版で展開できる|
|E4|AI協調開発の特徴が伝わるか|特徴が見えない|名前だけで差別化が伝わる|
|E5|覚えやすさ・日本語での呼びやすさ|長くて呼びにくい|短く自然に言える|

#### 採点シート（記入用）

|#|案|E1|E2|E3|E4|E5|合計|メモ|
|---|---|---|---|---|---|---|---|---|
||現行名: Spring Boot Business Starter||||||||
|2|Practical App Starter & Reference||||||||
|11|Blueprint Starter||||||||
|21|AI-Ready App Starter||||||||
|41|DocCraft Starter||||||||
||||||||||
||||||||||
||||||||||

### 検討時の論点

#### 「Business」を名前から外すか

|外す場合の利点|残す場合の利点|
|---|---|
|学習者・興味本位の方が入りやすい|業務システム（CRUD・管理画面）であることが明確|
|`Practical` や `App` で柔らかく表現できる|実務者への訴求が強い|

**論点** : 「業務システム」という意味は、名前ではなくタグラインや README の説明で補えるか。

#### 「& Reference」を付けるか

|付ける場合|付けない場合|
|---|---|
|スターターキットと参照実装の両方であることが明確|短くて覚えやすい|
|README の説明と一致しやすい|タグラインで「リファレンス」を補える|

**論点** : 正式名称は長く、リポジトリ名は短く、という分け方で十分か（運用方針の詳細は §7）。

#### AI協調開発を名前に入れるか

|入れる場合|入れない場合|
|---|---|
|最大の差別化が名前から伝わる|時代とともに陳腐化しにくい|
|検索・発見されやすい可能性|タグライン・README で十分説明できる|

**論点** : ブランド名に入れるか、版やタグラインに回すか。

#### 避けた方がよさそうな方向

|方向|理由|
|---|---|
|Enterprise 系|Business より「大企業・上級者向け」に見えやすい|
|Spring Boot をブランド名に含める|将来の他言語版と相性が悪い|
|Learn / Tutorial 系|実案件品質というコンセプトとズレる|

---

## 5. 判断結果

|項目|内容|
|---|---|
|判断日|2026/07/24|
|ブランド名|Practical App Starter|
|版・エディション名（v1）|Spring Boot Edition|
|タグライン|開発ドキュメントで人とAIの認識を揃える ― 実践的な業務システム開発のスターターキット兼リファレンス|
|正式名称|Practical App Starter — Spring Boot Edition|
|日本語での呼び方|実践アプリスターター|
|リポジトリ名|`practical-app-starter`|
|Organization 名（将来用）|未定（必要になった時点で検討）|
|Maven `groupId`|`io.github.ysmkotm`|
|Maven `artifactId` / 表示名|`practical-app-starter`|
|Java ベースパッケージ|`io.github.ysmkotm.practicalappstarter`|

対外名称は **説明路線**（何のプロジェクトかが名前から分かることを優先）で確定する。短い固有ブランド化（Blueprint / DocCraft 等）は Version 3.x のブランド化検討まで先送りする。

識別子の対応（現行 → 決定後）:

|種類|現行|決定後|
|---|---|---|
|対外名称|Spring Boot Business Starter|Practical App Starter — Spring Boot Edition|
|リポジトリ名|`spring-boot-business-starter`|`practical-app-starter`|
|`groupId`|`com.ysmkotm`|`io.github.ysmkotm`|
|`artifactId`|`businessstarter`|`practical-app-starter`|
|ベースパッケージ|`com.ysmkotm.businessstarter`|`io.github.ysmkotm.practicalappstarter`|
|DB 名（ローカル）|`businessstarter`|当面変更しない（アプリ識別子とは分離）。**後に `practical_app_starter` へ変更**（2026/07/27）|

---

## 6. 判断理由

|項目|内容|
|---|---|
|判断理由|Version 1.0 の主目的は公開実績と初見の分かりやすさである。説明的な `Practical App Starter` は README を開いた瞬間の理解に強く、公開方針（リファレンス／学習・実務の土台）とも矛盾しない。`Business` とブランド内の `Spring Boot` は外し、技術名は Edition に分離する既存の構成方針を維持する。`& Reference` と AI 関連語は名前に入れず、タグライン／README で補う。|
|他の案との比較|短いブランド案（Blueprint / DocCraft / CraftKit 等）は長期シリーズ向きだが、初見説明力が落ちる／衝突リスク（CraftKit・SpecKit）がある。V1 では説明路線を採り、短いブランド化は Version 3.x で再検討する。`Practical App Starter & Reference` は役割明示は良いが冗長なため不採用。|
|タグライン更新理由|「人とAIが同じ開発ドキュメントで進める」は進め方の説明に寄るため、「開発ドキュメントで人とAIの認識を揃える」へ変更した。手段ではなく、同じ前提で開発できるという価値を伝えることを優先する。|
|経緯・補足|ChatGPT・Cursor でブランド路線と説明路線を比較したうえで、説明路線に確定。パッケージ／Maven 座標の詳細方針は [`java_package_and_maven_coordinates.md`](java_package_and_maven_coordinates.md) を正とする。|

#### 日本語での呼び方（参考・確定）

|英語名|日本語呼び方|
|---|---|
|Practical App Starter|実践アプリスターター|

---

## 7. 今後の対応

### 名称の運用方針

> **ステータス** : 決定。正式ルールとして [`docs/02_rules/naming.md`](../02_rules/naming.md) へ移行する（PRG-PRJ-002）。

本セクションは、§4・§5 の構成要素を**どの場面でどう使うか** の運用方針です。`naming.md` へ移行後は、正本を `naming.md` とし、本節は参照または簡潔化します。

#### 名称の種類と役割

|種類|役割|主な利用場面|
|---|---|---|
|ブランド名|§4 の構成要素|ドキュメント本文、対外説明|
|版・エディション|§4 の構成要素|README、正式名称の構成|
|タグライン|§4 の構成要素|README、対外説明|
|正式名称|ブランド名＋版|README タイトル、ドキュメント|
|リポジトリ名|ブランド名の kebab-case|GitHub、クローン URL|
|Maven 座標|`groupId` / `artifactId`|`pom.xml`、将来の公開|
|Java ベースパッケージ|ソースのルートパッケージ|`src/main/java` 以下|
|Organization 名|GitHub Organization（将来）|GitHub|

#### 使い分けの方針

- **正式名称**は README タイトル等で使用する。
- **リポジトリ名**は `practical-app-starter`。
- **Maven**: `groupId` は `io.github.ysmkotm`（GitHub ユーザー配下。独自ドメインなしでも Maven Central 向き）、`artifactId` は `practical-app-starter`。
- **Java ベースパッケージ**は `io.github.ysmkotm.practicalappstarter`（`groupId` ＋ プロジェクト識別子。レイヤー構成は現行どおり）。
- **DB 名**はローカル開発用識別子とする。判断時点では `businessstarter` のまま（V1 では変更しない）としたが、**2026/07/27 に `practical_app_starter` へ変更**した（`artifactId` の snake_case。現行は [`naming.md`](../02_rules/naming.md) §5.1）。
- **Organization 名**は将来検討。

#### 記載例（決定値）

|種類|値|
|---|---|
|ブランド名|Practical App Starter|
|版・エディション|Spring Boot Edition|
|タグライン|開発ドキュメントで人とAIの認識を揃える ― 実践的な業務システム開発のスターターキット兼リファレンス|
|正式名称|Practical App Starter — Spring Boot Edition|
|リポジトリ名|`practical-app-starter`|
|`groupId`|`io.github.ysmkotm`|
|`artifactId`|`practical-app-starter`|
|ベースパッケージ|`io.github.ysmkotm.practicalappstarter`|
|ローカル DB 名|`practical_app_starter`|

#### naming.md への移行（予定）

PRG-PRJ-002 で以下を [`docs/02_rules/naming.md`](../02_rules/naming.md) に正式ルールとして記載する。

- プロジェクト名の種類と役割
- 正式名称・リポジトリ名・Maven 座標・パッケージの使い分け
- 日本語での呼び方

### 決定後の反映箇所

反映は主に [`PRG-PRJ-002`](../01_project/progress.md) で行う。

|#|反映箇所|ファイル・場所|対応|
|---|---|---|---|
|1|README タイトル・概要・タグライン|[`README.md`](../../README.md)|済|
|2|プロジェクト概要|[`docs/01_project/project.md`](../01_project/project.md)|未|
|3|セットアップ手順|[`docs/01_project/setup.md`](../01_project/setup.md)|済（ローカル DB 名 `practical_app_starter` 含む。2026/07/27）|
|4|命名ルール（正式化）|[`docs/02_rules/naming.md`](../02_rules/naming.md)|済（§5.1。ローカル DB 名含む）|
|5|ディレクトリ構成のベースパッケージ|[`docs/02_rules/directory.md`](../02_rules/directory.md)|未|
|6|本ドキュメントの §5・§6・§7|本ファイル|済|
|7|Cursor ルール|[`.cursor/rules/project.mdc`](../../.cursor/rules/project.mdc)|未|
|8|Maven 座標・説明|[`pom.xml`](../../pom.xml)|未|
|9|Java パッケージ移転・起動クラス名|`src/main/java` 以下・Mapper XML・`application.properties`|未|
|10|GitHub リポジトリ名|GitHub 上の設定|未|
|11|その他|`docs` 配下で旧名称を検索して置換|未|

#### 検索コマンド（反映時の確認用）

```bash
rg -i "business starter|Spring Boot Business|businessstarter|com\\.ysmkotm\\.businessstarter" --glob '!*.git/*'
```

### 将来的な見直し

|項目|内容|
|---|---|
|naming.md への移行|本節の運用方針を正式ルールとして `naming.md` に記載する|
|他言語版の追加|ブランド名＋版（Edition）方式で展開できるか確認する|
|短いブランド化|Version 3.x で Blueprint / DocCraft 等への寄せを再検討してよい|
|リポジトリ名の変更|リンク切れ・クローン URL の案内が必要|
|DB 名の変更|2026/07/27 に `practical_app_starter` へ変更済み|

### 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`docs/01_project/project.md`](../01_project/project.md)|プロジェクト概要|
|[`docs/02_rules/naming.md`](../02_rules/naming.md)|命名ルール（決定後の移行先）|
|[`java_package_and_maven_coordinates.md`](java_package_and_maven_coordinates.md)|Maven 座標・Java パッケージの判断（正本）|
|[`public_offering_strategy.md`](public_offering_strategy.md)|公開・提供方針|
