package com.goodmeal.controllers;

import com.goodmeal.entities.Recipe;
import com.goodmeal.entities.Selection;
import com.goodmeal.entities.User;
import com.goodmeal.repositoriesImplementations.RecipesRepositoryImplementation;
import com.goodmeal.repositoriesImplementations.SelectionsRepositoryImplementation;
import com.goodmeal.services.impl.SelectionsService;
import com.goodmeal.services.impl.UsersService;
import com.goodmeal.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/edit-selections")
public class SelectionsController {

    @Autowired
    SelectionsService selectionsService;

    @Autowired
    RecipesRepositoryImplementation recipesRepository;

    @Autowired
    UsersService usersService;

    //@Transactional;  метод  в сервисе который сцепляет с рецептами
    @RequestMapping(value = "/add-to-selection/{selectionId}", method = RequestMethod.POST)
    public boolean addToSelection(HttpServletRequest request, @PathVariable Long selectionId, @RequestBody String recipeId) {
        String login = Utils.getLoginFromPrincipal(request);

        Selection selection = selectionsService.getSelectionById(selectionId);
        String selectionOwner = selection.getUser().getLogin();
        Recipe recipe = recipesRepository.getRecipeById(Long.parseLong(recipeId, 10));

        if (!login.equals(selectionOwner)) {
            return false;
        }

        Set<Recipe> recipeSet = selection.getRecipeSet();
        recipeSet.add(recipe);
        selection.setRecipeSet(recipeSet);
        selectionsService.saveSelection(selection);

        return true;
    }


    @RequestMapping(value = "/new-selection/{selectionName}", method = RequestMethod.POST)
    public boolean createNewSelection(HttpServletRequest request, @PathVariable String selectionName) {
        String login = Utils.getLoginFromPrincipal(request);

        Selection selection = new Selection(selectionName);
        User user = usersService.getUserByLogin(login);

        selection.setUser(user);
        selection.setRecipeSet(new HashSet<>());
        selection.setIngredientSet(new HashSet<>());
        selectionsService.saveSelection(selection);

        return true;
    }


    @RequestMapping(value = "/remove-item/{itemId}", method = RequestMethod.POST)
    public boolean removeItemFromSelecion(HttpServletRequest request, @PathVariable String itemId, @RequestBody String selectionId) {
        String login = Utils.getLoginFromPrincipal(request);

        Selection selection = selectionsService.getSelectionById(Long.parseLong(selectionId, 10));
        String selectionOwner = selection.getUser().getLogin();
        Recipe recipe = recipesRepository.getRecipeById(Long.parseLong(itemId, 10));

        if (!login.equals(selectionOwner)) {
            return false;
        }

        Set<Recipe> recipeSet = selection.getRecipeSet();
        recipeSet.remove(recipe);
        selection.setRecipeSet(recipeSet);
        selectionsService.saveSelection(selection);

        return true;
    }


    @RequestMapping(value = "/delete/{selectionId}", method = RequestMethod.GET)
    public boolean deleteSelection(HttpServletRequest request, @PathVariable String selectionId) {
        String login = Utils.getLoginFromPrincipal(request);

        Selection selection = selectionsService.getSelectionById(Long.parseLong(selectionId, 10));
        String selectionOwner = selection.getUser().getLogin();

        if (!login.equals(selectionOwner)) {
            return false;
        }

        selectionsService.deleteSelectionById(Long.parseLong(selectionId, 10));
        return true;
    }
}