package dev.itboot.rest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import dev.itboot.rest.model.Task;

@Mapper
public interface TaskMapper {
	// ここにSQLと紐付けるメソッドを記述する
	List<Task> selectAllTasks();
	
	int insertTask(Task task);
	
	void truncate(String userName);
}
