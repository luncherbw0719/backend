package com.lambdaschool.luncher.repository;

import com.lambdaschool.luncher.models.Quote;
import org.springframework.data.repository.CrudRepository;

public interface QuoteRepository extends CrudRepository<Quote, Long>
{

}
