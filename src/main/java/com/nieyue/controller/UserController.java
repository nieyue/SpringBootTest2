package com.nieyue.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nieyue.bean.User;
import com.nieyue.dao.UserDao;
import com.nieyue.rabbitmq.confirmcallback.AmqpConfig;
import com.nieyue.rabbitmq.confirmcallback.Sender;
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
	@Autowired  
 	private Sender sender;
	@ResponseBody
	@RequestMapping(value = "/update")
	public boolean updateUser(@ModelAttribute User user ){
		boolean b=false;
		//b=userService.updateUser(user);
		sender.send(user);
		return b;
	}
	
	@ResponseBody
	@RequestMapping(value = "/list")
	public List<User> browsePagingUser(
			@RequestParam(value="orderName",required=false,defaultValue="id")String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="asc")String orderWay,
			@RequestParam(value="pageNum",required=false,defaultValue="1")int pageNum,
			@RequestParam(value="pageSize",required=false,defaultValue="10")int pageSize 
			){
		List<User> l = userService.browsePagingUser(orderName, orderWay, pageNum, pageSize);
		return l;
	}
}
