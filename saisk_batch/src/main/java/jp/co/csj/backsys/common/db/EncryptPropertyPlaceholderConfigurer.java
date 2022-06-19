/*****************************************************************************
 * プログラム ：EncryptPropertyPlaceholderConfigurer.java
 * 説明 ：データベースパスワードDecrypt用.
 *****************************************************************************
 * 変更履歴： 2020.xx.xx : 新規作成
 ******************************************************************************/

package jp.co.csj.backsys.common.db;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

@SuppressWarnings("deprecation")
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	/** ログ出力用 */
	//private static Logger log = Logger.getLogger(EncryptPropertyPlaceholderConfigurer.class);

	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		return propertyValue;

	}
}
