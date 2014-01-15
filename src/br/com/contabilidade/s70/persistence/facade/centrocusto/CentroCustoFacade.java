package br.com.contabilidade.s70.persistence.facade.centrocusto;

import java.util.Collection;

import br.com.contabilidade.s70.persistence.DriverManagerImpl;
import br.com.contabilidade.s70.persistence.beans.CentroCusto;
import br.com.contabilidade.s70.persistence.exception.PersistenceException;

public interface CentroCustoFacade {

	public class Factory {

		public static CentroCustoFacade create() {
			return new CentroCustoFacadeImpl(DriverManagerImpl.INSTANCE);
		}

	}

	public interface ReturnSaved {
		public CentroCusto getCentroCusto();

		public String getMessage();
	}

	public abstract ReturnSaved save(final CentroCusto centroCusto) throws PersistenceException;

	public abstract void delete(Long idCentroCusto) throws PersistenceException;

	public abstract Collection<CentroCusto> get() throws PersistenceException;

	public abstract CentroCusto get(Long centroCusto) throws PersistenceException;

}
