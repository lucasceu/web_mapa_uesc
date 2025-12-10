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

  // Resultado da busca por numero de sala 
  salaBuscada: { pavilhao: Local; andar: string; sala: string } | null = null;

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
          'Nao foi possivel carregar os locais. Tente novamente mais tarde.';
        this.carregando = false;
      },
    });
  }

  toggleMenu(): void {
    this.menuAberto = !this.menuAberto;
  }

  /** True se existe um local selecionado E ele ainda esta na lista filtrada */
  get localSelecionadoVisivel(): boolean {
    return !!(
      this.localSelecionado &&
      this.locaisFiltrados.some((l) => l.id === this.localSelecionado!.id)
    );
  }

  /** Filtra lista de locais */
  filtrarLocais(): void {
    const termo = this.termoBusca.trim().toLowerCase();

    this.salaBuscada = null; // Limpa busca anterior

    if (!termo) {
      this.locaisFiltrados = [...this.locais];
      return;
    }

    // Verifica se e uma busca por numero de sala (ex: 3201, 2105, 1101)
    const resultadoSala = this.buscarPorNumeroSala(termo);
    if (resultadoSala) {
      this.salaBuscada = resultadoSala;
      this.locaisFiltrados = [resultadoSala.pavilhao];
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

    // se o selecionado saiu do filtro, limpa (remove modal/pino selecionado)
    if (
      this.localSelecionado &&
      !this.locaisFiltrados.some((l) => l.id === this.localSelecionado!.id)
    ) {
      this.localSelecionado = null;
    }
  }

  /**
   * Busca pavilhao e andar baseado no numero da sala.
   * Sistema UESC:
   * - 1xxx = Pavilhao Pedro Calmon
   * - 2xxx = Pavilhao Adonias Filho  
   * - 3xxx = Pavilhao Jorge Amado
   * - 2o digito indica o andar (ex: 3201 = 2o andar)
   */
  private buscarPorNumeroSala(termo: string): { pavilhao: Local; andar: string; sala: string } | null {
    // Remove espacos e caracteres nao numericos
    const numeroLimpo = termo.replace(/\D/g, '');
    
    // Precisa ter pelo menos 3 digitos (ex: 101, 3201)
    if (numeroLimpo.length < 3 || numeroLimpo.length > 4) {
      return null;
    }

    const primeiroDigito = parseInt(numeroLimpo[0], 10);
    
    // So aceita series 1, 2 ou 3
    if (primeiroDigito < 1 || primeiroDigito > 3) {
      return null;
    }

    // Mapeia serie para nome do pavilhao
    const pavilhoes: { [key: number]: string } = {
      1: 'Pedro Calmon',
      2: 'Adonias Filho',
      3: 'Jorge Amado'
    };

    const nomePavilhao = pavilhoes[primeiroDigito];
    
    // Encontra o local correspondente
    const pavilhao = this.locais.find(l => 
      l.nome?.toLowerCase().includes(nomePavilhao.toLowerCase())
    );

    if (!pavilhao) {
      return null;
    }

    // Determina o andar baseado no 2o digito
    const digitoAndar = parseInt(numeroLimpo[1], 10);
    const andar = digitoAndar === 0 ? 'Terreo' : `${digitoAndar}o Andar`;

    return {
      pavilhao,
      andar,
      sala: `Sala ${numeroLimpo}`
    };
  }

  abrirModal(local: Local, event?: MouseEvent): void {
    this.localSelecionado = local;
    
    // Se veio de busca por sala, abre na aba salas; senao, abre na foto
    if (!this.salaBuscada) {
      this.abaAtual = 'foto';
    }
    
    this.definirPosicaoModal(local, event);
  }

  /** Abre o modal do pavilhao quando clica no card de sala encontrada */
  abrirModalSala(): void {
    if (this.salaBuscada) {
      this.abaAtual = 'salas'; // Vai direto para aba de salas
      this.localSelecionado = this.salaBuscada.pavilhao;
      this.definirPosicaoModal(this.salaBuscada.pavilhao);
    }
  }

  fecharModal(): void {
    this.localSelecionado = null;
    // Limpa a busca por sala ao fechar o modal
    this.salaBuscada = null;
  }

  mudarAba(aba: 'foto' | 'salas' | 'info'): void {
    this.abaAtual = aba;
  }

  /** Calcula posicao do modal perto do pino ou clique, sem sair da tela */
  private definirPosicaoModal(local: Local, event?: MouseEvent): void {
    const viewportWidth = window.innerWidth;
    const viewportHeight = window.innerHeight;
    const modalWidth = 340;
    const modalHeight = 260;

    let left: number;
    let top: number;

    if (event) {
      // Clicou no pino: posiciona perto do clique
      left = event.clientX + 16;
      top = event.clientY - modalHeight / 2;
    } else {
      // Clicou no menu: calcula posicao baseada nas coordenadas do local
      const mapContainer = document.querySelector('.map-container') as HTMLElement;
      if (mapContainer) {
        const rect = mapContainer.getBoundingClientRect();
        // Converte coordenadas % do local para pixels na tela
        left = rect.left + (local.coordenadaX / 100) * rect.width + 20;
        top = rect.top + (local.coordenadaY / 100) * rect.height - modalHeight / 2;
      } else {
        // Fallback central
        left = viewportWidth / 2 - modalWidth / 2;
        top = viewportHeight / 2 - modalHeight / 2;
      }
    }

    // Garante que nao sai da tela
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
   * Constroi lista de salas a partir da string detalhesSalas.
   * Formato: "Terreo|Salas X;1o Andar|Y;2o Andar|Z"
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
   * Util para desenvolvimento - mostra no console as coordenadas para Java.
   */
  capturarCoordenadas(event: MouseEvent): void {
    const container = event.currentTarget as HTMLElement;
    const rect = container.getBoundingClientRect();

    const x = event.clientX - rect.left;
    const y = event.clientY - rect.top;

    const percentX = (x / rect.width) * 100;
    const percentY = (y / rect.height) * 100;

    console.log(
      `Para Java: .setCoordenadaX(${percentX.toFixed(2)}); .setCoordenadaY(${percentY.toFixed(2)});`
    );
  }

  /** Fallback de imagem caso a foto do local nao exista */
  onErroImagem(event: Event): void {
    const img = event.target as HTMLImageElement;
    img.src = 'assets/mapa-base.png';
  }

  /**
   * Abre o Google Maps em nova aba com rota a pe ate o local selecionado.
   * Usa a localizacao atual do usuario como origem.
   */
  abrirRota(local: Local): void {
    if (!local.latitude || !local.longitude) {
      alert('Coordenadas de rota nao disponiveis para este local.');
      return;
    }

    const destino = `${local.latitude},${local.longitude}`;
    const url = `https://www.google.com/maps/dir/?api=1&destination=${destino}&travelmode=walking`;
    window.open(url, '_blank');
  }
}
