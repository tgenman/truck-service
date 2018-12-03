import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Driver} from '../../model/driver';
import {ApiClientService} from '../../services/api-client/api-client.service';
import {DriverService} from '../../services/services/driver.service';
import {NgxSmartModalService} from 'ngx-smart-modal';
import {map} from 'rxjs/operators';
import {City} from '../../model/city';

@Component({
    selector: 'app-driver-table',
    templateUrl: './driver-table.component.html',
    styleUrls: ['../../../assets/css/material-dashboard.min.css']
})
export class DriverTableComponent implements OnInit {

    public drivers$: Observable<Driver[]>;
    public selectedDriverId: number;
    public driverUpdate: Driver;

    constructor(private apiClientService: ApiClientService, private service: DriverService,
                private ngxSmartModalService: NgxSmartModalService) {
    }

    ngOnInit() {
        this.drivers$ = this.apiClientService.getDrivers();
    }

    public deleteDriver(id: number) {
        this.service.deleteDriver(id);
    }


    public onClickUpdate(id: number) {
        console.log('onClickUpdate');
        this.selectedDriverId = id;
        this.service.getDriverById(this.selectedDriverId).subscribe(res => {
            console.log('GET DRIVER BY ID' + this.selectedDriverId + 'JSON ' + JSON.stringify(res));
            this.driverUpdate = res;
        });
        this.ngxSmartModalService.getModal('updateDriverModal').open();
    }


}
