import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";


declare const FB:any;

@Component({
    selector: 'facebook-login',
    templateUrl: 'facebook-login.html',
})

export class FacebookLoginComponent implements OnInit {

    constructor() {
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
            // connect here with your server for facebook login by passing access token given by facebook
            console.log(resp);
        }else if (resp.status === 'not_authorized') {
            
        }else {
            
        }
    };
    ngOnInit() {
        FB.getLoginStatus(response => {
            this.statusChangeCallback(response);
        });

        FB.api('/me?access_token=EAAU4cVPebncBAIl4ctWGvF4ve6dxDuE3U3SHwbZAOzzRCgH7DqYZCu7NgllMNhEtvVdaHk24LhZBKtdeqyajsFhmSZCwKbEsuCDIGb37VTxsohvEGxWztycMZCGZB8xZBkZC2R9P52nkgZCZCT8ajlwW4Ua0DkLbVnqLAWX1rcrqOcRZAmAJPp6hC38cDZBrpncrIrcZD', response => {
            console.log(response);
        });
    }
}