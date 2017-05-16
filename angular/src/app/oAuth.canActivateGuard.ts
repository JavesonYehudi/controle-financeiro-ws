import { Injectable } from '@angular/core';
import { CanActivate , Router} from '@angular/router';

@Injectable()
export class CanActivateViaOAuthGuard implements CanActivate {
	constructor(public router : Router) {}
	
	canActivate() {
		console.log(JSON.parse(localStorage.getItem("user")) === null || JSON.parse(localStorage.getItem("user")).token === undefined);
		if(JSON.parse(localStorage.getItem("user")) === null || JSON.parse(localStorage.getItem("user")).token === undefined)
			this.router.navigateByUrl('/login');

		return !(JSON.parse(localStorage.getItem("user")) === null) && !(JSON.parse(localStorage.getItem("user")).token === undefined);
	}
}