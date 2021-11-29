import { Component, OnInit } from '@angular/core';
import { RestapiService } from '../restapi.service';
import {ActivatedRoute, Router} from '@angular/router';
import { NgForm } from '@angular/forms';
import {ConvertUser, User} from '../model/User'
import { HttpErrorResponse } from '@angular/common/http';
import {RecipeRating} from "../model/RecipeRating";

@Component({
    selector: 'app-review',
    templateUrl: './review.component.html',
    styleUrls: ['./review.component.css']
})

export class ReviewComponent implements OnInit {
    recipeId : number;
    userData : User;

    constructor(private route : ActivatedRoute, private service : RestapiService) {
        this.recipeId = route.snapshot.params['id'];
    }

    ngOnInit() {
        this.service.getUserdata().subscribe( data => {
            this.userData = ConvertUser.toUser(data.toString());
        });
    }

    public addReview(addForm: NgForm): void {
        document.getElementById('add-review').click();
        console.log(addForm.value);
        this.service.addRating(addForm.value).subscribe(
            (response: RecipeRating[]) => {

            }
            ,
            (error: HttpErrorResponse) => {
                console.log(error)
            })
        ;
    }


}