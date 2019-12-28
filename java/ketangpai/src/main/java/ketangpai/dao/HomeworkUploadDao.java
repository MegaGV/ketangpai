package ketangpai.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ketangpai.entity.Homework_upload;;

@Mapper
public interface HomeworkUploadDao {
	public Homework_upload getHomeworkUpload(Integer id);
	public Homework_upload getHomeworkUploadById(@Param("student")Integer student, @Param("homework")Integer homework);
	public void createHomework_upload(Homework_upload upload);
	public void Scoring(@Param("id")Integer id, @Param("score")Integer score);
	public List<Homework_upload> getHomeworkAllUpload(Integer id);
	
	public void submitHomeworkUpload(Homework_upload upload);
}
