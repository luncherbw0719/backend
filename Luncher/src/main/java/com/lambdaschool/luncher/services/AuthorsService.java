package com.lambdaschool.luncher.services;

import com.lambdaschool.luncher.models.Authors;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface AuthorsService
{
    List<Authors> findAll(Pageable pageable);

    Authors findById(long id);

    Authors save (Authors authors);

}
