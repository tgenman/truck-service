import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {Cargo} from '../../model/cargo';
import {Observable, of} from 'rxjs';
import {catchError, map, tap} from 'rxjs/operators';

const BASE_URL = 'http://localhost:8080/api';

@Injectable({
    providedIn: 'root'
})
export class LoaderService {


    constructor(private http: HttpClient) {
    }

    public getCargoes(cargoes) {
        console.log('loader getCargoes');
        this.http.get<Cargo[]>(`${BASE_URL}/cargo/list`)
            .subscribe(updated_cargoes => {
                cargoes = updated_cargoes;
            });
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
