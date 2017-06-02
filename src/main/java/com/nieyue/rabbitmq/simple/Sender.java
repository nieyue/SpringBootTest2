package com.nieyue.rabbitmq.simple;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * 消息生产者
 * @author 聂跃
 * @date 2017年5月31日
 */
//@Component
public class Sender {
	@Autowired  
    private RabbitTemplate rabbitTemplate;
	 public void send(String msg) {  
	        this.rabbitTemplate.convertAndSend("foo", msg);  
	    }  
}
