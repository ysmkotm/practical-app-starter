# コーディングルール

**Document Version** : 1.0

**更新日** : 2026/07/30

---

## 1. 基本ルール

- インデントはタブを使用する。
- IDEで自動整形する場合も、インデント・文字コード・改行コードのルールに合わせる。
- 空行は、役割や意味のある区切りを表すために使用する。不要な空行は入れない。
- クラス内の空行は次のとおりとする。
  - フィールド・コンストラクタ・メソッドなど、役割の異なるクラスメンバー同士の間は1行空ける。
  - メソッド同士の間は1行空ける。
  - 最後のクラスメンバー（メソッド・フィールド等）の直後と、クラスを閉じる `}` の間には空行を入れない。
- 短く読みやすい処理は、不要に複数行へ分割しない。ただし、行が長くなる場合や構造を明確にできる場合は、可読性を優先して改行する。
- 文字コードはUTF-8とする。
- 改行コードはLFとする。
- Thymeleafの共通部品は `th:fragment` と `th:replace` を使用する。
- IDE上で警告が発生しないコードを基本とする。

---

## 2. コメント

- publicメソッドにはJavaDocを記述する。クラスやメソッドの役割を説明する。
- 処理内のコメントは、必要に応じて以下を補足する。
  - 処理のまとまり
  - コードだけでは分かりにくい意図
  - 業務上の判断理由
  - エラー時に通常処理を行わない理由
- コードを読めば分かる内容を逐語的に説明するコメントは、原則として避ける。

---

## 3. レイヤー構成と責務

本プロジェクトでは、**Controller → Service → Mapper** のレイヤー構成を基本とします。

|レイヤー|責務|
|---|---|
|Controller|リクエスト受付、Form バインド、Service 呼び出し、Model 設定、画面遷移|
|Service|業務処理、Form と Entity の変換、Mapper 呼び出し|
|Mapper|データベースアクセス（SQL の実行）|

- Service のメソッドは、処理内容が分かる単位に分ける。複数の取得処理を1メソッドにまとめない。
- SQL は Mapper インタフェースまたは Mapper XML に記述し、Service には記述しない。
- 画面固有の処理と、複数画面で再利用する共通処理を同一メソッドに混在させない。

---

## 4. データクラスの使い分け

本プロジェクトでは、データ保持クラスの役割を以下のとおり統一します。配置先は [`docs/02_rules/directory.md`](directory.md) §2 を参照してください。採用背景は [`docs/07_decisions/entity_form_dto_roles.md`](../07_decisions/entity_form_dto_roles.md) を参照してください。Lombok の利用範囲は §6 を参照してください。

### Entity

- 1つの Entity は1つのテーブルに対応させる。
- テーブル定義書を正としてプロパティを定義する（[`docs/04_db/table/`](../04_db/table/) 参照）。
- フィールドは、原則としてテーブル定義書のカラム定義順に並べる。
- クラスコメント（JavaDoc）には、対応するテーブルの論理名と物理名を記載する（例：`社員マスタ（employee）に対応する Entity です。`）。
- フィールド名で意味が十分に分かる場合は、各フィールドへの論理名コメントや JavaDoc は付けない。
- JOIN で取得する他テーブルの名称や、画面表示専用項目は持たせない。
- 外部キーは関連 Entity ではなく、参照先主キーの値（例：`departmentId`）として保持する。DB 上の外部キー方針は [`docs/02_rules/db.md`](db.md) §2 を参照する。
- Lombok の利用範囲は §6 を参照する。
- `equals`・`hashCode`・`toString` の実装、`Serializable` の実装は、明確な必要性がない限り追加しない。

### Form

- 検索・登録・更新ごとに Form を作成する。
- Thymeleaf 画面からの入力値は Form で受け取る。Entity を画面入力として直接使用しない。
- Bean Validation は Form に定義する（§5 参照）。
- getter / setter は、画面項目・バリデーション・Mapper 連携など実際の用途に応じて必要なものだけを実装する。ソース上で直接呼び出しがなくても、Spring MVC（`@ModelAttribute` 等）・Thymeleaf（`th:field` 等）・MyBatis が JavaBeans 規約で暗黙に利用する場合があるため、削除前に利用経路を確認する。
- 画面にバインドする項目には、リクエストのバインド（setter）と入力値の再表示（getter）のために、原則として getter / setter の両方を実装する。
- 実装の詳細（Lombok 非使用、入力変換等）は §5 を参照する。

