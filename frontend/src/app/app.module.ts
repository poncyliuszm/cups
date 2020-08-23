import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {MatCardModule} from '@angular/material/card';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatInputModule} from "@angular/material/input";
import {MatToolbarModule} from "@angular/material/toolbar";
import {CupListComponent, DeleteCupDialog} from './cups/cup-list/cup-list.component';
import {HomeComponent} from './home/home.component';
import {RepeatComponent} from './repeat/repeat.component';
import {HttpClientModule} from "@angular/common/http";
import {MatTableModule} from "@angular/material/table";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatButtonModule} from "@angular/material/button";
import {CupAddComponent} from './cups/cup-add/cup-add.component';
import {MatSelectModule} from "@angular/material/select";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {MatIconModule} from "@angular/material/icon";
import {CupPreviewComponent} from "./cups/cup-preview/cup-preview.component";
import {CupEditComponent} from "./cups/cup-edit/cup-edit.component";
import {MatDialogModule} from "@angular/material/dialog";
import {PractiseComponent} from './practise/practise.component';
import {SettingsComponent} from './settings/settings.component';
import {MatStepperModule} from "@angular/material/stepper";
import {CategoryListComponent, DeleteCategoryDialog} from './categories/category-list/category-list.component';
import {CategoryAddComponent} from './categories/category-add/category-add.component';
import {CategoryEditComponent} from './categories/category-edit/category-edit.component';
import {CategoryPreviewComponent} from './categories/category-preview/category-preview.component';

@NgModule({
  declarations: [
    AppComponent,
    CupListComponent,
    HomeComponent,
    RepeatComponent,
    CupPreviewComponent,
    CupAddComponent,
    CupEditComponent,
    DeleteCupDialog,
    DeleteCategoryDialog,
    PractiseComponent,
    SettingsComponent,
    CategoryListComponent,
    CategoryAddComponent,
    CategoryEditComponent,
    CategoryPreviewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatCardModule,
    MatInputModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    HttpClientModule,
    MatTableModule,
    MatCheckboxModule,
    MatButtonModule,
    MatSelectModule,
    FormsModule,
    MatSnackBarModule,
    MatIconModule,
    MatDialogModule,
    MatStepperModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
