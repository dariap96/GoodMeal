package com.goodmeal.services.impl;

import com.goodmeal.entities.Role;
import com.goodmeal.repositoriesImplementations.RolesRepositoryImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("jpaRolesService")
public class RolesService {
    @Autowired
    private RolesRepositoryImplementation rolesRepository;

    public Role getRoleByRole(String role) {
        return rolesRepository.getRoleByRole(role);
    }
}
