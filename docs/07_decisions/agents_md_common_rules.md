# AIエージェント共通ルールを AGENTS.md に集約する

**Document Version** : 1.0

**更新日** : 2026/07/30

**ステータス** : 保留

---

## 1. 目的

`.cursor/rules` と `CLAUDE.md` に重複していた AI エージェント向け行動ルールを、ツール横断の単一入口へ集約し、更新漏れと記述の乖離を防ぐ。

---

## 2. 背景

Claude Code 導入時に [`CLAUDE.md`](../../CLAUDE.md) を新設し、Cursor 向けの [`.cursor/rules/project.mdc`](../../.cursor/rules/project.mdc) / [`recording.mdc`](../../.cursor/rules/recording.mdc) と同趣旨の行動トリガーを置いた（[`claude_code_role_addition.md`](claude_code_role_addition.md)）。

詳細の正本は既に `docs` 側へ委譲済み（[`cursor_rules_slimming.md`](cursor_rules_slimming.md)）だが、**入口の本文そのもの**がツールごとに二重管理となっていた。

当初案として独自フォルダ `agents/` への集約も検討したが、主要ツールが自動読込する標準パスではないため、入口を消すとルールが効かなくなるリスクがある。

一方、ルートの `AGENTS.md` は Cursor・Claude Code・Codex 等で読込が進んでいるオープンな慣習である。

---

## 3. 検討した案

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. 現状維持|`.cursor/rules` と `CLAUDE.md` にそれぞれ本文を置く|変更コストがない。各ツールの確実な読込パスに本文がある|文言の二重更新が続き、乖離しやすい|
|B. 独自フォルダ `agents/` に共通本文を置き、入口を削除または極薄化|フォルダ名で意図が分かりやすい|主要ツールが `agents/` を自動読込しない。入口削除時は動作支障のリスクが高い|
|C. ルート `AGENTS.md` に共通本文を置き、ツール固有入口は薄いポインタにする|本文は1か所。標準的な配置で読込互換性が高い|Cursor の `.mdc` 内リンクは自動展開されない。`AGENTS.md` の Always 注入に依存すると、未注入時にルールが弱くなる|
|D. `AGENTS.md` に本文を置き、Cursor の `.mdc` にも本文を残す|Claude は `@AGENTS.md`、Cursor は `alwaysApply` 本文で確実|本文の二重管理が残る（同期が必要）|

---

## 4. 判断基準

- 同じ行動トリガー本文を複数ファイルへ書かないこと（理想）
- 詳細の正本は引き続き `docs` 配下とすること（[`cursor_rules_slimming.md`](cursor_rules_slimming.md) の延長）
- ツールが自動読込する標準的な配置を優先し、独自パスに依存しないこと
- **各ツールで行動ルールが確実に効くこと**（DRY より優先）
- Cursor 固有機能（`alwaysApply` / `globs`）は `.cursor/rules` に残せる余地を残すこと

---

## 5. 判断結果

**当面は案A（現状維持）とし、案Cの導入は保留**する。

試行として案Cをリポジトリに反映したが、次の理由で実装差分は元に戻した。

- Cursor 側: `.mdc` の薄いポインタはリンク先を自動展開しない。ルート `AGENTS.md` の Always 注入に依存すると、未注入・Requestable 扱いのときにルールが実質弱くなる
- Claude Code 側: 公式が `CLAUDE.md` 先頭の `@AGENTS.md` 取込を推奨しており、案C自体の支障は相対的に小さい
- 未コミットのうちに巻き戻せるため、確実性を優先して復帰した

独自フォルダ `agents/`（案B）は採用しない。

判断記録（本ドキュメント）のみ残し、`AGENTS.md` 新設・入口薄型化・関連 docs 更新は行わない。

---

## 6. 判断理由

- 入口本文の重複解消自体は価値があるが、Cursor の確実な注入経路は現状 `.cursor/rules` + `alwaysApply: true` の本文である
- Claude Code だけを見れば案C（`@AGENTS.md`）は公式手順に沿う
- ただし本プロジェクトの実装主担当は当面 Cursor であり、Cursor 側のリスクを残したまま共通化すべきではない
- 案Dは確実だが二重管理が残るため、再実施時の候補として残す

---

## 7. 今後の対応

再実施する場合の候補:

1. **案D**: `AGENTS.md` を共通本文にし、Cursor の `.mdc` にも同本文を残す（同期ルールを決める）
2. **案C の再評価**: Cursor でルート `AGENTS.md` が Always として安定注入されることを Settings → Rules 等で確認したうえで、薄いポインタへ移行する
3. Claude Code のみ先に `@AGENTS.md` 化する案は、Cursor と本文が分岐しやすいため、原則セットで検討する

公開セットへの `AGENTS.md` 追加は、導入決定後に行う。

### 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`CLAUDE.md`](../../CLAUDE.md)|Claude Code 向け入口（現状は本文あり）|
|[`.cursor/rules/project.mdc`](../../.cursor/rules/project.mdc)|Cursor 向け入口（現状は本文あり）|
|[`.cursor/rules/recording.mdc`](../../.cursor/rules/recording.mdc)|Cursor 向け記録係（現状は本文あり）|
|[`docs/02_rules/recording.md`](../02_rules/recording.md)|記録ルールの正本|
|[`docs/02_rules/ai.md`](../02_rules/ai.md)|AI協調開発の方針|
|[`cursor_rules_slimming.md`](cursor_rules_slimming.md)|入口は薄く・詳細は docs|
|[`claude_code_role_addition.md`](claude_code_role_addition.md)|Claude Code 役割追加時の判断|
