package br.com.contabilidade.s70.bo.exceptions;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ValidateExceptionTest {

	private ValidateException validate;

	@Before
	public void antes() {
		this.validate = new ValidateException();
	}

	@Test
	public void testAddError() {
		this.validate.addError("teste", "testeErro");

		Assert.assertTrue(this.validate.hasErro());
		final Collection<String> errosCollection = this.validate.getErrors("teste");
		final List<String> erros = new LinkedList<>(errosCollection);

		Assert.assertNotNull(erros);
		Assert.assertFalse(erros.isEmpty());
		Assert.assertEquals(1, erros.size());
		Assert.assertEquals("testeErro", erros.get(0));

	}

	@Test
	public void testAddMaisErro() {
		this.validate.addError("Teste", "erro1");
		this.validate.addError("Teste", "erro2");

		final Collection<String> errosInvalidos = this.validate.getErrors("teste");

		Assert.assertTrue(errosInvalidos.isEmpty());

		final Collection<String> errosCollection = this.validate.getErrors("Teste");
		final List<String> erros = new LinkedList<>(errosCollection);

		Assert.assertNotNull(erros);
		Assert.assertFalse(erros.isEmpty());
		Assert.assertEquals(2, erros.size());
		Assert.assertEquals("erro1", erros.get(0));
		Assert.assertEquals("erro2", erros.get(1));

	}

	@Test
	public void testTodosErros() {
		this.validate.addError("Teste", "erro1");
		this.validate.addError("Teste1", "erro2");

		final Collection<String> erroCollection = this.validate.getAllErrors();
		final Set<String> erros = new HashSet<>(erroCollection);


		Assert.assertNotNull(erros);
		Assert.assertFalse(erros.isEmpty());
		Assert.assertEquals(2, erros.size());
		Assert.assertTrue(erros.contains("erro1"));
		Assert.assertTrue(erros.contains("erro2"));

	}

	@Test
	public void testFieldsErro() {
		this.validate.addError("Teste", "cuidado1");
		this.validate.addError("Teste1", "cuidado2");

		final Collection<String> erroCollection = this.validate.getAllFieldsErro();
		final Set<String> erros = new HashSet<>(erroCollection);

		Assert.assertNotNull(erros);
		Assert.assertFalse(erros.isEmpty());
		Assert.assertEquals(2, erros.size());
		Assert.assertTrue(erros.contains("Teste"));
		Assert.assertTrue(erros.contains("Teste1"));

	}

	@Test
	public void testAddCuidado() {
		this.validate.addWarning("teste", "testCuidado");

		Assert.assertTrue(this.validate.hasWarning());

		final Collection<String> cuidadosCollection = this.validate.getWarnings("teste");
		final List<String> cuidados = new LinkedList<>(cuidadosCollection);

		Assert.assertNotNull(cuidados);
		Assert.assertFalse(cuidados.isEmpty());
		Assert.assertEquals(1, cuidados.size());
		Assert.assertEquals("testCuidado", cuidados.get(0));

	}

	@Test
	public void testAddMaisCuidados() {
		this.validate.addWarning("Teste", "cuidado1");
		this.validate.addWarning("Teste", "cuidado2");

		final Collection<String> errosInvalidos = this.validate.getWarnings("teste");

		Assert.assertTrue(errosInvalidos.isEmpty());

		final Collection<String> cuidadosCollection = this.validate.getWarnings("Teste");
		final Set<String> cuidados = new HashSet<>(cuidadosCollection);

		Assert.assertNotNull(cuidados);
		Assert.assertFalse(cuidados.isEmpty());
		Assert.assertEquals(2, cuidados.size());
		Assert.assertTrue(cuidados.contains("cuidado1"));
		Assert.assertTrue(cuidados.contains("cuidado2"));

	}

	@Test
	public void testTodosCuidados() {
		this.validate.addWarning("Teste", "cuidado1");
		this.validate.addWarning("Teste1", "cuidado2");

		final Collection<String> cuidadosCollection = this.validate.getAllWarnings();
		final Set<String> cuidados = new HashSet<>(cuidadosCollection);

		Assert.assertNotNull(cuidados);
		Assert.assertFalse(cuidados.isEmpty());
		Assert.assertEquals(2, cuidados.size());
		Assert.assertTrue(cuidados.contains("cuidado1"));
		Assert.assertTrue(cuidados.contains("cuidado2"));

	}

	@Test
	public void testFieldsCuidados() {
		this.validate.addWarning("Teste", "cuidado1");
		this.validate.addWarning("Teste1", "cuidado2");

		final Collection<String> cuidadosCollection = this.validate.getAllFieldsWarning();
		final Set<String> cuidados = new HashSet<>(cuidadosCollection);

		Assert.assertNotNull(cuidados);
		Assert.assertFalse(cuidados.isEmpty());
		Assert.assertEquals(2, cuidados.size());
		Assert.assertTrue(cuidados.contains("Teste"));
		Assert.assertTrue(cuidados.contains("Teste1"));

	}

}
