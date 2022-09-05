package com.v2.dgtimes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// 어노테이션을 등록해 AOP(@Aspect)를 찾을 수 있게 해준다.
@EnableJpaAuditing
@EnableAspectJAutoProxy
@SpringBootApplication
public class DgtimesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DgtimesApplication.class, args);
	}

}
