import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-search',
  imports: [],
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
export class SearchComponent {
  searchString: string='';
  @Output() searchQuery: EventEmitter<string>;
  constructor() {
    this.searchQuery = new EventEmitter();
  }
  emitSearchQuery() {
    console.log(this.searchString);
    this.searchQuery.emit(this.searchString);
  }

}
