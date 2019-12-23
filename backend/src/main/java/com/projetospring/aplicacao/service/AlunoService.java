package com.projetospring.aplicacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projetospring.aplicacao.entities.Aluno;
import com.projetospring.aplicacao.exceptions.NegocioException;
import com.projetospring.aplicacao.repository.AlunoRepository;

/**
 * Classe responsável pelas regras de negócio da aplicação.
 */
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
	 * matricula  e id especificados
	 */
	public int countByMatricula(String matricula, long id) {
		return repository.countAlunoByMatricula(matricula, id);
	}
	
	/**
	 * Retorna o número de alunos no banco com apenas
	 * a matrícula específicada
	 */
	public int countByMatricula(String matricula) {
		return repository.countAlunoByMatricula(matricula);
	}
	
	/**
	 * Método que persiste o aluno especificado e o retorna-o.
	 */
	@Transactional
	public Aluno save(Aluno aluno) throws NegocioException{
		aluno.validar();
		
		if(aluno.getId() > 0) { // editando
			// ao editar um aluno, concatenar ao seu nome a palavra "(Editado)"
			aluno.getPessoa().setName( aluno.getPessoa().getName() + "(Editado)" );
		}
		
		validaAlunoNoBanco(aluno);
		repository.save(aluno);
		return aluno;
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
		if(aluno.getId() == 0) {
			if(countByMatricula(aluno.getMatricula()) == 1) { 
				throw new  NegocioException("Já existe um aluno com essa matrícula.");
			}
		}
		else { 
			if(countByMatricula(aluno.getMatricula(), aluno.getId()) == 1) { 
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
