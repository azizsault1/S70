package br.com.contabilidade.s70.persistence.dao.historico;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contabilidade.s70.fabrica.FabricaObjetos;
import br.com.contabilidade.s70.persistence.DriveManager;
import br.com.contabilidade.s70.persistence.DriverManagerImpl;
import br.com.contabilidade.s70.persistence.beans.Historico;
import br.com.contabilidade.s70.persistence.beans.Historico.HistoricoComplemento;
import br.com.contabilidade.s70.persistence.exception.PersistenceException;
import br.com.contabilidade.s70.persistence.exception.PersistenceException.TypeError;

public class HistoricoDaoImplIntegrationTest {

	private static DriveManager drive;
	private final FabricaObjetos fabrica;
	private HistoricoDao dao;
	private EntityManager manager;

	public HistoricoDaoImplIntegrationTest() {
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
		this.manager = drive.getEntityManager();

		this.dao = HistoricoDao.Factory.createDao(this.manager);
		this.manager.getTransaction().begin();
	}

	@Test
	public void testInsertHistorico() {
		final Historico historico = this.fabrica.createHistorico(1, "Descrição", HistoricoComplemento.NAO);

		try {

			this.buscarHistoricoNaoEsperado(historico);

			final Historico historicoSalvo = this.dao.save(historico);
			Assert.assertEquals(historico, historicoSalvo);

			this.buscarHistoricoComSucesso(historicoSalvo);

		} catch (final PersistenceException e) {
			this.fabrica.erroNaoEsperado(e);
		}

	}

	private void buscarHistoricoComSucesso(final Historico historico) {
		try {
			final Historico histConsult = this.dao.getById(historico.getId());
			Assert.assertEquals(historico.getId(), histConsult.getId());
		} catch (final Exception e) {
			this.fabrica.erroNaoEsperado(e);
		}
	}

	private void buscarHistoricoNaoEsperado(final Historico historico) {
		try {
			this.dao.getById(historico.getId());
			this.fabrica.erroNaoEsperado();
		} catch (final PersistenceException e) {
			Assert.assertEquals("Não foi possível encontrar o histórico.", e.getMessage());
			Assert.assertEquals(TypeError.CONSULTA_VAZIA, e.getType());
		} catch (final Exception e) {
			this.fabrica.erroNaoEsperado(e);
		}
	}

	@Test
	public void testDeleteHistorico() {

		try {
			final Historico historico = this.fabrica.createHistorico(1, "Descricao", HistoricoComplemento.NAO);

			this.manager.getTransaction().begin();

			this.buscarHistoricoComSucesso(historico);

			this.dao.delete(Long.valueOf(1));

			this.buscarHistoricoNaoEsperado(historico);

		} catch (final PersistenceException e) {
			Assert.assertEquals("Não foi possível consultar o histórico. Contate o administrador do sistema.", e.getMessage());
			Assert.assertNotNull(e.getCause());
			Assert.assertEquals(TypeError.CONSULTA_VAZIA, e.getType());
			System.out.println("HistoricoFacadeImplIntegrationTest.testDeleteHistorico() " + e.getMessage());
		}
	}

	@After
	public void after() {
		this.manager.getTransaction().rollback();

		this.manager.close();

	}

	@AfterClass
	public static void afterClass() {
		drive.closeDataSource();
	}
}
