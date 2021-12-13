import { Component, OnInit } from "@angular/core";
import { ConvertSelections, Selections } from "../model/Selections";
import { ConvertUserInfo, UserInfo } from "../model/User";
import { RestapiService } from "../restapi.service";
import {ThemePalette} from "@angular/material/core";
import {forkJoin} from "rxjs";

@Component({
    selector: 'app-selections-page',
    templateUrl: './selections-page.component.html',
    styleUrls: ['./selections-page.component.css']
})

export class SelectionsPageComponent implements OnInit {
    userSelections : Selections;
    activeUser : UserInfo;
    newSelectionName : string;
    background: ThemePalette = undefined;

    constructor(private service : RestapiService) {}

    ngOnInit() {
        forkJoin(this.service.getUserInfo(),this.service.getUserSelections).subscribe(([userInfo,userSelections]) => {
            this.activeUser = ConvertUserInfo.toUserInfo(userInfo.toString());
            this.userSelections = ConvertSelections.toSelections(userSelections.toString());
        })
        // this.service.getUserInfo().subscribe( data => {
        //     this.activeUser = ConvertUserInfo.toUserInfo(data.toString());
        //
        //     this.service.getUserSelections(this.activeUser.login).subscribe( data => {
        //         this.userSelections = ConvertSelections.toSelections(data.toString());
        //     });

    }

    createNewSelection() {
        this.service.addNewSelection(this.newSelectionName, this.activeUser.login).subscribe( data => {
            this.newSelectionName = '';
        });
    }

    removeSelection(selectionId: string) {
        this.service.removeSelection(parseInt(selectionId, 10)).subscribe( data => {});
    }
}