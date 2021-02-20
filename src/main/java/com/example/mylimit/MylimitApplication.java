package com.example.mylimit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.mylimit.mymapper")
@SpringBootApplication
public class MylimitApplication {

	public static void main(String[] args) {
		SpringApplication.run(MylimitApplication.class, args);
	}

}
