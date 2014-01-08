package br.com.contabilidade.s70.persistence;

import java.util.Collection;

import br.com.contabilidade.s70.persistence.beans.Historico;
import br.com.contabilidade.s70.persistence.dao.historico.HistoricoDao;
import br.com.contabilidade.s70.persistence.exception.PersistenceException;
import br.com.contabilidade.s70.persistence.transactional.Transactional;

public class HistoricoFacadeImpl implements HistoricoFacade {

	private final Transactional transactional;
	private final HistoricoDao dao;

	public HistoricoFacadeImpl(final Transactional transactional, final HistoricoDao dao) {
		this.transactional = transactional;
		this.dao = dao;
	}

	@Override
	public Historico save(final Historico s70t004) throws PersistenceException {
		this.transactional.beginTransaction();
		try {
			return this.dao.save(s70t004);
		} catch (final Exception e) {
			this.transactional.rollback();
			throw e;
		} finally {
			this.transactional.commitAndCloseTransaction();
		}
	}

	@Override
	public Historico get(final Long id) throws PersistenceException {
		return this.dao.getById(id);
	}

	@Override
	public Collection<Historico> get() throws PersistenceException {
		return this.dao.getAll();
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
			this.transactional.commitAndCloseTransaction();
		}

	}

}
