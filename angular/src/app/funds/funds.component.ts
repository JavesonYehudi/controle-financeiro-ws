import { Component, OnInit } 	from '@angular/core';
import { Router } 				from '@angular/router';

import { Funds } 				from '../model/funds';
import { EFundsType } 			from '../model/e-funds-type';

import { FundsService } 		from './funds.service';

@Component({
    selector: 'my-funds',
    templateUrl: './funds.component.html',
    providers : [ FundsService ]
})
export class FundsComponent implements OnInit{
	funds:Funds[];
	EFundsType = EFundsType;

	constructor(
		private router: Router, 
		private fundsService: FundsService) {}
	
	ngOnInit(): void {
		this.getFunds();
	}

	getFunds(): void {
		this.fundsService.getFunds().then(funds => this.funds = funds);
	}
}