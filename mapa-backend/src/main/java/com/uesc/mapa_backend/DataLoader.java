package com.uesc.mapa_backend;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.uesc.mapa_backend.model.Local;
import com.uesc.mapa_backend.repository.LocalRepository;
import com.uesc.mapa_backend.service.GrafoService;

@Component
public class DataLoader implements CommandLineRunner {

    private final LocalRepository repository;
    private final GrafoService grafoService;

    public DataLoader(LocalRepository repository, GrafoService grafoService) {
        this.repository = repository;
        this.grafoService = grafoService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() == 0) {
            System.out.println("--- INICIANDO CARGA: MODO ESTRELA (GUARITA -> TODOS) ---");

            // ==================================================================================
            // 1. CRIAÇÃO DOS LOCAIS (COORDENADAS CORRIGIDAS MANTIDAS)
            // ==================================================================================
            
            // SETOR 1
            Local local01 = criar("Hospital Veterinário", "Hospital Veterinário da UESC", 27.0, 1.0, "instalacoes/hvet.png");
            Local local02 = criar("Campo de Futebol", "Campo de futebol do campus", 23.0, 13.0, "instalacoes/campo.png");
            Local local03 = criar("Piscina Semi-Olímpica", "Piscina semi-olímpica", 24.5, 22.0, "instalacoes/piscina.png");
            Local local04 = criar("Pavilhão de Educação Física", "Salas e Ginásio", 14.0, 22.0, "instalacoes/edufisica.png");
            Local local05 = criar("Quadra Poliesportiva", "Quadra aberta", 20.0, 28.0, "instalacoes/quadra.png");
            Local local44 = criar("Ciências Sociais", "Departamento de Ciências Sociais", 17.0, 18.0, "instalacoes/cienciassociais.png");
            Local local47 = criar("Crematório", "Crematório", 29.5, 1.0, "instalacoes/crematorio.png");

            // SETOR 2
            Local local06 = criar("Restaurante Universitário", "Refeitório (RU)", 32.0, 17.0, "instalacoes/ru.png");
            Local local07 = criar("Centro de Microscopia Eletrônica", "CME", 38.0, 19.0, "instalacoes/cme.png");
            Local local34 = criar("Casa de Vegetação 1", "Estufa 1", 37.0, 11.0, "instalacoes/cv1.png");
            Local local45 = criar("Germoplasma", "Banco de Germoplasma", 33.0, 12.0, "instalacoes/germoplasma.png");
            Local local46 = criar("LAPNAR", "Laboratório de Nutrição de Ruminantes", 35.2, 8.0, "instalacoes/lapnar.png");

            // SETOR 3
            Local local08 = criar("Pavilhão Waldir Pires", "Juizado Modelo", 42.5, 15.5, "instalacoes/juizadomodelo.png");
            Local local09 = criar("Pavilhão Pedro Calmon", "Série 1000 - Licenciaturas", 28.0, 29.0, "instalacoes/pedrocal.png");
            local09.setDetalhesSalas("Térreo|Auditórios;1º Andar|Salas 1xxx");
            Local local10 = criar("Pavilhão Adonias Filho", "Série 2000 - Administrativo", 53.0, 19.0, "instalacoes/adonias.png");
            local10.setDetalhesSalas("Térreo|Protocolo;1º Andar|Salas 2xxx");
            Local local16 = criar("Pavilhão Jorge Amado", "Série 3000", 65.0, 24.0, "instalacoes/jorge.png");
            local16.setDetalhesSalas("Térreo|Auditório;1º Andar|Salas 3xxx");
            Local local15 = criar("Torre Administrativa", "Reitoria (Ed. José Haroldo)", 56.5, 13.0, "instalacoes/torre.png");
            Local local41 = criar("Arena Ramon Vane", "Teatro Aberto", 62.5, 29.0, "instalacoes/arena.png");

            // SETOR 4 (GUARITA - O HUB)
            Local local13 = criar("Guarita", "Entrada Principal", 54.0, 55.0, "instalacoes/guarita.png"); // ID DA GUARITA
            Local local14 = criar("Praça das Bandeiras", "Brasão UESC", 56.0, 46.0, "instalacoes/brasao.png");
            Local local29 = criar("Placa Direitos Humanos", "Monumento", 59.0, 40.0, "instalacoes/dirhumanos.png");
            Local local30 = criar("Placa José Haroldo", "Monumento", 58.0, 25.0, "instalacoes/joseharoldo.png");

            // SETOR 5
            Local local11 = criar("Pavilhão Max de Menezes", "Pós-Graduação", 45.0, 40.0, "instalacoes/max.png");
            Local local12 = criar("CCAM / NBCGIB", "Computação Avançada", 47.5, 49.0, "instalacoes/nbcgib.png");
            Local local17 = criar("CBG", "Biotecnologia e Genética", 74.0, 49.0, "instalacoes/cbg.png");
            Local local42 = criar("CLCE", "Complexo Ciências Exatas", 42.0, 34.0, "instalacoes/clce.png");
            Local local43 = criar("NEPAB", "Arqueologia", 38.0, 32.0, "instalacoes/nepab.png");
            Local local31 = criar("Base Ambiental", "Gestão Ambiental", 35.0, 37.0, "instalacoes/baseambiental.png");
            Local local32 = criar("Bosque", "Área de Preservação", 65.0, 32.0, "instalacoes/bosque.png");

            // SETOR 6
            Local local18 = criar("Pavilhão Agroindústria", "Tecnologia de Alimentos", 77.5, 20.0, "instalacoes/agro.png");
            Local local25 = criar("Pavilhão Evandro Sena", "Ciências Exatas", 72.0, 10.0, "instalacoes/exatas.png");
            Local local26 = criar("Oficina e Transportes", "Prefeitura do Campus", 72.0, 18.0, "instalacoes/veiculos.png");
            Local local27 = criar("Núcleo de EaD", "Educação a Distância", 75.5, 17.0, "instalacoes/ead.png");
            Local local40 = criar("Cervejaria/Cachaçaria", "Produção Experimental", 74.0, 21.0, "instalacoes/cervejaria.png");
            Local local19 = criar("IPAF", "Análise Físico-Químicas", 84.0, 23.0, "instalacoes/fisicoquimicas.png");
            Local local21 = criar("Pavilhão Manoel Nabuco", "Laboratórios Biológicas", 81.0, 38.0, "instalacoes/manoelnabuco.png");
            Local local38 = criar("LAPROBIO", "Biorrefinarias", 82.0, 21.0, "instalacoes/laprobio.png");
            Local local39 = criar("Chocosol", "Fábrica Escola de Chocolate", 79.6, 21.8, "instalacoes/chocosol.png");

            // SETOR 7
            Local local22 = criar("Biblioteca Central", "Biblioteca e Auditório Max", 84.0, 54.0, "instalacoes/biblioteca.png");
            Local local23 = criar("Auditório Paulo Souto", "Auditório Principal", 88.0, 65.0, "instalacoes/auditorio.png");
            Local local20 = criar("CPqCTR", "Centro de Radiações", 88.0, 28.0, "instalacoes/cpqctr.png");
            Local local24 = criar("Pavilhão Cascardo", "Laboratórios", 91.0, 46.0, "instalacoes/cascardo.png");
            Local local28 = criar("Biotério", "Manutenção de Animais", 86.0, 31.5, "instalacoes/recria.png");

            // SETOR 8
            Local local33 = criar("Horto Medicinal", "Plantas Medicinais", 49.0, 14.0, "instalacoes/horto.png");
            Local local34_2 = criar("Casa de Vegetação 1", "Estufa 1", 37.0, 11.0, "instalacoes/cv1.png"); // Repetido no original, ajustado
            Local local35 = criar("Casa de Vegetação 2", "Estufa 2", 47.0, 17.0, "instalacoes/cv2.png");
            Local local36 = criar("Casa de Vegetação 3", "Estufa 3", 70.0, 44.0, "instalacoes/cv3.png");
            Local local37 = criar("Horto Florestal", "Fazenda", 55.0, 3.0, "instalacoes/fazenda.png");

            List<Local> todosLocais = Arrays.asList(
                local01, local02, local03, local04, local05, local06, local07, local08, local09, local10,
                local11, local12, local13, local14, local15, local16, local17, local18, local19, local20,
                local21, local22, local23, local24, local25, local26, local27, local28, local29, local30,
                local31, local32, local33, local34, local35, local36, local37, local38, local39, local40,
                local41, local42, local43, local44, local45, local46, local47
            );
            repository.saveAll(todosLocais);
            System.out.println("--- 47 LOCAIS SALVOS ---");

            // ==================================================================================
            // 2. CONSTRUÇÃO DO GRAFO - MODO SIMPLIFICADO (ESTRELA)
            // ==================================================================================
            // Objetivo: A Guarita (ID 13) conecta diretamente com TODOS os outros pontos.
            // O usuário "voa" ou anda em linha reta da guarita até o destino.
            
            Local guarita = local13; 

            for (Local destino : todosLocais) {
                // Não conecta a guarita com ela mesma
                if (!destino.getId().equals(guarita.getId())) {
                    // Calcula distância simples (Euclidiana) para o peso não ser zero
                    double dist = calcularDistancia(guarita, destino);
                    conectar(guarita, destino, dist);
                }
            }
            System.out.println("--- MODO ESTRELA ATIVADO: GUARITA CONECTADA A TUDO ---");


            // ==================================================================================
            // 3. (FUTURO) MODO TEIA / MALHA COMPLETA
            // ==================================================================================
            // Descomente este bloco abaixo quando quiser ativar a navegação realista (nó a nó).
            // Certifique-se de comentar o loop "Modo Estrela" acima antes de ativar este.
            /*
            // EIXO PRINCIPAL
            conectar(local13, local14, 60.0);
            conectar(local14, local12, 100.0);
            conectar(local14, local22, 150.0);
            conectar(local14, local29, 30.0);
            conectar(local14, local15, 200.0);

            // MIOLO E PAVILHÕES
            conectar(local15, local30, 20.0);
            conectar(local15, local10, 60.0);
            conectar(local15, local16, 80.0);
            conectar(local10, local09, 70.0);
            conectar(local09, local08, 80.0);
            conectar(local16, local41, 30.0);
            conectar(local16, local32, 40.0); // Bosque
            conectar(local10, local11, 70.0);
            
            // ... (Insira aqui o restante das conexões detalhadas do código anterior) ...
            */
        }
    }

    // Cria o local com dados básicos
    private Local criar(String nome, String desc, Double x, Double y, String img) {
        Local l = new Local();
        l.setNome(nome);
        l.setDescricao(desc);
        l.setCoordenadaX(x);
        l.setCoordenadaY(y);
        l.setUrlImagem(img);
        // Coordenadas geográficas fictícias para não quebrar a lógica antiga se usada
        l.setLatitude(0.0);
        l.setLongitude(0.0);
        l.setDetalhesSalas("Térreo|Recepção;1º Andar|Salas");
        return l;
    }

    private void conectar(Local origem, Local destino, Double distancia) {
        if (origem.getId() != null && destino.getId() != null) {
            grafoService.adicionarConexao(origem.getId(), destino.getId(), distancia);
        }
    }

    // Cálculo simples de distância visual para dar peso ao grafo
    private double calcularDistancia(Local a, Local b) {
        double dx = a.getCoordenadaX() - b.getCoordenadaX();
        double dy = a.getCoordenadaY() - b.getCoordenadaY();
        return Math.sqrt(dx * dx + dy * dy);
    }
}