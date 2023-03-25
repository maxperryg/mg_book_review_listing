package com.dotdash.recruiting.bookreview.controller;

import com.dotdash.recruiting.bookreview.entity.dto.BookDto;
import com.dotdash.recruiting.bookreview.handler.BooksHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksController {
    private BooksHandler booksHandler;

    @GetMapping("/book/search/{bookId}")
    public ResponseEntity<BookDto> SearchBookById(@PathVariable Long bookId) {
        var bookDto = booksHandler.searchBookById(bookId);
        return ResponseEntity.ok(bookDto);
    }

    @Autowired
    public void setBooksHandler(BooksHandler booksHandler) {
        this.booksHandler = booksHandler;
    }
}
