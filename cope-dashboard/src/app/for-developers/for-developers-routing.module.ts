import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ForDevelopersComponent } from "./for-developers.component";
import { ArchitectureComponent } from "./components/architecture/architecture.component";
import { PrescriptionReportingComponent } from "./components/prescription-reporting/prescription-reporting.component";
import { AcknowledgementsComponent } from "./components/acknowledgements/acknowledgements.component";
import { SpringBootStarterCopeComponent } from "./components/spring-boot-starter-cope/spring-boot-starter-cope.component";


const routes: Routes = [
  {
    path: 'home', component: ForDevelopersComponent, children: [
      { path: 'architecture', component: ArchitectureComponent, outlet: 'content' },
      { path: 'acknowledgements', component: AcknowledgementsComponent, outlet: 'content' },
      { path: 'prescription-reporting', component: PrescriptionReportingComponent, outlet: 'content' },
      { path: 'spring-boot-starter-cope', component: SpringBootStarterCopeComponent, outlet: 'content' }
    ]
  },
  {
    path: '**',
    redirectTo: '/for-developers/home/(content:architecture)'
  }
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ForDevelopersRoutingModule {
}
