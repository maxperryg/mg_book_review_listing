package com.dotdash.recruiting.bookreview.dao.api;

import com.dotdash.recruiting.bookreview.entity.model.GoodreadsResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component
public class GoodReadsApiDaoImpl implements IGoodReadsApiDao {
    // Logging Related
    private static final String MESSAGE_CALLING_PREFIX = "Making HTTP request to {}";

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

    @Override
    public GoodreadsResponse searchBooks(String query, Long page, String searchBy) {
        var uri = UriComponentsBuilder.newInstance()
                .scheme(GOOD_READS_API_SCHEME)
                .host(GOOD_READS_API_URL_HOST)
                .path(GOOD_READS_API_URL_PATH)
                .queryParam(GOOD_READS_API_URL_PARAM_KEY, GOOD_READS_API_KEY)
                .queryParam(GOOD_READS_API_URL_PARAM_QUERY, query)
                .queryParam(GOOD_READS_API_URL_PARAM_PAGE, page)
                .queryParam(GOOD_READS_API_URL_PARAM_SEARCH_BY, searchBy)
                .build()
                .toUriString();
        logger.info(MESSAGE_CALLING_PREFIX, uri);

        var headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_XML));

        var entity = new HttpEntity<>(headers);
        var template = new RestTemplate();
        var goodReadsResponseEntity = template.exchange(uri, HttpMethod.GET, entity, String.class);
        var goodReadsResponseString = goodReadsResponseEntity.getBody();

        var mapper = new XmlMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        GoodreadsResponse goodReadsObject;
        try {
            goodReadsObject = mapper.readValue(goodReadsResponseString, GoodreadsResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return goodReadsObject;
    }
}
