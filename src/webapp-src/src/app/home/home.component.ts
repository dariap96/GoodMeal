import {Component, NgModule, OnInit} from '@angular/core';
import { RestapiService } from '../restapi.service';
import { ConvertDishes, Dishes } from '../model/Dishes';
import { ConvertMeals, Meals} from "../model/Meals";
import { ConvertCuisines, Cuisines } from "../model/Cuisines";
import { ConvertUser, User } from "../model/User";
import { ConvertRecipes, Recipes } from "../model/Recipes";
import {ConvertLabels, Labels} from "../model/Labels";
import {ConvertIngredients, Ingredients} from "../model/Ingredients";
import {ThemePalette} from "@angular/material/core";




@Component({
    selector: 'app-home',
    templateUrl: './home.component.html'

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
    displayedColumns: string[] = ['#', 'Name'];
    loading: boolean= true;





    constructor(private service: RestapiService) {

    }

    ngOnInit() {


        this.service.getUserdata().subscribe( data => {
            this.userdata = ConvertUser.toUser(data.toString());
        });

        this.service.getLabels().subscribe(  data => {
            this.labelsList = ConvertLabels.toLabels(data.toString());
        });

        this.service.getDishes().subscribe(data => {
            this.dishesList = ConvertDishes.toDishes(data.toString());
        });

        this.service.getMeals().subscribe( data => {
            this.mealsList = ConvertMeals.toMeals(data.toString());

        });

        this.service.getIngredients().subscribe(data => {
            this.ingredientsList = ConvertIngredients.toIngredients(data.toString());
        });

        this.service.getCuisines().subscribe( data => {
            this.cuisinesList = ConvertCuisines.toCuisines(data.toString());
        });

        this.service.getFirstHundredRecipes().subscribe( data => {
            this.visibleRecipes = ConvertRecipes.toRecipes(data.toString());
        });
        this.service.getFirstTenIng().subscribe( data => {
            this.visibleIng = ConvertIngredients.toIngredients(data.toString());
        });

    }

    selectIncludeIng(e){
        this.includeIng = e.option.value;
    }
    selectExcludeIng(e){
        this.excludeIng = e.option.value;
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
    selectChangeHandlerIncludeIng(e){
        this.includeIng = e.value;
    }
    selectChangeHandlerExcludeIng(e){
        this.excludeIng = e.value;
    }
    selectChangeHandlerLabel(e){
        this.selectedLabel = e.value;
    }
    selectClickHandlerRecipe(){
        let base = 'http://localhost:4200/api/recipe';
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
            if(counter > 0) { //отдельную функцию под counter
                base = base + '&filter[cuisine.id]=' +  this.selectedCuisine;
            } else {
                base = base + '?filter[cuisine.id]=' + this.selectedCuisine;
            }
        }
        if(this.includeIng != null && this.includeIng != 'default') {
            if(counter > 0) {

                base = base +  '&filter[ingredientsSet.ingredient.id]=' + this.includeIng + '%25%22}}';
            } else {

                base = base + '?filter[ingredientsSet.ingredient.id]=' + this.includeIng + '%25%22}}';
            }
        }
        if(this.excludeIng != null && this.excludeIng != 'default') {
            if(counter > 0) {
                base = base +  '&filter[ingredientsSet.ingredient.id][NEQ]=' + this.includeIng + '%25%22}}';
            } else {
                base = base + '?filter[ingredientsSet.ingredient.id][NEQ]=' + this.includeIng + '%25%22}}';
            }
        }
        if(this.selectedLabel != null && this.selectedLabel != 'default') {
            if(counter > 0) {
                base = base + '&filter[labelsSet.id]=' +  this.selectedLabel;
            } else {
                base = base + '?filter[labelsSet.id]=' + this.selectedLabel;
            }
        }
        if (this.searchBoxInput!= null ) {
            if (counter >0) {
                base = base + '&filter={%20%22LIKE%22%3A{%22name%22%3A%22%25' + this.searchBoxInput + '%25%22}}';
            } else {
                base = base + '?filter={%20%22LIKE%22%3A{%22name%22%3A%22%25' + this.searchBoxInput + '%25%22}}';
            }
        }

        console.log(base);

        this.service.getFilteredRecipes(base).subscribe( data => {
            this.visibleRecipes = ConvertRecipes.toRecipes(data.toString());


        });

    }






}