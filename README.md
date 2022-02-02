# Spring Data JDBC - How to custom ID generation
* Reference: https://spring.io/blog/2021/09/09/spring-data-jdbc-how-to-use-custom-id-generation
* Demonstration using Listener

# Spring Data JDBC - How do I make Bidirectional Relationships
* https://spring.io/blog/2021/09/22/spring-data-jdbc-how-do-i-make-bidirectional-relationships
* It does not work with current version 2.2.x ???

# Spring Data JDBC - How do I implement caching ?
* https://spring.io/blog/2021/10/18/spring-data-jdbc-how-do-i-implement-caching
* Using JCache on repository layer

# Spring Data - Support jMolecules’ @Identity as ID annotation
* Reference: https://spring.io/blog/2021/09/17/spring-data-2021-1-0-m3-released
* Still not in current version 2.5.4 ?

# Multiple DataSource Sample
* Sample configuration for distributed session datasource and application datasource.
* ChainedTransactionManager: https://stackoverflow.com/questions/48954763/spring-transactional-with-a-transaction-across-multiple-data-sources
```Java
@Bean(name = "chainedTransactionManager")
public ChainedTransactionManager transactionManager(@Qualifier("primaryDs") PlatformTransactionManager ds1, @Qualifier("secondaryDs") PlatformTransactionManager ds2) {
    return new ChainedTransactionManager(ds1, ds2);
}
