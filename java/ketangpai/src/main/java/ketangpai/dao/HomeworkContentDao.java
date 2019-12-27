package ketangpai.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ketangpai.entity.Homework_content;

@Mapper
public interface HomeworkContentDao {
	public Homework_content getHomeworkContentById(Integer id);
	public List<Homework_content> getAllHomeworkContent(String[] homeworkContentList);
}
