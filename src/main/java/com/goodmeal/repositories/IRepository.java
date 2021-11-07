package com.goodmeal.repositories;

import io.crnk.data.jpa.JpaEntityRepositoryBase;
import io.crnk.data.jpa.JpaRepositoryConfig;

public abstract class IRepository<T, I> extends JpaEntityRepositoryBase<T,I> {
    public IRepository(Class<T> entityClass) {
        super(entityClass);
    }

    public IRepository(JpaRepositoryConfig<T> config) {
        super(config);
    }
}
