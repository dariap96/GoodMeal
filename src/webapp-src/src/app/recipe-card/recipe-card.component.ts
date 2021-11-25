import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { RestapiService } from "../restapi.service";
import { ConvertRecipe, Recipe } from "../model/Recipe";
import { ConvertIngredients, Ingredients } from "../model/Ingredients";

@Component({
    selector: 'app-recipe-card',
    templateUrl: './recipe-card.component.html',
    styleUrls: ['./recipe-card.component.css']
})

export class RecipeCardComponent implements OnInit {

    recipeId : number;
    selectedRecipe : Recipe;
    relatedIngredients : Ingredients;
    rating : string;
    recipeName : string = 'Loading...';
    recipeCookTime : number = -1;
    recipeImg : string = 'Loading...';
    recipeDescr : string = 'Loading...';

    constructor(private route : ActivatedRoute, private service : RestapiService) { this.recipeId = route.snapshot.params['id']; }

    ngOnInit() {
        this.service.getRecipeById(this.recipeId).subscribe( data => {
            this.selectedRecipe = ConvertRecipe.toRecipe(data.toString());

            this.recipeName = this.selectedRecipe.data.attributes.name;
            this.recipeCookTime = this.selectedRecipe.data.attributes.time;
            this.recipeImg = this.selectedRecipe.data.attributes.image;
            this.recipeDescr = this.selectedRecipe.data.attributes.actionsSequence;
        });

        this.service.getIngredientsByRecipeId(this.recipeId).subscribe( data => {
            this.relatedIngredients = ConvertIngredients.toIngredients(data.toString());
        });

        this.service.getRecipeRatingById(this.recipeId).subscribe(
            data => {
                this.rating = data.toString();
                console.log(data)
            }
        )
    }
}