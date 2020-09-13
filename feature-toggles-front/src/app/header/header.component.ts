import { Component, OnInit, Output, Input } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { AuthenticationService } from '../core/providers/authentication.service';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  @Input()
  title = 'header.title';

  @Output()
  menuBtnClicked = new Subject<boolean>();

  constructor(private translateService: TranslateService,
              public authentication: AuthenticationService) { }

  ngOnInit(): void {
  }

  changeLanguage(lang: string) {
    this.translateService.setDefaultLang(lang);
  }

  logout() {
    this.authentication.logout();
  }

  openMenu() {
    this.menuBtnClicked.next(true);
  }
}
