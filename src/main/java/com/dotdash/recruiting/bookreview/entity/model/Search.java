package com.dotdash.recruiting.bookreview.entity.model;

import java.math.BigDecimal;

public class Search {
	public String query;
	public Long resultsstart;
	public Long resultsend;
	public Long totalresults;
	public String source;
	public BigDecimal querytimeseconds;
	public Results results;

	public Search() {
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public Long getResultsstart() {
		return resultsstart;
	}

	public void setResultsstart(Long resultsstart) {
		this.resultsstart = resultsstart;
	}

	public Long getResultsend() {
		return resultsend;
	}

	public void setResultsend(Long resultsend) {
		this.resultsend = resultsend;
	}

	public Long getTotalresults() {
		return totalresults;
	}

	public void setTotalresults(Long totalresults) {
		this.totalresults = totalresults;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public BigDecimal getQuerytimeseconds() {
		return querytimeseconds;
	}

	public void setQuerytimeseconds(BigDecimal querytimeseconds) {
		this.querytimeseconds = querytimeseconds;
	}

	public Results getResults() {
		return results;
	}

	public void setResults(Results results) {
		this.results = results;
	}
}
