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
    @ApiModelProperty(name = "schoolid", value = "primary key for school", required = true, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long schoolid;

    @ApiModelProperty(name = "name", value = "School Name", required = true, example = "Boston High School")
    private String name;

    @ApiModelProperty(name = "location", value = "Book Title", required = true, example = "Physics")
    private String location;

    @ApiModelProperty(name = "currentfunds", value = "Amount of money a school has for student lunches", required = true, example = "10000")
    private double currentfunds;

    @ApiModelProperty(name = "neededfunds", value = "Amount of money a school still needs for student lunches", required = true, example = "10000")
    private double neededfunds;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("schools")
    List<Donor> donors = new ArrayList<>();

    public School()
    {
    }

    public School(String name, String location, double currentfunds, double neededfunds)
    {
        this.name = name;
        this.location = location;
        this.currentfunds = currentfunds;
        this.neededfunds = neededfunds;
    }

    public long getSchoolid()
    {
        return schoolid;
    }

    public void setSchoolid(long schoolid)
    {
        this.schoolid = schoolid;
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

    public double getNeededfunds()
    {
        return neededfunds;
    }

    public void setNeededfunds(double neededfunds)
    {
        this.neededfunds = neededfunds;
    }

    public List<Donor> getDonors()
    {
        return donors;
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
