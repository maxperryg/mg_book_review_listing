package com.dotdash.recruiting.bookreview.entity.dto;

public class BookDto {
    private String title;
    private String author;
    private String image;

    public BookDto() {
    }

    private BookDto(Builder builder) {
        setTitle(builder.title);
        setAuthor(builder.author);
        setImage(builder.image);
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    /**
     * {@code BookDto} builder static inner class.
     */
    public static final class Builder {
        private String title;
        private String author;
        private String image;

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
        public Builder withAuthor(String author) {
            this.author = author;
            return this;
        }

        /**
         * Sets the {@code image} and returns a reference to this Builder enabling method chaining.
         *
         * @param image the {@code image} to set
         * @return a reference to this Builder
         */
        public Builder withImage(String image) {
            this.image = image;
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
