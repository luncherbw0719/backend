package com.lambdaschool.luncher.repository;

import com.lambdaschool.luncher.models.Donor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

@Component
public interface DonorRepository extends PagingAndSortingRepository<Donor, Long>
{
}
