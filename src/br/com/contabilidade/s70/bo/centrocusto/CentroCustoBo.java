package br.com.contabilidade.s70.bo.centrocusto;

import java.util.Collection;

import br.com.contabilidade.s70.bo.exceptions.ValidateException;
import br.com.contabilidade.s70.persistence.beans.CentroCusto;
import br.com.contabilidade.s70.persistence.exception.PersistenceException;
import br.com.contabilidade.s70.persistence.facade.centrocusto.CentroCustoFacade;
import br.com.contabilidade.s70.persistence.facade.centrocusto.CentroCustoFacade.ReturnSaved;

public interface CentroCustoBo {

	public ReturnSaved save(final CentroCusto s70t004) throws PersistenceException, ValidateException;

	public CentroCusto get(Long id) throws PersistenceException;

	public Collection<CentroCusto> get() throws PersistenceException;

	public void delete(Long idCentroCusto) throws PersistenceException;

	public class Factory {
		public static CentroCustoBo create() {
			return new CentroCustoBoImpl(CentroCustoFacade.Factory.create());
		}

		public static CentroCustoBo create(final CentroCustoFacade facade) {
			return new CentroCustoBoImpl(facade);
		}
	}
}
