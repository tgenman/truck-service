import {Injectable, Input} from '@angular/core';
import {HttpClient} from '@angular/common/http';
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

@Injectable({
    providedIn: 'root'
})
export class LoaderService {

    BASE_URL = 'http://localhost:8080/api';

    constructor(private httpClient: HttpClient) {
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
                console.log('Empty ' + res);
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
