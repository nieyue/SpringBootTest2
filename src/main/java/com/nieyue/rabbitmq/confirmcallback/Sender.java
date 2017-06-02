package com.nieyue.rabbitmq.confirmcallback;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nieyue.bean.User;

/**
 * 消息生产者
 * @author 聂跃
 * @date 2017年5月31日
 */
@Component 
public class Sender  implements RabbitTemplate.ConfirmCallback{
	 private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);  
	/**
	 * 不能注入，否则没回调
	 */
	 private RabbitTemplate rabbitTemplate;
	 
	@Autowired  
	    public Sender(RabbitTemplate rabbitTemplate) {  
	        this.rabbitTemplate = rabbitTemplate;  
	        this.rabbitTemplate.setConfirmCallback(this); 
	    } 
	
	 public void send(User user) {  
	        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString()); 
	       // this.rabbitTemplate.convertAndSend(AmqpConfig.DIRECT_EXCHANGE, AmqpConfig.DIRECT_ROUTINGKEY,user, correlationData);  
	      // this.rabbitTemplate.convertAndSend(AmqpConfig.FANOUT_EXCHANGE, user, correlationData);  
	         this.rabbitTemplate.convertAndSend(AmqpConfig.FANOUT_EXCHANGE, AmqpConfig.DIRECT_ROUTINGKEY,user, correlationData);  
	        // this.rabbitTemplate.convertAndSend(AmqpConfig.TOPIC_EXCHANGE, AmqpConfig.TOPIC_ROUTINGKEY,user, correlationData);  
	     
	 }  
//	 public void send(User user) {  
//		 CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString()); 
//		 this.rabbitTemplate.convertAndSend(AmqpConfig.EARNTURN_EXCHANGE, AmqpConfig.EARNTURN_ROUTINGKEY,JSONObject.fromObject( user).toString(), correlationData);  
//		 // this.rabbitTemplate.convertAndSend(AmqpConfig.EARNTURN_EXCHANGE, AmqpConfig.EARNTURN_ROUTINGKEY, userId.toString(), correlationData);  
//		 
//	 }  
//	 public void send(User user) {  
//		 CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString()); 
//		 //this.rabbitTemplate.convertAndSend(AmqpConfig.EARNTURN_EXCHANGE, AmqpConfig.EARNTURN_ROUTINGKEY, user, correlationData);  
//		 this.rabbitTemplate.convertAndSend(AmqpConfig.EARNTURN_EXCHANGE, AmqpConfig.EARNTURN_ROUTINGKEY, JSONObject.fromObject(user).toString(), correlationData);  
//		 
//	 }  
	 /** 回调方法 */
	 @Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		 LOGGER.info("confirm: " + correlationData); 
		 LOGGER.info("cause: " + cause); 
	        if (ack) {
	        	LOGGER.info("消息发送确认成功");
	        } else {
	        	LOGGER.info("消息发送确认失败:" + cause);

	        }  
		
	}

}
