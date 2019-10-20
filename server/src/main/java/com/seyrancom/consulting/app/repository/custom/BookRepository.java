package com.seyrancom.consulting.app.repository.custom;

import com.seyrancom.consulting.app.domain.model.Book;

public interface BookRepository {

    Book getByIsbn(String isbn);

}