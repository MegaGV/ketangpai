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
	public User getUserByAccount(String account) {
		return dao.getUserByAccount(account);
	}
}
