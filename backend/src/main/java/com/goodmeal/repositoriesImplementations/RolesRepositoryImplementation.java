package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface RolesRepositoryImplementation extends JpaRepository<Role,Long> {

    Role getRoleByRole(String role);
}
