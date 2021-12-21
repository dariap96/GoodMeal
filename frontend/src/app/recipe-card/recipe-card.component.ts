import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { RestapiService } from "../restapi.service";
import { Recipe } from "../model/Recipe";
import { Ingredients } from "../model/Ingredients";
import { UserInfo } from "../model/User";
import { Selections } from "../model/Selections";
import { ThemePalette } from "@angular/material/core";
import { RecipeRatingInfo } from "../model/RecipesRatingsInfo";
import { Labels } from "../model/Labels";

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
    selectedSelection = null;
    relatedIngredients : Ingredients;
    rating : string;
    reviews : RecipeRatingInfo[];
    recipeName : string = 'Loading...';
    recipeCookTime : number = -1;
    recipeImg : string = 'Loading...';
    recipeDescr : string = 'Loading...';
    recipeLables : Labels;
    background: ThemePalette = undefined;

    constructor(private route : ActivatedRoute, private service : RestapiService) { this.recipeId = route.snapshot.params['id']; }

    ngOnInit() {
        // forkJoin(this.service.getRecipeById(this.recipeId), this.service.getIngredientsByRecipeId(this.recipeId),
        //     this.service.getRecipeRatingsById(this.recipeId), this.service.getLabelsByRecipeId(this.recipeId),
        //     this.service.getUserInfo(), this.service.getUserSelections(this.activeUser.login),
        //     this.service.getReviews(this.recipeId)).subscribe(([recipeById, ingByRecipeId, recipeRatings,
        //                                                                           labels, userInfo, userSelections, reviews]) => {
        //     this.selectedRecipe = ConvertRecipe.toRecipe(recipeById.toString());
        //     this.recipeName = this.selectedRecipe.data.attributes.name;
        //     this.recipeCookTime = this.selectedRecipe.data.attributes.time;
        //     this.recipeImg = this.selectedRecipe.data.attributes.image;
        //     this.recipeDescr = this.selectedRecipe.data.attributes.actionsSequence;
        //     this.relatedIngredients = ConvertIngredients.toIngredients(ingByRecipeId.toString());
        //     this.rating = recipeRatings.toString();
        //     this.recipeLables = ConvertLabels.toLabels(labels.toString());
        //     this.activeUser = ConvertUserInfo.toUserInfo(userInfo.toString());
        //     this.userSelections = ConvertSelections.toSelections(userSelections.toString());
        //     this.reviews = ConvertRecipesRatingsArray.toRecipesRatingsArray(reviews.toString());
        // })

        this.service.getRecipeById(this.recipeId).subscribe( data => {
            this.selectedRecipe = data;

            this.recipeName = this.selectedRecipe.data.attributes.name;
            this.recipeCookTime = this.selectedRecipe.data.attributes.time;
            this.recipeImg = this.selectedRecipe.data.attributes.image;
            this.recipeDescr = this.selectedRecipe.data.attributes.actionsSequence;
        });

        this.service.getIngredientsByRecipeId(this.recipeId).subscribe( data => {
            this.relatedIngredients = data;
        });

        this.service.getRecipeRatingsById(this.recipeId).subscribe(data => {
                this.rating = data.toString();
        });

        this.service.getLabelsByRecipeId(this.recipeId).subscribe(data => {
            this.recipeLables = data;
        })

        this.service.getUserInfo().subscribe( data => {
            this.activeUser = data;

            this.service.getUserSelections(this.activeUser.login).subscribe( selectionsData => {
                this.userSelections = selectionsData;
            });
        });

        this.service.getReviews(this.recipeId).subscribe(data => {
            this.reviews = data;
        });
    }

    selectChangeHandlerSelection(e) {
        this.selectedSelection = e.value;
    }

    addToSelection() {
        if (this.selectedSelection != 'Select selection to save' && this.selectedSelection != null) {
            this.service.addRecipeToSelectionById(this.selectedSelection, this.recipeId).subscribe( data => {});
        }
    }

    PrintRating() {
        if (this.rating == '')
            return 'Not rated';
        else
            return 'Rating: ' + this.rating;
    }
}