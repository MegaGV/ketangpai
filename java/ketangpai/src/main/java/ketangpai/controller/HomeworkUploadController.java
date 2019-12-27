package ketangpai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ketangpai.entity.Homework_upload;
import ketangpai.service.HomeworkUploadService;

@RestController
@RequestMapping("/HomeworkUploadController")
@CrossOrigin(allowCredentials = "true")
public class HomeworkUploadController {

	@Autowired
	private HomeworkUploadService service;
	
	@GetMapping("/getHomeworkUploadById")
	public Homework_upload getHomeworkUploadById(Integer id, Integer hid) {
		return service.getHomeworkUploadById(id ,hid);
	}
	
}
