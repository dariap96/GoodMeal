export interface Ingredients {
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
    name:       string;
    energy:     number;
    fat:        number;
    protein:    number;
    carbs:      number;
    fiber:      number;
    image:      null | string;
    originalId: string;
}

export interface DatumLinks {
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
