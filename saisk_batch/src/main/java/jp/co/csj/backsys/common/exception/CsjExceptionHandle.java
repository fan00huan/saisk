/*****************************************************************************
 * プログラム ：CsjException.java
 * 説明 ：エクセプションクラス.
 *****************************************************************************
 * 変更履歴： 2020.xx.xx : 新規作成
 ******************************************************************************/

package jp.co.csj.backsys.common.exception;

import org.apache.log4j.Logger;

public class CsjExceptionHandle {

    private static Logger log = Logger.getLogger(CsjExceptionHandle.class);

	private Throwable e;
	public CsjExceptionHandle(Throwable e) {
		this.e = e;
	}

	public void outputLog() {
        log.error(e.getClass().getName());
        log.error(e.getMessage());
        e.printStackTrace();
        for (StackTraceElement ste : e.getStackTrace()) {
            log.error(ste.toString());
        }
	}
}
