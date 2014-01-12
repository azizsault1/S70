package br.com.contabilidade.s70.persistence.dao.historico;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.LockTimeoutException;
import javax.persistence.OptimisticLockException;
import javax.persistence.PessimisticLockException;
import javax.transaction.TransactionRequiredException;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.contabilidade.s70.fabrica.FabricaObjetos;
import br.com.contabilidade.s70.persistence.beans.Historico;
import br.com.contabilidade.s70.persistence.beans.Historico.HistoricoComplemento;
import br.com.contabilidade.s70.persistence.dao.DefaultDao;
import br.com.contabilidade.s70.persistence.exception.ChaveDuplicadaExcpetion;
import br.com.contabilidade.s70.persistence.exception.PersistenceException;
import br.com.contabilidade.s70.persistence.exception.PersistenceException.TypeError;

public class HistoricoDaoImplTest {

	private Mockery ctx;
	private DefaultDao<Long, HistoricoImpl> defaultDao;
	private HistoricoDaoImpl dao;
	private FabricaObjetos fabrica;

	private static final String HISTORICO_REMOVER = "Não foi possível remover o histórico. Contate o administrador do sistema.";
	private static final String HISTORICO_SALVAR = "Não foi possível gravar o histórico. Contate o administrador do sistema.";
	private static final String HISTORICO_ALTERAR = "Não foi possível alterar o histórico. Contate o administrador do sistema.";
	private static final String HISTORICO_CONSULTAR = "Não foi possível consultar o histórico. Contate o administrador do sistema.";
	private static final String HISTOFICO_CONULTA_VAZIA = "Não foi possível encontrar o histórico.";
	private static final String HISTORICO_CONSTULTA_TIMEOUT = "A consulta demorou mais do que o esperado. Contate o administrador do sistema.";
	private static final String HISTORICO_DUPLICADO = "Já existe um histórico com este código.";

	@SuppressWarnings("unchecked")
	@Before
	public void before() {
		this.ctx = new Mockery();
		this.defaultDao = this.ctx.mock(DefaultDao.class);
		this.dao = new HistoricoDaoImpl(this.defaultDao);
		this.fabrica = FabricaObjetos.Factory.create();

	}

	// CRIACAOO DE OBJETOS
	private HistoricoImpl create(final int id, final HistoricoComplemento complemento) {
		final HistoricoImpl historico = new HistoricoImpl(id, Long.valueOf(id).toString(), complemento.toSave());

		return historico;
	}

	private List<HistoricoImpl> createHistoricos(final int qtd, final HistoricoComplemento complemento) {

		final List<HistoricoImpl> historicos = new LinkedList<>();

		for (int i = 1; i <= qtd; i++) {
			historicos.add(new HistoricoImpl(i, Long.valueOf(i).toString(), complemento.toSave()));

		}

		return historicos;
	}

	// SALVAR

	@Test
	public void saveHistorico() {
		try {

			final HistoricoImpl historico = this.create(1, HistoricoComplemento.SIM);

			this.ctx.checking(new Expectations() {
				{
					this.oneOf(HistoricoDaoImplTest.this.defaultDao).save(this.with(historico));
					this.will(returnValue(historico));

				}
			});

			final Historico historicoSalvo = this.dao.save(historico);
			Assert.assertNotNull(historicoSalvo);

		} catch (final Exception e) {
			this.fabrica.erroNaoEsperado(e);
		}
	}

	@Test
	public void saveHistoricoException() {
		try {

			final HistoricoImpl historico = this.create(1, HistoricoComplemento.SIM);

			this.ctx.checking(new Expectations() {
				{
					this.oneOf(HistoricoDaoImplTest.this.defaultDao).save(this.with(historico));
					this.will(throwException(new javax.persistence.PersistenceException()));
				}
			});

			this.dao.save(historico);

			this.fabrica.erroNaoEsperado();

		} catch (final PersistenceException e) {
			Assert.assertEquals(TypeError.SALVAR, e.getType());
			Assert.assertEquals(HISTORICO_SALVAR, e.getMessage());
			Assert.assertNotNull(e.getCause());
		} catch (final Exception e) {
			this.fabrica.erroNaoEsperado(e);
		}
	}

