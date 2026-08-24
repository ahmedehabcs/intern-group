import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Navbar } from '../customer-layout/components/navbar/navbar';

@Component({
  selector: 'app-checkout-layout',
  imports: [RouterOutlet, Navbar],
  templateUrl: './checkout-layout.html',
})
export class CheckoutLayout {}
