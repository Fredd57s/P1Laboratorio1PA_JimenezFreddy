package com.laboratorio.streams.controller;

import com.laboratorio.streams.dto.TareaDTO;
import com.laboratorio.streams.model.EstadoTarea;
import com.laboratorio.streams.model.entity.Tarea;
import com.laboratorio.streams.service.TareaService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    @Autowired
    private TareaService service;

    @PostMapping
    public ResponseEntity<Tarea> crear(@Valid @RequestBody TareaDTO dto) {
        return new ResponseEntity<>(service.crear(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Tarea>> listar(
            @RequestParam(required = false) EstadoTarea estado,
            Pageable pageable) {
        return ResponseEntity.ok(service.listarTodas(pageable));
    }
}