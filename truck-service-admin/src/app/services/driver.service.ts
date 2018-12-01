import { Injectable } from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {LoaderService} from './loader/loader.service';

@Injectable({
  providedIn: 'root'
})
export class DriverService {

  public driversData$: BehaviorSubject<any> = new BehaviorSubject({});

  updateData() {
    this.loader.getDrivers().subscribe(data => this.driversData$.next(data));
  }

  constructor(private loader: LoaderService) { }
}
