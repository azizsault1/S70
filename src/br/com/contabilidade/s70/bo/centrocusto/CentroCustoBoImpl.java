package br.com.contabilidade.s70.bo.centrocusto;

import java.util.Collection;

import br.com.contabilidade.s70.bo.exceptions.ValidateException;
import br.com.contabilidade.s70.bo.validators.centrocusto.CentroCustoValidator;
import br.com.contabilidade.s70.persistence.beans.CentroCusto;
import br.com.contabilidade.s70.persistence.exception.PersistenceException;
import br.com.contabilidade.s70.persistence.facade.centrocusto.CentroCustoFacade;
import br.com.contabilidade.s70.persistence.facade.centrocusto.CentroCustoFacade.ReturnSaved;

class CentroCustoBoImpl implements CentroCustoBo {

	private final CentroCustoFacade facade;
	private final CentroCustoValidator validador;

	public CentroCustoBoImpl(final CentroCustoFacade facade) {
		this(facade, CentroCustoValidator.Factory.create());

	}

	public CentroCustoBoImpl(final CentroCustoFacade facade, final CentroCustoValidator validador) {
		this.facade = facade;
		this.validador = validador;
	}

	@Override
	public ReturnSaved save(final CentroCusto centroCusto) throws PersistenceException, ValidateException {
		this.validador.validate(centroCusto);

		return this.facade.save(centroCusto);
	}

	@Override
	public CentroCusto get(final Long long1) throws PersistenceException {
		return this.facade.get(long1);
	}

	@Override
	public Collection<CentroCusto> get() throws PersistenceException {
		return this.facade.get();
	}

	@Override
	public void delete(final Long idCentroCusto) throws PersistenceException {
		this.facade.delete(idCentroCusto);
	}

}
