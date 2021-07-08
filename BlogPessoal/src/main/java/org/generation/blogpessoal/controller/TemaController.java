package org.generation.blogpessoal.controller;

import java.util.List;

import org.generation.blogpessoal.model.Tema;
import org.generation.blogpessoal.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/tema")
public class TemaController {
	
	@Autowired
	private TemaRepository classetema;

	@GetMapping
	public ResponseEntity<List<Tema>> PegarTudo(){
		return ResponseEntity.ok(classetema.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Tema> PegarPorId(@PathVariable long id){
		return classetema.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Tema>> PegarPorNome(@PathVariable String nome){
		return ResponseEntity.ok(classetema.findAllByDescricaoContainingIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity<Tema> postar(@RequestBody Tema tema){
		return ResponseEntity.status(HttpStatus.CREATED).body(classetema.save(tema));
	}
	
	@PutMapping
	public ResponseEntity<Tema> editar(@RequestBody Tema tema){
		return ResponseEntity.status(HttpStatus.OK).body(classetema.save(tema));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		classetema.deleteById(id);
	}
	
}
