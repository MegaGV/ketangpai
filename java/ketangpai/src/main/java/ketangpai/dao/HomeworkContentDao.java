package ketangpai.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ketangpai.entity.Homework_content;

@Mapper
public interface HomeworkContentDao {
	public Homework_content getHomeworkContentById(Integer id);
	public String getHomeworkContentName(Integer id);
	public Integer getHomeworkContentIdByName(String name);
	public List<Homework_content> getAllHomeworkContent(String[] homeworkContentList);
	public void createHomework(Homework_content homework);
	public void updateReviewed(@Param("id")Integer id, @Param("reviewed")Integer reviewed);
}
