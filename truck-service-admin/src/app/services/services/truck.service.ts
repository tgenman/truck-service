import {Injectable} from '@angular/core';
import {ApiClientService} from '../api-client/api-client.service';
import {Driver} from '../../model/driver';
import {Observable} from 'rxjs';
import {Truck} from '../../model/truck';

@Injectable({
    providedIn: 'root'
})
export class TruckService {

    constructor(private apiClient: ApiClientService) {
    }

    public addNewTruck(driver: Truck): void {
        this.apiClient.addNewTruck(driver);
    }

    public deleteTruck(id: number): void {
        this.apiClient.deleteTruck(id);
    }

    public updateTruck(updatableTruck: Truck) {
        this.apiClient.updateTruck(updatableTruck);
    }

    public getTruckById(id: number): Observable<Truck> {
        return this.apiClient.getTruckById(id);
    }
}
