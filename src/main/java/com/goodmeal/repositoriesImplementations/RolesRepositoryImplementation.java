package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Role;
import com.goodmeal.repositories.RolesRepository;
import io.crnk.data.jpa.JpaEntityRepositoryBase;
import org.springframework.stereotype.Component;

@Component
public class RolesRepositoryImplementation extends JpaEntityRepositoryBase<Role, Long> implements RolesRepository {

    public RolesRepositoryImplementation() { super(Role.class); }

}
