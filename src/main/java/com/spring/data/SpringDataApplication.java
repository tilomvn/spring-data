package com.spring.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class SpringDataApplication {

	Logger log = LoggerFactory.getLogger(getClass());
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Bean
	ApplicationRunner run(StringIdMinionRepository stringIdMinionRepository) {
		return args -> {
			StringIdMinion before = new StringIdMinion("Kevin");
			
			stringIdMinionRepository.save(before);
			log.info(before.id);

			StringIdMinion reloaded = stringIdMinionRepository.findById(before.id).get();
			log.info(reloaded.name);
		};
	}
}
