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

        if (school.getNeededfunds() > 0)
        {
            newSchool.setNeededfunds(school.getNeededfunds());
        }else
        {
            newSchool.setNeededfunds(0);
        }

        if (!school.getDonors().isEmpty())
        {
            for (Donor d : school.getDonors())
            {
                newSchool.getDonors().add(d);
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
        }else
        {
            currentSchool.setCurrentfunds(0);
        }

        if (school.getNeededfunds() > 0)
        {
            currentSchool.setNeededfunds(school.getNeededfunds());
        }else
        {
            currentSchool.setNeededfunds(0);
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
