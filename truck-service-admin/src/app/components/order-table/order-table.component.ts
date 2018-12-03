import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {Order} from '../../model/order';
import {ApiClientService} from '../../services/api-client/api-client.service';

@Component({
  selector: 'app-order-table',
  templateUrl: './order-table.component.html',
  styleUrls: ['../../../assets/css/material-dashboard.min.css']
})
export class OrderTableComponent implements OnInit {

    public orders$: Observable<Order[]>;

    constructor(private loader: ApiClientService) { }

  ngOnInit() {
      this.orders$ = this.loader.getOrders();
  }

}
