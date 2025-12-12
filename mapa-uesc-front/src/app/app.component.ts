import { Component, OnInit, Inject, PLATFORM_ID } from '@angular/core';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { MapService, Local } from './map.service';

interface SalaDetalhe {
  andar: string;
  salas: string;
}

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, HttpClientModule, FormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent implements OnInit {
  title = 'mapa-uesc-front';

  menuAberto = true;
  locais: Local[] = [];
  locaisFiltrados: Local[] = [];
  localSelecionado: Local | null = null;
  termoBusca = '';
  
  // --- Variáveis que faltavam no HTML ---
  salaBuscada: { pavilhao: Local; andar: string; sala: string } | null = null;
  rotaAtiva: Local[] = [];
  caminhoSVG: string = '';
  posicaoModal: { top: string; left: string } = { top: '50%', left: '50%' };
  abaAtual: 'foto' | 'salas' | 'info' = 'foto';
  
  carregando = false;
  erroCarregamento: string | null = null;

  constructor(
    private mapService: MapService,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {}

  ngOnInit() {
    this.carregarLocais();
  }

  // --- Carregamento ---
  private carregarLocais(): void {
    this.carregando = true;
    this.mapService.getLocais().subscribe({
      next: (dados) => {
        this.locais = dados || [];
        this.locaisFiltrados = [...this.locais];
        this.carregando = false;
      },
      error: (err) => {
        console.error(err);
        this.erroCarregamento = 'Erro ao carregar locais.';
        this.carregando = false;
      }
    });
  }

  // --- Rotas ---
  tracarRota(destino: Local): void {
    const origemId = 13; // Guarita
    if (destino.id === origemId) return;

    this.rotaAtiva = [];
    this.caminhoSVG = '';

    this.mapService.getRota(origemId, destino.id).subscribe({
      next: (caminho) => {
        this.rotaAtiva = caminho;
        this.desenharLinhaSVG();
        this.menuAberto = false;
        this.fecharModal();
      }
    });
  }

  private desenharLinhaSVG(): void {
    if (this.rotaAtiva.length < 2) return;
    this.caminhoSVG = this.rotaAtiva.map((p, i) => 
      `${i === 0 ? 'M' : 'L'} ${p.coordenadaX} ${p.coordenadaY}`
    ).join(' ');
  }

  isLocalNaRota(local: Local): boolean {
    return this.rotaAtiva.some(p => p.id === local.id);
  }

  // --- Interface ---
  toggleMenu() { this.menuAberto = !this.menuAberto; }

  filtrarLocais() {
    const termo = this.termoBusca.toLowerCase();
    this.salaBuscada = null;

    if (!termo) {
      this.locaisFiltrados = [...this.locais];
      return;
    }

    // Busca por Sala (Lógica simplificada)
    const numSala = termo.replace(/\D/g, '');
    if (numSala.length >= 3 && numSala.length <= 4) {
      const serie = parseInt(numSala[0]);
      const pavilhoes = { 1: 'Pedro Calmon', 2: 'Adonias Filho', 3: 'Jorge Amado' };
      // @ts-ignore
      const nomePav = pavilhoes[serie];
      
      if (nomePav) {
        const pavilhao = this.locais.find(l => l.nome.includes(nomePav));
        if (pavilhao) {
          const andar = parseInt(numSala[1]) === 0 ? 'Térreo' : `${numSala[1]}º Andar`;
          this.salaBuscada = { pavilhao, andar, sala: `Sala ${numSala}` };
          this.locaisFiltrados = [pavilhao];
          return;
        }
      }
    }

    this.locaisFiltrados = this.locais.filter(l => 
      l.nome.toLowerCase().includes(termo) || l.descricao.toLowerCase().includes(termo)
    );
  }

  abrirModal(local: Local, event?: MouseEvent) {
    this.localSelecionado = local;
    if (!this.salaBuscada) this.abaAtual = 'foto';
    this.definirPosicaoModal(local, event);
  }

  abrirModalSala() {
    if (this.salaBuscada) {
      this.abaAtual = 'salas';
      this.localSelecionado = this.salaBuscada.pavilhao;
      this.definirPosicaoModal(this.salaBuscada.pavilhao);
    }
  }

  fecharModal() { this.localSelecionado = null; }
  mudarAba(aba: 'foto' | 'salas' | 'info') { this.abaAtual = aba; }

  get localSelecionadoVisivel(): boolean {
    return !!(this.localSelecionado && this.locaisFiltrados.some(l => l.id === this.localSelecionado!.id));
  }

  getListaSalas(): SalaDetalhe[] {
    if (!this.localSelecionado?.detalhesSalas) return [];
    return this.localSelecionado.detalhesSalas.split(';').map(parte => {
      const [andar, salas] = parte.split('|');
      return { andar: andar?.trim(), salas: salas?.trim() };
    }).filter(s => s.andar && s.salas);
  }

  private definirPosicaoModal(local: Local, event?: MouseEvent) {
    if (!isPlatformBrowser(this.platformId)) return;
    let top = '50%', left = '50%';
    
    if (event) {
      left = (event.clientX + 16) + 'px';
      top = (event.clientY - 130) + 'px'; // Ajuste básico para centralizar altura
    }
    this.posicaoModal = { top, left };
  }

  // Método que faltava no HTML
  capturarCoordenadas(event: any) {
    // Método vazio apenas para não quebrar o HTML antigo se ainda tiver a chamada
  }

  onErroImagem(event: Event) {
    (event.target as HTMLImageElement).src = 'assets/mapa-base.png';
  }
}