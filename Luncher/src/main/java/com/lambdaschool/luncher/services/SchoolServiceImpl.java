package com.lambdaschool.luncher.services;

import com.lambdaschool.luncher.exceptions.ResourceNotFoundException;
import com.lambdaschool.luncher.models.Donor;
import com.lambdaschool.luncher.models.School;
import com.lambdaschool.luncher.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class SchoolServiceImpl implements SchoolService
{
    @Autowired
    private SchoolRepository schoolrepos;

    @Autowired
    private SchoolService schoolService;

    public SchoolServiceImpl()
    {
        super();
    }

    @Override
    public List<School> findAll(Pageable pageable)
    {
        List<School> list = new ArrayList<>();
        schoolrepos.findAll(pageable).iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public List<School> findAll()
    {
        List<School> list = new ArrayList<>();
        schoolrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public School findById(long id) throws ResourceNotFoundException
    {
        return schoolrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
    }

    @SuppressWarnings("Duplicates")
    @Transactional
    @Override
    public School save(School school)
    {
        School newSchool = new School();
        double updatedFunds = 0;

        if (!school.getName().isEmpty())
        {
            newSchool.setName(school.getName());
        }

        if (!school.getLocation().isEmpty())
        {
            newSchool.setLocation(school.getLocation());
        }

        if (school.getCurrentfunds() > 0)
        {
            newSchool.setCurrentfunds(school.getCurrentfunds());
        }else
        {
            newSchool.setCurrentfunds(0);
        }

        if (school.getFundgoals() > 0)
        {
            newSchool.setFundgoals(school.getFundgoals());
        }

        newSchool.setIsdonor(school.isIsdonor());

        if (!school.getDonors().isEmpty())
        {
            for (Donor d : school.getDonors())
            {
                updatedFunds += d.getAmountdonated();
                newSchool.getDonors().add(d);
            }

            newSchool.setCurrentfunds(updatedFunds);
        }

        if (!school.getSchools().isEmpty())
        {
            for (School s : school.getSchools())
            {
                newSchool.getSchools().add(s);
            }
        }

        return schoolrepos.save(newSchool);
    }

    @SuppressWarnings("Duplicates")
    @Override
    public School update(School school, long id)
    {
        School currentSchool = schoolrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));

        double updatedFunds = 0;
        if (!school.getName().isEmpty())
        {
            currentSchool.setName(school.getName());
        }

        if (!school.getLocation().isEmpty())
        {
            currentSchool.setLocation(school.getLocation());
        }

        if (school.getCurrentfunds() > 0)
        {
            currentSchool.setCurrentfunds(school.getCurrentfunds());
        }

        if (school.getFundgoals() > 0)
        {
            currentSchool.setFundgoals(school.getFundgoals());
        }

        currentSchool.setIsdonor(school.isIsdonor());

        if (!school.getDonors().isEmpty())
        {
            if (!currentSchool.getDonors().isEmpty())
            {
                currentSchool.getDonors().clear();
            }
            for (Donor d : school.getDonors())
            {
                updatedFunds += d.getAmountdonated();
                currentSchool.getDonors().add(d);
            }

            currentSchool.setCurrentfunds(updatedFunds);
        }

        if (!school.getSchools().isEmpty())
        {
            if (!currentSchool.getSchools().isEmpty())
            {
                currentSchool.getSchools().clear();
            }
            for (School s : school.getSchools())
            {
                currentSchool.getSchools().add(s);
            }
        }

        return schoolrepos.save(currentSchool);
    }


    @Transactional
    @Override
    public void delete(long id) throws ResourceNotFoundException
    {
        if (schoolrepos.findById(id).isPresent())
        {
            schoolrepos.deleteById(id);
        } else
        {
            throw new ResourceNotFoundException(Long.toString(id));
        }
    }
}
