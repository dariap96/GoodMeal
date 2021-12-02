import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { RestapiService } from "../restapi.service";
import { Selection, ConvertSelection } from "../model/Selection";
import { Recipes, ConvertRecipes } from "../model/Recipes";

@Component({
    selector: 'app-selection-card',
    templateUrl: './selection-card.component.html',
    styleUrls: ['./selection-card.component.css']
})

export class SelectionCardComponent implements OnInit {
    selectionId : number;
    selectedSelection : Selection;
    recipeSet : Recipes;

    constructor(private route : ActivatedRoute, private service : RestapiService) { this.selectionId = route.snapshot.params['id']; }

    ngOnInit() {
        this.service.getSelectionById(this.selectionId).subscribe( data => {
            this.selectedSelection = ConvertSelection.toSelection(data.toString());
        });

        this.service.getRecipeSetForSelectionById(this.selectionId).subscribe( data => {
            this.recipeSet = ConvertRecipes.toRecipes(data.toString());
        });
    }

    deleteFromSelection(recipeId: number, selectionId: number) {
        this.service.removeRecipeFromSelection(recipeId, selectionId).subscribe( data => {});
    }
}