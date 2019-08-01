package com.lambdaschool.luncher.services;

import com.lambdaschool.luncher.LuncherApplication;
import com.lambdaschool.luncher.LuncherApplicationTests;
import com.lambdaschool.luncher.exceptions.ResourceNotFoundException;
import com.lambdaschool.luncher.models.School;
import com.lambdaschool.luncher.services.SchoolService;
import com.lambdaschool.luncher.services.SchoolServiceImpl;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LuncherApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//@WebMvcTest(value = SchoolServiceImpl.class, useDefaultFilters = false)
public class SchoolServiceImplTest
{
    @Autowired
    SchoolService schoolService;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks((this));
    }

    @Test
    public void AfindById()
    {
        assertEquals("Greeley Hill Elementary", schoolService.findById(14).getName());

    }

    @Test(expected = ResourceNotFoundException.class)
    public void BdeleteNotFound()
    {


        schoolService.delete(1000);
        assertEquals(4, schoolService.findAll().size());
    }

    @Test
    public void CdeleteFound()
    {
        schoolService.delete(14);
        assertEquals(3, schoolService.findAll().size());
    }

    @Test
    public void Dsave()
    {
        School newSchool = new School("John Wick", "Your Death", 0, false);
        assertNotNull(newSchool);

        School addSchool = schoolService.save(newSchool);

        School foundSchool = schoolService.findById(addSchool.getId());
        assertEquals(addSchool.getName(), foundSchool.getName());
    }

}
