import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ViewPrescriptionsComponent } from "./components/view-prescriptions/view-prescriptions.component";

const routes: Routes = [{ path: '', component: ViewPrescriptionsComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PrescriptionRoutingModule {
}
