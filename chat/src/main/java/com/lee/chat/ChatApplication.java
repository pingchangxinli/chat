package com.lee.chat;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
//该注解开启spring中方法有@aysc注解的异步调用
@EnableAsync
public class ChatApplication {

	// Simple example shows how a command line spring application can execute an
	// injected bean service. Also demonstrates how you can use @Value to inject
	// command line args ('--name=whatever') or application properties


	public static void main(String[] args) throws Exception {
		SpringApplication.run(ChatApplication.class, args);
	}

}
