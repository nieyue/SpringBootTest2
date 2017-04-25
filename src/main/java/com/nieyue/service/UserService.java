package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.User;

public interface UserService {
	User findUserByName(Integer id,String value);
	List<User> browsePagingUser( String orderName, String orderWay, int pageNum,int pageSize);
	public boolean addUser(User user);
	public boolean updateUser(User user);
}
