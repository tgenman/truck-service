import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {RouterModule, Routes} from '@angular/router';
import {AppComponent} from './app.component';
import {Injectable} from '@angular/core';
import {HTTP_INTERCEPTORS} from '@angular/common/http';

import {HomeComponent} from './components/home/home.component';
import {LoginComponent} from './components/login/login.component';
import {AuthenticationService} from './services/authentication/authentication.service';
import {RoutingModule} from './app.routing';
import {DashboardComponent} from './components/dashboard/dashboard.component';
import {XhrInterceptor} from './interceptors/xhr.interceptor';
import {AuthGuard} from './guards/auth-guard';

@NgModule({
    declarations: [
        AppComponent,
        HomeComponent,
        LoginComponent,
        DashboardComponent,
    ],
    imports: [
        BrowserModule,
        FormsModule,
        HttpClientModule,
        RoutingModule
    ],
    providers: [AuthenticationService, {provide: HTTP_INTERCEPTORS, useClass: XhrInterceptor, multi: true}],
    bootstrap: [AppComponent]
})
export class AppModule {
}
