package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Ingredient;
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

@JsonApiResource(type = "ingredient")
@Component


public class IngredientsRepositoryImplementation implements CrudRepository<Ingredient,Long> {


    @Override
    public <S extends Ingredient> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Ingredient> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Ingredient> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return null;
    }

    @Override
    public Iterable<Ingredient> findAllById(Iterable<Long> longs) {
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
    public void delete(Ingredient entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Ingredient> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
