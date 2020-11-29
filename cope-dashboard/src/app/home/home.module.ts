import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home.component';
import { HomeRoutingModule } from './home-routing.module';
import { NzGridModule } from "ng-zorro-antd/grid";


@NgModule({
  declarations: [HomeComponent],
  imports: [
    CommonModule,
    HomeRoutingModule,
    NzGridModule,
    NzGridModule,
    NzGridModule
  ]
})
export class HomeModule {
}
