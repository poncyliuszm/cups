import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class UtilsComponent {

  constructor() {
  }

  compareObjects(o1: any, o2: any): boolean {
    return o1.id === o2.id;
  }
}
