package com.dotdash.recruiting.bookreview.entity.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GoodreadsResponse {
	@JsonProperty("Request")
	public Request request;
	public Search search;

	public GoodreadsResponse() {
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Search getSearch() {
		return search;
	}

	public void setSearch(Search search) {
		this.search = search;
	}
}
