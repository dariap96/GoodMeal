package com.goodmeal.services.impl;

import com.goodmeal.entities.Role;
import com.goodmeal.repositoriesImplementations.RolesRepositoryImplementation;
import com.goodmeal.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service("jpaRolesService")
public class RolesService implements IService<Role> {
    @Autowired
    private RolesRepositoryImplementation rolesRepository;

    public Role getRoleByRole(String role) {
        return rolesRepository.getRoleByRole(role);
    }

    @Override
    public Iterable<Role> findAll() {
        return rolesRepository.findAll();
    }

    @Override
    public void create(Role role) {
        rolesRepository.save(role);
    }

    @Override
    public Optional<Role> findById(Long id) {
        return Optional.of(rolesRepository.getById(id));
    }

    @Override
    public boolean existsById(Long id) {
        return rolesRepository.existsById(id);
    }
}
