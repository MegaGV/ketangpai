package ketangpai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ketangpai.service.UserService;
import ketangpai.entity.User;

@RestController
@RequestMapping("/UserController")
@CrossOrigin(allowCredentials = "true")
public class UserController {

	@Autowired
	private UserService service;
	
	@PostMapping("/register")
	@ResponseBody
	public void register(@RequestBody User user) {
		System.out.println("!!!");
		service.register(user);
	}
	
	@GetMapping("/getUserByAccount")
	public User getUserByAccount(String account) {
		return service.getUserByAccount(account);
	}
	
}
