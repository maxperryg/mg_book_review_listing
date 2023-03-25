package com.dotdash.recruiting.bookreview.dao.api;

import com.dotdash.recruiting.bookreview.entity.model.Book;

public interface IGoodReadsApiDao {
    public Book getBookById(Long bookId);
}
