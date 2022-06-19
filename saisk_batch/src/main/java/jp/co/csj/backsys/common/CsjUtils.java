/*****************************************************************************
 * プログラム ：CsjUtils.java
 * ユーティリティクラス.
 *****************************************************************************
 * 変更履歴： 2020.xx.xx : 新規作成
 ******************************************************************************/

package jp.co.csj.backsys.common;

import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

import com.ibm.icu.text.Transliterator;

import jp.co.csj.backsys.common.exception.CsjExceptionHandle;
import jp.co.csj.backsys.common.property.CsjProperties;

public class CsjUtils {

	/** ログ出力用 */
    private static Logger log = Logger.getLogger(CsjUtils.class);

    /** 現在処理中のプロパティ名. */
    private static ThreadLocal<String> currentCopyPropertyName = new ThreadLocal<String>();

    public static String getNullStr(Object obj) throws Throwable {
    	if (obj instanceof String) {
			return String.valueOf(obj);
		} else if (isEmpty(obj)) {
			return null;
		}
        return String.valueOf(obj);
    }

    /**
     * HashCodeSha256に転換する.
     * @param str
     * @return
     * @throws Throwable
     */
    public static String getHashCodeSha256(String str) throws Throwable {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(str.getBytes());
        byte[] cipher_byte = md.digest();
        StringBuilder sb = new StringBuilder(2 * cipher_byte.length);
        for(byte b: cipher_byte) {
                sb.append(String.format("%02x", b&0xff) );
        }
        return sb.toString();
    }
    /**
     * Object to ByteArray
     *
     * @param obj
     * @return
     */
    public static byte[] toByteArray(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
            oos.close();
            bos.close();
        } catch (IOException ex) {
        	new CsjExceptionHandle(ex).outputLog();
        }
        return bytes;
    }

    /**
     *  ByteArray to Object
     *
     * @param bytes
     * @return
     */
	public static Object toObject(byte[] bytes) {
		Object obj = null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bis);
			obj = ois.readObject();
			ois.close();
			bis.close();
		} catch (Throwable ex) {
			new CsjExceptionHandle(ex).outputLog();
		}
		return obj;
	}

    /**
     * 空チェック.
     *
     * @param obj 任意オブジェクト
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof String) {
            return obj.toString().length() == 0;
        } else if (obj instanceof List) {
            return ((List) obj).size() == 0;
        } else if (obj instanceof Map) {
            return ((Map) obj).size() == 0;
        } else if (obj instanceof Set) {
            return ((Set) obj).size() == 0;
        } else if (obj instanceof Object[]) {
            return ((Object[]) obj).length == 0;
        }
        return false;
    }

    /**
     * 非空チェック.
     *
     * @param obj 任意オブジェクト
     * @return
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /**
     * バリューイコールチェック.
     * @param oldObj 任意オブジェクト1
     * @param newObj 任意オブジェクト2
     * @return
     */
    public static boolean isEqual(Object oldObj, Object newObj) {
        boolean retVal = false;
        if (oldObj == null || newObj == null) {
            return retVal;
        }
        if (oldObj instanceof String && newObj instanceof String) {
            if (String.valueOf(oldObj).equals(newObj)) {
                retVal = true;
            }
        }
        return retVal;
    }

    /**
     * バリューイコールチェック.
     * @param oldObj 任意オブジェクト1
     * @param newObj 任意オブジェクト2
     * @return
     */
    public static boolean isEqualExcludeSpaceHalftoFull(String oldObj, String newObj) {
        if (oldObj == null) {
            oldObj = "";
        }
        if (newObj == null) {
            newObj = "";
        }

		oldObj = oldObj.replaceAll(CsjConsts.STR_ZENKAKU_SPACE, "").replaceAll(CsjConsts.STR_HANKAKU_SPACE, "");
		newObj = newObj.replaceAll(CsjConsts.STR_ZENKAKU_SPACE, "").replaceAll(CsjConsts.STR_HANKAKU_SPACE, "");

		return isEqual(halftoFull(oldObj), halftoFull(newObj));
    }

	public static boolean isEqualExcludeSpaceHalftoFullAddress(String oldObj, String newObj, Set<String> addressSet) {

		if (oldObj == null) {
			oldObj = "";
		}
		if (newObj == null) {
			newObj = "";
		}

		oldObj = oldObj.replaceAll(CsjConsts.STR_ZENKAKU_SPACE, "").replaceAll(CsjConsts.STR_HANKAKU_SPACE, "");
		newObj = newObj.replaceAll(CsjConsts.STR_ZENKAKU_SPACE, "").replaceAll(CsjConsts.STR_HANKAKU_SPACE, "");
		for (String address : addressSet) {
			oldObj = oldObj.replaceAll(address, "");
			newObj = newObj.replaceAll(address, "");
		}
		return isEqual(halftoFull(oldObj), halftoFull(newObj));
	}

    /**
     * バリューノーイコールチェック.
     * @param oldObj 任意オブジェクト1
     * @param newObj 任意オブジェクト2
     * @return
     */
    public static boolean isNotEqual(Object oldObj, Object newObj) {
        return !isEqual(oldObj, newObj);
    }



    /**
     * DEEP COPY.
     * @param dest コピー先
     * @param src コピー元
     */
    public static void copyProperties(Object dest, Object src) {
        copyProperties(dest, src, false);
    }

    /**
     * DEEP COPY.
     * @param dest コピー先
     * @param src コピー元
     * @param ignoreError エラーを無視するかどうか<br>trueの場合無視する
     */
    public static void copyProperties(Object dest, Object src, boolean ignoreError) {

        try {
            //            BeanUtils.copyProperties(dest, src);

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
                    if (PropertyUtils.isReadable(src, name)
                            && PropertyUtils.isWriteable(dest, name)) {
                        Object value = ((DynaBean) src).get(name);
                        if (ignoreError) {
                            try {
                                copyProperty(dest, name, value);
                            } catch (Exception e) {
                                log.warn(e.getMessage());
                            }
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
                        if (ignoreError) {
                            try {
                                copyProperty(dest, name, entry.getValue());
                            } catch (Exception e) {
                            	new CsjExceptionHandle(e).outputLog();
                            }
                        } else {
                            copyProperty(dest, name, entry.getValue());
                        }
                    }
                }
            } else /* if (orig is a standard JavaBean) */ {
                PropertyDescriptor[] srcDescriptors = PropertyUtils.getPropertyDescriptors(src);
                for (int i = 0; i < srcDescriptors.length; i++) {
                    String name = srcDescriptors[i].getName();
                    if ("class".equals(name)) {
                        continue; // No point in trying to set an object's class
                    }
                    if (PropertyUtils.isReadable(src, name)
                            && PropertyUtils.isWriteable(dest, name)) {
                        Object value = PropertyUtils.getSimpleProperty(src, name);
                        if (ignoreError) {
                            try {
                                copyProperty(dest, name, value);
                            } catch (Exception e) {
                            	new CsjExceptionHandle(e).outputLog();
                            }
                        } else {
                            copyProperty(dest, name, value);
                        }
                    }
                }
            }
        } catch (Exception e) {
        	new CsjExceptionHandle(e).outputLog();
            throw new RuntimeException(e);
        }
    }

    /**
     * DEEP COPY.
     * @param dest コピー先
     * @param name プロパティ名
     * @param value プロパティ値
     */
    private static void copyProperty(Object dest, String name, Object value) {

        try {
            currentCopyPropertyName.set(name);
            BeanUtils.copyProperty(dest, name, value);
        } catch (Exception e) {
        	new CsjExceptionHandle(e).outputLog();
            throw new RuntimeException(e);
        } finally {
            currentCopyPropertyName.remove();
        }
    }

    public static boolean checkSys(Map<String,String> sysMap, String msgKey,String para) {
        boolean isSysChkOk = true;

        for (Entry<String,String> entry : sysMap.entrySet() ) {

            String key = entry.getKey();
            String val = entry.getValue();
            if (CsjUtils.isEmpty(getSysEnv(key))) {
                isSysChkOk = false;
                log.error(CsjProperties.getMsgLog(msgKey, key, val, para));
            }
        }
        return isSysChkOk;

    }

    public static String getSysEnv(String key) {

        String retVal=System.getenv().get(key.toUpperCase());
        if (isEmpty(retVal)) {
            retVal = System.getenv().get(key.toLowerCase());
        }
        return retVal;
    }

    public static boolean checkProperties(Map<String,String> sysMap, String msgKey,String para) {
        boolean isSysChkOk = true;

        for (Entry<String,String> entry : sysMap.entrySet() ) {

            String key = entry.getKey();
            String val = entry.getValue();

            try {
                if (CsjUtils.isEmpty(CsjProperties.getMsg(key))) {
                    isSysChkOk = false;
                    log.error(CsjProperties.getMsg(msgKey, key, val, para));
                }
			} catch (Exception e) {
				isSysChkOk = false;
				log.error(CsjProperties.getMsg(msgKey, key, val, para));
			}

        }
        return isSysChkOk;
    }


	public static String halftoFull(String target) {
		Transliterator halftoFull = Transliterator.getInstance("Halfwidth-Fullwidth");
		return halftoFull.transliterate(target);
	}
	public static String addQuotation(String str, String ch) {

		if (isEmpty(str)) {
			return CsjConsts.STR_EMPTY;
		} else {
			return ch + str + ch;
		}
	}

    // add end souka 20200430 DBIZDEV-134

	public static boolean isLikeStr(String fileNm, Set<String> fileLikeSet) {
		for (String fileLikeName : fileLikeSet) {
			if (fileNm.matches(fileLikeName)) {
				return true;
			}
		}
		return false;
	}
}
