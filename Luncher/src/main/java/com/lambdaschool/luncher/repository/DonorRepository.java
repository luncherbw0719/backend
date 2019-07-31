package com.lambdaschool.luncher.repository;

import com.lambdaschool.luncher.models.Donor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DonorRepository extends PagingAndSortingRepository<Donor, Long>
{
}
