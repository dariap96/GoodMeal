import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import {ConvertUser, User} from './model/User'
import {BehaviorSubject, Observable, of} from "rxjs";


const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
    providedIn: 'root'
})

export class RestapiService {
    public status: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

    authHeader: HttpHeaders;

    constructor(private http: HttpClient) {}

    login(username: string, password: string) {
        this.authHeader = new HttpHeaders({Authorization: 'Basic ' + btoa(username + ':' + password)});
        let headers = this.authHeader;
        return this.http.get("http://localhost:8080/", {headers, responseType: 'text' as 'json'})
    }

    getUserdata() {
        let headers = this.authHeader
        return this.http.get("http://localhost:4200/userdata", {headers, responseType: 'text' as 'json'});
    }

    addUser(user: User) {
        return this.http.post<User[]>('http://localhost:4200/register', user);
    }

    getDishes() {
        let headers = this.authHeader;
        return this.http.get("http://localhost:4200/api/dish", {headers, responseType: 'text' as 'json'});
    }

    getMeals() {
        let headers = this.authHeader;
        return this.http.get("http://localhost:4200/api/meal", {headers, responseType: 'text' as 'json'});
    }

    getCuisines() {
        let headers = this.authHeader;
        return this.http.get("http://localhost:4200/api/cuisine", {headers, responseType: 'text' as 'json'});
    }

    getLabels(){
        let headers = this.authHeader;
        return this.http.get("http://localhost:4200/api/healthDietLabel", {headers, responseType: 'text' as 'json'});
    }


    getAllRecipes() {
        let headers = this.authHeader;
        return this.http.get('http://localhost:4200/api/recipe', {headers, responseType: 'text' as 'json'});
    }
    searchRecipes(term: string)  {
        let headers = this.authHeader;
        return this.http.get(`http://localhost:4200/api/recipe?filter[name][EQ]=${term}`)
    }



    getFirstHundredRecipes() {
        let headers = this.authHeader;
        return this.http.get('http://localhost:4200/api/recipe?filter[id][LE]=100', {headers, responseType: 'text' as 'json'});
    }


    getFilteredRecipes(req: string) {
        let headers = this.authHeader;
        return this.http.get(req, {headers, responseType: 'text' as 'json'});
    }

    getRecipeById(id: number) {
        let headers = this.authHeader;
        return this.http.get('http://localhost:4200/api/recipe/' + id, {headers, responseType: 'text' as 'json'});
    }


    getIngredientsByRecipeId(id: number) {
        let headers = this.authHeader;
        return this.http.get('http://localhost:4200/api/ingredient?filter[ingredientsSet.recipe.id]=' + id, {headers, responseType: 'text' as 'json'});
    }

    getIngredientById(id: number) {
        let headers = this.authHeader;
        return this.http.get('http://localhost:4200/api/ingredient/' + id, {headers, responseType: 'text' as 'json'});
    }
    getIngredientByName(name : string) {
        let headers = this.authHeader;
        return this.http.get('http://localhost:4200/api/ingredient?filter[ingredient.name]=' + name, {headers, responseType: 'text' as 'json'});
    }
    getIngredients() {
        let headers = this.authHeader;
        return this.http.get('http://localhost:4200/api/ingredient', {headers, responseType: 'text' as 'json'});
    }
    getFirstTenIng() {
        let headers = this.authHeader;
        return this.http.get('http://localhost:4200/api/ingredient?filter[id][LE]=10', {headers, responseType: 'text' as 'json'});
    }

    getUserInfo() {
        let headers = this.authHeader;
        return this.http.get('http://localhost:4200/userinfo', {headers, responseType: 'text' as 'json'});
    }

    getAllUsers() {
        let headers = this.authHeader;
        return this.http.get('http://localhost:4200/api/user', {headers, responseType: 'text' as 'json'});
    }


}