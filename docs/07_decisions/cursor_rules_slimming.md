# Cursorルールのスリム化

**Document Version** : 1.0

**更新日** : 2026/07/30

**ステータス** : 決定

---

## 1. 目的

`.cursor/rules` 配下（`project.mdc` / `recording.mdc`）の記述量を、実務で困らない範囲まで削減し、常時読み込まれるルールとして軽量に保つ。

---

## 2. 背景

`project.mdc` は当初、`docs` 配下の各フォルダの役割を個別に列挙しており、`docs/README.md`（開発ドキュメント索引）と内容が重複していた。

`recording.mdc` も、記録種別ごとに「保存先」と「テンプレート参照」を別々の章（§3, §6〜8）に分けて記載しており、同じ分類の情報が離れた場所に存在していた。

`.cursor/rules` 配下は Cursor が毎回読み込む行動ルールであるため、`docs` 側の詳細と重複した記述が増えるほど、更新漏れや肥大化のリスクが高まるという課題があった。

---

## 3. 検討した案

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. 現状維持|`project.mdc` にフォルダ説明を残し、`recording.mdc` も章立てを維持する|変更コストがない|`docs/README.md` との重複が残り、記述が増えるたびに二重更新が必要|
|B. `docs` 側の記述を `.cursor/rules` へ集約する|索引やテンプレート内容を `.cursor/rules` に転記する|参照が1ファイルで完結する|`.cursor/rules` が肥大化し、`docs` 側の更新に追従できなくなる|
|C. `.cursor/rules` は入口・行動指針のみに絞り、詳細は `docs` やテンプレートに委譲する|重複部分を削除し、参照リンクに置き換える|`.cursor/rules` が軽量に保たれ、正本（`docs`）が一つになる|参照を辿る一手間が発生する|

---

## 4. 判断基準

- `.cursor/rules` は常時読み込まれるため、軽量さを優先すること
- 同じ情報を `docs` と `.cursor/rules` の両方に書かないこと
- 詳細の正本は `docs` 配下（索引・ルール・テンプレート）とすること
- 記録種別のように関連する情報（保存先・テンプレート）は近くにまとめること

---

## 5. 判断結果

**案Cを採用**する。

具体的には以下を実施した。

- `project.mdc`：`docs` 配下の個別フォルダ説明を削除し、`README.md` / `docs/README.md` への参照（「参照先」）に置き換え。開発ドキュメントの定義とSSOTの記述を冒頭文に統合
- `recording.mdc`：記録種別ごとの章（§3, §6〜8）を統合し、「判断記録」「AI協調開発ログ」「販売・教材化候補」それぞれの直下に保存先とテンプレート参照をまとめた。また、`.cursor/rules` の重複禁止を説明する自己言及的な一文（本ファイルの記載方針）を削除し、`project.mdc` 側の同趣旨の記述に委譲

---

## 6. 判断理由

- `docs/README.md` が開発ドキュメント索引として正本を持っているため、`project.mdc` に同じ内容を書くと二重管理になる
- `.cursor/rules` は「AIが常に踏まえる行動指針」であり、詳細な一覧や記載例までは不要
- 記録種別ごとに保存先とテンプレートをまとめることで、実際に記録を作成する際の参照性が上がる
- 案Aは重複が残り続け、案Bは `.cursor/rules` が索引化・肥大化してしまう

---

## 7. 今後の対応

- `.cursor/rules` に新しい内容を追記する際は、まず `docs` 配下に置けないかを検討し、置けない場合のみ `.cursor/rules` に短く追記する
- `project.mdc` や `recording.mdc` が再び煩雑になったと感じた場合は、本判断記録の判断基準（§4）に立ち返って見直す

### 追記（2026/07/25）：記録ルールの docs 正本化（BLG-PRJ-010）

公開範囲の確定（[`public_offering_strategy.md`](public_offering_strategy.md) §5.2）を受け、記録種別ごとの詳細（分類・保存先・公開／非公開・価値判定・提案形式）を [`docs/02_rules/recording.md`](../02_rules/recording.md) へ移し、[`.cursor/rules/recording.mdc`](../../.cursor/rules/recording.mdc) は行動トリガーのみにスリム化した。案 C（入口は `.cursor/rules`、詳細は docs）の続きであり、本判断の基準（§4）に沿う。

### 関連ドキュメント

- [`.cursor/rules/project.mdc`](../../.cursor/rules/project.mdc)
- [`.cursor/rules/recording.mdc`](../../.cursor/rules/recording.mdc)
- [`docs/02_rules/recording.md`](../02_rules/recording.md)
- [`docs/README.md`](../README.md)
- [`public_offering_strategy.md`](public_offering_strategy.md)
