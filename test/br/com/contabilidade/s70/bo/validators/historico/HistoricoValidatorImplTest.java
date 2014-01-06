package br.com.contabilidade.s70.bo.validators.historico;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.contabilidade.s70.bo.exceptions.ValidateException;
import br.com.contabilidade.s70.persistence.beans.Historico;
import br.com.contabilidade.s70.persistence.beans.Historico.HistoricoComplemento;

public class HistoricoValidatorImplTest {

	private static final String ID_INVALIDO = "Código do histórico inválido. Deve estar entre: 1 e 99999";
	private static final String DESC_INVALIDO = "Descrição do histórico inválido.";
	private static final String DESC_VAZIO = "Descrição do histórico não pode ser vazio.";
	private static final String COMPL_INVALIDO = "Complemento nulo. Contate o administrador do sistema.";

	private static final String FIELD_ID = "id";
	private static final String FIELD_DESCRICAO = "descricao";
	private static final String FIELD_COMPLEMENTO = "complemento";

	private Mockery ctx;
	private HistoricoValidatorImpl validator;
	private Historico historico;

	@Before
	public void before() {
		this.ctx = new Mockery();
		this.historico = this.ctx.mock(Historico.class);
		this.validator = new HistoricoValidatorImpl();

	}

	// ERROS PADRÃO
	private void erroNaoEsperado() {
		Assert.fail("Erro não esperado.");
	}

	private void erroNaoEsperado(final Exception e) {
		e.printStackTrace();
		this.erroNaoEsperado();
	}

	@Test
	public void validatorOk() {

		this.ctx.checking(new Expectations() {
			{
				this.oneOf(HistoricoValidatorImplTest.this.historico).getId();
				this.will(returnValue(Long.valueOf(1)));

				this.oneOf(HistoricoValidatorImplTest.this.historico).getDescricao();
				this.will(returnValue("Teste"));

				this.oneOf(HistoricoValidatorImplTest.this.historico).hasComplemento();
				this.will(returnValue(HistoricoComplemento.SIM));

			}
		});

		try {
			this.validator.validate(this.historico);
		} catch (final ValidateException e) {
			this.erroNaoEsperado(e);
		} catch (final Exception e) {
			this.erroNaoEsperado(e);
		}
	}

	@Test
	public void validatorIdLimiteInferior() {

		this.ctx.checking(new Expectations() {
			{
				this.oneOf(HistoricoValidatorImplTest.this.historico).getId();
				this.will(returnValue(Long.valueOf(0)));

				this.oneOf(HistoricoValidatorImplTest.this.historico).getDescricao();
				this.will(returnValue("Teste"));

				this.oneOf(HistoricoValidatorImplTest.this.historico).hasComplemento();
				this.will(returnValue(HistoricoComplemento.SIM));

			}
		});

		try {
			this.validator.validate(this.historico);
			this.erroNaoEsperado();
		} catch (final ValidateException e) {
			Assert.assertTrue(e.hasErro());
			final Collection<String> errosCollection = e.getAllErrors();

			Assert.assertNotNull(errosCollection);
			Assert.assertFalse(errosCollection.isEmpty());
			Assert.assertEquals(1, errosCollection.size());

			final List<String> erros = new ArrayList<>(errosCollection);
			Assert.assertEquals(ID_INVALIDO, erros.get(0));

			final Set<String> fields = new HashSet<>(e.getAllFieldsErro());
			Assert.assertTrue(fields.contains(FIELD_ID));

		} catch (final Exception e) {
			this.erroNaoEsperado(e);
		}
	}

	@Test
	public void validatorIdLimiteSuperior() {

		this.ctx.checking(new Expectations() {
			{
				this.oneOf(HistoricoValidatorImplTest.this.historico).getId();
				this.will(returnValue(Long.valueOf(100000)));

				this.oneOf(HistoricoValidatorImplTest.this.historico).getDescricao();
				this.will(returnValue("Teste"));

				this.oneOf(HistoricoValidatorImplTest.this.historico).hasComplemento();
				this.will(returnValue(HistoricoComplemento.SIM));

			}
		});

		try {
			this.validator.validate(this.historico);
			this.erroNaoEsperado();
		} catch (final ValidateException e) {
			Assert.assertTrue(e.hasErro());
			final Collection<String> errosCollection = e.getAllErrors();

			Assert.assertNotNull(errosCollection);
			Assert.assertFalse(errosCollection.isEmpty());
			Assert.assertEquals(1, errosCollection.size());

			final List<String> erros = new ArrayList<>(errosCollection);
			Assert.assertEquals(ID_INVALIDO, erros.get(0));

			final Set<String> fields = new HashSet<>(e.getAllFieldsErro());
			Assert.assertTrue(fields.contains(FIELD_ID));

		} catch (final Exception e) {
			this.erroNaoEsperado(e);
		}
	}

