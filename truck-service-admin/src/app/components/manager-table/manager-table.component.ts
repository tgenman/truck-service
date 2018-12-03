import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {Driver} from '../../model/driver';
import {ApiClientService} from '../../services/api-client/api-client.service';
import {DriverService} from '../../services/services/driver.service';
import {NgxSmartModalService} from 'ngx-smart-modal';
import {Manager} from '../../model/manager';
import {ManagerService} from '../../services/services/manager.service';

@Component({
  selector: 'app-manager-table',
  templateUrl: './manager-table.component.html',
  styleUrls: ['../../../assets/css/material-dashboard.min.css']}
  )
export class ManagerTableComponent implements OnInit {

    public managers$: Observable<Manager[]>;
    public selectedManagerId: number;
    public managerUpdate: Manager;

    constructor(private apiClientService: ApiClientService, private service: ManagerService,
                private ngxSmartModalService: NgxSmartModalService) {
    }

    ngOnInit() {
        this.managers$ = this.apiClientService.getManagers();
    }

    public deleteManager(id: number) {
        this.service.deleteManager(id);
    }


    public onClickUpdate(id: number) {
        console.log('onClickUpdate');
        this.selectedManagerId = id;
        this.service.getManagerById(this.selectedManagerId).subscribe(res => {
            console.log('GET DRIVER BY ID' + this.selectedManagerId + 'JSON ' + JSON.stringify(res));
            this.managerUpdate = res;
        });
        this.ngxSmartModalService.getModal('updateManagerModal').open();
    }

    public onClickCredentials() {

    }

}
