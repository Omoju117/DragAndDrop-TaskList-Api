package dev.itboot.rest.repository.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.itboot.rest.mapper.UsersMapper;
import dev.itboot.rest.model.Task;
import dev.itboot.rest.model.Users;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UsersService {
	//トランザクションでまとめる
	@Autowired
	private UsersMapper mapper;
	
	public int checkUserAlreadyExist(Users targetUser) {
		return mapper.checkUserAlreadyExist(targetUser.getUserName(), targetUser.getPassword());
	}
	
	public void insertUserInfo(Users targetUser) {
		mapper.insertUserInfo(targetUser.getUserName(), targetUser.getPassword());
	}
	
	public String[] fetchLoginUserData(Users targetUser) {
		return mapper.fetchLoginUserData(targetUser.getUserName());
	}
}
