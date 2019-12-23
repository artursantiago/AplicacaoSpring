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

/**
 *A anotação CrossOrigin permite requisições vindas de origens diferentes
 */
@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class AlunoController {
	
	@Autowired
	private AlunoService service;
	
	/**
	 * Método que retorna a lista de todos os alunos do banco de dados
	 */
	@GetMapping("/listagem")
	public ResponseEntity<List<Aluno>> findAll() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}
	
	/**
	 * Método que salva um novo aluno no banco de dados
	 * e retorna um "entidade de resposta" para o vue
	 */
	@PostMapping("/formulario")
	public ResponseEntity<String> save(@RequestBody Aluno aluno) {
		try {
			service.save(aluno);
			return new ResponseEntity<>("O aluno foi salvo com sucesso.", HttpStatus.OK);
		} catch (NegocioException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}catch(IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Método que atualiza os dados do aluno no banco de dados
	 * e retorna um "entidade de resposta" para o vue.
	 */
	@PutMapping("/formulario")
	public ResponseEntity<String> update(@RequestBody Aluno aluno) {
		try {
			service.save(aluno);
			return new ResponseEntity<>("O aluno foi salvo com sucesso.", HttpStatus.OK);
		} catch (NegocioException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}catch(IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Método que deleta um aluno com o id especificado.
	 */
	@DeleteMapping("/listagem/{id}")
	public void delete(@PathVariable("id") long id) { 
		service.deleteById(id);
	}
	
	/**
	 * Método que retorna um aluno com a id especificado.
	 */
	@GetMapping("/listagem/{id}")
	public ResponseEntity<Aluno> findAlunoById(@PathVariable long id) {
		try {
			return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
		} catch (NegocioException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
