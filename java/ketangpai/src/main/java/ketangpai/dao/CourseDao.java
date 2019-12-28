package ketangpai.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ketangpai.entity.Course;

@Mapper
public interface CourseDao {
	public Course getCourseById(Integer id);
	public List<Course> getAllCourses(String[] courseList);
	public Integer getCourseIdByCode(String code);
	public Integer getCourseTeacher(Integer id);
	public String getCourseAssistants(Integer id);
	public String getCourseStudents(Integer id);
	public void updateCourseStudents(@Param("id")Integer id, @Param("students")String students);
	public void createCourse(Course course);
	public void editCourse(Course course);
	public void deleteCourse(Integer id);
	
	public String getCourseHomeworks(Integer id);
	public void updateCourseHomeworks(@Param("id")Integer id, @Param("homeworks")String homeworks);
}
