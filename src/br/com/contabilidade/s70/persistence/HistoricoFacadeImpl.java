package br.com.contabilidade.s70.persistence;

import java.util.Collection;

import javax.persistence.EntityManager;

import br.com.contabilidade.s70.persistence.beans.Historico;
import br.com.contabilidade.s70.persistence.dao.historico.HistoricoDao;
import br.com.contabilidade.s70.persistence.exception.PersistenceException;
import br.com.contabilidade.s70.persistence.exception.PersistenceException.TypeError;
import br.com.contabilidade.s70.persistence.transactional.Transactional;

public class HistoricoFacadeImpl implements HistoricoFacade {

	private static final String HISTORICO_SALVO = "Historico Salvo com sucesso.";
	private static final String HISTORICO_ALTERADO = "Historico alterado com sucesso.";

	private final HistoricoDao dao;
	private final Transactional transactional;

	public HistoricoFacadeImpl(final DriveManager driveManager) {
		final EntityManager em = driveManager.getEntityManager();
		this.dao = HistoricoDao.Factory.createDao(em);
		this.transactional = Transactional.Factory.create(em);
	}

	@Override
	public ReturnSaved save(final Historico s70t004) throws PersistenceException {

		final Historico historicoSalvo;
		final String message;

		this.transactional.beginTransaction();

		try {
			if (this.contains(s70t004.getId())) {
				historicoSalvo = this.update(s70t004);
				message = HISTORICO_ALTERADO;
			} else {
				historicoSalvo = this.persist(s70t004);
				message = HISTORICO_SALVO;
			}

			this.transactional.commit();

			return new ReturnSavedImp(historicoSalvo, message);

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

	private Historico update(final Historico s70t004) throws PersistenceException {
		return this.dao.update(s70t004);
	}

	private Historico persist(final Historico s70t004) throws PersistenceException {
		final Historico historico = this.dao.save(s70t004);
		return historico;
	}

	@Override
	public Historico get(final Long id) throws PersistenceException {
		System.out.println("HistoricoFacadeImpl.get(Long " + id + ")");
		final Historico historico = this.dao.getById(id);
		this.transactional.close();
		return historico;
	}

	@Override
	public Collection<Historico> get() throws PersistenceException {
		System.out.println("HistoricoFacadeImpl.get()");
		final Collection<Historico> historicos = this.dao.getAll();
		this.transactional.close();
		return historicos;
	}

	@Override
	public void delete(final Long idHistorico) throws PersistenceException {
		this.transactional.beginTransaction();
		try {
			this.dao.delete(idHistorico);
		} catch (final Exception e) {
			this.transactional.rollback();
			throw e;
		} finally {
			this.transactional.close();
		}

	}

	class ReturnSavedImp implements ReturnSaved {

		private final Historico historico;
		private final String message;

		public ReturnSavedImp(final Historico historico, final String message) {
			this.historico = historico;
			this.message = message;

		}

		@Override
		public Historico getHistorico() {
			return this.historico;
		}

		@Override
		public String getMessage() {
			return this.message;
		}

	}

}
