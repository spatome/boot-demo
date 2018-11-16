package com.spatome.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.spatome.boot.vo.SSVO;

/**
 * 注意：
 * （1）@Component和@Configuration的区别：@Component使用拦截 而@Configuration使用CGLIB(动态代理)
 * 
 * 
springboot 启动原理A
(1)初始化，载入Application Context Initializers（spring-boot包META-INF/spring.factories的工厂类）
可自定义ex:META-INF/spring.factories配置SimpleApplicationContextInitializer

(2)监听, 载入ApplicationEvent(ApplicationStartedEvent,ApplicationFailedEvent,ApplicationPreparedEvent等)
可自定义ex:META-INF/spring.factories配置SimpleApplicationListener

(3)注意：spring容器创建后refresh(*配置类的解析、各种BeanFactoryPostProcessor和BeanPostProcessor的注册、国际化配置的初始化、web内置容器的构造等等)
initialize和PreparedEvent在refresh之前


springboot 启动原理B(refresh)
(1)
**/

/*@SpringBootApplication
@PropertySources(@PropertySource("classpath:simple.properties"))
@Import(ImportService.class)
@ImportResource("classpath:import.xml")*/

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan("com.spatome.boot")
public class BootApplication {

	public static void main(String[] args) {
		SpringApplication sa = new SpringApplication(BootApplication.class);

		ApplicationContext applicationContext = sa.run(args);
		
		SSVO ssVO = applicationContext.getBean("ssVO", SSVO.class);
		System.out.println("(运行后数据)"+ssVO.getValue());
	}
}
