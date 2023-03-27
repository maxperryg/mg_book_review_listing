package com.dotdash.recruiting.bookreview.entity.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Work {
	@JsonProperty("best_book")
	public BestBook bestBook;

	public Work() {
	}

	private Work(Builder builder) {
		setBestBook(builder.bestBook);
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public BestBook getBestBook() {
		return bestBook;
	}

	public void setBestBook(BestBook bestBook) {
		this.bestBook = bestBook;
	}

	/**
	 * {@code Work} builder static inner class.
	 */
	public static final class Builder {
		private BestBook bestBook;

		private Builder() {
		}

		/**
		 * Sets the {@code bestBook} and returns a reference to this Builder enabling method chaining.
		 *
		 * @param bestBook the {@code bestBook} to set
		 * @return a reference to this Builder
		 */
		public Builder withBestBook(BestBook bestBook) {
			this.bestBook = bestBook;
			return this;
		}

		/**
		 * Returns a {@code Work} built from the parameters previously set.
		 *
		 * @return a {@code Work} built with parameters of this {@code Work.Builder}
		 */
		public Work build() {
			return new Work(this);
		}
	}
}
