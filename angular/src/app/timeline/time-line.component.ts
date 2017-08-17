import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router } 								from '@angular/router';
import { Observable }   						from 'rxjs/Observable';

import { FinancialTransaction } 				from '../model/financial-transaction';
import { EFinancialTransactionType }			from '../model/e-financial-transaction-type';
import { Maturity }				 				from '../model/maturity';
import { TimelineItem, TimeLineDateItems } 		from '../model/timeline-item';

import { TimelineService } 			from '../timeline/time-line.service'

@Component({
    selector: 'time-line',
    templateUrl: './time-line.html',
    styleUrls: ['./time-line.css'],
    encapsulation: ViewEncapsulation.None,
    providers : [ TimelineService ]
})

export class TimelineComponent implements OnInit {
	timelineItems: Observable<TimelineItem[]>;
	EFinancialTransactionType = EFinancialTransactionType;

	constructor(
		private router: Router, 
		private timelineService: TimelineService) {}

	ngOnInit(): void {
		this.getFinancialTransaction();
	}

   	getFinancialTransaction(): void {
		this.timelineItems = this.timelineService.getTimelineItens("2017-08-01", "2017-08-31");
	}
}