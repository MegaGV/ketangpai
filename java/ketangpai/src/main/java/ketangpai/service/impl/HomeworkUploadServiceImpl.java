package ketangpai.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ketangpai.dao.HomeworkUploadDao;
import ketangpai.entity.Homework_upload;
import ketangpai.service.HomeworkUploadService;


@Service
public class HomeworkUploadServiceImpl implements HomeworkUploadService {
	
	@Autowired
	private HomeworkUploadDao dao;
	
	@Override
	public Homework_upload getHomeworkUploadById(Integer id, Integer hid) {
		return dao.getHomeworkUploadById(id,hid);
	}
}
