package com.dotdash.recruiting.bookreview.entity.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Work {
	public Id id;

	@JsonProperty("books_count")
	public BooksCount booksCount;

	@JsonProperty("ratings_count")
	public RatingsCount ratingsCount;

	@JsonProperty("test_reviews_count")
	public TextReviewsCount textReviewsCount;

	@JsonProperty("original_publication_year")
	public OriginalPublicationYear originalPublicationYear;

	@JsonProperty("original_publication_month")
	public OriginalPublicationMonth originalPublicationMonth;

	@JsonProperty("original_publication_day")
	public OriginalPublicationDay originalPublicationDay;

	@JsonProperty("average_rating")
	public String averageRating;

	@JsonProperty("best_book")
	public BestBook bestBook;

	public Work() {
	}

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public BooksCount getBooksCount() {
		return booksCount;
	}

	public void setBooksCount(BooksCount booksCount) {
		this.booksCount = booksCount;
	}

	public RatingsCount getRatingsCount() {
		return ratingsCount;
	}

	public void setRatingsCount(RatingsCount ratingsCount) {
		this.ratingsCount = ratingsCount;
	}

	public TextReviewsCount getTextReviewsCount() {
		return textReviewsCount;
	}

	public void setTextReviewsCount(TextReviewsCount textReviewsCount) {
		this.textReviewsCount = textReviewsCount;
	}

	public OriginalPublicationYear getOriginalPublicationYear() {
		return originalPublicationYear;
	}

	public void setOriginalPublicationYear(OriginalPublicationYear originalPublicationYear) {
		this.originalPublicationYear = originalPublicationYear;
	}

	public OriginalPublicationMonth getOriginalPublicationMonth() {
		return originalPublicationMonth;
	}

	public void setOriginalPublicationMonth(OriginalPublicationMonth originalPublicationMonth) {
		this.originalPublicationMonth = originalPublicationMonth;
	}

	public OriginalPublicationDay getOriginalPublicationDay() {
		return originalPublicationDay;
	}

	public void setOriginalPublicationDay(OriginalPublicationDay originalPublicationDay) {
		this.originalPublicationDay = originalPublicationDay;
	}

	public String getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(String averageRating) {
		this.averageRating = averageRating;
	}

	public BestBook getBestBook() {
		return bestBook;
	}

	public void setBestBook(BestBook bestBook) {
		this.bestBook = bestBook;
	}
}