	@Test
	public void saveHistoricoSimulandoErroChaveDuplicada() {
		try {

			final HistoricoImpl historico = this.create(1, HistoricoComplemento.SIM);

			this.ctx.checking(new Expectations() {
				{
					this.oneOf(HistoricoDaoImplTest.this.defaultDao).save(this.with(historico));
					this.will(throwException(new ChaveDuplicadaExcpetion("Simulando um erro de update.")));
				}
			});

			this.dao.save(historico);

			this.fabrica.erroNaoEsperado();

		} catch (final PersistenceException e) {
			Assert.assertEquals(TypeError.CHAVE_DUPLICADA, e.getType());
			Assert.assertEquals(HISTORICO_DUPLICADO, e.getMessage());
			Assert.assertNotNull(e.getCause());
		} catch (final Exception e) {
			this.fabrica.erroNaoEsperado(e);
		}
	}

	// UPDATE
	@Test
	public void update() {
		try {
			final HistoricoImpl historico = this.create(1, HistoricoComplemento.SIM);

			this.ctx.checking(new Expectations() {
				{
					this.oneOf(HistoricoDaoImplTest.this.defaultDao).update(this.with(historico));
					this.will(returnValue(historico));
				}
			});

			final Historico historicoAlterado = this.dao.update(historico);
			Assert.assertNotNull(historicoAlterado);

		} catch (final Exception e) {
			this.fabrica.erroNaoEsperado(e);
		}

	}

	@Test
	public void updateErroPersistenceException() {
		try {
			final HistoricoImpl historico = this.create(1, HistoricoComplemento.SIM);

			this.ctx.checking(new Expectations() {
				{
					this.oneOf(HistoricoDaoImplTest.this.defaultDao).update(this.with(historico));
					this.will(throwException(new javax.persistence.PersistenceException()));
				}
			});

			this.dao.update(historico);
			Assert.fail("Era para ter dado erro.");

		} catch (final PersistenceException e) {
			Assert.assertEquals(TypeError.ALTERACAO, e.getType());
			Assert.assertEquals(HISTORICO_ALTERAR, e.getMessage());
			Assert.assertNotNull(e.getCause());
		} catch (final Exception e) {
			this.fabrica.erroNaoEsperado(e);
		}

	}

	// REMOVER

	@Test
	public void removerHistorico() {

		this.ctx.checking(new Expectations() {
			{
				this.oneOf(HistoricoDaoImplTest.this.defaultDao).delete(this.with("S70t004.remove"), this.with(Long.valueOf(2)));
			}
		});

		try {
			this.dao.delete(Long.valueOf(2));
		} catch (final PersistenceException e) {
			this.fabrica.erroNaoEsperado(e);
		}
	}

	@Test
	public void removerErroPersistence() {
		this.ctx.checking(new Expectations() {
			{
				this.oneOf(HistoricoDaoImplTest.this.defaultDao).delete(this.with("S70t004.remove"), this.with(Long.valueOf(2)));
				this.will(throwException(new PersistenceException(TypeError.REMOVE, "Erro vindo do banco.")));
			}
		});

		try {
			this.dao.delete(Long.valueOf(2));
			this.fabrica.erroNaoEsperado();
		} catch (final PersistenceException e) {
			Assert.assertEquals(TypeError.REMOVE, e.getType());
			Assert.assertEquals(HISTORICO_REMOVER, e.getMessage());
			Assert.assertNotNull(e.getCause());
		} catch (final Exception e) {
			this.fabrica.erroNaoEsperado(e);
		}
	}

	@Test
	public void removerErroPersistenceIllegalArgumentException() {
		this.ctx.checking(new Expectations() {
			{
				this.oneOf(HistoricoDaoImplTest.this.defaultDao).delete(this.with("S70t004.remove"), this.with(Long.valueOf(2)));
				this.will(throwException(new IllegalArgumentException("Erro vindo do banco.")));
			}
		});

		try {
			this.dao.delete(Long.valueOf(2));
			this.fabrica.erroNaoEsperado();
		} catch (final PersistenceException e) {
			Assert.assertEquals(TypeError.REMOVE, e.getType());
			Assert.assertEquals(HISTORICO_REMOVER, e.getMessage());
			Assert.assertNotNull(e.getCause());
		} catch (final Exception e) {
			this.fabrica.erroNaoEsperado(e);
		}
	}

	@Test
	public void removerErroPersistenceException() {
		this.ctx.checking(new Expectations() {
			{
				this.oneOf(HistoricoDaoImplTest.this.defaultDao).delete(this.with("S70t004.remove"), this.with(Long.valueOf(2)));
				this.will(throwException(new Exception("Erro vindo do banco.")));
			}
		});

		try {
			this.dao.delete(Long.valueOf(2));
			this.fabrica.erroNaoEsperado();
		} catch (final PersistenceException e) {
			Assert.assertEquals(TypeError.REMOVE, e.getType());
			Assert.assertEquals(HISTORICO_REMOVER, e.getMessage());
			Assert.assertNotNull(e.getCause());
		} catch (final Exception e) {
			this.fabrica.erroNaoEsperado(e);
		}
	}

