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
import java.util.Map;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    @Autowired
    private TareaService service;

    @PostMapping
    public ResponseEntity<Tarea> crear(@Valid @RequestBody TareaDTO dto) {
        return new ResponseEntity<>(service.crear(dto), HttpStatus.CREATED); // 201
    }

    @GetMapping
    public ResponseEntity<List<Tarea>> listar(
            @RequestParam(required = false) EstadoTarea estado,
            Pageable pageable) {
        return ResponseEntity.ok(service.listar(estado, pageable)); // 200
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarea> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id)); // 200
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarea> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody TareaDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto)); // 200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build(); // 204
    }

    @GetMapping("/resumen")
    public ResponseEntity<Map<String, Long>> obtenerResumen() {
        return ResponseEntity.ok(service.obtenerResumen()); // 200
    }
}