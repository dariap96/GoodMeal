export interface Ingredient {
    data:  Data;
    links: DataLinks;
}

export interface Data {
    id:            string;
    type:          string;
    links:         DataLinks;
    attributes:    Attributes;
    relationships: Relationships;
}

export interface Attributes {
    name:       string;
    energy:     number;
    fat:        number;
    protein:    number;
    carbs:      number;
    fiber:      number;
    image:      string;
    originalId: string;
}

export interface DataLinks {
    self: string;
}

export interface Relationships {
    selectionSet:   Set;
    ingredientsSet: Set;
}

export interface Set {
    links: IngredientsSetLinks;
}

export interface IngredientsSetLinks {
    self:    string;
    related: string;
}
