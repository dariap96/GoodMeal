import { Component, OnInit } from '@angular/core';
import { RestapiService } from '../restapi.service';
import { currentUser, User } from '../model/User';
import {FormControl, FormGroup} from "@angular/forms";
import { ConvertDishes, Dishes } from '../model/Dishes';
import { ConvertMeals, Meals} from "../model/Meals";
import { ConvertCuisines, Cuisines } from "../model/Cuisines";

/*
class Dish {
    id : String;
    type : String;
}
*/

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html'
})


export class HomeComponent implements OnInit {
    /*
    selectedMeal: string = '';
    selectedDish: string ='';
    */

    dishesList : Dishes;
    mealsList : Meals;
    cuisinesList : Cuisines;

    constructor(private service : RestapiService) {}

    ngOnInit() {
        this.service.getDishes().subscribe( data => {
            this.dishesList = ConvertDishes.toDishes(data.toString());

            // пример доступа к id и к имени dish-a
            console.log(this.dishesList.data[0].id);
            console.log(this.dishesList.data[0].attributes.type);
        });

        this.service.getMeals().subscribe( data => {
            this.mealsList = ConvertMeals.toMeals(data.toString());

            // пример доступа к id и к имени meal-a
            console.log(this.mealsList.data[0].id);
            console.log(this.mealsList.data[0].attributes.type);
        });

        this.service.getCuisines().subscribe( data => {
            this.cuisinesList = ConvertCuisines.toCuisines(data.toString());

            // пример доступа к id и к имени cuisine
            console.log(this.cuisinesList.data[0].id);
            console.log(this.cuisinesList.data[0].attributes.type);
        });
    }


    //dishess =this.service.getDishes();


    /*
    dishes = [{
        "id": "1",
        "type": "dish",
        "links": {
            "self": "http://localhost:8080/api/dish/1"
        },
        "attributes": {
            "type": "desserts"
        },
        "relationships": {
            "recipes": {
                "links": {
                    "self": "http://localhost:8080/api/dish/1/relationships/recipes",
                    "related": "http://localhost:8080/api/dish/1/recipes"
                }
            }
        }
    },
        {
            "id": "2",
            "type": "dish",
            "links": {
                "self": "http://localhost:8080/api/dish/2"
            },
            "attributes": {
                "type": "main course"
            },
            "relationships": {
                "recipes": {
                    "links": {
                        "self": "http://localhost:8080/api/dish/2/relationships/recipes",
                        "related": "http://localhost:8080/api/dish/2/recipes"
                    }
                }
            }
        }];


    form: FormGroup;

    constructor(private service: RestapiService) {
        this.form = new FormGroup({
            dish: new FormControl(null)
        })
    }

    get dish(): string {
        return this.form ? this.form.get('dish').value : '';
    }
    


    selectChangeHandler (event: any) {
        this.selectedMeal = event.target.value;
    }
    selectChangeHandler1(event:any){
        this.selectedDish = event.target.value;
    }
    */
}