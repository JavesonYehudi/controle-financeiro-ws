import { Component }    from '@angular/core';
import { Router }       from '@angular/router';
import { Observable }   from 'rxjs/Rx';
import { LoginService } from './login.service';
import { User }         from '../model/user';

@Component({
  selector: 'my-login',
  templateUrl: './login.html',
  styleUrls: ['./login.css'],
  providers: [LoginService]
})
export class LoginComponent {
  user: User = new User();
  constructor(private router: Router, private loginService: LoginService) {}

  login(event, username, password) {
    this.user.login = username;
    this.user.pass = password;
    event.preventDefault();
    this.loginService.login(this.user).then(
      response => {
        localStorage.setItem('user', this.handleData(response));
        this.router.navigate(['']);
      });
  }
  private handleData(res: Response) {
    let body = res.json();
    return JSON.stringify(body);
  }

}