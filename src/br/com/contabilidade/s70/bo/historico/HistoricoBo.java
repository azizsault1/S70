package br.com.contabilidade.s70.bo.historico;

import java.util.Collection;

import br.com.contabilidade.s70.bo.exceptions.ValidateException;
import br.com.contabilidade.s70.persistence.HistoricoFacade;
import br.com.contabilidade.s70.persistence.beans.Historico;
import br.com.contabilidade.s70.persistence.exception.PersistenceException;

public interface HistoricoBo {

	public Historico save(final Historico s70t004) throws PersistenceException, ValidateException;

	public Historico get(Long long1) throws PersistenceException;

	public Collection<Historico> get() throws PersistenceException;

	public void delete(Long idHistorico) throws PersistenceException;

	public class Factory {
		public static HistoricoBo create() {
			return new HistoricoBoImpl(HistoricoFacade.Factory.create());
		}

		public static HistoricoBo create(final HistoricoFacade facade) {
			return new HistoricoBoImpl(facade);
		}
	}
}
