import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AppCommonModule } from '../common/app-common.module';
import { ListFeaturesComponent } from './list-features/list-features.component';
import { EditFeatureComponent } from './edit-feature/edit-feature.component';
import { ManageFeaturesComponent } from './manage-features/manage-features.component';

const routes: Routes = [
  {
    path: '',
    component: ManageFeaturesComponent,
  }
];

@NgModule({
  declarations: [
    ListFeaturesComponent,
    EditFeatureComponent,
    ManageFeaturesComponent,
  ],
  imports: [
    AppCommonModule,
    RouterModule.forChild(routes)
  ]
})
export class FeatureManagementModule { }
