package com.dotdash.recruiting.bookreview.dao.api;

import com.dotdash.recruiting.bookreview.entity.model.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockedConstruction;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

public class GoodReadsApiDaoImplTest {
    @InjectMocks
    GoodReadsApiDaoImpl goodReadsApiDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(goodReadsApiDao, "GOOD_READS_API_URL_HOST", "goodreads.com");
        ReflectionTestUtils.setField(goodReadsApiDao, "GOOD_READS_API_URL_PATH", "search");
        ReflectionTestUtils.setField(goodReadsApiDao, "GOOD_READS_API_KEY", "123456");
        ReflectionTestUtils.setField(goodReadsApiDao, "GOOD_READS_API_SECRET", "654321");
    }

    @Test
    void test_searchBooks() {
        // Mocks and entities
        var query = "test query";
        var page = 1L;
        var searchBy = "test search by";
        var mockedUri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("goodreads.com")
                .path("search")
                .queryParam("key", "123456")
                .queryParam("q", query)
                .queryParam("page", page)
                .queryParam("search[field]", searchBy)
                .build()
                .toUriString();
        var mockedHeaders = new HttpHeaders();
        mockedHeaders.setAccept(List.of(MediaType.APPLICATION_XML));
        var MockedEntity = new HttpEntity<>(mockedHeaders);

        var expectedGoodReadsResponse = GoodreadsResponse.newBuilder()
                .withSearch(Search.newBuilder()
                        .withTotalResults(88794L)
                        .withResults(Results.newBuilder()
                                .withWork(List.of(
                                        Work.newBuilder()
                                                .withBestBook(BestBook.newBuilder()
                                                        .withTitle("Harry Potter and the Philosopher’s Stone (Harry Potter, #1)")
                                                        .withAuthor(Author.newBuilder()
                                                                .withName("J.K. Rowling")
                                                                .build())
                                                        .withImageUrl("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1170803558l/72193._SX98_SY160_.jpg")
                                                        .withSmallImageUrl("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1170803558l/72193._SX50_SY75_.jpg")
                                                        .build())
                                                .build()
                                ))
                                .build())
                        .build())
                .build();
        var mockedXml = """
                <?xml version="1.0" encoding="UTF-8"?>
                <GoodreadsResponse>
                    <Request>
                        <authentication>true</authentication>
                        <key><![CDATA[RDfV4oPehM6jNhxfNQzzQ]]></key>
                        <method><![CDATA[search_index]]></method>
                    </Request>
                    <search>
                        <query><![CDATA[harry]]></query>
                        <results-start>1</results-start>
                        <results-end>20</results-end>
                        <total-results>88794</total-results>
                        <source>Goodreads</source>
                        <query-time-seconds>0.08</query-time-seconds>
                        <results>
                            <work>
                                <id type="integer">4640799</id>
                                <books_count type="integer">1048</books_count>
                                <ratings_count type="integer">9212675</ratings_count>
                                <text_reviews_count type="integer">145962</text_reviews_count>
                                <original_publication_year type="integer">1997</original_publication_year>
                                <original_publication_month type="integer">6</original_publication_month>
                                <original_publication_day type="integer">26</original_publication_day>
                                <average_rating>4.47</average_rating>
                                <best_book type="Book">
                                    <id type="integer">72193</id>
                                    <title>Harry Potter and the Philosopher’s Stone (Harry Potter, #1)</title>
                                    <author>
                                        <id type="integer">1077326</id>
                                        <name>J.K. Rowling</name>
                                    </author>
                                    <image_url>https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1170803558l/72193._SX98_SY160_.jpg</image_url>
                                    <small_image_url>https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1170803558l/72193._SX50_SY75_.jpg</small_image_url>
                                </best_book>
                            </work>
                        </results>
                    </search>
                </GoodreadsResponse>
                """;
        MockedConstruction<RestTemplate> templateMock = mockConstruction(RestTemplate.class,
                (mock, context) -> {
                    when(mock.exchange(mockedUri, HttpMethod.GET, MockedEntity, String.class)).thenReturn(
                            ResponseEntity.ok(mockedXml)
                    );
                });

        // Calls
        var actualResponse = goodReadsApiDao.searchBooks(query, page, searchBy);

        // Validations
        Assertions.assertThat(actualResponse).usingRecursiveComparison().isEqualTo(expectedGoodReadsResponse);
    }
}
