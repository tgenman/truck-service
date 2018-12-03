import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {AuthenticationService} from '../../services/authentication/authentication.service';
import {finalize} from 'rxjs/operators';
import {Router} from '@angular/router';

@Component({
    templateUrl: './home.component.html'
})
export class HomeComponent {

    greeting = {};

    constructor(private auth: AuthenticationService, private http: HttpClient, private router: Router) {
    }

    authenticated() { return this.auth.authenticated; }

    logout() {
        this.http.post(`${URL}/logout`, {}).pipe(finalize(() => {
            this.auth.authenticated = false;
            localStorage.removeItem('currentUser');
            this.router.navigateByUrl('/');
        })).subscribe();
    }

}
