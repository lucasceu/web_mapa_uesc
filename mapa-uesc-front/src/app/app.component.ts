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

  // --- Variáveis de Interface ---
  menuAberto = true;
  carregando = false;
  erroCarregamento: string | null = null;
  
  // Posição inicial do modal
  posicaoModal: { top: string; left: string } = { top: '60%', left: '70%' };
  abaAtual: 'foto' | 'salas' | 'info' = 'foto';

  // --- Dados dos Locais ---
  locais: Local[] = [];
  locaisFiltrados: Local[] = [];
  localSelecionado: Local | null = null;

  // --- Busca ---
  termoBusca = '';
  // Resultado da busca por número de sala 
  salaBuscada: { pavilhao: Local; andar: string; sala: string } | null = null;

  // --- ROTAS (Novas Variáveis) ---
  rotaAtiva: Local[] = []; // Guarda a lista de pontos do caminho vindo do Java
  caminhoSVG: string = ''; // O desenho da linha (path d="M...")
  pontoOrigemId: number = 13; // ID 13 = Guarita (Padrão)

  constructor(private mapService: MapService) {}


  
  telaLargaOuHorizontal = true;

  ngOnInit() {
    this.carregarLocais();
    this.checarOrientacao();
    window.addEventListener('resize', () => this.checarOrientacao());
  }

<<<<<<< HEAD
  checarOrientacao() {
    const largura = window.innerWidth;
    const altura = window.innerHeight;

    // Se largura maior que altura → está em paisagem
    const horizontal = largura > altura;

    // Tela larga é desktop (> 900px)
    const desktop = largura > 900;

    this.telaLargaOuHorizontal = horizontal || desktop;
  }




=======
  // ========================================================================
  // CARREGAMENTO DE DADOS
  // ========================================================================
