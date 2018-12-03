import {Component, Input, OnInit} from '@angular/core';
import {Truck} from '../../model/truck';
import {Observable} from 'rxjs';
import {City} from '../../model/city';
import {ApiClientService} from '../../services/api-client/api-client.service';
import {TruckService} from '../../services/services/truck.service';
import {NgxSmartModalService} from 'ngx-smart-modal';
import {Manager} from '../../model/manager';
import {ManagerService} from '../../services/services/manager.service';

@Component({
  selector: 'app-update-manager-form',
  templateUrl: './update-manager-form.component.html',
    styleUrls: ['../../../assets/css/material-dashboard.min.css']
})
export class UpdateManagerFormComponent implements OnInit {

    @Input() selectedId: number;
    public managerUpdated: Manager;
    public reloaded = false;


    constructor(private apiClientService: ApiClientService, private service: ManagerService,
                private ngxSmartModalService: NgxSmartModalService) {
        this.managerUpdated = new Manager();
    }

    ngOnInit() {
        this.service.getManagerById(this.selectedId).subscribe(res => {
            this.managerUpdated = res;
        });
    }

    updateData(): void {
        this.service.getManagerById(this.selectedId).subscribe(res => {
            this.managerUpdated = res;
        });
        this.reloaded = true;
    }

    public onSubmitUpdate() {
        console.log('UPDATE TRUCK' + this.selectedId + 'JSON ' + JSON.stringify(this.managerUpdated));
        this.ngxSmartModalService.closeLatestModal();
        this.service.updateManager(this.managerUpdated);
        this.reloaded = false;
        this.managerUpdated.firstName = '';
        this.managerUpdated.lastName = '';
        this.managerUpdated.username = '';
        this.managerUpdated.password = '';
    }


}
