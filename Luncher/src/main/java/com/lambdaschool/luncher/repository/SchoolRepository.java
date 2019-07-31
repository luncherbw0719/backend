package com.lambdaschool.luncher.repository;

import com.lambdaschool.luncher.models.School;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

@Component
public interface SchoolRepository extends PagingAndSortingRepository<School, Long>
{
}
