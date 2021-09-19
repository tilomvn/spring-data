package com.spring.data.jpa.app;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;

@Entity
public class TEntity {

//	@Identity
	@Id
	@Column(length = 50)
	String id;
	
	@Column(length = 100)
	String name;
	
	TEntity() {}
	public TEntity(String name) { this.name = name; }
	
	@PrePersist
	void prePersist() {
		id = UUID.randomUUID().toString();
	}
	
	public String getId() { return id; }
	public String getName() { return name; }
}
