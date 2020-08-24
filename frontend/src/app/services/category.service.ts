import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: HttpClient) {
  }

  list() {
    return this.http.get(environment.appContext + '/categories');
  }

  getCategory(id: any) {
    return this.http.get(environment.appContext + '/categories/' + id);
  }

  addCategory(category: any) {
    return this.http.post(environment.appContext + '/categories', category);
  }

  editCategory(category: any) {
    return this.http.put(environment.appContext + '/categories/' + category.id, category);
  }

  deleteCup(id: any) {
    return this.http.delete(environment.appContext + '/categories/' + id);
  }


}
