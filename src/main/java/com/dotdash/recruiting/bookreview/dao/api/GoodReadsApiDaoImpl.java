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
    // Logging
    private static final String MESSAGE_CALLING_PREFIX = "Making HTTP request to {}";

    @Value("${application.api.goodreads.url}")
    private String url;

    @Value("${application.api.goodreads.endpoint.search}")
    private String searchEndpoint;

    @Value("${application.api.goodreads.credentials.key}")
    private String key;

    @Value("${application.api.goodreads.credentials.secret}")
    private String secret;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public GoodreadsResponse searchBooks() {
        var uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(url)
                .path(searchEndpoint)
                .queryParam("key", key)
                .queryParam("q", "moby")
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
