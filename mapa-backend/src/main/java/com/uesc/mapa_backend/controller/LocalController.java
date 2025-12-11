package com.uesc.mapa_backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping; // Import necessário para o código novo
import org.springframework.web.bind.annotation.RequestMapping; // Imports gerais
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uesc.mapa_backend.model.Local;
import com.uesc.mapa_backend.repository.LocalRepository;
import com.uesc.mapa_backend.service.GrafoService;

@RestController
@RequestMapping("/api/locais")
@CrossOrigin("*") 
public class LocalController {

    // ==================================================================================
    // CÓDIGO NOVO: Injeção de dependência via Construtor (Melhor prática) + GrafoService
    // ==================================================================================
    private final LocalRepository repository;
    private final GrafoService grafoService;

    public LocalController(LocalRepository repository, GrafoService grafoService) {
        this.repository = repository;
        this.grafoService = grafoService;
    }
    // ==================================================================================

    /* ==================================================================================
    CÓDIGO ANTIGO: Injeção via @Autowired no atributo (Field Injection)
    ==================================================================================
    @Autowired
    private LocalRepository repository;
    */

    @GetMapping
    public List<Local> listarTodos() {
        return repository.findAll();
    }

    // ==================================================================================
    // CÓDIGO NOVO: Novo endpoint para calcular rotas
    // ==================================================================================
    @GetMapping("/rota")
    public List<Local> getRota(@RequestParam Long origem, @RequestParam Long destino) {
        return grafoService.calcularMenorRota(origem, destino);
    }
    // ==================================================================================
}