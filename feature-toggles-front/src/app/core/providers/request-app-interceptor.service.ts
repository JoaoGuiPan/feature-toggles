import { Injectable, Inject } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { LoaderService } from './loader.service';
import {Observable, throwError, of} from 'rxjs';
import {catchError, tap} from 'rxjs/operators';
import { SESSION_STORAGE, StorageService } from 'ngx-webstorage-service';
import { AuthenticationService } from './authentication.service';
import { CONSTANTS } from 'src/app/common/constants';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

// TODO i18n
const INVALID_PARAMETERS = 'Invalid parameters. Please, try again.';
const INVALID_CREDENTIALS = 'Invalid credentials. Please, Login again.';
const INTERNAL_ERROR = 'Internal Error. If the error persists, please contact the administrators.';
const CLOSE_BTN = 'x';

@Injectable({
  providedIn: 'root'
})
export class RequestAppInterceptorService implements HttpInterceptor {

  constructor(@Inject(SESSION_STORAGE) private storageService: StorageService, private snackBar: MatSnackBar,
              private loaderService: LoaderService, private authentication: AuthenticationService,
              private router: Router) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    this.loaderService.show();

    const authHeader = this.storageService.get(CONSTANTS.authorizationHeader);

    if (authHeader) {
      request = request.clone({
        setHeaders: {
          [CONSTANTS.authorizationHeader]: authHeader
        }
      });
    }

    return next.handle(request)
    .pipe(
      tap(event => {
        if (event instanceof HttpResponse) {
          this.loaderService.hide();
        }
        return event;
      }),
      catchError((error: HttpErrorResponse) => {
        return this.handleAuthError(error);
      })
    );
  }

  private handleAuthError(error: HttpErrorResponse): Observable<any> {
    if (error.status === 401 || error.status === 403) {
      this.snackBar.open(INVALID_CREDENTIALS, CLOSE_BTN);
      this.authentication.logout();
      this.router.navigateByUrl('login');
      return of(error.message);
    } else {
      if (error.status === 400) {
        this.snackBar.open(INVALID_PARAMETERS, CLOSE_BTN);
      } else {
        this.snackBar.open(INTERNAL_ERROR, CLOSE_BTN);
      }
      this.loaderService.hide();
      return throwError(() => error);
    }
  }

}
