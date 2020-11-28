import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ViewAcknowledgementsComponent } from "./components/view-acknowledgements/view-acknowledgements.component";

const routes: Routes = [{ path: '', component: ViewAcknowledgementsComponent }];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AcknowledgementRoutingModule {
}
