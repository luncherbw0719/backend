package com.lambdaschool.luncher.services;

import com.lambdaschool.luncher.models.Book;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService
{
    List<Book> findAll(Pageable pageable);

    Book findById(long id);

    Book update(Book book, long id);

    Book save (Book book);

    void delete(long id);
}
