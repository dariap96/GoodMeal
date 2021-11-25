import { Component, OnInit } from '@angular/core';
import { ConvertUser, User } from "../model/User";
import {RestapiService} from "../restapi.service";

@Component({
    selector: 'app-user-profile',
    templateUrl: './user-profile.component.html',
    styleUrls: ['user-profile.component.css']
})

export class UserProfileComponent implements OnInit {

    activeUser : User;

    constructor(private service : RestapiService) {}

    ngOnInit() {
        this.service.getUserdata().subscribe( data => {
            this.activeUser = ConvertUser.toUser(data.toString());
        });
    }
}