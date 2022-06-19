/*****************************************************************************
 * プログラム ：Ck001.java
 * 説明 ：チェッククラス.
 *****************************************************************************
 * 変更履歴： 2020.xx.xx : 新規作成
 ******************************************************************************/

package jp.co.csj.backsys.batch.check;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import jp.co.csj.backsys.common.CsjUtils;
import jp.co.csj.backsys.common.message.CsjMessageKey;
import jp.co.csj.backsys.common.property.CsjProperties;
@Service
public class Ck001 {


    /**
     * 環境変数をチェックする.
     * @throws Throwable
     */
    public void checkSys() throws Throwable {
        Map<String, String> map = new LinkedHashMap<String, String>();

        // MAIL
        //ckCommon.setMailSys(map);

        // Common
        //ckCommon.setCommonSys(map);

        if (!CsjUtils.checkSys(map, CsjMessageKey.E1002,"batch id 001")) {
            throw new Throwable(CsjProperties.getMsgLog(CsjMessageKey.E0001, "batch id 001"));
        }
    }

    /**
     * プロパティ存在チェック処理を行う.
     * @throws Throwable
     */
    public void checkProperties() throws Throwable {

//        ckCommon.checkMailVmFileProperties(CsjProperties.Bt005_MAIL_VM_FILE);

    }
}
