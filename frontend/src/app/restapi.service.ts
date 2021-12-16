import {Injectable} from '@angular/core';
import { User } from './model/User'
import { RecipeRating } from "./model/RecipeRating";
import {RecipeRatingInfo} from "./model/RecipesRatingsInfo";
// import {dishesService} from './services/dishesService';
// import {cuisinesService} from "./services/cuisinesService";
// import {labelsService} from "./services/labelsService";
// import {mealsService} from "./services/mealsService";
// import {ratingsService} from "./services/ratingsService";
// import {reviewsService} from "./services/reviewsService";
// import {recipesService} from "./services/recipesService";
// import {selectionsService} from "./services/selectionsService";
// import {ingredientsService} from "./services/ingredientsService";
// import {usersService} from "./services/usersService";
import {baseUrl,backendUrl} from "./configuration";
import { BehaviorSubject } from "rxjs";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {UpdateData} from "./model/UpdateData";

@Injectable({
    providedIn: 'root'
})

export class RestapiService {
    public status: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
    authHeader: HttpHeaders;

    constructor(private http: HttpClient) {}

    // dishesService : dishesService;
    // cuisinesService : cuisinesService;
    // labelsService : labelsService;
    // mealsService : mealsService;
    // ratingsService : ratingsService;
    // reviewsService : reviewsService;
    // recipesService : recipesService;
    // selectionsService : selectionsService;
    // ingredientsService : ingredientsService;
    //usersService : usersService;

    login(username: string, password: string) {
        this.authHeader = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
        let headers = this.authHeader;
        return this.http.get(backendUrl, {headers, responseType: 'text' as 'json'})
    }
    // login(username: string, password: string) {
    //     this.usersService.login(username,password);
    // }

    getUserdata() {
        return this.http.get(baseUrl + "/userdata", {/*headers,*/ responseType: 'text' as 'json'});
    }
    // getUserdata() {
    //     this.usersService.getUserdata();
    // }

    addUser(user: User) {
        return this.http.post<User[]>(baseUrl + '/register', user);
    }
    // addUser(user: User) {
    //     this.usersService.addUser(user);
    // }

    getDishes() {
        let headers = this.authHeader;
        return this.http.get(baseUrl + "/api/dish", {headers, responseType: 'text' as 'json'});
    }
    // getDishes() {
    //    this.dishesService.getDishes();
    // }

    getMeals() {
        let headers = this.authHeader;
        return this.http.get(baseUrl + "/api/meal", {headers, responseType: 'text' as 'json'});
    }
    // getMeals() {
    //     this.mealsService.getMeals();
    // }

    getLabels(){
        let headers = this.authHeader;
        return this.http.get(baseUrl + "/api/healthDietLabel", {headers, responseType: 'text' as 'json'});
    }
    // getLabels() {
    //     this.labelsService.getLabels();
    // }

    searchRecipes(term: string)  {
        let headers = this.authHeader;
        return this.http.get(baseUrl + "/api/recipe?filter[name][EQ]=${term}", {headers, responseType: 'text' as 'json'})
    }
    // searchRecipes(term: string) {
    //     this.recipesService.searchRecipes(term);
    // }

    getCuisines() {
        let headers = this.authHeader;
        return this.http.get(baseUrl + "/api/cuisine", {headers, responseType: 'text' as 'json'});
    }
    // getCuisines() {
    //     this.cuisinesService.getCuisines();
    // }

    getAllRecipes() {
        let headers = this.authHeader;
        return this.http.get(baseUrl + '/api/recipe', {headers, responseType: 'text' as 'json'});
    }
    // getAllRecipes() {
    //     this.recipesService.getAllRecipes();
    // }

    getFirstHundredRecipes() {
        let headers = this.authHeader;
        return this.http.get(baseUrl + '/api/recipe?filter[id][LE]=100', {headers, responseType: 'text' as 'json'});
    }
    // getFirstHundredRecipes() {
    //     this.recipesService.getFirstHundredRecipes();
    // }

