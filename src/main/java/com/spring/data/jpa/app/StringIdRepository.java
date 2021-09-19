package com.spring.data.jpa.app;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StringIdRepository extends JpaRepository<TEntity, String> {

}
