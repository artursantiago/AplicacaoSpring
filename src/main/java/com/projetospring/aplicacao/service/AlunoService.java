package com.projetospring.aplicacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetospring.aplicacao.entities.Aluno;
import com.projetospring.aplicacao.exceptions.NegocioException;
import com.projetospring.aplicacao.repository.AlunoRepository;

@Service
public class AlunoService {
	
	@Autowired
	private AlunoRepository repository;
	
	/**
	 * Metodo que busca todos os dados da tabela
	 */
	public List<Aluno> findAll(){ 
		return repository.findAll();
	}
	
	/**
	 * Método que retorna o Aluno com o Id especificado
	 */
	public Aluno findById(long id) throws NegocioException { 
		Optional<Aluno> alunoOptional = repository.findById(id);
		
		if(! alunoOptional.isPresent()) {
			 throw new NegocioException("Aluno com id = " + id + " não econtrado no banco de dados.");
		}
		return alunoOptional.get();
	}
	
	/**
	 * Retorna o numero de alunos no banco com a
	 * matricula especificada
	 */
	public int countByMatriculaAndId(String matricula, long id) {
		return repository.countAlunoByMatriculaAndId(matricula, id);
	}
	
	/**
	 * 
	 * @param matricula
	 * @return
	 */
	public int countByMatricula(String matricula) {
		return repository.countAlunoByMatricula(matricula);
	}
	
	/**
	 * Método que persiste o aluno especificado e o retorna-o.
	 */
	public Aluno save(Aluno aluno) throws NegocioException{
		aluno.validar();
		validaAlunoNoBanco(aluno);
		return repository.save(aluno);
	}
	
	/**
	 * Método que deleta um aluno passando-o como parâmetro.
	 */
	public void delete(Aluno aluno) {
		aluno.validar();
		repository.delete(aluno);
	}
	
	/**
	 * Método que deleta um aluno pelo seu id.
	 */
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
	/**
	 * Método privado que verifica se já existe um aluno
	 * com a mesma matrícula no banco de dados.
	 */
	private void validaAlunoNoBanco(Aluno aluno) throws NegocioException {
		//valida se um aluno tem uma matricula de outro aluno já presente no banco de dados
		if(aluno.getId() == 0) {
			if(countByMatricula(aluno.getMatricula()) == 1) { 
				throw new  NegocioException("Já existe um aluno com essa matrícula.");
			}
		}
		else { 
			if(countByMatriculaAndId(aluno.getMatricula(), aluno.getId()) == 1) { 
				throw new  NegocioException("Já existe um aluno com essa matrícula.");
			}
		}
	}
	
	/////////////Getters and Setters///////////////////
	public AlunoRepository getRepository() {
		return repository;
	}

	public void setRepository(AlunoRepository repository) {
		this.repository = repository;
	}
	 
	
}
