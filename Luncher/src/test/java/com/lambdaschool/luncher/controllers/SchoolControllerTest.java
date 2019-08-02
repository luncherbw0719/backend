//package com.lambdaschool.luncher.controllers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.lambdaschool.luncher.controllers.SchoolController;
//import com.lambdaschool.luncher.models.School;
//import com.lambdaschool.luncher.services.SchoolService;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//
//import java.util.ArrayList;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(value = SchoolController.class, secure = false)
//public class SchoolControllerTest
//{
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private SchoolService schoolService;
//
//    private ArrayList<School> schoolList;
//
//    @Before
//    public void setUp()
//    {
////        schoolList = new ArrayList<>();
////        School s1 = new School("Greeley Hill Elementary", "10326 Fiske Rd, Greeley Hill Rt Coulterville, CA 95311", 64000, false);
////        schoolList.add(s1);
////        School s2 = new School("Woodland Elementary", "3394 Woodland Dr, Mariposa CA 95338", 50000, false);
////        schoolList.add(s2);
////        School s3 = new School("Spring Hill Opportunity", "5171 Silva Rd Mariposa, CA 95338", 23500, false);
////        schoolList.add(s3);
////        School s4 = new School("Coulterville High", "10326 Fiske Rd, Greeley Hill Rt Coulterville, CA 96311", 54309, false);
////        schoolList.add(s4);
//    }
//
//    @After
//    public void tearDown() throws Exception
//    {
//    }
//
//    @Test
//    public void listAllSchools() throws Exception
//    {
////        String apiUrl = "/schools/schools";
////
////        Mockito.when(schoolService.findAll()).thenReturn(schoolList);
////
////        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
////
////        // the following actually performs a real controller call
////        MvcResult r = mockMvc.perform(rb).andReturn(); // this could throw an exception
////        String tr = r.getResponse().getContentAsString();
////
////        ObjectMapper mapper = new ObjectMapper();
////        String er = mapper.writeValueAsString(schoolList);
////
////        assertEquals("Rest API Returns List", er, tr);
//    }
//
//    @Test
//    public void addNewSchool() throws Exception
//    {
//        String apiUrl = "/schools/school/add";
//
//        // build a school
//
////        School s1 = new School("Bobby Brown", "Here", 0, false);
////        s1.setId(1000);
////        ObjectMapper mapper = new ObjectMapper();
////        String schoolString = mapper.writeValueAsString(s1);
////
////        Mockito.when(schoolService.save(any(School.class))).thenReturn(s1);
////
////        RequestBuilder rb = MockMvcRequestBuilders.post(apiUrl)
////                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
////                .content(schoolString);
////        mockMvc.perform(rb).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
//    }
//
//}
