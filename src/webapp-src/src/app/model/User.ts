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

export var currentUser : User;