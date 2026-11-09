package com.landgraf.bffscheduletasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BffScheduleTasksApplication {

	public static void main(String[] args) {
		SpringApplication.run(BffScheduleTasksApplication.class, args);
	}

}
