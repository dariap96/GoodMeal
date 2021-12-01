package com.goodmeal.controllers;

import com.goodmeal.entities.Recipe;
import com.goodmeal.entities.Selection;
import com.goodmeal.entities.User;
import com.goodmeal.repositoriesImplementations.RecipesRepositoryImplementation;
import com.goodmeal.repositoriesImplementations.SelectionsRepositoryImplementation;
import com.goodmeal.services.impl.RecipesService;
import com.goodmeal.services.impl.SelectionsService;
import com.goodmeal.services.impl.UsersService;
import io.crnk.core.engine.transaction.TransactionRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Set;
import java.util.concurrent.Callable;

@RestController
@CrossOrigin(origins = "*")
public class SelectionsController {

    @Autowired
    SelectionsRepositoryImplementation selectionsRepository;

    @Autowired
    RecipesRepositoryImplementation recipesRepository;

    @Autowired
    UsersService usersService;

    @RequestMapping(value = "/add-to-selection/{selectionId}", method = RequestMethod.POST)
    private boolean addToSelection(HttpServletRequest request, @PathVariable Long selectionId, @RequestBody String recipeId) {
        Principal principal = request.getUserPrincipal();
        String login = principal.getName();

        Selection selection = selectionsRepository.getSelectionById(selectionId);
        String selectionOwner = selection.getUser().getLogin();
        Recipe recipe = recipesRepository.getRecipeById(Long.parseLong(recipeId, 10));

        System.out.println(selectionOwner);
        System.out.println(login);

//        if(login != selectionOwner) {
//            return false;
//        }

        Set<Recipe> recipeSet = selection.getRecipeSet();
        recipeSet.add(recipe);
        selection.setRecipeSet(recipeSet);
        selectionsRepository.save(selection);

        return true;
    }
}