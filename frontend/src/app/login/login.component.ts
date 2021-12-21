import { Component, OnInit} from '@angular/core';
import { RestapiService } from '../restapi.service';
import { Router } from '@angular/router';
import { FormGroup } from "@angular/forms";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']

})

export class LoginComponent implements OnInit {
    username : string;
    password : string;
    message : any
    hide = true;
    loginFormControl: FormGroup;
    loading = false;
    submitted = false;
    error = '';
    er:boolean;

    constructor(private service: RestapiService,private router:Router) { }

    ngOnInit() {
        this.service.forceLogout().subscribe( data => {});
        this.er=false;
    }

    doLogin() {
        let resp = this.service.login(this.username, this.password);

        resp.subscribe(data => {
            this.router.navigate(["/home"]) },
            error => { this.error = error;
            this.er = true;})
            }

    goToRegistration() {
        this.router.navigate(["/registration"])
    }

    changeUsernameHandler(e) {
        this.username = e.value;
    }

    changePasswordHandler(e) {
        this.password = e.value;
    }
}