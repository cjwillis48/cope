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
import { NavbarComponent } from './shared/components/navbar/navbar.component';
import { NzMenuModule } from "ng-zorro-antd/menu";
import { en_US, NZ_I18N } from "ng-zorro-antd/i18n";
import { NzLayoutModule } from "ng-zorro-antd/layout";
import { ObjectViewerModal } from './shared/components/object-viewer-modal/object-viewer-modal.component';
import { NzButtonModule } from "ng-zorro-antd/button";
import { NzModalModule } from "ng-zorro-antd/modal";
import { NzTableModule } from "ng-zorro-antd/table";

registerLocaleData(en);

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    ObjectViewerModal
  ],
  imports: [
    BrowserModule,
    CommonModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    AcknowledgementModule,
    NzMenuModule,
    NzLayoutModule,
    HttpClientModule,
    NzButtonModule,
    NzModalModule,
    NzTableModule
  ],
  providers: [{ provide: NZ_I18N, useValue: en_US }],
  bootstrap: [AppComponent]
})
export class AppModule { }
