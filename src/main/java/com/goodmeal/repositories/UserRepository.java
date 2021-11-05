package com.goodmeal.repositories;

import com.goodmeal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User getUserByLogin(String login);

}

/*
@Query(value = "SELECT u FROM User u WHERE u.login = :login")
public User getUserByLogin(@Param("login") String login);
 */
