import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http'; // Adicionado HttpParams
import { Observable } from 'rxjs';

export interface Local {
  id: number;
  nome: string;
  descricao: string;
  detalhesSalas?: string;
  coordenadaX: number;
  coordenadaY: number;
  latitude?: number;
  longitude?: number;
  urlImagem: string;
}

@Injectable({
  providedIn: 'root'
})
export class MapService {

  private readonly apiUrl = 'http://localhost:8080/api/locais';

  constructor(private http: HttpClient) {}

  getLocais(): Observable<Local[]> {
    return this.http.get<Local[]>(this.apiUrl);
  }

  // --- CÓDIGO NOVO ---
  // Pede ao backend a lista de pontos ordenados para formar o caminho
  getRota(origemId: number, destinoId: number): Observable<Local[]> {
    const params = new HttpParams()
      .set('origem', origemId.toString())
      .set('destino', destinoId.toString());
    
    return this.http.get<Local[]>(`${this.apiUrl}/rota`, { params });
  }
  // -------------------
}