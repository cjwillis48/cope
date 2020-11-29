import { Injectable } from '@angular/core';
import { Observable, of } from "rxjs";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { map } from "rxjs/operators";
import { environment } from "../../../environments/environment";
import { Prescription } from "../interfaces/prescription.interface";

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class PrescriptionService {

  constructor(private http: HttpClient) {
  }

  getPrescriptions(): Observable<Prescription[]> {
    return this.http.get<Prescription[]>(`${environment.serviceBaseUrl}/prescription`, {
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
