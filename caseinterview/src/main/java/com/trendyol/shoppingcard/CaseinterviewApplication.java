package com.trendyol.shoppingcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EntityScan("com.trendyol.shoppingcard.entities")
@EnableSwagger2
public class CaseinterviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaseinterviewApplication.class, args);
	}

}
