import { Component, OnInit } 			from '@angular/core';
import { Router } 						from '@angular/router';

import { FinancialTransaction } 		from '../model/financial-transaction';
import { EFinancialTransactionType }	from '../model/e-financial-transaction-type';

import { LoginService } 				from '../login/login.service';
import { FinancialTransactionService } 	from '../financial-transaction/financial-transaction.service';

@Component({
    selector: 'my-home',
    templateUrl: './home.html',
    styleUrls: ['./home.css'],
    providers : [ LoginService, FinancialTransactionService ]
})
export class HomeComponent implements OnInit{
	financialTransactions: FinancialTransaction[];
	EFinancialTransactionType = EFinancialTransactionType;

	constructor(
		private router: Router, 
		private loginService: LoginService,
		private financialTransactionService: FinancialTransactionService) {}

    logout(){
        this.loginService.logout();
        this.router.navigateByUrl('/login');
    }

	ngOnInit(): void {
		this.getFinancialTransaction();
	}

   	getFinancialTransaction(): void {
		this.financialTransactionService.getFunds().then(financialTransactions => this.financialTransactions = financialTransactions);
	}
}