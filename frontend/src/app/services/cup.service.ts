import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class CupService {

  constructor(private http: HttpClient) {
  }

  list() {
    return this.http.get(environment.appContext + '/cups')
  }

  getCup(cupId: any) {
    return this.http.get(environment.appContext + '/cups/' + cupId);
  }

  getLastNameOfCategory(categoryId: any) {
    return this.http.get(environment.appContext + '/cups/lastNameOfCategory/' + categoryId, {responseType: "text"})
  }

  addCup(cup: any) {
    return this.http.post(environment.appContext + '/cups', cup);
  }


  editCup(cup: any) {
    return this.http.put(environment.appContext + '/cups/' + cup.id, cup);
  }

  deleteCup(cup: any) {
    return this.http.delete(environment.appContext + '/cups/' + cup.id);
  }
}
