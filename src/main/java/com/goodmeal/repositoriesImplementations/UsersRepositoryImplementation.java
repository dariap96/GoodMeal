package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Role;
import com.goodmeal.entities.Selection;
import com.goodmeal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public interface UsersRepositoryImplementation extends JpaRepository<User,Long> {
    User getUserByLogin(String login);
}
