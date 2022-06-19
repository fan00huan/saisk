/*****************************************************************************
 * プログラム ：CsjMessageKey.java 説明 ：メッセージキークラス.
 *****************************************************************************
 * 変更履歴： 2020.xx.xx : 新規作成
 ******************************************************************************/

package jp.co.csj.backsys.common.message;

public class CsjMessageKey {

	public static final String BATCH_VERSION = "BATCH_VERSION";

	/** メッセージキー：E1001_J(ファイル読込失敗しました。（ファイル名：{0}）（バッチID：{1}）) */
	public static final String E1001_J = "ファイル読込失敗しました。（ファイル名：{0}）（バッチID：{1}）";
	// 業務異常(共通)
	/** メッセージキー：E0001(バッチ異常発生しました。（バッチID：{0}）) */
	public static final String E0001 = "E0001";

	/** メッセージキー：E0002(TODO) */
	public static final String E0002 = "E0002";

	/** メッセージキー：E0003({0}取得失敗しました。（バッチID：{1}）) */
	public static final String E0003 = "E0003";

	/** メッセージキー：E0004({0}は失敗しました。time:{1}.jvid:{2}.cid:{3}.（バッチID：{4}）) */
	public static final String E0004 = "E0004";

	/** メッセージキー：E0005(TODO) */
	public static final String E0005 = "E0005";

	/** メッセージキー：E0006(TODO) */
	public static final String E0006 = "E0006";

	/** メッセージキー：E0007({0}は失敗しました。time:{1}.（バッチID：{2}）) */
	public static final String E0007 = "E0007";

	/** メッセージキー：E0008(フォルダー存在していません。（{0}）（{1}）（バッチID：{2}）) */
	public static final String E0008 = "E0008";

	/** メッセージキー：E0009(TODO) */
	public static final String E0009 = "E0009";

	/** メッセージキー：E0010(TODO) */
	public static final String E0010 = "E0010";

	// 業務異常(本人確認・反社照会)
	/** メッセージキー：E0201(TODO) */
	public static final String E0201 = "E0201";

	/** メッセージキー：E0202(TODO) */
	public static final String E0202 = "E0202";

	/** メッセージキー：E0204(TODO) */
	public static final String E0204 = "E0204";

	/** メッセージキー：E0205(TODO) */
	public static final String E0205 = "E0205";

	// 環境異常
	/** メッセージキー：E1001(ファイル読込失敗しました。（ファイル名：{0}）（バッチID：{1}）) */
	public static final String E1001 = "E1001";

	/** メッセージキー：E1002(サーバの環境変数は設定されていないです。（{0}）（{1}）（バッチID：{2}）) */
	public static final String E1002 = "E1002";
	/** メッセージキー：E1003(プロパティファイルには設定されていないです。（{0}）（{1}）（バッチID：{2}）) */
	public static final String E1003 = "E1003";

	/** メッセージキー：E1004(TODO) */
	public static final String E1004 = "E1004";

	/** メッセージキー：E1005(データベースのヘルスチェックは通れません。（データベース名前：{0}）（バッチID：{1}）) */
	public static final String E1005 = "E1005";


	/** メッセージキー：E1007(TODO) */
	public static final String E1007 = "E1007";

	/** メッセージキー：E1008(TODO) */
	public static final String E1008 = "E1008";

	/** メッセージキー：E1009(TODO) */
	public static final String E1009 = "E1009";

	/** メッセージキー：E1010(TODO) */
	public static final String E1010 = "E1010";

	/** メッセージキー：E1011(メール配信失敗しました。{0}.（バッチID：{1}）) */
	public static final String E1011 = "E1011";

	/** メッセージキー：E1013(TODO) */
	public static final String E1013 = "E1013";

	/** メッセージキー：E1014(TODO) */
	public static final String E1014 = "E1014";

	/** メッセージキー：E1015(TODO) */
	public static final String E1015 = "E1015";

	// 情報ログ(共通)
	/** メッセージキー：I0001(バッチ実行{0}。（バッチID：{1}）) */
	public static final String I0001 = "I0001";

	/** メッセージキー：I0002(環境変数チェック{0}。（バッチID：{1}）) */
	public static final String I0002 = "I0002";

	/** メッセージキー：I0003(プロパティ存在チェック{0}。（バッチID：{1}）) */
	public static final String I0003 = "I0003";

	/** メッセージキー：I0004(データベースのヘルスチェック{0}。（バッチID：{1}）) */
	public static final String I0004 = "I0004";

	/** メッセージキー：I0005(メール配信しました。{0}.（バッチID：{1}）) */
	public static final String I0005 = "I0005";

	/** メッセージキー：I0006(データベースのヘルスチェックは正常です。（データベース名前：{0}）（バッチID：{1}）) */
	public static final String I0006 = "I0006";

	/** メッセージキー：I0008(ファイル作成{0}。[{1}]（バッチID：{2}）) */
	public static final String I0008 = "I0008";

	/** メッセージキー：I0021({0}バックアップしました。from:[{1}]to:[{2}]（バッチID：{3}）) */
	public static final String I0021 = "I0021";

	/** メッセージキー：I0022({0}ファイル削除した。{1}（バッチID：{2}）) */
	public static final String I0022 = "I0022";

}
