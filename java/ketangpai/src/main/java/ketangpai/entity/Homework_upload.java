package ketangpai.entity;

import java.util.Date;

public class Homework_upload {
	private Integer id;
	private String content;
	private String file;
	private Integer student;
	private Integer homework;
	private Integer score;
	private Integer complete;
	private Integer riview;
	private Date upload_data;
	
	public Homework_upload(Integer id, String content, String file, Integer student, Integer homework, Integer score,
			Integer complete, Integer riview, Date upload_data) {
		super();
		this.id = id;
		this.content = content;
		this.file = file;
		this.student = student;
		this.homework = homework;
		this.score = score;
		this.complete = complete;
		this.riview = riview;
		this.upload_data = upload_data;
	}

	public Homework_upload(Integer id, String content, String file, Integer student, Integer homework, Integer score,
			Integer complete, Integer riview) {
		super();
		this.id = id;
		this.content = content;
		this.file = file;
		this.student = student;
		this.homework = homework;
		this.score = score;
		this.complete = complete;
		this.riview = riview;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Integer getStudent() {
		return student;
	}

	public void setStudent(Integer student) {
		this.student = student;
	}

	public Integer getHomework() {
		return homework;
	}

	public void setHomework(Integer homework) {
		this.homework = homework;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getComplete() {
		return complete;
	}

	public void setComplete(Integer complete) {
		this.complete = complete;
	}

	public Integer getRiview() {
		return riview;
	}

	public void setRiview(Integer riview) {
		this.riview = riview;
	}

	public Date getUpload_data() {
		return upload_data;
	}

	public void setUpload_data(Date upload_data) {
		this.upload_data = upload_data;
	}
	
	
}