    getFilteredRecipes(req: string) {
        let headers = this.authHeader;
        return this.http.get(req, {headers, responseType: 'text' as 'json'});
    }
    // getFilteredRecipes(req: string) {
    //     this.recipesService.getFilteredRecipes(req);
    // }

    getRecipeById(id: number) {
        let headers = this.authHeader;
        return this.http.get(baseUrl + '/api/recipe/' + id, {headers, responseType: 'text' as 'json'});
    }
    // getRecipeById(id: number) {
    //     this.recipesService.getRecipeById(id);
    // }

    getIngredientsByRecipeId(id: number) {
        let headers = this.authHeader;
        return this.http.get(baseUrl + '/api/ingredient?filter[ingredientsSet.recipe.id]=' + id, {headers, responseType: 'text' as 'json'});
    }
    // getIngredientsByRecipeId(id: number) {
    //     this.ingredientsService.getIngredientsByRecipeId(id);
    // }

    getIngredientById(id: number) {
        let headers = this.authHeader;
        return this.http.get(baseUrl + '/api/ingredient/' + id, {headers, responseType: 'text' as 'json'});
    }
    // getIngredientById(id: number) {
    //     this.ingredientsService.getIngredientById(id);
    // }

    getIngredientByName(name : string) {
        let headers = this.authHeader;
        return this.http.get(baseUrl + '/api/ingredient?filter[ingredient.name]=' + name, {headers, responseType: 'text' as 'json'});
    }
    // getIngredientByName(name : string) {
    //     this.ingredientsService.getIngredientByName(name);
    // }

    getIngredients() {
        let headers = this.authHeader;
        return this.http.get(baseUrl + '/api/ingredient', {headers, responseType: 'text' as 'json'});
    }
    // getIngredients() {
    //     this.ingredientsService.getIngredients();
    // }

    getFirstTenIng() {
        let headers = this.authHeader;
        return this.http.get(baseUrl + '/api/ingredient?filter[id][LE]=10', {headers, responseType: 'text' as 'json'});
    }
    // getFirstTenIng() {
    //     this.ingredientsService.getFirstTenIng();
    // }

    getRecipeRatingsById(id: number) {
        let headers = this.authHeader;
        return this.http.get(baseUrl + '/recipe_rating/' + id, {headers, responseType: 'text' as 'json'});
    }
    // getRecipeRatingsById(id: number){
    //     this.ratingsService.getRecipeRatingsById(id);
    // }

    addRating(rating: RecipeRating) {
        return this.http.post<RecipeRating[]>(
            baseUrl + '/recipe_rating/new', rating);
    }
    // addRating(rating: RecipeRating) {
    //     this.ratingsService.addRating(rating);
    // }

    getUserInfo() {
        return this.http.get(baseUrl + '/userinfo', {responseType: 'text' as 'json'});
    }
    // getUserInfo() {
    //     this.usersService.getUserInfo();
    // }

    getAllUsers() {
        let headers = this.authHeader;
        return this.http.get(baseUrl + '/api/user', {headers, responseType: 'text' as 'json'});
    }
    // getAllUsers() {
    //     this.usersService.getAllUsers();
    // }

    updatePassword(newPass: string) {
        let headers = this.authHeader;
        return this.http.put(baseUrl + '/update-password', newPass, {headers, responseType: 'text' as 'json'});
    }
    // updatePassword(newPass: string) {
    //     this.usersService.updatePassword(newPass);
    // }

    updatePasswordByAdmin(login: string, newPass: string) {
        return this.http.put(baseUrl + '/update-password-by-admin/' + login, newPass, {responseType: 'text' as 'json'});
    }
    // updatePasswordByAdmin(login: string, newPass: string) {
    //     this.usersService.updatePasswordByAdmin(login,newPass);
    // }

    getUserSelections(login: string) {
        let headers = this.authHeader;
        return this.http.get(baseUrl + '/api/selection?filter[user.login]=' + login, {headers, responseType: 'text' as 'json'});
    }
    // getUserSelections(login: string) {
    //     this.selectionsService.getUserSelections(login)
    // }

