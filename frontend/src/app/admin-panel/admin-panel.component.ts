import { Component, OnInit } from "@angular/core";
import { UserInfo, Users } from "../model/User";
import { ThemePalette } from "@angular/material/core";
import { RecipeRatingInfo } from "../model/RecipesRatingsInfo";
import { RestapiService} from "../restapi.service";
import { Router } from "@angular/router";

@Component({
    selector: 'app-admin-panel',
    templateUrl: './admin-panel.component.html',
    styleUrls: ['./admin-panel.component.css']
})

export class AdminPanelComponent implements OnInit {
    activeUser : UserInfo;
    usersList : Users;
    adminAccess : boolean = false;
    background : ThemePalette = undefined;
    usersRatings : RecipeRatingInfo[];


    constructor(private service : RestapiService, private router : Router ) {}

    ngOnInit() {
        this.service.getUserInfo().subscribe( data => {
            this.activeUser = data;

            for(let role of this.activeUser.roles) {
                if(role == "ADMIN") {
                    this.adminAccess = true;
                }
            }

            if(this.adminAccess) {
                this.service.getAllUsers().subscribe( users => {
                    this.usersList = users;
                });
                this.service.getUserReviews(this.activeUser.login).subscribe(reviews => {
                    this.usersRatings = reviews;
                });
            }
        });
    }

    getUserReviews(userLogin : string) {
        return this.service.getUserReviews(userLogin)
    }

    removeReview(rating : RecipeRatingInfo) {
        return this.service.removeReviewByAdmin(rating)
    }

    grantAdminAccess(login: String) {
        return this.service.grantAdminAccess(login).subscribe( data => {});
    }

    openUpdateData() {
        this.router.navigate(["/recipe-update-page"]);
    }
}