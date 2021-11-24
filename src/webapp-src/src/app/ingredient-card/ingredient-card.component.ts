import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { RestapiService } from "../restapi.service";
import { ConvertIngredient, Ingredient } from "../model/Ingredient";

@Component({
    selector: 'app-ingredient-card',
    templateUrl: './ingredient-card.component.html',
    styleUrls: ['ingredient-card.component.css']
})

export class IngredientCardComponent implements OnInit {

    ingredientId : number;
    selectedIngredient : Ingredient;

    ingredientName : string;
    ingredientEnergy : number;
    ingredientFat : number;
    ingredientProtein : number;
    ingredientCarbs : number;
    ingredientFiber : number;
    ingredientImg : null | string;

    constructor(private route : ActivatedRoute, private service : RestapiService) { this.ingredientId = route.snapshot.params['id']; }

    ngOnInit() {
        this.service.getIngredientById(this.ingredientId).subscribe( data => {
            this.selectedIngredient = ConvertIngredient.toIngredient(data.toString());

            this.ingredientName = this.selectedIngredient.data.attributes.name;
            this.ingredientEnergy = this.selectedIngredient.data.attributes.energy;
            this.ingredientFat = this.selectedIngredient.data.attributes.fat;
            this.ingredientProtein = this.selectedIngredient.data.attributes.protein;
            this.ingredientCarbs = this.selectedIngredient.data.attributes.carbs;
            this.ingredientFiber = this.selectedIngredient.data.attributes.fiber;
            this.ingredientImg = this.selectedIngredient.data.attributes.image;
        });
    }

}