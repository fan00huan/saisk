package spring.test;

import java.util.List;

public interface UserMapper {
	public List<User> getUserList();
	public void updateUser(User user);
}