import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

/**
 * Representação do Local.java do backend.
 * Campos opcionais devem estar marcados com "?".
 */
export interface Local {
  id: number;
  nome: string;
  descricao: string;
  detalhesSalas?: string;  // pode vir null do backend
  coordenadaX: number;
  coordenadaY: number;
  urlImagem: string;
}

@Injectable({
  providedIn: 'root'
})
export class MapService {

  /** Ajuste conforme seu backend */
  private readonly apiUrl = 'http://localhost:8080/api/locais';

  constructor(private http: HttpClient) {}

  /** Lista todos os locais cadastrados no backend */
  getLocais(): Observable<Local[]> {
    return this.http.get<Local[]>(this.apiUrl);
  }
}
