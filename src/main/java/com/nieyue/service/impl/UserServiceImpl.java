package com.nieyue.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.User;
import com.nieyue.dao.UserDao;
import com.nieyue.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Resource
	UserDao userDao;
	@Override
	public User findUserByName(Integer id, String value) {
		User u = userDao.findUserByName(id, value);
		return u;
	}

	@Override
	public List<User> browsePagingUser(String orderName, String orderWay, int pageNum, int pageSize) {
		List<User> l = userDao.browsePagingUser(orderName, orderWay,pageNum-1,pageSize);
		return l;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addUser(User user) {
		boolean b = userDao.addUser(user);
		return b;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateUser(User user) {
		//User u = userDao.findUserByName(user.getId(), null);
		//user.setValue(String.valueOf(Integer.valueOf(u.getValue())+1));
		boolean b = userDao.updateUser(user);
		return b;
	}

}
