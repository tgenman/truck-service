import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {Driver} from '../../model/driver';
import {LoaderService} from '../../services/loader/loader.service';
import {DriverService} from '../../services/driver.service';

@Component({
  selector: 'app-driver-table',
  templateUrl: './driver-table.component.html',
  styleUrls: ['./driver-table.component.css']
})
export class DriverTableComponent implements OnInit {

  constructor(private driverService: DriverService) { }

  drivers$: Observable<Driver[]>;
  interval: any;

  ngOnInit() {
    this.driverService.driversData$.subscribe(data => this.drivers$ = data);
    this.refreshDate();
    this.interval = setInterval(() => {
      this.refreshDate(); }, 5000);
  }

  public refreshDate() {
    this.driverService.updateData();
  }

}
