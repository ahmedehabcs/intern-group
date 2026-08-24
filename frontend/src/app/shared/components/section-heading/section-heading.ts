import { Component, input } from '@angular/core';
@Component({
  selector: 'app-section-heading',
  templateUrl: './section-heading.html',
})
export class SectionHeading {
  readonly title = input.required<string>();
  readonly eyebrow = input('');
  readonly description = input('');
}
