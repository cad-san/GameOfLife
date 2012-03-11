
# ライフゲーム

Androidで動作するライフゲームアプリです。

## コンセプト

* ユーザに適当に初期設定を行ってもらい、セルの生死ボーッと眺めて
  楽しんでもらうことが目的
* 眺めてもらうために、UIは操作しやすい物を。標準的なものが良さそう。
  Twitter Bootstrapを参考に。

## 設計方針

1. MVCで設計する。Model部分はTDDで実装することにする。
2. Viewはとりあえずは標準のパーツでやる。拡張は動くようになってから。
 * 基本的に、初期設定命のゲームなので、設定項目は階層状/わかりやすくする。

## 仕様

### LifeGame
* 本体となるクラス。グリッドを管理する。
* 管理する情報は以下のとおり
 * 現在のグリッド
 * 次世代のグリッド
* 世代更新の為に、現世代と一つ前の世代の2つのGridを保持する。
* createNextGeneration()メソッドで世代を更新する。
 * 現在のGridを生成
 * Cellクラスはバリューオブジェクトなので、Gridの更新はシャローコピーでOK
 * 世代を重ねても変化がないとき終了

### Grid
* 1世代のフィールドの状態を管理する。
* 管理する情報は以下のとおり
 * 世代
 * ライフゲームのフィールドの縦横サイズ
 * セルの状態（二次元配列）
 * 生存しているセル数
* 実装する機能は以下のとおり
 * 初期化メソッド
 * 次世代の生成
 * 特定座標の周りの生存セル数を取得するメソッド
 * ArrayListのゲッタメソッド
 * 世代のゲッタメソッド
 * サイズのゲッタメソッド
* 二次元ArrayListになったCellクラスでライフを管理する。
* createNextGeneration()で、次の世代のGridを生成する。
 * 二次元配列を走査し、CellのcreateNextGeneration()をCallすることでCellを更新する。
 * Gridを戻り値として返す。
 * Cellには自分を渡して生存判定させる。
* 初期化はとりあえず、booleanのArrayListを渡すことにする。

### Cell
* マップの1セルを表すクラス
* prev/currの2つのマップがあるので、バリューオブジェクトとする。
 * 不変とするのは、セルの状態（生死/グループ/カラー）
* コンストラクタで座標をセットする
 * 初期状態もセットしてやる必要があるので、
* createNextGeneration()メソッドで、該当セルの次の世代を生成する。
 * 該当セルの生死判定
* equals()メソッドを実装する
 * 座標が同じか/生死が同じか/グループが同じか/判定
* toString()は実装予定/内容は未定
