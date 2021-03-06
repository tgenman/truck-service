import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import {AuthenticationService} from '../../services/authentication/authentication.service';

@Component({
    templateUrl: './login.component.html',
    styleUrls: ['../../../assets/css/material-dashboard.min.css']
})
export class LoginComponent {

    credentials = {username: '', password: ''};

    constructor(private auth: AuthenticationService, private http: HttpClient, private router: Router) {
    }

    login() {
        this.auth.authenticate(this.credentials, () => {
            this.router.navigateByUrl('/dashboard');
        });
        return false;
    }

}