### DTO

- Entity 単体では表現できないデータを保持する。
- 主な用途は、複数テーブルの JOIN 結果、一覧表示用データ、詳細表示用データ、集計結果。
- MyBatis の JOIN 結果は DTO へ直接マッピングしてよい。
- 画面表示用 DTO など、用途に必要な最小限の機能のみを持たせる。入力変換や業務ロジックは持たせない。
- クラスコメント（JavaDoc）には、DTO の用途（どの画面・どのデータか）が分かる説明を記載する。
- Lombok の利用範囲、Java `record` の採用可否は §6 を参照する。
- `equals`・`hashCode`・`toString` は、`Set` への格納、重複判定、比較、ログ出力など明確な必要性がある場合のみ追加する。

### Request DTO・Response DTO

- Version 1（Thymeleaf 画面）では作成しない。
- REST API を実装する段階で導入を検討する。

### `model` パッケージ

- Java クラスの配置先として `model` パッケージは使用しない。

### 変換ルール

- Form から Entity への変換は、原則として Service で行う。
- 検索 Form と Mapper の検索条件が一致している間は、Mapper の引数として Form を使用してよい（§9 参照）。
- 将来、画面固有項目や検索条件が増えて Form と SQL の責務が分かれた場合は、検索条件クラスへの分離を検討する。

---

## 5. 入力・バリデーション

- 検索・登録・更新の入力値には Bean Validation を使用する。定義場所は Form（§4 参照）。
- 前後空白は Form の setter 等で除去する。
- 除去後に空文字となった値は、未入力（`null`）として扱う。
- バリデーションエラー時は、対象処理（検索・登録・更新・削除等）を実行しない。
- バリデーションエラー後も、入力値および画面表示に必要な選択肢（プルダウン等）を保持する。

エラーメッセージの表示位置・デザイン等、画面 UI の詳細は [`docs/02_rules/ui.md`](ui.md) §7 を参照してください。Version 1 時点では UI 詳細は未定義です。

### Form の実装

- Form は Lombok を使用せず、手書きの getter / setter で実装する（§6 参照）。
- 文字列項目の前後空白除去・空文字の `null` 化は、setter 内で行う。

---

## 6. Lombok 利用ルール

採用背景は [`docs/07_decisions/lombok_limited_adoption.md`](../07_decisions/lombok_limited_adoption.md) を参照してください。

### 基本方針

- Lombok は **限定導入** とする。ボイラープレート削減のためだけに広く使わない。
- 必要最小限の機能を、用途に応じて明示的に付与する。Lombok も `@Getter`・`@Setter` など、必要なアノテーションのみを使用する。
- 画面入力の受け取りや、trim・空文字の `null` 化などの入力変換は Form の責務とし、**Form には Lombok を適用しない**（§5 参照）。
- DTO に入力変換や業務ロジックを持たせる構成は、現時点では原則として想定しない。
- AI（Cursor 等）がコード生成する際も、本節のルールに従う。

### 利用可能な対象とアノテーション

|対象|許可|備考|
|---|---|---|
|Entity|`@Getter` `@Setter` のみ|1 テーブル対応（§4 参照）|
|DTO（単純な MyBatis マッピング用）|`@Getter` `@Setter` のみ|下記「DTO の判断基準」参照|
|Form|なし|手書きの getter / setter を維持（§5 参照）|
|Controller / Service / Mapper 等|なし|現時点では Lombok を使用しない|

### 禁止事項

- `@Data` は使用しない。複数のアノテーションを一括で付与するため、クラスが持つべき機能がコード上で分かりにくく、必要最小限を明示的に付与する方針に反する。あわせて `equals`・`hashCode`・`toString` など、用途に不要なメソッドまで生成する場合がある。
- `@Builder`・`@EqualsAndHashCode`・`@ToString`・`@AllArgsConstructor` は使用しない。
- Form およびその他のクラスへ、上記以外の Lombok アノテーション（`@Slf4j` 等）を安易に追加しない。必要になった場合は別途判断記録を残してから検討する。

