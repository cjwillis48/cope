import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AcknowledgementRoutingModule } from "./acknowledgement-routing.module";
import { ViewAcknowledgementsComponent } from "./components/view-acknowledgements/view-acknowledgements.component";
import { NzTableModule } from "ng-zorro-antd/table";


@NgModule({
  declarations: [
    ViewAcknowledgementsComponent
  ],
  imports: [
    CommonModule,
    AcknowledgementRoutingModule,
    NzTableModule
  ]
})
export class AcknowledgementModule {
}
