import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { ConvertUserInfo, UserInfo } from "../model/User";
import { RestapiService } from "../restapi.service";
import { ConvertRecipesRatingsArray, RecipeRatingInfo } from "../model/RecipesRatingsInfo";
import {ThemePalette} from "@angular/material/core";
import {baseUrl} from "../configuration";

@Component({
    selector: 'app-user-card',
    templateUrl: './user-card.component.html',
    styleUrls: ['./user-card.component.css']
})

export class UserCardComponent implements OnInit {

    activeUser : UserInfo;
    adminAccess : boolean = false;
    selectedUser : UserInfo;
    selectedUserId : string;
    selectedUsersRatings : RecipeRatingInfo[];

    showPasswordUpdateMenu : boolean = false;
    showDifferentPasswordsLabel : boolean = false;
    newPassword : string = '';
    newPasswordVerificatiion : string = '';
    background : ThemePalette = undefined;

    constructor(private route : ActivatedRoute, private service : RestapiService) { this.selectedUserId = route.snapshot.params['id']; }

    ngOnInit() {
        this.service.getUserInfo().subscribe( data => {
            this.activeUser = ConvertUserInfo.toUserInfo(data.toString());

            for(let role of this.activeUser.roles) {
                if(role == "ADMIN") {
                    this.adminAccess = true;
                }
            }

            if(this.adminAccess) {
                this.service.getUserByAdmin(this.selectedUserId).subscribe( data => {
                    this.selectedUser = ConvertUserInfo.toUserInfo(data.toString());

                    this.service.getUserReviews(this.selectedUser.login).subscribe(data => {
                        this.selectedUsersRatings = ConvertRecipesRatingsArray.toRecipesRatingsArray(data.toString())
                    });
                });
            }
        });
    }

    removeUserReview(reviewDTO: RecipeRatingInfo) {
        this.service.removeReviewByAdmin(reviewDTO).subscribe( data => {});
    }

    showPassUpdMenu() {
        this.showPasswordUpdateMenu = true;
    }

    hidePassUpdMenu() {
        if (!((this.newPassword || this.newPasswordVerificatiion) == '' || (this.newPassword || this.newPasswordVerificatiion) == null)) {
            if (this.newPassword != this.newPasswordVerificatiion) {
                this.showDifferentPasswordsLabel = true;
                this.newPassword = this.newPasswordVerificatiion = '';
            }
            else {
                this.service.updatePasswordByAdmin(this.selectedUser.login, this.newPassword).subscribe( data => {
                    this.showPasswordUpdateMenu = false;
                });
            }
        }
    }
}