package cn.itcast.ssm.batch;
import javax.annotation.Resource;

import org.apache.log4j.Logger;

import cn.itcast.ssm.mapper.original.TItemMapper;

/**
 * バッチ処理
 */
public class Batch0001Task {

	protected Logger log = Logger.getLogger(Batch0001Task.class);
	
	@Resource
	private TItemMapper tItemMapper;
	
	public void runInterval() throws Exception {
		System.out.println(111);
		log.debug("runInterval 実行");
	}
	
	public void runBackup() throws Exception {
		log.debug("runBackup 実行");
	}
	
	public void runDelete() throws Exception {
		log.debug("runDelete 実行");
	}
}
