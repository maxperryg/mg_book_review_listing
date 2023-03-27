package com.dotdash.recruiting.bookreview.entity.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class Search {
	public String query;

	@JsonProperty("results-start")
	public Long resultsStart;

	@JsonProperty("results-end")
	public Long resultsEnd;

	@JsonProperty("total-results")
	public Long totalResults;

	public String source;

	@JsonProperty("query-time-seconds")
	public BigDecimal queryTimeSeconds;

	public Results results;

	public Search() {
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public Long getResultsStart() {
		return resultsStart;
	}

	public void setResultsStart(Long resultsStart) {
		this.resultsStart = resultsStart;
	}

	public Long getResultsEnd() {
		return resultsEnd;
	}

	public void setResultsEnd(Long resultsEnd) {
		this.resultsEnd = resultsEnd;
	}

	public Long getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(Long totalResults) {
		this.totalResults = totalResults;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public BigDecimal getQueryTimeSeconds() {
		return queryTimeSeconds;
	}

	public void setQueryTimeSeconds(BigDecimal queryTimeSeconds) {
		this.queryTimeSeconds = queryTimeSeconds;
	}

	public Results getResults() {
		return results;
	}

	public void setResults(Results results) {
		this.results = results;
	}
}
