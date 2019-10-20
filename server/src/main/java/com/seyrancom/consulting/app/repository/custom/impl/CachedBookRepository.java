package com.seyrancom.consulting.app.repository.custom.impl;

import com.seyrancom.consulting.app.domain.model.Book;
import com.seyrancom.consulting.app.repository.custom.BookRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
public class CachedBookRepository implements BookRepository {

    @Override
    @Cacheable("main")
    public Book getByIsbn(String isbn) {
        simulateSlowService();
        return new Book(isbn, "Some book");
    }

    // Don't do this at prod
    private void simulateSlowService() {
        try {
            long time = 1000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

}