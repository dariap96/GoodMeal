import { Component, OnInit } from '@angular/core';
import { baseUrl } from "../configuration";
import { RestapiService } from '../restapi.service';
import { ConvertDishes, Dishes } from '../model/Dishes';
import { ConvertMeals, Meals} from "../model/Meals";
import { ConvertCuisines, Cuisines } from "../model/Cuisines";
import { ConvertUser, User } from "../model/User";
import { ConvertRecipes, Recipes } from "../model/Recipes";
import { ConvertLabels, Labels } from "../model/Labels";
import { ConvertIngredients, Ingredients } from "../model/Ingredients";
import { ThemePalette } from "@angular/material/core";
import {combineAll} from "rxjs/operators";
import {forkJoin} from "rxjs";

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {

    labelsList : Labels;
    dishesList : Dishes;
    mealsList : Meals;
    cuisinesList : Cuisines;
    userdata : User;
    visibleRecipes : Recipes;

    ingredientsList: Ingredients;
    visibleIng: Ingredients;
    selectedLabel = null;

    selectedMeal = null;
    selectedDish = null;
    selectedCuisine = null;
    searchBoxInput = null;
    includeIng = null;
    excludeIng = null;
    background: ThemePalette = undefined;
    loading: boolean= true;

    constructor(private service: RestapiService) {}

    ngOnInit() {
        this.loading = true;
        forkJoin( this.service.getUserdata(), this.service.getDishes(),this.service.getMeals(), this.service.getLabels(),
            this.service.getIngredients(),  this.service.getCuisines(), this.service.getFirstTenIng(),this.service.getFirstHundredRecipes()
        ).subscribe(([userdata,dishesList,mealsList,labelsList,ingredientsList,cuisinesList,visibleIng,visibleRecipes]) =>
        {
            this.userdata = ConvertUser.toUser(userdata.toString());
            this.dishesList = ConvertDishes.toDishes(dishesList.toString());
            this.mealsList = ConvertMeals.toMeals(mealsList.toString());
            this.labelsList = ConvertLabels.toLabels(labelsList.toString());
            this.ingredientsList = ConvertIngredients.toIngredients(ingredientsList.toString());
            this.visibleIng = ConvertIngredients.toIngredients(visibleIng.toString());
            this.cuisinesList = ConvertCuisines.toCuisines(cuisinesList.toString());
            this.visibleRecipes = ConvertRecipes.toRecipes(visibleRecipes.toString());
            this.loading = false;
        })
        // this.service.getUserdata().subscribe( data => {
        //     this.userdata = ConvertUser.toUser(data.toString());
        // });
        //
        // this.service.getDishes().subscribe(data => {
        //     this.dishesList = ConvertDishes.toDishes(data.toString());
        // });
        //
        // this.service.getMeals().subscribe( data => {
        //     this.mealsList = ConvertMeals.toMeals(data.toString());
        //
        // });
        //
        // this.service.getLabels().subscribe(  data => {
        //     this.labelsList = ConvertLabels.toLabels(data.toString());
        // });
        // this.service.getIngredients().subscribe(data => {
        //     this.ingredientsList = ConvertIngredients.toIngredients(data.toString());
        // });
        // this.service.getFirstTenIng().subscribe( data => {
        //     this.visibleIng = ConvertIngredients.toIngredients(data.toString());
        // });
        //
        // this.service.getCuisines().subscribe( data => {
        //     this.cuisinesList = ConvertCuisines.toCuisines(data.toString());
        // });
        //
        // this.service.getFirstHundredRecipes().subscribe( data => {
        //     this.visibleRecipes = ConvertRecipes.toRecipes(data.toString());
        //     this.loading = false;
        // });
    }

    selectIncludeIng(e) {
        this.includeIng = e.value;
    }

    selectExcludeIng(e) {
        this.excludeIng = e.value;
    }

    selectChangeHandlerSearchBox(e){
        this.searchBoxInput = e.target.value;
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

    selectChangeHandlerLabel(e){
        this.selectedLabel = e.value;
    }

    selectClickHandlerRecipe(){
        let base = baseUrl + '/api/recipe';
        let counter = 0;

        if(this.selectedMeal != null && this.selectedMeal != 'default') {
            base = base + '?filter[meal.id]=' + this.selectedMeal;
            counter++;
        }
        if(this.selectedDish != null && this.selectedDish != 'default') {
            if(counter > 0) {
                base = base + '&filter[dish.id]=' + this.selectedDish;
            } else {
                base = base + '?filter[dish.id]=' + this.selectedDish;
                counter++;
            }
        }
        if(this.selectedCuisine != null && this.selectedCuisine != 'default') {
            if(counter > 0) {
                base = base + '&filter[cuisine.id]=' + this.selectedCuisine;
            } else {
                base = base + '?filter[cuisine.id]=' + this.selectedCuisine;
                counter++;
            }
        }
        if(this.includeIng != null && this.includeIng != 'default') {
            if(counter > 0) {
                base = base + '&filter[ingredientsSet.ingredient.id]=' + this.includeIng;
            } else {
                base = base + '?filter[ingredientsSet.ingredient.id]=' + this.includeIng;
                counter++;
            }
        }
        if(this.excludeIng != null && this.excludeIng != 'default') {
            if (counter > 0) {
                base = base + '&filter[ingredientsSet.ingredient.id][NEQ]=' + this.excludeIng;
            } else {
                base = base + '?filter[ingredientsSet.ingredient.id][NEQ]=' + this.excludeIng;
                counter++;
            }
        }
        if(this.selectedLabel != null && this.selectedLabel != 'default') {
            if (counter > 0) {
                base = base + '&filter[labelsSet.id]=' + this.selectedLabel;
            } else {
                base = base + '?filter[labelsSet.id]=' + this.selectedLabel;
                counter++;
            }
        }
        if (this.searchBoxInput != null && this.searchBoxInput != 'default' && this.searchBoxInput != '') {
            if (counter >0) {
                base = base + '&filter={%20%22LIKE%22%3A{%22name%22%3A%22%25' + this.searchBoxInput + '%25%22}}';
            } else {
                base = base + '?filter={%20%22LIKE%22%3A{%22name%22%3A%22%25' + this.searchBoxInput + '%25%22}}';
                counter++;
            }
        }

        console.log(base);

            this.service.getFilteredRecipes(base).subscribe( data => {
            this.visibleRecipes = ConvertRecipes.toRecipes(data.toString());
        });
    }
}