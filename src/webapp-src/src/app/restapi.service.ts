import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { User } from './model/User'
import { map } from 'rxjs/operators';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
    providedIn: 'root'
})

export class RestapiService {

    authHeader : HttpHeaders;
    constructor(private http:HttpClient) {}

    login(username: string, password: string){
        this.authHeader = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
        let headers = this.authHeader;
        headers.append('Access-Control-Allow-Origin', 'localhost:8080');

        return this.http.get("http://localhost:8080/", {headers, responseType: 'text' as 'json'})
    }

    getUser(login: String) {
        return this.http.post<User>("http://localhost:8080/get_userdetails", login)
            .pipe(map(response => {
                    let res = <User> response;
                    Object.setPrototypeOf(res, User.prototype);
                    return res;
                }
            ))
    }

    getCredentials() {
        return this.http.get("http://localhost:8080/get_credentials")
    }

    getUsers() {
        let headers = this.authHeader;
        //headers.append('Access-Control-Allow-Origin', 'localhost:8080');

        //return  this.http.get("http://localhost:4200/api/user",{headers, responseType: 'text' as 'json'});
    }

    addUser(user: User) {
        return this.http.post<User[]>('http://localhost:4200/register', user);
    }
}