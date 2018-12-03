import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiClientService} from '../api-client/api-client.service';
import {Driver} from '../../model/driver';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DriverService {

    constructor(private apiClient: ApiClientService) {
    }

    public addNewDriver(driver: Driver): void {
      this.apiClient.addNewDriver(driver);
    }

    public deleteDriver(id: number): void {
        this.apiClient.deleteDriver(id);
    }

    public updateDriver(updatableDriver: Driver) {
        this.apiClient.updateDriver(updatableDriver);
    }

    public getDriverById(id: number): Observable<Driver> {
        return this.apiClient.getDriverById(id);
    }
}
