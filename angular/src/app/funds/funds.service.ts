import { Injectable }                                                        from '@angular/core';
import { Http , RequestOptions , Response, RequestMethod, Request, Headers } from '@angular/http';
import { Observable }                                                        from 'rxjs/Observable';
import { environment }                                                       from '../../environments/environment'

import { Funds }                                                             from '../model/funds';

@Injectable()
export class FundsService {
  private fundsUrl = `${environment.rootPath}/funds`;

  constructor(private http: Http) {}

  getFunds() : Observable<Funds[]> {
    var requestoptions = new RequestOptions({
      method: RequestMethod.Get,
      url: this.fundsUrl
    });

    return this.http.request(new Request(requestoptions)).map(this.handleData).catch(this.handleError);
  }

  private handleData(res: Response): Funds[] {
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