import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PrescriptionRoutingModule } from './prescription-routing.module';
import { ViewPrescriptionsComponent } from './components/view-prescriptions/view-prescriptions.component';
import { NzTableModule } from "ng-zorro-antd/table";
import { NzButtonModule } from "ng-zorro-antd/button";


@NgModule({
  declarations: [ViewPrescriptionsComponent],
  imports: [
    CommonModule,
    PrescriptionRoutingModule,
    NzTableModule,
    NzButtonModule
  ]
})
export class PrescriptionModule {
}
