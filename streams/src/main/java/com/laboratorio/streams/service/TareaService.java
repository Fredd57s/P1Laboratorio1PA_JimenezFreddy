package com.laboratorio.streams.service;

import com.laboratorio.streams.dto.TareaDTO;
import com.laboratorio.streams.model.entity.Tarea;
import com.laboratorio.streams.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class TareaService {
    @Autowired
    private TareaRepository repository;

    public List<Tarea> listarTodas(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    public Tarea crear(TareaDTO dto) {
        Tarea tarea = new Tarea();
        tarea.setTitulo(dto.getTitulo());
        tarea.setDescripcion(dto.getDescripcion());
        tarea.setEstado(dto.getEstado());
        tarea.setPrioridad(dto.getPrioridad());
        return repository.save(tarea);
    }
}