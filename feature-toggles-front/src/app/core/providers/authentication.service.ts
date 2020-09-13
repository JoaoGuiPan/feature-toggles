import { Injectable, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import {StorageService, SESSION_STORAGE} from 'ngx-webstorage-service';
import { BaseUrls } from 'src/app/model/base-urls.model';
import { CONSTANTS } from 'src/app/common/constants';
import { Router } from '@angular/router';

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
    this.storageService.set(CONSTANTS.authenticationHeader, `Basic ${encondedAuth}`);
    this.storageService.set(CONSTANTS.loggedUser, user);
    return this.http.get(loginUrls.root);
  }

  logout() {
    this.storageService.clear();
    this.router.navigateByUrl('login');
  }
}
