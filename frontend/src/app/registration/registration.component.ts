import { Component, OnInit } from '@angular/core';
import { RestapiService } from '../restapi.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { User } from '../model/User'
import { HttpErrorResponse } from '@angular/common/http';


@Component({
    selector: 'app-registration',
    templateUrl: './registration.component.html',
    styleUrls: ['./registration.component.css']
  })

export class RegistrationComponent implements OnInit {
    hide = true;
    constructor(private service : RestapiService, private router : Router) { }

    ngOnInit() {}




    public onAddUser(addForm: NgForm): void {

      document.getElementById('add-user-form').click();

            this.service.addUser(addForm.value).subscribe(
                (response: User[]) => {
                    this.router.navigate(["/login"])
                },
                (error: HttpErrorResponse) => {
                    alert(error.message);
                    addForm.reset();
                }
            );
        }

    
}