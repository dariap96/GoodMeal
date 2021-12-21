import { Component, OnInit } from "@angular/core";
import { ConvertSelections, Selections } from "../model/Selections";
import { ConvertUserInfo, UserInfo } from "../model/User";
import { RestapiService } from "../restapi.service";
import { ThemePalette } from "@angular/material/core";


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
        this.service.getUserInfo().subscribe( data => {
            this.activeUser = data;

            this.service.getUserSelections(this.activeUser.login).subscribe(selectionsData => {
                this.userSelections = selectionsData;
            });
        });
    }

    createNewSelection() {
        this.service.addNewSelection(this.newSelectionName, this.activeUser.login).subscribe( data => {
            this.newSelectionName = '';
            this.refreshData();
        });
    }

    removeSelection(selectionId: string) {
        this.service.removeSelection(parseInt(selectionId, 10)).subscribe( data => {
            this.refreshData();
        });
    }

    refreshData() {
        this.service.getUserSelections(this.activeUser.login).subscribe( data => {
            this.userSelections = data;
        });
    }
}