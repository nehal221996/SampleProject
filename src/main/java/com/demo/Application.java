package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude =  HibernateJpaAutoConfiguration.class)
//org.springframework.orm.jpa.EntityManagerHolder cannot be cast to org.springframework.orm.hibernate5.SessionHolder

public class Application 
{
	public static void main(String[] args)
	{
		SpringApplication.run(Application.class, args);
	}
}
