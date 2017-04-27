package com.nieyue.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nieyue.bean.User;
import com.nieyue.dao.UserDao;
import com.nieyue.service.UserService;

@RestController
@RequestMapping(value="/user")
public class UserController {
	@Resource
	UserService userService;
	@ResponseBody
	@RequestMapping(value = "/get")
	public User user(
			@RequestParam(value="id")Integer id,
			@RequestParam(value="value",required=false)String value
			){
	User user = userService.findUserByName(id,value);
	return user;
	}
	@ResponseBody
	@RequestMapping(value = "/add")
	public boolean addUser(@ModelAttribute User user){
		User user2=new User();
		user2=user;
		boolean b=userService.addUser(user2);
		return b;
	}
	@ResponseBody
	@RequestMapping(value = "/update")
	public boolean updateUser(@ModelAttribute User user ){
		boolean b=userService.updateUser(user);
		return b;
	}
	@ResponseBody
	@RequestMapping(value = "/list")
	public List<User> browsePagingUser(
			@RequestParam(value="orderName",required=false)String orderName,
			@RequestParam(value="orderWay",required=false)String orderWay,
			@RequestParam(value="pageNum",required=false)int pageNum,
			@RequestParam(value="pageSize",required=false)int pageSize 
			){
		List<User> l = userService.browsePagingUser(orderName, orderWay, pageNum, pageSize);
		return l;
	}
}
