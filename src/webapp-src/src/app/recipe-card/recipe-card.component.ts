import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { RestapiService } from "../restapi.service";
import { ConvertRecipe, Recipe } from "../model/Recipe";
import { ConvertIngredients, Ingredients } from "../model/Ingredients";
import { UserInfo, ConvertUserInfo } from "../model/User";
import { Selections, ConvertSelections } from "../model/Selections";
import {ConvertRecipesRatingsArray, RecipeRatingInfo} from "../model/RecipesRatingsInfo";

@Component({
    selector: 'app-recipe-card',
    templateUrl: './recipe-card.component.html',
    styleUrls: ['./recipe-card.component.css']
})

export class RecipeCardComponent implements OnInit {

    activeUser : UserInfo;
    userSelections : Selections;
    recipeId : number;
    selectedRecipe : Recipe;
    relatedIngredients : Ingredients;
    showAddToSelection : boolean = false
    rating : string;
    reviews : RecipeRatingInfo[];
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

        this.service.getRecipeRatingById(this.recipeId).subscribe(data => {
                this.rating = data.toString();
        });

        this.service.getUserInfo().subscribe( data => {
            this.activeUser = ConvertUserInfo.toUserInfo(data.toString());

            this.service.getUserSelections(this.activeUser.login).subscribe( data => {
                this.userSelections = ConvertSelections.toSelections(data.toString());
            });
        });

        this.service.getReviews(this.recipeId).subscribe(
            data => {
                this.reviews = ConvertRecipesRatingsArray.toRecipesRatingsArray(data.toString());
            }
        );
    }

    showAddToSelectionMenu() {
        this.showAddToSelection = true;
    }

    addToSelection(id: string) {
        this.service.addRecipeToSelectionById(id, this.recipeId).subscribe( data => {
            this.showAddToSelection = false;
        });
    }

    PrintRating() {
        if (this.rating == '') {
            return 'Not rated';
        }

        return 'Rating: ' + this.rating;
    }
}