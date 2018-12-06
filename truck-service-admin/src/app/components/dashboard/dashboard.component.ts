import { Component, OnInit } from '@angular/core';
import {ApiClientService} from '../../services/api-client/api-client.service';
import {Cargo} from '../../model/cargo';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {Customer} from '../../model/customer';
import {Order} from '../../model/order';
import {RoutePoint} from '../../model/routepoint';
import {Shift} from '../../model/shift';
import {Tempshift} from '../../model/tempshift';
import {Truck} from '../../model/truck';
import {Driver} from '../../model/driver';
import {finalize} from 'rxjs/operators';
import {AuthenticationService} from '../../services/authentication/authentication.service';
import {Router} from '@angular/router';
const BASE_URL = 'http://localhost:8080/truckservice/api';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

    public cargoes$: Observable<Cargo[]>;
    public customers$: Observable<Customer[]>;
    public routePoints$: Observable<RoutePoint[]>;
    public shifts$: Observable<Shift[]>;
    public tempShifts$: Observable<Tempshift[]>;
    public trucks$: Observable<Truck[]>;


    constructor(private loader: ApiClientService, private http: HttpClient,
                private auth: AuthenticationService, private router: Router) { }

  ngOnInit() {
      this.cargoes$ = this.loader.getCargoes();
      this.customers$ = this.loader.getCustomers();
      this.routePoints$ = this.loader.getRoutePoints();
      this.shifts$ = this.loader.getShifts();
      this.tempShifts$ = this.loader.getTempShifts();
      this.trucks$ = this.loader.getTrucks();
  }

    logout() {
        this.http.post(`${URL}/logout`, {}).pipe(finalize(() => {
            this.auth.authenticated = false;
            localStorage.removeItem('currentUser');
            this.router.navigateByUrl('/');
        })).subscribe();
    }

}
