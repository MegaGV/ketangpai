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
	private Integer review;
	private Date upload_date;
	
	public Homework_upload() {
		super();
	}
	
	public Homework_upload(Integer student, Integer homework) {
		super();
		this.student = student;
		this.homework = homework;
	}
	
	public Homework_upload(Integer id, String content, String file, Integer student, Integer homework, Integer score,
			Integer complete, Integer review, Date upload_date) {
		super();
		this.id = id;
		this.content = content;
		this.file = file;
		this.student = student;
		this.homework = homework;
		this.score = score;
		this.complete = complete;
		this.review = review;
		this.upload_date = upload_date;
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

	public Integer getReview() {
		return review;
	}

	public void setReview(Integer review) {
		this.review = review;
	}

	public Date getUpload_date() {
		return upload_date;
	}

	public void setUpload_date(Date upload_date) {
		this.upload_date = upload_date;
	}
	
	
}
