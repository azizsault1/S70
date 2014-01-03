package br.com.contabilidade.s70.persistence.dao.historico;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.contabilidade.s70.persistence.beans.Historico;
import br.com.contabilidade.s70.persistence.dao.DaoImp;

class HistoricoDaoImpl extends DaoImp<Long, Historico, HistoricoImpl> implements HistoricoDao {

	public HistoricoDaoImpl(final EntityManager em) {
		super(em, HistoricoImpl.class);
	}

	@Override
	public List<Historico> getAll() {
		final List<HistoricoImpl> list = super.findAll();
		return new LinkedList<Historico>(list);
	}

	@Override
	protected HistoricoImpl interfaceParaImplementacao(final Historico e) {
		return new HistoricoImpl(e.getId(), e.getDescricao(), e.hasComplemento().getBancoValue());
	}

	@Override
	public Historico getById(final Long id) {
		final Historico st = super.find(id);
		return st;
	}

}
