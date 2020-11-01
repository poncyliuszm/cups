import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class CupService {

  constructor(private http: HttpClient) {
  }

  findCups(pageNumber, pageSize) {
    return this.http.get(environment.appContext + '/cups', {
      params: new HttpParams()
        .set('page', pageNumber.toString())
        .set('size', pageSize.toString())
    });
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

  getRepeatCups() {
    return this.http.get(environment.appContext + '/cups/repeat');
  }
}
