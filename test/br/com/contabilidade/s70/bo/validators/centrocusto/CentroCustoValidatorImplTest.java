package br.com.contabilidade.s70.bo.validators.centrocusto;

import java.math.BigDecimal;
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
import br.com.contabilidade.s70.fabrica.FabricaObjetos;
import br.com.contabilidade.s70.persistence.beans.CentroCusto;
import br.com.contabilidade.s70.persistence.beans.CentroCusto.Tipo;

public class CentroCustoValidatorImplTest {

	private static final String ID_INVALIDO = "Código do Centro de Custo inválido. Deve estar entre: 1 e 99999";
	private static final String NOME_INVALIDO = "O nome do centro de custo não pode ser vazio.";
	private static final String TAXA_INVALIDA_OBRA_ANDAMENTO = "A taxa de administração para uma obra em andamento não pode ser zero.";
	private static final String TAXA_INVALIDA_OBRA_ENCERRADA = "A taxa de administração para uma obra em encerrada tem que ser zero.";

	private static final String FIELD_ID = "codigo";
	private static final String FIELD_NOME = "centroCustoNome";
	private static final String FIELD_TAXA = "percentId";

	private Mockery ctx;
	private CentroCusto cCusto;
	private CentroCustoValidatorImpl validator;
	private FabricaObjetos fabrica;

	@Before
	public void setUp() throws Exception {

		this.ctx = new Mockery();
		this.cCusto = this.ctx.mock(CentroCusto.class);
		this.validator = new CentroCustoValidatorImpl();
		this.fabrica = FabricaObjetos.Factory.create();
	}

	@Test
	public void testValidateCodigoInvalido() {

		this.ctx.checking(new Expectations() {
			{
				this.oneOf(CentroCustoValidatorImplTest.this.cCusto).getCodigo();
				this.will(returnValue(Long.valueOf(-1)));

				this.oneOf(CentroCustoValidatorImplTest.this.cCusto).getNome();
				this.will(returnValue("Teste"));

				this.oneOf(CentroCustoValidatorImplTest.this.cCusto).getNome();
				this.will(returnValue("Teste"));

				this.oneOf(CentroCustoValidatorImplTest.this.cCusto).getTaxa();
				this.will(returnValue(new BigDecimal(3)));

				this.oneOf(CentroCustoValidatorImplTest.this.cCusto).getTipo();
				this.will(returnValue(Tipo.OBRA_ANDAMENTO));
			}
		});

		try {
			this.validator.validate(this.cCusto);
			this.fabrica.erroNaoEsperado();
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

		}
	}

	@Test
	public void testValidateNomeNulo() {

		this.ctx.checking(new Expectations() {
			{
				this.oneOf(CentroCustoValidatorImplTest.this.cCusto).getCodigo();
				this.will(returnValue(Long.valueOf(1)));

				this.oneOf(CentroCustoValidatorImplTest.this.cCusto).getNome();
				this.will(returnValue(null));

				this.oneOf(CentroCustoValidatorImplTest.this.cCusto).getNome();
				this.will(returnValue(null));

				this.oneOf(CentroCustoValidatorImplTest.this.cCusto).getTaxa();
				this.will(returnValue(new BigDecimal(0)));

				this.oneOf(CentroCustoValidatorImplTest.this.cCusto).getTipo();
				this.will(returnValue(Tipo.OBRA_ENCERRADA));
			}
		});

		try {
			this.validator.validate(this.cCusto);
			this.fabrica.erroNaoEsperado();
		} catch (final ValidateException e) {
			Assert.assertTrue(e.hasErro());
			final Collection<String> errosCollection = e.getAllErrors();

			Assert.assertNotNull(errosCollection);
			Assert.assertFalse(errosCollection.isEmpty());
			Assert.assertEquals(1, errosCollection.size());

			final List<String> erros = new ArrayList<>(errosCollection);
			Assert.assertEquals(NOME_INVALIDO, erros.get(0));

			final Set<String> fields = new HashSet<>(e.getAllFieldsErro());
			Assert.assertTrue(fields.contains(FIELD_NOME));

		}
	}

	@Test
	public void testValidateNomeVazio() {

		this.ctx.checking(new Expectations() {
			{
				this.oneOf(CentroCustoValidatorImplTest.this.cCusto).getCodigo();
				this.will(returnValue(Long.valueOf(1)));

				this.oneOf(CentroCustoValidatorImplTest.this.cCusto).getNome();
				this.will(returnValue(""));

				this.oneOf(CentroCustoValidatorImplTest.this.cCusto).getNome();
				this.will(returnValue(""));

				this.oneOf(CentroCustoValidatorImplTest.this.cCusto).getTaxa();
				this.will(returnValue(new BigDecimal(0)));

				this.oneOf(CentroCustoValidatorImplTest.this.cCusto).getTipo();
				this.will(returnValue(Tipo.OBRA_ENCERRADA));
			}
		});

		try {
			this.validator.validate(this.cCusto);
			this.fabrica.erroNaoEsperado();
		} catch (final ValidateException e) {
			Assert.assertTrue(e.hasErro());
			final Collection<String> errosCollection = e.getAllErrors();

			Assert.assertNotNull(errosCollection);
			Assert.assertFalse(errosCollection.isEmpty());
			Assert.assertEquals(1, errosCollection.size());

			final List<String> erros = new ArrayList<>(errosCollection);
			Assert.assertEquals(NOME_INVALIDO, erros.get(0));

			final Set<String> fields = new HashSet<>(e.getAllFieldsErro());
			Assert.assertTrue(fields.contains(FIELD_NOME));

		}
	}

