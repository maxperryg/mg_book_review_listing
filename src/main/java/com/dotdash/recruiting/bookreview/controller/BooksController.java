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
    // Endpoints
    private static final String ENDPOINT_SEARCH = "/book/search";

    // Defaults for query params
    private static final String DEFAULT_PAGE = "1";
    private static final String DEFAULT_SEARCH_BY = "all";
    private static final String DEFAULT_SORT_BY = "title";

    // Dependencies
    private BooksHandler booksHandler;

    /**
     * Get a list of {@link BookDto} based on a search query
     *
     * @param query search query
     * @param page page number
     * @param searchBy field to search by
     * @param sortBy field to sort by
     *
     * @return {@link ResponseEntity}
     * HTTP response with a collection entity containing the book info
     */
    @GetMapping(value = ENDPOINT_SEARCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionDto<BookDto>> searchBooks(@RequestParam String query,
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
