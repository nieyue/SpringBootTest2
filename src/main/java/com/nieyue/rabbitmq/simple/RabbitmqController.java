package com.nieyue.rabbitmq.simple;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nieyue.bean.User;
import com.nieyue.service.UserService;

/**
 * 测试
 * @author 聂跃
 * @date 2017年5月31日
 */
@RestController
public class RabbitmqController {
	 	@Autowired  
	 	private com.nieyue.rabbitmq.confirmcallback.Sender sender;
	 	@Resource
		UserService userService;
	  
	    @GetMapping("/send")  
	    public String send(
	    		@RequestParam(value="orderName",required=false,defaultValue="id")String orderName,
				@RequestParam(value="orderWay",required=false,defaultValue="asc")String orderWay,
				@RequestParam(value="pageNum",required=false,defaultValue="1")int pageNum,
				@RequestParam(value="pageSize",required=false,defaultValue="10")int pageSize ,
	    		HttpServletRequest request, String msg,int delay) {  
	    	List<User> l = userService.browsePagingUser(orderName, orderWay, pageNum, pageSize);
	    	for (User user : l) {
	    		//sender.send(user);  
			}
	        return "Send OK.";  
	    }  
}
