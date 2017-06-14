import { Injectable }                                                               from '@angular/core';
import { Request, XHRBackend, BrowserXhr, ResponseOptions, XSRFStrategy, Response } from '@angular/http';
import { Router }                                                                   from '@angular/router';
import { Observable }                                                               from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';

@Injectable()
export class ExtendedXHRBackend extends XHRBackend {

  constructor(browserXhr: BrowserXhr, baseResponseOptions: ResponseOptions, xsrfStrategy: XSRFStrategy, private router: Router) {
    super(browserXhr, baseResponseOptions, xsrfStrategy);
  }

  createConnection(request: Request) {
    let user = JSON.parse(localStorage.getItem('user'));
    let token = user === null ? null : user.token;
    request.headers.set('Authorization', `${token}`); 
    request.headers.set('Content-Type', 'application/json');
    let xhrConnection = super.createConnection(request);
    xhrConnection.response = xhrConnection.response.catch((error: Response) => {
      if (error.status === 401 || error.status === 403){
        localStorage.removeItem('user');
        this.router.navigate(['login']);
      }

      return Observable.throw(error);
    });

    return xhrConnection;
  }
}