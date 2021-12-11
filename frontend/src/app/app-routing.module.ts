import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { RegistrationComponent } from './registration/registration.component'
import { RecipeCardComponent } from "./recipe-card/recipe-card.component";
import { IngredientCardComponent } from "./ingredient-card/ingredient-card.component";
import { UserProfileComponent } from "./user-profile/user-profile.component";
import { ReviewComponent } from "./review/review.component";
import { SelectionCardComponent } from "./selection-card/selection-card.component";
import { SelectionsPageComponent } from "./selections-page/selections-page.component";
import { NgSelectModule } from '@ng-select/ng-select';
import { FormsModule } from '@angular/forms';
import { AppComponent } from "./app.component";
import { UserCardComponent } from "./user-card/user-card.component";

const routes: Routes = [
    { path: "login", component: LoginComponent },
    { path: "home", component: HomeComponent },
    { path: "registration", component: RegistrationComponent },
    { path: "recipe-card/:id", component: RecipeCardComponent },
    { path: "ingredient-card/:id", component: IngredientCardComponent },
    { path: "selection-card/:id", component: SelectionCardComponent },
    { path: "selections", component: SelectionsPageComponent },
    { path: "user-profile", component: UserProfileComponent },
    { path: "user-card/:id", component: UserCardComponent },
    { path: "recipe_review/:id", component: ReviewComponent }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class AppRoutingModule {}

NgModule({
    imports: [NgSelectModule, FormsModule],
    bootstrap: [AppComponent]
})
export class AppModule {}