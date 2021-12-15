package com.goodmeal.services.impl;

import com.goodmeal.entities.User;
import com.goodmeal.repositoriesImplementations.UsersRepositoryImplementation;
import com.goodmeal.services.IService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service("jpaUsersService")
public class UsersService implements IService<User> {

    private final UsersRepositoryImplementation userRepository;

    public UsersRepositoryImplementation getUserRepository() {
        return userRepository;
    }

    @Autowired
    public UsersService(UsersRepositoryImplementation userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void create(User user) {
          userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return (userRepository.existsById(id));
    }

    public User getUserByLogin(String login){
        return userRepository.getUserByLogin(login);
    }

    public User getUserById(Long id) { return userRepository.getById(id); }

    public void saveUser(User user) { userRepository.save(user); }
}
