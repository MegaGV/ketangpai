package ketangpai.entity;

public class User {
	private String account;
	private String password;
	private String name;
	private String identity;
	private String school;
	private String schoolId;
	private String email;
	private String phone;
	private String courses;
	private String fieldcourses;
	
	
	
	public User(String account, String password, String name, String identity, String school, String schoolId,
			String email, String phone, String courses, String fieldcourses) {
		super();
		this.account = account;
		this.password = password;
		this.name = name;
		this.identity = identity;
		this.school = school;
		this.schoolId = schoolId;
		this.email = email;
		this.phone = phone;
		this.courses = courses;
		this.fieldcourses = fieldcourses;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
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
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
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
	public String getFieldCourses() {
		return fieldcourses;
	}
	public void setFieldCourses(String fieldcourses) {
		this.fieldcourses = fieldcourses;
	}
}
