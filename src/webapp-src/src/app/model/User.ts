export class User {
    login : String;
    password : String;
    name : String;
    surname : String;
    email : String;
    bday : String;
    userCredentials? : String;
}

export let currentUser = new User();