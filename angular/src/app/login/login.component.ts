import { Component }    from '@angular/core';
import { Router }       from '@angular/router';
import { Observable }   from 'rxjs/Rx';
import { LoginService } from './login.service';
import { User }         from '../model/user';

@Component({
  selector: 'my-login',
  templateUrl: './login.html',
  providers: [LoginService]
})
export class LoginComponent {
  user: User = new User();
  constructor(public router: Router ,private loginService: LoginService) {}

  login(event, username, password) {
    this.user.login = username;
    this.user.pass = password;
    event.preventDefault();
    this.loginService.login(this.user).then(
      response => {
        if(response.json().token === undefined)
          alert(response.json().error);
        else{
          localStorage.setItem('user', JSON.stringify(response.json()));
          this.router.navigateByUrl('/home');
        }
      },
      error => {alert(error);}
    );
  }
}
