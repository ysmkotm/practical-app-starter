# コンテンツ制作まわり判断記録の公開範囲

**Document Version** : 1.0

**更新日** : 2026/07/30

**ステータス** : 決定

---

## 1. 目的

`private/contents` の制作ワークスペース化に関する判断記録を、公開 `docs` に残すか非公開へ移すかを定め、公開方針の「型は公開・深さは非公開」と整合させる。

---

## 2. 背景

公開方針では、開発ドキュメント（`docs` 全体）は公開し、`private/ai_logs`・`private/contents` は教材原資として非公開とする（[`public_offering_strategy.md`](public_offering_strategy.md) §5.2）。一方で同方針は、公開 docs を「型・実例」に留め、深掘りは教材側へ置くとも定めている。

この境界上に、次の2ファイルがあった。

|ファイル|性質|
|---|---|
|[`contents_and_ai_logs_separation.md`](contents_and_ai_logs_separation.md)|`docs` と非公開資料を分ける基準（型寄り）|
|`contents_as_content_workspace.md`（旧 `docs/07_decisions/`）|種・piece・執筆フローなど制作パイプライン（深さ寄り）|

後者を公開したままにすると、「`docs` は全部公開」と「深さは非公開」が衝突し、将来の教材化の下ごしらえまで公開面に出すことになる。

---

## 3. 検討した案

|案|概要|メリット|デメリット|
|---|---|---|---|
|A. 両方とも公開のまま|判断記録はすべて公開で一貫|透明性が高い|制作パイプラインまで見せる|
|B. 両方とも非公開へ移す|教材原資まわりを守れる|分離基準（型）まで見えなくなる|
|C. 分離基準は公開、制作ワークスペース詳細は非公開|型／深さの原則と整合|判断記録の置き場に例外ができる|
|D. 両ファイルとも公開は要約のみ|公開面は薄い|分離基準の厚みも失う|

---

## 4. 判断基準

- [`public_offering_strategy.md`](public_offering_strategy.md) §5.2・§5.3（型は公開、深さ・教材原資は非公開）と矛盾しないこと
- OSS 利用者が記録種別・`private/` の存在理由を理解できること
- 制作オペレーション（種・piece・執筆フロー）を公開必須にしないこと
- Version 1.0 公開を遅らせないこと

---

## 5. 判断結果

**案 C を採用する。**

|対象|扱い|
|---|---|
|[`contents_and_ai_logs_separation.md`](contents_and_ai_logs_separation.md)|**公開に残す**。分離の目的・基準・結論を正とする。制作運用の詳細は書かない|
|制作ワークスペース化の判断|**非公開**へ移す。正本は `private/contents/contents_as_content_workspace.md`（ローカル）。運用手順は `private/contents/README.md`|
|公開 docs からの参照|制作詳細への Markdown リンクは付けない。必要なら「非公開の制作ワークスペース方針（ローカル）」等の一般表記に留める|

### 5.1 公開判断記録の例外について

原則として判断記録は `docs/07_decisions/`（公開）に置く。ただし、**判断の主題そのものが非公開運用の深さ（教材制作パイプライン等）である場合**は、`private/` 側に判断記録を置いてよい。公開側には本ファイルのように「何を非公開にしたか」の線引きだけを残す。

---

## 6. 判断理由

- 分離基準は「なぜ `private/` があるか」を説明する型であり、公開価値がある
- 種・piece・front matter・執筆フローは将来の隣接課金の作り方そのものであり、深さに当たる
- 公開 docs に制作詳細を残すと、型／深さのメッセージがぼやける
- ローカルでは `private/contents/` に判断と運用が並ぶため、Cursor・人間の導線は維持できる

---

## 7. 今後の対応

- `docs/07_decisions/contents_as_content_workspace.md` を `private/contents/` へ移す（本判断と同時実施）
- [`contents_and_ai_logs_separation.md`](contents_and_ai_logs_separation.md) から制作詳細への依存を薄くする
- [`recording.md`](../02_rules/recording.md) ほか参照箇所を更新する
- 同様の境界案件が出たら、本判断の §5.1 を参照する

### 関連ドキュメント

|ドキュメント|内容|
|---|---|
|[`public_offering_strategy.md`](public_offering_strategy.md)|公開範囲（型／深さ）|
|[`contents_and_ai_logs_separation.md`](contents_and_ai_logs_separation.md)|ai_logs／contents 分離（公開・型）|
|[`knowledge_publish_inventory.md`](knowledge_publish_inventory.md)|公開セット・docs リンク方針|
|[`recording.md`](../02_rules/recording.md)|記録種別と公開／非公開|
|`private/contents/contents_as_content_workspace.md`（非公開・ローカル）|制作ワークスペース化の判断正本|
|`private/contents/README.md`（非公開・ローカル）|制作フローの運用正本|
