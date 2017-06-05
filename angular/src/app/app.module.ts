import { BrowserModule }                 from '@angular/platform-browser';
import { NgbModule } 				             from '@ng-bootstrap/ng-bootstrap';
import { NgModule }                      from '@angular/core';
import { FormsModule }                   from '@angular/forms';
import { HttpModule, XHRBackend }        from '@angular/http';
import { ExtendedXHRBackend }            from './extended-xhr-backend'

import { AppComponent }                  from './app.component';
import { AppRoutesModule }               from './app.routes.module';
import { LoginComponent }                from './login/login.component';
import { HomeComponent }                 from './home/home.component';
import { FundsComponent }                from './funds/funds.component';
import { FinancialTransactionComponent } from './financial-transaction/financial-transaction.component';
import { MyCalendarComponent }           from './calendar/my-calendar.component';
import { FacebookLoginComponent }        from './login/facebook-login.component';


import { CanActivateViaOAuthGuard }      from './oAuth.canActivateGuard';

import { CalendarComponent }             from "ap-angular2-fullcalendar/src/calendar/calendar";
import { ScheduleModule }                from 'primeng/primeng';
import { DialogModule }                  from 'primeng/primeng';


@NgModule({
  declarations: [ 
    AppComponent, 
    HomeComponent, 
    FundsComponent,
    LoginComponent,
    CalendarComponent,
    MyCalendarComponent,
    FacebookLoginComponent,
    FinancialTransactionComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutesModule,
    NgbModule.forRoot(),
    ScheduleModule,
    DialogModule
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
