package ketangpai.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ketangpai.entity.User;

@Mapper
public interface UserDao {
	public User getUserById(Integer id);
	public User getUserByUsername(String username);
	public void register(User user);
	
	public String getUserCourses(Integer id);
	public String getUserFieldCourses(Integer id);
	
	public void updateCourses(@Param("id")Integer id, @Param("courses")String courses);
	public void updateFieldCourses(@Param("id")Integer id, @Param("fieldcourses")String fieldcourses);
}
