package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.IngredientsToRecipes;
import com.goodmeal.entities.IngredientsToRecipesKey;
import com.goodmeal.repositories.IRepository;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.resource.list.ResourceList;
import io.crnk.data.jpa.JpaEntityRepositoryBase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class IngredientsToRecipesRepositoryImplementation implements CrudRepository<IngredientsToRecipes, IngredientsToRecipesKey> {

    @Override
    public <S extends IngredientsToRecipes> S save(S entity) {
        return null;
    }

    @Override
    public <S extends IngredientsToRecipes> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<IngredientsToRecipes> findById(IngredientsToRecipesKey ingredientsToRecipesKey) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(IngredientsToRecipesKey ingredientsToRecipesKey) {
        return false;
    }

    @Override
    public Iterable<IngredientsToRecipes> findAll() {
        return null;
    }

    @Override
    public Iterable<IngredientsToRecipes> findAllById(Iterable<IngredientsToRecipesKey> ingredientsToRecipesKeys) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(IngredientsToRecipesKey ingredientsToRecipesKey) {

    }

    @Override
    public void delete(IngredientsToRecipes entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends IngredientsToRecipesKey> ingredientsToRecipesKeys) {

    }

    @Override
    public void deleteAll(Iterable<? extends IngredientsToRecipes> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
