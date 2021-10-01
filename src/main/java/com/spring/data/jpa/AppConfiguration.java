package com.spring.data.jpa;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.spring.data.jpa.app.TEntity;


@Configuration
@EnableJpaRepositories(basePackages = "com.spring.data.jpa.app", entityManagerFactoryRef = "appEntityManagerFactory", transactionManagerRef = "appTransactionManager")
class AppConfiguration {
	
	@Bean
	@Primary
	@ConfigurationProperties("app.datasource")
	DataSourceProperties appDataSourceProperties() {
	    return new DataSourceProperties();
	}
	@Bean
	@Primary
	//@ConfigurationProperties("app.datasource.configuration")
	DataSource appDataSource() {
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
	    dataSourceBuilder.driverClassName("org.h2.Driver");
	    dataSourceBuilder.url("jdbc:h2:file:./db/dbtest");
	    dataSourceBuilder.username("sa");
	    dataSourceBuilder.password("");
	    return dataSourceBuilder.build();
//	    return appDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}
	@Primary
    @Bean(name = "appEntityManagerFactory")
    LocalContainerEntityManagerFactoryBean appEntityManagerFactory(EntityManagerFactoryBuilder builder, DataSource appDataSource) {
       return builder.dataSource(appDataSource).properties(new HashMap<String, Object>() {{
    	   put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
    	   put("hibernate.hbm2ddl.auto", "update");
    	   put("hibernate.show_sql", "true");
       }}).packages(TEntity.class).build();
    }
	
	/**
	 * App Transaction management, must add @EnableTransactionManagement for using transaction in service layer.
	 */
	@Primary
	@Bean
	PlatformTransactionManager appTransactionManager(LocalContainerEntityManagerFactoryBean appEntityManagerFactory) {
	    return new JpaTransactionManager(appEntityManagerFactory.getObject());
	}
}
