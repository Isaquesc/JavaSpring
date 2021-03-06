package br.com.generation.farmacia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import br.com.generation.farmacia.model.Categoria;
import br.com.generation.farmacia.repository.CategoriaRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaRepository bancocategoria;

    @GetMapping
    public ResponseEntity<List<Categoria>> getAll() {
        return ResponseEntity.ok(bancocategoria.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getById(@PathVariable long id){
        return bancocategoria.findById(id)
            .map(resp -> ResponseEntity.ok(resp))
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/departamento/{departamento}")
    public ResponseEntity<List<Categoria>> getPorDepartamento(@PathVariable String departamento) {
        return ResponseEntity.ok(bancocategoria.findAllByDepartamentoContainingIgnoreCase(departamento));
    }

    @PostMapping
    public ResponseEntity<Categoria> post(@RequestBody Categoria categoria){
        return ResponseEntity.status(HttpStatus.CREATED).body(bancocategoria.save(categoria));
    }

    @PutMapping
    public ResponseEntity<Categoria> editar(@RequestBody Categoria categoria) {
        return ResponseEntity.status(HttpStatus.OK).body(bancocategoria.save(categoria));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        bancocategoria.deleteById(id);
    }



}
