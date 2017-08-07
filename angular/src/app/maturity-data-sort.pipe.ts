import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: "maturityDataSort" })
export class MaturityDataSortPipe implements PipeTransform {
  transform(array: any[], args: string): any[] {
    array.sort((a: any, b: any) => {
      console.log(a.date);
      console.log(b.date);
      if (a.date < b.date) {
        return 1;
      } else if (a.date > b.date) {
        return -1;
      } else {
        return 0;
      }
    });
    return array;
  }
}