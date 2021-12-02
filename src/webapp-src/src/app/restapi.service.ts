import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { baseUrl, backendUrl } from './configuration';
import { User } from './model/User'
import { RecipeRating } from "./model/RecipeRating";

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
    providedIn: 'root'
})

export class RestapiService {

    authHeader: HttpHeaders;

    constructor(private http: HttpClient) {}

    login(username: string, password: string) {
        this.authHeader = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
        let headers = this.authHeader;
        return this.http.get(backendUrl, {headers, responseType: 'text' as 'json'})
    }

    getUserdata() {
        let headers = this.authHeader
        return this.http.get(baseUrl + "/userdata", {headers, responseType: 'text' as 'json'});
    }

    addUser(user: User) {
        return this.http.post<User[]>(baseUrl + '/register', user);
    }

    getDishes() {
        let headers = this.authHeader;
        return this.http.get(baseUrl + "/api/dish", {headers, responseType: 'text' as 'json'});
    }

    getMeals() {
        let headers = this.authHeader;
        return this.http.get(baseUrl + "/api/meal", {headers, responseType: 'text' as 'json'});
    }

    getCuisines() {
        let headers = this.authHeader;
        return this.http.get(baseUrl + "/api/cuisine", {headers, responseType: 'text' as 'json'});
    }

    getAllRecipes() {
        let headers = this.authHeader;
        return this.http.get(baseUrl + '/api/recipe', {headers, responseType: 'text' as 'json'});
    }

    getFirstHundredRecipes() {
        let headers = this.authHeader;
        return this.http.get(baseUrl + '/api/recipe?filter[id][LE]=100', {headers, responseType: 'text' as 'json'});
    }

    getFilteredRecipes(req: string) {
        let headers = this.authHeader;
        return this.http.get(req, {headers, responseType: 'text' as 'json'});
    }

    getRecipeById(id: number) {
        let headers = this.authHeader;
        return this.http.get(baseUrl + '/api/recipe/' + id, {headers, responseType: 'text' as 'json'});
    }

    getIngredientsByRecipeId(id: number) {
        let headers = this.authHeader;
        return this.http.get(baseUrl + '/api/ingredient?filter[ingredientsSet.recipe.id]=' + id, {headers, responseType: 'text' as 'json'});
    }

    getIngredientById(id: number) {
        let headers = this.authHeader;
        return this.http.get(baseUrl + '/api/ingredient/' + id, {headers, responseType: 'text' as 'json'});
    }

    getRecipeRatingById(recipeId: number) {
        let headers = this.authHeader;
        return this.http.get(baseUrl + '/recipe_rating/' + recipeId, {headers, responseType: 'text' as 'json'});
    }

    addRating(rating: RecipeRating) {
        return this.http.post<RecipeRating[]>(
            baseUrl + '/recipe_rating/new', rating);
    }

    getReviews(recipeId: number){
        let headers = this.authHeader;
        return this.http.get(baseUrl + '/recipe_rating/' + recipeId + '/reviews', {headers, responseType: 'text' as 'json'});
    }

    getUserInfo() {
        let headers = this.authHeader;
        return this.http.get(baseUrl + '/userinfo', {headers, responseType: 'text' as 'json'});
    }

    getAllUsers() {
        let headers = this.authHeader;
        return this.http.get(baseUrl + '/api/user', {headers, responseType: 'text' as 'json'});
    }

    updatePassword(login: string, newPass: string) {
        let headers = this.authHeader;
        return this.http.put(baseUrl + '/update-password/' + login, newPass, {headers, responseType: 'text' as 'json'});
    }

    getUserSelections(login: string) {
        let headers = this.authHeader;
        return this.http.get(baseUrl + '/api/selection?filter[user.login]=' + login, {headers, responseType: 'text' as 'json'});
    }

    getSelectionById(id: number) {
        let headers = this.authHeader;
        return this.http.get(baseUrl + '/api/selection/' + id, {headers, responseType: 'text' as 'json'});
    }

    getRecipeSetForSelectionById(id: number) {
        let headers = this.authHeader;
        return this.http.get(baseUrl + '/api/selection/' + id + '/recipeSet', {headers, responseType: 'text' as 'json'});
    }

    addRecipeToSelectionById(selectionId: string, recipeId: number) {
        let headers = this.authHeader;
        return this.http.post(baseUrl + '/edit-selections/add-to-selection/' + selectionId, recipeId, {headers, responseType: 'text' as 'json'});
    }

    addNewSelection(selectionName: string, login: string) {
        let headers = this.authHeader;
        return this.http.post(baseUrl + '/edit-selections/new-selection/' + selectionName, login, {headers, responseType: 'text' as 'json'});
    }
}