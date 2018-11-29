import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from './components/login/login.component';
import {HomeComponent} from './components/home/home.component';
import {AdminCommonComponent} from './components/admin-common/admin-common.component';


const routes: Routes = [
    { path: 'login', component: LoginComponent},
    { path: 'home', component: HomeComponent},
    { path: '', component: HomeComponent},
    {path: 'admin-common', component: AdminCommonComponent}

];

@NgModule({
    imports: [ RouterModule.forRoot(routes) ],
    exports: [
        RouterModule
    ]
})
export class RoutingModule { }
