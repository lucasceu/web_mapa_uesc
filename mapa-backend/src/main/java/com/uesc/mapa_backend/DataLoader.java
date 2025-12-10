package com.uesc.mapa_backend;

import com.uesc.mapa_backend.model.Local;
import com.uesc.mapa_backend.repository.LocalRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    private final LocalRepository repository;

    public DataLoader(LocalRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Se o banco estiver vazio, vamos adicionar dados
        if (repository.count() == 0) {

            // 01 - Hospital Veterinário
            Local local01 = new Local();
            local01.setNome("Hospital Veterinário");
            local01.setDescricao("Hospital Veterinário da UESC");
            local01.setCoordenadaX(25.0);
            local01.setCoordenadaY(6.0);
            local01.setLatitude(null);
            local01.setLongitude(null);
            local01.setUrlImagem("instalacoes/hvet.png");

            // 02 - Campo de Futebol
            Local local02 = new Local();
            local02.setNome("Campo de Futebol");
            local02.setDescricao("Campo de futebol do campus");
            local02.setCoordenadaX(21.0);
            local02.setCoordenadaY(22.0);
            local02.setUrlImagem("instalacoes/campo.png");

            // 03 - Piscina Semi-Olímpica
            Local local03 = new Local();
            local03.setNome("Piscina Semi-Olímpica");
            local03.setDescricao("Piscina semi-olímpica para atividades aquáticas");
            local03.setCoordenadaX(22.5);
            local03.setCoordenadaY(31.0);
            local03.setUrlImagem("instalacoes/piscina.png");

            // 04 - Pavilhão de Educação Física
            Local local04 = new Local();
            local04.setNome("Pavilhão de Educação Física");
            local04.setDescricao("Infraestrutura esportiva e salas do curso de Ed. Física.");
            local04.setCoordenadaX(12.0);
            local04.setCoordenadaY(31.0);
            local04.setUrlImagem("instalacoes/edufisica.png");
            local04.setDetalhesSalas("Térreo|Ginásio, Quadras cobertas;1º Andar|Salas de aula teóricas, Sala de professores");

            // 05 - Quadra Poliesportiva
            Local local05 = new Local();
            local05.setNome("Quadra Poliesportiva");
            local05.setDescricao("Quadra para prática de diversos esportes");
            local05.setCoordenadaX(18.0);
            local05.setCoordenadaY(37.0);
            local05.setUrlImagem("instalacoes/quadra.png");

            // 06 - Restaurante Universitário
            Local local06 = new Local();
            local06.setNome("Restaurante Universitário");
            local06.setDescricao("Fornece refeições subsidiadas para estudantes e servidores.");
            local06.setCoordenadaX(30.0);
            local06.setCoordenadaY(26.0);
            local06.setUrlImagem("instalacoes/ru.png");
            local06.setDetalhesSalas("Térreo|Refeitório Principal, Caixas;1º Andar|Cozinha Industrial, Administração");

            // 07 - Centro de Microscopia Eletrônica
            Local local07 = new Local();
            local07.setNome("Centro de Microscopia Eletrônica");
            local07.setDescricao("CME - Centro de Microscopia Eletrônica");
            local07.setCoordenadaX(36.0);
            local07.setCoordenadaY(28.0);
            local07.setUrlImagem("instalacoes/cme.png");

            // 08 - Pavilhão Waldir Pires
            Local local08 = new Local();
            local08.setNome("Pavilhão Waldir Pires");
            local08.setDescricao("Pavilhão Waldir Pires - Juizado Modelo");
            local08.setCoordenadaX(40.5);
            local08.setCoordenadaY(24.5);
            local08.setUrlImagem("instalacoes/juizadomodelo.png");

            // 09 - Pavilhão Pedro Calmon (Série 1000)
            Local local09 = new Local();
            local09.setNome("Pavilhão Pedro Calmon");
            local09.setDescricao("Pavilhão de aulas - Licenciaturas e Humanas. Salas série 1xxx.");
            local09.setCoordenadaX(26.0);
            local09.setCoordenadaY(38.0);
            local09.setUrlImagem("instalacoes/pedrocal.png");
            local09.setDetalhesSalas("Térreo|Auditórios, Salas PARFOR, Áreas de convivência;1º Andar|Salas 1101 a 1110 (Licenciaturas e Humanas)");

            // 10 - Pavilhão Adonias Filho (Série 2000)
            Local local10 = new Local();
            local10.setNome("Pavilhão Adonias Filho");
            local10.setDescricao("Pavilhão de aulas e administrativo. Salas série 2xxx.");
            local10.setCoordenadaX(51.0);
            local10.setCoordenadaY(28.0);
            local10.setUrlImagem("instalacoes/adonias.png");
            local10.setDetalhesSalas("Térreo|Protocolo Geral, Núcleos de Pesquisa, Cantina, Banheiros acessíveis;1º Andar|Salas 2101 a 2114;2º Andar|Salas 2201 a 2208, Pós-Graduação");

            // 11 - Pavilhão Professor Max de Menezes (Pós-Graduação)
            Local local11 = new Local();
            local11.setNome("Pavilhão Professor Max de Menezes");
            local11.setDescricao("Uso exclusivo da Pós-Graduação.");
            local11.setCoordenadaX(43.0);
            local11.setCoordenadaY(49.0);
            local11.setUrlImagem("instalacoes/max.png");
            local11.setDetalhesSalas("Térreo|Secretaria, Recepção;1º Andar|Salas de aula, 2 Salas de estudo, Auditório");

            // 12 - Centro de Computação Avançada e Multidisciplinar
            Local local12 = new Local();
            local12.setNome("Centro de Computação Avançada e Multidisciplinar");
            local12.setDescricao("CCAM - Núcleo de Biologia Computacional e Gestão de Informações Biotecnológicas");
            local12.setCoordenadaX(45.5);
            local12.setCoordenadaY(58.0);
            local12.setUrlImagem("instalacoes/nbcgib.png");

            // 13 - Guarita
            Local local13 = new Local();
            local13.setNome("Guarita");
            local13.setDescricao("Entrada principal da Universidade");
            local13.setCoordenadaX(52.0);
            local13.setCoordenadaY(64.0);
            local13.setUrlImagem("instalacoes/guarita.png");

            // 14 - Brasão UESC e Praça das Bandeiras
            Local local14 = new Local();
            local14.setNome("Brasão UESC e Praça das Bandeiras");
            local14.setDescricao("Brasão da UESC e Praça das Bandeiras");
            local14.setCoordenadaX(54.0);
            local14.setCoordenadaY(55.0);
            local14.setUrlImagem("instalacoes/brasao.png");

            // 15 - Torre Administrativa
            Local local15 = new Local();
            local15.setNome("Torre Administrativa - Edifício José Haroldo Castro Vieira");
            local15.setDescricao("Torre Administrativa da UESC");
            local15.setCoordenadaX(54.5);
            local15.setCoordenadaY(22.0);
            local15.setUrlImagem("instalacoes/torre.png");

            // 16 - Pavilhão Jorge Amado (Série 3000)
            Local local16 = new Local();
            local16.setNome("Pavilhão Jorge Amado");
            local16.setDescricao("Maior pavilhão de aulas. Salas série 3xxx.");
            local16.setCoordenadaX(63.0);
            local16.setCoordenadaY(33.0);
            local16.setUrlImagem("instalacoes/jorge.png");
            local16.setDetalhesSalas("Térreo|Salas 3001-3005, Laboratórios, Colegiados, Auditório (153 lugares);1º Andar|Salas 3101 a 3117, Labs de Informática;2º Andar|Salas 3201 a 3216");

            // 17 - Quadra Poliesportiva 2
            Local local17 = new Local();
            local17.setNome("Quadra Poliesportiva 2");
            local17.setDescricao("Segunda quadra poliesportiva do campus");
            local17.setCoordenadaX(62.0);
            local17.setCoordenadaY(60.0);
            local17.setUrlImagem("instalacoes/quadra2.png");

            // 18 - Centro de Biotecnologia e Genética
            Local local18 = new Local();
            local18.setNome("Centro de Biotecnologia e Genética");
            local18.setDescricao("CBG - Centro de Biotecnologia e Genética");
            local18.setCoordenadaX(72.0);
            local18.setCoordenadaY(58.0);
            local18.setUrlImagem("instalacoes/cbg.png");

            // 19 - Pavilhão Agroindústria
            Local local19 = new Local();
            local19.setNome("Pavilhão Agroindústria");
            local19.setDescricao("Pavilhão de Agroindústria");
            local19.setCoordenadaX(75.5);
            local19.setCoordenadaY(29.0);
            local19.setUrlImagem("instalacoes/agro.png");

            // 20 - Instituto de Pesquisas e Análise Físico-Químicas
            Local local20 = new Local();
            local20.setNome("Instituto de Pesquisas e Análise Físico-Químicas");
            local20.setDescricao("Instituto de Pesquisas e Análise Físico-Químicas");
            local20.setCoordenadaX(82.0);
            local20.setCoordenadaY(32.0);
            local20.setUrlImagem("instalacoes/fisicoquimicas.png");

            // 21 - Centro de Pesquisas em Ciências e Tecnologias das Radiações
            Local local21 = new Local();
            local21.setNome("Centro de Pesquisas em Ciências e Tecnologias das Radiações");
            local21.setDescricao("CPqCTR - Centro de Pesquisas em Ciências e Tecnologias das Radiações");
            local21.setCoordenadaX(86.0);
            local21.setCoordenadaY(37.0);
            local21.setUrlImagem("instalacoes/cpqctr.png");

            // 22 - Pavilhão Manoel Fontes Nabuco (Laboratórios)
            Local local22 = new Local();
            local22.setNome("Pavilhão Manoel Fontes Nabuco");
            local22.setDescricao("Foco em Laboratórios de Ciências Biológicas e Saúde.");
            local22.setCoordenadaX(79.0);
            local22.setCoordenadaY(47.0);
            local22.setUrlImagem("instalacoes/manoelnabuco.png");
            local22.setDetalhesSalas("Térreo|Laboratórios (Anatomia Humana, Biologia);1º Andar|Laboratórios de pesquisa");

            // 23 - Biblioteca Central
            Local local23 = new Local();
            local23.setNome("Biblioteca Central");
            local23.setDescricao("Acervo bibliográfico, salas de estudo e laboratórios de informática.");
            local23.setCoordenadaX(82.0);
            local23.setCoordenadaY(63.0);
            local23.setUrlImagem("instalacoes/biblioteca.png");
            local23.setDetalhesSalas("Térreo|Recepção, Acervo Geral, Empréstimo;1º Andar|Salas de Estudo em Grupo, Periódicos;2º Andar|Auditório, Videoteca, Labs de Informática");

            // 24 - Auditório Paulo Souto
            Local local24 = new Local();
            local24.setNome("Auditório Paulo Souto");
            local24.setDescricao("Auditório Paulo Souto");
            local24.setCoordenadaX(86.0);
            local24.setCoordenadaY(74.0);
            local24.setUrlImagem("instalacoes/auditorio.png");

            // 25 - Pavilhão Júlio Cezar de Mattos Cascardo
            Local local25 = new Local();
            local25.setNome("Pavilhão Júlio Cezar de Mattos Cascardo");
            local25.setDescricao("Pavilhão Júlio Cezar de Mattos Cascardo");
            local25.setCoordenadaX(89.0);
            local25.setCoordenadaY(55.0);
            local25.setUrlImagem("instalacoes/cascardo.png");

            // 26 - Pavilhão Prof. Evandro Sena Freire
            Local local26 = new Local();
            local26.setNome("Pavilhão Prof. Evandro Sena Freire");
            local26.setDescricao("Pavilhão de Ciências Exatas");
            local26.setCoordenadaX(70.0);
            local26.setCoordenadaY(19.0);
            local26.setUrlImagem("instalacoes/exatas.png");

            // 27 - Oficina de Manutenção e Estacionamento
            Local local27 = new Local();
            local27.setNome("Oficina de Manutenção e Estacionamento de Veículos Oficiais");
            local27.setDescricao("Oficina de Manutenção e Estacionamento de Veículos Oficiais e Prefeitura do campus");
            local27.setCoordenadaX(70.0);
            local27.setCoordenadaY(27.0);
            local27.setUrlImagem("instalacoes/veiculos.png");

            // 28 - Núcleo de Educação a Distância
            Local local28 = new Local();
            local28.setNome("Núcleo de Educação a Distância e Laboratórios");
            local28.setDescricao("EaD - Núcleo de Educação a Distância e Laboratórios");
            local28.setCoordenadaX(73.5);
            local28.setCoordenadaY(26.0);
            local28.setUrlImagem("instalacoes/ead.png");

            // 29 - Estação de Manutenção e Recria de Animais
            Local local29 = new Local();
            local29.setNome("Estação de Manutenção e Recria de Animais de Laboratório");
            local29.setDescricao("Estação de Manutenção e Recria de Animais de Laboratório");
            local29.setCoordenadaX(84.0);
            local29.setCoordenadaY(40.5);
            local29.setUrlImagem("instalacoes/recria.png");

            // 30 - Placa comemorativa pelos Direitos Humanos
            Local local30 = new Local();
            local30.setNome("Placa comemorativa pelos Direitos Humanos");
            local30.setDescricao("Placa comemorativa pelos Direitos Humanos");
            local30.setCoordenadaX(57.0);
            local30.setCoordenadaY(49.0);
            local30.setUrlImagem("instalacoes/dirhumanos.png");

            // 31 - Placa em homenagem a José Haroldo Castro Vieira
            Local local31 = new Local();
            local31.setNome("Placa em homenagem a José Haroldo Castro Vieira");
            local31.setDescricao("Placa em homenagem a José Haroldo Castro Vieira");
            local31.setCoordenadaX(56.0);
            local31.setCoordenadaY(34.0);
            local31.setUrlImagem("instalacoes/joseharoldo.png");

            // 32 - Base Ambiental
            Local local32 = new Local();
            local32.setNome("Base Ambiental");
            local32.setDescricao("Base Ambiental");
            local32.setCoordenadaX(33.0);
            local32.setCoordenadaY(46.0);
            local32.setUrlImagem("instalacoes/baseambiental.png");

            // 33 - Bosque
            Local local33 = new Local();
            local33.setNome("Bosque");
            local33.setDescricao("Área verde do campus");
            local33.setCoordenadaX(63.0);
            local33.setCoordenadaY(41.0);
            local33.setUrlImagem("instalacoes/bosque.png");

            // 34 - Horto de Plantas Medicinais
            Local local34 = new Local();
            local34.setNome("Horto de Plantas Medicinais");
            local34.setDescricao("Horto de Plantas Medicinais");
            local34.setCoordenadaX(47.0);
            local34.setCoordenadaY(23.0);
            local34.setUrlImagem("instalacoes/horto.png");

            // 35 - Casa de Vegetação 1
            Local local35 = new Local();
            local35.setNome("Casa de Vegetação 1");
            local35.setDescricao("Casa de Vegetação");
            local35.setCoordenadaX(35.0);
            local35.setCoordenadaY(20.0);
            local35.setUrlImagem("instalacoes/cv1.png");

            // 36 - Casa de Vegetação 2
            Local local36 = new Local();
            local36.setNome("Casa de Vegetação 2");
            local36.setDescricao("Casa de Vegetação");
            local36.setCoordenadaX(45.0);
            local36.setCoordenadaY(26.0);
            local36.setUrlImagem("instalacoes/cv2.png");

            // 37 - Casa de Vegetação 3
            Local local37 = new Local();
            local37.setNome("Casa de Vegetação 3");
            local37.setDescricao("Casa de Vegetação");
            local37.setCoordenadaX(68.0);
            local37.setCoordenadaY(53.0);
            local37.setUrlImagem("instalacoes/cv3.png");

            // 38 - Horto Florestal da UESC
            Local local38 = new Local();
            local38.setNome("Horto Florestal da UESC");
            local38.setDescricao("Horto Florestal da UESC - Fazenda");
            local38.setCoordenadaX(53.0);
            local38.setCoordenadaY(12.0);
            local38.setUrlImagem("instalacoes/fazenda.png");

            // 39 - Laboratório Biorrefinarias Integradas (LAPROBIO)
            Local local39 = new Local();
            local39.setNome("Laboratório Biorrefinarias Integradas (LAPROBIO)");
            local39.setDescricao("LAPROBIO - Laboratório Biorrefinarias Integradas");
            local39.setCoordenadaX(80.0);
            local39.setCoordenadaY(30.0);
            local39.setUrlImagem("instalacoes/laprobio.png");

            // 40 - Chocosol
            Local local40 = new Local();
            local40.setNome("Chocosol");
            local40.setDescricao("Chocosol");
            local40.setCoordenadaX(77.6);
            local40.setCoordenadaY(30.8);
            local40.setUrlImagem("instalacoes/chocosol.png");

            // 41 - Cervejaria/Cachaçaria
            Local local41 = new Local();
            local41.setNome("Cervejaria/Cachaçaria");
            local41.setDescricao("Cervejaria e Cachaçaria");
            local41.setCoordenadaX(72.0);
            local41.setCoordenadaY(30.0);
            local41.setUrlImagem("instalacoes/cervejaria.png");

            // 42 - Arena Ramon Vane
            Local local42 = new Local();
            local42.setNome("Arena Ramon Vane");
            local42.setDescricao("Arena Ramon Vane");
            local42.setCoordenadaX(60.5);
            local42.setCoordenadaY(38.0);
            local42.setUrlImagem("instalacoes/arena.png");

            // 43 - Complexo de laboratórios para as Ciências Exatas (CLCE)
            Local local43 = new Local();
            local43.setNome("Complexo de laboratórios para as Ciências Exatas (CLCE)");
            local43.setDescricao("CLCE - Complexo de laboratórios para as Ciências Exatas");
            local43.setCoordenadaX(40.0);
            local43.setCoordenadaY(43.0);
            local43.setUrlImagem("instalacoes/clce.png");

            // 44 - Núcleo de Estudos e Pesquisas Arqueológicas da Bahia (NEPAB)
            Local local44 = new Local();
            local44.setNome("Núcleo de Estudos e Pesquisas Arqueológicas da Bahia (NEPAB)");
            local44.setDescricao("NEPAB - Núcleo de Estudos e Pesquisas Arqueológicas da Bahia");
            local44.setCoordenadaX(36.0);
            local44.setCoordenadaY(41.0);
            local44.setUrlImagem("instalacoes/nepab.png");

            // 45 - Ciências Sociais
            Local local45 = new Local();
            local45.setNome("Ciências Sociais");
            local45.setDescricao("Departamento de Ciências Sociais");
            local45.setCoordenadaX(15.0);
            local45.setCoordenadaY(27.0);
            local45.setUrlImagem("instalacoes/cienciassociais.png");

            // 46 - Germoplasma
            Local local46 = new Local();
            local46.setNome("Germoplasma");
            local46.setDescricao("Banco de Germoplasma");
            local46.setCoordenadaX(31.0);
            local46.setCoordenadaY(21.0);
            local46.setUrlImagem("instalacoes/germoplasma.png");

            // 47 - Laboratório de Pesquisa em Nutrição e Alimentação de Ruminantes (LAPNAR)
            Local local47 = new Local();
            local47.setNome("Laboratório de Pesquisa em Nutrição e Alimentação de Ruminantes (LAPNAR)");
            local47.setDescricao("LAPNAR - Laboratório de Pesquisa em Nutrição e Alimentação de Ruminantes");
            local47.setCoordenadaX(33.2);
            local47.setCoordenadaY(17.0);
            local47.setUrlImagem("instalacoes/lapnar.png");

            // 48 - Crematório
            Local local48 = new Local();
            local48.setNome("Crematório");
            local48.setDescricao("Crematório");
            local48.setCoordenadaX(27.5);
            local48.setCoordenadaY(2.7);
            local48.setUrlImagem("instalacoes/crematorio.png");

            repository.saveAll(Arrays.asList(
                local01, local02, local03, local04, local05, local06, local07, local08, local09, local10,
                local11, local12, local13, local14, local15, local16, local17, local18, local19, local20,
                local21, local22, local23, local24, local25, local26, local27, local28, local29, local30,
                local31, local32, local33, local34, local35, local36, local37, local38, local39, local40,
                local41, local42, local43, local44, local45, local46, local47, local48
            ));
            
            System.out.println("--- 48 LOCAIS CARREGADOS NO BANCO ---");
        }
    }
}
