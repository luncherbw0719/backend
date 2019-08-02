package com.lambdaschool.luncher.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// User is considered the parent entity

@Entity
@Table(name = "users")
public class User extends Auditable
{
    @ApiModelProperty(name = "userid", value = "primary key for User", required = true, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;

    @ApiModelProperty(name = "username", value = "username for login", required = true, example = "hellokitty")
    @Column(nullable = false,
            unique = true)
    private String username;

    @ApiModelProperty(name = "password", value = "password for login", required = true, example = "password")
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ApiModelProperty(name = "isdonor", value = "determines if the user is a donor or school admin", example = "1")
    private boolean isdonor = false;

    @ApiModelProperty(name = "schoolname", value = "Name of the school or donor", example = "John High Point Middle school")
    private String schoolname = "";

    @ApiModelProperty(name = "schoolid", value = "the id of a school admin or donor", example = "1")
    private long schoolid = 0;

    @OneToMany(mappedBy = "user",
               cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    private List<UserRoles> userRoles = new ArrayList<>();

    @OneToMany(mappedBy = "user",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    @JsonIgnoreProperties("user")
    private List<Quote> quotes = new ArrayList<>();

    public User()
    {
    }

    public User(String username, String password, List<UserRoles> userRoles, boolean isdonor)
    {
        setUsername(username);
        setPassword(password);
        setIsdonor(isdonor);
        for (UserRoles ur : userRoles)
        {
            ur.setUser(this);
        }
        this.userRoles = userRoles;
    }

    public long getUserid()
    {
        return userid;
    }

    public void setUserid(long userid)
    {
        this.userid = userid;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    public void setPasswordNoEncrypt(String password)
    {
        this.password = password;
    }

    public boolean isIsdonor()
    {
        return isdonor;
    }

    public void setIsdonor(boolean isdonor)
    {
        this.isdonor = isdonor;
    }


    public String getSchoolname()
    {
        return schoolname;
    }

    public void setSchoolname(String schoolname)
    {
        this.schoolname = schoolname;
    }

    public long getSchoolid()
    {
        return schoolid;
    }

    public void setSchoolid(long schoolid)
    {
        this.schoolid = schoolid;
    }

    public List<UserRoles> getUserRoles()
    {
        return userRoles;
    }

    public void setUserRoles(List<UserRoles> userRoles)
    {
        this.userRoles = userRoles;
    }

    public List<Quote> getQuotes()
    {
        return quotes;
    }

    public void setQuotes(List<Quote> quotes)
    {
        this.quotes = quotes;
    }

    public List<SimpleGrantedAuthority> getAuthority()
    {
        List<SimpleGrantedAuthority> rtnList = new ArrayList<>();

        for (UserRoles r : this.userRoles)
        {
            String myRole = "ROLE_" + r.getRole().getName().toUpperCase();
            rtnList.add(new SimpleGrantedAuthority(myRole));
        }

        return rtnList;
    }
}
