import { Injectable } from '@angular/core';
import {ApiClientService} from '../api-client/api-client.service';
import {Driver} from '../../model/driver';
import {Observable} from 'rxjs';
import {Manager} from '../../model/manager';

@Injectable({
  providedIn: 'root'
})
export class ManagerService {

    constructor(private apiClient: ApiClientService) {
    }

    public addNewManager(manager: Manager): void {
        this.apiClient.addNewManager(manager);
    }

    public deleteManager(id: number): void {
        this.apiClient.deleteManager(id);
    }

    public updateManager(updatableManager: Manager) {
        this.apiClient.updateManager(updatableManager);
    }

    public getManagerById(id: number): Observable<Manager> {
        return this.apiClient.getManagerById(id);
    }
}
