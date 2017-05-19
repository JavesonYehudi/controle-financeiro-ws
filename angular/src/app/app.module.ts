import { BrowserModule }            from '@angular/platform-browser';
import { NgbModule } 				        from '@ng-bootstrap/ng-bootstrap';
import { NgModule }                 from '@angular/core';
import { FormsModule }              from '@angular/forms';
import { HttpModule }               from '@angular/http';

import { AppComponent }             from './app.component';
import { AppRoutesModule }          from './app.routes.module';
import { LoginComponent }           from './login/login.component';
import { HomeComponent }            from './home/home.component';
import { MyCalendarComponent }      from './calendar/my-calendar.component';
import { FacebookLoginComponent }      from './login/facebook-login.component';


import { CanActivateViaOAuthGuard } from './oAuth.canActivateGuard';

import { CalendarComponent }          from "ap-angular2-fullcalendar/src/calendar/calendar";
import { ScheduleModule }           from 'primeng/primeng';
import { DialogModule }             from 'primeng/primeng';


@NgModule({
  declarations: [ 
    AppComponent, 
    HomeComponent, 
    LoginComponent,
    CalendarComponent,
    MyCalendarComponent,
    FacebookLoginComponent
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
  providers: [ CanActivateViaOAuthGuard ],
  bootstrap: [ 
    AppComponent
  ]
})
export class AppModule { }
