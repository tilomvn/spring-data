package com.spring.data.jdbc;

import javax.persistence.Column;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
class StringIdMinion {
	
	@Id
	@Column(length = 50)
	String id;
	
	@Column(length = 100)
	String name;
	
	StringIdMinion() {}
	StringIdMinion(String name) { this.name = name; }
	
}