### MyBatis との整合

- Entity および MyBatis へマッピングする DTO は、**引数なしコンストラクタ（no-args constructor）** を維持する。
- `@Getter` `@Setter` のみであれば、暗黙の no-args コンストラクタは維持される。
- `@AllArgsConstructor` は no-args コンストラクタを失うため、使用しない（禁止事項）。

### DTO の判断基準

|条件|方針|
|---|---|
|MyBatis の検索結果などを保持する単純な DTO|Entity と同様に `@Getter` `@Setter` を使用してよい|
|画面入力の受け取りや、trim・空文字の `null` 化などの入力変換が必要|Form の責務とする。DTO ではなく Form を使用し、手書きの getter / setter を維持する（§5 参照）|
|不変 DTO や Java `record` の利用|Version 1 では見送り。必要になった段階で別途判断記録を残して検討する|
|DTO に入力変換や業務ロジックを持たせる構成|現時点では原則として想定しない|

---

## 7. Controller 実装ルール

### GET

- 画面を表示する。

### POST

- 登録・更新・削除を行う。

### メソッド命名

#### GET

- `showList()`
- `showCreate()`
- `showDetail()`
- `showEdit()`

#### POST

- `create()`
- `update()`
- `delete()`

### メソッドの並び順

- GET（画面表示）を POST（登録・更新・削除）より前に配置する。
- GET 内は、一覧 → 登録 → 詳細 → 編集の順を基本とする。
- POST 内は、登録 → 更新 → 削除の順を基本とする。

### 引数の順番

1. URL・リクエスト情報
   - @PathVariable
   - @RequestParam

2. Form
   - @Validated Form

3. BindingResult

4. Model

### 責務

- リクエストの受付、Form へのバインド、Service の呼び出し、Model への設定、テンプレート名の返却を行う。
- 業務処理・SQL は Controller に記述しない。

### 依存性注入

- 原則としてコンストラクタインジェクションを使用する。
- コンストラクタが1つの場合、`@Autowired` は省略する。

### URLマッピング

- 共通する URL プレフィックスがある場合は、クラスレベルの `@RequestMapping` へ定義する。

### パス変数

- パス変数名には、対象が分かる名称（`employeeId`、`departmentId` 等）を使用する（[`docs/02_rules/naming.md`](naming.md) §3 参照）。

### 実装の進め方

- 未実装のメソッドをコメントアウトした状態で残すことは、原則として避ける（[`docs/02_rules/development.md`](development.md) §6 参照）。

### 一覧画面（GET）

一覧画面の UI 方針（DataTables 利用等）は [`docs/02_rules/ui.md`](ui.md) §3 を参照してください。

- 一覧表示・検索は GET で行う。
- 検索条件は Form クラスで受け取る。
- Version 1 では、Mapper は検索条件に合致する **全件** を返却する。SQL に `LIMIT` / `OFFSET` 等のページング条件は付けない。
- ページング・列ソートは DataTables のクライアントサイド機能に任せる。
- バリデーションエラー時は検索を実行しない（§5 参照）。ただし、画面表示に必要な選択肢（プルダウン等）は取得して Model に設定する。

---

## 8. Service 実装ルール

### メソッド命名

prefix の意味は [`docs/02_rules/naming.md`](naming.md) §8 を参照してください。

- 業務上の意味が分かる名前にする（例：`searchEmployees`、`findDepartments`）。
- 複数条件による一覧検索は `search` + 対象名（複数形）とする（例：`searchEmployees`）。

### メソッドの並び順

- 参照系（`search` / `find`）を更新系（`create` / `update` / `delete` 等）より前に配置する。
- 参照系内では、一覧・検索を単一取得より前に配置する。
- 当該機能の主要メソッドを先に、複数画面で共有する補助的な取得メソッドを後に配置する。

---

## 9. Mapper 実装ルール

