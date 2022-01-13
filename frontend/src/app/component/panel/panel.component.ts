import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/dtos/User.model';
import { UserService } from 'src/app/services/users/user.service';

@Component({
  selector: 'app-panel',
  templateUrl: './panel.component.html',
  styleUrls: ['./panel.component.css']
})
export class PanelComponent implements OnInit {

  user: User;

  constructor(
    private usuariosSrv: UserService,
  ) { }

  opcion: String = "publications"

  ngOnInit(): void {
    this.user = this.usuariosSrv.getUserLoggedIn();
  }

  selectMain(){
    
  }

}
