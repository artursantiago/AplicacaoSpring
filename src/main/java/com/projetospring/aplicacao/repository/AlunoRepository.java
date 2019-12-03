package com.projetospring.aplicacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projetospring.aplicacao.entities.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>{
	
	/**
	 * Retorna o número de alunos que existem no banco
	 * com a matricula igual a matricula especificada.
	 */
	@Query("select count(matricula) from com.projetospring.aplicacao.entities.Aluno a where a.matricula = ?1")
	public int finfAlunoByMatricula(String matricula);
	
	@Query("select a.id from com.projetospring.aplicacao.entities.Aluno a where a.matricula = ?1")
	public long findIdByMatricula(String matricula);
}
