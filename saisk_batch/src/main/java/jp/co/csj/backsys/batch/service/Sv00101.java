/*****************************************************************************
 * プログラム ：Sv00502.java
 * 説明 ：xxxxxx.
 *****************************************************************************
 * 変更履歴： 2020.xx.xx : 新規作成
 ******************************************************************************/

package jp.co.csj.backsys.batch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jp.co.csj.backsys.mapper.original.PersonMapper;
import jp.co.csj.backsys.po.original.Person;

@Component
public class Sv00101 {


	@Autowired
	private PersonMapper personMapper;
	

	@Transactional(rollbackFor = Exception.class)
	public void update() throws Exception {
		Person person = new Person();
		person.setId(1);
		person.setName("更新後1155");
		personMapper.updateByPrimaryKey(person);
		System.out.println("更新");

		//throw new Exception("ccccccccccccccc");
		// Exceptionが発生するとロールバックされる。通常に終わるとコミットされる。
		// String str = null;
		// System.out.println(str.length());
	}

	public void show() {
		Person p = personMapper.selectByPrimaryKey(1);
			System.out.println(p.getId() + "-" + p.getName());
	}
}
