import { NgModule }                      from '@angular/core';
import { HttpModule, XHRBackend }        from '@angular/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { BrowserModule }                 from '@angular/platform-browser';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {
  MdAutocompleteModule,
  MdButtonModule,
  MdButtonToggleModule,
  MdCardModule,
  MdCheckboxModule,
  MdChipsModule,
  MdCoreModule,
  MdDatepickerModule,
  MdDialogModule,
  MdExpansionModule,
  MdGridListModule,
  MdIconModule,
  MdInputModule,
  MdListModule,
  MdMenuModule,
  MdNativeDateModule,
  MdPaginatorModule,
  MdProgressBarModule,
  MdProgressSpinnerModule,
  MdRadioModule,
  MdRippleModule,
  MdSelectModule,
  MdSidenavModule,
  MdSliderModule,
  MdSlideToggleModule,
  MdSnackBarModule,
  MdSortModule,
  MdTableModule,
  MdTabsModule,
  MdToolbarModule,
  MdTooltipModule,
} from '@angular/material';
import {CdkTableModule} from '@angular/cdk';
import 'hammerjs';

import { ExtendedXHRBackend }            from './extended-xhr-backend'

import { AppComponent }                  from './app.component';
import { AppRoutesModule }               from './app.routes.module';
import { HomeComponent }                 from './home/home.component';
import { FundsComponent }                from './funds/funds.component';

import { LoginComponent }                from './login/login.component';
import { TimelineComponent }             from './timeline/time-line.component';
import { FacebookLoginComponent }        from './login/facebook-login/facebook-login.component';
import { FinancialTransactionComponent } from './financial-transaction/financial-transaction.component';

import { CanActivateViaOAuthGuard }      from './oAuth.canActivateGuard';

@NgModule({
  exports: [
    BrowserAnimationsModule,
    CdkTableModule,
    MdAutocompleteModule,
    MdButtonModule,
    MdButtonToggleModule,
    MdCardModule,
    MdCheckboxModule,
    MdChipsModule,
    MdCoreModule,
    MdDatepickerModule,
    MdDialogModule,
    MdExpansionModule,
    MdGridListModule,
    MdIconModule,
    MdInputModule,
    MdListModule,
    MdMenuModule,
    MdNativeDateModule,
    MdPaginatorModule,
    MdProgressBarModule,
    MdProgressSpinnerModule,
    MdRadioModule,
    MdRippleModule,
    MdSelectModule,
    MdSidenavModule,
    MdSliderModule,
    MdSlideToggleModule,
    MdSnackBarModule,
    MdSortModule,
    MdTableModule,
    MdTabsModule,
    MdToolbarModule,
    MdTooltipModule,
  ]
})

export class MaterialModule {}

@NgModule({
  declarations: [ 
    AppComponent, 
    HomeComponent, 
    FundsComponent,
    LoginComponent,
    TimelineComponent,
    FacebookLoginComponent,
    FinancialTransactionComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutesModule,
    MaterialModule,
    MdNativeDateModule,
    ReactiveFormsModule,
  ],
  providers: [
    { provide: XHRBackend, useClass: ExtendedXHRBackend },
    CanActivateViaOAuthGuard 
  ],
  bootstrap: [ 
    AppComponent
  ]
})
export class AppModule { }
