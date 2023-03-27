package com.dotdash.recruiting.bookreview.entity.dto;

public class BookDto {
    private String name;

    private BookDto(Builder builder) {
        setName(builder.name);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@code BookDto} builder static inner class.
     */
    public static final class Builder {
        private String name;

        private Builder() {
        }

        /**
         * Sets the {@code name} and returns a reference to this Builder enabling method chaining.
         *
         * @param name the {@code name} to set
         * @return a reference to this Builder
         */
        public Builder withName(String name) {
            this.name = name;
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
