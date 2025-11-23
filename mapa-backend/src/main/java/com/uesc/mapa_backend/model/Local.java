package com.uesc.mapa_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity // Diz ao Spring que isso é uma tabela no banco de dados
@Data   // O Lombok cria automaticamente os Getters, Setters e toString
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera o ID automático (1, 2, 3...)
    private Long id;

    private String nome;
    
    private String descricao; // Texto explicativo do local

    // NOVO CAMPO: Vamos salvar assim: "Térreo|Salas 1 a 10;1º Andar|Laboratórios"
    // Usamos ";" para separar linhas e "|" para separar Título de Conteúdo
    private String detalhesSalas;
    
    // Coordenadas relativas (0 a 100%) para funcionar em qualquer tamanho de tela
    private Double coordenadaX; 
    private Double coordenadaY;

    private String urlImagem; // Nome do arquivo da imagem ou URL
}