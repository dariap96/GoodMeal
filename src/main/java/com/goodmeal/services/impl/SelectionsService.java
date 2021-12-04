package com.goodmeal.services.impl;

import com.goodmeal.entities.Ingredient;
import com.goodmeal.entities.Recipe;
import com.goodmeal.entities.Selection;
import com.goodmeal.repositoriesImplementations.IngredientsRepositoryImplementation;
import com.goodmeal.repositoriesImplementations.RecipesRepositoryImplementation;
import com.goodmeal.repositoriesImplementations.SelectionsRepositoryImplementation;
import com.goodmeal.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Transactional
@Service("jpaSelectionsService")
@Repository
public class SelectionsService implements IService<Selection> {
//как достать тип подборки из таблицы подборка_тип?
    @Autowired
    private SelectionsRepositoryImplementation selectionRepository;

    @Autowired
    private RecipesRepositoryImplementation recipesRepositoryImplementation;

    @Autowired
    private IngredientsRepositoryImplementation ingredientsRepositoryImplementation;

    @Override
    public Iterable<Selection> findAll() {
        return selectionRepository.findAll();
    }

    @Override
    public void create(Selection selection) {
        selectionRepository.save(selection);
    }

    @Override
    public Optional<Selection> findById(Long id) {
        return selectionRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return (selectionRepository.existsById(id));
    }

//    public void createSelection(String selectionName){
//        this.selectionRepository.save(new Selection(selectionName));
//    }
//    public void deleteSelection(String selectionName){
//        this.selectionRepository.deleteSelectionBySelectionName(selectionName);
//    }
//
//
//
//    public List<String> listAllSelectionsRecipeNames(String selectionName) {
//        List<String> list = new ArrayList<>();
//        this.selectionRepository.getSelectionBySelectionName(selectionName).getRecipeSet().forEach(it ->list.add(it.getName()));
//        return list;
//    }
//
//    public List<String> listAllSelectionsIngredientNames(String selectionName) {
//        List<String> list = new ArrayList<>();
//        this.selectionRepository.getSelectionBySelectionName(selectionName).getIngredientSet().forEach(it ->list.add(it.getName()));
//        return list;
//    }
//
//    public Set<Recipe> listAllSelectionsRecipes(String selectionName) {
//       return this.selectionRepository.getSelectionBySelectionName(selectionName).getRecipeSet();
//    }
//
//    public Set<Ingredient> listAllSelectionIngredients(String selectionName) {
//        return this.selectionRepository.getSelectionBySelectionName(selectionName).getIngredientSet();
//    }
//
//    public Selection addRecipeIntoSelection(String selectionName, String recipeName) { // оставить в этом сервисе?
//        Recipe recipe = recipesRepositoryImplementation.getRecipeByName(recipeName);
//        Selection selection = this.selectionRepository.getSelectionBySelectionName(selectionName);
//         this.selectionRepository.getSelectionBySelectionName(selectionName).getRecipeSet().add(recipe);
//         return selection;
//    }
//
//    public Selection addIngredientIntoSelection(String selectionName, String ingredientName) { // оставить в этом сервисе?
//        Ingredient ingredient = ingredientsRepositoryImplementation.getIngredientByName(ingredientName);
//        Selection selection = this.selectionRepository.getSelectionBySelectionName(selectionName);
//         this.selectionRepository.getSelectionBySelectionName(selectionName).getIngredientSet().add(ingredient);
//         return selection;
//    }
//
//    public void deleteRecipeFromSelection(String selectionName, String recipeName) { // оставить в этом сервисе?
//        Recipe recipe = recipesRepositoryImplementation.getRecipeByName(recipeName);
//        this.selectionRepository.getSelectionBySelectionName(selectionName).getRecipeSet().remove(recipe);
//    }
//
//    public void deleteIngredientFromSelection(String selectionName, String ingredientName) { // оставить в этом сервисе?
//        Ingredient ingredient =ingredientsRepositoryImplementation.getIngredientByName(ingredientName);
//        this.selectionRepository.getSelectionBySelectionName(selectionName).getIngredientSet().remove(ingredient);
//    }

}
