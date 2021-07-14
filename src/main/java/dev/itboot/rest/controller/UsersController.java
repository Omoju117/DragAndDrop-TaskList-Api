package dev.itboot.rest.controller;

import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import dev.itboot.rest.model.Task;
import dev.itboot.rest.model.Users;
import dev.itboot.rest.repository.service.UsersService;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "https://omoju117.github.io")
//@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/api/users")
@RequiredArgsConstructor
@RestController
public class UsersController {
	Logger logger = Logger.getLogger("DragAndDropTaskList-Api");
	ConsoleHandler cHandler = new ConsoleHandler();
	Gson gson = new Gson();
	
	
	@Autowired
	private UsersService service;
	
	@PostMapping("/signUp")
	String signUp(@RequestBody Users user) {
		try {
			System.out.println(user.getUserName() + ":" + user.getPassword());
			if(service.checkUserAlreadyExist(user) == 0) {
				service.insertUserInfo(user);
				System.out.println("Save is completed");
			} else {
				System.out.println("targetUser is Already Exists");
				return gson.toJson("ErrorCode:001");
			}
		} catch(Exception e) {
			System.out.println("DB Access Error");
			return gson.toJson(false);
		}
			
		return gson.toJson(true);
	}
	
	@PostMapping("/signIn")
	String[] signIn(@RequestBody Users user) {
		String[] fetchedData = new String[0];
		try {
			// ユーザーが登録されていなかった場合
			if(service.checkUserAlreadyExist(user) == 0) {
				return fetchedData;
			}
			fetchedData = service.fetchLoginUserData(user);
		} catch(Exception e) {
			System.out.println("DB Access Error");
		}
		return fetchedData;
	}

}
