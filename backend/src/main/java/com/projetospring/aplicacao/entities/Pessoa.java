package com.projetospring.aplicacao.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * 
 * @author artur
 *
 *Classe responsável por mapear a tabela pessoa do banco de dados.
 */
@Entity
@Table(name = "pessoa", schema = "comum")
public class Pessoa{
	
	/**
	 * O Id foi definida para ser gerado automaticamente a partir de uma sequencia pessoa_seq.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoa_seq")
	@SequenceGenerator(name = "pessoa_seq", sequenceName = "pessoa_seq", schema = "comum", allocationSize = 1)
	private long id;
	
	/**
	 * Coluna name do banco. Deve ter no máximo 100 caracteres.
	 */
	private String name;
	
	/**
	 * Relação Um-para-Muitos com a tabela aluno, ou seja, uma pessoa pode ter vários vínculos como aluno.
	 * Esta lista armazena todos os "vínculos" de aluno que essa pessoa tem.
	 */
	@JsonBackReference("pessoa") // Impedde que retorne um json com recursão infinita
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
	private List<Aluno> alunos;
	

	/**
	 * Valida as regras que se aplicão aos atributos dessa pessoa.
	 */
	public void validar() {
		
		/**
		 * Verifica se o campo o nome está vazio ou se está null.
		 */
		if(this.getName() == "" || this.getName() == null) {
			throw new IllegalArgumentException("Informe o nome do aluno.");
		}
		
		/**
		 * Verifica se o nome do aluno tem mais que 100 caracteres.
		 */
		if(this.getName().length() > 100) {
			throw new IllegalArgumentException("O nome do Aluno deve ter no máximo 100 caracteres.");
		}
	}
	
	////////////////Getters and Setters///////////////////////
	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Aluno> getAlunos() {
		return alunos;
	}
	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	
	
}
