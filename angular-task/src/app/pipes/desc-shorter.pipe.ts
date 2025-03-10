import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'descShorter'
})
export class DescShorterPipe implements PipeTransform {

  transform(value: any): string | null {
    if(value)
    return value.substring(0,10)+"...etc";

    return null;
  }

}
