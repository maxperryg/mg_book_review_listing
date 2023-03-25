package com.dotdash.recruiting.bookreview.handler;

import com.dotdash.recruiting.bookreview.dao.api.IGoodReadsApiDao;
import com.dotdash.recruiting.bookreview.entity.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BooksHandler {
    private IGoodReadsApiDao goodReadsApiDao;

    public BookDto searchBookById(Long bookId) {
        var book = goodReadsApiDao.getBookById(bookId);
        return BookDto.newBuilder().withId(book.getId()).build();
    }

    @Autowired
    public void setGoodReadsApiDao(IGoodReadsApiDao goodReadsApiDao) {
        this.goodReadsApiDao = goodReadsApiDao;
    }
}
