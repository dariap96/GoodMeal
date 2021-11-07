package com.goodmeal.repositories;

import com.goodmeal.entities.*;
import io.crnk.core.repository.ResourceRepository;
import io.crnk.data.jpa.JpaEntityRepositoryBase;
import io.crnk.data.jpa.JpaRepositoryConfig;
import org.springframework.data.repository.CrudRepository;

public interface IRepository<T, I> extends CrudRepository<T, I> {
}
