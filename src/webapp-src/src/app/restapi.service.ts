import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
    providedIn: 'root'
})

export class RestapiService {

    authHeader : HttpHeaders;
    constructor(private http:HttpClient) {}

    login(username:string,password:string){
        this.authHeader = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
        let headers = this.authHeader;
        headers.append('Access-Control-Allow-Origin', 'localhost:8080');

        return this.http.get("http://localhost:8080/",{headers, responseType: 'text' as 'json'})
    }

    getUsers() {
        let headers = this.authHeader;
        headers.append('Access-Control-Allow-Origin', 'localhost:8080');

        return  this.http.get("http://localhost:4200/api/ingredient",{headers, responseType: 'text' as 'json'});
    }

    register(username: string, email: string, password: string): Observable<any> {
        return this.http.post(AUTH_API + 'signup', {
          username,
          email,
          password
        }, httpOptions);
      }
}