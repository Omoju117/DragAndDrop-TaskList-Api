package dev.itboot.rest.repository.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.itboot.rest.mapper.TaskMapper;
import dev.itboot.rest.model.Task;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskService {
	//トランザクションでまとめる
	@Autowired
	private TaskMapper mapper;
	
	public void truncate(Task task) {
		mapper.truncate(task.getUserName());
	}
	

}
