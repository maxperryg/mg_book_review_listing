package com.dotdash.recruiting.bookreview.handler;

import com.dotdash.recruiting.bookreview.dao.api.IGoodReadsApiDao;
import com.dotdash.recruiting.bookreview.entity.dto.BookDto;
import com.dotdash.recruiting.bookreview.entity.dto.CollectionDto;
import com.dotdash.recruiting.bookreview.entity.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

@Component
public class BooksHandler {
    // Fields
    private static final String FIELD_TITLE = "title";
    private static final String FIELD_AUTHOR = "author";
    private static final String FIELD_ALL = "all";

    // Comparator functions for the BookDto object
    private static final Function<BookDto, String> sortFieldAuthor = BookDto::getAuthor;
    private static final Function<BookDto, String> sortFieldTitle = BookDto::getTitle;

    // Logging related
    private static final String ERROR_UNKNOWN_SORT_FIELD = "%s is not a valid sort field";
    private static final String ERROR_UNKNOWN_SEARCH_FIELD = "%s is not a valid search field";

    // Dependencies
    private IGoodReadsApiDao goodReadsApiDao;

    /**
     * Get a list of {@link BookDto} based on a search query
     *
     * @param query search query
     * @param page page number
     * @param searchBy field to search by
     * @param sortBy field to sort by
     *
     * @return {@link CollectionDto}
     * Collection entity containing the book info
     */
    public CollectionDto<BookDto> searchBooks(String query, Long page, String searchBy, String sortBy) {
        // validate the request params
        var validatedSearchByField = getValidatedSearchByField(searchBy);
        var validatedSortByField = getValidatedSortByField(sortBy);
        // TODO: validate search query

        // Query for books from the API
        var booksResponse = goodReadsApiDao.searchBooks(query, page, validatedSearchByField);

        var search = booksResponse.getSearch();
        var totalResults = search.getTotalResults();

        // Return an empty list if there are no results
        // Not an error, just no results
        var worksList = search.results.getWork();
        if (worksList == null) {
            worksList = List.of();
        }

        // Sort by whichever field was requested to sort by
        var sortedBookDtoList = worksList.stream()
                .map(book -> BookDto.newBuilder()
                        .withTitle(book.getBestBook().getTitle())
                        .withAuthor(book.bestBook.author.getName())
                        .withImage(book.bestBook.imageUrl)
                        .build())
                .sorted((Comparator.comparing(validatedSortByField)))
                .toList();

        return CollectionDto.<BookDto>newBuilder()
                .withPage(page)
                .withRpp(sortedBookDtoList.size())
                .withTotalItems(totalResults)
                .withTotalPages((int) Math.ceil((double) totalResults / sortedBookDtoList.size()))
                .withItems(sortedBookDtoList)
                .build();
    }

    /**
     * Validate the search by field. Can only be "title", "author", or "all
     *
     * @param searchBy the search by field
     *
     * @return the valid search by field
     */
    private static String getValidatedSearchByField(String searchBy) {
        return switch (searchBy.toLowerCase()) {
            case FIELD_TITLE -> FIELD_TITLE;
            case FIELD_AUTHOR -> FIELD_AUTHOR;
            case FIELD_ALL -> FIELD_ALL;
            default -> throw new BadRequestException(String.format(ERROR_UNKNOWN_SEARCH_FIELD, searchBy));
        };
    }

    /**
     * Validate the sort by field. Can only be "title" or "author"
     *
     * @param sortBy the search by field
     *
     * @return the valid sort by field
     */
    private static Function<BookDto, String> getValidatedSortByField(String sortBy) {
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
