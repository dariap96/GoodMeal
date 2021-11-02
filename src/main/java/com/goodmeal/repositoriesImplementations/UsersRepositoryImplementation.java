package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.User;
import com.goodmeal.repositories.IRepository;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.resource.list.ResourceList;
import io.crnk.data.jpa.JpaEntityRepositoryBase;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UsersRepositoryImplementation extends JpaEntityRepositoryBase<User,Long> implements IRepository<User,Long> {

    //private Map<Long, User> users = new HashMap<>();

    public UsersRepositoryImplementation() {
        super(User.class);
    }

    // ------ WTF ------
    //
    // При наличии метода ниже при запросе localhost:8080/user я получаю ровно ничего. При отсутствии – список всех юзеров
    //
    // -----------------

    //@Override
    //public ResourceList<User> findAll(QuerySpec querySpec) { return querySpec.apply(users.values()); }
}
