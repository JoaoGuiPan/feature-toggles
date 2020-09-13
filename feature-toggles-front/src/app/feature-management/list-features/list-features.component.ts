import { Component, Output, AfterViewInit, ViewChild } from '@angular/core';
import { FeatureToggleListItem } from 'src/app/model/feature-toggle.model';
import { FeatureService } from 'src/app/core/providers/feature.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { FormControl } from '@angular/forms';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-list-features',
  templateUrl: './list-features.component.html',
  styleUrls: ['./list-features.component.scss']
})
export class ListFeaturesComponent implements AfterViewInit {

  readonly displayedColumns: string[] = ['name', 'displayName'];

  dataSource: MatTableDataSource<FeatureToggleListItem>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  @Output()
  selectedFeature = new Subject<FeatureToggleListItem>();

  selected: FeatureToggleListItem | null;

  filterCtrl = new FormControl();

  features: FeatureToggleListItem[] = [];

  constructor(private featureService: FeatureService) {
    this.filterCtrl.valueChanges.subscribe((value: string) => this.filterFeatures(value));
  }

  async ngAfterViewInit() {
    this.init();
  }

  filterFeatures(value: string) {

    this.dataSource.filter = value.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  create() {
    this.selectedFeature.next(
      {
        technicalName: null,
        displayName: null
      }
    );
  }

  select(feature: FeatureToggleListItem) {
    this.selected = feature;
    this.selectedFeature.next(this.selected);
  }

  async init() {
    const featureList = await this.featureService.list().toPromise();
    Object.assign(this.features, featureList);

    this.dataSource = new MatTableDataSource<FeatureToggleListItem>(featureList);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;

    this.select(featureList[0]);
  }
}
