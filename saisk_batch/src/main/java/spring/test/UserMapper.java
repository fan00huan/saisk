package spring.test;

import java.util.List;
//01
public interface UserMapper {
	public List<User> getUserList();
	public void updateUser(User user);
}