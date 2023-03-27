package com.dotdash.recruiting.bookreview.controller;

import com.dotdash.recruiting.bookreview.entity.dto.BookDto;
import com.dotdash.recruiting.bookreview.entity.dto.CollectionDto;
import com.dotdash.recruiting.bookreview.handler.BooksHandler;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.Mockito.*;

public class BooksControllerTest {
    @Mock
    BooksHandler booksHandlerMock;

    @InjectMocks
    BooksController booksController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_searchBooks() {
        // Mocks and entities
        var query = "test query";
        var page = 1L;
        var searchBy = "testSearchBy";
        var sortBy = "testSortBy";
        var expectedBookDtos = List.of(BookDto.newBuilder().build());
        var expectedCollection = CollectionDto.<BookDto>newBuilder().withItems(expectedBookDtos).build();
        var expectedResponse = ResponseEntity.ok(expectedCollection);
        when(booksHandlerMock.searchBooks(query, page, searchBy, sortBy)).thenReturn(CollectionDto.<BookDto>newBuilder().withItems(expectedBookDtos).build());

        // Calls
        var actualResponse = booksController.searchBooks(query, page, searchBy, sortBy);

        // Validations
        Assertions.assertThat(expectedResponse).usingRecursiveComparison().isEqualTo(actualResponse);
        verify(booksHandlerMock, times(1)).searchBooks(query, page, searchBy, sortBy);
    }

    @Test
    void test_searchBooks_emptyParams() {
        // Mocks and entities
        var query = "";
        Long page = null;
        var searchBy = "";
        var sortBy = "";
        var expectedBookDtos = List.of(BookDto.newBuilder().build());
        var expectedCollection = CollectionDto.<BookDto>newBuilder().withItems(expectedBookDtos).build();
        var expectedResponse = ResponseEntity.ok(expectedCollection);
        when(booksHandlerMock.searchBooks(query, page, searchBy, sortBy)).thenReturn(CollectionDto.<BookDto>newBuilder().withItems(expectedBookDtos).build());

        // Calls
        var actualResponse = booksController.searchBooks(query, page, searchBy, sortBy);

        // Validations
        Assertions.assertThat(expectedResponse).usingRecursiveComparison().isEqualTo(actualResponse);
    }

    @Test
    void test_searchBooks_NullParams() {
        // Mocks and entities
        String query = null;
        Long page = null;
        String searchBy = null;
        String sortBy = null;
        var expectedBookDtos = List.of(BookDto.newBuilder().build());
        var expectedCollection = CollectionDto.<BookDto>newBuilder().withItems(expectedBookDtos).build();
        var expectedResponse = ResponseEntity.ok(expectedCollection);
        when(booksHandlerMock.searchBooks(query, page, searchBy, sortBy)).thenReturn(CollectionDto.<BookDto>newBuilder().withItems(expectedBookDtos).build());

        // Calls
        var actualResponse = booksController.searchBooks(query, page, searchBy, sortBy);

        // Validations
        Assertions.assertThat(expectedResponse).usingRecursiveComparison().isEqualTo(actualResponse);
    }
}
