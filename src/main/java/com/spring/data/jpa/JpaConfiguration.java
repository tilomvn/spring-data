package com.spring.data.jpa;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;


@Configuration
class JpaConfiguration {

	Logger log = LoggerFactory.getLogger(getClass());
	
	@Bean
    public DataSource dataSource() {                                                   
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.H2).build();
    }
    
    @Bean
	ApplicationRunner run(StringIdRepository stringIdRepository) {
		return args -> {
			TEntity before = new TEntity("Kevin");
			
			stringIdRepository.save(before);
			log.info(before.id);

			TEntity reloaded = stringIdRepository.findById(before.id).get();
			log.info(reloaded.name);
		};
	}
}
