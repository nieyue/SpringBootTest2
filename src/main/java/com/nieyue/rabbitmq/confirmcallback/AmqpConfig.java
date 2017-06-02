package com.nieyue.rabbitmq.confirmcallback;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
/**
 * 配置
 * @author 聂跃
 * @date 2017年5月31日
 */
@Configuration  
public class AmqpConfig {
    public static final String DIRECT_EXCHANGE   = "callback.exchange.direct";  
    public static final String TOPIC_EXCHANGE   = "callback.exchange.topic";  
    public static final String FANOUT_EXCHANGE   = "callback.exchange.fanout";  
    public static final String DIRECT_ROUTINGKEY = "callback.routingkey.direct";  
    public static final String TOPIC_ROUTINGKEY = "callback.#";  
    public static final String DIRECT_QUEUE      = "callback.queue.direct"; 
    public static final String TOPIC_QUEUE      = "callback.queue.topic"; 
    public static final String FANOUT_QUEUE      = "callback.queue.fanout"; 
    @Autowired
    ConnectionFactory  connectionFactory ;
    /** 因为要设置回调类，所以应是prototype类型，如果是singleton类型，则回调类为最后一次设置 */  
    @Bean  
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)  
    public RabbitTemplate rabbitTemplate() {  
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);  
        return rabbitTemplate;  
    } 
	 /** 设置交换机类型  */  
    @Bean  
    public FanoutExchange defaultExchange() {  
        /** 
         * DirectExchange:按照routingkey分发到指定队列 
         * TopicExchange:多关键字匹配 
         * FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念 
         * HeadersExchange ：通过添加属性key-value匹配 
         */  
    	//DirectExchange de = new DirectExchange(AmqpConfig.DIRECT_EXCHANGE);
    	//TopicExchange de = new TopicExchange(AmqpConfig.TOPIC_EXCHANGE);
    	FanoutExchange de = new FanoutExchange(AmqpConfig.FANOUT_EXCHANGE);
    	//de.setDelayed(true);  
        return de;
    }  
    @Bean  
    public Queue DIRECTQueue() {  
    return new Queue(AmqpConfig.DIRECT_QUEUE);  
    } 
    @Bean  
    public Queue TOPICQueue() {  
    	return new Queue(AmqpConfig.TOPIC_QUEUE);  
    } 
    @Bean  
    public Queue FANOUTQueue() {  
    	return new Queue(AmqpConfig.FANOUT_QUEUE);  
    } 
    @Bean  
    public Binding binding() {  
        /** 将队列绑定到交换机 */  
        return BindingBuilder.bind(FANOUTQueue()).to(defaultExchange())
        		//.with(AmqpConfig.DIRECT_ROUTINGKEY)
        		;  
    } 
    @Bean  
    public Binding binding2() {  
    	/** 将队列绑定到交换机 */  
    	return BindingBuilder.bind(TOPICQueue()).to(defaultExchange())
    			//.with(AmqpConfig.DIRECT_ROUTINGKEY)
    			;  
    } 
    @Bean  
    public Binding binding3() {  
    	/** 将队列绑定到交换机 */  
    	return BindingBuilder.bind(DIRECTQueue()).to(defaultExchange())
    			//.with(AmqpConfig.DIRECT_ROUTINGKEY)
    			;  
    } 
 

}
