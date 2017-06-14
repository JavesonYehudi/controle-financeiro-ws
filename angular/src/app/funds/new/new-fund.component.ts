import { Component, OnInit } 			from '@angular/core';
import { Router } 						from '@angular/router';

import { Funds }	  					from '../../model/funds'
import { FundsService }					from '../funds.service'

@Component({
    selector: 'new-fund',
    templateUrl: './new-fund.html',
    providers : [ FundsService ]
})

export class NewFundComponent implements OnInit{
	fund = new Funds();

	constructor(
		private router: Router, 
		private fundsService: FundsService) {}
	
	ngOnInit(): void {
	}

}