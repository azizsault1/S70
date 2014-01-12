package br.com.contabilidade.s70.persistence;

import java.util.Collection;

import br.com.contabilidade.s70.persistence.beans.Historico;
import br.com.contabilidade.s70.persistence.exception.PersistenceException;

public interface HistoricoFacade {

	public class Factory {

		public static HistoricoFacade create() {
			return new HistoricoFacadeImpl(DriverManagerImpl.INSTANCE);
		}

	}

	public interface ReturnSaved {
		public Historico getHistorico();

		public String getMessage();
	}

	public abstract ReturnSaved save(final Historico s70t004) throws PersistenceException;

	public abstract void delete(Long idHistorico) throws PersistenceException;

	public abstract Collection<Historico> get() throws PersistenceException;

	public abstract Historico get(Long long1) throws PersistenceException;

}
