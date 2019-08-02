package com.lambdaschool.luncher.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "school", description =  "List of schools needing lunch donations")
@Entity
@Table(name = "school")
public class School
{
    @ApiModelProperty(name = "id", value = "primary key for school", required = true, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ApiModelProperty(name = "name", value = "School Name", required = true, example = "Boston High School")
    private String name;

    @ApiModelProperty(name = "location", value = "School location", required = true, example = "454 oak st Orlando Fl")
    private String location;

    @ApiModelProperty(name = "currentfunds", value = "Amount of money a school has for student lunches", required = true, example = "10000")
    private double currentfunds;

    @ApiModelProperty(name = "fundgoals", value = "Amount of money a school still needs for student lunches", required = true, example = "10000")
    private double fundgoals;

    @ApiModelProperty(name = "isdonor", value = "Amount of money a school still needs for student lunches", required = true, example = "10000")
    private boolean isdonor = false;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("schools")
    List<Donor> donors = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    List<School> schools = new ArrayList<>();

    public School()
    {
    }

    public School(String name, String location, double fundgoals, boolean isdonor)
    {
        this.name = name;
        this.location = location;
        this.fundgoals = fundgoals;
        this.isdonor = isdonor;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public double getCurrentfunds()
    {
        return currentfunds;
    }

    public void setCurrentfunds(double currentfunds)
    {
        this.currentfunds = currentfunds;
    }

    public double getFundgoals()
    {
        return fundgoals;
    }

    public void setFundgoals(double fundgoals)
    {
        this.fundgoals = fundgoals;
    }


    public List<Donor> getDonors()
    {
        return donors;
    }

    public boolean isIsdonor()
    {
        return isdonor;
    }

    public void setIsdonor(boolean isdonor)
    {
        this.isdonor = isdonor;
    }

    public List<School> getSchools()
    {
        return schools;
    }

    public void setSchools(List<School> schools)
    {
        this.schools = schools;
    }

    public void setDonors(List<Donor> donors)
    {
        this.donors = donors;
    }

    //    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "bookauthors",
//            joinColumns = {@JoinColumn(name = "authorid")},
//            inverseJoinColumns = {@JoinColumn(name = "bookid")})
//    @JsonIgnoreProperties("authors")
//    private List<Book> books = new ArrayList<>();

//    @ManyToMany(mappedBy = "books",
//            cascade = CascadeType.ALL)
//    @JsonIgnoreProperties("books")
//    private List<Authors> authors = new ArrayList<>();


}
