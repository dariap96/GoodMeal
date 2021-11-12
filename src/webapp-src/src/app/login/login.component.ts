import { Component, OnInit } from '@angular/core';
import { RestapiService } from '../restapi.service';
import { Router } from '@angular/router';
import { currentUser } from '../model/User';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

    username : string;
    password : string;
    message : any

    constructor(private service: RestapiService,private router:Router) { }

    ngOnInit() {}

    doLogin() {
        let resp = this.service.login(this.username, this.password);
        
        resp.subscribe(data => {
            this.message = data;
            currentUser.login = this.username;
            this.router.navigate(["/home"])
        });
    }

    goToRegistration() {
        this.router.navigate(["/registration"])
    }
}