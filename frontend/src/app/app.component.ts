import { Component } from '@angular/core';
import { EventEmitter, Output } from '@angular/core';
import { User } from 'src/app/dtos/User.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'proyecto-web';
}
