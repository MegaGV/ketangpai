package ketangpai.exception;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Integer id;

	public UserNotFoundException(Integer id) {
		super("user not exist");
		this.id = id;
	}

	public Integer getUsername() {
		return id;
	}

	public void setUsername(Integer id) {
		this.id = id;
	}
	
	
}
