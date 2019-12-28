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
	
	@GetMapping("/getHomeworkUpload")
	public Homework_upload getHomeworkUpload(Integer id) {
		return service.getHomeworkUpload(id);
	}
	
	@GetMapping("/getHomeworkAllUpload")
	public List<Homework_upload> getHomeworkAllUpload(Integer id) {
		return service.getHomeworkAllUpload(id);
	}
	
	@PostMapping("/Scoring")
	public void Scoring(Integer id, String score) {
		service.Scoring(id, Integer.parseInt(score));
	}
	
	@ResponseBody
	@PostMapping("/submitHomeworkUpload")
	public void submitHomeworkUpload(@RequestBody Homework_upload upload) {
		service.submitHomeworkUpload(upload);
	}
	
	
	
}
