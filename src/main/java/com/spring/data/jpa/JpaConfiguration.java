package com.spring.data.jpa;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.data.jpa.app.StringIdRepository;
import com.spring.data.jpa.app.TEntity;
import com.spring.data.jpa.session.TStringIdRepository;
import com.spring.data.jpa.session.TTEntity;


@Configuration
class JpaConfiguration {

	Logger log = LoggerFactory.getLogger(getClass());
	
    @Bean
	ApplicationRunner run(StringIdRepository stringIdRepository, TStringIdRepository tStringIdRepository) {
		return args -> {
			TEntity before = new TEntity("Kevin");
			
			stringIdRepository.save(before);
			log.info(before.getId());

			TEntity reloaded = stringIdRepository.findById(before.getId()).get();
			log.info(reloaded.getName());
			
			
			TTEntity beforeJeff = new TTEntity("Jeff");
			
			tStringIdRepository.save(beforeJeff);
			log.info(beforeJeff.getId());

			TTEntity reloadedJeff = tStringIdRepository.findById(beforeJeff.getId()).get();
			log.info(reloadedJeff.getName());
		};
	}
}
