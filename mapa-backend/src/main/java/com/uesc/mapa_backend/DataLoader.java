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
            local01.setLatitude(-14.794715399692587);
            local01.setLongitude(-39.17497109316048);
            local01.setUrlImagem("instalacoes/hvet.png");

            // 02 - Campo de Futebol
            Local local02 = new Local();
            local02.setNome("Campo de Futebol");
            local02.setDescricao("Campo de futebol do campus");
            local02.setCoordenadaX(21.0);
            local02.setCoordenadaY(22.0);
            local02.setUrlImagem("instalacoes/campo.png");
            local02.setLatitude(-14.796811148521758);
            local02.setLongitude(-39.17498680264272);

            // 03 - Piscina Semi-Olímpica
            Local local03 = new Local();
            local03.setNome("Piscina Semi-Olímpica");
            local03.setDescricao("Piscina semi-olímpica para atividades aquáticas");
            local03.setCoordenadaX(22.5);
            local03.setCoordenadaY(31.0);
            local03.setUrlImagem("instalacoes/piscina.png");
            local03.setLatitude(-14.79728262095909);
            local03.setLongitude(-39.175038843743785);

            // 04 - Pavilhão de Educação Física
            Local local04 = new Local();
            local04.setNome("Pavilhão de Educação Física");
            local04.setDescricao("Infraestrutura esportiva e salas do curso de Ed. Física.");
            local04.setCoordenadaX(12.0);
            local04.setCoordenadaY(31.0);
            local04.setUrlImagem("instalacoes/edufisica.png");
            local04.setDetalhesSalas("Térreo|Ginásio, Quadras cobertas;1º Andar|Salas de aula teóricas, Sala de professores");
            local04.setLatitude(-14.797505095351823);
            local04.setLongitude(-39.175704185076576);

            // 05 - Quadra Poliesportiva
            Local local05 = new Local();
            local05.setNome("Quadra Poliesportiva");
            local05.setDescricao("Quadra para prática de diversos esportes");
            local05.setCoordenadaX(18.0);
            local05.setCoordenadaY(37.0);
            local05.setUrlImagem("instalacoes/quadra.png");
            local05.setLatitude(-14.797696916315864);
            local05.setLongitude(-39.17528212319101);

            // 06 - Restaurante Universitário
            Local local06 = new Local();
            local06.setNome("Restaurante Universitário");
            local06.setDescricao("Fornece refeições subsidiadas para estudantes e servidores.");
            local06.setCoordenadaX(30.0);
            local06.setCoordenadaY(26.0);
            local06.setUrlImagem("instalacoes/ru.png");
            local06.setDetalhesSalas("Térreo|Refeitório Principal, Caixas;1º Andar|Cozinha Industrial, Administração");
            local06.setLatitude(-14.796777878278638);
            local06.setLongitude(-39.17426454945874);

            // 07 - Centro de Microscopia Eletrônica
            Local local07 = new Local();
            local07.setNome("Centro de Microscopia Eletrônica");
            local07.setDescricao("CME - Centro de Microscopia Eletrônica");
            local07.setCoordenadaX(36.0);
            local07.setCoordenadaY(28.0);
            local07.setUrlImagem("instalacoes/cme.png");
            local07.setLatitude(-14.796480088128027);
            local07.setLongitude(-39.174039383589616);

            // 08 - Pavilhão Waldir Pires
            Local local08 = new Local();
            local08.setNome("Pavilhão Waldir Pires");
            local08.setDescricao("Pavilhão Waldir Pires - Juizado Modelo");
            local08.setCoordenadaX(40.5);
            local08.setCoordenadaY(24.5);
            local08.setUrlImagem("instalacoes/juizadomodelo.png");
            local08.setLatitude(-14.79715823782434);
            local08.setLongitude(-39.17351949763906);

            // 09 - Pavilhão Pedro Calmon (Série 1000)
            Local local09 = new Local();
            local09.setNome("Pavilhão Pedro Calmon");
            local09.setDescricao("Pavilhão de aulas - Licenciaturas e Humanas. Salas série 1xxx.");
            local09.setCoordenadaX(26.0);
            local09.setCoordenadaY(38.0);
            local09.setUrlImagem("instalacoes/pedrocal.png");
            local09.setLatitude(-14.79755731726017);
            local09.setLongitude(-39.1736664135392);
            local09.setDetalhesSalas("Térreo|Auditórios, Salas PARFOR, Áreas de convivência;1º Andar|Salas 1101 a 1110 (Licenciaturas e Humanas)");

            // 10 - Pavilhão Adonias Filho (Série 2000)
            Local local10 = new Local();
            local10.setNome("Pavilhão Adonias Filho");
            local10.setDescricao("Pavilhão de aulas e administrativo. Salas série 2xxx.");
            local10.setCoordenadaX(51.0);
            local10.setCoordenadaY(28.0);
            local10.setUrlImagem("instalacoes/adonias.png");
            local10.setDetalhesSalas("Térreo|Protocolo Geral, Núcleos de Pesquisa, Cantina, Banheiros acessíveis;1º Andar|Salas 2101 a 2114;2º Andar|Salas 2201 a 2208, Pós-Graduação");
            local10.setLatitude(-14.797295277134044);
            local10.setLongitude(-39.17328953247696);

            // 11 - Pavilhão Professor Max de Menezes (Pós-Graduação)
            Local local11 = new Local();
            local11.setNome("Pavilhão Professor Max de Menezes");
            local11.setDescricao("Uso exclusivo da Pós-Graduação.");
            local11.setCoordenadaX(43.0);
            local11.setCoordenadaY(49.0);
            local11.setUrlImagem("instalacoes/max.png");
            local11.setDetalhesSalas("Térreo|Secretaria, Recepção;1º Andar|Salas de aula, 2 Salas de estudo, Auditório");
            local11.setLatitude(-14.797995339301362);
            local11.setLongitude(-39.172837815815946);

            // 12 - Centro de Computação Avançada e Multidisciplinar
            Local local12 = new Local();
            local12.setNome("Centro de Computação Avançada e Multidisciplinar");
            local12.setDescricao("CCAM - Núcleo de Biologia Computacional e Gestão de Informações Biotecnológicas");
            local12.setCoordenadaX(45.5);
            local12.setCoordenadaY(58.0);
            local12.setUrlImagem("instalacoes/nbcgib.png");
            local12.setLatitude(-14.798183744110384);
            local12.setLongitude(-39.17256107020449);

            // 13 - Guarita
            Local local13 = new Local();
            local13.setNome("Guarita");
            local13.setDescricao("Entrada principal da Universidade");
            local13.setCoordenadaX(52.0);
            local13.setCoordenadaY(64.0);
            local13.setUrlImagem("instalacoes/guarita.png");
            local13.setLatitude(-14.798539807533885);
            local13.setLongitude(-39.17223883376562);

            // 14 - Brasão UESC e Praça das Bandeiras
            Local local14 = new Local();
            local14.setNome("Brasão UESC e Praça das Bandeiras");
            local14.setDescricao("Brasão da UESC e Praça das Bandeiras");
            local14.setCoordenadaX(54.0);
            local14.setCoordenadaY(55.0);
            local14.setUrlImagem("instalacoes/brasao.png");
            local14.setLatitude(-14.798092256084926);
            local14.setLongitude(-39.17223553652922);

            // 15 - Torre Administrativa
            Local local15 = new Local();
            local15.setNome("Torre Administrativa - Edifício José Haroldo Castro Vieira");
            local15.setDescricao("Torre Administrativa da UESC");
            local15.setCoordenadaX(54.5);
            local15.setCoordenadaY(22.0);
            local15.setUrlImagem("instalacoes/torre.png");
            local15.setLatitude(-14.796711480419622);
            local15.setLongitude(-39.17225695039043);

            // 16 - Pavilhão Jorge Amado (Série 3000)
            Local local16 = new Local();
            local16.setNome("Pavilhão Jorge Amado");
            local16.setDescricao("Maior pavilhão de aulas. Salas série 3xxx.");
            local16.setCoordenadaX(63.0);
            local16.setCoordenadaY(33.0);
            local16.setUrlImagem("instalacoes/jorge.png");
            local16.setDetalhesSalas("Térreo|Salas 3001-3005, Laboratórios, Colegiados, Auditório (153 lugares);1º Andar|Salas 3101 a 3117, Labs de Informática;2º Andar|Salas 3201 a 3216");
            local16.setLatitude(-14.797204883189423);
            local16.setLongitude(-39.17112135409294);

            // 17 - Centro de Biotecnologia e Genética
            Local local17 = new Local();
            local17.setNome("Centro de Biotecnologia e Genética");
            local17.setDescricao("CBG - Centro de Biotecnologia e Genética");
            local17.setCoordenadaX(72.0);
            local17.setCoordenadaY(58.0);
            local17.setUrlImagem("instalacoes/cbg.png");
            local17.setLatitude(-14.797819941860505);
            local17.setLongitude(-39.17104912251621);

            // 18 - Pavilhão Agroindústria
            Local local18 = new Local();
            local18.setNome("Pavilhão Agroindústria");
            local18.setDescricao("Pavilhão de Agroindústria");
            local18.setCoordenadaX(75.5);
            local18.setCoordenadaY(29.0);
            local18.setUrlImagem("instalacoes/agro.png");
            local18.setLatitude(-14.796718977816635);
            local18.setLongitude(-39.171138837884676);

            // 19 - Instituto de Pesquisas e Análise Físico-Químicas
            Local local19 = new Local();
            local19.setNome("Instituto de Pesquisas e Análise Físico-Químicas");
            local19.setDescricao("Instituto de Pesquisas e Análise Físico-Químicas");
            local19.setCoordenadaX(82.0);
            local19.setCoordenadaY(32.0);
            local19.setUrlImagem("instalacoes/fisicoquimicas.png");
            local19.setLatitude(-14.79715844699731);
            local19.setLongitude(-39.17069476804524);

            // 20 - Centro de Pesquisas em Ciências e Tecnologias das Radiações
            Local local20 = new Local();
            local20.setNome("Centro de Pesquisas em Ciências e Tecnologias das Radiações");
            local20.setDescricao("CPqCTR - Centro de Pesquisas em Ciências e Tecnologias das Radiações");
            local20.setCoordenadaX(86.0);
            local20.setCoordenadaY(37.0);
            local20.setUrlImagem("instalacoes/cpqctr.png");
            local20.setLatitude(-14.797166547808953);
            local20.setLongitude(-39.170246508875444);

            // 21 - Pavilhão Manoel Fontes Nabuco
            Local local21 = new Local();
            local21.setNome("Pavilhão Manoel Fontes Nabuco");
            local21.setDescricao("Foco em Laboratórios de Ciências Biológicas e Saúde.");
            local21.setCoordenadaX(79.0);
            local21.setCoordenadaY(47.0);
            local21.setUrlImagem("instalacoes/manoelnabuco.png");
            local21.setDetalhesSalas("Térreo|Laboratórios (Anatomia Humana, Biologia);1º Andar|Laboratórios de pesquisa");
            local21.setLatitude(-14.797487468809393);
            local21.setLongitude(-39.17066109397524);

            // 22 - Biblioteca Central
            Local local22 = new Local();
            local22.setNome("Biblioteca Central");
            local22.setDescricao("Acervo bibliográfico, salas de estudo e laboratórios de informática.");
            local22.setCoordenadaX(82.0);
            local22.setCoordenadaY(63.0);
            local22.setUrlImagem("instalacoes/biblioteca.png");
            local22.setDetalhesSalas("Térreo|Recepção, Acervo Geral, Empréstimo;1º Andar|Salas de Estudo em Grupo, Periódicos;2º Andar|Auditório, Videoteca, Labs de Informática");
            local22.setLatitude(-14.798442993665756);
            local22.setLongitude(-39.1708471161852);

            // 23 - Auditório Paulo Souto
            Local local23 = new Local();
            local23.setNome("Auditório Paulo Souto");
            local23.setDescricao("Auditório Paulo Souto");
            local23.setCoordenadaX(86.0);
            local23.setCoordenadaY(74.0);
            local23.setUrlImagem("instalacoes/auditorio.png");
            local23.setLatitude(-14.798505554089076);
            local23.setLongitude(-39.17049293196931);

            // 24 - Pavilhão Júlio Cezar de Mattos Cascardo
            Local local24 = new Local();
            local24.setNome("Pavilhão Júlio Cezar de Mattos Cascardo");
            local24.setDescricao("Pavilhão Júlio Cezar de Mattos Cascardo");
            local24.setCoordenadaX(89.0);
            local24.setCoordenadaY(55.0);
            local24.setUrlImagem("instalacoes/cascardo.png");
            local24.setLatitude(-14.798049722382023);
            local24.setLongitude(-39.17038154494663);

            // 25 - Pavilhão Prof. Evandro Sena Freire
            Local local25 = new Local();
            local25.setNome("Pavilhão Prof. Evandro Sena Freire");
            local25.setDescricao("Pavilhão de Ciências Exatas");
            local25.setCoordenadaX(70.0);
            local25.setCoordenadaY(19.0);
            local25.setUrlImagem("instalacoes/exatas.png");
            local25.setLatitude(-14.796306889170097);
            local25.setLongitude(-39.1716685673339);

            // 26 - Oficina de Manutenção e Estacionamento de Veículos Oficiais
            Local local26 = new Local();
            local26.setNome("Oficina de Manutenção e Estacionamento de Veículos Oficiais");
            local26.setDescricao("Oficina de Manutenção e Estacionamento de Veículos Oficiais e Prefeitura do campus");
            local26.setCoordenadaX(70.0);
            local26.setCoordenadaY(27.0);
            local26.setUrlImagem("instalacoes/veiculos.png");
            local26.setLatitude(-14.79655916344517);
            local26.setLongitude(-39.17150222650753);

            // 27 - Núcleo de Educação a Distância e Laboratórios
            Local local27 = new Local();
            local27.setNome("Núcleo de Educação a Distância e Laboratórios");
            local27.setDescricao("EaD - Núcleo de Educação a Distância e Laboratórios");
            local27.setCoordenadaX(73.5);
            local27.setCoordenadaY(26.0);
            local27.setUrlImagem("instalacoes/ead.png");
            local27.setLatitude(-14.796601565746421);
            local27.setLongitude(-39.17117791317171);

            // 28 - Estação de Manutenção e Recria de Animais de Laboratório
            Local local28 = new Local();
            local28.setNome("Estação de Manutenção e Recria de Animais de Laboratório");
            local28.setDescricao("Estação de Manutenção e Recria de Animais de Laboratório");
            local28.setCoordenadaX(84.0);
            local28.setCoordenadaY(40.5);
            local28.setUrlImagem("instalacoes/recria.png");
            local28.setLatitude(-14.797257477364084);
            local28.setLongitude(-39.170336424263965);

            // 29 - Placa comemorativa pelos Direitos Humanos
            Local local29 = new Local();
            local29.setNome("Placa comemorativa pelos Direitos Humanos");
            local29.setDescricao("Placa comemorativa pelos Direitos Humanos");
            local29.setCoordenadaX(57.0);
            local29.setCoordenadaY(49.0);
            local29.setUrlImagem("instalacoes/dirhumanos.png");
            local29.setLatitude(-14.79796384151325);
            local29.setLongitude(-39.17216291184032);

            // 30 - Placa em homenagem a José Haroldo Castro Vieira
            Local local30 = new Local();
            local30.setNome("Placa em homenagem a José Haroldo Castro Vieira");
            local30.setDescricao("Placa em homenagem a José Haroldo Castro Vieira");
            local30.setCoordenadaX(56.0);
            local30.setCoordenadaY(34.0);
            local30.setUrlImagem("instalacoes/joseharoldo.png");
            local30.setLatitude(-14.796888527325502);
            local30.setLongitude(-39.17227054414535);

            // 31 - Base Ambiental
            Local local31 = new Local();
            local31.setNome("Base Ambiental");
            local31.setDescricao("Base Ambiental");
            local31.setCoordenadaX(33.0);
            local31.setCoordenadaY(46.0);
            local31.setUrlImagem("instalacoes/baseambiental.png");
            local31.setLatitude(-14.797824588766616);
            local31.setLongitude(-39.17387792990232);

            // 32 - Bosque
            Local local32 = new Local();
            local32.setNome("Bosque");
            local32.setDescricao("Área verde do campus");
            local32.setCoordenadaX(63.0);
            local32.setCoordenadaY(41.0);
            local32.setUrlImagem("instalacoes/bosque.png");
            local32.setLatitude(-14.797299474975617);
            local32.setLongitude(-39.171713448403665);

            // 33 - Horto de Plantas Medicinais
            Local local33 = new Local();
            local33.setNome("Horto de Plantas Medicinais");
            local33.setDescricao("Horto de Plantas Medicinais");
            local33.setCoordenadaX(47.0);
            local33.setCoordenadaY(23.0);
            local33.setUrlImagem("instalacoes/horto.png");
            local33.setLatitude(-14.796640891597978);
            local33.setLongitude(-39.17313034538136);

            // 34 - Casa de Vegetação 1
            Local local34 = new Local();
            local34.setNome("Casa de Vegetação 1");
            local34.setDescricao("Casa de Vegetação");
            local34.setCoordenadaX(35.0);
            local34.setCoordenadaY(20.0);
            local34.setUrlImagem("instalacoes/cv1.png");
            local34.setLatitude(-14.796320773354871);
            local34.setLongitude(-39.17410310544157);

            // 35 - Casa de Vegetação 2
            Local local35 = new Local();
            local35.setNome("Casa de Vegetação 2");
            local35.setDescricao("Casa de Vegetação");
            local35.setCoordenadaX(45.0);
            local35.setCoordenadaY(26.0);
            local35.setUrlImagem("instalacoes/cv2.png");
            local35.setLatitude(-14.796580513157114);
            local35.setLongitude(-39.17316582810951);

            // 36 - Casa de Vegetação 3
            Local local36 = new Local();
            local36.setNome("Casa de Vegetação 3");
            local36.setDescricao("Casa de Vegetação");
            local36.setCoordenadaX(68.0);
            local36.setCoordenadaY(53.0);
            local36.setUrlImagem("instalacoes/cv3.png");
            local36.setLatitude(-14.797799632653298);
            local36.setLongitude(-39.171486501969646);

            // 37 - Horto Florestal da UESC
            Local local37 = new Local();
            local37.setNome("Horto Florestal da UESC");
            local37.setDescricao("Horto Florestal da UESC - Fazenda");
            local37.setCoordenadaX(53.0);
            local37.setCoordenadaY(12.0);
            local37.setUrlImagem("instalacoes/fazenda.png");
            local37.setLatitude(-14.796140929225741);
            local37.setLongitude(-39.172481807140144);

            // 38 - Laboratório Biorrefinarias Integradas (LAPROBIO)
            Local local38 = new Local();
            local38.setNome("Laboratório Biorrefinarias Integradas (LAPROBIO)");
            local38.setDescricao("LAPROBIO - Laboratório Biorrefinarias Integradas");
            local38.setCoordenadaX(80.0);
            local38.setCoordenadaY(30.0);
            local38.setUrlImagem("instalacoes/laprobio.png");
            local38.setLatitude(-14.79690410613677);
            local38.setLongitude(-39.17065216777301);

            // 39 - Chocosol
            Local local39 = new Local();
            local39.setNome("Chocosol");
            local39.setDescricao("Chocosol");
            local39.setCoordenadaX(77.6);
            local39.setCoordenadaY(30.8);
            local39.setUrlImagem("instalacoes/chocosol.png");
            local39.setLatitude(-14.796938615688415);
            local39.setLongitude(-39.170804700434275);

            // 40 - Cervejaria/Cachaçaria
            Local local40 = new Local();
            local40.setNome("Cervejaria/Cachaçaria");
            local40.setDescricao("Cervejaria e Cachaçaria");
            local40.setCoordenadaX(72.0);
            local40.setCoordenadaY(30.0);
            local40.setUrlImagem("instalacoes/cervejaria.png");
            local40.setLatitude(-14.796666523101827);
            local40.setLongitude(-39.1713390923085);

            // 41 - Arena Ramon Vane
            Local local41 = new Local();
            local41.setNome("Arena Ramon Vane");
            local41.setDescricao("Arena Ramon Vane");
            local41.setCoordenadaX(60.5);
            local41.setCoordenadaY(38.0);
            local41.setUrlImagem("instalacoes/arena.png");
            local41.setLatitude(-14.797351339751792);
            local41.setLongitude(-39.17184502544748);

            // 42 - Complexo de laboratórios para as Ciências Exatas (CLCE)
            Local local42 = new Local();
            local42.setNome("Complexo de laboratórios para as Ciências Exatas (CLCE)");
            local42.setDescricao("CLCE - Complexo de laboratórios para as Ciências Exatas");
            local42.setCoordenadaX(40.0);
            local42.setCoordenadaY(43.0);
            local42.setUrlImagem("instalacoes/clce.png");
            local42.setLatitude(-14.79787653211272);
            local42.setLongitude(-39.17307347634382);

            // 43 - Núcleo de Estudos e Pesquisas Arqueológicas da Bahia (NEPAB)
            Local local43 = new Local();
            local43.setNome("Núcleo de Estudos e Pesquisas Arqueológicas da Bahia (NEPAB)");
            local43.setDescricao("NEPAB - Núcleo de Estudos e Pesquisas Arqueológicas da Bahia");
            local43.setCoordenadaX(36.0);
            local43.setCoordenadaY(41.0);
            local43.setUrlImagem("instalacoes/nepab.png");
            local43.setLatitude(-14.797628425641692);
            local43.setLongitude(-39.173501172327924);

            // 44 - Ciências Sociais
            Local local44 = new Local();
            local44.setNome("Ciências Sociais");
            local44.setDescricao("Departamento de Ciências Sociais");
            local44.setCoordenadaX(15.0);
            local44.setCoordenadaY(27.0);
            local44.setUrlImagem("instalacoes/cienciassociais.png");
            local44.setLatitude(-14.797321626923102);
            local44.setLongitude(-39.175617577727486);

            // 45 - Germoplasma
            Local local45 = new Local();
            local45.setNome("Germoplasma");
            local45.setDescricao("Banco de Germoplasma");
            local45.setCoordenadaX(31.0);
            local45.setCoordenadaY(21.0);
            local45.setUrlImagem("instalacoes/germoplasma.png");
            local45.setLatitude(-14.796328716205082);
            local45.setLongitude(-39.17460268385288);

            // 46 - Laboratório de Pesquisa em Nutrição e Alimentação de Ruminantes (LAPNAR)
            Local local46 = new Local();
            local46.setNome("Laboratório de Pesquisa em Nutrição e Alimentação de Ruminantes (LAPNAR)");
            local46.setDescricao("LAPNAR - Laboratório de Pesquisa em Nutrição e Alimentação de Ruminantes");
            local46.setCoordenadaX(33.2);
            local46.setCoordenadaY(17.0);
            local46.setUrlImagem("instalacoes/lapnar.png");
            local46.setLatitude(-14.795271469341884);
            local46.setLongitude(-39.174759252563824);

            // 47 - Crematório
            Local local47 = new Local();
            local47.setNome("Crematório");
            local47.setDescricao("Crematório");
            local47.setCoordenadaX(27.5);
            local47.setCoordenadaY(2.7);
            local47.setUrlImagem("instalacoes/crematorio.png");
            local47.setLatitude(-14.794292489437364);
            local47.setLongitude(-39.17553671978207);

            repository.saveAll(Arrays.asList(
                local01, local02, local03, local04, local05, local06, local07, local08, local09, local10,
                local11, local12, local13, local14, local15, local16, local17, local18, local19, local20,
                local21, local22, local23, local24, local25, local26, local27, local28, local29, local30,
                local31, local32, local33, local34, local35, local36, local37, local38, local39, local40,
                local41, local42, local43, local44, local45, local46, local47
            ));
            
            System.out.println("--- 47 LOCAIS CARREGADOS NO BANCO ---");
        }
    }
}
