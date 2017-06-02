package com;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.nieyue.datasource.DynamicDataSourceRegister;

@SpringBootApplication
//public class MyApplication {
//	public static void main(String[] args) {
//		SpringApplication.run(MyApplication.class,args);
//		
//	}
@Configuration
//@Import({DynamicDataSourceRegister.class}) // 注册动态多数据源
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
	
}