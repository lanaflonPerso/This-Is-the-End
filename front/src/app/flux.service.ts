import { Flux } from './modeles/flux';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class FluxService {

  constructor(private http: HttpClient) { }


  getAllFlux(): Observable<Flux[]> {
    return this.http
      .get<Flux[]>('http://localhost:8080/api/flux');
  }

  getFlux(id: number): Observable<Flux> {
    return this.http
      .get<Flux>('http://localhost:8080/api/flux/' + id);
  }
  updateFlux(flux: Flux): Observable<Flux> {
    return this.http
      .put<Flux>('http://localhost:8080/api/flux/' + flux.id, flux);
  }

  createFlux(flux: Flux): Observable<Flux> {
    return this.http
      .post<Flux>('http://localhost:8080/api/flux/', flux);
  }

  deleteFlux(id: number) {
    return this.http
      .delete<any>('http://localhost:8080/api/flux/' + id);
  }
}
