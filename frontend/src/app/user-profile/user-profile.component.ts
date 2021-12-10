import { Component, OnInit } from '@angular/core';
import { ConvertUserInfo, ConvertUsers, UserInfo, Users } from "../model/User";
import { RestapiService } from "../restapi.service";
import { Selections, ConvertSelections } from "../model/Selections";
import {ThemePalette} from "@angular/material/core";

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
    background: ThemePalette = undefined;

    constructor(private service : RestapiService ) {}

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

            if(this.adminAccess == true) {
                this.service.getAllUsers().subscribe( data => {
                    this.usersList = ConvertUsers.toUsers(data.toString());
                });
            }

            this.service.getUserSelections(this.activeUser.login).subscribe( data => {
                this.userSelections = ConvertSelections.toSelections(data.toString());
            });
        });
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
                this.service.updatePassword(this.newPassword).subscribe( data => {
                    this.showPasswordUpdateMenu = false;
                });
            }
        }
    }
}