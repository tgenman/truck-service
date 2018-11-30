import { Component, OnInit } from '@angular/core';
import {LoaderService} from '../../services/loader/loader.service';
import {Cargo} from '../../model/cargo';
import {Observable, of} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {catchError} from 'rxjs/operators';
import {Driver} from '../../model/driver';
import {ajax} from 'rxjs/ajax';
import {Customer} from '../../model/customer';
import {Order} from '../../model/order';
import {RoutePoint} from '../../model/routepoint';
import {Shift} from '../../model/shift';
import {Tempshift} from '../../model/tempshift';
import {Truck} from '../../model/truck';
const BASE_URL = 'http://localhost:8080/api';


@Component({
  selector: 'app-admin-common',
  templateUrl: './admin-common.component.html',
  styleUrls: ['./admin-common.component.css']
})
export class AdminCommonComponent implements OnInit {

    public cargoes$: Observable<Cargo[]>;
    public drivers$: Observable<Driver[]>;
    public customers$: Observable<Customer[]>;
    public orders$: Observable<Order[]>;
    public routePoints$: Observable<RoutePoint[]>;
    public shifts$: Observable<Shift[]>;
    public tempShifts$: Observable<Tempshift[]>;
    public trucks$: Observable<Truck[]>;

  constructor(private loader: LoaderService, private http: HttpClient) { }

  ngOnInit() {
      this.cargoes$ = this.loader.getCargoes();
      this.drivers$ = this.loader.getDrivers();
      this.customers$ = this.loader.getCustomers();
      this.orders$ = this.loader.getOrders();
      this.routePoints$ = this.loader.getRoutePoints();
      this.shifts$ = this.loader.getShifts();
      this.tempShifts$ = this.loader.getTempShifts();
      this.trucks$ = this.loader.getTrucks();

  }

}
