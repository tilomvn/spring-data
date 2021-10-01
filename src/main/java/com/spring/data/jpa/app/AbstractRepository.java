package com.spring.data.jpa.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

@NoRepositoryBean
interface AbstractRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    @Modifying
    @Query(value = "update #{#entityName} e set e.recordStatus = 'DELETED' where e = :var")
    void delete(@Param("var") T var);

    @Modifying
    @Query(value = "update #{#entityName} e set e.recordStatus = 'DELETED' where e in :vars")
    void deleteAll(@Param("vars") Iterable<? extends T> vars);

}
