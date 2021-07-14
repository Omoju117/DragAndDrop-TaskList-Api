package dev.itboot.rest.mapper;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UsersMapper {
	int checkUserAlreadyExist(String userName, String password);
	void insertUserInfo(String userName, String password);
	String[] fetchLoginUserData(String userName);
}
