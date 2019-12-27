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
import ketangpai.exception.UserNotFoundException;
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
		service.register(user);
	}
	
	@GetMapping("/login")
	public String login(String username, String password) {
		return service.login(username, password);
	}
	
	@GetMapping("/getUserById")
	public User getUserById(Integer id) {
		User user = service.getUserById(id);
		if (user == null) {
			throw new UserNotFoundException(id);
		}
		return user;
	}
	
}
