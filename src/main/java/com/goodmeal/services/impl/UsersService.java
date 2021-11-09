package com.goodmeal.services.impl;

import com.goodmeal.repositoriesImplementations.UsersRepositoryImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("jpaUsersService")
@Repository
public class UsersService {

    @Autowired
    private UsersRepositoryImplementation userRepository;

}