>>>>>>> fidelispapaizinho

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

  // ========================================================================
  // LÓGICA DE ROTAS (DIJKSTRA & SVG)
  // ========================================================================

  /**
   * Chama o backend para calcular a rota entre a Guarita (padrão) e o destino.
   */
  tracarRota(destino: Local): void {
    const origemId = this.pontoOrigemId; // 13 (Guarita)

    if (destino.id === origemId) {
      alert("Você já está na origem (Guarita)!");
      return;
    }

    // Limpa rota anterior visualmente antes de buscar a nova
    this.limparRota();

    this.mapService.getRota(origemId, destino.id).subscribe({
      next: (caminho) => {
        if (!caminho || caminho.length === 0) {
          alert("Não foi possível encontrar um caminho para este local.");
          return;
        }
        
        this.rotaAtiva = caminho;
        this.desenharLinhaSVG();
        
        // Fecha o menu e o modal para o usuário ver o mapa desenhado limpo
        this.menuAberto = false;
        this.fecharModal();
      },
      error: (erro) => {
        console.error('Erro ao calcular rota:', erro);
        alert('Erro ao traçar a rota. Verifique se o Backend está rodando.');
      }
    });
  }

  /**
   * Transforma a lista de locais (rotaAtiva) em comandos SVG (M x y L x y...)
   * para desenhar a linha sobre o mapa.
   */
  private desenharLinhaSVG(): void {
    if (this.rotaAtiva.length < 2) return;

    this.caminhoSVG = this.rotaAtiva.map((ponto, index) => {
      // O primeiro ponto usa "M" (Move to), os próximos usam "L" (Line to)
      const comando = index === 0 ? 'M' : 'L';
      // Usa as coordenadas X e Y (porcentagem) salvas no banco
      return `${comando} ${ponto.coordenadaX} ${ponto.coordenadaY}`;
    }).join(' ');
  }

  limparRota(): void {
    this.rotaAtiva = [];
    this.caminhoSVG = '';
  }

  /** * (Antiga abrirRota) 
   * Abre o Google Maps em nova aba com rota a pé.
   */
  abrirRotaGoogleMaps(local: Local): void {
    if (!local.latitude || !local.longitude) {
      alert('Coordenadas de latitude/longitude não disponíveis para este local.');
      return;
    }
    const destino = `${local.latitude},${local.longitude}`;
    const url = `https://www.google.com/maps/dir/?api=1&destination=${destino}&travelmode=walking`;
    window.open(url, '_blank');
  }

  // ========================================================================
  // INTERFACE E EVENTOS (Menu, Modal, Busca)
  // ========================================================================

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

  /** Filtra lista de locais pela barra de busca */
  filtrarLocais(): void {
    const termo = this.termoBusca.trim().toLowerCase();

    this.salaBuscada = null; // Limpa busca de sala anterior

    if (!termo) {
      this.locaisFiltrados = [...this.locais];
      return;
    }

    // Tenta identificar se é uma busca por número de sala (ex: 3201)
    const resultadoSala = this.buscarPorNumeroSala(termo);
    if (resultadoSala) {
      this.salaBuscada = resultadoSala;
      // Mostra apenas o pavilhão onde a sala está
      this.locaisFiltrados = [resultadoSala.pavilhao];
      return;
    }

    // Filtro normal por texto (nome, descrição, detalhes)
    this.locaisFiltrados = this.locais.filter((local) => {
      const nome = local.nome?.toLowerCase() || '';
      const desc = local.descricao?.toLowerCase() || '';
      const detalhes = local.detalhesSalas?.toLowerCase() || '';
      return (
        nome.includes(termo) || desc.includes(termo) || detalhes.includes(termo)
      );
    });

    // Se o item selecionado sumiu do filtro, deseleciona
    if (
      this.localSelecionado &&
      !this.locaisFiltrados.some((l) => l.id === this.localSelecionado!.id)
    ) {
      this.localSelecionado = null;
    }
  }

  /**
   * Lógica para encontrar o pavilhão e andar pelo número da sala.
   * Ex: 3201 -> Pavilhão Jorge Amado (Série 3000), 2º Andar.
   */
  private buscarPorNumeroSala(termo: string): { pavilhao: Local; andar: string; sala: string } | null {
    const numeroLimpo = termo.replace(/\D/g, '');
    
    // Validação básica (3 ou 4 dígitos)
    if (numeroLimpo.length < 3 || numeroLimpo.length > 4) {
      return null;
    }

    const primeiroDigito = parseInt(numeroLimpo[0], 10);
    
    // Regra da UESC: 1xxx (Pedro Calmon), 2xxx (Adonias Filho), 3xxx (Jorge Amado)
    if (primeiroDigito < 1 || primeiroDigito > 3) {
      return null;
    }

    const mapeamentoPavilhoes: { [key: number]: string } = {
      1: 'Pedro Calmon',
      2: 'Adonias Filho',
      3: 'Jorge Amado'
    };

    const nomePavilhao = mapeamentoPavilhoes[primeiroDigito];
    
    // Procura o objeto Local correspondente na lista carregada
    const pavilhao = this.locais.find(l => 
      l.nome?.toLowerCase().includes(nomePavilhao.toLowerCase())
    );

    if (!pavilhao) {
      return null;
    }

    // O segundo dígito geralmente indica o andar (ex: 3201 -> 2º andar)
    const digitoAndar = parseInt(numeroLimpo[1], 10);
    const andar = digitoAndar === 0 ? 'Térreo' : `${digitoAndar}º Andar`;

    return {
      pavilhao,
      andar,
      sala: `Sala ${numeroLimpo}`
    };
  }

  abrirModal(local: Local, event?: MouseEvent): void {
    this.localSelecionado = local;
    
    // Se não for uma busca específica de sala, abre na aba de fotos
    if (!this.salaBuscada) {
      this.abaAtual = 'foto';
    }
    
    this.definirPosicaoModal(local, event);
  }

  abrirModalSala(): void {
    if (this.salaBuscada) {
      this.abaAtual = 'salas'; // Abre direto na lista de salas
      this.localSelecionado = this.salaBuscada.pavilhao;
      this.definirPosicaoModal(this.salaBuscada.pavilhao);
    }
  }

  fecharModal(): void {
    this.localSelecionado = null;
    this.salaBuscada = null;
  }

  mudarAba(aba: 'foto' | 'salas' | 'info'): void {
    this.abaAtual = aba;
  }

  /**
   * Calcula a posição do modal para ficar próximo ao clique ou ao pino,
   * garantindo que não saia da tela.
   */
  private definirPosicaoModal(local: Local, event?: MouseEvent): void {
    const viewportWidth = window.innerWidth;
    const viewportHeight = window.innerHeight;
    const modalWidth = 340;
    const modalHeight = 260;

    let left: number;
    let top: number;

    if (event) {
      // Se clicou no pino (mouse), posiciona perto do cursor
      left = event.clientX + 16;
      top = event.clientY - modalHeight / 2;
    } else {
      // Se clicou na lista lateral, tenta calcular baseado na % do pino no mapa
      const mapContainer = document.querySelector('.map-container') as HTMLElement;
      if (mapContainer) {
        const rect = mapContainer.getBoundingClientRect();
        // Converte coordenadas % do local para pixels na tela
        left = rect.left + (local.coordenadaX / 100) * rect.width + 20;
        top = rect.top + (local.coordenadaY / 100) * rect.height - modalHeight / 2;
      } else {
        // Fallback: centraliza na tela
        left = viewportWidth / 2 - modalWidth / 2;
        top = viewportHeight / 2 - modalHeight / 2;
      }
    }

    // Ajustes de limites de tela (evita cortar o modal)
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

  onErroImagem(event: Event): void {
    const img = event.target as HTMLImageElement;
    img.src = 'assets/mapa-base.png';
  }

  /**
   * Verifica se o local passado faz parte da rota ativa.
   * Usado no HTML para pintar a bolinha de verde.
   */
  isLocalNaRota(local: Local): boolean {
    if (!this.rotaAtiva || this.rotaAtiva.length === 0) {
      return false;
    }
    // Retorna true se o ID do local existir na lista de rotaAtiva
    return this.rotaAtiva.some(ponto => ponto.id === local.id);
  }
}