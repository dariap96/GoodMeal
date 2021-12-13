import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { RestapiService } from "../restapi.service";
import { Selection, ConvertSelection } from "../model/Selection";
import { Recipes, ConvertRecipes } from "../model/Recipes";
import {ThemePalette} from "@angular/material/core";
import {forkJoin} from "rxjs";

@Component({
    selector: 'app-selection-card',
    templateUrl: './selection-card.component.html',
    styleUrls: ['./selection-card.component.css']
})

export class SelectionCardComponent implements OnInit {
    selectionId : number;
    selectedSelection : Selection;
    recipeSet : Recipes;
    background: ThemePalette = undefined;

    constructor(private route : ActivatedRoute, private service : RestapiService) { this.selectionId = route.snapshot.params['id']; }

    ngOnInit() {
        forkJoin(this.service.getSelectionById(this.selectionId),this.service.getRecipeSetForSelectionById(this.selectionId)).subscribe(
            ([selectionById, recipeSetForSelection]) => {
                this.selectedSelection = ConvertSelection.toSelection(selectionById.toString());
                this.recipeSet = ConvertRecipes.toRecipes(recipeSetForSelection.toString());

            }
        )
        // this.service.getSelectionById(this.selectionId).subscribe( data => {
        //     this.selectedSelection = ConvertSelection.toSelection(data.toString());
        // });
        //
        // this.service.getRecipeSetForSelectionById(this.selectionId).subscribe( data => {
        //     this.recipeSet = ConvertRecipes.toRecipes(data.toString());
        // });
    }

    deleteFromSelection(recipeId: number, selectionId: number) {
        this.service.removeRecipeFromSelection(recipeId, selectionId).subscribe( data => {});
    }
}