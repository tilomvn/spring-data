package com.spring.data.jpa;

import javax.persistence.Entity;

import org.springframework.data.annotation.Id;

@Entity
public class TEntity {

//	@Identity
	@Id
	String id;
	String name;
	
}
