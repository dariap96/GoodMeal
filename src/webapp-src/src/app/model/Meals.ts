export interface Meals {
    data:  Datum[];
    links: DatumLinks;
}

export interface Datum {
    id:            number;
    type:          Type;
    links:         DatumLinks;
    attributes:    Attributes;
    relationships: Relationships;
}

export interface Attributes {
    type: string;
}

export interface DatumLinks {
    self: string;
}

export interface Relationships {
    recipes: Recipes;
}

export interface Recipes {
    links: RecipesLinks;
}

export interface RecipesLinks {
    self:    string;
    related: string;
}

export enum Type {
    Meal = "meal",
}

export class ConvertMeals {
    public static toMeals(json: string): Meals {
        return JSON.parse(json);
    }

    public static mealsToJson(value: Meals): string {
        return JSON.stringify(value);
    }
}