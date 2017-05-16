import { Injectable }                                                        from '@angular/core';
import { Http , RequestOptions , Response, RequestMethod, Request, Headers } from '@angular/http';
import { Observable }                                                        from 'rxjs/Rx';
import { User }                                                              from '../model/user';
import { environment }                                                       from '../../environments/environment'

import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';

@Injectable()
export class LoginService {
  private OauthLoginEndPointUrl = environment.rootPath + '/user/log-in';  // Oauth Login EndPointUrl to web API
  user: User = new User();

  constructor(public http: Http) {}

  login(user: User) : Promise<any> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    var requestoptions = new RequestOptions({
      method: RequestMethod.Post,
      url: this.OauthLoginEndPointUrl,
      body: JSON.stringify(user),
      headers: headers
    });

    return this.http.request(new Request(requestoptions)).toPromise();

  }

  private handleData(res: Response) {
    let body = res.json();
    return body;
  }

  private handleError (error: any) {
    // In a real world app, we might use a remote logging infrastructure
    // We'd also dig deeper into the error to get a better message
    let errMsg = (error.message) ? error.message :
    error.status ? `${error.status} - ${error.statusText}` : 'Server error';
    console.error(errMsg); // log to console instead
    return Observable.throw(errMsg);
  }

  public logout() {
     localStorage.removeItem('token');
  }
}