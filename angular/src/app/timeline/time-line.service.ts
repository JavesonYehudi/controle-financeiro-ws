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

	getTimelineItens(start: string, end: string) : Observable<TimelineItem[]> {
		let params: URLSearchParams = new URLSearchParams();
		params.set("date.start", start);
		params.set("date.end", end);

		var requestOptions = new RequestOptions({
			method: RequestMethod.Get,
			url: this.timeLineUrl,
			params: params
		});

		return this.http.request(new Request(requestOptions)).map(this.handleData).catch(this.handleError);
	}

	private handleData(res: Response): TimelineItem[] {
		return res.json();
	}

	private handleError (error: any) {
		// In a real world app, we might use a remote logging infrastructure
		// We'd also dig deeper into the error to get a better message
		let errMsg = (error.message) ? error.message :
		error.status ? `${error.status} - ${error.statusText}` : 'Server error';
		console.error(errMsg); // log to console instead
		return Observable.throw(errMsg);
  	}
}