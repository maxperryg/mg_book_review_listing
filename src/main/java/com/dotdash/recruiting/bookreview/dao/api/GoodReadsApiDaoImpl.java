package com.dotdash.recruiting.bookreview.dao.api;

import com.dotdash.recruiting.bookreview.entity.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GoodReadsApiDaoImpl implements IGoodReadsApiDao {
    @Value("${application.credentials.api.goodreads.key}")
    private String key;

    @Value("${application.credentials.api.goodreads.secret}")
    private String secret;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Book getBookById(Long bookId) {
        logger.info("Key: {}", key);
        logger.info("Secret: {}", secret);
        return Book.newBuilder().withId(bookId).build();
    }
}
