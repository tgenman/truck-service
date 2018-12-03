import {AfterContentInit, AfterViewChecked, AfterViewInit, Component, Input, OnInit} from '@angular/core';
import {Driver} from '../../model/driver';
import {BehaviorSubject, Observable} from 'rxjs';
import {City} from '../../model/city';
import {ApiClientService} from '../../services/api-client/api-client.service';
import {DriverService} from '../../services/services/driver.service';
import {NgxSmartModalService} from 'ngx-smart-modal';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
    selector: 'app-update-driver-form',
    templateUrl: './update-driver-form.component.html',
    styleUrls: ['../../../assets/css/material-dashboard.min.css']
})
export class UpdateDriverFormComponent implements OnInit, AfterContentInit {

    @Input() selectedId: number;
    public driverUpdated: Driver;
    public cities$: Observable<City[]>;


    constructor(private apiClientService: ApiClientService, private service: DriverService,
                private ngxSmartModalService: NgxSmartModalService) {
        this.driverUpdated = new Driver();
    }

    ngAfterContentInit(): void {
        this.service.getDriverById(this.selectedId).subscribe(res => {
            this.driverUpdated = res;
        });
    }

    ngOnInit() {
        this.cities$ = this.apiClientService.getCities();
        console.log('GET DRIVER BY ID' + this.selectedId + 'JSON ' + JSON.stringify(this.driverUpdated));
    }

    updateData(): void {
        this.service.getDriverById(this.selectedId).subscribe(res => {
            this.driverUpdated = res;
        });
    }

    public onSubmitUpdate() {

        console.log('UPDATE DRIVER' + this.selectedId + 'JSON ' + JSON.stringify(this.driverUpdated));
        this.ngxSmartModalService.closeLatestModal();
        this.service.updateDriver(this.driverUpdated);
        this.driverUpdated.firstName = '';
        this.driverUpdated.lastName = '';
        this.driverUpdated.city = '';
    }

}
