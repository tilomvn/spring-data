package com.spring.data.jdbc;

import org.springframework.data.repository.CrudRepository;

interface StringIdMinionRepository extends CrudRepository<StringIdMinion, String> {

}
