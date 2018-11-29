import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {AuthenticationService} from '../../services/authentication/authentication.service';

@Component({
    templateUrl: './home.component.html'
})
export class HomeComponent {

    greeting = {};

    constructor(private auth: AuthenticationService, private http: HttpClient) {
    }

    authenticated() { return this.auth.authenticated; }

}
