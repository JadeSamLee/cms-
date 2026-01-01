import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Complaint {
  id: number;
  ticketId: string;
  category: string;
  subCategory?: string;
  priority: string;
  status: string;
  description: string;
  departmentId?: number;
  slaPenaltyPoints: number;
}

export interface Team {
  id: number;
  name: string;
  description?: string;
}

export interface SlaPolicy {
  id: number;
  category: string;
  priority: string;
  responseHours: number;
  resolutionHours: number;
}

@Injectable({
  providedIn: 'root'
})
export class Api {
  private baseUrl = 'http://localhost:8081/api/v1/admin';

  constructor(private http: HttpClient) {}

  getComplaints(): Observable<Complaint[]> {
    return this.http.get<Complaint[]>(`${this.baseUrl}/complaints`);
  }

  assignToTeam(id: number, teamId: number): Observable<Complaint> {
    return this.http.post<Complaint>(`${this.baseUrl}/workflow/complaints/${id}/assign/${teamId}`, {});
  }

  verifyComplaint(id: number): Observable<Complaint> {
    return this.http.post<Complaint>(`${this.baseUrl}/workflow/complaints/${id}/verify`, {});
  }

  rejectComplaint(id: number, newTeamId: number): Observable<Complaint> {
    return this.http.post<Complaint>(`${this.baseUrl}/workflow/complaints/${id}/reject`, { newTeamId });
  }

  getTeams(): Observable<Team[]> {
    return this.http.get<Team[]>(`${this.baseUrl}/teams`);
  }

  createTeam(team: Partial<Team>): Observable<Team> {
    return this.http.post<Team>(`${this.baseUrl}/teams`, team);
  }

  getSlaPolicies(): Observable<SlaPolicy[]> {
    return this.http.get<SlaPolicy[]>(`${this.baseUrl}/sla`);
  }
}