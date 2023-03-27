package com.dotdash.recruiting.bookreview.entity.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class TextReviewsCount {
	@JacksonXmlProperty(isAttribute = true)
	public String type;
	public Long value;

	public TextReviewsCount() {
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}
}
