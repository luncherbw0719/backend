package com.lambdaschool.luncher.services;

import com.lambdaschool.luncher.models.School;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SchoolService
{
    List<School> findAll(Pageable pageable);

    List<School> findAll();

    School findById(long id);

    School update(School school, long id);

    School save (School school);

    void delete(long id);
}