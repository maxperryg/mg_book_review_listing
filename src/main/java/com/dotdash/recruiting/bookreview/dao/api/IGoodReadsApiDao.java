package com.dotdash.recruiting.bookreview.dao.api;

import com.dotdash.recruiting.bookreview.entity.model.GoodreadsResponse;

public interface IGoodReadsApiDao {
    /**
     * Get a {@link GoodreadsResponse} based on a search query
     *
     * @param query search query
     * @param page page number
     * @param searchBy field to search by
     *
     * @return {@link GoodreadsResponse}
     * GoodReadsResponse containing the book info
     */
    GoodreadsResponse searchBooks(String query, Long page, String searchBy);
}
