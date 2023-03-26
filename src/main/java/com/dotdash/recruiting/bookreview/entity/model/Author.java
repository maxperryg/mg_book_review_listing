package com.dotdash.recruiting.bookreview.entity.model;

public class Author {
	public Id id;
	public String name;

	public Author() {
	}

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
