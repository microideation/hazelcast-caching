package com.microideation.samples.hazelcastcaching;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class HazelcastCachingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HazelcastCachingApplication.class, args);
	}
}
