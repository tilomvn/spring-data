package com.spring.data.jpa.session;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;

@Entity
public class TTEntity {

	@Id
	@Column(length = 50)
	String id;
	
	@Column(length = 100)
	String name;
	
	TTEntity() {}
	public TTEntity(String name) { this.name = name; }
	
	@PrePersist
	void prePersist() {
		this.id = UUID.randomUUID().toString();
	}
	
	public String getId() { return id; }
	public String getName() { return name; }
}
