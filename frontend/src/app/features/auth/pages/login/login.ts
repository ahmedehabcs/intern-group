import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  imports: [FormsModule, RouterLink],
  templateUrl: './login.html',
  styleUrl: './login.scss',
})
export class Login {
  showPassword = false;
  email = '';
  password = '';

  constructor(private readonly router: Router) {}

  submit(): void {
    this.router.navigateByUrl('/');
  }
}
