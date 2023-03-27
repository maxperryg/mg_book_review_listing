package com.dotdash.recruiting.bookreview.entity.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.List;

public class Results {
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<Work> work;

	public Results() {
	}

	public List<Work> getWork() {
		return work;
	}

	public void setWork(List<Work> work) {
		this.work = work;
	}
}
