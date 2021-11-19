import { Component, OnInit } from '@angular/core';
import { RestapiService } from '../restapi.service';
import { ConvertDishes, Dishes } from '../model/Dishes';
import { ConvertMeals, Meals} from "../model/Meals";
import { ConvertCuisines, Cuisines } from "../model/Cuisines";
import { ConvertUser, User } from "../model/User";

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html'
})

export class HomeComponent implements OnInit {

    dishesList : Dishes;
    mealsList : Meals;
    cuisinesList : Cuisines;
    userdata : User;

    selectedMeal = ""
    selectedDish = "    "
    selectedCuisine = null

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
        console.log(this.service.getFilteredRecipe(this.selectedCuisine));
    }

    ngOnInit() {
        this.service.getUserdata().subscribe( data => {
            this.userdata = ConvertUser.toUser(data.toString());
        });

        this.service.getDishes().subscribe(data => {
            this.dishesList = ConvertDishes.toDishes(data.toString());
            // пример доступа к к имени dish-a
            //console.log(this.dishesList.data[0].attributes.type);
        });

        this.service.getMeals().subscribe( data => {
            this.mealsList = ConvertMeals.toMeals(data.toString());

        });

        this.service.getCuisines().subscribe( data => {
            this.cuisinesList = ConvertCuisines.toCuisines(data.toString());
        });

        this.service.getRecipesFilteredByDish()
    }

    constructor(private service: RestapiService) {}

}