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
	
	@Column(length = 10)
	String recordStatus;
	
	
	TEntity() {}
	
	public TEntity(String name) { this.name = name; }
	
	@PrePersist
	void prePersist() {
		id = UUID.randomUUID().toString();
		recordStatus = "NORMAL";
	}
	
	public String getId() { return id; }
	public String getName() { return name; }
	public String getRecordStatus() { return recordStatus; }
}
