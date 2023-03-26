package com.dotdash.recruiting.bookreview.dao.api;

import com.dotdash.recruiting.bookreview.entity.model.GoodreadsResponse;

public interface IGoodReadsApiDao {
    public GoodreadsResponse searchBooks();
}
