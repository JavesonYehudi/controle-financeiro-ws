import { Injectable } from '@angular/core';
import { CanActivate , Router} from '@angular/router';

@Injectable()
export class CanActivateViaOAuthGuard implements CanActivate {
	constructor(public router : Router) {}
	
	canActivate() {
		console.log(localStorage.getItem("token"));
		if(localStorage.getItem("token") === null){
			this.router.navigateByUrl('/login');
		}
		return (localStorage.getItem("token") === null) ? false : true;
	}
}