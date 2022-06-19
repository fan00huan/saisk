/*****************************************************************************
 * プログラム ：CsjProperties.java
 * 説明 ：ResourceBundleでUTF-8に対応するためのクラス.
 *****************************************************************************
 * 変更履歴： 2020.xx.xx : 新規作成
 ******************************************************************************/

package jp.co.csj.backsys.common.property;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import jp.co.csj.backsys.common.CsjConsts;

public class ResourceBundleUtf8Control extends ResourceBundle.Control {

	public static final String PROPERTY_EXT = "properties";

	/**
	 * プロパティファイルをUTF-8で読み込み、新たなPropertyResourceBundleを返却する。
	 */
	@Override
	public ResourceBundle newBundle(final String baseName, final Locale locale, final String format,
			final ClassLoader loader, final boolean reload)
			throws IllegalAccessException, InstantiationException, IOException {

		final String bundleName = toBundleName(baseName, locale);
		final String resourceName = toResourceName(bundleName, PROPERTY_EXT);

		InputStream is = loader.getResourceAsStream(resourceName);
		InputStreamReader isr = new InputStreamReader(is, CsjConsts.CHAR_CODE);
		BufferedReader reader = new BufferedReader(isr);

		return new PropertyResourceBundle(reader);
	}
}