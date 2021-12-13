import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { RestapiService } from "../restapi.service";
import { ConvertRecipe, Recipe } from "../model/Recipe";
import { ConvertIngredients, Ingredients } from "../model/Ingredients";
import { UserInfo, ConvertUserInfo } from "../model/User";
import { Selections, ConvertSelections } from "../model/Selections";
import {ThemePalette} from "@angular/material/core";
import {ConvertRecipesRatingsArray, RecipeRatingInfo} from "../model/RecipesRatingsInfo";
import {ConvertLabels, Labels} from "../model/Labels";
import {forkJoin} from "rxjs";

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
        forkJoin(this.service.getRecipeById(this.recipeId),this.service.getIngredientsByRecipeId(this.recipeId), this.service.getRecipeRatingsById(this.recipeId),this.service.getLabelsByRecipeId(this.recipeId),
            this.service.getUserInfo(), this.service.getUserSelections(this.activeUser.login), this.service.getReviews(this.recipeId)).subscribe(([ recipeById, ingByRecipeId,recipeRatings,
        labels, userInfo, userSelections,reviews]) =>  {
            this.selectedRecipe = ConvertRecipe.toRecipe(recipeById.toString());
            this.recipeName = this.selectedRecipe.data.attributes.name;
            this.recipeCookTime = this.selectedRecipe.data.attributes.time;
            this.recipeImg = this.selectedRecipe.data.attributes.image;
            this.recipeDescr = this.selectedRecipe.data.attributes.actionsSequence;
            this.relatedIngredients = ConvertIngredients.toIngredients( ingByRecipeId.toString());
            this.rating = recipeRatings.toString();
            this.recipeLables = ConvertLabels.toLabels(labels.toString());
            this.activeUser = ConvertUserInfo.toUserInfo(userInfo.toString());
            this.userSelections = ConvertSelections.toSelections(userSelections.toString());
            this.reviews = ConvertRecipesRatingsArray.toRecipesRatingsArray(reviews.toString());
        })
        // this.service.getRecipeById(this.recipeId).subscribe( data => {
        //     this.selectedRecipe = ConvertRecipe.toRecipe(data.toString());
        //
        //     this.recipeName = this.selectedRecipe.data.attributes.name;
        //     this.recipeCookTime = this.selectedRecipe.data.attributes.time;
        //     this.recipeImg = this.selectedRecipe.data.attributes.image;
        //     this.recipeDescr = this.selectedRecipe.data.attributes.actionsSequence;
        // });

        // this.service.getIngredientsByRecipeId(this.recipeId).subscribe( data => {
        //     this.relatedIngredients = ConvertIngredients.toIngredients(data.toString());
        // });
        //
        // this.service.getRecipeRatingsById(this.recipeId).subscribe(data => {
        //         this.rating = data.toString();
        // });
        //
        // this.service.getLabelsByRecipeId(this.recipeId).subscribe(data => {
        //     this.recipeLables = ConvertLabels.toLabels(data.toString());
        // })
        //
        // this.service.getUserInfo().subscribe( data => {
        //     this.activeUser = ConvertUserInfo.toUserInfo(data.toString());
        //
        //     this.service.getUserSelections(this.activeUser.login).subscribe( data => {
        //         this.userSelections = ConvertSelections.toSelections(data.toString());
        //     });
        // });
        //
        // this.service.getReviews(this.recipeId).subscribe(
        //     data => {
        //         this.reviews = ConvertRecipesRatingsArray.toRecipesRatingsArray(data.toString());
        //     }
        // );
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
        if (this.rating == '') {
            return 'Not rated';
        }

        return 'Rating: ' + this.rating;
    }
}