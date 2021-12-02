package com.goodmeal.controllers;

import com.goodmeal.entities.Recipe;
import com.goodmeal.entities.Selection;
import com.goodmeal.entities.User;
import com.goodmeal.repositoriesImplementations.RecipesRepositoryImplementation;
import com.goodmeal.repositoriesImplementations.SelectionsRepositoryImplementation;
import com.goodmeal.services.impl.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/edit-selections")
public class SelectionsController {

    @Autowired
    SelectionsRepositoryImplementation selectionsRepository;

    @Autowired
    RecipesRepositoryImplementation recipesRepository;

    @Autowired
    UsersService usersService;

    @RequestMapping(value = "/add-to-selection/{selectionId}", method = RequestMethod.POST)
    private boolean addToSelection(HttpServletRequest request, @PathVariable Long selectionId, @RequestBody String recipeId) {
        String login = getLoginFromPrincipal(request);

        Selection selection = selectionsRepository.getSelectionById(selectionId);
        String selectionOwner = selection.getUser().getLogin();
        Recipe recipe = recipesRepository.getRecipeById(Long.parseLong(recipeId, 10));

        System.out.println(selectionOwner);
        System.out.println(login);

        if (!login.equals(selectionOwner)) {
            return false;
        }

        Set<Recipe> recipeSet = selection.getRecipeSet();
        recipeSet.add(recipe);
        selection.setRecipeSet(recipeSet);
        selectionsRepository.save(selection);

        return true;
    }

    @RequestMapping(value = "/new-selection/{selectionName}", method = RequestMethod.POST)
    private boolean createNewSelection(HttpServletRequest request, @PathVariable String selectionName, @RequestBody String login) {
        String loginFromCookies = getLoginFromPrincipal(request);

        if(!login.equals(loginFromCookies)) {
            return false;
        }

        Selection selection = new Selection(selectionName);
        User user = usersService.getUserByLogin(login);

        selection.setUser(user);
        selection.setRecipeSet(new HashSet<>());
        selection.setIngredientSet(new HashSet<>());
        selectionsRepository.save(selection);

        return true;
    }

    @RequestMapping(value = "/remove-item/{itemId}", method = RequestMethod.POST)
    private boolean removeItemFromSelecion(HttpServletRequest request, @PathVariable String itemId, @RequestBody String selectionId) {
        String login = getLoginFromPrincipal(request);

        Selection selection = selectionsRepository.getSelectionById(Long.parseLong(selectionId, 10));
        String selectionOwner = selection.getUser().getLogin();
        Recipe recipe = recipesRepository.getRecipeById(Long.parseLong(itemId, 10));

        if (!login.equals(selectionOwner)) {
            return false;
        }

        Set<Recipe> recipeSet = selection.getRecipeSet();
        recipeSet.remove(recipe);
        selection.setRecipeSet(recipeSet);
        selectionsRepository.save(selection);

        return true;
    }

    @RequestMapping(value = "/delete/{selectionId}", method = RequestMethod.GET)
    private boolean deleteSelection(HttpServletRequest request, @PathVariable String selectionId) {
        String login = getLoginFromPrincipal(request);

        Selection selection = selectionsRepository.getSelectionById(Long.parseLong(selectionId, 10));
        String selectionOwner = selection.getUser().getLogin();

        if (!login.equals(selectionOwner)) {
            return false;
        }

        selectionsRepository.deleteById(Long.parseLong(selectionId, 10));
        return true;
    }

    private String getLoginFromPrincipal(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String login = principal.getName();

        return login;
    }
}