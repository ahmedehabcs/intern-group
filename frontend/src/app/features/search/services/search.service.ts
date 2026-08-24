import { HttpClient, HttpParams } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { SearchResponse } from '../models/search.models';
import { MockDataStore } from '../../../mocks/mock-data.store';
@Injectable({ providedIn: 'root' })
export class SearchService {
  private http = inject(HttpClient);
  private mock = inject(MockDataStore);
  search(query: string): Observable<SearchResponse> {
    if (environment.mock.enabled) return this.mock.search(query);
    return this.http.get<SearchResponse>(`${environment.apiUrl}/api/search`, {
      params: new HttpParams().set('search', query),
    });
  }
}
