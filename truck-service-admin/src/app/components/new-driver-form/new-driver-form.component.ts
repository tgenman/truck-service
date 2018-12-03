import {Component, OnInit} from '@angular/core';
import {NgxSmartModalService} from 'ngx-smart-modal';
import {Observable} from 'rxjs';
import {Driver} from '../../model/driver';
import {City} from '../../model/city';
import {ApiClientService} from '../../services/api-client/api-client.service';
import {ifTrue} from 'codelyzer/util/function';
import {DriverService} from '../../services/services/driver.service';

@Component({
    selector: 'app-new-driver-form',
    templateUrl: './new-driver-form.component.html',
    styleUrls: ['../../../assets/css/material-dashboard.min.css']
})
export class NewDriverFormComponent implements OnInit {

    driver: Driver;
    cities$: Observable<City[]>;

    constructor(private ngxSmartModalService: NgxSmartModalService, private apiClientService: ApiClientService,
                private driverService: DriverService) {
    }

    ngOnInit() {
        this.driver = new Driver();
        this.apiClientService.getCities();
        this.cities$ = this.apiClientService.getCities();
        console.log('cities' + this.cities$);
    }

    public onSubmit() {
        console.log(`${this.driver.firstName} | and ${this.driver.city}`);

        this.driverService.addNewDriver(this.driver);
        this.ngxSmartModalService.closeLatestModal();
        this.driver.firstName = '';
        this.driver.lastName = '';
        this.driver.city = '';
    }

}
