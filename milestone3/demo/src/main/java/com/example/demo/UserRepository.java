package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>
{

    @Query("SELECT c from User c")
    List<User> getAll();

    @Query("select c from User c where c.username=:username")
    User getUserByUsername(@Param("username") String username);

}