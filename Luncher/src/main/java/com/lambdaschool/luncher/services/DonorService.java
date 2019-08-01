package com.lambdaschool.luncher.services;

import com.lambdaschool.luncher.models.Donor;
import org.springframework.stereotype.Component;

@Component
public interface DonorService
{
    void addDonor(long id, Donor donor);
    Donor save(Donor donor);
}
