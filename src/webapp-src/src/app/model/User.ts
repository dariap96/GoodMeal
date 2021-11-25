export interface User {
    enabled :               boolean;
    authorities :           Authority[];
    accountNonExpired :     boolean;
    accountNonLocked :      boolean;
    credentialsNonExpired : boolean;
    username :              string;
    password :              string;
}

export interface Authority {
    authority : string;
}

export class ConvertUser {
    public static toUser(json : string) : User {
        return JSON.parse(json);
    }

    public static userToJson(value : User) : string {
        return JSON.stringify(value);
    }
}

export interface UserInfo {
    name:    string;
    surname: string;
    login:   string;
    email:   string;
    bday:    Date;
    roles:   string[];
}

export class ConvertUserInfo {
    public static toUserInfo(json: string): UserInfo {
        return JSON.parse(json);
    }

    public static userInfoToJson(value: UserInfo): string {
        return JSON.stringify(value);
    }
}

// To parse this data:
//
//   import { Convert, Users } from "./file";
//
//   const users = Convert.toUsers(json);

export interface Users {
    data:  UsersDatum[];
    links: DatumLinks;
}

export interface UsersDatum {
    id:            string;
    type:          string;
    links:         DatumLinks;
    attributes:    Attributes;
    relationships: Relationships;
}

export interface Attributes {
    login:   string;
    name:    string;
    surname: string;
    email:   string;
    bday:    Date;
}

export interface DatumLinks {
    self: string;
}

export interface Relationships {
    selectionSet: Set;
    roleSet:      Set;
}

export interface Set {
    data:  RoleSetDatum[];
    links: RoleSetLinks;
}

export interface RoleSetDatum {
    id:   string;
    type: string;
}

export interface RoleSetLinks {
    self:    string;
    related: string;
}

// Converts JSON strings to/from your types
export class ConvertUsers {
    public static toUsers(json: string): Users {
        return JSON.parse(json);
    }

    public static usersToJson(value: Users): string {
        return JSON.stringify(value);
    }
}

export var currentUser : User;