	@Test
	public void testTaxaInvalida() {

		this.ctx.checking(new Expectations() {
			{
				this.oneOf(CentroCustoValidatorImplTest.this.cCusto).getCodigo();
				this.will(returnValue(Long.valueOf(1)));

				this.oneOf(CentroCustoValidatorImplTest.this.cCusto).getNome();
				this.will(returnValue("Teste"));

				this.oneOf(CentroCustoValidatorImplTest.this.cCusto).getNome();
				this.will(returnValue("Teste"));

				this.oneOf(CentroCustoValidatorImplTest.this.cCusto).getTaxa();
				this.will(returnValue(new BigDecimal(0)));

				this.oneOf(CentroCustoValidatorImplTest.this.cCusto).getTipo();
				this.will(returnValue(Tipo.OBRA_ANDAMENTO));

			}
		});

		try {
			this.validator.validate(this.cCusto);
			this.fabrica.erroNaoEsperado();
		} catch (final ValidateException e) {
			Assert.assertTrue(e.hasErro());
			final Collection<String> errosCollection = e.getAllErrors();

			Assert.assertNotNull(errosCollection);
			Assert.assertFalse(errosCollection.isEmpty());
			Assert.assertEquals(1, errosCollection.size());

			final List<String> erros = new ArrayList<>(errosCollection);
			Assert.assertEquals(TAXA_INVALIDA_OBRA_ANDAMENTO, erros.get(0));

			final Set<String> fields = new HashSet<>(e.getAllFieldsErro());
			Assert.assertTrue(fields.contains(FIELD_TAXA));

		}
	}

	@Test
	public void testObraEncerradaTaxaMaiorQueZero() {

		this.ctx.checking(new Expectations() {
			{
				this.oneOf(CentroCustoValidatorImplTest.this.cCusto).getCodigo();
				this.will(returnValue(Long.valueOf(1)));

				this.oneOf(CentroCustoValidatorImplTest.this.cCusto).getNome();
				this.will(returnValue("Teste"));

				this.oneOf(CentroCustoValidatorImplTest.this.cCusto).getNome();
				this.will(returnValue("Teste"));

				this.oneOf(CentroCustoValidatorImplTest.this.cCusto).getTaxa();
				this.will(returnValue(new BigDecimal(2)));

				this.oneOf(CentroCustoValidatorImplTest.this.cCusto).getTipo();
				this.will(returnValue(Tipo.OBRA_ENCERRADA));

			}
		});

		try {
			this.validator.validate(this.cCusto);
			this.fabrica.erroNaoEsperado();
		} catch (final ValidateException e) {
			Assert.assertTrue(e.hasErro());
			final Collection<String> errosCollection = e.getAllErrors();

			Assert.assertNotNull(errosCollection);
			Assert.assertFalse(errosCollection.isEmpty());
			Assert.assertEquals(1, errosCollection.size());

			final List<String> erros = new ArrayList<>(errosCollection);
			Assert.assertEquals(TAXA_INVALIDA_OBRA_ENCERRADA, erros.get(0));

			final Set<String> fields = new HashSet<>(e.getAllFieldsErro());
			Assert.assertTrue(fields.contains(FIELD_TAXA));

		}
	}

	@Test
	public void testObraSucesso() {

		this.ctx.checking(new Expectations() {
			{
				this.oneOf(CentroCustoValidatorImplTest.this.cCusto).getCodigo();
				this.will(returnValue(Long.valueOf(1)));

				this.oneOf(CentroCustoValidatorImplTest.this.cCusto).getNome();
				this.will(returnValue("Teste"));

				this.oneOf(CentroCustoValidatorImplTest.this.cCusto).getNome();
				this.will(returnValue("Teste"));

				this.oneOf(CentroCustoValidatorImplTest.this.cCusto).getTaxa();
				this.will(returnValue(new BigDecimal(2)));

				this.oneOf(CentroCustoValidatorImplTest.this.cCusto).getTipo();
				this.will(returnValue(Tipo.OBRA_ANDAMENTO));

			}
		});

		try {
			this.validator.validate(this.cCusto);
			Assert.assertTrue(true);
		} catch (final ValidateException e) {
			this.fabrica.erroNaoEsperado(e);

		}
	}
}
