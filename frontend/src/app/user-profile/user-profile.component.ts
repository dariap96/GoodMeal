import { Component, OnInit } from '@angular/core';
import { ConvertUserInfo, ConvertUsers, UserInfo, Users } from "../model/User";
import { RestapiService } from "../restapi.service";
import { Selections, ConvertSelections } from "../model/Selections";
import { ThemePalette } from "@angular/material/core";
import { Router } from "@angular/router";
import { baseUrl } from "../configuration";
import {ConvertRecipesRatingsArray, RecipeRatingInfo} from "../model/RecipesRatingsInfo";
import { NgForm } from '@angular/forms';

@Component({
    selector: 'app-user-profile',
    templateUrl: './user-profile.component.html',
    styleUrls: ['./user-profile.component.css']
})

export class UserProfileComponent implements OnInit {
    activeUser : UserInfo;
    usersList : Users;
    userSelections : Selections;
    adminAccess : boolean = false;
    showPasswordUpdateMenu : boolean = false;
    showDifferentPasswordsLabel : boolean = false;
    newPassword : string;
    newPasswordVerificatiion : string;
    activeUserBdayDay : number;
    activeUserBdayMonth : number;
    activeUserBdayYear : number;
    background : ThemePalette = undefined;
    usersRatings : RecipeRatingInfo[];


    constructor(private service : RestapiService, private router : Router ) {}

    ngOnInit() {
        this.service.getUserInfo().subscribe( data => {
            this.activeUser = ConvertUserInfo.toUserInfo(data.toString());
            this.activeUserBdayDay = new Date(this.activeUser.bday.toString()).getDate();
            this.activeUserBdayMonth = new Date(this.activeUser.bday.toString()).getMonth() + 1; //because january - 0-month etc
            this.activeUserBdayYear = new Date(this.activeUser.bday.toString()).getFullYear();

            for(let role of this.activeUser.roles) {
                if(role == "ADMIN") {
                    this.adminAccess = true;
                }
            }

            if(this.adminAccess) {
                this.service.getAllUsers().subscribe( data => {
                    this.usersList = ConvertUsers.toUsers(data.toString());
                });
                this.service.getUserReviews(this.activeUser.login).subscribe(data => {
                    this.usersRatings = ConvertRecipesRatingsArray.toRecipesRatingsArray(data.toString())
                });
            }
        });
    }

    showPassUpdMenu() {
        this.showPasswordUpdateMenu = true;
    }

    logout() {
        this.service.authHeader = null;
        window.open(baseUrl + '/logout', '_self');
    }

    changeUserPassword() {}

    hidePassUpdMenu() {
        if (!((this.newPassword || this.newPasswordVerificatiion) == '' || (this.newPassword || this.newPasswordVerificatiion) == null)) {
            if (this.newPassword != this.newPasswordVerificatiion) {
                this.showDifferentPasswordsLabel = true;
                this.newPassword = this.newPasswordVerificatiion = '';
            }
            else {
                this.service.updatePassword(this.newPassword).subscribe( data => {
                    this.showPasswordUpdateMenu = false;
                });
            }
        }
    }

    getUserReviews(userLogin : string) {
        return this.service.getUserReviews(userLogin)
    }

    removeReview(rating : RecipeRatingInfo) {
        return this.service.removeReviewByAdmin(rating)
    }
}