	// BUSCA
	/*
	 * IllegalArgumentException - if the first argument does not denote an entity type or the second argument is not a valid type for that entity's primary key
	 * or is null
	 * 
	 * TransactionRequiredException - if there is no transaction and a lock mode other than NONE is specified
	 * 
	 * OptimisticLockException - if the optimistic version check fails
	 * 
	 * PessimisticLockException - if pessimistic locking fails and the transaction is rolled back
	 * 
	 * LockTimeoutException - if pessimistic locking fails and only the statement is rolled back
	 * 
	 * PersistenceException - if an unsupported lock call is made
	 */
	@Test
	public void getByIdExistente() {
		final Historico historico = this.create(1, HistoricoComplemento.SIM);
		this.ctx.checking(new Expectations() {
			{
				this.oneOf(HistoricoDaoImplTest.this.defaultDao).find(this.with(Long.valueOf(1)));
				this.will(returnValue(historico));
			}
		});

		try {
			final Historico historicoEncontrado = this.dao.getById(Long.valueOf(1));

			Assert.assertNotNull(historicoEncontrado);
			Assert.assertEquals(1, historicoEncontrado.getId());
			Assert.assertEquals("1", historicoEncontrado.getDescricao());
			Assert.assertEquals(HistoricoComplemento.SIM, historicoEncontrado.hasComplemento());
		} catch (final Exception e) {
			this.fabrica.erroNaoEsperado(e);
		}
	}

	@Test
	public void getByIdNaoExistente() {
		this.ctx.checking(new Expectations() {
			{
				this.oneOf(HistoricoDaoImplTest.this.defaultDao).find(this.with(Long.valueOf(1)));
				this.will(returnValue(null));
			}
		});

		try {
			this.dao.getById(Long.valueOf(1));

		} catch (final PersistenceException e) {
			Assert.assertEquals(TypeError.CONSULTA_VAZIA, e.getType());
			Assert.assertEquals(HISTOFICO_CONULTA_VAZIA, e.getMessage());
			Assert.assertNull(e.getCause());
		} catch (final Exception e) {
			this.fabrica.erroNaoEsperado(e);
		}
	}

	// TransactionRequiredException
	@Test
	public void getByIdTransactionRequiredException() {
		this.ctx.checking(new Expectations() {
			{
				this.oneOf(HistoricoDaoImplTest.this.defaultDao).find(this.with(Long.valueOf(1)));
				this.will(throwException(new TransactionRequiredException()));
			}
		});

		try {
			this.dao.getById(Long.valueOf(1));

		} catch (final PersistenceException e) {
			Assert.assertEquals(TypeError.CONSULTA, e.getType());
			Assert.assertEquals(HISTORICO_CONSULTAR, e.getMessage());
			Assert.assertNotNull(e.getCause());
		} catch (final Exception e) {
			this.fabrica.erroNaoEsperado(e);
		}
	}

	// IllegalArgumentException
	@Test
	public void getByIdIllegalArgumentException() {
		this.ctx.checking(new Expectations() {
			{
				this.oneOf(HistoricoDaoImplTest.this.defaultDao).find(this.with(Long.valueOf(1)));
				this.will(throwException(new IllegalArgumentException()));
			}
		});

		try {
			this.dao.getById(Long.valueOf(1));

		} catch (final PersistenceException e) {
			Assert.assertEquals(TypeError.CONSULTA, e.getType());
			Assert.assertEquals(HISTORICO_CONSULTAR, e.getMessage());
			Assert.assertNotNull(e.getCause());
		} catch (final Exception e) {
			this.fabrica.erroNaoEsperado(e);
		}
	}

	// OptimisticLockException

	@Test
	public void getByIdOptimisticLockException() {
		this.ctx.checking(new Expectations() {
			{
				this.oneOf(HistoricoDaoImplTest.this.defaultDao).find(this.with(Long.valueOf(1)));
				this.will(throwException(new OptimisticLockException()));
			}
		});

		try {
			this.dao.getById(Long.valueOf(1));

		} catch (final PersistenceException e) {
			Assert.assertEquals(TypeError.CONSULTA, e.getType());
			Assert.assertEquals(HISTORICO_CONSULTAR, e.getMessage());
			Assert.assertNotNull(e.getCause());
		} catch (final Exception e) {
			this.fabrica.erroNaoEsperado(e);
		}
	}

