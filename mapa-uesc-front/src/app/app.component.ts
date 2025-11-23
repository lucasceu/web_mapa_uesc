import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { MapService, Local } from './map.service'; 
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, HttpClientModule], 
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  
  title = 'mapa-uesc-front';
  menuAberto = false;
  locais: Local[] = [];
  localSelecionado: Local | null = null;
  abaAtual: string = 'foto';
  posicaoModal = { top: 'auto', left: 'auto', right: 'auto', bottom: 'auto' };

  constructor(private mapService: MapService) {}

  ngOnInit() {
    this.carregarLocais();
  }

  toggleMenu() {
    this.menuAberto = !this.menuAberto;
  }

  abrirModal(local: Local) {
    this.localSelecionado = local;
    this.menuAberto = false;
    this.abaAtual = 'foto';
    this.calcularPosicaoModal(local.coordenadaX, local.coordenadaY);
  }

  fecharModal() {
    this.localSelecionado = null;
  }

  mudarAba(aba: string) {
    this.abaAtual = aba;
  }

  calcularPosicaoModal(x: number, y: number) {
    let style: any = {};
    if (x > 50) { style.left = 'auto'; style.right = (100 - x + 1) + '%'; } 
    else { style.left = (x + 1) + '%'; style.right = 'auto'; }

    if (y > 60) { style.top = 'auto'; style.bottom = (100 - y + 3) + '%'; } 
    else { style.top = (y + 3) + '%'; style.bottom = 'auto'; }
    
    this.posicaoModal = style;
  }

  getListaSalas(): {andar: string, salas: string}[] {
    if (!this.localSelecionado?.detalhesSalas) return [];
    return this.localSelecionado.detalhesSalas.split(';').map(item => {
      const partes = item.split('|');
      return { andar: partes[0]?.trim() || '', salas: partes[1]?.trim() || '' };
    });
  }

  carregarLocais() {
    this.mapService.getLocais().subscribe({
      next: (dados) => this.locais = dados,
      error: (erro) => console.error(erro)
    });
  }

  getPoligonoSelecao(): string {
    if (!this.localSelecionado) return '';
    const x = this.localSelecionado.coordenadaX;
    const y = this.localSelecionado.coordenadaY;
    const largura = 6; const altura = 4;  
    return `${x},${y - altura} ${x + largura},${y} ${x},${y + altura} ${x - largura},${y}`;
  }

  capturarCoordenadas(event: MouseEvent) {
    const imagem = event.target as HTMLElement;
    const rect = imagem.getBoundingClientRect();
    const x = event.clientX - rect.left;
    const y = event.clientY - rect.top;
    const percentX = (x / rect.width) * 100;
    const percentY = (y / rect.height) * 100;
    console.log(`Para Java: .setCoordenadaX(${percentX.toFixed(2)}); .setCoordenadaY(${percentY.toFixed(2)});`);
  }
}