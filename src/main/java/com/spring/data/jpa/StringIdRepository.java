package com.spring.data.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

interface StringIdRepository extends JpaRepository<TEntity, String> {

}
