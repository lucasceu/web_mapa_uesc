import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
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

  carregando = false;
  erroCarregamento: string | null = null;

  localSelecionado: Local | null = null;
  posicaoModal: { top: string; left: string } = { top: '60%', left: '70%' };

  abaAtual: 'foto' | 'salas' | 'info' = 'foto';

  termoBusca = '';
  mostrarSugestoes = false;

  constructor(private mapService: MapService) {}

  ngOnInit(): void {
    this.carregarLocais();
  }

  private carregarLocais(): void {
    this.carregando = true;
    this.erroCarregamento = null;

    this.mapService.getLocais().subscribe({
      next: (dados) => {
        this.locais = dados || [];
        this.locaisFiltrados = [...this.locais];
        this.carregando = false;
      },
      error: (err) => {
        console.error('Erro ao carregar locais', err);
        this.erroCarregamento =
          'Não foi possível carregar os locais. Tente novamente mais tarde.';
        this.carregando = false;
      },
    });
  }

  toggleMenu(): void {
    this.menuAberto = !this.menuAberto;
  }

  /** True se existe um local selecionado E ele ainda está na lista filtrada */
  get localSelecionadoVisivel(): boolean {
    return !!(
      this.localSelecionado &&
      this.locaisFiltrados.some((l) => l.id === this.localSelecionado!.id)
    );
  }

  /** Filtra lista e controla exibição do autocomplete */
  filtrarLocais(): void {
    const termo = this.termoBusca.trim().toLowerCase();

    this.mostrarSugestoes = termo.length > 0;

    if (!termo) {
      this.locaisFiltrados = [...this.locais];
      return;
    }

    this.locaisFiltrados = this.locais.filter((local) => {
      const nome = local.nome?.toLowerCase() || '';
      const desc = local.descricao?.toLowerCase() || '';
      const detalhes = local.detalhesSalas?.toLowerCase() || '';
      return (
        nome.includes(termo) || desc.includes(termo) || detalhes.includes(termo)
      );
    });

    // se o selecionado saiu do filtro, limpa (remove highlight/modal)
    if (
      this.localSelecionado &&
      !this.locaisFiltrados.some((l) => l.id === this.localSelecionado!.id)
    ) {
      this.localSelecionado = null;
    }
  }

  /** Clique em uma sugestão do autocomplete */
  selecionarSugestao(local: Local): void {
    this.termoBusca = local.nome;
    this.mostrarSugestoes = false;
    this.locaisFiltrados = [local];
    this.abrirModal(local);
  }

  abrirModal(local: Local, event?: MouseEvent): void {
    this.localSelecionado = local;
    this.abaAtual = 'foto';
    this.definirPosicaoModal(event);
  }

  fecharModal(): void {
    this.localSelecionado = null;
  }

  mudarAba(aba: 'foto' | 'salas' | 'info'): void {
    this.abaAtual = aba;
  }

  /** Calcula posição do modal perto do clique, sem sair da tela */
  private definirPosicaoModal(event?: MouseEvent): void {
    if (!event) {
      this.posicaoModal = { top: '60%', left: '70%' };
      return;
    }

    const viewportWidth = window.innerWidth;
    const viewportHeight = window.innerHeight;
    const modalWidth = 340;
    const modalHeight = 260;

    let left = event.clientX + 16;
    let top = event.clientY - modalHeight / 2;

    if (left + modalWidth > viewportWidth - 16) {
      left = viewportWidth - modalWidth - 16;
    }
    if (left < 16) {
      left = 16;
    }

    if (top + modalHeight > viewportHeight - 16) {
      top = viewportHeight - modalHeight - 16;
    }
    if (top < 16) {
      top = 16;
    }

    this.posicaoModal = { top: `${top}px`, left: `${left}px` };
  }

  /**
   * Constrói lista de salas a partir da string detalhesSalas.
   * Formato vindo do back (DataLoader):
   * "Térreo|Salas X;1º Andar|Y;2º Andar|Z"
   *  - ";" separa linhas
   *  - "|" separa andar de descrição
   */
  getListaSalas(): SalaDetalhe[] {
    if (!this.localSelecionado || !this.localSelecionado.detalhesSalas) {
      return [];
    }

    return this.localSelecionado.detalhesSalas
      .split(';')
      .map((parte) => parte.trim())
      .filter((parte) => parte.length > 0)
      .map((parte) => {
        const [andar, salas] = parte.split('|');
        return {
          andar: (andar || '').trim(),
          salas: (salas || '').trim(),
        };
      });
  }

  /**
   * Captura coordenadas em % relativas ao container do mapa.
   * Útil pra cadastrar novos locais no backend.
   */
  capturarCoordenadas(event: MouseEvent): void {
    const container = event.currentTarget as HTMLElement;
    const rect = container.getBoundingClientRect();

    const x = event.clientX - rect.left;
    const y = event.clientY - rect.top;

    const percentX = (x / rect.width) * 100;
    const percentY = (y / rect.height) * 100;

    console.log(
      `Para Java: .setCoordenadaX(${percentX.toFixed(
        2,
      )}); .setCoordenadaY(${percentY.toFixed(2)});`,
    );
  }

  /** Fallback de imagem caso a foto do local não exista */
  onErroImagem(event: Event): void {
    const img = event.target as HTMLImageElement;
    img.src = 'assets/mapa-base.png';
  }
}
