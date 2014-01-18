package br.com.contabilidade.s70.persistence.facade.centrocusto;

import java.util.Collection;

import javax.persistence.EntityManager;

import br.com.contabilidade.s70.persistence.DriveManager;
import br.com.contabilidade.s70.persistence.beans.CentroCusto;
import br.com.contabilidade.s70.persistence.dao.centrocusto.CentroCustoDao;
import br.com.contabilidade.s70.persistence.exception.PersistenceException;
import br.com.contabilidade.s70.persistence.exception.PersistenceException.TypeError;
import br.com.contabilidade.s70.persistence.transactional.Transactional;

public class CentroCustoFacadeImpl implements CentroCustoFacade {

	private static final String HISTORICO_SALVO = "Historico Salvo com sucesso.";
	private static final String HISTORICO_ALTERADO = "Historico alterado com sucesso.";

	private final CentroCustoDao dao;
	private final Transactional transactional;

	public CentroCustoFacadeImpl(final DriveManager driveManager) {
		final EntityManager em = driveManager.getEntityManager();
		this.dao = CentroCustoDao.Factory.createDao(em);
		this.transactional = Transactional.Factory.create(em);
	}

	public CentroCustoFacadeImpl(final Transactional transactional, final CentroCustoDao dao) {
		this.dao = dao;
		this.transactional = transactional;
	}

	@Override
	public ReturnSaved save(final CentroCusto centroCusto) throws PersistenceException {

		final CentroCusto centroCustoSalvo;
		final String message;

		this.transactional.beginTransaction();

		try {
			if (this.contains(centroCusto.getId())) {
				centroCustoSalvo = this.update(centroCusto);
				message = HISTORICO_ALTERADO;
			} else {
				centroCustoSalvo = this.persist(centroCusto);
				message = HISTORICO_SALVO;
			}

			this.transactional.commit();

			return new ReturnSavedImp(centroCustoSalvo, message);

		} catch (final javax.persistence.PersistenceException e) {
			this.transactional.rollback();
			throw new PersistenceException(TypeError.TRANSACAO, "Falha no controle de transação. Contate o administrador do sistema.", e);
		} catch (final Exception e) {
			this.transactional.rollback();
			throw e;
		} finally {
			this.transactional.close();
		}

	}

	private boolean contains(final long id) {
		return this.dao.contais(id);
	}

	private CentroCusto update(final CentroCusto s70t004) throws PersistenceException {
		return this.dao.update(s70t004);
	}

	private CentroCusto persist(final CentroCusto s70t004) throws PersistenceException {
		final CentroCusto historico = this.dao.save(s70t004);
		return historico;
	}

	@Override
	public CentroCusto get(final Long id) throws PersistenceException {
		final CentroCusto historico = this.dao.getById(id);
		this.transactional.close();
		return historico;
	}

	@Override
	public Collection<CentroCusto> get() throws PersistenceException {
		final Collection<CentroCusto> historicos = this.dao.getAll();
		this.transactional.close();
		return historicos;
	}

	@Override
	public void delete(final Long idHistorico) throws PersistenceException {
		this.transactional.beginTransaction();
		try {
			this.dao.delete(idHistorico);

			this.transactional.commit();
		} catch (final Exception e) {
			this.transactional.rollback();
			throw e;
		} finally {
			this.transactional.close();
		}

	}

	class ReturnSavedImp implements ReturnSaved {

		private final CentroCusto historico;
		private final String message;

		public ReturnSavedImp(final CentroCusto historico, final String message) {
			this.historico = historico;
			this.message = message;

		}

		@Override
		public CentroCusto getCentroCusto() {
			return this.historico;
		}

		@Override
		public String getMessage() {
			return this.message;
		}

	}

}
