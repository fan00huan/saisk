/*****************************************************************************
 * プログラム ：AbsBatch.java バッチの親クラス.
 *****************************************************************************
 * 変更履歴： 2020.xx.xx : 新規作成
 ******************************************************************************/
package jp.co.csj.backsys.batch.abs;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import jp.co.csj.backsys.common.CsjConsts;
import jp.co.csj.backsys.common.CsjUtilsDate;
import jp.co.csj.backsys.common.exception.CsjExceptionHandle;
import jp.co.csj.backsys.common.message.CsjMessageKey;
import jp.co.csj.backsys.common.property.CsjProperties;
import jp.co.csj.backsys.common.property.ResourceBundleUtf8Control;
import jp.co.csj.backsys.mapper.HeathCheckMapper;

public abstract class AbsBatch extends AbsLogger{

	
	public static ApplicationContext context ;
	
	static {
		
        String propFile = "classpath:spring/applicationContext.xml";

        // アプリケーションコンテクストを初期化する
        try {
        	context = new ClassPathXmlApplicationContext(propFile);
        } catch (Exception e) {
        	e.printStackTrace();
//            log.error(MessageFormat.format(CsjMessageKey.E1001_J, propFile, getBatchId()) + CsjConsts.STR_KAKO_LEFT
//                    + CsjMessageKey.E1001 + CsjConsts.STR_KAKO_RIGHT);
            throw e;
        }
		
	}
	public AbsBatch() {
		super();

	}

    /** バッチ実行結果 0:成功. */
    public static final int BATCH_RET_SUCCESS = 0;

    /** バッチ実行結果 1:異常. */
    public static final int BATCH_RET_ERROR = 1;

    /** 共通プロパティ. */
    private Properties commonProp;

    

    /** バッチID. */
    protected String batchId;

    /** バッチ名前. */
    protected String batchNm;

    protected List<String> dbHeathLst;

    private Date beginDate = new Date();

    /** 暗号化復号化キー */
    private String csjColumnKey;

    /**
     * プロパティファイルを読み込み.
     *
     * @throws Throwable システムエラー
     */
    public void setup() throws Throwable {


        try {
            // プロパティファイルを読み込み
            commonProp = CsjProperties.getCommonProperties();
        } catch (Exception e) {
            log.error(MessageFormat.format(CsjMessageKey.E1001_J,
                    CsjProperties.RESOURCE_BUNDLE + CsjConsts.STR_DOT + ResourceBundleUtf8Control.PROPERTY_EXT,
                    getBatchId()) + CsjConsts.STR_KAKO_LEFT + CsjMessageKey.E1001 + CsjConsts.STR_KAKO_RIGHT);
            throw e;
        }
        log.info(CsjProperties.getMsg(CsjMessageKey.BATCH_VERSION, getBatchId()));
    }

    /**
     * 環境変数をチェックする.
     */
    public abstract void checkSys() throws Throwable;

    /**
     * プロパティ存在チェック処理を行う.
     *
     * @throws Throwable システムエラー
     */
    public abstract void checkProperties() throws Throwable;

    /**
     * バッチ実行する前の準備を行う.
     *
     * @throws Throwable システムエラー
     */
    public void preRun() throws Throwable {

        // 環境変数存在チェック
        log.info(CsjProperties.getMsgLog(CsjMessageKey.I0002, CsjConsts.MSG_START, getBatchId()));
        checkSys();
        log.info(CsjProperties.getMsgLog(CsjMessageKey.I0002, CsjConsts.MSG_END, getBatchId()));

        // プロパティ存在チェック
        log.info(CsjProperties.getMsgLog(CsjMessageKey.I0003, CsjConsts.MSG_START, getBatchId()));
        checkProperties();
        log.info(CsjProperties.getMsgLog(CsjMessageKey.I0003, CsjConsts.MSG_END, getBatchId()));

        // データベースのヘルスチェック
        log.info(CsjProperties.getMsgLog(CsjMessageKey.I0004, CsjConsts.MSG_START, getBatchId()));
        dbHeathCheck();
        log.info(CsjProperties.getMsgLog(CsjMessageKey.I0004, CsjConsts.MSG_END, getBatchId()));
    }

