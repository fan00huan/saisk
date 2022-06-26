/*****************************************************************************
 * プログラム ：Bt001.java
 * 説明 ：XXXXバッチ.
 *****************************************************************************
 * 変更履歴： 2020.xx.xx : 新規作成
 ******************************************************************************/

package jp.co.csj.backsys.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Service;

import jp.co.csj.backsys.batch.abs.AbsBatch;
import jp.co.csj.backsys.batch.check.Ck001;
import jp.co.csj.backsys.batch.service.Sv00101;

@Service
public class Bt001 extends AbsBatch {

    // チェッククラスxx
	@Autowired
    private Ck001 ck001;
	@Autowired
    private Sv00101 sv00101;


    /**
     * バッチ入口.
     * @param args 引数
     */
    public static void main(String[] args) {
        // 処理を行う。
        new Bt001().produce();
        
        
		ApplicationContext context = new FileSystemXmlApplicationContext(
				"classpath:spring/SprapplicationContext.xml");
		
        context.getBean("Bt001")
    }

    /**
     * 主処理.
     */
    @Override
    public void run() throws Throwable {

    	// ここから、業務ロジックを処理する
        sv00101.update();
    }

    /**
     * プロパティファイルを読み込み.
     */
    @Override
    public void setup() throws Throwable {

        // バッチIDの設定
        setBatchId(Bt001.class.getSimpleName());

        super.setup();

    }

    /**
     * 環境変数をチェックする.
     */
    @Override
    public void checkSys() throws Throwable {
    	ck001.checkSys();
    }

    /**
     * プロパティ存在チェック処理を行う.
     */
    @Override
    public void checkProperties() throws Throwable {
    	ck001.checkProperties();
    }

	@Override
	public void init() throws Throwable {
		// TODO 自動生成されたメソッド・スタブ
		
	}


}
