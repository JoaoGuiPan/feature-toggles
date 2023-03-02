import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { HeaderComponent } from './header/header.component';
import { LoaderComponent } from './loader/loader.component';
import { AppCommonModule } from './common/app-common.module';
import { CoreModule } from './core/core.module';
import { CONSTANTS } from './common/constants';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';

export function createTranslateLoader(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/');
}

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoaderComponent,
    LoginComponent,
    HomeComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: createTranslateLoader,
        deps: [HttpClient]
      },
      defaultLanguage: CONSTANTS.locale.default
    }),
    AppCommonModule,
    AppRoutingModule,
    CoreModule,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
