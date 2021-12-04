import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { RegistrationComponent } from './registration/registration.component'
import { RecipeCardComponent } from "./Recipe-card/recipe-card.component";
import { IngredientCardComponent } from "./ingredient-card/ingredient-card.component";
import {UserProfileComponent} from "./user-profile/user-profile.component";
import { NgSelectModule } from '@ng-select/ng-select';
import { FormsModule } from '@angular/forms';
import {AppComponent} from "./app.component";



const routes: Routes = [
    { path: "", redirectTo: "login", pathMatch:"full" },
    { path: "login", component: LoginComponent },
    { path: "home", component: HomeComponent  },
    { path: "registration", component: RegistrationComponent },
    { path: "recipe-card/:id", component: RecipeCardComponent },
    { path: "ingredient-card/:id", component: IngredientCardComponent },
    { path: "user-profile", component: UserProfileComponent }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {}

@NgModule({
    imports: [NgSelectModule, FormsModule],
    bootstrap: [AppComponent]
})
export class AppModule {}

