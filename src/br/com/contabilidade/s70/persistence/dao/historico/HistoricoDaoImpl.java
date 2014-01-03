package br.com.contabilidade.s70.persistence.dao.historico;

import java.util.LinkedList;
import java.util.List;

import br.com.contabilidade.s70.persistence.beans.Historico;
import br.com.contabilidade.s70.persistence.dao.DefaultDao;
import br.com.contabilidade.s70.persistence.exception.PersistenceException;
import br.com.contabilidade.s70.persistence.exception.PersistenceException.TypeError;

class HistoricoDaoImpl implements HistoricoDao {

	private final DefaultDao<Long, HistoricoImpl> defaultDao;

	public HistoricoDaoImpl(final DefaultDao<Long, HistoricoImpl> defaultDao) {
		this.defaultDao = defaultDao;
	}

	@Override
	public List<Historico> getAll() {
		final List<HistoricoImpl> list = this.defaultDao.findAll();
		return new LinkedList<Historico>(list);
	}

	private HistoricoImpl interfaceParaImplementacao(final Historico e) {
		return new HistoricoImpl(e.getId(), e.getDescricao(), e.hasComplemento().getBancoValue());
	}

	@Override
	public Historico getById(final Long id) {
		final Historico st = this.defaultDao.find(id);
		return st;
	}

	@Override
	public void save(final Historico entity) {
		this.defaultDao.save(this.interfaceParaImplementacao(entity));
	}

	@Override
	public void delete(final Long id) throws PersistenceException {
		try {

			this.defaultDao.delete("S70t004.remove", id);

		} catch (final Exception e) {
			throw new PersistenceException(TypeError.REMOVE, "Não foi possível remover o histórico.", e);
		}
	}
}
