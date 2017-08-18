import { Injectable }                                                        from '@angular/core';
import { Http , RequestOptions , Response, RequestMethod, Request, Headers } from '@angular/http';
import { Observable }                                                        from 'rxjs/Observable';
import { environment }                                                       from '../../environments/environment'

import { FinancialTransaction }                                              from '../model/financial-transaction';

import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';

@Injectable()
export class FinancialTransactionService {
  private financialTransactionUrl = `${environment.rootPath}/financial-transaction`;

  constructor(private http: Http) {}

  getFinancialTransactions() : Observable<FinancialTransaction[]> {
    var requestoptions = new RequestOptions({
      method: RequestMethod.Get,
      url: this.financialTransactionUrl
    });

    return this.http.request(new Request(requestoptions)).map(this.handleData).catch(this.handleError);
  }

  private handleData(res: Response): FinancialTransaction[] {
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