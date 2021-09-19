package com.spring.data.jpa;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.spring.data.jpa.session.TTEntity;


@Configuration
@EnableJpaRepositories(basePackages = "com.spring.data.jpa.session", entityManagerFactoryRef = "sessionEntityManagerFactory", transactionManagerRef = "sessionTransactionManager")
class SessionConfiguration {

	@Bean(name = "sessionDataSource")
    DataSource sessionDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
	    dataSourceBuilder.driverClassName("org.h2.Driver");
	    dataSourceBuilder.url("jdbc:h2:file:./db/sessiondb");
	    dataSourceBuilder.username("sa");
	    dataSourceBuilder.password("");
	    return dataSourceBuilder.build();
    }
	@Bean(name = "sessionEntityManagerFactory")
    LocalContainerEntityManagerFactoryBean sessionEntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("sessionDataSource") DataSource sessionDataSource) {
       return builder.dataSource(sessionDataSource).properties(new HashMap<String, Object>() {{
    	   put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
    	   put("hibernate.hbm2ddl.auto", "update");
       }}).packages(TTEntity.class).build();
    }
	
	@Bean(name = "sessionTransactionManager")
	PlatformTransactionManager sessionTransactionManager(@Qualifier("sessionEntityManagerFactory") LocalContainerEntityManagerFactoryBean sessionEntityManagerFactory) {
	    return new JpaTransactionManager(sessionEntityManagerFactory.getObject());
	}
}
