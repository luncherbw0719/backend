package com.lambdaschool.luncher;

import com.lambdaschool.luncher.models.*;
import com.lambdaschool.luncher.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.lambdaschool.luncher.services.SchoolService;

import java.util.ArrayList;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    SchoolService schoolService;

    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        roleService.save(r1);
        roleService.save(r2);
        roleService.save(r3);

        
        // admin, data, user
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        admins.add(new UserRoles(new User(), r2));
        admins.add(new UserRoles(new User(), r3));
        User u1 = new User("admin", "password", admins);
        u1.getQuotes().add(new Quote("A creative man is motivated by the desire to achieve, not by the desire to beat others", u1));
        u1.getQuotes().add(new Quote("The question isn't who is going to let me; it's who is going to stop me.", u1));
        userService.save(u1);

        // data, user
        ArrayList<UserRoles> datas = new ArrayList<>();
        datas.add(new UserRoles(new User(), r3));
        datas.add(new UserRoles(new User(), r2));
        User u2 = new User("cinnamon", "1234567", datas);
        userService.save(u2);

        // user
        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u3 = new User("barnbarn", "ILuvM4th!", users);
        u3.getQuotes().add(new Quote("Live long and prosper", u3));
        u3.getQuotes().add(new Quote("The enemy of my enemy is the enemy I kill last", u3));
        u3.getQuotes().add(new Quote("Beam me up", u3));
        userService.save(u3);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u4 = new User("Bob", "password", users);
        userService.save(u4);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u5 = new User("Jane", "password", users);
        userService.save(u5);

        School s1 = new School("Greeley Hill Elementary", "10326 Fiske Rd, Greeley Hill Rt Coulterville, CA 95311", 34654, 23451);
        schoolService.save(s1);

        School s2 = new School("Woodland Elementary", "3394 Woodland Dr, Mariposa CA 95338", 50321, 12458);
        Donor d1 = new Donor("John Smith", 5000);
        Donor d2 = new Donor("Tyler Cox", 300);
        s2.getDonors().add(d1);
        s2.getDonors().add(d2);
        schoolService.save(s2);

        School s3 = new School("Spring Hill Opportunity", "5171 Silva Rd Mariposa, CA 95338", 19034, 24589);
        Donor d3 = new Donor("Ash Mcdonald", 1500);
        s3.getDonors().add(d3);
        schoolService.save(s3);

        School s4 = new School("Coulterville High", "10326 Fiske Rd, Greeley Hill Rt Coulterville, CA 96311", 33216, 54098);
        Donor d4 = new Donor("Sarah Connor", 900);
        s4.getDonors().add(d4);
        schoolService.save(s4);

    }
}