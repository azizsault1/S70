package br.com.contabilidade.s70.persistence;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contabilidade.s70.fabrica.FabricaObjetos;
import br.com.contabilidade.s70.persistence.beans.Historico;
import br.com.contabilidade.s70.persistence.beans.Historico.HistoricoComplemento;
import br.com.contabilidade.s70.persistence.dao.historico.HistoricoDao;
import br.com.contabilidade.s70.persistence.exception.PersistenceException;
import br.com.contabilidade.s70.persistence.exception.PersistenceException.TypeError;
import br.com.contabilidade.s70.persistence.facade.historico.HistoricoFacadeImpl;
import br.com.contabilidade.s70.persistence.facade.historico.HistoricoFacade.ReturnSaved;
import br.com.contabilidade.s70.persistence.transactional.Transactional;

public class HistoricoFacadeImplIntegrationTest {

	private HistoricoFacadeImpl facade;
	private static DriveManager drive;
	private final FabricaObjetos fabrica;
	private Transactional trans;

	public HistoricoFacadeImplIntegrationTest() {
		this.fabrica = FabricaObjetos.Factory.create();
	}

	@BeforeClass
	public static void beforeClass() {
		drive = DriverManagerImpl.INSTANCE;
		try {
			drive.createDataSource();
		} catch (final PersistenceException e) {
			Assert.fail("Não conseguiu criar o pool de conexão.");
		}
	}

	@Before
	public void before() {
		final EntityManager manager = drive.getEntityManager();

		this.trans = Transactional.Factory.create(manager);
		final Transactional dummyTransactional = new TransactionalTest();
		this.facade = new HistoricoFacadeImpl(dummyTransactional, HistoricoDao.Factory.createDao(manager));
	}

	class TransactionalTest implements Transactional {

		@Override
		public void beginTransaction() {
		}

		@Override
		public void commit() {
		}

		@Override
		public void rollback() {
		}

		@Override
		public void close() {
		}

	}

	@Test
	public void testInsertHistorico() {
		final Historico historico = this.fabrica.createHistorico(1, "Descrição", HistoricoComplemento.NAO);

		try {
			final ReturnSaved historicoSalvo = this.facade.save(historico);

			Assert.assertEquals(historico, historicoSalvo.getHistorico());
			Assert.assertEquals("", historicoSalvo.getMessage());
		} catch (final PersistenceException e) {
			this.fabrica.erroNaoEsperado(e);
		}
	}

	@Test
	public void testDeleteHistorico() {

		try {
			this.facade.delete(Long.valueOf(1));
			this.facade.get(Long.valueOf(1));
			this.fabrica.erroNaoEsperado();
		} catch (final PersistenceException e) {
			Assert.assertEquals("Não foi possível consultar o histórico. Contate o administrador do sistema.", e.getMessage());
			Assert.assertNotNull(e.getCause());
			Assert.assertEquals(TypeError.CONSULTA_VAZIA, e.getType());
			System.out.println("HistoricoFacadeImplIntegrationTest.testDeleteHistorico() " + e.getMessage());
		}
	}

	@AfterClass
	public static void afterClass() {
		drive.closeDataSource();
	}
}
