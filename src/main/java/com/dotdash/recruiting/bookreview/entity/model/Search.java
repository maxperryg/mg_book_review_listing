package com.dotdash.recruiting.bookreview.entity.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Search {
	@JsonProperty("total-results")
	public Long totalResults;

	public Results results;

	public Search() {
	}

	private Search(Builder builder) {
		setTotalResults(builder.totalResults);
		setResults(builder.results);
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public Long getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(Long totalResults) {
		this.totalResults = totalResults;
	}

	public Results getResults() {
		return results;
	}

	public void setResults(Results results) {
		this.results = results;
	}

	/**
	 * {@code Search} builder static inner class.
	 */
	public static final class Builder {
		private Long totalResults;
		private Results results;

		private Builder() {
		}

		/**
		 * Sets the {@code totalResults} and returns a reference to this Builder enabling method chaining.
		 *
		 * @param totalResults the {@code totalResults} to set
		 * @return a reference to this Builder
		 */
		public Builder withTotalResults(Long totalResults) {
			this.totalResults = totalResults;
			return this;
		}

		/**
		 * Sets the {@code results} and returns a reference to this Builder enabling method chaining.
		 *
		 * @param results the {@code results} to set
		 * @return a reference to this Builder
		 */
		public Builder withResults(Results results) {
			this.results = results;
			return this;
		}

		/**
		 * Returns a {@code Search} built from the parameters previously set.
		 *
		 * @return a {@code Search} built with parameters of this {@code Search.Builder}
		 */
		public Search build() {
			return new Search(this);
		}
	}
}
