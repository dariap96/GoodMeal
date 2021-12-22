import { Component, OnInit } from '@angular/core';
import {async} from '@angular/core/testing';
import { baseUrl } from "../configuration";
import { RestapiService } from '../restapi.service';
import { Dishes } from '../model/Dishes';
import { Meals} from "../model/Meals";
import { Cuisines } from "../model/Cuisines";
import { User } from "../model/User";
import { Recipes } from "../model/Recipes";
import { Labels } from "../model/Labels";
import { Ingredients } from "../model/Ingredients";
import { ThemePalette } from "@angular/material/core";
import { Observable } from "rxjs";
import { PageEvent } from '@angular/material/paginator';
import { FormControl } from "@angular/forms";
import {filter, map, startWith} from "rxjs/operators";

import {Injectable } from '@angular/core'; 7
import { HttpClient } from '@angular/common/http';
import {  of } from 'rxjs';
import { tap,} from 'rxjs/operators';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {
    lowValue: number = 0;
    highValue: number = 10;
    labelsList : Labels;
    dishesList : Dishes;
    mealsList : Meals;
    cuisinesList : Cuisines;
    userdata : User;
    visibleRecipes : Recipes;
    ingredientsList;
    visibleIng: Ingredients;
    selectedLabel = null;
    currentIng = '';

    selectedMeal = null;
    selectedDish = null;
    selectedCuisine = null;
    searchBoxInput = null;
    includeIng = null;
    excludeIng = null;
    background: ThemePalette = undefined;
    loading: boolean= true;
    jokes = [];


    getData() {
        return this.jokes.length ? of(this.jokes)
            : this.httpClient.get<Ingredients>(baseUrl + '/api/ingredient').pipe(
                map((data) => {
                    this.jokes = data.data;
                    return this.jokes;
                })
            )
    }

    constructor(private service: RestapiService, private httpClient: HttpClient) {}

    public getPaginatorData(event: PageEvent): PageEvent {
        this.lowValue = event.pageIndex * event.pageSize;
        this.highValue = this.lowValue + event.pageSize;
        return event;
    }

    ngOnInit() {
        this.loading = true;

        this.service.getUserdata().subscribe( data => {
            this.userdata = data;
        });

        this.service.getDishes().subscribe(data => {
            this.dishesList = data;
        });

        this.service.getMeals().subscribe( data => {
            this.mealsList = data;
        });

        this.service.getLabels().subscribe(  data => {
            this.labelsList = data;
        });

        this.service.getFirstTenIng().subscribe( data => {
            this.visibleIng = data;
        });

        this.service.getCuisines().subscribe( data => {
            this.cuisinesList = data;
        });

        this.service.getFirstTenIng().subscribe( data => {
            this.ingredientsList = data;
        })

        this.service.getFirstHundredRecipes().subscribe( data => {
            this.visibleRecipes = data;
            this.loading = false;
        });
    }

    // ngAfterViewInit() {
    //     this.service.getIngredients().subscribe(data => {
    //         this.ingredientsList = data;
    //     });
    // }

    doFilter() {
        this.ingredientsList = this.getData().pipe(map(jokes => this.filter(jokes)),
            )
    }

    filter(values) {
        return values.filter(ing => ing.attributes.name.toLowerCase().includes(this.currentIng))
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

    selectClickHandlerRecipe() {
        let base = baseUrl + '/api/recipe';
        let counter = 0;

        if(this.selectedMeal != null && this.selectedMeal != 'default' && this.selectedMeal != '') {
            base = base + '?filter[meal.id]=' + this.selectedMeal;
            counter++;
        }

        if(this.selectedDish != null && this.selectedDish != 'default' && this.selectedDish != '') {
            if(counter > 0) {
                base = base + '&filter[dish.id]=' + this.selectedDish;
            } else {
                base = base + '?filter[dish.id]=' + this.selectedDish;
                counter++;
            }
        }

        if(this.selectedCuisine != null && this.selectedCuisine != 'default' && this.selectedCuisine != '') {
            if(counter > 0) {
                base = base + '&filter[cuisine.id]=' + this.selectedCuisine;
            } else {
                base = base + '?filter[cuisine.id]=' + this.selectedCuisine;
                counter++;
            }
        }

        if(this.includeIng != null && this.includeIng != 'default' && this.includeIng != '') {
            if(counter > 0) {
                base = base + '&filter[ingredientsSet.ingredient.id]=' + this.includeIng;
            } else {
                base = base + '?filter[ingredientsSet.ingredient.id]=' + this.includeIng;
                counter++;
            }
        }

        if(this.excludeIng != null && this.excludeIng != 'default' && this.excludeIng != '') {
            if (counter > 0) {
                base = base + '&filter[ingredientsSet.ingredient.id][NEQ]=' + this.excludeIng;
            } else {
                base = base + '?filter[ingredientsSet.ingredient.id][NEQ]=' + this.excludeIng;
                counter++;
            }
        }

        if(this.selectedLabel != null && this.selectedLabel != 'default' && this.selectedLabel != '') {
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
            this.visibleRecipes = data;
        });
    }
}