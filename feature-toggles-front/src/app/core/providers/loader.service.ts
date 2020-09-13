import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoaderService {

  isLoading = new Subject<boolean>();

  mustShow = true;

  show() {
    this.isLoading.next(true && this.mustShow);
  }

  hide() {
    this.isLoading.next(false);
  }

  setMustShow(mustShow: boolean) {
    this.mustShow = mustShow;
  }
}
