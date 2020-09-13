import { COMMA, ENTER } from '@angular/cdk/keycodes';
import { Component, OnInit, Input, Output } from '@angular/core';
import { FeatureToggleListItem, FeatureToggle } from 'src/app/model/feature-toggle.model';
import { FeatureService } from 'src/app/core/providers/feature.service';
import { MatChipInputEvent } from '@angular/material/chips';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import * as moment from 'moment';

@Component({
  selector: 'app-edit-feature',
  templateUrl: './edit-feature.component.html',
  styleUrls: ['./edit-feature.component.scss']
})
export class EditFeatureComponent implements OnInit {

  readonly separatorKeysCodes: number[] = [ENTER, COMMA];

  feature: FeatureToggle | null;

  @Output()
  added = new Subject<FeatureToggle>();

  constructor(private featureService: FeatureService, private router: Router) { }

  ngOnInit() {
  }

  @Input()
  set selectedFeature(feature: FeatureToggleListItem) {
    if (feature) {

      if (feature.id) {
        this.featureService.getById(feature.id).toPromise()
          .then(f => this.feature = f)
          .catch(err => console.error(err));

      } else {

        this.newFeature();
      }

    }
  }

  get customerIdsEmpty() {
    return !this.feature || !this.feature.customerIds.length;
  }

  get isExpired() {

    if (this.feature) {
      const expiresOn = moment(this.feature.expiresOn).startOf('day');
      return !moment().startOf('day').diff(expiresOn);
    }

    return false;
  }

  addCustomer(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;

    if ((value || '').trim()) {
      this.feature.customerIds.push(value.trim());
    }

    if (input) {
      input.value = '';
    }
  }

  removeCustomer(customer: string) {
    const index = this.feature.customerIds.indexOf(customer);

    if (index >= 0) {
      this.feature.customerIds.splice(index, 1);
    }
  }

  save() {
    if (this.feature.id) {
      this.featureService.update(this.feature.id, this.feature).toPromise()
        .then(_ => this.added.next(this.feature))
        .catch(err => console.error(err));
    } else {
      this.featureService.create(this.feature).toPromise()
        .then(_ => this.added.next(this.feature))
        .catch(err => console.error(err));
    }
  }

  archive() {
    this.featureService.archive(this.feature.id).toPromise()
      .then(_ => this.added.next(this.feature))
      .catch(err => console.error(err));
  }

  private newFeature() {
    this.feature = {
      technicalName: null,
      displayName: null,
      expiresOn: null,
      description: null,
      inverted: false,
      customerIds: [],
    };
  }

}
