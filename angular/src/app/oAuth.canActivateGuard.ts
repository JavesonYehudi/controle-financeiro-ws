import { Injectable } from '@angular/core';
import { CanActivate , Router} from '@angular/router';

@Injectable()
export class CanActivateViaOAuthGuard implements CanActivate {
	constructor(public router : Router) {}
	
	canActivate() {
		let user = JSON.parse(localStorage.getItem("user"));
		if(user === null || user.token === undefined)
			this.router.navigateByUrl('/login');

		return !(JSON.parse(localStorage.getItem("user")) === null) && !(JSON.parse(localStorage.getItem("user")).token === undefined);
	}
}