    /**
     * 初期化を行う.
     *
     * @throws Throwable システムエラー
     */
    public abstract void init() throws Throwable;


    /**
     * 主処理を行う.
     *
     * @throws Throwable システムエラー
     */
    public abstract void run() throws Throwable;

    /**
     * 後処理を行う.
     *
     * @throws Throwable システムエラー
     */
    public void afterRun() {
        Date endDate = new Date();
        log.info("beginTime:[" + CsjUtilsDate.getFormatDate(beginDate, CsjConsts.YYYY_MM_DD_HH_MM_SS_SLASH_24)
                + "] endTime:[" + CsjUtilsDate.getFormatDate(endDate, CsjConsts.YYYY_MM_DD_HH_MM_SS_SLASH_24)
                + "] costTime:[" + CsjUtilsDate.longTimeToDay(endDate.getTime() - beginDate.getTime()) + "]");
    }

    /**
     * バッチ実行流れ.
     *
     * @throws Throwable システムエラー
     */
    public void produce() {
        try {

            // プロパティファイルを読み込み.
            setup();

            log.info(CsjProperties.getMsgLog(CsjMessageKey.I0001, CsjConsts.MSG_START, getBatchId()));

            // バッチ実行する前の準備を行う.
            preRun();

            // 初期化を行う.
            init();

            // 主処理を行う.
            run();
            log.info(CsjProperties.getMsgLog(CsjMessageKey.I0001, CsjConsts.MSG_END, getBatchId()));

            // 後処理を行う.
            afterRun();

            System.exit(BATCH_RET_SUCCESS);
        } catch (Throwable e) {

        	new CsjExceptionHandle(e).outputLog();

            try {
                // バッチ異常発生しました。
                log.error(CsjProperties.getMsgLog(CsjMessageKey.E0001, getBatchId()));
            } catch (Throwable e2) {
                // 処理なし
            }

            // 後処理を行う.
            afterRun();

            System.exit(BATCH_RET_ERROR);
        }
    }

    /**
     * データベースのヘルスチェックを行う.
     *
     * @throws Throwable システムエラー
     */
    public void dbHeathCheck() throws Throwable {

        boolean isOk = true;

        try {
            HeathCheckMapper heathCheckMapper =
                    (HeathCheckMapper) getApplicationContext().getBean("heathCheckMapper");
            heathCheckMapper.heathCheck();

            // データベースのヘルスチェックは正常です。（データベース名前：{0}）（バッチID：{1}）
            log.info(CsjProperties.getMsgLog(CsjMessageKey.I0006, "", getBatchId()));

        } catch (Throwable e) {
        	new CsjExceptionHandle(e).outputLog();
            // データベースのヘルスチェックは通れません。
            log.error(CsjProperties.getMsgLog(CsjMessageKey.E1005, "", getBatchId()));
            isOk = false;
        }

        if (!isOk) {
            throw new Throwable("dbHeathCheck error");
        }

    }
//
//    public ApplicationContext getApplicationContext() {
//        return applicationContext;
//    }
//
//    public void setApplicationContext(ApplicationContext applicationContext) {
//        this.applicationContext = applicationContext;
//    }

    public Properties getCommonProp() {
        return commonProp;
    }

    public void setCommonProp(Properties commonProp) {
        this.commonProp = commonProp;
    }

    public List<String> getDbHeathLst() {
        return dbHeathLst;
    }

    public void setDbHeathLst(List<String> dbHeathLst) {
        this.dbHeathLst = dbHeathLst;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getBatchNm() {
        return batchNm;
    }

    public void setBatchNm(String batchNm) {
        this.batchNm = batchNm;
    }

	public String getCsjColumnKey() {
		return csjColumnKey;
	}

	public void setCsjColumnKey(String csjColumnKey) {
		this.csjColumnKey = csjColumnKey;
	}
}
