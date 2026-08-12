import { Component, ElementRef, QueryList, ViewChildren } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-otp-verification',
  imports: [CommonModule, FormsModule],
  templateUrl: './otp-verification.html',
  styleUrl: './otp-verification.css',
})
export class OtpVerification {
  @ViewChildren('otpInput') inputs!: QueryList<ElementRef<HTMLInputElement>>;

  readonly digits = ['', '', '', '', '', ''];
  readonly email: string;
  seconds = 118;

  constructor(
    route: ActivatedRoute,
    private readonly router: Router,
  ) {
    this.email = route.snapshot.queryParamMap.get('email') || 'a.design@studio.com';
    window.setInterval(() => {
      if (this.seconds > 0) this.seconds--;
    }, 1000);
  }

  move(index: number, event: Event): void {
    const input = event.target as HTMLInputElement;
    this.digits[index] = input.value.replace(/\D/g, '').slice(-1);
    if (this.digits[index] && index < 5) this.inputs.get(index + 1)?.nativeElement.focus();
  }

  keydown(index: number, event: KeyboardEvent): void {
    if (event.key === 'Backspace' && !this.digits[index] && index > 0) {
      this.inputs.get(index - 1)?.nativeElement.focus();
    }
  }

  verify(): void {
    this.router.navigateByUrl('/');
  }

  get time(): string {
    return `${Math.floor(this.seconds / 60).toString().padStart(2, '0')}:${(this.seconds % 60).toString().padStart(2, '0')}`;
  }
}
