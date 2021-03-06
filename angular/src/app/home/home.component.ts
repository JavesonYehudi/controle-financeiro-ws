import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router } 						        from '@angular/router';

import { LoginService } 				        from '../login/login.service';

@Component({
    selector: 'my-home',
    templateUrl: './home.html',
    styleUrls: ['./home.css'],
    encapsulation: ViewEncapsulation.None,
    providers : [ LoginService ]
})
export class HomeComponent implements OnInit{

	constructor(
		private router: Router, 
		private loginService: LoginService) {}

    logout(){
        this.loginService.logout();
        this.router.navigateByUrl('/login');
    }

	ngOnInit(): void {	}

}