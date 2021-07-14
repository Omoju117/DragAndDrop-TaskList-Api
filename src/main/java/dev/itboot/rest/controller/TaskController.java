package dev.itboot.rest.controller;

import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import dev.itboot.rest.model.Task;
import dev.itboot.rest.model.Users;
import dev.itboot.rest.repository.TaskRepository;
import dev.itboot.rest.repository.service.TaskService;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "https://omoju117.github.io")
//@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@RestController
public class TaskController {
	Logger logger = Logger.getLogger("DragAndDropTaskList-Api");
	ConsoleHandler cHandler = new ConsoleHandler();
	Gson gson = new Gson();
	
	@Autowired
	private TaskRepository repository;
	
	@Autowired
	private TaskService service;
	
	private final String DEFAULT_USER_NAME = "default";

	@GetMapping("/")
	List<Task> findAll() {
		return repository.findAll();		
	}
	
	
	@PostMapping("/")
	String save(@RequestBody Task[] taskList) {
		cHandler.setLevel(Level.SEVERE);
		logger.addHandler(cHandler);
		String statusMsg = "DB access is OK";
		
		try {
			// ユーザーがサインインしていない場合は空回し
			if(taskList.length > 0 && !taskList[0].getUserName().equals(DEFAULT_USER_NAME)) {
				service.truncate(taskList[0]);
				for(Task task:taskList) {
					repository.save(task);
				}
				System.out.println("save is successed!");	
			}
		} catch(Exception e) {
			StringBuilder sb = new StringBuilder(); 
			logger.log(Level.SEVERE,"error: " , e);
			sb.append(e.getMessage() + "@" + e.getCause() + "@");
			StackTraceElement[] steArr = e.getStackTrace();
			for(StackTraceElement ste :steArr) {
				sb.append(ste.toString() + "@") ;
			}
			return gson.toJson(sb.toString());
		}
		return gson.toJson(statusMsg);
	}
}
