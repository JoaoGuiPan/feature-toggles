import { COMMA, ENTER } from '@angular/cdk/keycodes';
import { Component, OnInit, Input, Output, OnDestroy } from '@angular/core';
import { FeatureToggleListItem, FeatureToggle } from 'src/app/model/feature-toggle.model';
import { FeatureService } from 'src/app/core/providers/feature.service';
import { MatChipInputEvent } from '@angular/material/chips';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';

@Component({
  selector: 'app-edit-feature',
  templateUrl: './edit-feature.component.html',
  styleUrls: ['./edit-feature.component.scss']
})
export class EditFeatureComponent implements OnInit, OnDestroy {

  readonly separatorKeysCodes: number[] = [ENTER, COMMA];
  readonly minDate = new Date()

  feature: FeatureToggle | null;

  @Output()
  saved = new Subject<FeatureToggle>();

  destroy = new Subject<void>();

  constructor(private featureService: FeatureService, private router: Router) { }

  ngOnInit() {
  }

  ngOnDestroy(): void {
    this.destroy.next();
    this.destroy.complete();
  }

  @Input()
  set selectedFeature(feature: FeatureToggleListItem) {
    if (feature) {
      if (feature.id) {
        this.featureService.getById(feature.id)
        .pipe(takeUntil(this.destroy))
        .subscribe(f => this.feature = f);
      } else {
        this.feature = new FeatureToggle();
      }
    }
  }

  get customerIdsEmpty() {
    return !this.feature || !this.feature.customerIds.length;
  }

  get isExpired() {

    if (this.feature) {
      const expiresOn = new Date(this.feature.expiresOn).getTime();
      return new Date().getTime() >= expiresOn;
    }

    return false;
  }

  addCustomer(event: MatChipInputEvent): void {
    const value = event.value;

    if ((value || '').trim()) {
      this.feature.customerIds.push(value.trim());
    }

    event.chipInput.clear();
  }

  removeCustomer(customer: string) {
    const index = this.feature.customerIds.indexOf(customer);

    if (index >= 0) {
      this.feature.customerIds.splice(index, 1);
    }
  }

  save() {
    this.featureService.save(this.feature)
      .pipe(takeUntil(this.destroy))
      .subscribe(() => this.saved.next(this.feature));
  }

  archive() {
    this.featureService.archive(this.feature.id)
      .pipe(takeUntil(this.destroy))
      .subscribe(() => this.saved.next(this.feature));
  }

}
