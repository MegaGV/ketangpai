package ketangpai.dao;

import org.apache.ibatis.annotations.Mapper;

import ketangpai.entity.User;

@Mapper
public interface UserDao {
	public User getUserByAccount(String account);
	public void register(User user);
}
