import { Agents } from './modeles/agents';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class AgentService {

  constructor(private http: HttpClient) { }


  getAgents(): Observable<Agents[]> {
    return this.http
      .get<Agents[]>('http://localhost:8080/api/agents');
  }

  getAgent(id: number): Observable<Agents> {
    return this.http
      .get<Agents>('http://localhost:8080/api/agent/' + id);
  }
  updateAgent(agent: Agents): Observable<Agents> {
    return this.http
      .put<Agents>('http://localhost:8080/api/agent/' + agent.id, agent);
  }

  createAgent(agent: Agents): Observable<Agents> {
    return this.http
      .post<Agents>('http://localhost:8080/api/agent/', agent);
  }

  deleteAgent(id: number) {
    return this.http
      .delete<any>('http://localhost:8080/api/agent/' + id);
  }

}
