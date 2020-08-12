import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class RepeatService {

  constructor(private http: HttpClient) {
  }

  sendAnswers(answers: any[]) {
    return this.http.post(environment.appContext + '/repeat', answers)
  }
}
