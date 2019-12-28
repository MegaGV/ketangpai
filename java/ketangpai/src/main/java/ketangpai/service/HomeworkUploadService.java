package ketangpai.service;

import java.util.List;

import ketangpai.entity.Homework_upload;

public interface HomeworkUploadService {
	public Homework_upload getHomeworkUploadById(Integer id, Integer hid);
	public Homework_upload getHomeworkUpload(Integer id);
	public List<Homework_upload> getHomeworkAllUpload(Integer id);
	public void Scoring(Integer id, Integer score);
	
	public void submitHomeworkUpload( Homework_upload upload);
}
