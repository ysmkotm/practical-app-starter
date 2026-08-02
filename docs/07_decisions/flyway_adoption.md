# DB マイグレーション基盤への Flyway 採用

**Document Version** : 1.0

**更新日** : 2026/08/02

**ステータス** : 決定

---

## 1. 目的

Version 1.x の DB マイグレーション基盤として、手動 SQL 実行を継続するか、Flyway または Liquibase を導入するかを決定する。

---

## 2. 背景

- 現在は [`docs/01_project/setup.md`](../01_project/setup.md) に従い、6 本の SQL ファイルを利用者が番号順に手動実行している。
- 初見視点レビューで、手動実行がセットアップ時の離脱リスクとして指摘され、PRG-CMN-004 の優先度を引き上げた。
- 既存 SQL は [`docs/02_rules/db.md`](../02_rules/db.md) §6 に従い、Flyway の命名形式を見据えた `V001__...` ～ `V006__...` で管理している。
- 本プロジェクトは Spring Boot 3.5、MyBatis、PostgreSQL を使用し、SQL を明示的な成果物として扱っている。
- Spring Boot は Flyway と Liquibase の起動時実行をサポートしている。

---

## 3. 検討した案

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. 手動 SQL 実行を継続|利用者が SQL を番号順に実行する|依存追加や仕組みの学習が不要|実行漏れ・順序間違いが起こり得る。適用履歴を管理できず、セットアップの手間が残る|
|B. Flyway を導入（採用）|バージョン付き SQL を起動時に自動適用する|既存の SQL・命名方針をほぼそのまま利用できる。構成が単純で、適用履歴を管理できる|既存 DB の初回移行方針が必要。適用済み SQL は原則変更できない|
|C. Liquibase を導入|changelog と changeset で DB 変更を管理する|XML・YAML・JSON・SQL を選べ、条件分岐やロールバックを扱いやすい|既存 SQL を changelog として再構成する必要がある。現状の要件に対して管理要素が増える|

---

## 4. 判断基準

- 初見利用者のセットアップ手順を簡潔にできること
- 既存の SQL と DB 設計ルールを活かせること
- PostgreSQL の変更履歴を安全かつ再現可能に管理できること
- 初学者と AI の双方が理解しやすい、過度に複雑でない構成であること
- 現在必要な機能に対し、導入・保守コストが妥当であること

---

## 5. 判断結果

**案 B の Flyway を採用する。**

### 導入方針

- Spring Boot の自動構成を利用し、アプリケーション起動時に migration を実行する。
- PostgreSQL 対応に必要な Flyway の依存関係を追加する。
- 既存 6 ファイルは、原則として内容とファイル名を維持したまま、標準配置である `src/main/resources/db/migration` へ移動する。
- 今後の DB 変更は新しいバージョンの SQL ファイルとして追加し、適用済みファイルは原則変更しない。

### 既存 DB の移行方針

- `spring.flyway.baseline-on-migrate=true` は既定設定として有効にしない。
- 保存すべきデータがない既存のローカル開発 DB は、導入時に DB を再作成し、Flyway に `V001` ～ `V006` を適用させる方法を標準手順とする。
- 保存すべきデータがある環境は一律に自動 baseline せず、バックアップを取得したうえで、環境ごとに baseline またはデータ移行手順を個別に判断する。

---

## 6. 判断理由

- 既存 SQL とファイル名がすでに Flyway を見据えた構成であり、移行コストが最も小さい。
- SQL を明示的に管理する現在の MyBatis・PostgreSQL 構成と相性がよく、スターターキットの分かりやすさを維持できる。
- 手動 SQL 実行を廃止することで、初見利用者の作業量と実行順序ミスのリスクを減らせる。
- Liquibase の高度な changelog、条件分岐、ロールバック機能を必要とする要件は現時点でなく、管理要素の増加に見合う利点が少ない。
- `baseline-on-migrate` の常時有効化は、誤った接続先の非空スキーマを正しい初期状態として扱う安全上のリスクがある。公開直後で移行対象が限定的な現在は、DB 再作成を標準とする方が挙動を確認しやすい。

---

## 7. 今後の対応

- PRG-CMN-004 として、Cursor へ `private/prompts/cursor_flyway_migration_request.md` を渡して実装する。
- 空の DB への初回 migration、2 回目以降の起動で再適用されないこと、既存 CRUD の動作が変わらないことを確認する。
- 実装時に [`docs/01_project/setup.md`](../01_project/setup.md) と [`docs/02_rules/db.md`](../02_rules/db.md) を Flyway 運用へ更新する。
- 複数 DB 製品への対応や高度なロールバック管理が必要になった場合は、Liquibase を含めて再検討する。

### 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`docs/01_project/progress.md`](../01_project/progress.md)|PRG-CMN-004 の作業状態|
|[`docs/01_project/setup.md`](../01_project/setup.md)|現行の DB セットアップ手順|
|[`docs/02_rules/db.md`](../02_rules/db.md)|SQL ファイル管理ルール|
|[`docs/02_rules/ai.md`](../02_rules/ai.md)|AI の役割分担|
|[Spring Boot Database Initialization](https://docs.spring.io/spring-boot/3.5/how-to/data-initialization.html)|Flyway・Liquibase の起動時実行と標準配置|
|[Flyway Baseline On Migrate Setting](https://documentation.red-gate.com/flyway/reference/configuration/flyway-namespace/flyway-baseline-on-migrate-setting)|`baselineOnMigrate` の挙動と注意点|
|[Liquibase rollback](https://docs.liquibase.com/oss/reference-guide-4-33/init-update-and-rollback-commands/rollback)|changelog 形式ごとの rollback 方針|
