package com.boot;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class DemoApplication{
	
	@Resource(name="transactionManager")
	private PlatformTransactionManager transactionManager;
	
	//创建事务管理器
	@Bean(name = "transactionManager")
	    public PlatformTransactionManager txManager(EntityManagerFactory factory) {
	        return new JpaTransactionManager(factory);
	 }
	
	public PlatformTransactionManager annotationDrivenTransactionManager() {
	        return transactionManager;
	    }
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("start is finished");
	}
	
}
