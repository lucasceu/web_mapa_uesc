# 🗺️ Projeto de WEB - Mapa Interativo UESC

Este projeto consiste em um sistema de navegação e visualização espacial do campus da UESC (Universidade Estadual de Santa Cruz). Utiliza uma arquitetura moderna com Spring Boot no Back-end para processamento de rotas e Angular (v17+) no Front-end para renderização interativa.

Repositório: https://github.com/lucasceu/web_mapa_uesc

---

## 👥 Equipe
- Henrique Daniel Resende
- João Manoel Fidelis Santos
- Lucas Pereira Céu
- Maria Eduarda Guedes Alves
- Ryan Araujo Ribeiro
- Sara Raquel Pinto Brandao Silva

---

## 🎯 Objetivo
Implementar um mapa aprimorado da UESC utilizando uma imagem estática como base, permitindo localizar prédios e traçar rotas otimizadas entre a guarita e qualquer ponto do campus.

---

## 🚀 Funcionalidades Implementadas

### Back-end (Spring Boot)
- Algoritmo de Dijkstra.
- API REST (GET /api/locais, GET /api/locais/rota).
- DataLoader com 47 locais e conexões.
- Compatibilidade Java 21.

### Front-end (Angular)
- Suporte SSR com isPlatformBrowser.
- Rota desenhada em SVG via path.
- Pinos interativos com mudança de cor.
- Modais responsivos.
- Trava de proporção CSS.

---

## 📂 Estrutura do Projeto

### Back-end (mapa-backend)
    src/main/java/com/uesc/mapa_backend/
    ┣ controller/
    ┃ ┗ LocalController.java
    ┣ model/
    ┃ ┗ Local.java
    ┣ repository/
    ┃ ┗ LocalRepository.java
    ┣ service/
    ┃ ┗ GrafoService.java
    ┣ DataLoader.java
    ┗ MapaBackendApplication.java

### Front-end (mapa-uesc-front)
    src/
    ┣ assets/
    ┣ app/
    ┃ ┣ app.component.ts
    ┃ ┣ app.component.html
    ┃ ┣ app.component.scss
    ┃ ┗ map.service.ts
    ┣ main.ts
    ┗ main.server.ts

---

## 🛠️ Instalação e Execução

### Requisitos
- Java JDK 21
- Node.js 18+

### 1. Executar o Back-end
    Rodar classe:
    src/main/java/com/uesc/mapa_backend/MapaBackendApplication.java

Acesso:
    http://localhost:8080

### 2. Executar o Front-end
    npm install
    npm start

Acesso:
    http://localhost:4200

---

## 🔧 Debug e Manutenção (Mapslaoq)

### 1. Pinos desalinhados
Manter no SCSS:
    aspect-ratio: 1083 / 755;
    object-fit: contain;
    object-position: top left;

Ajustar coordenadas no DataLoader.criar().

### 2. Rota não aparece
Grafo desconexo. Verificar conexões via conectar() no DataLoader.java.

### 3. Erro "window is not defined"
    if (isPlatformBrowser(this.platformId)) {
        // uso de window, document, localStorage
    }

### 4. Conflitos Git
Branch estável: production.  
Priorizar lógica de rotas e SSR no app.component.ts.

---

Fim do README.md.
