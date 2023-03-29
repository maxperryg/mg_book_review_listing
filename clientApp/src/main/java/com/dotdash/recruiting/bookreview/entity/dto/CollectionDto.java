package com.dotdash.recruiting.bookreview.entity.dto;

import java.util.List;

public class CollectionDto<T> {
    Long page;
    Integer rpp;
    Long totalItems;
    Integer totalPages;
    List<T> items;

    public CollectionDto() {
    }

    private CollectionDto(Builder<T> builder) {
        setPage(builder.page);
        setRpp(builder.rpp);
        setTotalItems(builder.totalItems);
        setTotalPages(builder.totalPages);
        setItems(builder.items);
    }

    public static <T> Builder<T> newBuilder() {
        return new Builder<T>();
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Integer getRpp() {
        return rpp;
    }

    public void setRpp(Integer rpp) {
        this.rpp = rpp;
    }

    public Long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Long totalItems) {
        this.totalItems = totalItems;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    /**
     * {@code CollectionDto} builder static inner class.
     */
    public static final class Builder<T> {
        private Long page;
        private Integer rpp;
        private Long totalItems;
        private Integer totalPages;
        private List<T> items;

        private Builder() {
        }

        /**
         * Sets the {@code page} and returns a reference to this Builder enabling method chaining.
         *
         * @param page the {@code page} to set
         * @return a reference to this Builder
         */
        public Builder<T> withPage(Long page) {
            this.page = page;
            return this;
        }

        /**
         * Sets the {@code rpp} and returns a reference to this Builder enabling method chaining.
         *
         * @param rpp the {@code rpp} to set
         * @return a reference to this Builder
         */
        public Builder<T> withRpp(Integer rpp) {
            this.rpp = rpp;
            return this;
        }

        /**
         * Sets the {@code totalItems} and returns a reference to this Builder enabling method chaining.
         *
         * @param totalItems the {@code totalItems} to set
         * @return a reference to this Builder
         */
        public Builder<T> withTotalItems(Long totalItems) {
            this.totalItems = totalItems;
            return this;
        }

        /**
         * Sets the {@code totalPages} and returns a reference to this Builder enabling method chaining.
         *
         * @param totalPages the {@code totalItems} to set
         * @return a reference to this Builder
         */
        public Builder<T> withTotalPages(Integer totalPages) {
            this.totalPages = totalPages;
            return this;
        }

        /**
         * Sets the {@code items} and returns a reference to this Builder enabling method chaining.
         *
         * @param items the {@code items} to set
         * @return a reference to this Builder
         */
        public Builder<T> withItems(List<T> items) {
            this.items = items;
            return this;
        }

        /**
         * Returns a {@code CollectionDto} built from the parameters previously set.
         *
         * @return a {@code CollectionDto} built with parameters of this {@code CollectionDto.Builder}
         */
        public CollectionDto<T> build() {
            return new CollectionDto<T>(this);
        }
    }
}