    getSelectionById(id: number) {
        let headers = this.authHeader;
        return this.http.get(baseUrl + '/api/selection/' + id, {headers, responseType: 'text' as 'json'});
    }
    // getSelectionById(id: number) {
    //     this.selectionsService.getSelectionById(id);
    // }

    getRecipeSetForSelectionById(id: number) {
        let headers = this.authHeader;
        return this.http.get(baseUrl + '/api/selection/' + id + '/recipeSet', {headers, responseType: 'text' as 'json'});
    }
    // getRecipeSetForSelectionById(id: number) {
    //     this.selectionsService.getRecipeSetForSelectionById(id);
    // }

    addRecipeToSelectionById(selectionId: string, recipeId: number) {
        let headers = this.authHeader;
        return this.http.post(baseUrl + '/edit-selections/add-to-selection/' + selectionId, recipeId, {headers, responseType: 'text' as 'json'});
    }
    // addRecipeToSelectionById(selectionId: string, recipeId: number) {
    //     this.selectionsService.addRecipeToSelectionById(selectionId,recipeId);
    // }

    addNewSelection(selectionName: string, login: string) {
        let headers = this.authHeader;
        return this.http.post(baseUrl + '/edit-selections/new-selection/' + selectionName, login, {headers, responseType: 'text' as 'json'});
    }
    // addNewSelection(selectionName: string, login: string) {
    //     this.selectionsService.addNewSelection(selectionName,login);
    // }

    removeRecipeFromSelection(recipeId: number, selectionId: number) {
        let headers = this.authHeader;
        return this.http.post(baseUrl + '/edit-selections/remove-item/' + recipeId, selectionId, {headers, responseType: 'text' as 'json'});
    }
    // removeRecipeFromSelection(recipeId: number, selectionId: number) {
    //     this.selectionsService.removeRecipeFromSelection(recipeId,selectionId);
    // }

    removeSelection(selectionId: number) {
        let headers = this.authHeader;
        return this.http.get(baseUrl + '/edit-selections/delete/' + selectionId, {headers, responseType: 'text' as 'json'});
    }
    // removeSelection(selectionId: number) {
    //     this.selectionsService.removeSelection(selectionId);
    // }

    getReviews(recipeId: number) {
        let headers = this.authHeader;
        return this.http.get(baseUrl + '/recipe_rating/' + recipeId + '/reviews', {headers, responseType: 'text' as 'json'});
    }
    // getReviews(recipeId: number) {
    //     this.reviewsService.getReviews(recipeId);
    // }

    removeReviewByAdmin(rating : RecipeRatingInfo) {
        return this.http.post(baseUrl + '/recipe_rating/remove-by-admin/', rating);
    }
    // removeReviewByAdmin(rating : RecipeRatingInfo) {
    //     this.reviewsService.removeReviewByAdmin(rating);
    // }

    getUserReviews(userLogin : string) {
        let headers = this.authHeader;
        return this.http.get(baseUrl + '/recipe_rating/user-reviews/' + userLogin, {headers, responseType: 'text' as 'json'});
    }
    // getUserReviews(userLogin : string) {
    //     this.reviewsService.getUserReviews(userLogin);
    // }

    getLabelsByRecipeId(recipeId:number) {
        let headers = this.authHeader;
        return this.http.get(baseUrl + '/api/recipe/' + recipeId + '/labelsSet',  {headers, responseType: 'text' as 'json'});
    }
    // getLabelsByRecipeId(recipeId:number) {
    //     this.labelsService.getLabelsByRecipeId(recipeId);
    // }

    forceLogout() {
        return this.http.get(baseUrl + '/logout', {responseType: 'text' as 'json'});
    }
    // forceLogout() {
    //     this.usersService.forceLogout();
    // }

    getUserByAdmin(userId: string) {
        return this.http.get(baseUrl + '/userinfo/' + userId, {responseType: 'text' as 'json'});
    }
    // getUserByAdmin(userId: string) {
    //     this.usersService.getUserByAdmin(userId);
    // }

    updateData(request: UpdateData) {
        return this.http.post(baseUrl + '/admin/recipe-update', request);
    }
}