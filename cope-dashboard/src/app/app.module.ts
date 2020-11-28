import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CommonModule, registerLocaleData } from '@angular/common';
import en from '@angular/common/locales/en';
import { AcknowledgementModule } from './acknowledgement/acknowledgement.module';
import {TrackingModule} from "./tracking/tracking.module";
import { NavbarComponent } from './shared/components/navbar/navbar.component';
import { NzMenuModule } from "ng-zorro-antd/menu";
import { en_US, NZ_I18N } from "ng-zorro-antd/i18n";
import { NzLayoutModule } from "ng-zorro-antd/layout";
import { ServiceTestComponent } from './temp/components/service-test/service-test.component';
import { NzGridModule } from "ng-zorro-antd/grid";
import {QuestionnaireModule} from "./questionnaire/questionnaire.module"
import { NzButtonModule } from 'ng-zorro-antd/button';
import { LoginCallbackComponent } from './shared/components/login-callback-component/login-callback.component';
import { QuestionnaireComponent } from './questionnaire/components/questionnaire/questionnaire.component';

registerLocaleData(en);

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    ServiceTestComponent,
    LoginCallbackComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    TrackingModule,
    AcknowledgementModule,
    QuestionnaireModule,
    NzMenuModule,
    NzLayoutModule,
    NzGridModule,
    NzButtonModule,
    HttpClientModule,
    // AuthRoutingModule
  ],
  providers: [{ provide: NZ_I18N, useValue: en_US }],
  bootstrap: [AppComponent]
})
export class AppModule { }
