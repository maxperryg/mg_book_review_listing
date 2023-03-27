package com.dotdash.recruiting.bookreview.entity.model;

public class GoodreadsResponse {
	private Search search;

	public GoodreadsResponse() {
	}

	private GoodreadsResponse(Builder builder) {
		setSearch(builder.search);
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public Search getSearch() {
		return search;
	}

	public void setSearch(Search search) {
		this.search = search;
	}

	/**
	 * {@code GoodreadsResponse} builder static inner class.
	 */
	public static final class Builder {
		private Search search;

		private Builder() {
		}


		/**
		 * Sets the {@code search} and returns a reference to this Builder enabling method chaining.
		 *
		 * @param search the {@code search} to set
		 * @return a reference to this Builder
		 */
		public Builder withSearch(Search search) {
			this.search = search;
			return this;
		}

		/**
		 * Returns a {@code GoodreadsResponse} built from the parameters previously set.
		 *
		 * @return a {@code GoodreadsResponse} built with parameters of this {@code GoodreadsResponse.Builder}
		 */
		public GoodreadsResponse build() {
			return new GoodreadsResponse(this);
		}
	}
}
