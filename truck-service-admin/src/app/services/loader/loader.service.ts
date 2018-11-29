import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {Cargo} from '../../model/cargo';
import {catchError, map, share, tap} from 'rxjs/operators';
import {Observable, of} from 'rxjs';
import {Driver} from '../../model/driver';

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
                    return response as Driver[];
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
