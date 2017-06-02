package com.nieyue.rabbitmq.confirmcallback;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import com.nieyue.bean.User;
import com.nieyue.service.UserService;
import com.rabbitmq.client.Channel;
/**
 * 消息监听者
 * @author 聂跃
 * @date 2017年5月31日
 */
@Configuration  
public class Listener {
	@Resource
	UserService userService;
	private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class);  
	//@RabbitListener(queues = AmqpConfig.TOPIC_QUEUE) 
	@RabbitListener(queues = AmqpConfig.DIRECT_QUEUE) 
	public void process(Channel channel,User user,Message message) {  
	           try {
	        	   LOGGER.info("message.getBody: " + new String (message.getBody()));
	        	 // User user = (User) JSONObject.toBean(JSONObject.fromObject(EARNTURN),User.class);
	        	    //Integer userId = Integer.valueOf( EARNTURN);
	        	   // User user=new User();user.setId(userId);
	        	  List<User> l = userService.browsePagingUser("id", "asc", 1, 10);
	        	  LOGGER.info("List<User>: " + l.get(0).toString());
	        	  boolean b = userService.updateUser(user);
	        	 if(b){
	        	  channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	        	 }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //确认消息成功消费 
	        
	    } 
	@RabbitListener(queues = AmqpConfig.TOPIC_QUEUE) 
	//@RabbitListener(queues = AmqpConfig.DIRECT_QUEUE) 
	//@RabbitListener(queues = AmqpConfig.FANOUT_QUEUE) 
	public void process2(Channel channel,User user,Message message) {  
		try {
			LOGGER.info("message.getBody: " + new String (message.getBody()));
			//User user = (User) JSONObject.toBean(JSONObject.fromObject(EARNTURN),User.class);
			//Integer userId = Integer.valueOf( EARNTURN);
			// User user=new User();user.setId(userId);
			List<User> l = userService.browsePagingUser("id", "asc", 1, 10);
			LOGGER.info("List<User>: " + l.get(0).toString());
			boolean b = userService.updateUser(user);
			if(b){
				channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //确认消息成功消费 
		
	} 
//	@RabbitListener(queues = AmqpConfig.EARNTURN_QUEUE) 
//	public void process2(User user) {  
//			LOGGER.info("消费端接收到消息2: " + user);
//			List<User> l = userService.browsePagingUser("id", "asc", 1, 10);
//			LOGGER.info("List<User>: " + l.get(0).toString());
//			boolean b = userService.updateUser(user);
//		
//	} 
	  /* @Bean  
	    public SimpleMessageListenerContainer messageContainer() {  
	        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(amqpConfig.connectionFactory);  
	        container.setQueues(EARNTURNQueue());  
	        container.setExposeListenerChannel(true);  
	        container.setMaxConcurrentConsumers(1);  
	        container.setConcurrentConsumers(1);  
	        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认  
	        container.setMessageListener(new ChannelAwareMessageListener() {  
	  
	            @Override  
	            public void onMessage(Message message, Channel channel) throws Exception {  
	               LOGGER.info( message.getMessageProperties().toString());
	            	byte[] body = message.getBody();  
	                System.out.println("接收消息 : " + JSONObject.toBean(JSONObject.fromObject(new String(body)),User.class));  
	              //  channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); //确认消息成功消费  
	            }  
	        });  
	        return container;  
	    } */
	    
}
