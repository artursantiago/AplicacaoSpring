package com.projetospring.aplicacao.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.projetospring.aplicacao.entities.Aluno;
import com.projetospring.aplicacao.exceptions.NegocioException;
import com.projetospring.aplicacao.service.AlunoService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class AlunoController {
	
	@Autowired
	private AlunoService service;
	
	/**
	 * Método que retorna a lista de todos os alunos do banco de dados
	 */
	@GetMapping("/listagem")
	public List<Aluno> findAll() {
		return service.findAll();
	}
	
	
	/**
	 * Método que salva um novo aluno no banco de dados
	 */
	@PostMapping("/formulario")
	public ResponseEntity<Aluno> save(@RequestBody Aluno aluno) {
		try {
			Aluno alunoSalvo = service.save(aluno);
			return new ResponseEntity<Aluno>(alunoSalvo, HttpStatus.CREATED);
		} catch (NegocioException e) {
			//mostra mensgem de erro
			return ResponseEntity.badRequest().build();
		}catch(IllegalArgumentException e) {
			
			return ResponseEntity.badRequest().build();
		}
	}
	
	/**
	 * Método que atualiza os dados do aluno no banco de dados.
	 */
	@PutMapping("/formulario")
	public ResponseEntity<Aluno> update(@RequestBody Aluno aluno) {
		try {
			Aluno alunoSalvo = service.save(aluno);
			return new ResponseEntity<Aluno>(alunoSalvo, HttpStatus.CREATED);
		} catch (NegocioException e) {
			//mostra mensgem de erro
			return ResponseEntity.badRequest().build();
		}catch(IllegalArgumentException e) {
			
			return ResponseEntity.badRequest().build();
		}
	}
	
	/**
	 * Método que deleta um aluno com o id especificado.
	 */
	@DeleteMapping("/listagem/{id}")
	public void delete(@PathVariable("id") long id) { 
		service.deleteById(id);
	}
	 
	@GetMapping("/listagem/{id}")
	public ResponseEntity<Aluno> findAlunoById(@PathVariable long id) {
		try {
			return ResponseEntity.ok(service.findById(id));
		} catch (NegocioException e) {
			System.out.println("bbbbbbbbbbbbbbbbbbbbbb");
//			log.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}
}
