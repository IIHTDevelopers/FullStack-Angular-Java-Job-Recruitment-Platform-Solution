import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Job } from '../model/job';

@Injectable({
  providedIn: 'root'
})
export class JobService {
  private apiUrl = 'http://127.0.0.1:8081/jobrecruitplatform/jobs';

  constructor(private http: HttpClient) { }

  getAllJobs(): Observable<Job[]> {
    return this.http.get<Job[]>(this.apiUrl);
  }

  getJobById(id: number): Observable<Job> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<Job>(url);
  }

  createJob(job: Job): Observable<Job> {
    return this.http.post<Job>(this.apiUrl, job);
  }

  updateJob(id: number, job: Job): Observable<Job> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.put<Job>(url, job);
  }

  deleteJob(id: number): Observable<void> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.delete<void>(url);
  }

  searchJobs(searchParams: any): Observable<Job[]> {
    const url = `${this.apiUrl}/search`;
    return this.http.get<Job[]>(url, { params: searchParams });
  }
}
