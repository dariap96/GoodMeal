import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import {ConvertUser, User} from './model/User'
import { map } from 'rxjs/operators';
import {ConvertRecipes} from "./model/Recipes";

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
    providedIn: 'root'
})

export class RestapiService {

    authHeader : HttpHeaders;
    constructor(private http:HttpClient) {}

    login(username: string, password: string){
        this.authHeader = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
        let headers = this.authHeader;
        headers.append('Access-Control-Allow-Origin', 'localhost:8080');

        return this.http.get("http://localhost:8080/", {headers, responseType: 'text' as 'json'})
    }

    // getUser(login: String) {
    //     return this.http.post<User>("http://localhost:8080/get_userdetails", login)
    //         .pipe(map(response => {
    //                 let res = <User> response;
    //                 Object.setPrototypeOf(res, User.prototype);
    //                 return res;
    //             }
    //         ))
    // }

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

    getRecipesFilteredByDish(){
        let headers = this.authHeader;
        // 2 is tmp, just for example
        return this.http.get("http://localhost:4200/api/meal/2/recipes", {headers, responseType: 'text' as 'json'});
    }

    getAllMealRecipe(mealId:number ) {
        return '/meal/${mealId}/recipes'
    }

    getFilteredRecipe(cuisineId:number){
        let headers=this.authHeader;
        console.log("http://localhost:4200/api/recipe?filter[cuisine.id]=" + cuisineId.toString())
        let name = "";
        this.http.get(
            "http://localhost:4200/api/recipe?filter[cuisine.id]=" + cuisineId.toString(),
            {headers, responseType: 'text' as 'json'})
            .subscribe(data => {
                console.log(data);
                name = ConvertRecipes.toRecipes(data.toString()).data[0].attributes.name;
            });
        return name;
    }


}