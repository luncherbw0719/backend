package com.lambdaschool.luncher.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "donor", description =  "Donors that contributed to the school lunch program")
@Entity
@Table(name = "donor")

public class Donor
{
    @ApiModelProperty(name = "donorid", value = "Primary key for donor", required = true, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long donorid;

    @ApiModelProperty(name = "name", value = "Donor Name", required = true, example = "John Smith")
    private String name;

    @ApiModelProperty(name = "amountdonated", value = "Amount of money a donor has donated to the program", required = true, example = "10000")
    private double amountdonated;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "schooldonors",
            joinColumns = {@JoinColumn(name = "schoolid")},
            inverseJoinColumns = {@JoinColumn(name = "donorid")})
    @JsonIgnoreProperties("donors")
    private List<School> schools = new ArrayList<>();

    public Donor()
    {
    }

    public Donor(String name, double amountdonated)
    {
        this.name = name;
        this.amountdonated = amountdonated;
    }

    public long getDonorid()
    {
        return donorid;
    }

    public void setDonorid(long donorid)
    {
        this.donorid = donorid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getAmountdonated()
    {
        return amountdonated;
    }

    public void setAmountdonated(double amountdonated)
    {
        this.amountdonated = amountdonated;
    }

    public List<School> getSchools()
    {
        return schools;
    }

    public void setSchools(List<School> schools)
    {
        this.schools = schools;
    }
}
