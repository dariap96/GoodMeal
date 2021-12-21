import {Injectable} from '@angular/core';
import {User, UserInfo, Users} from './model/User'
import { RecipeRating } from "./model/RecipeRating";
import { RecipeRatingInfo } from "./model/RecipesRatingsInfo";
import { baseUrl,backendUrl } from "./configuration";
import {BehaviorSubject, Observable} from "rxjs";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UpdateData } from "./model/UpdateData";
import {Ingredients} from "./model/Ingredients";
import {Ingredient} from "./model/Ingredient";
import {Dishes} from "./model/Dishes";
import {Meals} from "./model/Meals";
import {Labels} from "./model/Labels";
import {Cuisines} from "./model/Cuisines";
import {Recipes} from "./model/Recipes";
import {Recipe} from "./model/Recipe";
import {Selections} from "./model/Selections";
import {Selection} from "./model/Selection";

@Injectable({
    providedIn: 'root'
})

export class RestapiService {
    public status: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
    authHeader: HttpHeaders;

    constructor(private http: HttpClient) {}

    login(username: string, password: string) {
        this.authHeader = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
        let headers = this.authHeader;
        return this.http.get(backendUrl, {headers, responseType: 'text' as 'json'})
    }

    getUserdata() : Observable<User> {
        return this.http.get<User>(baseUrl + "/userdata");
    }

    addUser(user: User) {
        return this.http.post<User[]>(baseUrl + '/register', user);
    }

    getDishes() : Observable<Dishes>{
        return this.http.get<Dishes>(baseUrl + "/api/dish");
    }

    getMeals() : Observable<Meals> {
        return this.http.get<Meals>(baseUrl + "/api/meal");
    }

    getLabels() : Observable<Labels>{
        return this.http.get<Labels>(baseUrl + "/api/healthDietLabel");
    }

    searchRecipes(term: string)  {
        let headers = this.authHeader;
        return this.http.get(baseUrl + "/api/recipe?filter[name][EQ]=${term}", {headers, responseType: 'text' as 'json'})
    }

    getCuisines() : Observable<Cuisines> {
        return this.http.get<Cuisines>(baseUrl + "/api/cuisine");
    }

    getAllRecipes() : Observable<Recipes> {
        return this.http.get<Recipes>(baseUrl + '/api/recipe');
    }

    getFirstHundredRecipes() : Observable<Recipes> {
        return this.http.get<Recipes>(baseUrl + '/api/recipe?filter[id][LE]=100');
    }

    getFilteredRecipes(req: string) : Observable<Recipes> {
        return this.http.get<Recipes>(req);
    }

    getRecipeById(id: number) : Observable<Recipe> {
        return this.http.get<Recipe>(baseUrl + '/api/recipe/' + id);
    }

    getIngredientsByRecipeId(id: number) : Observable<Ingredients> {
        return this.http.get<Ingredients>(baseUrl + '/api/ingredient?filter[ingredientsSet.recipe.id]=' + id);
    }

    getIngredientById(id: number) : Observable<Ingredient> {
        return this.http.get<Ingredient>(baseUrl + '/api/ingredient/' + id);
    }

    getIngredients() : Observable<Ingredients> {
        return this.http.get<Ingredients>(baseUrl + '/api/ingredient');
    }

    getFirstTenIng() : Observable<Ingredients>{
        return this.http.get<Ingredients>(baseUrl + '/api/ingredient?filter[id][LE]=10');
    }

    // понятия не имею зачем оно у Миши так (пока не трогаю)
    getRecipeRatingsById(id: number) {
        return this.http.get(baseUrl + '/recipe_rating/' + id, {responseType: 'text' as 'json'});
    }

    addRating(rating: RecipeRating) {
        return this.http.post<RecipeRating[]>(
            baseUrl + '/recipe_rating/new', rating);
    }

    getUserInfo() : Observable<UserInfo>{
        return this.http.get<UserInfo>(baseUrl + '/userinfo');
    }

    getAllUsers() : Observable<Users> {
        return this.http.get<Users>(baseUrl + '/api/user');
    }

    updatePassword(newPass: string) {
        let headers = this.authHeader;
        return this.http.put(baseUrl + '/update-password', newPass, {headers, responseType: 'text' as 'json'});
    }

    updatePasswordByAdmin(login: string, newPass: string) {
        return this.http.put(baseUrl + '/update-password-by-admin/' + login, newPass, {responseType: 'text' as 'json'});
    }

    //
    //
    //
    //
    //

    getUserSelections(login: string) : Observable<Selections>  {
        return this.http.get<Selections>(baseUrl + '/api/selection?filter[user.login]=' + login);
    }

    getSelectionById(id: number) : Observable<Selection> {
        return this.http.get<Selection>(baseUrl + '/api/selection/' + id);
    }

    getRecipeSetForSelectionById(id: number) : Observable<Recipes> {
        return this.http.get<Recipes>(baseUrl + '/api/selection/' + id + '/recipeSet');
    }

    addRecipeToSelectionById(selectionId: string, recipeId: number) {
        let headers = this.authHeader;
        return this.http.post(baseUrl + '/edit-selections/add-to-selection/' + selectionId, recipeId, {headers, responseType: 'text' as 'json'});
    }

    addNewSelection(selectionName: string, login: string) {
        let headers = this.authHeader;
        return this.http.post(baseUrl + '/edit-selections/new-selection/' + selectionName, login, {headers, responseType: 'text' as 'json'});
    }

    removeRecipeFromSelection(recipeId: number, selectionId: number) {
        let headers = this.authHeader;
        return this.http.post(baseUrl + '/edit-selections/remove-item/' + recipeId, selectionId, {headers, responseType: 'text' as 'json'});
    }

    removeSelection(selectionId: number) {
        let headers = this.authHeader;
        return this.http.get(baseUrl + '/edit-selections/delete/' + selectionId, {headers, responseType: 'text' as 'json'});
    }

    getReviews(recipeId: number) : Observable<RecipeRatingInfo[]>{
        return this.http.get<RecipeRatingInfo[]>(baseUrl + '/recipe_rating/' + recipeId + '/reviews');
    }

    removeReviewByAdmin(rating : RecipeRatingInfo) {
        return this.http.post(baseUrl + '/recipe_rating/remove-by-admin/', rating);
    }

    getUserReviews(userLogin : string) : Observable<RecipeRatingInfo[]>{
        return this.http.get<RecipeRatingInfo[]>(baseUrl + '/recipe_rating/user-reviews/' + userLogin);
    }

    getLabelsByRecipeId(recipeId:number) : Observable<Labels> {
        return this.http.get<Labels>(baseUrl + '/api/recipe/' + recipeId + '/labelsSet');
    }

    forceLogout() {
        return this.http.get(baseUrl + '/logout', {responseType: 'text' as 'json'});
    }

    getUserByAdmin(userId: string) : Observable<UserInfo> {
        return this.http.get<UserInfo>(baseUrl + '/userinfo/' + userId);
    }

    grantAdminAccess(login: String) {
        return this.http.get(baseUrl + '/grant-admin-access/' + login, {responseType: 'text' as 'json'});
    }

    disableAdminAccess(login: String) {
        return this.http.get(baseUrl + '/disable-admin-access/' + login, {responseType: 'text' as 'json'});
    }

    updateData(request: UpdateData) {
        return this.http.post(baseUrl + '/admin/recipe-update', request);
    }
}