MyBatis の Mapper に関する実装ルールです。

### メソッド命名

prefix の意味は [`docs/02_rules/naming.md`](naming.md) §8 を参照してください。

- SQL の条件が分かる名前にする（例：`findByCodeType`、`findDepartments`）。論理削除の扱いは [`docs/02_rules/naming.md`](naming.md) §8 を参照する。
- 複数条件による一覧検索は `search` + 対象名（複数形）とする（例：`searchEmployees`）。
- Mapper インタフェースが1エンティティ専用であっても、`search` のような対象不明の短い名前は避ける。
- Service と Mapper で名前を完全一致させる必要はない。Service はドメイン語、Mapper は SQL 条件を表す名称でもよい。

### メソッドの並び順

- 操作種別順（参照系 → 更新系）を基本とする。
- 参照系内では、一覧・検索を単一取得より前に配置する。
- Mapper XML の SQL 定義順は Mapper インタフェースと一致させる。

### 実装

- Mapper の登録には `@MapperScan` を使用する。各 Mapper インタフェースへ `@Mapper` を重複して付与しない。
- SQL のカラム名と Java プロパティ名の対応は、`resultMap` 等で明示する。
- Entity 単体で表現できない検索結果は DTO へマッピングする（§4 参照）。
- 共通コードマスタを JOIN する場合は、コード値（`code`）だけでなくコード種別（`code_type`）も条件に含める（[`docs/02_rules/db.md`](db.md) §4 参照）。
- Mapper の検索条件と検索 Form が同一である場合は、Form を Mapper の引数として使用してよい（§4 参照）。
- INNER JOIN / LEFT JOIN の選択は業務仕様に従う。参照先が欠損・論理削除された場合も主データを表示する必要があるかを、画面設計書または判断記録で決める。
- `resultMap` の `type` は、`mybatis.type-aliases-package`（[`application.properties`](../../src/main/resources/application.properties)）に登録したパッケージのクラスについて **型エイリアス**（短いクラス名）を使用する（例：`Department`、`EmployeeListItemDto`）。登録対象外のクラスは完全修飾名を使用する。本プロジェクトでは `entity` と `dto` を登録対象とする。採用背景は [`docs/07_decisions/mybatis_resultmap_type_aliases.md`](../07_decisions/mybatis_resultmap_type_aliases.md) を参照する。
- `resultMap` の `<id>` は次のとおり設定する。
  - Entity：対応テーブルの主キーを `<id>` とする。
  - DTO：結果セット上で1行を一意に識別するカラムを `<id>` とする。JOIN 結果の一覧では、通常は主テーブルの主キーを用いる。将来の `association` / `collection` でも同一定義とする。
- マスタ一覧取得（プルダウン選択肢等）の `ORDER BY` では、`display_order` 等の表示順に加え、当該テーブルで UNIQUE 制約のある識別コード（`_code` 等）を第2ソートキーとし、並び順を安定させる。該当カラムがない場合は主キー（`_id`）とする。
- LIKE 条件における `%`・`_` 等のワイルドカード文字の扱い（許容するか、エスケープするか）は、画面ごとの検索仕様として画面設計書で定める。共通ルール化は [`docs/01_project/backlog.md`](../01_project/backlog.md) の BLG-CMN-007 を参照する。

Mapper XML の配置は [`docs/02_rules/directory.md`](directory.md) および `src/main/resources/mapper` を参照してください。

---

## 10. 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`docs/02_rules/naming.md`](naming.md)|命名規則|
|[`docs/02_rules/directory.md`](directory.md)|ディレクトリ構成ルール|
|[`docs/02_rules/ui.md`](ui.md)|UI設計ルール|
|[`docs/02_rules/db.md`](db.md)|DB設計ルール（共通コード）|
|[`docs/07_decisions/lombok_limited_adoption.md`](../07_decisions/lombok_limited_adoption.md)|Lombok 限定導入の判断記録|
|[`docs/07_decisions/mybatis_resultmap_type_aliases.md`](../07_decisions/mybatis_resultmap_type_aliases.md)|MyBatis resultMap type の型エイリアス方針|
