package com.dotdash.recruiting.bookreview.dao.api;

import com.dotdash.recruiting.bookreview.entity.exception.InternalErrorRequestException;
import com.dotdash.recruiting.bookreview.entity.model.GoodreadsResponse;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component
public class GoodReadsApiDaoImpl implements IGoodReadsApiDao {
    // Logging Related
    private static final String MESSAGE_CALLING_PREFIX = "Making HTTP request to {}";
    private static final String ERROR_MESSAGE_CALL_FAILED = "Error when calling %s";
    private static final String ERROR_MESSAGE_SERIALIZE_FAILED = "Error trying to serialize response";

    // GoodReads API Related
    private static final String GOOD_READS_API_SCHEME = "https";
    private static final String GOOD_READS_API_URL_PARAM_KEY = "key";
    private static final String GOOD_READS_API_URL_PARAM_QUERY = "q";
    private static final String GOOD_READS_API_URL_PARAM_PAGE = "page";
    private static final String GOOD_READS_API_URL_PARAM_SEARCH_BY = "search[field]";

    @Value("${application.api.goodreads.url}")
    private String GOOD_READS_API_URL_HOST;

    @Value("${application.api.goodreads.endpoint.search}")
    private String GOOD_READS_API_URL_PATH;

    @Value("${application.api.goodreads.credentials.key}")
    private String GOOD_READS_API_KEY;

    @Value("${application.api.goodreads.credentials.secret}")
    private String GOOD_READS_API_SECRET;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public GoodReadsApiDaoImpl() {
    }

    /**
     * Get a {@link GoodreadsResponse} based on a search query
     *
     * @param query search query
     * @param page page number
     * @param searchBy field to search by
     *
     * @return {@link GoodreadsResponse}
     * GoodReadsResponse containing the book info
     */
    @Override
    public GoodreadsResponse searchBooks(String query, Long page, String searchBy) {
        // Build the URI
        var uriBuilder = UriComponentsBuilder.newInstance()
                .scheme(GOOD_READS_API_SCHEME)
                .host(GOOD_READS_API_URL_HOST)
                .path(GOOD_READS_API_URL_PATH)
                .queryParam(GOOD_READS_API_URL_PARAM_QUERY, query)
                .queryParam(GOOD_READS_API_URL_PARAM_PAGE, page)
                .queryParam(GOOD_READS_API_URL_PARAM_SEARCH_BY, searchBy);
        logger.info(MESSAGE_CALLING_PREFIX, uriBuilder.toUriString());

        // Add the key after logging the URI with query params
        uriBuilder.queryParam(GOOD_READS_API_URL_PARAM_KEY, GOOD_READS_API_KEY);
        var uri = uriBuilder.build().toUriString();

        // Negotiate XML headers because it was coming back as html
        var headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_XML));

        // Call the API and get results as a String
        // TODO: Explore MessageConverters to make XML result parsing work
        var entity = new HttpEntity<>(headers);
        var restTemplate = new RestTemplate();
        ResponseEntity<String> goodReadsResponseEntity = null;
        try {
            goodReadsResponseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        } catch (Exception e) {
            throw new InternalErrorRequestException(String.format(ERROR_MESSAGE_CALL_FAILED, uri), e);
        }
        var goodReadsResponseString = goodReadsResponseEntity.getBody();

        // Map the String result to GoodReadsResponse POJO
        // TODO: account for all result fields in the POJO instead of ignoring unknown
        var mapper = new XmlMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        GoodreadsResponse goodReadsObject;
        try {
            goodReadsObject = mapper.readValue(goodReadsResponseString, GoodreadsResponse.class);
        } catch (Exception e) {
            throw new InternalErrorRequestException(ERROR_MESSAGE_SERIALIZE_FAILED, e);
        }

        return goodReadsObject;
    }
}
