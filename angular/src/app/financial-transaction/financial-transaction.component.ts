import { Component, OnInit } 			from '@angular/core';
import { Router } 						from '@angular/router';

import { FinancialTransaction } 		from '../model/financial-transaction';
import { EFinancialTransactionType }	from '../model/e-financial-transaction-type';

import { FinancialTransactionService } 	from './financial-transaction.service';

@Component({
    selector: 'my-transactions',
    templateUrl: './financial-transaction.component.html',
    providers : [ FinancialTransactionService ]
})
export class FinancialTransactionComponent implements OnInit{
	financialTransactions: FinancialTransaction[];
	EFinancialTransactionType = EFinancialTransactionType;

	constructor(
		private router: Router, 
		private financialTransactionService: FinancialTransactionService) {}
	
	ngOnInit(): void {
		this.getFinancialTransaction();
	}

	getFinancialTransaction(): void {
		this.financialTransactionService.getFunds().then(financialTransactions => this.financialTransactions = financialTransactions);
	}
}