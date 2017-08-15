import { Injectable }   from '@angular/core';
import { 
	Http, 
	RequestOptions, 
	Response, 
	RequestMethod, 
	Request, 
	Headers, 
	URLSearchParams }   from '@angular/http';
import { Observable }   from 'rxjs/Observable';
import { environment }  from '../../environments/environment'

import { TimelineItem } from '../model/timeline-item'


import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';

@Injectable()
export class TimelineService {
	private timeLineUrl = environment.rootPath + "/timeline";

	constructor(private http:Http){}

	getTimelineItens(start: string, end: string) : Promise<TimelineItem[]> {
		let params: URLSearchParams = new URLSearchParams();
		params.set("date.start", start);
		params.set("date.end", end);

		var requestOptions = new RequestOptions({
			method: RequestMethod.Get,
			url: this.timeLineUrl,
			params: params
		});

		return this.http.request(new Request(requestOptions)).toPromise().then(handleData);
	}

}

function handleData(res: Response): TimelineItem[] {
  return res.json();
}