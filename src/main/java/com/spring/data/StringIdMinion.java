package com.spring.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

//@Table
class StringIdMinion {
	@Id
	String id;
	String name;
	
	StringIdMinion() {}
	StringIdMinion(String name) { this.name = name; }
	StringIdMinion(String id, String name) { this.id = id; this.name = name; }
}
