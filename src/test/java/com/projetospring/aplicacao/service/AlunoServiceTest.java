package com.projetospring.aplicacao.service;

import static org.mockito.Mockito.when;  //é necessário que seja static

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.projetospring.aplicacao.entities.Aluno;
import com.projetospring.aplicacao.exceptions.NegocioException;
import com.projetospring.aplicacao.repository.AlunoRepository;

@RunWith(MockitoJUnitRunner.class)
public class AlunoServiceTest {
	
	private Aluno aluno;
	
	@Mock
	private AlunoRepository repository;
	
	@InjectMocks
	private AlunoService service;
	
	@Before
	public void criaAluno() { 
		this.aluno = new Aluno();
	}
	
	@Rule
	public ExpectedException exception;
	
	
	/**
	 * Método save do service executado corretamente para salvar
	 * um novo aluno.
	 * @throws NegocioException 
	 */
	@Ignore
	@Test
	public void testSaveNovoAluno(){ 
		aluno.setMatricula("2019101201");
		aluno.setAnoDeEntrada("2019");
		aluno.getPessoa().setName("Artur");
		
		when(repository.countAlunoByMatricula(aluno.getMatricula())).thenReturn(0);
		
//		service.save(aluno);
	}
	
	/**
	 * Método save do service executado corretamente para editar
	 * um aluno.
	 * @throws NegocioException 
	 */
	@Ignore
	@Test
	public void testSaveEditaAluno(){ 
		aluno.setMatricula("2019101201");
		aluno.setAnoDeEntrada("2019");
		aluno.setId(1);
		aluno.getPessoa().setName("Artur");
		aluno.getPessoa().setId(1);
		
//		service.save(aluno);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
