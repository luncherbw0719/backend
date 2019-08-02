//package com.lambdaschool.luncher.controllers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.lambdaschool.luncher.models.School;
//import io.restassured.module.mockmvc.RestAssuredMockMvc;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.web.context.WebApplicationContext;
//
//import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
//import static org.hamcrest.number.OrderingComparison.lessThan;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class SchoolControllerIntegrationTest
//{
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    @Before
//    public void initialiseRestAssuredMockMvcWebApplicationContext()
//    {
////        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
//    }
//
//    @Test
//    public void whenMeasuredReponseTime()
//    {
////        given().when().get("/schools/schools").then().time(lessThan(5000L));
//    }
//
//    @Test
//    public void givenPostASchool() throws Exception
//    {
////        School s1 = new School("Konos", "Klingon Home World", 0, false);
////        s1.setId(5000);
////
////        ObjectMapper mapper = new ObjectMapper();
////        String stringR3 = mapper.writeValueAsString(s1);
////
////        given().contentType("application/json").body(stringR3).when().post("/schools/school/add").then().statusCode(201);
//    }
//
//}
