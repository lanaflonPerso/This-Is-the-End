import { Ilots } from './modeles/ilots';
import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';


@Injectable()
export class IlotService {

  constructor(private http: HttpClient) { }


  getIlots(): Observable<Ilots[]> {
    return this.http
      .get<Ilots[]>('http://localhost:8080/api/ilots');
  }

  getIlot(id: number): Observable<Ilots> {
    return this.http
      .get<Ilots>('http://localhost:8080/api/ilot/' + id);
  }
  updateIlot(ilot: Ilots): Observable<Ilots> {
    return this.http
      .put<Ilots>('http://localhost:8080/api/ilot/' + ilot.id, ilot);
  }

  createIlot(ilot: Ilots): Observable<Ilots> {
    return this.http
      .post<Ilots>('http://localhost:8080/api/ilot/', ilot);
  }

  deleteIlot(id: number) {
    return this.http
      .delete<any>('http://localhost:8080/api/ilot/' + id);
  }
}
