package ketangpai.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ketangpai.dao.HomeworkContentDao;
import ketangpai.dao.HomeworkUploadDao;
import ketangpai.entity.Homework_content;
import ketangpai.entity.Homework_upload;
import ketangpai.service.HomeworkUploadService;


@Service
public class HomeworkUploadServiceImpl implements HomeworkUploadService {
	
	@Autowired
	private HomeworkUploadDao dao;
	
	@Autowired
	private HomeworkContentDao contentdao;
	
	@Override
	public Homework_upload getHomeworkUploadById(Integer id, Integer hid) {
		return dao.getHomeworkUploadById(id,hid);
	}
	
	@Override
	public Homework_upload getHomeworkUpload(Integer id) {
		return dao.getHomeworkUpload(id);
	}
	
	@Override
	public List<Homework_upload> getHomeworkAllUpload(Integer id) {
		return dao.getHomeworkAllUpload(id);
	}
	
	@Override
	public void Scoring(Integer id, Integer score) {
		Homework_upload upload = dao.getHomeworkUpload(id);
		Homework_content content = contentdao.getHomeworkContentById(upload.getHomework());
		if (upload.getReview() == 0) {
			contentdao.updateReviewed(content.getId(), content.getReviewed() + 1);
		}
		dao.Scoring(id, score);
	}
	
	@Override
	public void submitHomeworkUpload(Homework_upload upload) {
		Date upload_date = new Date();
		upload.setUpload_date(upload_date);
		dao.submitHomeworkUpload(upload);
	}
}
