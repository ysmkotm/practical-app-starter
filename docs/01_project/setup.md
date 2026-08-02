# 開発環境構築

**Document Version** : 1.1

**更新日** : 2026/08/02

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

Java が使えることは、次で確認できます（`21` 系であること）。

```bash
java -version
```

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

DDL・初期データは [`src/main/resources/db/migration`](../../src/main/resources/db/migration) 配下の SQL を、アプリケーション起動時に Flyway が自動適用します。SQL の手動実行は不要です。

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

空のデータベースを用意すれば十分です。テーブル作成と初期データ投入は、§6 のアプリケーション起動時に Flyway が行います。

#### 2. 手動 SQL で作成済みの DB がある場合

以前の手順で SQL を手動実行して作成したローカル DB がある場合は、次のとおりです。

- **保存すべきデータがない環境（標準）**: 既存 DB を削除して再作成し、空の DB に対して Flyway に `V001` ～ `V006` を適用させます。

```sql
DROP DATABASE IF EXISTS practical_app_starter;
CREATE DATABASE practical_app_starter
	ENCODING 'UTF8';
```

- **保存すべきデータがある環境**: 一律に自動 baseline しません。バックアップを取得したうえで、環境ごとに baseline またはデータ移行手順を個別に判断してください。方針の詳細は [`docs/07_decisions/flyway_adoption.md`](../07_decisions/flyway_adoption.md) を参照してください。

#### 3. テーブル作成の確認（起動後）

§6 でアプリケーションを起動したあと、以下のテーブルが作成されていればOKです。

- `common_code`
- `department`
- `employee`
- `flyway_schema_history`（Flyway が適用履歴を記録するテーブル）

---

## 6. アプリケーション起動

Spring Bootアプリケーションを起動します。

起動後、ブラウザからTOP画面へアクセスします。

### 起動方法

#### コマンドライン（Maven Wrapper）

プロジェクトのルートディレクトリで、以下を実行します。

Windows（PowerShell）

```powershell
.\mvnw.cmd spring-boot:run
```

Windows（コマンドプロンプト）では `mvnw.cmd spring-boot:run` でも可。

macOS / Linux

```bash
./mvnw spring-boot:run
```

なお、IDE から起動する場合は、Spring Boot アプリの実行／デバッグを使っても構いません（例：[`.vscode/launch.json`](../../.vscode/launch.json)）。

### 起動確認

コンソールにエラーが出ず、起動ログが表示されれば成功です。  
初回起動時は Flyway が `V001` ～ `V006` を適用します（2回目以降は未適用分のみ）。

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
