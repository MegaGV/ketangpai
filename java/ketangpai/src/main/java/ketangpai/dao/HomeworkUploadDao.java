package ketangpai.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ketangpai.entity.Homework_upload;;

@Mapper
public interface HomeworkUploadDao {
	public Homework_upload getHomeworkUploadById(@Param("id")Integer id, @Param("hid")Integer hid);
}
