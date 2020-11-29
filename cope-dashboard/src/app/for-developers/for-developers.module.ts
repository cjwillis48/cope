import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ForDevelopersRoutingModule } from './for-developers-routing.module';
import { NzGridModule } from "ng-zorro-antd/grid";
import { MenuComponent } from "./components/menu/menu.component";
import { SpringBootStarterCopeComponent } from './components/spring-boot-starter-cope/spring-boot-starter-cope.component';
import { ArchitectureComponent } from './components/architecture/architecture.component';
import { PrescriptionReportingComponent } from './components/prescription-reporting/prescription-reporting.component';
import { AcknowledgementsComponent } from './components/acknowledgements/acknowledgements.component';
import { ForDevelopersComponent } from "./for-developers.component";
import { NzDividerModule } from "ng-zorro-antd/divider";
import { NzSpaceModule } from "ng-zorro-antd/space";
import { NzLayoutModule } from "ng-zorro-antd/layout";
import { NzMenuModule } from "ng-zorro-antd/menu";
import { RouterModule } from "@angular/router";
import { HIGHLIGHT_OPTIONS, HighlightModule } from "ngx-highlightjs";
import { NzTableModule } from "ng-zorro-antd/table";


@NgModule({
  declarations: [
    ForDevelopersComponent,
    MenuComponent,
    SpringBootStarterCopeComponent,
    ArchitectureComponent,
    PrescriptionReportingComponent,
    AcknowledgementsComponent
  ],
  imports: [
    HighlightModule,
    CommonModule,
    RouterModule,
    ForDevelopersRoutingModule,
    NzGridModule,
    NzSpaceModule,
    NzDividerModule,
    NzLayoutModule,
    NzMenuModule,
    NzTableModule
  ],
  providers: [
    {
      provide: HIGHLIGHT_OPTIONS,
      useValue: {
        fullLibraryLoader: () => import('highlight.js'),
      }
    }
  ]
})
export class ForDevelopersModule {
}
