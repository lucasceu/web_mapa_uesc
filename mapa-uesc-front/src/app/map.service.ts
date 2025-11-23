import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Local {
  id: number;
  nome: string;
  descricao: string;
  detalhesSalas?: string;
  coordenadaX: number;
  coordenadaY: number;
  urlImagem: string;
}

@Injectable({
  providedIn: 'root'
})
export class MapService {
  private apiUrl = 'http://localhost:8080/api/locais';

  constructor(private http: HttpClient) { }

  getLocais(): Observable<Local[]> {
    return this.http.get<Local[]>(this.apiUrl);
  }
}