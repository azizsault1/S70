package br.com.contabilidade.s70.bo.historico;

import java.util.List;

import br.com.contabilidade.s70.bo.exceptions.ValidateException;
import br.com.contabilidade.s70.bo.validators.historico.HistoricoValidator;
import br.com.contabilidade.s70.persistence.HistoricoFacade;
import br.com.contabilidade.s70.persistence.beans.Historico;
import br.com.contabilidade.s70.persistence.exception.PersistenceException;

class HistoricoBoImpl implements HistoricoBo {

	private final HistoricoFacade facade;
	private final HistoricoValidator validador;

	public HistoricoBoImpl(final HistoricoFacade facade) {
		this(facade, HistoricoValidator.Factory.create());

	}

	public HistoricoBoImpl(final HistoricoFacade facade, final HistoricoValidator validador) {
		this.facade = facade;
		this.validador = validador;
	}

	@Override
	public void save(final Historico s70t004) throws PersistenceException, ValidateException {
		this.validador.validate(s70t004);
		this.facade.save(s70t004);
	}

	@Override
	public Historico get(final Long long1) throws PersistenceException {
		return this.facade.get(long1);
	}

	@Override
	public List<Historico> get() throws PersistenceException {
		return this.facade.get();
	}

	@Override
	public void delete(final Long idHistorico) throws PersistenceException {
		this.facade.delete(idHistorico);
	}

}
