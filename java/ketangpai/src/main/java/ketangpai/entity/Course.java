package ketangpai.entity;

public class Course {
	private Integer id;
	private String name;
	private String introduce;
	private String code;
	private Integer teacher;
	private String assistants;
	private String students;
	private String homeworks;
	
	public Course() {
		super();
	}
	
	public Course(Integer id, String name, String introduce, String code, Integer teacher, String assistants,
			String students, String homeworks) {
		super();
		this.id = id;
		this.name = name;
		this.introduce = introduce;
		this.code = code;
		this.teacher = teacher;
		this.assistants = assistants;
		this.students = students;
		this.homeworks = homeworks;
	}
	
	public Course(String name, String introduce, String code, Integer teacher) {
		super();
		this.name = name;
		this.introduce = introduce;
		this.code = code;
		this.teacher = teacher;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getTeacher() {
		return teacher;
	}

	public void setTeacher(Integer teacher) {
		this.teacher = teacher;
	}

	public String getAssistants() {
		return assistants;
	}

	public void setAssistants(String assistants) {
		this.assistants = assistants;
	}

	public String getStudents() {
		return students;
	}

	public void setStudents(String students) {
		this.students = students;
	}

	public String getHomeworks() {
		return homeworks;
	}

	public void setHomeworks(String homeworks) {
		this.homeworks = homeworks;
	}
	
	
}
