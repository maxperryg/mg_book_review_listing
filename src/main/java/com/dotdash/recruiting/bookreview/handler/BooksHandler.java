package com.dotdash.recruiting.bookreview.handler;

import com.dotdash.recruiting.bookreview.dao.api.IGoodReadsApiDao;
import com.dotdash.recruiting.bookreview.entity.dto.BookDto;
import com.dotdash.recruiting.bookreview.entity.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

@Component
public class BooksHandler {
    private static final String FIELD_TITLE = "title";
    private static final String FIELD_AUTHOR = "author";
    private static final String FIELD_ALL = "all";

    private static final Function<BookDto, String> sortFieldAuthor = BookDto::getAuthor;
    private static final Function<BookDto, String> sortFieldTitle = BookDto::getTitle;

    private static final String ERROR_UNKNOWN_SORT_FIELD = "%s is not a valid sort field";
    private static final String ERROR_UNKNOWN_SEARCH_FIELD = "%s is not a valid search field";

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private IGoodReadsApiDao goodReadsApiDao;

    public List<BookDto> searchBooks(String query, Long page, String searchBy, String sortBy) {
        var validatedSearchByField = getValidatedSearchByField(searchBy);
        var validatedSortByField = getValidatedSortByField(sortBy);
        var booksResponse = goodReadsApiDao.searchBooks(query, page, validatedSearchByField);

        var worksList = booksResponse.getSearch().results.getWork();
        if (worksList == null) {
            return List.of();
        }

        return booksResponse.getSearch().results.getWork().stream()
                .map(book -> BookDto.newBuilder()
                        .withTitle(book.getBestBook().getTitle())
                        .withAuthor(book.bestBook.author.getName())
                        .withImage(book.bestBook.imageUrl)
                        .build())
                .sorted(Comparator.comparing(validatedSortByField))
                .toList();
    }

    private String getValidatedSearchByField(String searchBy) {
        return switch (searchBy.toLowerCase()) {
            case FIELD_TITLE -> FIELD_TITLE;
            case FIELD_AUTHOR -> FIELD_AUTHOR;
            case FIELD_ALL -> FIELD_ALL;
            default -> throw new BadRequestException(String.format(ERROR_UNKNOWN_SEARCH_FIELD, searchBy));
        };
    }

    private Function<BookDto, String> getValidatedSortByField(String sortBy) {
        return switch (sortBy.toLowerCase()) {
            case FIELD_TITLE -> sortFieldTitle;
            case FIELD_AUTHOR -> sortFieldAuthor;
            default -> throw new BadRequestException(String.format(ERROR_UNKNOWN_SORT_FIELD, sortBy));
        };
    }

    @Autowired
    public void setGoodReadsApiDao(IGoodReadsApiDao goodReadsApiDao) {
        this.goodReadsApiDao = goodReadsApiDao;
    }
}
