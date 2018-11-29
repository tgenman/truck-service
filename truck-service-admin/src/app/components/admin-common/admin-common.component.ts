import { Component, OnInit } from '@angular/core';
import {LoaderService} from '../../services/loader/loader.service';
import {Cargo} from '../../model/cargo';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
const BASE_URL = 'http://localhost:8080/api';


@Component({
  selector: 'app-admin-common',
  templateUrl: './admin-common.component.html',
  styleUrls: ['./admin-common.component.css']
})
export class AdminCommonComponent implements OnInit {

    cargoes: Cargo[];

  constructor(private loader: LoaderService, private http: HttpClient) { }

  ngOnInit() {
  }

  public getCargoes(): void {
      console.log('admin-common: getCargoes() clicked');
      this.loader.getCargoes(this.cargoes);
      console.log(this.cargoes);
  }

}
