package com.uesc.mapa_backend.repository;

import com.uesc.mapa_backend.model.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalRepository extends JpaRepository<Local, Long> {
    // Só de estender JpaRepository, já ganhou métodos como:
    // .findAll() (buscar todos)
    // .save() (salvar)
    // .delete() (apagar)
    // Mágica do Spring!
}