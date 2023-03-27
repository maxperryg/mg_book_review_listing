package com.dotdash.recruiting.bookreview.controller;

import com.dotdash.recruiting.bookreview.entity.dto.BookDto;
import com.dotdash.recruiting.bookreview.entity.dto.CollectionDto;
import com.dotdash.recruiting.bookreview.handler.BooksHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksController {
    private static final String ENDPOINT_SEARCH = "/book/search";

    private static final String DEFAULT_PAGE = "1";
    private static final String DEFAULT_SEARCH_BY = "all";
    private static final String DEFAULT_SORT_BY = "title";

    private BooksHandler booksHandler;

    @GetMapping(value = ENDPOINT_SEARCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionDto<BookDto>> SearchBooks(@RequestParam String query,
                                                              @RequestParam(required = false, defaultValue = DEFAULT_PAGE) Long page,
                                                              @RequestParam(required = false, defaultValue = DEFAULT_SEARCH_BY) String searchBy,
                                                              @RequestParam(required = false, defaultValue = DEFAULT_SORT_BY) String sortBy) {
        var bookDto = booksHandler.searchBooks(query, page, searchBy, sortBy);
        return ResponseEntity.ok(bookDto);
    }

    @Autowired
    public void setBooksHandler(BooksHandler booksHandler) {
        this.booksHandler = booksHandler;
    }
}
