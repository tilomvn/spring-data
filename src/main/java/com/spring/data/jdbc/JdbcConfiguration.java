package com.spring.data.jdbc;

import java.util.UUID;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.relational.core.conversion.DbAction;
import org.springframework.data.relational.core.conversion.DbAction.AcquireLockRoot;
import org.springframework.data.relational.core.dialect.Dialect;
import org.springframework.data.relational.core.dialect.H2Dialect;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;
import org.springframework.data.relational.core.mapping.event.BeforeSaveCallback;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.TransactionManager;
import org.springframework.data.jdbc.core.convert.BasicJdbcConverter;
import org.springframework.data.jdbc.core.convert.DefaultDataAccessStrategy;
import org.springframework.data.jdbc.core.convert.JdbcConverter;
import org.springframework.data.jdbc.core.convert.RelationResolver;
import org.springframework.data.jdbc.core.convert.SqlGeneratorSource;

@Configuration
@EnableJdbcRepositories
class JdbcConfiguration {
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	@Bean
    public DataSource dataSource() {                                                   
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.H2).build();
    }

    @Bean
    NamedParameterJdbcOperations namedParameterJdbcOperations(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
    
    @Bean
    Dialect jdbcDialect(NamedParameterJdbcOperations namedParameterJdbcOperations) {
		return H2Dialect.INSTANCE;
    }
    
    @Bean
    RelationalMappingContext mappingContext() {
    	RelationalMappingContext relationalMappingContext = new RelationalMappingContext();
    	return relationalMappingContext;
    }
    
    @Bean
    SqlGeneratorSource sqlGeneratorSource(RelationalMappingContext mappingContext, @Lazy JdbcConverter jdbcConverter, Dialect jdbcDialect) {
    	SqlGeneratorSource sqlGeneratorSource = new SqlGeneratorSource(mappingContext, jdbcConverter, jdbcDialect);
    	return sqlGeneratorSource;
    }
    
    @Bean
    RelationResolver relationResolver(
    		@Lazy SqlGeneratorSource sqlGeneratorSource, 
    		@Lazy JdbcConverter jdbcConverter, 
    		RelationalMappingContext mappingContext,
    		NamedParameterJdbcOperations namedParameterJdbcOperations) {
    	
    	RelationResolver relationResolver = new DefaultDataAccessStrategy(sqlGeneratorSource, mappingContext, 
    																	jdbcConverter, namedParameterJdbcOperations);
    	return relationResolver;
    }
    
    @Bean
    JdbcConverter jdbcConverter(RelationalMappingContext relationalMappingContext, Dialect jdbcH2Dialect, 
    		@Lazy RelationResolver relationResolver) {
    	return new BasicJdbcConverter(relationalMappingContext, relationResolver);
    }
    
    @Bean
    TransactionManager transactionManager(DataSource dataSource) {                     
        return new DataSourceTransactionManager(dataSource);
    }
    
    @Bean
	BeforeSaveCallback<StringIdMinion> beforeSaveCallback() {
		return (minion, mutableAggregateChange) -> {
			if (minion.id == null) {
				minion.id = UUID.randomUUID().toString();
				//mutableAggregateChange.addAction(new DbAction.AcquireLockAllRoot<StringIdMinion>(StringIdMinion.class));
			}
			return minion;
		};
	}
    
    @Bean
	ApplicationRunner run(StringIdMinionRepository stringIdMinionRepository) {
		return args -> {
			StringIdMinion before = new StringIdMinion("Kevin");
			
			stringIdMinionRepository.save(before);
			log.info(before.getId());

			StringIdMinion reloaded = stringIdMinionRepository.findById(before.getId()).get();
			log.info(reloaded.getName());
		};
	}
}
