package ketangpai.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ketangpai.dao.HomeworkContentDao;
import ketangpai.entity.Homework_content;
import ketangpai.service.HomeworkContentService;


@Service
public class HomeworkContentServiceImpl implements HomeworkContentService {
	
	@Autowired
	private HomeworkContentDao dao;
	
	@Override
	public Homework_content getHomeworkContentById(Integer id) {
		return dao.getHomeworkContentById(id);
	}

	@Override
	public List<Homework_content> getAllHomeworkContent(String[] homeworkContentList) {
		return dao.getAllHomeworkContent(homeworkContentList);
	}
}