	// PessimisticLockException
	@Test
	public void getByIdPessimisticLockException() {
		this.ctx.checking(new Expectations() {
			{
				this.oneOf(HistoricoDaoImplTest.this.defaultDao).find(this.with(Long.valueOf(1)));
				this.will(throwException(new PessimisticLockException()));
			}
		});

		try {
			this.dao.getById(Long.valueOf(1));

		} catch (final PersistenceException e) {
			Assert.assertEquals(TypeError.CONSULTA, e.getType());
			Assert.assertEquals(HISTORICO_CONSULTAR, e.getMessage());
			Assert.assertNotNull(e.getCause());
		} catch (final Exception e) {
			this.fabrica.erroNaoEsperado(e);
		}
	}

	// LockTimeoutException
	@Test
	public void getByIdLockTimeoutException() {
		this.ctx.checking(new Expectations() {
			{
				this.oneOf(HistoricoDaoImplTest.this.defaultDao).find(this.with(Long.valueOf(1)));
				this.will(throwException(new LockTimeoutException()));
			}
		});

		try {
			this.dao.getById(Long.valueOf(1));

		} catch (final PersistenceException e) {
			Assert.assertEquals(TypeError.CONSULTA, e.getType());
			Assert.assertEquals(HISTORICO_CONSTULTA_TIMEOUT, e.getMessage());
			Assert.assertNotNull(e.getCause());
		} catch (final Exception e) {
			this.fabrica.erroNaoEsperado(e);
		}
	}

	// PersistenceException
	@Test
	public void getByIdPersistenceException() {
		this.ctx.checking(new Expectations() {
			{
				this.oneOf(HistoricoDaoImplTest.this.defaultDao).find(this.with(Long.valueOf(1)));
				this.will(throwException(new javax.persistence.PersistenceException()));
			}
		});

		try {
			this.dao.getById(Long.valueOf(1));

		} catch (final PersistenceException e) {
			Assert.assertEquals(TypeError.CONSULTA, e.getType());
			Assert.assertEquals(HISTORICO_CONSULTAR, e.getMessage());
			Assert.assertNotNull(e.getCause());
		} catch (final Exception e) {
			this.fabrica.erroNaoEsperado(e);
		}
	}

	@Test
	public void testGetAll() {
		try {

			this.ctx.checking(new Expectations() {
				{
					this.oneOf(HistoricoDaoImplTest.this.defaultDao).findAll();
					this.will(returnValue(HistoricoDaoImplTest.this.createHistoricos(10, HistoricoComplemento.SIM)));
				}
			});

			final Collection<Historico> historicos = this.dao.getAll();
			Assert.assertEquals(10, historicos.size());

		} catch (final Exception e) {
			this.fabrica.erroNaoEsperado(e);
		}
	}

	// IllegalStateException
	@Test
	public void testGetAllIllegalArgumentException() {

		this.ctx.checking(new Expectations() {
			{
				this.oneOf(HistoricoDaoImplTest.this.defaultDao).findAll();
				this.will(throwException(new javax.persistence.PersistenceException()));
			}
		});

		try {
			this.dao.getAll();

		} catch (final PersistenceException e) {
			Assert.assertEquals(TypeError.CONSULTA, e.getType());
			Assert.assertEquals(HISTORICO_CONSULTAR, e.getMessage());
			Assert.assertNotNull(e.getCause());
		} catch (final Exception e) {
			this.fabrica.erroNaoEsperado(e);
		}
	}

	@Test
	public void testNoContains() {
		this.ctx.checking(new Expectations() {
			{
				this.oneOf(HistoricoDaoImplTest.this.defaultDao).find(Long.valueOf(1));
				this.will(throwException(new javax.persistence.PersistenceException()));
			}
		});

		Assert.assertFalse(this.dao.contais(1));

	}

	@Test
	public void testContains() {
		this.ctx.checking(new Expectations() {
			{
				this.oneOf(HistoricoDaoImplTest.this.defaultDao).find(Long.valueOf(1));
				this.will(returnValue(HistoricoDaoImplTest.this.create(1, HistoricoComplemento.SIM)));
			}
		});

		Assert.assertTrue(this.dao.contais(1));

	}

	@After
	public void after() {
		this.ctx.assertIsSatisfied();
	}

}
