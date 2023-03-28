package com.dotdash.recruiting.bookreview.entity.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BestBook {
	public String title;
	public Author author;

	@JsonProperty("image_url")
	public String imageUrl;

	@JsonProperty("small_image_url")
	public String smallImageUrl;

	public BestBook() {
	}

	private BestBook(Builder builder) {
		setTitle(builder.title);
		setAuthor(builder.author);
		setImageUrl(builder.imageUrl);
		setSmallImageUrl(builder.smallImageUrl);
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getSmallImageUrl() {
		return smallImageUrl;
	}

	public void setSmallImageUrl(String smallImageUrl) {
		this.smallImageUrl = smallImageUrl;
	}

	/**
	 * {@code BestBook} builder static inner class.
	 */
	public static final class Builder {
		private String title;
		private Author author;
		private String imageUrl;
		private String smallImageUrl;

		private Builder() {
		}

		/**
		 * Sets the {@code title} and returns a reference to this Builder enabling method chaining.
		 *
		 * @param title the {@code title} to set
		 * @return a reference to this Builder
		 */
		public Builder withTitle(String title) {
			this.title = title;
			return this;
		}

		/**
		 * Sets the {@code author} and returns a reference to this Builder enabling method chaining.
		 *
		 * @param author the {@code author} to set
		 * @return a reference to this Builder
		 */
		public Builder withAuthor(Author author) {
			this.author = author;
			return this;
		}

		/**
		 * Sets the {@code imageUrl} and returns a reference to this Builder enabling method chaining.
		 *
		 * @param imageUrl the {@code imageUrl} to set
		 * @return a reference to this Builder
		 */
		public Builder withImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
			return this;
		}

		/**
		 * Sets the {@code smallImageUrl} and returns a reference to this Builder enabling method chaining.
		 *
		 * @param smallImageUrl the {@code smallImageUrl} to set
		 * @return a reference to this Builder
		 */
		public Builder withSmallImageUrl(String smallImageUrl) {
			this.smallImageUrl = smallImageUrl;
			return this;
		}

		/**
		 * Returns a {@code BestBook} built from the parameters previously set.
		 *
		 * @return a {@code BestBook} built with parameters of this {@code BestBook.Builder}
		 */
		public BestBook build() {
			return new BestBook(this);
		}
	}
}
