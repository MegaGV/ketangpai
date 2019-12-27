package ketangpai.entity;

import java.util.Date;

public class Homework_content {
	private Integer id;
	private String name;
	private String introduce;
	private Date starttime;
	private Date endtime;
	private Integer course;
	private Integer reviewed;
	private Integer uploaded;
	
	public Homework_content(Integer id, String name, String introduce, Date starttime, Date endtime, Integer course,
			Integer reviewed, Integer uploaded) {
		super();
		this.id = id;
		this.name = name;
		this.introduce = introduce;
		this.starttime = starttime;
		this.endtime = endtime;
		this.course = course;
		this.reviewed = reviewed;
		this.uploaded = uploaded;
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

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public Integer getCourse() {
		return course;
	}

	public void setCourse(Integer course) {
		this.course = course;
	}

	public Integer getReviewed() {
		return reviewed;
	}

	public void setReviewed(Integer reviewed) {
		this.reviewed = reviewed;
	}

	public Integer getUploaded() {
		return uploaded;
	}

	public void setUploaded(Integer uploaded) {
		this.uploaded = uploaded;
	}
	
	
}
