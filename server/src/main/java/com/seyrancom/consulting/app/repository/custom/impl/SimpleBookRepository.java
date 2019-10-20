package com.seyrancom.consulting.app.repository.custom.impl;

import com.seyrancom.consulting.app.domain.model.Book;
import com.seyrancom.consulting.app.repository.custom.BookRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SimpleBookRepository implements BookRepository {

    @Override
    public Book getByIsbn(String isbn) {
        simulateSlowService();
        return new Book(isbn, "Some book");
    }

    // Don't do this at home
    private void simulateSlowService() {
        try {
            long time = 5000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

}