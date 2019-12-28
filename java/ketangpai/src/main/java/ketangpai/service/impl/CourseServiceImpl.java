package ketangpai.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ketangpai.dao.CourseDao;
import ketangpai.dao.UserDao;
import ketangpai.entity.Course;
import ketangpai.entity.User;
import ketangpai.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	private CourseDao dao;
	
	@Autowired
	private UserDao userdao;
	
	@Override
	public Course getCourseById(Integer id) {
		return dao.getCourseById(id);
	}
	
	@Override
	public List<Course> getAllCourses(String[] courseList) {
		return dao.getAllCourses(courseList);
	}
	
	@Override
	public void createCourse(Course course) {
		dao.createCourse(course);
		
		Integer cid = dao.getCourseIdByCode(course.getCode());
		
		String courses = userdao.getUserCourses(course.getTeacher());
		if(courses != "")
			courses += ",";
		courses += cid.toString();
		
		userdao.updateCourses(course.getTeacher(), courses);
		
	}
	
	@Override
	public void editCourse(Course course) {
		dao.editCourse(course);
	}
	
	@Override
	public void deleteCourse(Integer id, Integer cid) {
		deleteCourseFromUser(id, cid);
		
		String students = dao.getCourseStudents(cid);
		if(!students.isEmpty()) {
			String[] studentList = students.split(",");
			for(int i = 0; i < studentList.length; i++)
				deleteCourseFromUser(Integer.parseInt(studentList[i]),cid);
		}
		dao.deleteCourse(cid);
	}
	
	@Override
	public String joinCourse(Integer id, String code) {
		
		Integer courseId = dao.getCourseIdByCode(code);
		if(courseId != null) {
			String students = dao.getCourseStudents(courseId);
			if(!students.isEmpty())
				students += ',';
			students += id.toString();
			
			String courses = userdao.getUserCourses(id);
			String fieldcourses = userdao.getUserFieldCourses(id);
			if(courses.isEmpty() && fieldcourses.isEmpty()) {
				userdao.updateCourses(id, courseId.toString());
				dao.updateCourseStudents(courseId, students);
				return "加课成功!!";
			}
			{
				String Allcourses = "";
				if(!courses.isEmpty() && !fieldcourses.isEmpty()) {
					Allcourses = courses + "," + fieldcourses;
				}
				else if(courses.isEmpty()) {
					Allcourses = fieldcourses;
				}
				else
					Allcourses = courses;
				
				String[] courseList= Allcourses.split(",");
				for(int i = 0; i <courseList.length; i++)
					if( Integer.parseInt(courseList[i]) == courseId)
						return "您已加入此课程";
			}
			if(courses.isEmpty()) {
				userdao.updateCourses(id, courseId.toString());
				dao.updateCourseStudents(courseId, students);
				return "加课成功!";
			}
			courses  = courses + "," + courseId.toString();
			userdao.updateCourses(id, courses);
			dao.updateCourseStudents(courseId, students);
			return "加课成功";
			
		}
		return "无此加课码的课程";
	}
	
	@Override
	public void exitCourse(Integer id, Integer cid) {
		String students = dao.getCourseStudents(cid);
		String[] studentList = students.split(",");
		students = "";
		for(int i = 0; i <studentList.length; i++) {
			if( Integer.parseInt(studentList[i]) != id) {
				if(!students.isEmpty())
					students += ",";
				students += studentList[i];
			}
		}
		dao.updateCourseStudents(cid, students);
		
		deleteCourseFromUser(id, cid);
	}
	
	@Override
	public void fileCourse(Integer id, Integer cid) {
		String courses = userdao.getUserCourses(id);
		String fieldcourses = userdao.getUserFieldCourses(id);
		
		String[] courseList = courses.split(",");
		courses = "";
		for(int i = 0; i <courseList.length; i++) {
			if( Integer.parseInt(courseList[i]) != cid) {
				if(!courses.isEmpty())
					courses += ",";
				courses += courseList[i];
			}
		}
		
		if(!fieldcourses.isEmpty())
			fieldcourses += ",";
		fieldcourses += cid.toString();
		
		userdao.updateCourses(id, courses);
		userdao.updateFieldCourses(id, fieldcourses);
		
	}
	
	@Override
	public void unfileCourse(Integer id, Integer cid) {
		String courses = userdao.getUserCourses(id);
		String fieldcourses = userdao.getUserFieldCourses(id);
		
		String[] courseList = fieldcourses.split(",");
		fieldcourses = "";
		for(int i = 0; i <courseList.length; i++) {
			if( Integer.parseInt(courseList[i]) != cid) {
				if(!fieldcourses.isEmpty())
					fieldcourses += ",";
				fieldcourses += courseList[i];
			}
		}
		
		if(!courses.isEmpty())
			courses += ",";
		courses += cid.toString();
		
		userdao.updateCourses(id, courses);
		userdao.updateFieldCourses(id, fieldcourses);
		
	}

	@Override
	public User getCourseTeacher(Integer id) {
		Integer userid = dao.getCourseTeacher(id);
		User teacher = userdao.getUserById(userid);
		return teacher;
	}

	@Override
	public List<User> getCourseAssistants(Integer id) {
		String userids = dao.getCourseAssistants(id);
		String[] useridList = userids.split(",");
		List<User> assistants = new ArrayList<User>();
		for(int i = 0; i < useridList.length; i++)
			assistants.add(userdao.getUserById(Integer.parseInt(useridList[i])));
		return assistants;
	}

	@Override
	public List<User> getCourseStudents(Integer id) {
		String userids = dao.getCourseStudents(id);
		String[] useridList = userids.split(",");
		List<User> students = new ArrayList<User>();
		for(int i = 0; i < useridList.length; i++)
			students.add(userdao.getUserById(Integer.parseInt(useridList[i])));
		return students;
	}
	
	private void deleteCourseFromUser(Integer id, Integer cid) {
		String courses = userdao.getUserCourses(id);
		String fieldcourses = userdao.getUserFieldCourses(id);
		Boolean isIn = false;
		if(!courses.isEmpty())
		{
			
			String[] courseList= courses.split(",");
			courses = "";
			
			for(int i = 0; i <courseList.length; i++) {
				if( Integer.parseInt(courseList[i]) == cid) 
					isIn = true;
				else {
					if(!courses.isEmpty())
						courses += ",";
					courses += courseList[i];
				}
			}
		}
		if(isIn) {
			userdao.updateCourses(id, courses);
		}
		else {
			String[] courseList= fieldcourses.split(",");
			fieldcourses = "";
			for(int i = 0; i <courseList.length; i++) {
				if( Integer.parseInt(courseList[i]) != cid) {
					if(!fieldcourses.isEmpty())
						fieldcourses += ",";
					fieldcourses += courseList[i];
				}
			}
			userdao.updateFieldCourses(id, fieldcourses);
		}
	}
}
