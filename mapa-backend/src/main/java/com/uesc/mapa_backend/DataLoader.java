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
            
            Local biblioteca = new Local();
            biblioteca.setNome("Biblioteca Central (Torre)");
            biblioteca.setDescricao("Acervo bibliográfico, salas de estudo e laboratórios de informática.");
            biblioteca.setCoordenadaX(87.46); 
            biblioteca.setCoordenadaY(60.83);
            biblioteca.setUrlImagem("biblioteca.jpg");
            // Dados no formato: Andar|O que tem lá ; Próximo Andar|O que tem lá
            biblioteca.setDetalhesSalas("Térreo|Recepção e Acervo Geral;1º Andar|Salas de Estudo em Grupo;2º Andar|Auditório e Videoteca");

            Local ru = new Local();
            ru.setNome("Restaurante Universitário (RU)");
            ru.setDescricao("Fornece refeições subsidiadas para estudantes e servidores.");
            ru.setCoordenadaX(28.50);
            ru.setCoordenadaY(30.69);
            ru.setUrlImagem("ru.jpg");
            ru.setDetalhesSalas("Térreo|Refeitório Principal;1º Andar|Cozinha Industrial e Administração");

            Local pavilhao = new Local();
            pavilhao.setNome("Pavilhão de Educação Física");
            pavilhao.setDescricao("Quadras poliesportivas e piscina.");
            pavilhao.setCoordenadaX(8.37);
            pavilhao.setCoordenadaY(33.73);
            pavilhao.setUrlImagem("educacao_fisica.jpg");
            pavilhao.setDetalhesSalas("Térreo|Quadra Poliesportiva e Piscina;1º Andar|Arquibancada e Vestiários");

            repository.saveAll(Arrays.asList(biblioteca, ru, pavilhao));
            System.out.println("--- DADOS DE TESTE CARREGADOS NO BANCO ---");
        }
    }
}