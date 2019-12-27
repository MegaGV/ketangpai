package ketangpai.service;

import ketangpai.entity.User;

public interface UserService {
	public void register(User user);
	public String login(String username, String password);
	public User getUserById(Integer id);
}
