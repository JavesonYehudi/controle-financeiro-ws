import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router } 								from '@angular/router';

import { FinancialTransaction } 				from '../model/financial-transaction';
import { EFinancialTransactionType }			from '../model/e-financial-transaction-type';
import { Maturity }				 				from '../model/maturity';
import { MaturityView, Transaction } 			from '../model/maturity-view';

import { FinancialTransactionService } 			from '../financial-transaction/financial-transaction.service';

@Component({
    selector: 'time-line',
    templateUrl: './time-line.html',
    styleUrls: ['./time-line.css'],
    encapsulation: ViewEncapsulation.None,
    providers : [ FinancialTransactionService ]
})

export class TimelineComponent implements OnInit {
	financialTransactions: FinancialTransaction[];
	maturityViewList = new Array<MaturityView>();
	EFinancialTransactionType = EFinancialTransactionType;

	constructor(
		private router: Router, 
		private financialTransactionService: FinancialTransactionService) {}

	ngOnInit(): void {
		this.getFinancialTransaction();
	}

   	getFinancialTransaction(): void {
		this.financialTransactionService.getFunds().then(financialTransactions => {
			this.financialTransactions = financialTransactions;
			financialTransactions.forEach(financialTransaction => {
				this.transactionsToMaturytiView(financialTransaction);
				console.log(financialTransaction);
			})
		});
	}

	transactionsToMaturytiView(financialTransaction: FinancialTransaction){

		financialTransaction.maturityList.forEach(maturity => {
			if(this.maturityViewList.some(maturityView => maturityView.date == maturity.date)){

			} else{
				let transaction = new Transaction();
				//transaction.id = financialTransaction.id;
				transaction.description = financialTransaction.description;
				transaction.value = maturity.value;
				transaction.type = financialTransaction.financialTransactionType;

				let maturityView = new MaturityView();
				maturityView.date = maturity.date;
				maturityView.transactions.push(transaction);
				this.maturityViewList.push(maturityView);
			}
		});
	}
}