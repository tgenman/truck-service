import { Component, OnInit } from '@angular/core';
import {Truck} from '../../model/truck';
import {Observable} from 'rxjs';
import {City} from '../../model/city';
import {NgxSmartModalService} from 'ngx-smart-modal';
import {ApiClientService} from '../../services/api-client/api-client.service';
import {TruckService} from '../../services/services/truck.service';
import {Manager} from '../../model/manager';
import {ManagerService} from '../../services/services/manager.service';

@Component({
  selector: 'app-new-manager-form',
  templateUrl: './new-manager-form.component.html',
  styleUrls: ['../../../assets/css/material-dashboard.min.css']
})
export class NewManagerFormComponent implements OnInit {


    manager: Manager;

    constructor(private ngxSmartModalService: NgxSmartModalService, private apiClientService: ApiClientService,
                private managerService: ManagerService) {
    }

    ngOnInit() {
        this.manager = new Manager();
    }

    public onSubmit() {
        console.log(`${this.manager.firstName} | and ${this.manager.lastName}`);

        this.managerService.addNewManager(this.manager);
        this.ngxSmartModalService.closeLatestModal();
        this.manager.firstName = '';
        this.manager.lastName = '';
        this.manager.username = '';
        this.manager.password = '';
    }

}
