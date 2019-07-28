package com.lambdaschool.luncher.repository;

import com.lambdaschool.luncher.models.Authors;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AuthorsRepository extends PagingAndSortingRepository<Authors, Long>
{
}
