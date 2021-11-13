import { Component, OnInit } from '@angular/core';
import { RestapiService } from '../restapi.service';
import { currentUser, User } from '../model/User';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html'
})

export class HomeComponent implements OnInit {

    users : any;
    message : any;
    currUser : any;
    constructor(private service : RestapiService) { }

    ngOnInit() {
        this.currUser = this.service.getUser(currentUser.login);
        this.currUser.userCredentials = this.service.getCredentials();
    }

    getUsers(){
        let resp=this.service.getUsers();
        //resp.subscribe(data=>this.users=data);
    }
}