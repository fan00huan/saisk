/*****************************************************************************
 * プログラム ：CsjConsts.java
 * 定数クラス.
 *****************************************************************************
 * 変更履歴： 2020.xx.xx : 新規作成
 ******************************************************************************/

package jp.co.csj.backsys.common;

public class CsjConsts {

	/** CSJ_APP_LOG */
	public static final String SYS_CSJ_APP_LOG = "CSJ_APP_LOG";

	/** https_proxy http://xxx.xxx.xxx.xxx:9999 */
	public static final String SYS_HTTPS_PROXY = "https_proxy";

	/** リスポンス 200:正常 */
	public static final int HTTP_STATUS_CODE_200 = 200;

	/** リスポンス 201:正常 */
	public static final int HTTP_STATUS_CODE_201 = 201;

	/** リスポンス 204:正常 */
	public static final int HTTP_STATUS_CODE_204 = 204;

	/** 文字列定数：全角スペース 「　」*/
	public static final String STR_ZENKAKU_SPACE = "　";

	/** 文字列定数：全角スペース 「 」*/
	public static final String STR_HANKAKU_SPACE = " ";

	/** 文字列定数： 「.」 */
	public static final String STR_DOT = ".";

	/** 文字列定数： 「:」 */
	public static final String STR_CORON = ":";

	/** 文字列定数： 「,」 */
	public static final String STR_CONMA = ",";

	/** 文字列定数： 「(」 */
	public static final String STR_KAKO_LEFT = "(";

	/** 文字列定数： 「)」 */
	public static final String STR_KAKO_RIGHT = ")";

	/** 文字列定数： 「"」 */
	public static final String STR_DOUBLE_QUOTATION = "\"";

	/** エンコード */
	public static final String CHAR_CODE = "UTF-8";

	public static final String YYYY_MM_DD = "yyyyMMdd";
	public static final String YYYY_MM_DD_HH_MM_SS_SSS_SLASH_24 = "yyyy/MM/dd HH:mm:ss:SSS";

	public static final String YYYY_MM_DD_HH_MM_SS_SLASH_24 = "yyyy/MM/dd HH:mm:ss";

	public static final String YYYY_MM_DD_H_MM_SS_SLASH_24 = "yyyy/MM/dd H:mm:ss";

	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

	public static final String YYYY_MM_SLASH = "yyyy/MM";

	public static final String YYYY_MM_DD_SLASH = "yyyy/MM/dd";

	public static final String YYYY_MM_JP = "yyyy年MM月";

	public static final String YYYY = "yyyy";

	public static final String MM = "MM";

	public static final String DD = "dd";

	public static final String YYYY_MM = "yyyyMM";

	/** ログメッセージ：開始 */
	public static final String MSG_START = "開始";

	/** ログメッセージ：終了 */
	public static final String MSG_END = "終了";


	public static final String STR_ENTER = "\n";
	/** データベース接続 */
	public static final String LOG_J_DB_CONECT_USE = "データベース接続";
	/** メール配信 */
	public static final String LOG_J_MAIL_SEND = "メール配信";

	/** メール件名 */
	public static final String LOG_J_MAIL_TITLE = "メール件名";
	/** メール差出人 */
	public static final String LOG_J_MAIL_SENDER = "メール差出人";

	/** ログ出力場所 */
	public static final String LOG_J_LOG_PATH = "ログ出力場所";

	/** http://xxx.xxx.xxx.xxx:9999 */
	public static final String LOG_J_HTTPS_PROXY = "proxy(http://xxx.xxx.xxx.xxx:9999)";

	/** 更新 */
	public static final String LOG_J_UPT = "更新";
	/** 登録 */
	public static final String LOG_J_INS = "登録";

	/** CID */
	public static final String STR_CID = "ID";

	/** sucessful.txt */
	public static final String STR_SUCESSFUL_FILE_NAME = "sucessful.txt";

	/** sucessful.txt 0 */
	public static final String STR_SUCESSFUL_STATUS = "0";

	/** {0} */
	public static final String STR_PLACEHOLDER = "{0}";

	/** ^\\d{n}$ */
	public static final String REG_NUM_14 = "\\d{14}";

	/** ^\\d{n}$ */
	public static final String REG_NUM_2 = "\\d{2}";
	/** ^\\d{n}$ */
	public static final String REG_NUM_4 = "\\d{4}";

	public static final String SYS_ENV_FILE_DIV = System.getProperty("file.separator");

	public static final String SYS_ENV_FOLDER_RESOURCES = System.getProperty("user.dir") + SYS_ENV_FILE_DIV + "src"
			+ SYS_ENV_FILE_DIV + "main" + SYS_ENV_FILE_DIV + "resources" + SYS_ENV_FILE_DIV;

	public static final String SYS_ENV_MAIL_RESOURCES = SYS_ENV_FOLDER_RESOURCES + "mail" + SYS_ENV_FILE_DIV;

	public static final String STR_EMPTY = "";

	public static final String STR_BK = "bk";



}
