package com.dotdash.recruiting.bookreview.controller;

import com.dotdash.recruiting.bookreview.entity.dto.BookDto;
import com.dotdash.recruiting.bookreview.handler.BooksHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BooksController {
    private static final String ENDPOINT_SEARCH = "/book/search";

    private BooksHandler booksHandler;

    @GetMapping(value = ENDPOINT_SEARCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookDto>> SearchBooks() {
        var bookDto = booksHandler.searchBooks();
        return ResponseEntity.ok(bookDto);
    }

    @Autowired
    public void setBooksHandler(BooksHandler booksHandler) {
        this.booksHandler = booksHandler;
    }
}
