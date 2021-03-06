package com.example.hello.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.hello.model.StockDTO;

@RestController
@RequestMapping(value = "/stock")
public class StockController {
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StockDTO> salve(@RequestBody StockDTO dto){
		return ResponseEntity.ok(dto);
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StockDTO> update(@RequestBody StockDTO dto){
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StockDTO>> pegatudo() {
		List<StockDTO> list = new ArrayList<>();
		StockDTO dto = new StockDTO();
		dto.setId(1L);
		dto.setName("Casas Bahia");
		dto.setPrice(10.000);
		dto.setVariacao(100.00);
		dto.setDate(LocalDate.now());
		list.add(dto);
		return ResponseEntity.ok(list);
	}
	/*
	@GetMapping(value = "/{id}")
	public ResponseEntity<StockDTO> findById(@PathVariable Long id){
		return ResponseEntity.ok(null);
	}*/

}
