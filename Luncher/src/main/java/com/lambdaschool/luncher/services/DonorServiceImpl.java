package com.lambdaschool.luncher.services;

import com.lambdaschool.luncher.models.Donor;
import com.lambdaschool.luncher.models.School;
import com.lambdaschool.luncher.repository.DonorRepository;
import com.lambdaschool.luncher.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DonorServiceImpl implements DonorService
{
    @Autowired
    DonorRepository donorrepos;
    @Autowired
    SchoolRepository schoolrepos;
    @Autowired
    SchoolService schoolService;

    @Override
    public Donor save(Donor donor)
    {
        Donor newDonor = new Donor();

        if (!donor.getName().isEmpty())
        {
            newDonor.setName(donor.getName());
        }
        if (donor.getAmountdonated() > 0)
        {
            newDonor.setAmountdonated(donor.getAmountdonated());
        }

        return donorrepos.save(newDonor);
    }

    @Override
    public void addDonor(long id, Donor donor)
    {
        Donor newDonor = new Donor();
        double updatedFunds = 0;

        School targetSchool = schoolService.findById(id);

        if (!donor.getName().isEmpty())
        {
            newDonor.setName(donor.getName());
        }
        if (donor.getAmountdonated() > 0)
        {
            newDonor.setAmountdonated(donor.getAmountdonated());
        }
        newDonor.getSchools().add(targetSchool);
        donorrepos.save(newDonor);

        targetSchool.getDonors().add(newDonor);

        for (Donor d : targetSchool.getDonors())
        {
            updatedFunds += d.getAmountdonated();
        }
        targetSchool.setCurrentfunds(updatedFunds);
        schoolrepos.save(targetSchool);
    }
}
