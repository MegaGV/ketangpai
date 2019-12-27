package ketangpai.service;

import java.util.List;

import ketangpai.entity.Homework_content;

public interface HomeworkContentService {
	public Homework_content getHomeworkContentById(Integer id);
	public List<Homework_content> getAllHomeworkContent(String[] homeworkContentList);
}
