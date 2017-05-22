import {Component, OnInit}  from '@angular/core';
import {Router}             from "@angular/router";
import { LoginService }     from './login.service';
import {User}               from "../model/user";
import {ExternalConnection} from "../model/external-connection";


declare const FB:any;

@Component({
    selector: 'facebook-login',
    templateUrl: 'facebook-login.html',
})

export class FacebookLoginComponent implements OnInit {

    constructor(private loginService: LoginService) {
        FB.init({
            appId      : '1469434273099383',
            cookie     : false,  // enable cookies to allow the server to access the session
            xfbml      : true,  // parse social plugins on this page
            version    : 'v2.9' // use graph api version 2.5
        });
    }

    onFacebookLoginClick() {
        FB.login();
    }

    statusChangeCallback(resp) {
        if (resp.status === 'connected') {
            FB.api(`me?access_token=${resp.authResponse.accessToken}`, response => {
            let user = new User();
            user.name = response.name;
            user.email = response.email;
            user.connections[0] = new ExternalConnection(response.id.toString(), 'FACEBOOK');

            this.loginService.loginFacebook(user);

        });
        }else if (resp.status === 'not_authorized') {
            
        }else {
            
        }
    };
    ngOnInit() {
        FB.getLoginStatus(response => {
            this.statusChangeCallback(response);
        });
    }
}