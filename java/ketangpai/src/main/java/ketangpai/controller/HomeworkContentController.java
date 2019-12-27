package ketangpai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ketangpai.entity.Homework_content;
import ketangpai.service.HomeworkContentService;


@RestController
@RequestMapping("/HomeworkContentController")
@CrossOrigin(allowCredentials = "true")
public class HomeworkContentController {

	@Autowired
	private HomeworkContentService service;
	
	@GetMapping("/getHomeworkContentById")
	public Homework_content getHomeworkContentById(Integer id) {
		return service.getHomeworkContentById(id);
	}
	
	@GetMapping("/getAllHomeworkContent")
	public List<Homework_content> getAllHomeworkContent(String homework_content) {
		String[] homeworkContentList= homework_content.split(",");
		return service.getAllHomeworkContent(homeworkContentList);
	}
	
}
