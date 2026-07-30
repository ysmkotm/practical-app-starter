# 公開・提供方針（OSS／収益化／ポートフォリオ）

**Document Version** : 1.0

**更新日** : 2026/07/30

**ステータス** : 決定（2026/07/27 更新。§5.6 に認知ターゲット仮確定を追記。改訂点は §5 を参照）

---

## 1. 目的

Version 1.0 公開に先立ち、本プロジェクトの主目的の優先順位、GitHub 等への公開形態、収益化の時期と形態、ライセンス選定への制約を定める。

---

## 2. 背景

当初は副業・販売も視野に開発を開始したが、成長に伴い GitHub 公開も現実的になった。一方で、フル OSS にするか、どこまで公開するか、利益・認知・実績のどれを優先するかは未整理だった。

既存の完成条件・ロードマップは次のとおりである。

- Version 1.0 は「公開可能な最小完成物」（[`version1_publish_scope.md`](version1_publish_scope.md)）
- デモサイト・販売導線の強化は Version 3.x（[`project.md`](../01_project/project.md) §4）
- 正式名称・ライセンスは公開ブロッカー（`BLG-PRJ-001` / `BLG-PRJ-007`）

ライセンス（`BLG-PRJ-007`）は公開・提供方針より下流の判断であるため、本方針を先に確定する必要がある。差別化は社員 CRUD 本体より、開発ドキュメント正本（SSOT）と AI 協調の進め方にある（販売・教材化時の位置づけメモは非公開の発信・教材化メモ（ローカル）で管理）。

---

## 3. 検討した案

### 3.1 主目的の優先

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. OSS 普及最優先|広く使われることを第一にする|認知が取りやすい|収益導線が後回しになりやすい|
|B. 副業・収益化最優先|今すぐ売ることを第一にする|収益意図が明確|ポートフォリオ・GitHub 公開と衝突しやすい|
|C. ポートフォリオ・実績最優先|公開可能な実績を第一にする|V1 完成条件と直結|売上は後続|
|D. 段階的ハイブリッド（採用）|短期は実績・認知、長期は隣接収益|ロードマップと整合。一人運用でも回る|即時売上は期待しない|

### 3.2 公開・提供形態

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. フル OSS|コード＋公開用 docs を OSS 公開|認知・信頼・実績が最速。運用が単純|同一成果物の直販は難しい|
|B. 一部公開・一部有料（機能切り出し）|Security 等を有料 Edition に|収益導線が明確|V1.x ロードマップと衝突。運用コスト高|
|C. OSS ＋ 有料サポート／教材（隣接課金）|リポは OSS、講座・伴走等は有料|進め方の差別化を売りやすい|収益は別仕事。即効性なし|
|D. 商用クローズ／非公開|非公開またはソース販売のみ|希少性|実績・認知が弱い。現行の公開準備と逆行|
|E. デュアルライセンス|OSS と商用許諾の両立|将来の企業課金に強い|個人規模では過剰|
|F. Source Available|閲覧可・再利用制限|見せる／売り止めの中間|OSS としての受けが弱い|

---

## 4. 判断基準

- [`project.md`](../01_project/project.md) の目的（AI 協調のスターター兼リファレンス）と矛盾しないこと
- Version 1.0 の「公開可能な最小完成物」を遅らせないこと（[`version1_publish_scope.md`](version1_publish_scope.md)）
- 一人開発で運用できる複雑さに収めること
- 収益オプションを閉じすぎないこと（特にライセンス制約）
- 差別化（docs・AI 協調）を対外メッセージで活かせること
- 機能の有料切り出しが既存ロードマップ（V1.x の Security 等）と衝突しないこと

---

## 5. 判断結果

**主目的は案 D（段階的ハイブリッド）を採用する。**  
**公開形態は、Version 1.0 では案 A（フル公開・OSS 寄り）とし、収益は案 C（隣接課金）を Version 3.x まで予約する。**

### 5.1 優先順位（当面）

1. ポートフォリオ・リファレンスとしての公開実績
2. 認知・フィードバックの取得
3. 収益化（当面は実施しない。選択肢は残す）

### 5.2 Version 1.0 の公開範囲（2026/07/25 再確定）

コードは OSS で公開する。ドキュメントは「手法・実例（型）は公開し、深さ（生ログ・教材原資）は非公開」を原則とする。

|対象|公開|備考|
|---|---|---|
|アプリケーションソース（`src`）|公開（OSS）|ライセンスは `BLG-PRJ-007`|
|開発ドキュメント（`docs` 全体：`progress` / `backlog` / `review_findings` / `07_decisions` / `02_rules`（`ai.md` 含む）等）|公開|AI 協調・タスク／進捗管理の運用そのものが差別化であり、実例として見せる|
|`prompts/`|公開代表のみ|手法のデモ。正本・実験は `private/prompts`。リポジトリ直下|
|`private/verification`|**非公開**|動作確認チェックリスト本体。公開しない|
|`private/ai_logs`|**非公開**|生の試行錯誤・失敗傾向。教材の中身になりやすい|
|`private/contents`|**非公開**|販売・教材化の原資そのもの|
|`private/`（全体）|**非公開**（新 Public へコピーしない）|公開後は Public のローカルに配置し、`.gitignore` で追跡しない|

