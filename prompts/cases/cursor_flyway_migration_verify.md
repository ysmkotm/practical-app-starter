# Flyway 動作確認依頼（Cursor）

## 使い方

1. [`cursor_flyway_migration_request.md`](cursor_flyway_migration_request.md)（設定＋初回マイグレーション）の実装完了・人間確認が終わってから、本ファイルを Cursor に渡す
2. 確認結果を人間が確認する
3. 問題なければ、Cursor の提示するコミット準備案（[`docs/02_rules/git.md`](../../docs/02_rules/git.md) §7）に沿って人間がコミットする
4. `docs/01_project/progress.md` の PRG-CMN-004、`README.md`「今後の予定」、`docs/01_project/project.md` §4 は完了後に更新する（Claude Code でも下書き可）

---

## 依頼文

```text
# Flyway 導入（DBマイグレーション基盤）動作確認依頼

あなたは本リポジトリのコードを編集できる実装担当です。PRG-CMN-004（Flyway導入）の動作確認を行ってください。

## 背景

- Flyway導入の設定・初回マイグレーション実装（pom.xml追加、既存SQLの`src/main/resources/db/migration/`への移動、application.properties設定）は完了済み
- 実装内容がFlyway採用の判断（`docs/07_decisions/flyway_adoption.md`）に沿っていることは人間が確認済み
- 残っているのは、実際にアプリを起動して自動マイグレーションが機能するかどうかの動作確認

## 目的

新規（またはクリーンな）DB環境でアプリを起動し、Flywayによる自動マイグレーションが正しく適用されることを確認する。

## 現状（着手前に確認すること）

- 既にsetup.mdの手動手順でDBを作成済みの環境は、保存すべきデータがなければDBを再作成してから確認する（Flyway導入後のsetup.md §5の手順に従う）
- `spring.flyway.baseline-on-migrate=true` は設定されていないこと

## 完成イメージ

- クリーンなDBに対して `spring-boot:run` 等でアプリを起動し、Flywayのマイグレーションが自動適用されることを確認する
- マイグレーション適用後、既存の社員CRUD機能（EMP001/EMP002）が従来どおり動作することを確認する
- 確認結果（適用されたマイグレーションのバージョン一覧、起動ログの該当箇所、社員CRUDの動作結果）を人間が確認できる形でまとめて報告する
- 確認中に問題が見つかった場合は、その場で修正せず内容を報告する

## 制約事項

- 動作確認の範囲に閉じる（設定変更が必要になった場合は理由を説明し、実施前に確認する）
- Flyway 採用と既存 DB の移行方針は `docs/07_decisions/flyway_adoption.md` で決定済みのため、別方針へ変更しない

## 関連ドキュメント

- `docs/01_project/setup.md` §5（Flyway導入後のDB構築手順）
- `docs/01_project/progress.md`（PRG-CMN-004）
- `docs/07_decisions/flyway_adoption.md`（採用理由・既存 DB の移行方針）
- `docs/02_rules/ai.md`（役割分担・品質確認方針）
```
