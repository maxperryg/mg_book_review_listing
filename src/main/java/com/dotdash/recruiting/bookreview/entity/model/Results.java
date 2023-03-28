package com.dotdash.recruiting.bookreview.entity.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.List;

public class Results {
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<Work> work;

	public Results() {
	}

	private Results(Builder builder) {
		setWork(builder.work);
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public List<Work> getWork() {
		return work;
	}

	public void setWork(List<Work> work) {
		this.work = work;
	}

	/**
	 * {@code Results} builder static inner class.
	 */
	public static final class Builder {
		private List<Work> work;

		private Builder() {
		}

		/**
		 * Sets the {@code work} and returns a reference to this Builder enabling method chaining.
		 *
		 * @param work the {@code work} to set
		 * @return a reference to this Builder
		 */
		public Builder withWork(List<Work> work) {
			this.work = work;
			return this;
		}

		/**
		 * Returns a {@code Results} built from the parameters previously set.
		 *
		 * @return a {@code Results} built with parameters of this {@code Results.Builder}
		 */
		public Results build() {
			return new Results(this);
		}
	}
}
