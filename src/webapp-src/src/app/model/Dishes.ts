export interface Dishes {
    data:  Datum[];
    links: DatumLinks;
}

export interface Datum {
    id:            string;
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
    Dish = "dish",
}

// Converts JSON strings to/from your types
export class Convert {
    public static toDishes(json: string): Dishes {
        return JSON.parse(json);
    }

    public static dishesToJson(value: Dishes): string {
        return JSON.stringify(value);
    }
}
