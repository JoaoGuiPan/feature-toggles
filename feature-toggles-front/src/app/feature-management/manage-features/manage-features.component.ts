import { Component, OnInit } from '@angular/core';
import { FeatureToggleListItem } from 'src/app/model/feature-toggle.model';

@Component({
  selector: 'app-manage-features',
  templateUrl: './manage-features.component.html',
  styleUrls: ['./manage-features.component.scss']
})
export class ManageFeaturesComponent implements OnInit {

  selectedFeature: FeatureToggleListItem | null = null;

  constructor() { }

  ngOnInit(): void {
  }

}
