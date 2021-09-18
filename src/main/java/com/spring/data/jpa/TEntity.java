package com.spring.data.jpa;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;

@Entity(name = "STRING_ID_MINION")
class TEntity {

//	@Identity
	@Id
	String id;
	String name;
	
	TEntity() {}
	TEntity(String name) { this.name = name; }
	
	@PrePersist
	void prePersist() {
		id = UUID.randomUUID().toString();
	}
}
