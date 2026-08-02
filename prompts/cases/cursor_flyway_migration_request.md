# Flyway 導入依頼（Cursor・設定＋初回マイグレーション）

## 使い方

1. 本ファイルを Cursor に渡し、下の依頼文に従って実装してもらう（[`docs/02_rules/ai.md`](../../docs/02_rules/ai.md) §2 の役割分担どおり、実装は Cursor）
2. 完了後、変更内容・確定済みの既存 DB 移行方針に沿っていることを Cursor に報告させ、人間が確認する
3. 動作確認は別依頼（[`cursor_flyway_migration_verify.md`](cursor_flyway_migration_verify.md)）に分けて行う。一気にやらせず、ここでは設定・移行までを完了させる
4. 動作確認後、Cursor の提示するコミット準備案（[`docs/02_rules/git.md`](../../docs/02_rules/git.md) §7）に沿って人間がコミットする
5. `docs/01_project/progress.md` の PRG-CMN-004、`README.md`「今後の予定」、`docs/01_project/project.md` §4 は完了後に更新する（Claude Code でも下書き可）

---

## 依頼文

```text
# Flyway 導入（DBマイグレーション基盤）実装依頼

あなたは本リポジトリのコードを編集できる実装担当です。PRG-CMN-004（Flyway導入）を進めてください。

## 背景

- 現在、DBのテーブル作成・初期データ投入は `src/main/resources/db/` 配下のSQLファイル（`V001__create_common_code.sql`〜`V006__insert_employee.sql`）を、`docs/01_project/setup.md` §5の手順に沿って手動で番号順に実行する運用になっている
- 初見ユーザー向けレビューで、この手動SQL実行がセットアップの離脱リスクとして指摘された
- `docs/01_project/project.md` §4「Version 1.x」の技術対応に元々Flywayが挙がっていたが、今回優先度を引き上げて前倒しで着手する

## 目的

Flywayを導入し、DBスキーマ作成・初期データ投入をアプリ起動時の自動マイグレーションに置き換える。

## 現状（着手前に確認すること）

- 既存SQL：`src/main/resources/db/V001__create_common_code.sql` 〜 `V006__insert_employee.sql`（採番・説明ともFlywayの命名規則 `V<バージョン>__説明.sql` に近い状態）
- DB接続設定：`src/main/resources/application.properties`（ホスト・DB名・ユーザー等は `docs/01_project/setup.md` §5参照）
- `pom.xml` を確認済みで、現状は Flyway 依存が入っていない
- Flywayを使ったことがない前提のため、設定は保守的に（デフォルト挙動を優先し、過度なカスタマイズは避ける）
- 手動 SQL・Flyway・Liquibase の比較は完了し、Flyway を採用済み（`docs/07_decisions/flyway_adoption.md`）

## 完成イメージ

- `pom.xml` に `flyway-core`（および使用するDBに応じたFlyway用モジュール）を追加
- 既存6ファイルは内容とファイル名を原則維持し、Flywayの標準配置 `src/main/resources/db/migration/` へ移動する（変更が必要な場合は実施前に理由を説明して確認する）
- `application.properties` はデフォルト挙動を優先し、必要な Flyway 設定だけを追加する（不要なデフォルト値の明記は避ける）
- `spring.flyway.baseline-on-migrate=true` は既定設定として有効にしない
- **既にsetup.mdの手動手順でDBを作成済みの環境**は、保存すべきデータがなければ DB を再作成する手順を標準とし、`docs/01_project/setup.md` に明記する
- 保存すべきデータがある環境は一律に自動 baseline せず、バックアップ後に個別の baseline またはデータ移行判断が必要であることを補足する
- `docs/01_project/setup.md` §5の手動SQL実行手順を、Flyway導入後の手順に書き換える
- 変更範囲と、確定済みの既存 DB 移行方針への対応内容を人間が確認できる単位でまとめて報告する

## 制約事項

- 実装は本依頼のみに閉じる（Spring Security・Docker等、他のVersion 1.x項目には手を広げない）
- 実際にアプリを起動しての動作確認（新規DBでのマイグレーション適用確認）は別依頼で行うため、本依頼には含めない
- 既存の社員CRUD機能（EMP001/EMP002）の動作を変えない
- Flyway 採用と既存 DB の移行方針は `docs/07_decisions/flyway_adoption.md` で決定済みのため、別方針へ変更せず、不明点があれば実装前に確認する
- 設定・命名は `docs/02_rules/coding.md`・`docs/02_rules/naming.md`・`docs/02_rules/directory.md` に従う
- 変更完了時は `docs/02_rules/git.md` §7 のコミット準備案を提示する（コミット自体は人間が行う）

## 関連ドキュメント

- `docs/01_project/project.md` §4（Version 1.x 技術対応）
- `docs/01_project/setup.md` §5（現行の手動DB構築手順）
- `docs/01_project/progress.md`（PRG-CMN-004）
- `docs/07_decisions/flyway_adoption.md`（採用理由・既存 DB の移行方針）
- `docs/02_rules/ai.md`（役割分担・品質確認方針）
```
