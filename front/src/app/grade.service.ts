import { Observable } from 'rxjs/Observable';
import { Grades } from './modeles/grade';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class GradeService {

  constructor(private http: HttpClient) { }


  getGrades(): Observable<Grades[]> {
    return this.http
      .get<Grades[]>('http://localhost:8080/api/grades');
  }

  getGrade(id: number): Observable<Grades> {
    return this.http
      .get<Grades>('http://localhost:8080/api/grade/' + id);
  }
  updateGrade(grade: Grades): Observable<Grades> {
    return this.http
      .put<Grades>('http://localhost:8080/api/grade/' + grade.id, grade);
  }

  createGrade(grade: Grades): Observable<Grades> {
    return this.http
      .post<Grades>('http://localhost:8080/api/grade/', grade);
  }

  deleteGrade(id: number) {
    return this.http
      .delete<any>('http://localhost:8080/api/grade/' + id);
  }
}
