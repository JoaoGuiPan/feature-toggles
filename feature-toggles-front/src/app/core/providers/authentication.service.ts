import { Injectable, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { StorageService, SESSION_STORAGE } from 'ngx-webstorage-service';
import { BaseUrls } from 'src/app/model/base-urls.model';
import { CONSTANTS } from 'src/app/common/constants';
import { Router } from '@angular/router';
import { tap } from 'rxjs/operators';

const loginUrls = new BaseUrls('auth');

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(@Inject(SESSION_STORAGE) private storageService: StorageService,
              private http: HttpClient, private router: Router) { }

  get userLoggedIn() {
    return this.storageService.get(CONSTANTS.loggedUser);
  }

  login(user: string, password: string) {

    const encondedAuth = btoa(`${user}:${password}`);
    const authHeader = `Basic ${encondedAuth}`;

    return this.http.get(loginUrls.root, { headers: { [CONSTANTS.authorizationHeader]: authHeader } })
    .pipe(
      tap(() => {
        this.storageService.set(CONSTANTS.authorizationHeader, authHeader);
        this.storageService.set(CONSTANTS.loggedUser, user);
      })
    );
  }

  logout() {
    this.storageService.clear();
    this.router.navigateByUrl('login');
  }
}
