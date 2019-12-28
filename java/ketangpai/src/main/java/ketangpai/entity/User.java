package ketangpai.entity;

public class User{
	private Integer id;
	private String username;
	private String password;
	private String name;
	private String identity;
	private String school;
	private String schoolID;
	private String email;
	private String phone;
	private String courses;
	private String fieldcourses;
	
	public User() {
		super();
	}
	
	public User(Integer id,String username, String password, String name, String identity, String school, String schoolID,
			String email, String phone, String courses, String fieldcourses) {
		super();
		this.setId(id);
		this.username = username;
		this.password = password;
		this.name = name;
		this.identity = identity;
		this.school = school;
		this.schoolID = schoolID;
		this.email = email;
		this.phone = phone;
		this.courses = courses;
		this.fieldcourses = fieldcourses;
	}
	
	public User(Integer id,String username, String password, String name, String identity, String school, String schoolID,
			String email, String phone) {
		super();
		this.setId(id);
		this.username = username;
		this.password = password;
		this.name = name;
		this.identity = identity;
		this.school = school;
		this.schoolID = schoolID;
		this.email = email;
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getschoolID() {
		return schoolID;
	}

	public void setschoolID(String schoolID) {
		this.schoolID = schoolID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCourses() {
		return courses;
	}

	public void setCourses(String courses) {
		this.courses = courses;
	}

	public String getFieldcourses() {
		return fieldcourses;
	}

	public void setFieldcourses(String fieldcourses) {
		this.fieldcourses = fieldcourses;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

}
