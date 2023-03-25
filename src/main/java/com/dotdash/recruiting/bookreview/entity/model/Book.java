package com.dotdash.recruiting.bookreview.entity.model;

public class Book {
    private Long id;

    private Book(Builder builder) {
        setId(builder.id);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * {@code Book} builder static inner class.
     */
    public static final class Builder {
        private Long id;

        private Builder() {
        }

        /**
         * Sets the {@code id} and returns a reference to this Builder enabling method chaining.
         *
         * @param id the {@code id} to set
         * @return a reference to this Builder
         */
        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        /**
         * Returns a {@code Book} built from the parameters previously set.
         *
         * @return a {@code Book} built with parameters of this {@code Book.Builder}
         */
        public Book build() {
            return new Book(this);
        }
    }
}
