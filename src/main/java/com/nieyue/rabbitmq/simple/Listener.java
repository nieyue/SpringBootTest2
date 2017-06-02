package com.nieyue.rabbitmq.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;

/**
 * 消息监听者
 * @author 聂跃
 * @date 2017年5月31日
 */
//@Configuration  
//@RabbitListener(queues = "foo") 
public class Listener {
	 private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class);  
	  
	    @Bean  
	    public Queue fooQueue() {  
	        return new Queue("foo");  
	    }  
	  
	    @RabbitHandler  
	    public void process(@Payload String foo) {  
	        LOGGER.info("Listener: " + foo);  
	    }  
}
