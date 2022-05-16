package spring.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class SpringBean {


	@Autowired
	private UserMapper userMapper;


//	private final UserMapper userMapper;
//
//	public SpringBean(UserMapper mp) {
//		this.userMapper = mp;
//	}

	@Transactional(rollbackFor = Exception.class)
	public void update() throws Exception {
		User user = new User();
		user.setId("5");
		user.setName("更新後1155");
		userMapper.updateUser(user);
		System.out.println("更新");

		//throw new Exception("ccccccccccccccc");
		// Exceptionが発生するとロールバックされる。通常に終わるとコミットされる。
		// String str = null;
		// System.out.println(str.length());
	}

	public void show() {
		List<User> list = userMapper.getUserList();
		for (User user : list) {
			System.out.println(user.getId() + "-" + user.getName());
		}
	}
}
