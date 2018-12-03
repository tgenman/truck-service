import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {Driver} from '../../model/driver';
import {ApiClientService} from '../../services/api-client/api-client.service';
import {DriverService} from '../../services/services/driver.service';
import {NgxSmartModalService} from 'ngx-smart-modal';
import {Truck} from '../../model/truck';
import {TruckService} from '../../services/services/truck.service';

@Component({
  selector: 'app-truck-table',
  templateUrl: './truck-table.component.html',
  styleUrls: ['../../../assets/css/material-dashboard.min.css']
})
export class TruckTableComponent implements OnInit {

    public trucks$: Observable<Truck[]>;
    public selectedTruckId: number;
    public truckUpdate: Truck;

    constructor(private apiClientService: ApiClientService, private service: TruckService,
                private ngxSmartModalService: NgxSmartModalService) {
    }

    ngOnInit() {
        this.trucks$ = this.apiClientService.getTrucks();
    }

    public deleteTruck(id: number) {
        this.service.deleteTruck(id);
    }


    public onClickUpdate(id: number) {
        console.log('onClickUpdate');
        this.selectedTruckId = id;
        this.service.getTruckById(this.selectedTruckId).subscribe(res => {
            console.log('GET DRIVER BY ID' + this.selectedTruckId + 'JSON ' + JSON.stringify(res));
            this.truckUpdate = res;
        });
        this.ngxSmartModalService.getModal('updateTruckModal').open();
    }

}
