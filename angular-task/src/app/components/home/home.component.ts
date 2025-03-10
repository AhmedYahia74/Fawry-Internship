import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ProductCardComponent } from '../product-card/product-card.component';
import  product  from '../../../../public/db.json';
import { Product } from '../../models/Product';
import { NavBarComponent } from '../nav-bar/nav-bar.component';
import { SearchComponent } from '../search/search.component';
@Component({
  selector: 'app-home',
  imports: [NavBarComponent,FormsModule,ProductCardComponent,SearchComponent,NavBarComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  products: Product[]=product;
  tempProducts: Product[]=product;
  filteredProducts(searchQuery : any){
    this.products = this.tempProducts;
    this.products = this.products.filter((product)=>{
      return product.name?.toLocaleLowerCase().startsWith(searchQuery.toLowerCase());
    });
  }
}
