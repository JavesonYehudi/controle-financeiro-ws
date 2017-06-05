// Imports
import { NgModule }                 	 from '@angular/core';
import { ModuleWithProviders }  		 from '@angular/core';
import { Routes, RouterModule } 		 from '@angular/router';

import { HomeComponent } 				 from './home/home.component';
import { FundsComponent } 				 from './funds/funds.component';
import { LoginComponent } 				 from './login/login.component';
import { FinancialTransactionComponent } from './financial-transaction/financial-transaction.component'

import { CanActivateViaOAuthGuard } 	 from './oAuth.canActivateGuard';

// Route Configuration
export const appRoutes: Routes = [
	{ path: 'home', component: HomeComponent , canActivate : [CanActivateViaOAuthGuard] },
	{ path: 'funds', component: FundsComponent , canActivate : [CanActivateViaOAuthGuard] },
	{ path: 'transaction', component: FinancialTransactionComponent , canActivate : [CanActivateViaOAuthGuard] },
	{ path: 'login', component: LoginComponent },
	{ path: '',  redirectTo: '/home',  pathMatch: 'full'},
];

@NgModule({
  imports: [ RouterModule.forRoot(appRoutes) ],
  exports: [ RouterModule ]
})

export class AppRoutesModule {}