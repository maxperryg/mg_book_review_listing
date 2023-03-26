package com.dotdash.recruiting.bookreview.entity.model;

public class OriginalPublicationDay {
	public String type;
	public Long text;
	public Boolean nil;

	public OriginalPublicationDay() {
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getText() {
		return text;
	}

	public void setText(Long text) {
		this.text = text;
	}

	public Boolean getNil() {
		return nil;
	}

	public void setNil(Boolean nil) {
		this.nil = nil;
	}
}
