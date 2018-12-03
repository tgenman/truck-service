import {Injectable, Input} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';
import {Cargo} from '../../model/cargo';
import {
    catchError,
    delay,
    delayWhen,
    exhaustMap,
    map,
    mergeMap,
    retry,
    retryWhen,
    share, shareReplay, startWith,
    switchMap,
    tap,
    timeInterval
} from 'rxjs/operators';
import {interval, Observable, of, timer} from 'rxjs';
import {Driver} from '../../model/driver';
import {Customer} from '../../model/customer';
import {Order} from '../../model/order';
import {RoutePoint} from '../../model/routepoint';
import {Shift} from '../../model/shift';
import {Tempshift} from '../../model/tempshift';
import {Truck} from '../../model/truck';
import {tryCatch} from 'rxjs/internal-compatibility';
import {City} from '../../model/city';

@Injectable({
    providedIn: 'root'
})
export class ApiClientService {

    BASE_URL = 'http://localhost:8080/api';

    httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };


    constructor(private httpClient: HttpClient) {
    }

    public addNewTruck(truck: Truck) {
        console.log('Send truck ' + JSON.stringify(truck));
        this.httpClient.put(`${this.BASE_URL}/truck/new`, truck, this.httpOptions)
            .subscribe(res => console.log(res));
    }

    public deleteTruck(id: number) {
        const url = `${this.BASE_URL}/truck/${id}`;
        console.log(url);
        return this.httpClient.delete<Truck>(url, this.httpOptions)
            .subscribe(res => console.log(res));
    }

    public updateTruck(truck: Truck) {
        this.httpClient.post(`${this.BASE_URL}/truck/update`, truck, this.httpOptions)
            .subscribe(res => console.log(res));
    }

    public getTruckById(id: number): Observable<Truck> {
        return  this.httpClient
            .get(`${this.BASE_URL}/truck/${id}`)
            .pipe(
                map(res => {
                    console.log('getTruckById ' + JSON.stringify(res));
                    return res as Truck;
                })
            );
    }



    public addNewDriver(driver: Driver) {
        console.log('Send ' + JSON.stringify(driver));
        this.httpClient.put(`${this.BASE_URL}/driver/new`, driver, this.httpOptions)
            .subscribe(res => console.log(res));
    }

    public deleteDriver(id: number) {
        const url = `${this.BASE_URL}/driver/${id}`;
        console.log(url);
        return this.httpClient.delete<Driver>(url, this.httpOptions)
            .subscribe(res => console.log(res));
    }

    public updateDriver(driver: Driver) {
        console.log('Update ' + JSON.stringify(driver));
        this.httpClient.post(`${this.BASE_URL}/driver/update`, driver, this.httpOptions)
            .subscribe(res => console.log(res));
    }

    public getDriverById(id: number): Observable<Driver> {
        return  this.httpClient
            .get(`${this.BASE_URL}/driver/${id}`)
            .pipe(
                map(res => {
                    console.log('getDriverById ' + JSON.stringify(res));
                    return res as Driver;
                })
            );
    }

    public getCities(): Observable<City[]> {
        return  this.httpClient
            .get(`${this.BASE_URL}/city/list`)
            .pipe(
                map(res => {
                    console.log('getCities ' + res);
                    return res as City[];
                })
            );
    }

    public getCargoes(): Observable<Cargo[]> {
        return interval(7000).pipe(
            startWith(0),
            tap(() => console.log('execute getCargoes()')),
            switchMap(() => this.httpClient
                .get(`${this.BASE_URL}/cargo/list`)),
            map(res => {
                console.log('Empty ' + res);
                return res as Cargo[];
            }),
            shareReplay(),
            retryWhen(err => {
                return err.pipe(
                    tap(() => console.log('retrying cargoes request...'))
                );
            })
        );
    }

    public getDrivers(): Observable<Driver[]> {
        return interval(7000).pipe(
            startWith(0),
            tap(() => console.log('execute getDrivers()')),
            switchMap(() => this.httpClient
                .get(`${this.BASE_URL}/driver/list`)),
            map(res => {
                console.log('Drivers ' + JSON.stringify(res));
                return res as Driver[];
            }),
            shareReplay(),
            retryWhen(err => {
                return err.pipe(
                    tap(() => console.log('retrying drivers request...'))
                );
            })
        );
    }

    public getCustomers(): Observable<Customer[]> {
        return interval(7000).pipe(
            startWith(0),
            tap(() => console.log('execute getCustomers()')),
            switchMap(() => this.httpClient
                .get(`${this.BASE_URL}/customer/list`)),
            map(res => {
                console.log('Empty ' + res);
                return res as Customer[];
            }),
            shareReplay(),
            retryWhen(err => {
                return err.pipe(
                    tap(() => console.log('retrying customers request...'))
                );
            })
        );
    }

    public getOrders(): Observable<Order[]> {
        return interval(7000).pipe(
            startWith(0),
            tap(() => console.log('execute getOrders()')),
            switchMap(() => this.httpClient
                .get(`${this.BASE_URL}/order/list`)),
            map(res => {
                console.log('Empty ' + res);
                return res as Order[];
            }),
            shareReplay(),
            retryWhen(err => {
                return err.pipe(
                    tap(() => console.log('retrying orders request...'))
                );
            })
        );
    }

    public getRoutePoints(): Observable<RoutePoint[]> {
        return interval(7000).pipe(
            startWith(0),
            tap(() => console.log('execute getRoutePoints()')),
            switchMap(() => this.httpClient
                .get(`${this.BASE_URL}/route-point/list`)),
            map(res => {
                console.log('Empty ' + res);
                return res as RoutePoint[];
            }),
            shareReplay(),
            retryWhen(err => {
                return err.pipe(
                    tap(() => console.log('retrying route points request...'))
                );
            })
        );
    }

    public getShifts(): Observable<Shift[]> {
        return interval(7000).pipe(
            startWith(0),
            tap(() => console.log('execute getShifts()')),
            switchMap(() => this.httpClient
                .get(`${this.BASE_URL}/shift/list`)),
            map(res => {
                console.log('Empty ' + res);
                return res as Shift[];
            }),
            shareReplay(),
            retryWhen(err => {
                return err.pipe(
                    tap(() => console.log('retrying shifts request...'))
                );
            })
        );
    }

    public getTempShifts(): Observable<Tempshift[]> {
        return interval(7000).pipe(
            startWith(0),
            tap(() => console.log('execute getTempShifts()')),
            switchMap(() => this.httpClient
                .get(`${this.BASE_URL}/temp-shift/list`)),
            map(res => {
                console.log('Empty ' + res);
                return res as Tempshift[];
            }),
            shareReplay(),
            retryWhen(err => {
                return err.pipe(
                    tap(() => console.log('retrying tempshifts request...'))
                );
            })
        );
    }

    public getTrucks(): Observable<Truck[]> {
        return interval(7000).pipe(
            startWith(0),
            tap(() => console.log('execute getTrucks()')),
            switchMap(() => this.httpClient
                .get(`${this.BASE_URL}/truck/list`)),
            map(res => {
                console.log('Empty ' + res);
                return res as Truck[];
            }),
            shareReplay(),
            retryWhen(err => {
                return err.pipe(
                    tap(() => console.log('retrying trucks request...'))
                );
            })
        );
    }

}
