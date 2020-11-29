import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { environment } from "../environments/environment";


const routes: Routes = [
  {
    path: 'home',
    loadChildren: () => import('./home/home.module').then(m => m.HomeModule)
  },
  {
    path: 'acknowledgement',
    loadChildren: () => import('./acknowledgement/acknowledgement.module').then(m => m.AcknowledgementModule)
  },
  {
    path: 'prescription',
    loadChildren: () => import('./prescription/prescription.module').then(m => m.PrescriptionModule)
  },
  {
    path: '**',
    redirectTo: '/home'
  }
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    HttpClientModule,
    RouterModule.forRoot(routes)
  ],
  providers: [

  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
