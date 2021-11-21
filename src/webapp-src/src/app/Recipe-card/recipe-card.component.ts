import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { RestapiService } from "../restapi.service";
import { ConvertRecipes, Recipes } from "../model/Recipes";

@Component({
    selector: 'app-recipe-card',
    templateUrl: './recipe-card.component.html',
    styleUrls: ['./recipe-card.component.css']
})

export class RecipeCardComponent implements OnInit {

    recipeId : number;
    selectedRecipe : Recipes;
    recipeName : string = 'Loading...';
    recipeCookTime : string = 'Loading...';
    recipeImg : string = 'Loading...';

    constructor(private route : ActivatedRoute, private service : RestapiService) { this.recipeId = route.snapshot.params['id']; }

    ngOnInit() {
        this.selectedRecipe.data = [];
        this.service.getRecipeById(this.recipeId).subscribe( data => {
            this.selectedRecipe = ConvertRecipes.toRecipes(data.toString());

            console.log(this.selectedRecipe);
        });
    }
}