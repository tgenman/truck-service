import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {Router} from '@angular/router';
const URL = 'http://localhost:8080';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

    authenticated = false;

    constructor(private http: HttpClient, private router: Router) {
    }

    authenticate(credentials, callback) {

        const headers = new HttpHeaders(credentials ? {
            authorization : 'Basic ' + btoa(credentials.username + ':' + credentials.password)
        } : {});

        this.http.get(`${URL}/api/user`, {headers: headers}).subscribe(response => {
            if (response['name']) {
                this.authenticated = true;

                localStorage.setItem('currentUser', JSON.stringify(btoa(credentials.username + ':' + credentials.password)));
                this.router.navigateByUrl('/admin-common');
            } else {
                this.authenticated = false;
            }
            return callback && callback();
        });

    }
}
