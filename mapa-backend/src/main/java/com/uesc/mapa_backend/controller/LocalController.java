package com.uesc.mapa_backend.controller;

import com.uesc.mapa_backend.model.Local;
import com.uesc.mapa_backend.repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/locais") // O endereço será http://localhost:8080/api/locais
@CrossOrigin("*") // Libera o acesso para qualquer front-end (Angular)
public class LocalController {

    @Autowired
    private LocalRepository repository;

    // Método para listar todos os locais do mapa
    @GetMapping
    public List<Local> listarTodos() {
        return repository.findAll();
    }
}