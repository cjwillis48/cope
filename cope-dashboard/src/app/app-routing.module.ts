import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { OktaCallbackComponent } from '@okta/okta-angular';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { OKTA_CONFIG, OktaAuthModule } from '@okta/okta-angular';
import { AuthInterceptor } from './shared/interceptors/auth.interceptor';
import { ServiceTestComponent } from "./temp/components/service-test/service-test.component";
import { environment } from "../environments/environment";
import { LoginCallbackComponent } from "./shared/components/login-callback-component/login-callback.component";

const oktaConfig = {
  issuer: 'https://dev-5317498.okta.com/oauth2/default',
  redirectUri: environment.redirectUri,
  postLogoutRedirectUri: environment.postLogoutRedirectUri,
  clientId: '0oad7wxuKwAtxoU9o5d5',
  scopes: ['openid', 'profile', 'email']
};

const routes: Routes = [
  {
    path: 'home',
    loadChildren: () => import('./prescription/home.module').then(m => m.HomeModule)
  },
  {
    path: 'acknowledgement',
    loadChildren: () => import('./acknowledgement/acknowledgement.module').then(m => m.AcknowledgementModule)
  },
  {
    path: 'prescription',
    loadChildren: () => import('./prescription/tracking.module').then(m => m.TrackingModule)
  },
  {
    path : 'questionnaire',
    loadChildren: () => import('./questionnaire/questionnaire.module').then(m => m.QuestionnaireModule)
  },
  {
    path: 'service-test',
    component: ServiceTestComponent
  },
  {
    path: 'callback',
    component: LoginCallbackComponent
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
    OktaAuthModule,
    RouterModule.forRoot(routes)
  ],
  providers: [
    { provide: OKTA_CONFIG, useValue: oktaConfig },
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
