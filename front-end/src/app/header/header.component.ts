import { MatToolbar } from '@angular/material/toolbar'
import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatBadgeModule } from '@angular/material/badge';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    RouterModule, CommonModule,
    MatBadgeModule, MatButtonModule, MatMenuModule, MatIconModule, MatToolbar
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit {

  isLogin: boolean = false;
  unReadNotifications: number = 10;

  constructor(){}

  ngOnInit(): void {
    this.isLogin = true;
  }

  async logout() {
    this.isLogin = false;
  }

  readNotifications() {
    this.unReadNotifications--;
  }

}
