import { Component, Host, HostListener, Input } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Product } from '../../models/Product';
import { DescShorterPipe } from '../../pipes/desc-shorter.pipe';
@Component({
  selector: 'app-product-card',
  imports: [DescShorterPipe,RouterOutlet],
  templateUrl: './product-card.component.html',
  styleUrl: './product-card.component.css'
})
export class ProductCardComponent {
  @Input() product!: Product;
}
