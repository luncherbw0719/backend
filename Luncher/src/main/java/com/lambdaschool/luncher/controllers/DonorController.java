package com.lambdaschool.luncher.controllers;

import com.lambdaschool.luncher.models.Donor;
import com.lambdaschool.luncher.models.ErrorDetail;
import com.lambdaschool.luncher.models.School;
import com.lambdaschool.luncher.services.DonorService;
import com.lambdaschool.luncher.services.SchoolService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/donors")
public class DonorController
{
    private static final Logger logger = LoggerFactory.getLogger(SchoolController.class);

    @Autowired
    DonorService donorService;


    @ApiOperation(value = "Adds a donor existing school",
            response = void.class)
    @ApiResponses(value = {@ApiResponse(code = 201,
            message = "School Updated",
            response = void.class), @ApiResponse(code = 500,
            message = "Error updating school",
            response = ErrorDetail.class)})
    @PutMapping(value = "/add/{schoolid}")
    public ResponseEntity<?> addDonorToSchool(
            HttpServletRequest request,
            @RequestBody
                    Donor donor,
            @ApiParam(value = "The school id",
                    required = true,
                    example = "1")
            @PathVariable
                    long schoolid)
    {
    logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

    donorService.addDonor(schoolid, donor);
    return new ResponseEntity<>(HttpStatus.OK);
    }

}
