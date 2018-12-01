import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {Cargo} from '../../model/cargo';
import {catchError, map, share, tap} from 'rxjs/operators';
import {Observable, of} from 'rxjs';
import {Driver} from '../../model/driver';
import {Customer} from '../../model/customer';
import {Order} from '../../model/order';
import {RoutePoint} from '../../model/routepoint';
import {Shift} from '../../model/shift';
import {Tempshift} from '../../model/tempshift';
import {Truck} from '../../model/truck';

@Injectable({
    providedIn: 'root'
})
export class LoaderService {

    BASE_URL = 'http://localhost:8080/api';

    constructor(private httpClient: HttpClient) {
    }


    public getCargoes(): Observable<Cargo[]> {
        console.log('loader getCargoes');
        return this.httpClient.get(`${this.BASE_URL}/cargo/list`)
            .pipe(
                map(response => {
                    share();
                    return response as Cargo[];
                })
            );
    }

    public getDrivers(): Observable<Driver[]> {
        console.log('loader getDrivers');
        return this.httpClient.get(`${this.BASE_URL}/driver/list`)
            .pipe(
                map(response => {
                    share();
                    console.log(response);
                    return response as Driver[];
                })
            );
    }

    public getCustomers(): Observable<Customer[]> {
        return this.httpClient.get(`${this.BASE_URL}/customer/list`)
            .pipe(
                map(response => {
                    share();
                    return response as Customer[];
                })
            );
    }

    public getOrders(): Observable<Order[]> {
        return this.httpClient.get(`${this.BASE_URL}/order/list`)
            .pipe(
                map(response => {
                    share();
                    console.log(response);
                    return response as Order[];
                })
            );
    }

    public getRoutePoints(): Observable<RoutePoint[]> {
        return this.httpClient.get(`${this.BASE_URL}/route-point/list`)
            .pipe(
                map(response => {
                    share();
                    console.log(response);
                    return response as RoutePoint[];
                })
            );
    }

    public getShifts(): Observable<Shift[]> {
        return this.httpClient.get(`${this.BASE_URL}/shift/list`)
            .pipe(
                map(response => {
                    share();
                    console.log(response);
                    return response as Shift[];
                })
            );
    }

    public getTempShifts(): Observable<Tempshift[]> {
        return this.httpClient.get(`${this.BASE_URL}/temp-shift/list`)
            .pipe(
                map(response => {
                    share();
                    console.log(response);
                    return response as Tempshift[];
                })
            );
    }

    public getTrucks(): Observable<Truck[]> {
        return this.httpClient.get(`${this.BASE_URL}/truck/list`)
            .pipe(
                map(response => {
                    share();
                    console.log(response);
                    return response as Truck[];
                })
            );
    }


    private handleError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {
            // TODO: better job of transforming error for user consumption
            console.log(`${operation} failed: ${error.message}`);

            // Let the app keep running by returning an empty result.
            return of(result as T);
        };
    }

}
