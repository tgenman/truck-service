import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { finalize } from 'rxjs/operators';
import {AuthenticationService} from './services/authentication/authentication.service';
const URL = 'http://localhost:8080';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
    constructor(private app: AuthenticationService, private http: HttpClient, private router: Router) {
        this.app.authenticate(undefined, undefined);
    }
    logout() {
        this.http.post(`${URL}/logout`, {}).pipe(finalize(() => {
            this.app.authenticated = false;
            localStorage.removeItem('currentUser');
            this.router.navigateByUrl('/');
        })).subscribe();
    }}
