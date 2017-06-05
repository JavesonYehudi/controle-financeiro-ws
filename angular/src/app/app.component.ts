import { Component } 				from '@angular/core';
import { CanActivateViaOAuthGuard } from './oAuth.canActivateGuard'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
	constructor(private canActivateViaOAuthGuard:CanActivateViaOAuthGuard){}

	isActive(): boolean{
		return this.canActivateViaOAuthGuard.canActivate();
	}
}
