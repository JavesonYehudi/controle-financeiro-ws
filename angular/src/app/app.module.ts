import { NgModule }                      from '@angular/core';
import { HttpModule, XHRBackend }        from '@angular/http';
import { FormsModule }                   from '@angular/forms';
import { ExtendedXHRBackend }            from './extended-xhr-backend'
import { BrowserModule }                 from '@angular/platform-browser';
import { NgbModule }                     from '@ng-bootstrap/ng-bootstrap';

import { AppComponent }                  from './app.component';
import { AppRoutesModule }               from './app.routes.module';
import { HomeComponent }                 from './home/home.component';
import { FundsComponent }                from './funds/funds.component';
import { NewFundComponent }              from './funds/new/new-fund.component';
import { LoginComponent }                from './login/login.component';
import { TimelineComponent }             from './timeline/time-line.component';
import { MyCalendarComponent }           from './calendar/my-calendar.component';
import { FacebookLoginComponent }        from './login/facebook-login/facebook-login.component';
import { FinancialTransactionComponent } from './financial-transaction/financial-transaction.component';

import { CanActivateViaOAuthGuard }      from './oAuth.canActivateGuard';

import { ScheduleModule }                from 'primeng/primeng';
import { DialogModule }                  from 'primeng/primeng';
import { CalendarComponent }             from 'ap-angular2-fullcalendar/src/calendar/calendar';


@NgModule({
  declarations: [ 
    AppComponent, 
    HomeComponent, 
    FundsComponent,
    NewFundComponent,
    LoginComponent,
    TimelineComponent,
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
