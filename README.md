# Projeto de WEB com Angular e Spring Boot

**Equipe:**

- Henrique Daniel Resende
- João Manoel Fidelis Santos
- Lucas Pereira Céu
- Maria Eduarda Guedes Alves
- Ryan Araujo Ribeiro
- Sara Raquel Pinto Brandao Silva

## Objetivo

Este projeto tem como objetivo a implementação de um mapa aprimorado da UESC, utilizando a mesma imagem estática, a fim de demonstrar os conhecimentos aprendidos na disciplina de WEB através da framework Angular e da ferramenta Spring Boot.

## Estrutura de Pastas Principais

**mapa-backend (Spring Boot)**  
 ┗ src/main                
    ┗ java/com/uesc/mapa_backend/   
        ┣ MapaBackendApplication.java -> classe principal com main, sobe o servidor  
        ┣ DataLoader.java -> preenche o banco com 47 locais na inicialização    
        ┣ controller  
        ┃ ┗ LocalController.java -> endpoint, lista todos os locais  
        ┣ model  
        ┃ ┗ Local.java -> Cria um local (tabela no banco) com todos os seus atributos  
        ┗ repository    
          ┗ LocalRepository.java  -> Cria interface com todo o SQL já criado pela classe extendida

**Fluxo de Navegação**

                    [MapaBackendApplication.java]
                      \          |          /
  [LocalController.java] [DataLoader.java] [LocalRepository.java]  
            |                    |  
  [LocalRepository.java] -> [Local.java]  

**mapa-uesc-front**  
  ┣ src         
  ┃ ┣ assets -> imagem do mapa e dos locais  
  ┃ ┣ app  
  ┃ ┣ ┣ app.component.ts -> componente principal com toda a lógica da tela  
  ┃ ┣ ┗ map.service.ts -> faz a chamada HTTP ao backend  
  ┃ ┣ main.ts -> ponto de entrada que inicializa o AppComponent    
  ┃ ┗ main.server.ts -> usado pelo server.ts para renderização  
  ┗ server.ts ->  renderização do servidor SSR  

**Fluxo de Navegação**

SSR:  
  [server.ts] -> [main.server.ts] -> [app.component.ts] -> [map.service.ts]

Cliente:  
  [main.ts] -> [app.component.ts] -> [map.service.ts]


[Link do Repositório no GitHub](https://github.com/lucasceu/web_mapa_uesc)

## Executar

1. Back-end - Spring Boot
  - Clique em `run` no arquivo do caminho `\web_mapa_uesc\mapa-backend\src\main\java\com\uesc\mapa_backend\MapaBackendApplication.java`

  - Ou pressione F5 em qualquer arquivo Java do back-end

2. Front-end - Angular
  - Na pasta do front-end:

    ```bash
    npm i
    ```

    ```bash
    npm start
    ```
    (ou `ng server`)

    **Acessar**: http://localhost:4200