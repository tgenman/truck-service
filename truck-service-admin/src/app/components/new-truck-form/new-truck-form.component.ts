import { Component, OnInit } from '@angular/core';
import {Driver} from '../../model/driver';
import {Observable} from 'rxjs';
import {City} from '../../model/city';
import {NgxSmartModalService} from 'ngx-smart-modal';
import {ApiClientService} from '../../services/api-client/api-client.service';
import {DriverService} from '../../services/services/driver.service';
import {Truck} from '../../model/truck';
import {TruckService} from '../../services/services/truck.service';

@Component({
  selector: 'app-new-truck-form',
  templateUrl: './new-truck-form.component.html',
  styleUrls: ['../../../assets/css/material-dashboard.min.css']
})
export class NewTruckFormComponent implements OnInit {

    truck: Truck;
    cities$: Observable<City[]>;

    constructor(private ngxSmartModalService: NgxSmartModalService, private apiClientService: ApiClientService,
                private truckService: TruckService) {
    }

    ngOnInit() {
        this.truck = new Truck();
        this.apiClientService.getCities();
        this.cities$ = this.apiClientService.getCities();
        console.log('cities' + this.cities$);
    }

    public onSubmit() {
        console.log(`${this.truck.brand} | and ${this.truck.cityName}`);

        this.truckService.addNewTruck(this.truck);
        this.ngxSmartModalService.closeLatestModal();
        this.truck.brand = '';
        this.truck.model = '';
        this.truck.licensePlate = '';
        this.truck.licensePlate = '';
    }

}
