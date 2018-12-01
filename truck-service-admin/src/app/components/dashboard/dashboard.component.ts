import { Component, OnInit } from '@angular/core';
import {LoaderService} from '../../services/loader/loader.service';
import {Cargo} from '../../model/cargo';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {Customer} from '../../model/customer';
import {Order} from '../../model/order';
import {RoutePoint} from '../../model/routepoint';
import {Shift} from '../../model/shift';
import {Tempshift} from '../../model/tempshift';
import {Truck} from '../../model/truck';
const BASE_URL = 'http://localhost:8080/api';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

    public cargoes$: Observable<Cargo[]>;
    public customers$: Observable<Customer[]>;
    public orders$: Observable<Order[]>;
    public routePoints$: Observable<RoutePoint[]>;
    public shifts$: Observable<Shift[]>;
    public tempShifts$: Observable<Tempshift[]>;
    public trucks$: Observable<Truck[]>;

  constructor(private loader: LoaderService, private http: HttpClient) { }

  ngOnInit() {
      this.cargoes$ = this.loader.getCargoes();
      this.customers$ = this.loader.getCustomers();
      this.orders$ = this.loader.getOrders();
      this.routePoints$ = this.loader.getRoutePoints();
      this.shifts$ = this.loader.getShifts();
      this.tempShifts$ = this.loader.getTempShifts();
      this.trucks$ = this.loader.getTrucks();

  }

}
