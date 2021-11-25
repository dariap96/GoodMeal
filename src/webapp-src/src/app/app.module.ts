import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { RegistrationComponent } from './registration/registration.component'
import { RestapiService } from './restapi.service';
import { RecipeCardComponent } from "./Recipe-card/recipe-card.component";
import {IngredientCardComponent} from "./ingredient-card/ingredient-card.component";
import {UserProfileComponent} from "./user-profile/user-profile.component";

@NgModule({
    declarations: [
        AppComponent,
        LoginComponent,
        HomeComponent,
        RegistrationComponent,
        RecipeCardComponent,
        IngredientCardComponent,
        UserProfileComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        HttpClientModule
    ],
    providers: [RestapiService],
    bootstrap: [AppComponent]
})

export class AppModule {}