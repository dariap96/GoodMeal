export interface Selections {
    data:  Datum[];
    links: DatumLinks;
}

export interface Datum {
    id:            string;
    type:          string;
    links:         DatumLinks;
    attributes:    Attributes;
    relationships: Relationships;
}

export interface Attributes {
    selectionName: string;
}

export interface DatumLinks {
    self: string;
}

export interface Relationships {
    recipeSet:     Set;
    user:          User;
    ingredientSet: Set;
}

export interface Set {
    links: IngredientSetLinks;
}

export interface IngredientSetLinks {
    self:    string;
    related: string;
}

export interface User {
    data:  Data;
    links: IngredientSetLinks;
}

export interface Data {
    id:   string;
    type: string;
}