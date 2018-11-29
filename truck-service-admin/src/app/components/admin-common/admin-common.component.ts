import { Component, OnInit } from '@angular/core';
import {LoaderService} from '../../services/loader/loader.service';
import {Cargo} from '../../model/cargo';
import {Observable, of} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {catchError} from 'rxjs/operators';
import {Driver} from '../../model/driver';
const BASE_URL = 'http://localhost:8080/api';


@Component({
  selector: 'app-admin-common',
  templateUrl: './admin-common.component.html',
  styleUrls: ['./admin-common.component.css']
})
export class AdminCommonComponent implements OnInit {

    public cargoes$: Observable<Cargo[]>;
    public drivers$: Observable<Driver[]>;

  constructor(private loader: LoaderService, private http: HttpClient) { }

  ngOnInit() {
      this.cargoes$ = this.loader.getCargoes();
      this.drivers$ = this.loader.getDrivers();
  }

}
