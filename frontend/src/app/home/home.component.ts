import { Component, OnInit } from '@angular/core';
import { baseUrl } from "../configuration";
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
        this.service.getUserdata().subscribe( data => {
            this.userdata = ConvertUser.toUser(data.toString());
        });

        this.service.getDishes().subscribe(data => {
            this.dishesList = ConvertDishes.toDishes(data.toString());
        });

        this.service.getMeals().subscribe( data => {
            this.mealsList = ConvertMeals.toMeals(data.toString());

        });

        this.service.getLabels().subscribe(  data => {
            this.labelsList = ConvertLabels.toLabels(data.toString());
        });
        this.service.getIngredients().subscribe(data => {
            this.ingredientsList = ConvertIngredients.toIngredients(data.toString());
        });
        this.service.getFirstTenIng().subscribe( data => {
            this.visibleIng = ConvertIngredients.toIngredients(data.toString());
        });

        this.service.getCuisines().subscribe( data => {
            this.cuisinesList = ConvertCuisines.toCuisines(data.toString());
        });

        this.service.getFirstHundredRecipes().subscribe( data => {
            this.visibleRecipes = ConvertRecipes.toRecipes(data.toString());
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
            if (counter > 0) {
                base = base + '&filter[ingredientsSet.ingredient.id][NEQ]=' + this.includeIng + '%25%22}}';
            } else {
                base = base + '?filter[ingredientsSet.ingredient.id][NEQ]=' + this.includeIng + '%25%22}}';
            }
        }
            if(this.selectedLabel != null && this.selectedLabel != 'default') {
                if (counter > 0) {
                    base = base + '&filter[labelsSet.id]=' + this.selectedLabel;
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
    autocomplete(inp, arr) {
        /*the autocomplete function takes two arguments,
        the text field element and an array of possible autocompleted values:*/
        var currentFocus;
        /*execute a function when someone writes in the text field:*/
        inp.addEventListener("input", function(e) {
            var a, b, i, val = this.value;
            /*close any already open lists of autocompleted values*/
            if (!val) { return false;}
            currentFocus = -1;
            /*create a DIV element that will contain the items (values):*/
            a = document.createElement("DIV");
            a.setAttribute("id", this.id + "search-container-list");
            a.setAttribute("class", "search-container-items");
            /*append the DIV element as a child of the autocomplete container:*/
            this.parentNode.appendChild(a);
            /*for each item in the array...*/
            for (i = 0; i < arr.length; i++) {
                /*check if the item starts with the same letters as the text field value:*/
                if (arr[i].substr(0, val.length).toUpperCase() == val.toUpperCase()) {
                    /*create a DIV element for each matching element:*/
                    b = document.createElement("DIV");
                    /*make the matching letters bold:*/
                    b.innerHTML = "<strong>" + arr[i].substr(0, val.length) + "</strong>";
                    b.innerHTML += arr[i].substr(val.length);
                    /*insert a input field that will hold the current array item's value:*/
                    b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";
                    /*execute a function when someone clicks on the item value (DIV element):*/
                    b.addEventListener("click", function(e) {
                        /*insert the value for the autocomplete text field:*/
                        inp.value = this.getElementsByTagName("input")[0].value;
                        /*close the list of autocompleted values,
                        (or any other open lists of autocompleted values:*/
                    });
                    a.appendChild(b);
                }
            }
        });
        /*execute a function presses a key on the keyboard:*/
        inp.addEventListener("keydown", function(e) {
            var x = document.getElementById(this.id + "search-container-list");
             if (x) x.getElementsByTagName("div");
            if (e.keyCode == 40) {
                /*If the arrow DOWN key is pressed,
                increase the currentFocus variable:*/
                currentFocus++;
                /*and and make the current item more visible:*/
                addActive(x);
            } else if (e.keyCode == 38) { //up
                /*If the arrow UP key is pressed,
                decrease the currentFocus variable:*/
                currentFocus--;
                /*and and make the current item more visible:*/
                addActive(x);
            } else if (e.keyCode == 13) {
                /*If the ENTER key is pressed, prevent the form from being submitted,*/
                e.preventDefault();
                if (currentFocus > -1) {
                    /*and simulate a click on the "active" item:*/
                    if (x) x[currentFocus].click();
                }
            }
        });
        function addActive(x) {
            /*a function to classify an item as "active":*/
            if (!x) return false;
            /*start by removing the "active" class on all items:*/
            removeActive(x);
            if (currentFocus >= x.length) currentFocus = 0;
            if (currentFocus < 0) currentFocus = (x.length - 1);
            /*add class "autocomplete-active":*/
            x[currentFocus].classList.add("search-container-active");
        }
        function removeActive(x) {
            /*a function to remove the "active" class from all autocomplete items:*/
            for (var i = 0; i < x.length; i++) {
                x[i].classList.remove("search-container-active");
            }
        }
        function closeAllLists(elmnt) {
            /*close all autocomplete lists in the document,
            except the one passed as an argument:*/
            var x = document.getElementsByClassName("search-container-items");
            for (var i = 0; i < x.length; i++) {
                if (elmnt != x[i] && elmnt != inp) {
                    x[i].parentNode.removeChild(x[i]);
                }
            }
        }
        /*execute a function when someone clicks in the document:*/
        document.addEventListener("click", function (e) {
            closeAllLists(e.target);
        });
        var recipes = this.visibleRecipes;
    }



}