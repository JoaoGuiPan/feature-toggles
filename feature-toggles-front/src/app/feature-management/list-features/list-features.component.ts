import { Component, Output, AfterViewInit, ViewChild, OnDestroy } from '@angular/core';
import { FeatureToggleListItem } from 'src/app/model/feature-toggle.model';
import { FeatureService } from 'src/app/core/providers/feature.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { UntypedFormControl } from '@angular/forms';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';

@Component({
  selector: 'app-list-features',
  templateUrl: './list-features.component.html',
  styleUrls: ['./list-features.component.scss']
})
export class ListFeaturesComponent implements AfterViewInit, OnDestroy {

  readonly displayedColumns: string[] = ['name', 'displayName'];

  dataSource: MatTableDataSource<FeatureToggleListItem> = new MatTableDataSource();

  @ViewChild(MatPaginator) paginator: MatPaginator | null = null;
  @ViewChild(MatSort) sort: MatSort | null = null;

  @Output()
  selectedFeature = new Subject<FeatureToggleListItem>();

  selected: FeatureToggleListItem | null = null;

  filterCtrl = new UntypedFormControl();

  features: FeatureToggleListItem[] = [];

  destroy = new Subject<void>();

  constructor(private featureService: FeatureService) {
    this.filterCtrl.valueChanges
    .pipe(takeUntil(this.destroy))
    .subscribe((value: string) => this.filterFeatures(value));
  }

  ngAfterViewInit() {
    this.list();
  }

  ngOnDestroy(): void {
    this.destroy.next();
    this.destroy.complete();
  }

  list() {
    this.featureService.list()
    .pipe(
      takeUntil(this.destroy)
    )
    .subscribe(featureList => {
      Object.assign(this.features, featureList);

      this.dataSource = new MatTableDataSource<FeatureToggleListItem>(featureList);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;

      this.select(featureList[0]);
    })
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
        technicalName: '',
        displayName: ''
      }
    );
  }

  select(feature: FeatureToggleListItem) {
    this.selected = feature;
    this.selectedFeature.next(this.selected);
  }
}
