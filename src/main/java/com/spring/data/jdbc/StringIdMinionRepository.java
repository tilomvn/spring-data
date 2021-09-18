package com.spring.data.jdbc;

import org.springframework.data.repository.CrudRepository;

public interface StringIdMinionRepository extends CrudRepository<StringIdMinion, String> {

}
