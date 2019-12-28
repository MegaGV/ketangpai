package ketangpai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ketangpai.service.CourseService;
import ketangpai.entity.Course;
import ketangpai.entity.User;


@RestController
@RequestMapping("/CourseController")
@CrossOrigin(allowCredentials = "true")
public class CourseController {

	@Autowired
	private CourseService service;
	
	@GetMapping("/getCourseById")
	public Course getCourseById(Integer id) {
		return service.getCourseById(id);
	}
	
	@GetMapping("/getCourseTeacher")
	public User getCourseTeacher(Integer id) {
		return service.getCourseTeacher(id);
	}
	
	@GetMapping("/getCourseAssistants")
	public List<User> getCourseAssistants(Integer id) {
		return service.getCourseAssistants(id);
	}
	
	@GetMapping("/getCourseStudents")
	public List<User> getCourseStudents(Integer id) {
		return service.getCourseStudents(id);
	}
	
	@GetMapping("/getAllCourses")
	public List<Course> getAllCourses(String courses) {
		String[] courseList= courses.split(",");
		return service.getAllCourses(courseList);
	}
	
	@GetMapping("/joinCourse")
	public String joinCourse(Integer id, String code) {
		return service.joinCourse(id, code);
	}
	
	@PostMapping("/exitCourse")
	public void exitCourse(Integer id, Integer cid) {
		service.exitCourse(id, cid);
	}
	
	@PostMapping("/fileCourse")
	public void fileCourse(Integer id, Integer cid) {
		service.fileCourse(id, cid);
	}
	
	@PostMapping("/unfileCourse")
	public void unfileCourse(Integer id, Integer cid) {
		service.unfileCourse(id, cid);
	}
	
	@PostMapping("/createCourse")
	@ResponseBody
	public void createCourse(@RequestBody Course course) {
		service.createCourse(course);
	}
	
	@PostMapping("/editCourse")
	@ResponseBody
	public void editCourse(@RequestBody Course course) {
		service.editCourse(course);
	}
	
	@PostMapping("/deleteCourse")
	public void deleteCourse(Integer id, Integer cid) {
		service.deleteCourse(id, cid);
	}
}
