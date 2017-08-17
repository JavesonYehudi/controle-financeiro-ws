import { Component, OnInit } 			from '@angular/core';
import { Router } 						from '@angular/router';
import { Observable }           		from 'rxjs/Observable';

import { FinancialTransaction } 		from '../model/financial-transaction';
import { EFinancialTransactionType }	from '../model/e-financial-transaction-type';

import { FinancialTransactionService } 	from './financial-transaction.service';

@Component({
    selector: 'my-transactions',
    templateUrl: './financial-transaction.html',
    providers : [ FinancialTransactionService ]
})
export class FinancialTransactionComponent implements OnInit{
	financialTransactions: Observable<FinancialTransaction[]>;
	EFinancialTransactionType = EFinancialTransactionType;

	constructor(
		private router: Router, 
		private financialTransactionService: FinancialTransactionService) {}
	
	ngOnInit(): void {
		this.getFinancialTransaction();
	}

	getFinancialTransaction(): void {
		this.financialTransactions = this.financialTransactionService.getFinancialTransactions();
	}
}