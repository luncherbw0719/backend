package com.lambdaschool.luncher.repository;

import com.lambdaschool.luncher.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(String username);
}
