import {Component, Input, OnInit} from '@angular/core';
import {Driver} from '../../model/driver';
import {Observable} from 'rxjs';
import {City} from '../../model/city';
import {ApiClientService} from '../../services/api-client/api-client.service';
import {DriverService} from '../../services/services/driver.service';
import {NgxSmartModalService} from 'ngx-smart-modal';
import {Truck} from '../../model/truck';
import {TruckService} from '../../services/services/truck.service';

@Component({
  selector: 'app-update-truck-form',
  templateUrl: './update-truck-form.component.html',
  styleUrls: ['../../../assets/css/material-dashboard.min.css']
})
export class UpdateTruckFormComponent implements OnInit {

    @Input() selectedId: number;
    public truckUpdated: Truck;
    public cities$: Observable<City[]>;
    public reloaded = false;


    constructor(private apiClientService: ApiClientService, private service: TruckService,
                private ngxSmartModalService: NgxSmartModalService) {
        this.truckUpdated = new Truck();
    }

    ngOnInit() {
        this.cities$ = this.apiClientService.getCities();
        console.log('GET DRIVER BY ID' + this.selectedId + 'JSON ' + JSON.stringify(this.truckUpdated));
    }

    updateData(): void {
        this.service.getTruckById(this.selectedId).subscribe(res => {
            this.truckUpdated = res;
        });
        this.reloaded = true;
    }

    public onSubmitUpdate() {

        console.log('UPDATE TRUCK' + this.selectedId + 'JSON ' + JSON.stringify(this.truckUpdated));
        this.ngxSmartModalService.closeLatestModal();
        this.service.updateTruck(this.truckUpdated);
        this.reloaded = false;
        this.truckUpdated.brand = '';
        this.truckUpdated.model = '';
        this.truckUpdated.licensePlate = '';
        this.truckUpdated.cityName = '';
    }

}
