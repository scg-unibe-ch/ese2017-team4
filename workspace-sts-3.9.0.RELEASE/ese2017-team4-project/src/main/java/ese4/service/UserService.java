package ese4.service;

import ese4.model.User;

public interface UserService {
	public User findUserByName(String name);
	public void saveUser(User user);
}