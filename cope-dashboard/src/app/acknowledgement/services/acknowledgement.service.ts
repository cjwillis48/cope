import {Injectable} from '@angular/core';
import {Observable, of} from "rxjs";
import {Acknowledgement} from "../interfaces/acknowledgement.interface";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {map} from "rxjs/operators";

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class AcknowledgementService {

  constructor(private http: HttpClient) {
  }

  getAcknowledgements(): Observable<Acknowledgement[]> {
    return this.http.get<Acknowledgement[]>('http://localhost:8080/acknowledgement', {
      ...httpOptions,
      observe: 'response'
    })
      .pipe(
        map(res => {
            if (res.status != 200) {
              return [];
            }
            return res.body;
          }
        )
      );
  }
}
