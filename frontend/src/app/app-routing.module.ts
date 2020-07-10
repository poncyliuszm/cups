import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CupListComponent} from "./cups/cup-list/cup-list.component";
import {HomeComponent} from "./home/home.component";
import {RepeatComponent} from "./repeat/repeat.component";
import {CupAddComponent} from "./cups/cup-add/cup-add.component";
import {CupPreviewComponent} from "./cups/cup-preview/cup-preview.component";
import {CupEditComponent} from "./cups/cup-edit/cup-edit.component";
import {PractiseComponent} from "./practise/practise.component";
import {SettingsComponent} from "./settings/settings.component";


const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'cups', component: CupListComponent},
  {path: 'cups/preview/:id', component: CupPreviewComponent},
  {path: 'cups/add', component: CupAddComponent},
  {path: 'cups/edit/:id', component: CupEditComponent},
  {path: 'practise', component: PractiseComponent},
  {path: 'repeat', component: RepeatComponent},
  {path: 'settings', component: SettingsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