	@Test
	public void validatorDescNull() {

		this.ctx.checking(new Expectations() {
			{
				this.oneOf(HistoricoValidatorImplTest.this.historico).getId();
				this.will(returnValue(Long.valueOf(1)));

				this.oneOf(HistoricoValidatorImplTest.this.historico).getDescricao();
				this.will(returnValue(null));

			}
		});

		try {
			this.validator.validate(this.historico);
		} catch (final ValidateException e) {
			Assert.assertTrue(e.hasErro());
			final Collection<String> errosCollection = e.getAllErrors();

			Assert.assertNotNull(errosCollection);
			Assert.assertFalse(errosCollection.isEmpty());
			Assert.assertEquals(1, errosCollection.size());

			final List<String> erros = new ArrayList<>(errosCollection);
			Assert.assertEquals(DESC_INVALIDO, erros.get(0));

			final Set<String> fields = new HashSet<>(e.getAllFieldsErro());
			Assert.assertTrue(fields.contains(FIELD_DESCRICAO));
		} catch (final Exception e) {
			this.erroNaoEsperado(e);
		}
	}

	@Test
	public void validatorDescVazio() {

		this.ctx.checking(new Expectations() {
			{
				this.oneOf(HistoricoValidatorImplTest.this.historico).getId();
				this.will(returnValue(Long.valueOf(1)));

				this.oneOf(HistoricoValidatorImplTest.this.historico).getDescricao();
				this.will(returnValue(""));

				this.oneOf(HistoricoValidatorImplTest.this.historico).hasComplemento();
				this.will(returnValue(HistoricoComplemento.SIM));

			}
		});

		try {
			this.validator.validate(this.historico);
		} catch (final ValidateException e) {
			Assert.assertTrue(e.hasErro());
			final Collection<String> errosCollection = e.getAllErrors();

			Assert.assertNotNull(errosCollection);
			Assert.assertFalse(errosCollection.isEmpty());
			Assert.assertEquals(1, errosCollection.size());

			final List<String> erros = new ArrayList<>(errosCollection);
			Assert.assertEquals(DESC_VAZIO, erros.get(0));

			final Set<String> fields = new HashSet<>(e.getAllFieldsErro());
			Assert.assertTrue(fields.contains(FIELD_DESCRICAO));
		} catch (final Exception e) {
			this.erroNaoEsperado(e);
		}
	}

	@Test
	public void validatorComplementoNulo() {

		this.ctx.checking(new Expectations() {
			{
				this.oneOf(HistoricoValidatorImplTest.this.historico).getId();
				this.will(returnValue(Long.valueOf(1)));

				this.oneOf(HistoricoValidatorImplTest.this.historico).getDescricao();
				this.will(returnValue("teste"));

				this.oneOf(HistoricoValidatorImplTest.this.historico).hasComplemento();
				this.will(returnValue(null));

			}
		});

		try {
			this.validator.validate(this.historico);
		} catch (final ValidateException e) {
			Assert.assertTrue(e.hasErro());
			final Collection<String> errosCollection = e.getAllErrors();

			Assert.assertNotNull(errosCollection);
			Assert.assertFalse(errosCollection.isEmpty());
			Assert.assertEquals(1, errosCollection.size());

			final List<String> erros = new ArrayList<>(errosCollection);
			Assert.assertEquals(COMPL_INVALIDO, erros.get(0));

			final Set<String> fields = new HashSet<>(e.getAllFieldsErro());
			Assert.assertTrue(fields.contains(FIELD_COMPLEMENTO));
		} catch (final Exception e) {
			this.erroNaoEsperado(e);
		}
	}

	@Test
	public void TodosErrosPossiveis() {
		Assert.fail("Não implementado.");
	}
}
