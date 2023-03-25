package com.dotdash.recruiting.bookreview.entity.dto;

public class BookDto {
    private Long id;

    private BookDto(Builder builder) {
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
     * {@code BookDto} builder static inner class.
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
         * Returns a {@code BookDto} built from the parameters previously set.
         *
         * @return a {@code BookDto} built with parameters of this {@code BookDto.Builder}
         */
        public BookDto build() {
            return new BookDto(this);
        }
    }
}
