package com.spring.SpringApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@ComponentScan({"com.spring.SpringApp.appuser",
//		"com.spring.SpringApp.security.config",
//		"com.spring.SpringApp.security"})
//@EntityScan("com.spring.SpringApp.appuser.AppUser")
//
//@EntityScan("com.spring.SpringApp.appuser")
//@SpringBootApplication(scanBasePackages = {"com.spring.SpringApp.appuser"
//		, "com.spring.SpringApp.token"})
@SpringBootApplication(scanBasePackages = {"com.spring.SpringApp.*"})
public class SpringAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAppApplication.class, args);
	}

}
