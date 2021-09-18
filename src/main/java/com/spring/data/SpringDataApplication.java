package com.spring.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;


//@SpringBootApplication
@SpringBootApplication(scanBasePackages = "com.spring.data.jdbc", exclude = {JpaRepositoriesAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
//@SpringBootApplication(scanBasePackages = "com.spring.data.jpa")
public class SpringDataApplication {

	Logger log = LoggerFactory.getLogger(getClass());
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}
}
