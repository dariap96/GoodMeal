import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { RegistrationComponent } from './registration/registration.component'
import { RestapiService } from './restapi.service';
import { RecipeCardComponent } from "./recipe-card/recipe-card.component";
import { IngredientCardComponent } from "./ingredient-card/ingredient-card.component";
import { SelectionsPageComponent } from "./selections-page/selections-page.component";
import { UserProfileComponent } from "./user-profile/user-profile.component";
import { ReviewComponent } from "./review/review.component";
import { SelectionCardComponent } from "./selection-card/selection-card.component";
import {NgSelectModule} from "@ng-select/ng-select";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSliderModule } from '@angular/material/slider';
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {MatInputModule} from "@angular/material/input";
import {MatNativeDateModule, MatRippleModule} from "@angular/material/core";
import {MatButtonModule} from "@angular/material/button";
import {MatTabsModule} from "@angular/material/tabs";
import {MatCardModule} from "@angular/material/card";
import {MatListModule} from "@angular/material/list";
import {MatSelectModule} from "@angular/material/select";
import {MatTableModule} from "@angular/material/table";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatIconModule} from "@angular/material/icon";
import {MatRadioModule} from "@angular/material/radio";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {UserCardComponent} from "./user-card/user-card.component";
import {RecipeUpdateComponent} from "./recipe-update/recipe-update.component";

@NgModule({
    declarations: [
        AppComponent,
        LoginComponent,
        HomeComponent,
        RegistrationComponent,
        RecipeCardComponent,
        IngredientCardComponent,
        SelectionsPageComponent,
        SelectionCardComponent,
        UserProfileComponent,
        UserCardComponent,
        ReviewComponent,
        RecipeUpdateComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        HttpClientModule,
        HttpClientModule,
        ReactiveFormsModule,
        NgSelectModule,
        MatSliderModule,
        BrowserAnimationsModule,
        MatAutocompleteModule,
        MatInputModule,
        MatNativeDateModule,
        MatButtonModule,
        MatRippleModule,
        MatTabsModule,
        MatCardModule,
        MatListModule,
        MatSelectModule,
        MatTableModule,
        MatPaginatorModule,
        MatIconModule,
        MatRadioModule,
        MatProgressSpinnerModule,
        MatDatepickerModule,
    ],
    providers: [RestapiService],
    bootstrap: [AppComponent]
})

export class AppModule {}