import { Component, OnInit } from '@angular/core';
import { RestapiService } from '../restapi.service';
import {ActivatedRoute, Router} from '@angular/router';
import { NgForm } from '@angular/forms';
import {ConvertUser, ConvertUserInfo, User, UserInfo} from '../model/User'
import { HttpErrorResponse } from '@angular/common/http';
import {RecipeRating} from "../model/RecipeRating";
import {ConvertDishes, Dishes} from "../model/Dishes";
import {ConvertMeals, Meals} from "../model/Meals";
import {ConvertCuisines, Cuisines} from "../model/Cuisines";
import {forkJoin} from "rxjs";
import {ConvertLabels} from "../model/Labels";
import {ConvertIngredients} from "../model/Ingredients";
import {ConvertRecipes} from "../model/Recipes";
import {UpdateData} from "../model/UpdateData";
import {query} from "@angular/animations";

@Component({
    selector: 'app-recipe-update',
    templateUrl: './recipe-update.component.html',
    styleUrls: ['./recipe-update.component.css']
})

export class RecipeUpdateComponent implements OnInit {
    activeUser : UserInfo;
    adminAccess : boolean = false;

    dishesList : Dishes;
    mealsList : Meals;
    cuisinesList : Cuisines;

    query : string = null;
    selectedMeal : string = null;
    selectedDish : string = null;
    selectedCuisine : string = null;

    constructor(private service : RestapiService, private router : Router) {}

    ngOnInit() {
        forkJoin(this.service.getUserInfo(), this.service.getDishes(),this.service.getMeals(), this.service.getCuisines())
            .subscribe(([userInfo,dishesList,mealsList,cuisinesList]) =>
            {
                this.activeUser = ConvertUserInfo.toUserInfo(userInfo.toString());
                for(let role of this.activeUser.roles) {
                    if(role == "ADMIN") {
                        this.adminAccess = true;
                    }
                }

                if(this.adminAccess) {
                    this.dishesList = ConvertDishes.toDishes(dishesList.toString());
                    this.mealsList = ConvertMeals.toMeals(mealsList.toString());
                    this.cuisinesList = ConvertCuisines.toCuisines(cuisinesList.toString());
                }
            })


    }

    selectChangeHandlerMeal(e) {
        this.selectedMeal = e.value;
    }

    selectChangeHandlerDish(e){
        this.selectedDish = e.value;
    }

    selectChangeHandlerCuisine(e){
        this.selectedCuisine = e.value;
    }

    changeHandlerQuery(e) {
        this.query = e.value;
    }

    update() {
        let request = <UpdateData>{};
        request.query = this.query;
        request.meal = this.selectedMeal;
        request.dish = this.selectedDish;
        request.cuisine = this.selectedCuisine;

        console.log(request)

        this.service.updateData(request).subscribe(data => {
            console.log(data.toString());   
        });
    }

    Close() {
        this.router.navigate(['/user-profile']);
    }
}