- **対外メッセージ**: リファレンス／学習・実務の土台。進め方の詳細な教材は将来の有料提供を予定（約束はしない）。
- **Version 1.0 でやらない**: 有料壁、機能の有料 Edition 分割、デュアルライセンスの導入、商用クローズ。

### 5.3 収益化の方針

- Version 1.0〜2.x では、リポジトリ本体の販売や機能有料化は行わない。
- **AI 協調開発の「進め方」そのものを、将来 有料教材・伴走として提供することを主要な収益候補とする**（リポ外・隣接商品）。着手は Version 3.x 以降。
- そのため `private/ai_logs`・`private/contents` は教材原資として **非公開** を維持する。公開 docs は「型・実例」に留め、深掘り・体系化した内容は教材側へ置く。
- 型（docs）の公開は集客であり、教材（使いこなし・伴走・生の知見）と共食いしない、という整理に基づく。

### 5.4 ライセンス（`BLG-PRJ-007`）

- コードは OSS で公開することを再確定した。強いコピーレフトや Source Available のみには閉じない。
- **採用ライセンスは Apache License 2.0**（[`license_selection.md`](license_selection.md)。BLG-PRJ-007 完了）。
- `LICENSE` / README（必要なら `pom.xml`）への反映は `PRG-PRJ-002` で行う。

### 5.5 非公開資料の Git 運用（公開時の扱い）

- **採用構成は Public 正本内のフォルダ分離**（[`public_private_repo_topology.md`](public_private_repo_topology.md)）。
  - `prompts/` … 公開用の代表プロンプトのみ（常に追跡）
  - `private/` … 開発用・非公開の正本（Public のローカルワークスペースに配置し、`.gitignore` で追跡しない）
- Version 1.0 は公開対象のみを新規 Public リポジトリへコピーし、Version 1.0 起点の新規履歴とする。現行 Private リポジトリの履歴書き換え・force push は行わない（[`public_release_new_repo_snapshot.md`](public_release_new_repo_snapshot.md)）。
- 現行 Private リポジトリは公開前履歴のアーカイブとし、公開後のアプリ開発は Public リポジトリへ一本化する。
- 複数PCで非公開資料を Git 同期する必要が生じた場合は、`private` 専用 Private リポジトリへの切り出しを再検討する。
- 公開代表の置き場変更（`knowledge/` 廃止・直下 `prompts/`）は [`public_prompts_at_repo_root.md`](public_prompts_at_repo_root.md) を正とする。

### 5.6 Version 1.0 公開時の「仕込み」（認知獲得の初速）

戦略会議（2026/07/27）で、収益化本体は §5.3 のとおり Version 3.x 予約を維持し、Version 1.0 公開時は「販売活動そのもの」ではなく「認知の初速を高め、将来の収益オプションを安価に温存する仕込み」に限定することを再確認した。実施する仕込みは次のとおり。

- **差別化メッセージの言語化**: 「AI と人が同じ開発ドキュメント（SSOT）を見て開発を進める運用一式」を初見に伝わる具体表現へ翻訳し、`README.md` 冒頭の価値提案へ反映する。実施は [`PRG-PRJ-001`](../01_project/progress.md) の公開向け表現レビューに統合する（新規作業を増やさない）。語順は業務システム／再現可能な型を先に、AI は手段として置く（詳細は [`audience_target.md`](audience_target.md)）。
- **認知ターゲット（仮確定）**: 主は一人〜少人数の業務系 Java / Spring 開発者（AI 利用中・進め方の再現性が弱い層）。対外表記は [`README.md`](../../README.md) の対象者。定義・副／狙わない・困りごと仮説の正本は [`audience_target.md`](audience_target.md)。小規模チームリード（副B）は内部観測のみとし、README 本文には出さない。
- **最小 KPI（3 項目のみ）**: GitHub star 数／公開直後の解説記事の PV／フィードバック（Issue・反応）件数。一人運用で追える範囲に限定し、観測開始点を公開日とする。認知ターゲット仮説の当たり外れの材料にもする。
- **公開直後の解説記事の切り口**: 「Spring Boot CRUD の作り方」ではなく「docs を正本に、人と AI が役割分担して業務システムを進める運用一式を公開した」角度を主役にする（読者は [`audience_target.md`](audience_target.md) の主）。素材は公開 `docs`（`progress`／`07_decisions`）とし、生ログ（`private/ai_logs`・`private/contents`）は記事に出さない（§5.3 の温存方針を維持）。執筆は公開直前／直後でよく、Version 1.0 公開のブロッカーにはしない。
- **課金ターゲット像（仮説・要検証）**: AI 活用開発を学びたい個人／受託・SES 層／小規模チームリードのいずれを主軸にするかは仮説段階とし、公開後のフィードバックで検証する（[`backlog.md`](../01_project/backlog.md) BLG-PRJ-013）。認知ターゲット（[`audience_target.md`](audience_target.md)）とは別レイヤとする。

