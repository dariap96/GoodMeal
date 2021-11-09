package com.goodmeal.services.impl;

import com.goodmeal.entities.User;
import com.goodmeal.repositoriesImplementations.UsersRepositoryImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Transactional
@Service("jpaUsersService")
@Repository
public class UsersService {

    @Autowired
    private UsersRepositoryImplementation userRepository;


}
