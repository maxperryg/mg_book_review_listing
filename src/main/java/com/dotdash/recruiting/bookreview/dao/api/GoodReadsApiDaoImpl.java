package com.dotdash.recruiting.bookreview.dao.api;

import com.dotdash.recruiting.bookreview.entity.model.GoodreadsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
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
                .queryParam("q", "harry")
                .build()
                .toUriString();
        logger.info(MESSAGE_CALLING_PREFIX, uri);

        var headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_XML));

        var entity = new HttpEntity<>(headers);
        var template = new RestTemplate();

        template.setMessageConverters(getXmlMessageConverters());

        var goodReadsResponseEntity = template.exchange(uri, HttpMethod.GET, entity, GoodreadsResponse.class);

        return goodReadsResponseEntity.getBody();
    }

    private List<HttpMessageConverter<?>> getXmlMessageConverters() {
        XStreamMarshaller marshaller = new XStreamMarshaller();

        marshaller.setAnnotatedClass(GoodreadsResponse.class);
//        marshaller.setAnnotatedClasses(GoodreadsResponse.class);
        MarshallingHttpMessageConverter marshallingConverter =
                new MarshallingHttpMessageConverter(marshaller);

        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(marshallingConverter);
        return converters;
    }
}
