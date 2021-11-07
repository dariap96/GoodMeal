package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Recipe;
import com.goodmeal.repositories.IRepository;
import io.crnk.core.resource.annotations.JsonApiResource;
import io.crnk.data.jpa.JpaEntityRepositoryBase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@JsonApiResource(type="recipe")
public class RecipesRepositoryImplementation implements CrudRepository<Recipe, Long> {

    @Override
    public <S extends Recipe> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Recipe> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Recipe> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Recipe> findAll() {
        return null;
    }

    @Override
    public Iterable<Recipe> findAllById(Iterable<Long> longs) {
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
    public void delete(Recipe entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Recipe> entities) {
    }

    @Override
    public void deleteAll() {

    }
}
