import { NgModule } from '@angular/core';

// *************** FORM CONTROLS ***************
import {MatNativeDateModule} from '@angular/material/core';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';

// *************** NAVIGATION ***************
import {MatMenuModule} from '@angular/material/menu';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatToolbarModule} from '@angular/material/toolbar';

// *************** LAYOUT ***************
import {MatCardModule} from '@angular/material/card';
import {MatDividerModule} from '@angular/material/divider';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatListModule} from '@angular/material/list';

// *************** BUTTONS & INDICATORS ***************
import {MatButtonModule} from '@angular/material/button';
import {MatBadgeModule} from '@angular/material/badge';
import {MatChipsModule} from '@angular/material/chips';
import {MatIconModule} from '@angular/material/icon';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';

// *************** POPUPS & MODALS ***************
import {MatSnackBarModule} from '@angular/material/snack-bar';

// *************** DATA TABLE ***************
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSortModule} from '@angular/material/sort';
import {MatTableModule} from '@angular/material/table';

const MaterialModules = [
  // MatAutocompleteModule,
  MatCheckboxModule,
  MatDatepickerModule,
  MatNativeDateModule,
  MatFormFieldModule,
  MatInputModule,
  // MatRadioModule,
  MatSelectModule,
  // MatSliderModule,
  // MatSlideToggleModule,
  MatMenuModule,
  MatSidenavModule,
  MatToolbarModule,
  MatCardModule,
  MatDividerModule,
  MatExpansionModule,
  MatGridListModule,
  MatListModule,
  // MatStepperModule,
  // MatTabsModule,
  // MatTreeModule,
  MatButtonModule,
  // MatButtonToggleModule,
  MatBadgeModule,
  MatChipsModule,
  MatIconModule,
  MatProgressSpinnerModule,
  // MatProgressBarModule,
  // MatRippleModule,
  // MatBottomSheetModule,
  // MatDialogModule,
  MatSnackBarModule,
  // MatTooltipModule,
  MatPaginatorModule,
  MatSortModule,
  MatTableModule,
  // DragDropModule,
];

@NgModule({
  imports: [MaterialModules],
  exports: [MaterialModules],
})
export class MaterialModule {}
