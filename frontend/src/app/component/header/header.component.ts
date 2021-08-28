import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/dtos/User.model';
import { UserService } from 'src/app/services/users/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  user: User;

  constructor(
    private usuariosSrv: UserService,
    public router: Router
  ) { 
  }

  ngOnInit(): void {
    this.user = this.usuariosSrv.getUserLoggedIn();
  }

  clickedSalir() {
    this.usuariosSrv.setUserLoggedOut();
    window.location.href = '/';
  }

}
