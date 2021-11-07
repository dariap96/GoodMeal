package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Meal;
import com.goodmeal.repositories.IRepository;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.resource.annotations.JsonApiResource;
import io.crnk.core.resource.list.ResourceList;
import io.crnk.data.jpa.JpaEntityRepositoryBase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@JsonApiResource(type = "meal")
public class MealsRepositoryImplementation implements CrudRepository<Meal,Long> {

    @Override
    public <S extends Meal> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Meal> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Meal> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Meal> findAll() {
        return null;
    }

    @Override
    public Iterable<Meal> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Meal entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Meal> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
