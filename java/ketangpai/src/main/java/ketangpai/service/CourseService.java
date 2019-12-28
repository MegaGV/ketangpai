package ketangpai.service;

import java.util.List;


import ketangpai.entity.User;
import ketangpai.entity.Course;

public interface CourseService {
	public Course getCourseById(Integer id);
	public List<Course> getAllCourses(String[] courseList);
	public String joinCourse(Integer id, String code);
	public void exitCourse(Integer id, Integer cid);
	public void fileCourse(Integer id, Integer cid);
	public void unfileCourse(Integer id, Integer cid);
	
	public User getCourseTeacher(Integer id);
	public List<User> getCourseAssistants(Integer id);
	public List<User> getCourseStudents(Integer id);
	
	public void createCourse(Course course);
	public void editCourse(Course course);
	public void deleteCourse(Integer id, Integer cid);
}
