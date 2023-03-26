package com.dotdash.recruiting.bookreview.handler;

import com.dotdash.recruiting.bookreview.dao.api.IGoodReadsApiDao;
import com.dotdash.recruiting.bookreview.entity.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BooksHandler {
    private IGoodReadsApiDao goodReadsApiDao;

    public List<BookDto> searchBooks() {
        var booksResponse = goodReadsApiDao.searchBooks();
        return booksResponse.getSearch().results.getWork().stream()
                .map(book -> BookDto.newBuilder()
                        .withName(book.getBest_book().getTitle())
                        .build())
                .toList();
    }

    @Autowired
    public void setGoodReadsApiDao(IGoodReadsApiDao goodReadsApiDao) {
        this.goodReadsApiDao = goodReadsApiDao;
    }
}
