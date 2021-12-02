export interface RecipeRating {
    data :  Datum;
    links : DatumLinks;
}

export interface Datum {
    type :          Type;
    links :         DatumLinks;
    attributes :    Attributes;
}

export interface Attributes {
    recipeId :  number;
    userLogin :    string;
    rating :    number;
    review :    string;
}

export interface DatumLinks {
    self : string;
}

export enum Type {
    RecipeRating = "recipe rating"
}

export class ConvertRecipeRating {
    public static toRecipeRating(json : string) : RecipeRating {
        return JSON.parse(json);
    }

    public static recipeRatingToJson(value : RecipeRating) : string {
        return JSON.stringify(value);
    }
}