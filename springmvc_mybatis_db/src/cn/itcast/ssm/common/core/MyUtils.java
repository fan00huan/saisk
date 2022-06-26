package cn.itcast.ssm.common.core;

import java.beans.PropertyDescriptor;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * 各種ユーティリティ
 *
 */
public class MyUtils {

	public static Map<String,SimpleDateFormat> dateFormatMap = new LinkedHashMap<>();
	public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(MyConst.YYYY_MM_DD_HH_MM_SS_SLASH_24_SSS);
	public static SimpleDateFormat getSimpleDateFormat() {
		return simpleDateFormat;
	}
	public static Map<String, SimpleDateFormat> getDateFormatMap() {
		return dateFormatMap;
	}

	static {
		//实现SimpleDateFormat MAP
//		dateFormatMap.put(MyConst.YYYY_MM_DD_HH_MM_SS_SLASH_24,new SimpleDateFormat(MyConst.YYYY_MM_DD_HH_MM_SS_SLASH_24));
		dateFormatMap.put(MyConst.YYYY_MM_DD_SLASH,new SimpleDateFormat(MyConst.YYYY_MM_DD_SLASH));
//		dateFormatMap.put(MyConst.YYYY_MM_DD,new SimpleDateFormat(MyConst.YYYY_MM_DD));
//		dateFormatMap.put(MyConst.YYYY_MM_DD_HH_MM_SS_MINUS_24,new SimpleDateFormat(MyConst.YYYY_MM_DD_HH_MM_SS_MINUS_24));
		dateFormatMap.put(MyConst.YYYY_MM_DD_MINUS,new SimpleDateFormat(MyConst.YYYY_MM_DD_MINUS));
//		dateFormatMap.put(MyConst.YYYY_MM_DD_HH_MM_SS_ALL_MINUS_24,new SimpleDateFormat(MyConst.YYYY_MM_DD_HH_MM_SS_ALL_MINUS_24));
//		dateFormatMap.put(MyConst.YYYYMMDDHHMMSSMINUS_24,new SimpleDateFormat(MyConst.YYYYMMDDHHMMSSMINUS_24));
//		dateFormatMap.put(MyConst.YYYYMMDDHHMMSSSSS,new SimpleDateFormat(MyConst.YYYYMMDDHHMMSSSSS));
//		dateFormatMap.put(MyConst.YYYYMMDDHH_HHMMSS_24,new SimpleDateFormat(MyConst.YYYYMMDDHH_HHMMSS_24));
//		dateFormatMap.put(MyConst.YYYY_MM_DD_HH_MM_SS_SLASH_24_SSS,new SimpleDateFormat(MyConst.YYYY_MM_DD_HH_MM_SS_SLASH_24_SSS));
//		dateFormatMap.put(MyConst.YYYY_MM_DD_HH_MM_SS_MINUS_24_SSS,new SimpleDateFormat(MyConst.YYYY_MM_DD_HH_MM_SS_MINUS_24_SSS));
	}

	/** 現在処理中のプロパティ名 */
	private static ThreadLocal<String> currentCopyPropertyName = new ThreadLocal<String>();
// #36597 add end

	/**
	 * 開始位置を計算する
	 *
	 * @param page ページ番号
	 * @param limit 表示数
	 * @return 開始位置
	 */
	public static int calcOffset(int page, int limit) {
		return (page - 1) * limit;
	}

	/**
	 * ページ番号を計算する
	 *
	 * @param offset 開始位置
	 * @param limit 表示数
	 * @return ページ番号
	 */
	public static int calcPage(int offset, int limit) {
		return (offset / limit) + 1;
	}

	/**
	 * 最終ページ番号を計算する
	 *
	 * @param count 検索件数
	 * @param limit 表示数
	 * @return 最終ページ番号
	 */
	public static int calcLastPage(long count, int limit) {
		if (count == 0)
			return 1;
		if (count % limit == 0) {
			return (int) (count / limit);
		} else {
			return (int) (count / limit) + 1;
		}
	}

	/**
	 *
	 * @param dest コピー先
	 * @param src コピー元
	 */
	public static void copyProperties(Object dest, Object src) {
		copyProperties(dest, src, false);
	}

	/**
	 *
	 * @param dest コピー先
	 * @param src コピー元
	 * @param ignoreError エラーを無視するかどうか<br />trueの場合無視する
	 */
	public static void copyProperties(Object dest, Object src, boolean ignoreError) {

		try {
//			BeanUtils.copyProperties(dest, src);

			// Validate existence of the specified beans
			if (dest == null) {
				throw new IllegalArgumentException("No destination bean specified");
			}
			if (src == null) {
				throw new IllegalArgumentException("No origin bean specified");
			}

			// Copy the properties, converting as necessary
			if (src instanceof DynaBean) {
				DynaProperty[] srcDescriptors = ((DynaBean) src).getDynaClass().getDynaProperties();
				for (int i = 0; i < srcDescriptors.length; i++) {
					String name = srcDescriptors[i].getName();
					// Need to check isReadable() for WrapDynaBean
					// (see Jira issue# BEANUTILS-61)
					if (PropertyUtils.isReadable(src, name) && PropertyUtils.isWriteable(dest, name)) {
						Object value = ((DynaBean) src).get(name);
						if  (ignoreError) {
							try {
								copyProperty(dest, name, value);
							} catch (Exception e) {}
						} else {
							copyProperty(dest, name, value);
						}
					}
				}
			} else if (src instanceof Map) {
				Iterator<?> entries = ((Map<?, ?>) src).entrySet().iterator();
				while (entries.hasNext()) {
					Map.Entry<?, ?> entry = (Map.Entry<?, ?>) entries.next();
					String name = (String) entry.getKey();
					if (PropertyUtils.isWriteable(dest, name)) {
						if  (ignoreError) {
							try {
								copyProperty(dest, name, entry.getValue());
							} catch (Exception e) {}
						} else {
							copyProperty(dest, name, entry.getValue());
						}
					}
				}
			} else /* if (orig is a standard JavaBean) */{
				PropertyDescriptor[] srcDescriptors = PropertyUtils.getPropertyDescriptors(src);
				for (int i = 0; i < srcDescriptors.length; i++) {
					String name = srcDescriptors[i].getName();
					if ("class".equals(name)) {
						continue; // No point in trying to set an object's class
					}
					if (PropertyUtils.isReadable(src, name) && PropertyUtils.isWriteable(dest, name)) {
						Object value = PropertyUtils.getSimpleProperty(src, name);
						if  (ignoreError) {
							try {
								copyProperty(dest, name, value);
							} catch (Exception e) {}
						} else {
							copyProperty(dest, name, value);
						}
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 *
	 * @param dest コピー先
	 * @param name プロパティ名
	 * @param value プロパティ値
	 */
	public static void copyProperty(Object dest, String name, Object value) {

		try {
			currentCopyPropertyName.set(name);
			BeanUtils.copyProperty(dest, name, value);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			currentCopyPropertyName.remove();
		}
	}
}
