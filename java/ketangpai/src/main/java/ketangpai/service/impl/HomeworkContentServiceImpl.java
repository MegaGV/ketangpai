package ketangpai.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ketangpai.dao.CourseDao;
import ketangpai.dao.HomeworkContentDao;
import ketangpai.dao.HomeworkUploadDao;
import ketangpai.entity.Homework_content;
import ketangpai.entity.Homework_upload;
import ketangpai.service.HomeworkContentService;


@Service
public class HomeworkContentServiceImpl implements HomeworkContentService {
	
	@Autowired
	private HomeworkContentDao dao;
	
	@Autowired
	private CourseDao coursedao;
	
	@Autowired
	private HomeworkUploadDao uploaddao;
	
	@Override
	public Homework_content getHomeworkContentById(Integer id) {
		return dao.getHomeworkContentById(id);
	}
	
	@Override
	public String getHomeworkContentName(Integer id) {
		return dao.getHomeworkContentName(id);
	}

	@Override
	public List<Homework_content> getAllHomeworkContent(String[] homeworkContentList) {
		return dao.getAllHomeworkContent(homeworkContentList);
	}

	@Override
	public void createHomework(Homework_content homework) {
		Date date = new Date();
		homework.setStarttime(date);
		dao.createHomework(homework);
		
		String homeworks = coursedao.getCourseHomeworks(homework.getCourse());
		if (!homeworks.isEmpty())
			homeworks += ",";
		Integer hid = dao.getHomeworkContentIdByName(homework.getName());
		homeworks += hid.toString();
		coursedao.updateCourseHomeworks(homework.getCourse(), homeworks);
		
		String students = coursedao.getCourseStudents(homework.getCourse());
		if(!students.isEmpty()) {
			String[] studentList = students.split(",");
			for(int i = 0; i < studentList.length; i++) {
				Homework_upload upload = new Homework_upload(Integer.parseInt(studentList[i]), hid);
				uploaddao.createHomework_upload(upload);
			}
		}
	}
}
