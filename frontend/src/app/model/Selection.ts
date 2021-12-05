export interface Selection {
    data:  SelectionData;
    links: DataLinks;
}

export interface SelectionData {
    id:            string;
    type:          string;
    links:         DataLinks;
    attributes:    Attributes;
    relationships: Relationships;
}

export interface Attributes {
    selectionName: string;
}

export interface DataLinks {
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
    data:  UserData;
    links: IngredientSetLinks;
}

export interface UserData {
    id:   string;
    type: string;
}

export class ConvertSelection {
    public static toSelection(json: string): Selection {
        return JSON.parse(json);
    }

    public static selectionToJson(value: Selection): string {
        return JSON.stringify(value);
    }
}
