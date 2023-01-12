package com.ufcg.cfmetrics;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CfMetricsApplication {

	public static void main(String[] args) throws InterruptedException, IOException {
		SpringApplication.run(CfMetricsApplication.class, args);
	}
}
