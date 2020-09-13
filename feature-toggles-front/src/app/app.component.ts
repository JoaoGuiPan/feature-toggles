import { Component } from '@angular/core';
import { AuthenticationService } from './core/providers/authentication.service';
import { environment } from '../environments/environment';
import { Router } from '@angular/router';

const { docUrls } = environment;

interface MenuItem {
  icon: string;
  name: string;
  title: string;
  url: string;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'header.title';

  menuItems: MenuItem[] = [
    {
      icon: 'home',
      name: 'home.title',
      title: 'header.title',
      url: 'home'
    },
    {
      icon: 'toggle_on',
      name: 'feature.menu',
      title: 'feature.title',
      url: 'feature-toggles'
    }
  ];

  constructor(public authentication: AuthenticationService, private router: Router) { }

  openApiDocs() {
    window.open(docUrls, '_blank');
  }
}
