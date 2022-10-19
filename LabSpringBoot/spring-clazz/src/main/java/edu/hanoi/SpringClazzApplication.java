package edu.hanoi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ImportResource("classpath:config.xml")
@ComponentScan("edu.*")
public class SpringClazzApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringClazzApplication.class, args);
	}

}