これらは新規の戦略転換ではなく §5 方針の具体化である。発信チャネル選定・課金ターゲット検証は [`backlog.md`](../01_project/backlog.md)（BLG-PRJ-012 / BLG-PRJ-013）へ登録する。

---

## 6. 判断理由

- 文書上の目的・V1 公開スコープ・V3 販売導線と整合する
- CRUD 単体より「進め方」が差別化であるため、リポ直販より隣接課金の方が自然である
- Security 等を有料切り出しすると V1.x の公開増分方針と衝突する
- 一人開発ではフル公開＋後から隣接収益が、運用コストとオプション価値のバランスがよい
- ライセンスを先に決めると手戻りしやすいため、本方針を上流判断として確定した
- （2026/07/25 追記）差別化・収益の中心は「進め方（AI 協調・docs 運用）」にあるため、型・実例（`docs`）は公開して集客に用い、教材原資となる生の知見（`private/ai_logs`・`private/contents`）は非公開として将来の有料教材に温存する。コード自体は OSS でも実害が小さく、ポートフォリオ・認知の価値が上回ると判断した

---

## 7. 今後の対応

- `BLG-PRJ-007`（ライセンス：Apache-2.0）は完了。反映は `PRG-PRJ-002`
- `BLG-PRJ-009`（knowledge 棚卸し・docs リンク解消）は完了（[`knowledge_publish_inventory.md`](knowledge_publish_inventory.md)）
- 記録係ルールの公開／非公開整理は `BLG-PRJ-010` で完了（[`recording.md`](../02_rules/recording.md) 新設、[`recording.mdc`](../../.cursor/rules/recording.mdc) は行動トリガーにスリム化）
- `PRG-PRJ-002` で README・`LICENSE`・名称、公開対象の確定、新 Public リポジトリ作成、`private` の `.gitignore` 設定、開発正本の切替（§5.5、[`public_release_new_repo_snapshot.md`](public_release_new_repo_snapshot.md)、公開セットは [`knowledge_publish_inventory.md`](knowledge_publish_inventory.md)）を実施する
- [`project.md`](../01_project/project.md) §4（Version 1.0）の公開表記は、公開範囲限定（コード・docs 公開／一部資料は非公開）へ整合済み（2026/07/27）
- Version 1.0 公開時の認知獲得の仕込み（差別化メッセージ・認知ターゲット・最小 KPI・記事切り口・課金ターゲット仮説）は §5.6。認知ターゲットの正本は [`audience_target.md`](audience_target.md)。差別化メッセージの反映は `PRG-PRJ-001`、発信チャネル選定・課金ターゲット検証は [`backlog.md`](../01_project/backlog.md)（BLG-PRJ-012 / BLG-PRJ-013）で扱う
- Version 3.x で有料教材・隣接課金や AI Research Institute 本格化に着手する際は、本判断および [`public_private_repo_topology.md`](public_private_repo_topology.md) を見直し、必要なら更新する

### 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`project.md`](../01_project/project.md)|目的・ロードマップ・将来展開|
|[`version1_publish_scope.md`](version1_publish_scope.md)|Version 1.0 公開スコープ|
|[`backlog.md`](../01_project/backlog.md)|`BLG-PRJ-008`（本判断）、`BLG-PRJ-007`／`009`／`010`（派生・完了）|
|[`progress.md`](../01_project/progress.md)|公開準備作業（PRG-PRJ-001〜003）|
|[`recording.md`](../02_rules/recording.md)|記録ルール（公開／非公開を含む）|
|非公開の発信・教材化メモ（ローカル）|販売・教材化時の位置づけメモ|
|非公開のAI協調開発ログ（ローカル）|差し戻し・再確定の AI 協調ログ|
|[`knowledge_publish_inventory.md`](knowledge_publish_inventory.md)|knowledge 公開セット（BLG-PRJ-009）|
|[`public_private_repo_topology.md`](public_private_repo_topology.md)|Public 正本とローカル `private` の分離|
|[`public_release_new_repo_snapshot.md`](public_release_new_repo_snapshot.md)|Version 1.0 のリポジトリ・Git 履歴の公開方式|
|[`project_name_candidates.md`](project_name_candidates.md)|正式名称の検討|
|[`audience_target.md`](audience_target.md)|Version 1.0 公開向け認知ターゲット（仮確定）|
