export interface Labels {
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
    label : string;
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
    Label = "label",
}

export class ConvertLabels {
    public static toLabels(json : string) : Labels {
        return JSON.parse(json);
    }

    public static labelsToJson(value : Labels) : string {
        return JSON.stringify(value);
    }
}