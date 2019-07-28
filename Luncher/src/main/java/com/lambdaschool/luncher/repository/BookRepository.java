package com.lambdaschool.luncher.repository;

import com.lambdaschool.luncher.models.Book;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends PagingAndSortingRepository<Book, Long>
{
}
