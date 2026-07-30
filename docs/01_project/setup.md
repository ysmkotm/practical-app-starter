# 開発環境構築

**Document Version** : 1.0

**更新日** : 2026/07/30

---

## 1. 概要

本プロジェクトをローカル環境で動作させるためのセットアップ手順を説明します。

---

## 2. 前提環境

動作確認環境、および起動までに用意するものは以下のとおりです。インストール手順は各公式サイトを参照してください。

|項目|内容|
|---|---|
|OS|Windows 11（動作確認環境）|
|Java|21|
|PostgreSQL|17|
|Git|最新版|

### 推奨ツール

必須ではありませんが、本プロジェクトでは次を想定しています。

|用途|推奨ツール|代替例|
|---|---|---|
|IDE|Cursor|VS Code 等（Java 拡張が使えるもの）|
|DB 操作|pgAdmin 4|psql 等|

IDE は Cursor を推奨します。`.cursor/rules` により、AI が開発ドキュメントを参照する構成になっています。VS Code 等でも起動・開発は可能ですが、同梱の Cursor 向けルールは自動では効きません。

---

## 3. ソースコード取得

GitHubからプロジェクトをCloneします。

```bash
git clone https://github.com/ysmkotm/practical-app-starter.git
```

Clone後、IDEでプロジェクトを開きます。

---

## 4. IDE設定

Cursor / VS Code 向けに「Extension Pack for Java」等の Java 拡張を導入してください。  
その後、プロジェクトを開き直す、または Java 言語サーバーを再起動します。

### Lombok

本プロジェクトでは Entity および単純な DTO に Lombok（`@Getter` `@Setter`）を使用します。  
ビルド自体は `pom.xml` の依存関係で通りますが、IDE 上で getter / setter 参照に赤線が出ないことを確認してください。

なお、他の IDE（IntelliJ IDEA、Eclipse 等）でも構いません。Java の開発環境と Lombok 対応を確認してください。

---

## 5. データベース設定

PostgreSQLへ接続し、プロジェクト用データベースを作成します。

DDL・初期データは `src/main/resources/db` 配下のSQLを利用します。  
現時点ではFlyway未導入のため、SQLは手動で実行します。

### 接続情報（開発用）

[`src/main/resources/application.properties`](../../src/main/resources/application.properties) の設定は以下のとおりです。

|項目|値|
|---|---|
|ホスト|`localhost`|
|ポート|`5432`|
|データベース名|`practical_app_starter`|
|ユーザー名|`postgres`|
|パスワード|`password`|

ローカル環境のPostgreSQL設定と異なる場合は、[`src/main/resources/application.properties`](../../src/main/resources/application.properties) を環境に合わせて修正してください。

### 手順

#### 1. データベースを作成する

DB クライアント（pgAdmin 4 や psql 等）で、以下のSQLを実行します。

```sql
CREATE DATABASE practical_app_starter
	ENCODING 'UTF8';
```

#### 2. DDL・初期データを投入する

`practical_app_starter` データベースに接続し、以下のSQLを **番号順** に実行します。

|順番|ファイル|内容|
|---|---|---|
|1|[`src/main/resources/db/V001__create_common_code.sql`](../../src/main/resources/db/V001__create_common_code.sql)|共通コードマスタ作成|
|2|[`src/main/resources/db/V002__insert_common_code.sql`](../../src/main/resources/db/V002__insert_common_code.sql)|共通コードマスタの初期データ投入|
|3|[`src/main/resources/db/V003__create_department.sql`](../../src/main/resources/db/V003__create_department.sql)|部署マスタ作成|
|4|[`src/main/resources/db/V004__insert_department.sql`](../../src/main/resources/db/V004__insert_department.sql)|部署マスタの初期データ投入|
|5|[`src/main/resources/db/V005__create_employee.sql`](../../src/main/resources/db/V005__create_employee.sql)|社員マスタ作成|
|6|[`src/main/resources/db/V006__insert_employee.sql`](../../src/main/resources/db/V006__insert_employee.sql)|社員マスタの初期データ投入|

#### 3. テーブル作成を確認する

以下のテーブルが作成されていればOKです。

- `common_code`
- `department`
- `employee`

---

## 6. アプリケーション起動

Spring Bootアプリケーションを起動します。

起動後、ブラウザからTOP画面へアクセスします。

### 起動方法

#### コマンドライン（Maven Wrapper）

プロジェクトのルートディレクトリで、以下を実行します。

Windows（PowerShell / コマンドプロンプト）

```bash
mvnw.cmd spring-boot:run
```

macOS / Linux

```bash
./mvnw spring-boot:run
```

なお、IDE から起動する場合は、Spring Boot アプリの実行／デバッグを使っても構いません（例：[`.vscode/launch.json`](../../.vscode/launch.json)）。

### 起動確認

コンソールにエラーが出ず、起動ログが表示されれば成功です。

デフォルトでは `8080` 番ポートで起動します。  
すでに `8080` を使用している場合は、起動に失敗することがあります。

### アクセスURL

|画面|URL|
|---|---|
|TOP画面|`http://localhost:8080/`|
|社員一覧|`http://localhost:8080/employee`|

### 停止方法

ターミナルで `Ctrl + C` を押します。

---

## 7. 動作確認

以下を確認します。

- アプリケーションが起動すること
- TOP画面が表示されること
- 社員一覧画面が表示できること

---

## 8. 自分のアプリとして使う場合（パッケージ／Maven の置換）

本リポジトリは作者座標のまま配布します。自分のアプリにする場合は、次を利用者自身の値へ置換してください。

|項目|本リポジトリの値|置換の目安|
|---|---|---|
|Maven `groupId`|`io.github.ysmkotm`|利用者の検証可能なネームスペース|
|Maven `artifactId`|`practical-app-starter`|プロジェクト固有の kebab-case|
|Java ベースパッケージ|`io.github.ysmkotm.practicalappstarter`|`groupId` ＋ プロジェクト識別子（小文字・ハイフンなし）|
|起動クラス|`PracticalAppStarterApplication`|プロジェクトに合わせた名称|

置換対象の例：

- [`pom.xml`](../../pom.xml)
- `src/main/java` / `src/test/java` 配下のパッケージ宣言・import
- Mapper XML の `namespace`
- [`application.properties`](../../src/main/resources/application.properties) の `mybatis.type-aliases-package`・`logging.level`
- IDE の起動設定（例：[`.vscode/launch.json`](../../.vscode/launch.json)）

ローカル DB 名（既定は `practical_app_starter`）は必要に応じて別途変更してください。

---

## 9. 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`docs/README.md`](../README.md)|開発ドキュメント索引|
|[`docs/01_project/project.md`](project.md)|プロジェクト概要|
