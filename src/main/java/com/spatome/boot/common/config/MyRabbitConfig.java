//package com.spatome.boot.common.config;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.FanoutExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class MyRabbitConfig {
//
//	//=====================队列=====================
//	@Bean
//	public Queue testQueue(){
//		return new Queue("queue.test");
//	}
//	@Bean
//	public Queue testTtlQueue(){
//		return new Queue("queue.ttl.test");
//	}
////	@Bean
////	public Queue testDeadQueue(){
////		return new Queue("test.dead.queue");
////	}
//
//	//=====================交换机=====================
//	@Bean
//	public FanoutExchange testFanout(){
//		return new FanoutExchange("fanout.test");
//	}
//
//	//=====================绑定fanout=====================
//	@Bean
//	public Binding bindTestQueue(FanoutExchange testFanout, Queue testQueue){
//		return BindingBuilder.bind(testQueue).to(testFanout);
//	}
//	@Bean
//	public Binding bindTestTtlQueue(FanoutExchange testFanout, Queue testTtlQueue){
//		return BindingBuilder.bind(testTtlQueue).to(testFanout);
//	}
//}
