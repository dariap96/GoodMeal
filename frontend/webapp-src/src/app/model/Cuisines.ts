export interface Cuisines {
    data :  Datum[];
    links : DatumLinks;
}

export interface Datum {
    id :            number;
    type :          Type;
    links :         DatumLinks;
    attributes :    Attributes;
    relationships : Relationships;
}

export interface Attributes {
    type : string;
}

export interface DatumLinks {
    self : string;
}

export interface Relationships {
    recipes : Recipes;
}

export interface Recipes {
    links : RecipesLinks;
}

export interface RecipesLinks {
    self :    string;
    related : string;
}

export enum Type {
    Cuisine = "cuisine",
}

export class ConvertCuisines {
    public static toCuisines(json : string) : Cuisines {
        return JSON.parse(json);
    }

    public static cuisinesToJson(value : Cuisines) : string {
        return JSON.stringify(value);
    }
}