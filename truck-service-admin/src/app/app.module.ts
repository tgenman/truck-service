import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormGroup, FormsModule, ReactiveFormsModule} from '@angular/forms';
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
import { OrderTableComponent } from './components/order-table/order-table.component';
import { DriverTableComponent } from './components/driver-table/driver-table.component';
import {NgxSmartModalModule} from 'ngx-smart-modal';
import { NewDriverFormComponent } from './components/new-driver-form/new-driver-form.component';
import { UpdateDriverFormComponent } from './components/update-driver-form/update-driver-form.component';

@NgModule({
    declarations: [
        AppComponent,
        HomeComponent,
        LoginComponent,
        DashboardComponent,
        OrderTableComponent,
        DriverTableComponent,
        NewDriverFormComponent,
        UpdateDriverFormComponent,
    ],
    imports: [
        BrowserModule,
        FormsModule,
        HttpClientModule,
        RoutingModule,
        NgxSmartModalModule.forRoot(),
    ],
    providers: [AuthenticationService, {provide: HTTP_INTERCEPTORS, useClass: XhrInterceptor, multi: true}],
    bootstrap: [AppComponent]
})
export class AppModule {
}
