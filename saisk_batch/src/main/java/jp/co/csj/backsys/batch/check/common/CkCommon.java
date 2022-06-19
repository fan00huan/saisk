/*****************************************************************************
 * プログラム ：CkCommon.java
 * 説明 ：チェック共通クラス.
 *****************************************************************************
 * 変更履歴： 2020.xx.xx : 新規作成
 ******************************************************************************/

package jp.co.csj.backsys.batch.check.common;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import jp.co.csj.backsys.batch.abs.AbsBatch;
import jp.co.csj.backsys.common.CsjConsts;
import jp.co.csj.backsys.common.CsjUtils;
import jp.co.csj.backsys.common.message.CsjMessageKey;
import jp.co.csj.backsys.common.property.CsjProperties;

public class CkCommon {

    /** ログ出力用. */
    private static Logger log = Logger.getLogger(CkCommon.class);

    private AbsBatch batch;

    public CkCommon(AbsBatch batch) {
        this.batch = batch;
    }


    public void setMydbDbSys(Map<String, String> map) {

        // BACKOFFICE_DATABASE
    }
	public void setFrtDbSys(Map<String, String> map) {
	}

	public void setScrbDbSys(Map<String, String> map) {
	}

	public void setMailSys(Map<String, String> map) {
	}
	public void setSslCa(Map<String, String> map) {

	}

	/**
	 * ログフォルダー
	 *
	 * @param map
	 */
	public void setCommonSys(Map<String, String> map) {
        // log
        map.put(CsjConsts.SYS_CSJ_APP_LOG, CsjConsts.LOG_J_LOG_PATH);
	}

	/**
	 *
	 * @param map
	 */
	public void setLiquidSys(Map<String, String> map) {

	    map.put(CsjConsts.SYS_HTTPS_PROXY, CsjConsts.LOG_J_HTTPS_PROXY);
	}

	public void checkMailVmFileProperties(String vmFileNmKey) throws Throwable {
		Map<String, String> map = new LinkedHashMap<String, String>();

        // メール配信 VMファイル
        map.put(vmFileNmKey, CsjConsts.LOG_J_MAIL_SEND);

        if (!CsjUtils.checkProperties(map, CsjMessageKey.E1003, batch.getBatchId())) {
            throw new Throwable(CsjProperties.getMsgLog(CsjMessageKey.E0001, batch.getBatchId()));
        }

        File f = new File(CsjConsts.SYS_ENV_MAIL_RESOURCES + CsjProperties.getMsg(vmFileNmKey));
        if (f != null && f.isFile()) {
			// 処理なし
		} else {
			log.error(CsjProperties.getMsgLog(CsjMessageKey.E1001, CsjProperties.getMsg(vmFileNmKey), batch.getBatchId()));
            throw new Throwable(CsjProperties.getMsgLog(CsjMessageKey.E0001, batch.getBatchId()));
		}
	}

}
