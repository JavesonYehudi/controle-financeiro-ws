// Imports
import { NgModule }                 	 from '@angular/core';
import { ModuleWithProviders }  		 from '@angular/core';
import { Routes, RouterModule } 		 from '@angular/router';

import { HomeComponent } 				 from './home/home.component';
import { FundsComponent } 				 from './funds/funds.component';
import { TimelineComponent } 			 from './timeline/time-line.component';
import { LoginComponent } 				 from './login/login.component';
import { FinancialTransactionComponent } from './financial-transaction/financial-transaction.component'

import { CanActivateViaOAuthGuard } 	 from './oAuth.canActivateGuard';

// Route Configuration
export const appRoutes: Routes = [
	{ path: '', component: HomeComponent , canActivate : [CanActivateViaOAuthGuard], children: 
		[
			{ path: '', component: TimelineComponent, canActivate : [CanActivateViaOAuthGuard] },
			{ path: 'funds', component: FundsComponent, canActivate : [CanActivateViaOAuthGuard], children:
				[ 
					{ path: 'new', component: FundsComponent, canActivate : [CanActivateViaOAuthGuard] }
				]
			},
			{ path: 'transaction', component: FinancialTransactionComponent, canActivate : [CanActivateViaOAuthGuard] }
    	]
    },
	{ path: 'login', component: LoginComponent },
	{ path: '',  redirectTo: '/',  pathMatch: 'full' },
];

@NgModule({
  imports: [ RouterModule.forRoot(appRoutes) ],
  exports: [ RouterModule ]
})

export class AppRoutesModule {}