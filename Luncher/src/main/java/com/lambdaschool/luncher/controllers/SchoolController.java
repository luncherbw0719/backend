package com.lambdaschool.luncher.controllers;

import com.lambdaschool.luncher.models.*;
import com.lambdaschool.luncher.services.RoleService;
import com.lambdaschool.luncher.services.SchoolService;
import com.lambdaschool.luncher.services.UserService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/schools")
public class SchoolController
{
    private static final Logger logger = LoggerFactory.getLogger(SchoolController.class);

    @Autowired
    SchoolService schoolService;
//    @Autowired
//    UserService userService;
//    @Autowired
//    RoleService roleService;

    @ApiOperation(value = "returns all schools with Paging Ability",
            responseContainer = "List")
    @ApiImplicitParams({@ApiImplicitParam(name = "page",
            dataType = "integer",
            paramType = "query",
            value = "Results page you want to retrieve (0..N)"), @ApiImplicitParam(name = "size",
            dataType = "integer",
            paramType = "query",
            value = "Number of records per page."), @ApiImplicitParam(name = "sort",
            allowMultiple = true,
            dataType = "string",
            paramType = "query",
            value = "Sorting criteria in the format: property(,asc|desc). " + "Default sort order is ascending. " + "Multiple sort criteria are supported.")})

    @GetMapping(value = "/schools", produces = {"application/json"})
    public ResponseEntity<?> listAllSchools(HttpServletRequest request, @PageableDefault(page = 0, size = 500)
            Pageable pageable)
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        List<School> mySchools = schoolService.findAll(pageable);
        return new ResponseEntity<>(mySchools, HttpStatus.OK);
    }

    @ApiOperation(value = "Creates a new School",
            notes = "The newly created school id will be sent in the location header",
            response = void.class)
    @ApiResponses(value = {@ApiResponse(code = 201,
            message = "School Created",
            response = void.class), @ApiResponse(code = 500,
            message = "Error creating school",
            response = ErrorDetail.class)})

    @PostMapping(value = "/school/add",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> addNewSchool( @Valid
                                            @RequestBody
                                                    School newSchool) throws URISyntaxException
    {
        newSchool = schoolService.save(newSchool);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newSchoolURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{schoolid}").buildAndExpand(newSchool.getId()).toUri();
        responseHeaders.setLocation(newSchoolURI);


        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Updates an existing school",
            response = School.class)
    @ApiResponses(value = {@ApiResponse(code = 201,
            message = "School Updated",
            response = void.class), @ApiResponse(code = 500,
            message = "Error updating school",
            response = ErrorDetail.class)})
    @PutMapping(value = "/school/{schoolid}")
    public ResponseEntity<?> updateSchool(
            HttpServletRequest request,
            @RequestBody
                    School updateSchool,
            @ApiParam(value = "The school id",
                    required = true,
                    example = "1")
            @PathVariable
                    long schoolid)
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        schoolService.update(updateSchool, schoolid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Deletes an existing school",
            response = void.class)
    @ApiResponses(value = {@ApiResponse(code = 201,
            message = "School Deleted",
            response = void.class), @ApiResponse(code = 500,
            message = "Error deleting school",
            response = ErrorDetail.class)})
    @DeleteMapping("/delete/{schoolid}")
    public ResponseEntity<?> deleteSchoolById(
            @ApiParam(value = "The school id",
                    required = true,
                    example = "1")
            @PathVariable
                    long schoolid,
            HttpServletRequest request)
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        schoolService.delete(schoolid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // This code will be useful if we end up having to add a donor to a school
    // or add school to donor
//    @ApiOperation(value = "Adds an existing book to an existing author",
//            response = void.class)
//    @ApiResponses(value = {@ApiResponse(code = 201,
//            message = "Book Added",
//            response = void.class), @ApiResponse(code = 500,
//            message = "Error adding book to author",
//            response = ErrorDetail.class)})
//    @PostMapping(value = "/addbook/{authorid}/{bookid}")
//    public ResponseEntity<?> addBookToAuthor(HttpServletRequest request,
//                                             @ApiParam(value = "The author id",
//                                                     required = true,
//                                                     example = "1")
//                                             @PathVariable long authorid,
//                                             @ApiParam(value = "The book id",
//                                                     required = true,
//                                                     example = "1")
//                                             @PathVariable long bookid)
//    {
//        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");
//
//        // Add the book to the author
//        Book currentBook = bookService.findById(bookid);
//        Authors currentAuthor = authorsService.findById(authorid);
//
//        currentAuthor.getBooks().add(currentBook);
//        authorsService.save(currentAuthor);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

}
