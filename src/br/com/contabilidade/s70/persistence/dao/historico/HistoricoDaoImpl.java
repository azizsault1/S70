package br.com.contabilidade.s70.persistence.dao.historico;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import br.com.contabilidade.s70.persistence.beans.Historico;
import br.com.contabilidade.s70.persistence.dao.DefaultDao;

class HistoricoDaoImpl implements HistoricoDao {

	private final DefaultDao<Long, HistoricoImpl> defaultDao;

	public HistoricoDaoImpl(final DefaultDao<Long, HistoricoImpl> defaultDao) {
		this.defaultDao = defaultDao;
	}

	@Override
	public Collection<Historico> getAll() {
		return Collections.unmodifiableCollection(new LinkedList<Historico>(this.defaultDao.findAll()));
	}

	@Override
	public void save(final Historico s70t004) {

	}

	@Override
	public void delete(final Long idHistorico) {
		// TODO Auto-generated method stub

	}

	@Override
	public Historico getById(final Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
