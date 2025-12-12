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
            System.out.println("--- INICIANDO CARGA: MODO TEIA (Caminho Realista) ---");

            // ==================================================================================
            // 1. CRIAÇÃO DOS LOCAIS (MANTENDO SEUS VALORES ORIGINAIS)
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
            Local local13 = criar("Guarita", "Entrada Principal", 54.0, 55.0, "instalacoes/guarita.png"); 
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
            Local local34_2 = criar("Casa de Vegetação 1", "Estufa 1", 37.0, 11.0, "instalacoes/cv1.png");
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
            // 2. CONSTRUÇÃO DA TEIA (CAMINHO PONTO A PONTO)
            // ==================================================================================
            
            // --- Eixo Principal (A espinha dorsal) ---
            conectar(local13, local14, 50.0);  // Guarita -> Praça
            conectar(local14, local15, 100.0); // Praça -> Torre Adm
            conectar(local14, local29, 30.0);  // Praça -> Placa DH
            conectar(local14, local12, 120.0); // Praça -> CCAM (lado direito)
            conectar(local14, local22, 180.0); // Praça -> Biblioteca (lado esquerdo)

            // --- Miolo (Pavilhões) ---
            conectar(local15, local10, 50.0);  // Torre -> Adonias
            conectar(local15, local16, 60.0);  // Torre -> Jorge Amado
            conectar(local15, local30, 20.0);  // Torre -> Placa José Haroldo
            conectar(local10, local09, 60.0);  // Adonias -> Pedro Calmon
            conectar(local09, local08, 70.0);  // Pedro Calmon -> Waldir Pires
            conectar(local16, local41, 30.0);  // Jorge Amado -> Arena
            
            // --- Conexões Transversais ---
            conectar(local16, local18, 50.0);  // Jorge Amado -> Agroindústria
            conectar(local16, local32, 40.0);  // Jorge Amado -> Bosque
            conectar(local32, local31, 50.0);  // Bosque -> Base Ambiental
            conectar(local31, local43, 30.0);  // Base Ambiental -> NEPAB
            conectar(local43, local42, 30.0);  // NEPAB -> CLCE
            conectar(local42, local11, 40.0);  // CLCE -> Max
            conectar(local11, local10, 60.0);  // Max -> Adonias
            conectar(local11, local12, 50.0);  // Max -> CCAM
            
            // --- Esquerda (Serviços e Esportes) ---
            conectar(local09, local06, 100.0); // Pedro Calmon -> RU
            conectar(local08, local06, 50.0);  // Waldir Pires -> RU
            conectar(local06, local07, 30.0);  // RU -> CME
            conectar(local06, local05, 80.0);  // RU -> Quadra
            conectar(local06, local34, 40.0);  // RU -> Estufa 1
            conectar(local06, local46, 50.0);  // RU -> LAPNAR
            conectar(local06, local45, 40.0);  // RU -> Germoplasma
            
            conectar(local05, local03, 30.0);  // Quadra -> Piscina
            conectar(local03, local04, 30.0);  // Piscina -> Ed. Física
            conectar(local03, local02, 50.0);  // Piscina -> Campo
            conectar(local04, local44, 40.0);  // Ed. Física -> C. Sociais
            conectar(local02, local01, 150.0); // Campo -> Hosp. Veterinário
            conectar(local01, local47, 200.0); // Hosp. Vet -> Crematório

            // --- Direita (Saúde e Tecnologia) ---
            conectar(local15, local25, 100.0); // Torre -> Exatas
            conectar(local25, local26, 40.0);  // Exatas -> Veículos
            conectar(local26, local27, 30.0);  // Veículos -> EaD
            conectar(local27, local40, 30.0);  // EaD -> Cervejaria
            conectar(local40, local18, 20.0);  // Cervejaria -> Agro
            conectar(local18, local19, 60.0);  // Agro -> IPAF
            conectar(local19, local39, 20.0);  // IPAF -> Chocosol
            conectar(local39, local38, 20.0);  // Chocosol -> LAPROBIO
            conectar(local38, local21, 30.0);  // LAPROBIO -> Nabuco

            // --- Fundo Leste (Biblioteca) ---
            conectar(local22, local23, 50.0);  // Biblio -> Auditório
            conectar(local22, local24, 70.0);  // Biblio -> Cascardo
            conectar(local22, local21, 100.0); // Biblio -> Nabuco
            conectar(local23, local20, 80.0);  // Auditório -> CPqCTR
            conectar(local20, local28, 40.0);  // CPqCTR -> Biotério
            conectar(local24, local20, 60.0);  // Cascardo -> CPqCTR

            // --- Fundo (Conexão final) ---
            conectar(local12, local17, 80.0);  // CCAM -> CBG
            conectar(local17, local36, 40.0);  // CBG -> Estufa 3
            conectar(local17, local24, 120.0); // CBG -> Cascardo (Caminho de terra)

            // --- Hortos e Distantes ---
            conectar(local10, local33, 50.0);  // Adonias -> Horto Med
            conectar(local33, local35, 20.0);  // Horto Med -> Estufa 2
            conectar(local26, local37, 300.0); // Veículos -> Fazenda
            conectar(local15, local37, 400.0); // Torre -> Fazenda

            System.out.println("--- MODO TEIA ATIVADO: PONTOS INTERLIGADOS ---");
        }
    }

    // Cria o local com dados básicos (MANTENDO SUA LÓGICA X-1, Y+6)
    private Local criar(String nome, String desc, Double x, Double y, String img) {
        Local l = new Local();
        l.setNome(nome);
        l.setDescricao(desc);
        l.setCoordenadaX(x - 1);
        l.setCoordenadaY(y + 6); 
        l.setUrlImagem(img);
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
}