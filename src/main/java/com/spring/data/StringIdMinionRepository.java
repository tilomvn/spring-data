package com.spring.data;

import org.springframework.data.repository.CrudRepository;

interface StringIdMinionRepository extends CrudRepository<StringIdMinion, String> {

}
