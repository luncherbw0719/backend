package com.lambdaschool.luncher.services;

import com.lambdaschool.luncher.models.School;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SchoolService
{
    List<School> findAll(Pageable pageable);

    School findById(long id);

    School update(School book, long id);

    School save (School book);

    void delete(long id);
}