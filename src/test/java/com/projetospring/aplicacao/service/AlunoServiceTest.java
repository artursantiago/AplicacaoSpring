package com.projetospring.aplicacao.service;

import static org.mockito.Mockito.when;  //é necessário que seja static

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.projetospring.aplicacao.entities.Aluno;
import com.projetospring.aplicacao.exceptions.NegocioException;
import com.projetospring.aplicacao.repository.AlunoRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
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
	public ExpectedException exception = ExpectedException.none();
	
	
	/**
	 * Método save do service executado corretamente para salvar
	 * um novo aluno.
	 * @throws NegocioException 
	 */
	@Test
	public void testSaveNovoAluno() throws NegocioException{ 
		aluno.setMatricula("2019101201");
		aluno.setAnoDeEntrada("2019");
		aluno.getPessoa().setName("Artur");
		
		when(repository.countAlunoByMatricula(aluno.getMatricula())).thenReturn(0);
		
		service.save(aluno);
	}
	
	/**
	 * Método save do service executado corretamente para editar
	 * um aluno.
	 * @throws NegocioException 
	 */
	@Test
	public void testSaveEditaAluno() throws NegocioException{ 
		aluno.setMatricula("2019101201");
		aluno.setAnoDeEntrada("2019");
		aluno.setId(1);
		aluno.getPessoa().setName("Artur");
		aluno.getPessoa().setId(1);
		
		when(repository.countAlunoByMatriculaAndId(aluno.getMatricula(), aluno.getId())).thenReturn(0);
		
		service.save(aluno);
	}
	
	////////Testes Matrículas/////////

	
	/**
	 * Testa se uma matrícula vazia está sendo submetida.
	 * @throws NegocioException 
	 */
	@Test
	public void testMatriculaVazia() throws NegocioException {
		aluno.setMatricula("");
		aluno.setAnoDeEntrada("2019");
		aluno.getPessoa().setName("Artur");
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Informe a matricula do aluno.");
		
		service.save(aluno);
	}
	
	/**
	 * Testa se a matricula já existe no banco de dados quando um aluno está sendo editado.
	 * @throws NegocioException 
	 */
	@Test
	public void testAtualizarAlunoMatriculaJaExisteNoBanco() throws NegocioException {
		aluno.setId(2);
		aluno.setMatricula("20191012");
		aluno.setAnoDeEntrada("2021");
		aluno.getPessoa().setId(2);
		aluno.getPessoa().setName("Lucas");
		
		when(repository.countAlunoByMatriculaAndId(aluno.getMatricula(), aluno.getId())).thenReturn(1);
		
		exception.expect(NegocioException.class);
		exception.expectMessage("Já existe um aluno com essa matrícula.");
		
		service.save(aluno);
	}
	
	/**
	 * Testa se a matricula já existe no banco de dados quando um novo aluno está sendo persistido.
	 */
	@Test
	public void testSalvarNovoAlunoMatriculaJaExisteNoBanco() throws NegocioException{
		aluno.setMatricula("20191012");
		aluno.setAnoDeEntrada("2021");
		aluno.getPessoa().setName("Lucas");
		
		when(repository.countAlunoByMatricula(aluno.getMatricula())).thenReturn(1);
		
		exception.expect(NegocioException.class);
		exception.expectMessage("Já existe um aluno com essa matrícula.");
		
		service.save(aluno);
	}
	
	/**
	 * Testa se a matricula possui mais do que 15 caracteres.
	 * @throws NegocioException 
	 */
	@Test
	public void testMatriculaMaiorQueQuinzeCaracteres() throws NegocioException {
		aluno.setMatricula("20200102030405012");
		aluno.setAnoDeEntrada("2020");
		aluno.getPessoa().setName("Mateus");
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("A matrícula do aluno deve ter no máximo 15 caracteres.");
		
		service.save(aluno);
	}
	
///////Testes AnoDeEntrada///////
	
	/**
	 * Testa se o anoDeEntrada está vázio.
	 * @throws NegocioException 
	 */
	@Test
	public void testAnoDeEntradaVazio() throws NegocioException {
		aluno.setMatricula("2021010138");
		aluno.setAnoDeEntrada("");
		aluno.getPessoa().setName("Mateus");
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Informe o ano de entrada do aluno.");
		
		service.save(aluno);
	}
	
	/**
	 * Testa se o anoDeEntrada não possui 4 caracteres.
	 * @throws NegocioException 
	 */
	@Test
	public void testAnoDeEntradaDiferenteDeQuatroCaracteres() throws NegocioException {
		aluno.setMatricula("2021010138");
		aluno.setAnoDeEntrada("999");
		aluno.getPessoa().setName("Lucas");
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("O ano de entrada deve ter 4 caracteres.");
		
		service.save(aluno);
	}
	
	
	/**
	 * Testa se o anoDeEntrada é um ano com 4 caracteres menor que o ano atual.
	 * @throws NegocioException 
	 */
	@Test
	public void testAnoDeEntradaComQuatroCaracteresMenorQueAnoAtual() throws NegocioException {
		aluno.setMatricula("2021010138");
		aluno.setAnoDeEntrada("2010");
		aluno.getPessoa().setName("Jeremias");
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Informe um ano maior ou igual ao ano atual.");	
		
		service.save(aluno);		
	}
	
	////////////Tests Nome/////////////
	
	/**
	 * Testa se o nome está vazio.
	 * @throws NegocioException 
	 */
	@Test
	public void testNomeVazio() throws NegocioException {
		aluno.setMatricula("2021010138");
		aluno.setAnoDeEntrada("2021");
		aluno.getPessoa().setName("");
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Informe o nome do aluno.");
		
		service.save(aluno);
	}
	
	/**
	 * Testa se o nome possui mais do que 100 caracteres.
	 * @throws NegocioException 
	 */
	@Test
	public void testNomeMaiorQueCem() throws NegocioException {
		aluno.setMatricula("2021010138");
		aluno.setAnoDeEntrada("2020");
		aluno.getPessoa().setName("Matheus Santiago José da Silva Cavalcanti de Melo Paiva de Souza Santanna de Paula Ferreira Quintiliano Junior");
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("O nome do Aluno deve ter no máximo 100 caracteres.");
		
		service.save(aluno);
	}
	
}
