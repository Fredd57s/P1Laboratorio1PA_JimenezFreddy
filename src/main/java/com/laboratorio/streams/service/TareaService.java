package com.laboratorio.streams.service;

import com.laboratorio.streams.dto.TareaDTO;
import com.laboratorio.streams.exception.ResourceNotFoundException;
import com.laboratorio.streams.model.EstadoTarea;
import com.laboratorio.streams.model.entity.Tarea;
import com.laboratorio.streams.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TareaService {
    @Autowired
    private TareaRepository repository;

    public Tarea crear(TareaDTO dto) {
        Tarea tarea = new Tarea();
        tarea.setTitulo(dto.getTitulo());
        tarea.setDescripcion(dto.getDescripcion());
        tarea.setEstado(dto.getEstado());
        tarea.setPrioridad(dto.getPrioridad());
        return repository.save(tarea);
    }

    public List<Tarea> listar(EstadoTarea estado, Pageable pageable) {
        if (estado != null) {
            return repository.findByEstado(estado, pageable);
        }
        return repository.findAll(pageable).getContent();
    }

    public Tarea obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarea no encontrada con ID: " + id));
    }

    public Tarea actualizar(Long id, TareaDTO dto) {
        Tarea tarea = obtenerPorId(id); // Reutilizamos el método que lanza la excepción
        tarea.setTitulo(dto.getTitulo());
        tarea.setDescripcion(dto.getDescripcion());
        tarea.setEstado(dto.getEstado());
        tarea.setPrioridad(dto.getPrioridad());
        return repository.save(tarea);
    }

    public void eliminar(Long id) {
        Tarea tarea = obtenerPorId(id);
        repository.delete(tarea);
    }

    public Map<String, Long> obtenerResumen() {
        List<Object[]> resultados = repository.countTasksByStatus();
        Map<String, Long> resumen = new HashMap<>();
        for (Object[] fila : resultados) {
            EstadoTarea estado = (EstadoTarea) fila[0];
            Long cantidad = (Long) fila[1];
            resumen.put(estado.name(), cantidad);
        }
        return resumen;
    }
}