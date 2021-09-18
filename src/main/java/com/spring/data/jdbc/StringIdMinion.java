package com.spring.data.jdbc;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
public class StringIdMinion {
	
	@Id
	String id;
	String name;
	
	StringIdMinion() {}
	public StringIdMinion(String name) { this.name = name; }
	StringIdMinion(String id, String name) { this.id = id; this.name = name; }
	
	public String getId() { return this.id; }
	public String getName() { return this.name; }
}
