export interface RecipeRatingInfo {
    recipeId :  number;
    userLogin :    string;
    rating :    number;
    review :    string;
}

export class ConvertRecipesRatingsArray {
    public static toRecipesRatingsArray(json : string) : RecipeRatingInfo[] {
        return JSON.parse(json);
    }

    public static recipesRatingsArrayToJson(value : RecipeRatingInfo[]) : string {
        return JSON.stringify(value);
    }
}