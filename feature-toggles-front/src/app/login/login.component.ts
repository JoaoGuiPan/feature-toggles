import { Component, OnInit, Inject } from '@angular/core';
import { AuthenticationService } from '../core/providers/authentication.service';
import { Router } from '@angular/router';
import { SESSION_STORAGE, StorageService } from 'ngx-webstorage-service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  user: string | null;
  password: string | null;

  constructor(@Inject(SESSION_STORAGE) private storageService: StorageService,
              private authentication: AuthenticationService, private router: Router) { }

  ngOnInit(): void {
    this.storageService.clear();
  }

  login() {
    this.authentication.login(this.user, this.password).toPromise()
      .then(() => this.router.navigateByUrl('home'))
      .catch(err => console.error(err));
  }

}
