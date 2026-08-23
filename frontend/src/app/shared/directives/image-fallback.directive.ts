import { Directive, HostListener } from '@angular/core';

@Directive({ selector: 'img[appImageFallback]' })
export class ImageFallbackDirective {
  @HostListener('error', ['$event']) onError(event: Event): void {
    const image = event.target as HTMLImageElement;
    const fallback = '/assets/images/talabaty-food-table.png';
    if (!image.src.endsWith(fallback)) image.src = fallback;
  }
}
