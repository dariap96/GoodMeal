package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.User;
import com.goodmeal.repositories.UsersRepository;
import io.crnk.data.jpa.JpaEntityRepositoryBase;
import org.springframework.stereotype.Component;

@Component
public class UsersRepositoryImplementation extends JpaEntityRepositoryBase<User,Long> implements UsersRepository {

    public UsersRepositoryImplementation() { super(User.class); }

}
