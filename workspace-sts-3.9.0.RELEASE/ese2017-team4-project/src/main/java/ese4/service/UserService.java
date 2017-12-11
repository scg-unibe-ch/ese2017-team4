package ese4.service;

import ese4.model.User;

/**
 * 
 * @author ESE04
 * Service of the User objects. Finds, saves and deletes them.
 *
 */
public interface UserService {
	public User findUserByName(String name);
	public void saveUser(User user);
}