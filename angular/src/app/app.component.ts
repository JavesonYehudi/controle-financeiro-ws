import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
	constructor(){
		localStorage.setItem('url', 'http://localhost:8080/controle-financeiro-ws/ws');
	}
}
