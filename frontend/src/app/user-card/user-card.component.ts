import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { ConvertUserInfo, UserInfo } from "../model/User";
import { RestapiService } from "../restapi.service";
import { ConvertRecipesRatingsArray, RecipeRatingInfo } from "../model/RecipesRatingsInfo";
import {ThemePalette} from "@angular/material/core";

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

}