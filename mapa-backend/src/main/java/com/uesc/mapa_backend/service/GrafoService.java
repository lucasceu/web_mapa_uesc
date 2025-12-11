package com.uesc.mapa_backend.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.uesc.mapa_backend.model.Local;
import com.uesc.mapa_backend.repository.LocalRepository;

@Service
public class GrafoService {

    private final LocalRepository localRepository;
    
    // Armazena o grafo: Origem ID -> Lista de (Destino ID, Distância)
    private final Map<Long, List<Aresta>> adjacencias = new HashMap<>();

    public GrafoService(LocalRepository localRepository) {
        this.localRepository = localRepository;
    }

    // Método para adicionar conexões (caminhos) entre dois locais
    public void adicionarConexao(Long origemId, Long destinoId, double distancia) {
        // Adiciona ida
        adjacencias.computeIfAbsent(origemId, k -> new ArrayList<>()).add(new Aresta(destinoId, distancia));
        // Adiciona volta (grafo não direcionado - caminhos de mão dupla)
        adjacencias.computeIfAbsent(destinoId, k -> new ArrayList<>()).add(new Aresta(origemId, distancia));
    }

    // O Algoritmo de Dijkstra
    public List<Local> calcularMenorRota(Long inicioId, Long fimId) {
        Map<Long, Double> distancias = new HashMap<>();
        Map<Long, Long> anteriores = new HashMap<>();
        PriorityQueue<No> filaPrioridade = new PriorityQueue<>(Comparator.comparingDouble(n -> n.distancia));
        Set<Long> visitados = new HashSet<>();

        // Inicialização
        for (Long id : adjacencias.keySet()) {
            distancias.put(id, Double.MAX_VALUE);
        }
        distancias.put(inicioId, 0.0);
        filaPrioridade.add(new No(inicioId, 0.0));

        while (!filaPrioridade.isEmpty()) {
            No atual = filaPrioridade.poll();
            Long atualId = atual.id;

            if (visitados.contains(atualId)) continue;
            visitados.add(atualId);

            if (atualId.equals(fimId)) break; // Chegamos ao destino

            if (adjacencias.containsKey(atualId)) {
                for (Aresta vizinho : adjacencias.get(atualId)) {
                    if (!visitados.contains(vizinho.destinoId)) {
                        double novaDistancia = distancias.get(atualId) + vizinho.peso;
                        if (novaDistancia < distancias.get(vizinho.destinoId)) {
                            distancias.put(vizinho.destinoId, novaDistancia);
                            anteriores.put(vizinho.destinoId, atualId);
                            filaPrioridade.add(new No(vizinho.destinoId, novaDistancia));
                        }
                    }
                }
            }
        }

        return reconstruirCaminho(anteriores, inicioId, fimId);
    }

    private List<Local> reconstruirCaminho(Map<Long, Long> anteriores, Long inicioId, Long fimId) {
        LinkedList<Local> rota = new LinkedList<>();
        Long atual = fimId;

        // Se o destino não foi alcançado ou não existe no mapa de anteriores
        if (!anteriores.containsKey(atual) && !atual.equals(inicioId)) {
            return new ArrayList<>(); // Retorna lista vazia se não houver rota
        }

        while (atual != null) {
            Long finalAtual = atual;
            localRepository.findById(atual).ifPresent(rota::addFirst);
            atual = anteriores.get(atual);
        }
        return rota;
    }

    // Classes auxiliares internas
    private record Aresta(Long destinoId, double peso) {}
    private record No(Long id, double distancia) {}
}