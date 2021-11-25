import { Component, OnInit } from '@angular/core';
import { ConvertUser, ConvertUserInfo, User, UserInfo } from "../model/User";
import { RestapiService } from "../restapi.service";

@Component({
    selector: 'app-user-profile',
    templateUrl: './user-profile.component.html',
    styleUrls: ['user-profile.component.css']
})

export class UserProfileComponent implements OnInit {

    activeUser : UserInfo;

    constructor(private service : RestapiService) {}

    ngOnInit() {
        this.service.getUserInfo().subscribe( data => {
            this.activeUser = ConvertUserInfo.toUserInfo(data.toString());
        });
    }
}