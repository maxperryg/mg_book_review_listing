package com.dotdash.recruiting.bookreview.entity.model;

import java.math.BigDecimal;

public class Work {
	public Id id;
	public BooksCount books_count;
	public RatingsCount ratings_count;
	public TextReviewsCount text_reviews_count;
	public OriginalPublicationYear original_publication_year;
	public OriginalPublicationMonth original_publication_month;
	public OriginalPublicationDay original_publication_day;
	public BigDecimal average_rating;
	public BestBook best_book;

	public Work() {
	}

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public BooksCount getBooks_count() {
		return books_count;
	}

	public void setBooks_count(BooksCount books_count) {
		this.books_count = books_count;
	}

	public RatingsCount getRatings_count() {
		return ratings_count;
	}

	public void setRatings_count(RatingsCount ratings_count) {
		this.ratings_count = ratings_count;
	}

	public TextReviewsCount getText_reviews_count() {
		return text_reviews_count;
	}

	public void setText_reviews_count(TextReviewsCount text_reviews_count) {
		this.text_reviews_count = text_reviews_count;
	}

	public OriginalPublicationYear getOriginal_publication_year() {
		return original_publication_year;
	}

	public void setOriginal_publication_year(OriginalPublicationYear original_publication_year) {
		this.original_publication_year = original_publication_year;
	}

	public OriginalPublicationMonth getOriginal_publication_month() {
		return original_publication_month;
	}

	public void setOriginal_publication_month(OriginalPublicationMonth original_publication_month) {
		this.original_publication_month = original_publication_month;
	}

	public OriginalPublicationDay getOriginal_publication_day() {
		return original_publication_day;
	}

	public void setOriginal_publication_day(OriginalPublicationDay original_publication_day) {
		this.original_publication_day = original_publication_day;
	}

	public BigDecimal getAverage_rating() {
		return average_rating;
	}

	public void setAverage_rating(BigDecimal average_rating) {
		this.average_rating = average_rating;
	}

	public BestBook getBest_book() {
		return best_book;
	}

	public void setBest_book(BestBook best_book) {
		this.best_book = best_book;
	}
}
