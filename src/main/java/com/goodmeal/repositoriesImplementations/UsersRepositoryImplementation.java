package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UsersRepositoryImplementation extends JpaRepository<User,Long> {

     // void addUser(User user);

      // List<User> listAllUser();


      User getUserByLogin(String login);
}
