import { NgModule } from '@angular/core';
import { LoaderService } from './providers/loader.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { RequestAppInterceptorService } from './providers/request-app-interceptor.service';
import { AuthenticationService } from './providers/authentication.service';
import { FeatureService } from './providers/feature.service';
import { AuthGuard } from './providers/auth.guard';

export const httpInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: RequestAppInterceptorService, multi: true },
];

@NgModule({
  declarations: [],
  imports: [],
  providers: [
    AuthGuard,
    LoaderService,
    httpInterceptorProviders,
    AuthenticationService,
    FeatureService
  ]
})
export class CoreModule { }
