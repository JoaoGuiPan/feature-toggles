import { NgModule } from '@angular/core';
import { Routes, RouterModule, Router, NavigationStart, NavigationEnd, NavigationError } from '@angular/router';

import { LoaderService } from './core/providers/loader.service';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { AuthGuard } from './core/providers/auth.guard';


const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'feature-toggles',
    loadChildren: () => import('./feature-management/feature-management.module').then(m => m.FeatureManagementModule),
    canActivate: [AuthGuard]
  },
  {
    path: '**',
    redirectTo: 'home',
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {})],
  exports: [RouterModule]
})
export class AppRoutingModule {
  constructor(router: Router, loaderService: LoaderService) {
    router.events.subscribe(event => {
      if (event instanceof NavigationStart) {
        loaderService.show();
      } else if (event instanceof NavigationEnd) {
        loaderService.hide();
      } else if (event instanceof NavigationError) {
        loaderService.hide();
      }
    });
  }
}
