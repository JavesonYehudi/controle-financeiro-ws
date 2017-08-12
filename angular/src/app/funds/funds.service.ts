import { Injectable }                                                        from '@angular/core';
import { Http , RequestOptions , Response, RequestMethod, Request, Headers } from '@angular/http';
import { Observable }                                                        from 'rxjs/Observable';
import { environment }                                                       from '../../environments/environment'

import { Funds }                                                             from '../model/funds';

import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';

@Injectable()
export class FundsService {
  private fundsUrl = environment.rootPath + '/funds';

  constructor(private http: Http) {}

  getFunds() : Promise<Funds[]> {
    var requestoptions = new RequestOptions({
      method: RequestMethod.Get,
      url: this.fundsUrl
    });

    return this.http.request(new Request(requestoptions)).toPromise().then(handleData);
  }
}

function handleData(res: Response): Funds[] {
  return res.json();
}