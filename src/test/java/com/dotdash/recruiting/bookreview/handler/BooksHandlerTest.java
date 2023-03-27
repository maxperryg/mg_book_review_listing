package com.dotdash.recruiting.bookreview.handler;

import com.dotdash.recruiting.bookreview.dao.api.IGoodReadsApiDao;
import com.dotdash.recruiting.bookreview.entity.dto.BookDto;
import com.dotdash.recruiting.bookreview.entity.dto.CollectionDto;
import com.dotdash.recruiting.bookreview.entity.model.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class BooksHandlerTest {
    @Mock
    IGoodReadsApiDao goodReadsApiDaoMock;

    @InjectMocks
    BooksHandler booksHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_searchBooks_searchBySortByTitle() {
        // Mocks and entities
        var query = "test query";
        var page = 1L;
        var searchBy = "tiTlE";
        var sortBy = "title";

        var mockedGoodReadsResponse = GoodreadsResponse.newBuilder()
                .withSearch(Search.newBuilder()
                        .withTotalResults(1L)
                        .withResults(Results.newBuilder()
                                .withWork(List.of(
                                        Work.newBuilder()
                                                .withBestBook(BestBook.newBuilder()
                                                        .withTitle("test title")
                                                        .withAuthor(Author.newBuilder()
                                                                .withName("test author")
                                                                .build())
                                                        .withImageUrl("test image")
                                                        .build())
                                                .build()
                                ))
                                .build())
                        .build())
                .build();

        var expectedBookDtos = List.of(
                BookDto.newBuilder()
                        .withTitle("test title")
                        .withAuthor("test author")
                        .withImage("test image")
                        .build());
        var expectedCollection = CollectionDto.<BookDto>newBuilder()
                .withPage(1L)
                .withRpp(1)
                .withTotalItems(1L)
                .withTotalPages(1)
                .withItems(expectedBookDtos)
                .build();
        when(goodReadsApiDaoMock.searchBooks(query, page, searchBy.toLowerCase())).thenReturn(mockedGoodReadsResponse);

        // Calls
        var actualResponse = booksHandler.searchBooks(query, page, searchBy, sortBy);

        // Validations
        Assertions.assertThat(actualResponse).usingRecursiveComparison().isEqualTo(expectedCollection);
        verify(goodReadsApiDaoMock, times(1)).searchBooks(query, page, searchBy.toLowerCase());
    }

    @Test
    void test_searchBooks_searchBySortByAuthor() {
        // Mocks and entities
        var query = "test query";
        var page = 1L;
        var searchBy = "AuThoR";
        var sortBy = "aUThoR";

        var mockedGoodReadsResponse = GoodreadsResponse.newBuilder()
                .withSearch(Search.newBuilder()
                        .withTotalResults(1L)
                        .withResults(Results.newBuilder()
                                .withWork(List.of(
                                        Work.newBuilder()
                                                .withBestBook(BestBook.newBuilder()
                                                        .withTitle("test title")
                                                        .withAuthor(Author.newBuilder()
                                                                .withName("test author")
                                                                .build())
                                                        .withImageUrl("test image")
                                                        .build())
                                                .build()
                                ))
                                .build())
                        .build())
                .build();

        var expectedBookDtos = List.of(
                BookDto.newBuilder()
                        .withTitle("test title")
                        .withAuthor("test author")
                        .withImage("test image")
                        .build());
        var expectedCollection = CollectionDto.<BookDto>newBuilder()
                .withPage(1L)
                .withRpp(1)
                .withTotalItems(1L)
                .withTotalPages(1)
                .withItems(expectedBookDtos)
                .build();
        when(goodReadsApiDaoMock.searchBooks(query, page, searchBy.toLowerCase())).thenReturn(mockedGoodReadsResponse);

        // Calls
        var actualResponse = booksHandler.searchBooks(query, page, searchBy, sortBy);

        // Validations
        Assertions.assertThat(actualResponse).usingRecursiveComparison().isEqualTo(expectedCollection);
        verify(goodReadsApiDaoMock, times(1)).searchBooks(query, page, searchBy.toLowerCase());
    }

    @Test
    void test_searchBooks_searchByAll() {
        // Mocks and entities
        var query = "test query";
        var page = 1L;
        var searchBy = "All";
        var sortBy = "aUThoR";

        var mockedGoodReadsResponse = GoodreadsResponse.newBuilder()
                .withSearch(Search.newBuilder()
                        .withTotalResults(1L)
                        .withResults(Results.newBuilder()
                                .withWork(List.of(
                                        Work.newBuilder()
                                                .withBestBook(BestBook.newBuilder()
                                                        .withTitle("test title")
                                                        .withAuthor(Author.newBuilder()
                                                                .withName("test author")
                                                                .build())
                                                        .withImageUrl("test image")
                                                        .build())
                                                .build()
                                ))
                                .build())
                        .build())
                .build();

        var expectedBookDtos = List.of(
                BookDto.newBuilder()
                        .withTitle("test title")
                        .withAuthor("test author")
                        .withImage("test image")
                        .build());
        var expectedCollection = CollectionDto.<BookDto>newBuilder()
                .withPage(1L)
                .withRpp(1)
                .withTotalItems(1L)
                .withTotalPages(1)
                .withItems(expectedBookDtos)
                .build();
        when(goodReadsApiDaoMock.searchBooks(query, page, searchBy.toLowerCase())).thenReturn(mockedGoodReadsResponse);

        // Calls
        var actualResponse = booksHandler.searchBooks(query, page, searchBy, sortBy);

        // Validations
        Assertions.assertThat(actualResponse).usingRecursiveComparison().isEqualTo(expectedCollection);
        verify(goodReadsApiDaoMock, times(1)).searchBooks(query, page, searchBy.toLowerCase());
    }

    @Test
    void test_searchBooks_unknownSearchBy() {
        // Mocks and entities
        var query = "test query";
        var page = 1L;
        var searchBy = "UnKnowN SeArcH";
        var sortBy = "title";

        var mockedGoodReadsResponse = GoodreadsResponse.newBuilder()
                .withSearch(Search.newBuilder()
                        .withTotalResults(1L)
                        .withResults(Results.newBuilder()
                                .withWork(List.of(
                                        Work.newBuilder()
                                                .withBestBook(BestBook.newBuilder()
                                                        .withTitle("test title")
                                                        .withAuthor(Author.newBuilder()
                                                                .withName("test author")
                                                                .build())
                                                        .withImageUrl("test image")
                                                        .build())
                                                .build()
                                ))
                                .build())
                        .build())
                .build();

        var expectedBookDtos = List.of(
                BookDto.newBuilder()
                        .withTitle("test title")
                        .withAuthor("test author")
                        .withImage("test image")
                        .build());
        var expectedCollection = CollectionDto.<BookDto>newBuilder()
                .withPage(1L)
                .withRpp(1)
                .withTotalItems(1L)
                .withTotalPages(1)
                .withItems(expectedBookDtos)
                .build();
        when(goodReadsApiDaoMock.searchBooks(query, page, searchBy)).thenReturn(mockedGoodReadsResponse);

        // Calls
        String message = "";
        try {
            booksHandler.searchBooks(query, page, searchBy, sortBy);
        } catch (Exception e) {
            message = e.getMessage();
        }

        // Validations
        Assertions.assertThat(message)
                .isEqualTo("UnKnowN SeArcH is not a valid search field");
        verify(goodReadsApiDaoMock, times(0)).searchBooks(query, page, searchBy);
    }

    @Test
    void test_searchBooks_unknownSortBy() {
        // Mocks and entities
        var query = "test query";
        var page = 1L;
        var searchBy = "title";
        var sortBy = "UnKnowN SOrT";

        var mockedGoodReadsResponse = GoodreadsResponse.newBuilder()
                .withSearch(Search.newBuilder()
                        .withTotalResults(1L)
                        .withResults(Results.newBuilder()
                                .withWork(List.of(
                                        Work.newBuilder()
                                                .withBestBook(BestBook.newBuilder()
                                                        .withTitle("test title")
                                                        .withAuthor(Author.newBuilder()
                                                                .withName("test author")
                                                                .build())
                                                        .withImageUrl("test image")
                                                        .build())
                                                .build()
                                ))
                                .build())
                        .build())
                .build();

        var expectedBookDtos = List.of(
                BookDto.newBuilder()
                        .withTitle("test title")
                        .withAuthor("test author")
                        .withImage("test image")
                        .build());
        var expectedCollection = CollectionDto.<BookDto>newBuilder()
                .withPage(1L)
                .withRpp(1)
                .withTotalItems(1L)
                .withTotalPages(1)
                .withItems(expectedBookDtos)
                .build();
        when(goodReadsApiDaoMock.searchBooks(query, page, searchBy)).thenReturn(mockedGoodReadsResponse);

        // Calls
        String message = "";
        try {
            booksHandler.searchBooks(query, page, searchBy, sortBy);
        } catch (Exception e) {
            message = e.getMessage();
        }

        // Validations
        Assertions.assertThat(message)
                .isEqualTo("UnKnowN SOrT is not a valid sort field");
        verify(goodReadsApiDaoMock, times(0)).searchBooks(query, page, searchBy);
    }

    @Test
    void test_searchBooks_nullResults() {
        // Mocks and entities
        var query = "test query";
        var page = 1L;
        var searchBy = "tiTlE";
        var sortBy = "title";

        var mockedGoodReadsResponse = GoodreadsResponse.newBuilder()
                .withSearch(Search.newBuilder()
                        .withTotalResults(0L)
                        .withResults(Results.newBuilder()
                                .build())
                        .build())
                .build();

        var expectedBookDtos = new ArrayList<BookDto>();
        var expectedCollection = CollectionDto.<BookDto>newBuilder()
                .withPage(1L)
                .withRpp(0)
                .withTotalItems(0L)
                .withTotalPages(0)
                .withItems(expectedBookDtos)
                .build();
        when(goodReadsApiDaoMock.searchBooks(query, page, searchBy.toLowerCase())).thenReturn(mockedGoodReadsResponse);

        // Calls
        var actualResponse = booksHandler.searchBooks(query, page, searchBy, sortBy);

        // Validations
        Assertions.assertThat(actualResponse).usingRecursiveComparison().isEqualTo(expectedCollection);
        verify(goodReadsApiDaoMock, times(1)).searchBooks(query, page, searchBy.toLowerCase());
    }

    @Test
    void test_searchBooks_emptyResults() {
        // Mocks and entities
        var query = "test query";
        var page = 1L;
        var searchBy = "tiTlE";
        var sortBy = "title";

        var mockedGoodReadsResponse = GoodreadsResponse.newBuilder()
                .withSearch(Search.newBuilder()
                        .withTotalResults(0L)
                        .withResults(Results.newBuilder()
                                .withWork(List.of())
                                .build())
                        .build())
                .build();

        var expectedBookDtos = new ArrayList<BookDto>();
        var expectedCollection = CollectionDto.<BookDto>newBuilder()
                .withPage(1L)
                .withRpp(0)
                .withTotalItems(0L)
                .withTotalPages(0)
                .withItems(expectedBookDtos)
                .build();
        when(goodReadsApiDaoMock.searchBooks(query, page, searchBy.toLowerCase())).thenReturn(mockedGoodReadsResponse);

        // Calls
        var actualResponse = booksHandler.searchBooks(query, page, searchBy, sortBy);

        // Validations
        Assertions.assertThat(actualResponse).usingRecursiveComparison().isEqualTo(expectedCollection);
        verify(goodReadsApiDaoMock, times(1)).searchBooks(query, page, searchBy.toLowerCase());
    }


}

