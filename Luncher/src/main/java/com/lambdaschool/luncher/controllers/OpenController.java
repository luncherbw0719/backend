package com.lambdaschool.luncher.controllers;

import com.lambdaschool.luncher.exceptions.ResourceNotFoundException;
import com.lambdaschool.luncher.models.*;
import com.lambdaschool.luncher.services.RoleService;
import com.lambdaschool.luncher.services.SchoolService;
import com.lambdaschool.luncher.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

@RestController
public class OpenController
{
    private static final Logger logger = LoggerFactory.getLogger(RolesController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;
    @Autowired
    private SchoolService schoolService;

    @PostMapping(value = "/createnewuser",
                 consumes = {"application/json"},
                 produces = {"application/json"})
    public ResponseEntity<?> addNewUser(HttpServletRequest request, @Valid
    @RequestBody
            User newuser) throws URISyntaxException
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        ArrayList<UserRoles> newRoles = new ArrayList<>();
        newRoles.add(new UserRoles(newuser, roleService.findByName("user")));
        newuser.setUserRoles(newRoles);

        newuser = userService.save(newuser);

        // set the location header for the newly created resource - to another controller!
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newRestaurantURI = ServletUriComponentsBuilder.fromUriString(request.getServerName() + ":" + request.getLocalPort() + "/users/user/{userId}").buildAndExpand(newuser.getUserid()).toUri();
        responseHeaders.setLocation(newRestaurantURI);


        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @SuppressWarnings("Duplicates")
    @ApiOperation(value = "Creates a new School and a new user",
            notes = "The newly created school id will be sent in the location header",
            response = User.class)
    @ApiResponses(value = {@ApiResponse(code = 201,
            message = "Sign up completed",
            response = void.class), @ApiResponse(code = 500,
            message = "Error signing up",
            response = ErrorDetail.class)})

    @PostMapping(value = "/signup/{username}/{password}",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> schoolSignup( @Valid
                                           @RequestBody
                                                   School newSchool,
                                           @PathVariable
                                                   String username,
                                           @PathVariable String password) throws ResourceNotFoundException
    {
        HttpHeaders responseHeaders = null;
        ArrayList<UserRoles> admins = new ArrayList<>();
        Role admin = roleService.findByName("admin");
        Role user = roleService.findByName("user");
        admins.add(new UserRoles(new User(), admin));
        admins.add(new UserRoles(new User(), user));

        newSchool = schoolService.save(newSchool);
        User newUser = new User(username, password, admins, newSchool.isIsdonor());
        newUser.setSchoolid(newSchool.getId());
        newUser.setSchoolname(newSchool.getName());
        userService.save(newUser);

        // set the location header for the newly created resource
        responseHeaders = new HttpHeaders();
        URI newSchoolURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newSchool.getId()).toUri();
        responseHeaders.setLocation(newSchoolURI);

        return new ResponseEntity<>(newUser, responseHeaders, HttpStatus.CREATED);
    }


}
