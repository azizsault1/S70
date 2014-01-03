package br.com.contabilidade.s70.persistence;

import java.util.List;

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
	public void create(final Historico s70t004) {
		this.transactional.beginTransaction();
		this.dao.save(s70t004);
		this.transactional.commitAndCloseTransaction();
	}

	@Override
	public Historico get(final Long id) {
		return this.dao.getById(id);
	}

	@Override
	public List<Historico> get() {
		return this.dao.getAll();
	}

	@Override
	public void delete(final Long idHistorico) throws PersistenceException {
		this.transactional.beginTransaction();
		this.dao.delete(idHistorico);
		this.transactional.commitAndCloseTransaction();
	}

}
