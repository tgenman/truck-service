
import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable()
export class XhrInterceptor implements HttpInterceptor {

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        console.log('interceptor');
        const currentUser = JSON.parse(localStorage.getItem('currentUser'));
        console.log(currentUser);
        if (currentUser) {
            req = req.clone({
                headers: req.headers.set('authorization', `Basic ${currentUser}`)
            });
        }
        return next.handle(req);
    }
}
