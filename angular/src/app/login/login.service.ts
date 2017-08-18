import { Injectable }                                                        from '@angular/core';
import { Router }                                                            from '@angular/router';
import { Http , RequestOptions , Response, RequestMethod, Request, Headers } from '@angular/http';
import { Observable }                                                        from 'rxjs/Observable';
import { User }                                                              from '../model/user';
import { environment }                                                       from '../../environments/environment'

import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';

@Injectable()
export class LoginService {
  private OauthLoginEndPointUrl = `${environment.rootPath}/user`;  // Oauth Login EndPointUrl to web API

  constructor(private router: Router, private http: Http) {}

  login(user: User) : Observable<any> {
    var requestoptions = new RequestOptions({
      method: RequestMethod.Post,
      url: `${this.OauthLoginEndPointUrl}/login`,
      body: JSON.stringify(user)
    });

    return this.http.request(new Request(requestoptions)).map(this.handleData).catch(this.handleError);

  }

  loginFacebook(user: User) : Observable<any> {
    var requestoptions = new RequestOptions({
      method: RequestMethod.Post,
      url: `${this.OauthLoginEndPointUrl}/facebook-login/${user.connections[0].id}`,
      body: JSON.stringify(user)
    });

    return this.http.request(new Request(requestoptions)).map(this.handleData).catch(this.handleError);

  }

  private handleData(res: Response) {
    let body = res.json();
    return JSON.stringify(body);
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
     localStorage.removeItem("user");
  }
}