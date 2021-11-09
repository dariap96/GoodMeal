package com.goodmeal.repositories;

import com.goodmeal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User getUserByLogin(String login);

}

