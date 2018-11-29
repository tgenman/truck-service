import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from './components/login/login.component';
import {HomeComponent} from './components/home/home.component';
import {AdminCommonComponent} from './components/admin-common/admin-common.component';
import {AuthGuard} from './guards/AuthGuard';


const routes: Routes = [
    { path: 'login', component: LoginComponent},
    { path: '', component: HomeComponent},
    {path: 'dashboard', component: AdminCommonComponent, canActivate: [AuthGuard] }

];

@NgModule({
    imports: [ RouterModule.forRoot(routes) ],
    exports: [
        RouterModule
    ]
})
export class RoutingModule { }
