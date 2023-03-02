import { Component, OnInit, Inject } from '@angular/core';
import { AuthenticationService } from '../core/providers/authentication.service';
import { Router } from '@angular/router';
import { SESSION_STORAGE, StorageService } from 'ngx-webstorage-service';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  user = '';
  password = '';

  destroy = new Subject<void>();

  constructor(@Inject(SESSION_STORAGE) private storageService: StorageService,
              private authentication: AuthenticationService, private router: Router) { }

  ngOnInit(): void {
    this.storageService.clear();
  }

  login() {
    this.authentication.login(this.user, this.password)
    .pipe(takeUntil(this.destroy))
    .subscribe(() => this.router.navigateByUrl('home'));
  }

}
