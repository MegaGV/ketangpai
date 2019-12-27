package ketangpai.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ketangpai.dao.UserDao;
import ketangpai.entity.User;
import ketangpai.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao dao;
	
	@Override
	public void register(User user) {
		dao.register(user);
	}
	
	@Override
	public String login(String username, String password) {
		User user = dao.getUserByUsername(username);
		if(user == null)
			return "用户名不存在";
		if(user.getPassword().equals(password))
			return user.getIdentity();
		else
			return "密码错误";
	}
	
	@Override
	public User getUserById(Integer id) {
		return dao.getUserById(id);
	}
}
