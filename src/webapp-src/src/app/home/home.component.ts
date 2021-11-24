import { Component, OnInit } from '@angular/core';
import { RestapiService } from '../restapi.service';
import { ConvertDishes, Dishes } from '../model/Dishes';
import { ConvertMeals, Meals} from "../model/Meals";
import { ConvertCuisines, Cuisines } from "../model/Cuisines";
import { ConvertUser, User } from "../model/User";
import { ConvertRecipes, Recipes } from "../model/Recipes";

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {

    dishesList : Dishes;
    mealsList : Meals;
    cuisinesList : Cuisines;
    userdata : User;
    visibleRecipes : Recipes;

    selectedMeal = null;
    selectedDish = null;
    selectedCuisine = null;

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

        this.service.getCuisines().subscribe( data => {
            this.cuisinesList = ConvertCuisines.toCuisines(data.toString());
        });

        this.service.getAllRecipes().subscribe( data => {
            this.visibleRecipes = ConvertRecipes.toRecipes(data.toString());
        });
    }

    selectChangeHandlerMeal(e) {
        this.selectedMeal = e.target.value;
    }
    selectChangeHandlerDish(e){
        this.selectedDish = e.target.value;
    }
    selectChangeHandlerCuisine(e){
        this.selectedCuisine = e.target.value;
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
            if(counter > 0) {
                base = base + '&filter[cuisine.id]=' + this.selectedCuisine;
            } else {
                base = base + '?filter[cuisine.id]=' + this.selectedCuisine;
            }
        }

        console.log(base);

        this.service.getFilteredRecipes(base).subscribe( data => {
            this.visibleRecipes = ConvertRecipes.toRecipes(data.toString());
        });
    }
}