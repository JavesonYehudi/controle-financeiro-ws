export class TimelineItem {
	date: Date;
	timeLineDateItems = new Array<TimeLineDateItems>();
}

export class TimeLineDateItems {
	id: string;
	description: string;
	value: number;
	type: number;
}