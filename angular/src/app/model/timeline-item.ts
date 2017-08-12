export class TimelineItem {
	date: Date;
	transactions = new Array<Transaction>();
}

export class Transaction {
	id: string;
	description: string;
	value: number;
	type: number;
}