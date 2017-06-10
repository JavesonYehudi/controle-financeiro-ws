import { Component, OnInit } 			from '@angular/core';
import { Router } 						from '@angular/router';

import { FinancialTransaction } 		from '../model/financial-transaction';
import { EFinancialTransactionType }	from '../model/e-financial-transaction-type';

import { FinancialTransactionService } 	from '../financial-transaction/financial-transaction.service';

@Component({
    selector: 'time-line',
    templateUrl: './time-line.html',
    styleUrls: ['./time-line.css'],
    providers : [ FinancialTransactionService ]
})
export class TimelineComponent implements OnInit {
	financialTransactions: FinancialTransaction[];
	EFinancialTransactionType = EFinancialTransactionType;

	constructor(
		private router: Router, 
		private financialTransactionService: FinancialTransactionService) {
	}

	ngOnInit(): void {
		this.getFinancialTransaction();
	}

   	getFinancialTransaction(): void {
		this.financialTransactionService.getFunds().then(financialTransactions => this.financialTransactions = financialTransactions);
	}
}