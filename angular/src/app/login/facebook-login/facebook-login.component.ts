import {Component, OnInit, NgZone}  from '@angular/core';
import {Router}             from "@angular/router";
import { LoginService }     from '../login.service';
import {User}               from "../../model/user";
import {ExternalConnection} from "../../model/external-connection";

import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';

declare const FB:any;

@Component({
    selector: 'facebook-login',
    templateUrl: 'facebook-login.html',
})

export class FacebookLoginComponent implements OnInit {

    constructor(private router: Router, private loginService: LoginService, private zone: NgZone) {
        FB.init({
            appId      : '1469434273099383',
            cookie     : false,  // enable cookies to allow the server to access the session
            xfbml      : true,  // parse social plugins on this page
            version    : 'v2.9' // use graph api version 2.9
        });

        (<any>window).angularComponentRef = {
            zone: this.zone, 
            componentFn: () => this.ngOnInit(), 
            component: this
        };
    }

    onFacebookLoginClick() {
        FB.login({ scope: 'email' });
    }


    statusChangeCallback(resp) {
        if (resp.status === 'connected') {
            let user = new User();
            FB.api('me', {fields: 'name, email'}, response => {
                user.name = response.name;
                user.email = response.email;
                user.connections[0] = new ExternalConnection(response.id.toString(), 'FACEBOOK');

                this.loginService.loginFacebook(user).then(
                    response => {
                        localStorage.setItem('user', this.handleData(response));
                        this.zone.run(() => {console.log('Outside Done!'); this.router.navigate(['/']); });
                    }
                );
            });

        }else {
            
        }
    };

    getLoginStatus(){
        FB.getLoginStatus(response => {
            this.statusChangeCallback(response);
        });
    }

    ngOnInit() {
        this.getLoginStatus();
    }

    private handleData(res: Response) {
        let body = res.json();
        return JSON.stringify(body);
    }
}