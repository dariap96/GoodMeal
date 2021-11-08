package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UsersRepositoryImplementation extends JpaRepository<User,Long> {

    User getUserByLogin(String login);
}
