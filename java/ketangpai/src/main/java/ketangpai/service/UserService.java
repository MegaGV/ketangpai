package ketangpai.service;

import ketangpai.entity.User;

public interface UserService {
	public void register(User user);
	public User getUserByAccount(String account);
}
