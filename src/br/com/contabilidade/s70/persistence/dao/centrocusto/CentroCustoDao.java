package br.com.contabilidade.s70.persistence.dao.centrocusto;

import java.util.Collection;

import javax.persistence.EntityManager;

import br.com.contabilidade.s70.persistence.beans.CentroCusto;
import br.com.contabilidade.s70.persistence.dao.DefaultDao;
import br.com.contabilidade.s70.persistence.dao.DefaultDaoImpl;
import br.com.contabilidade.s70.persistence.exception.PersistenceException;

public interface CentroCustoDao {

	class Factory {
		public static CentroCustoDao createDao(final EntityManager em) {
			final DefaultDao<Long, S70t001> defaultDao = new DefaultDaoImpl<>(em, S70t001.class);
			return new CentroCustoDaoImpl(defaultDao);
		}
	}

	public abstract CentroCusto save(CentroCusto centroCusto) throws PersistenceException;

	public abstract CentroCusto update(CentroCusto centroCusto) throws PersistenceException;

	public abstract void delete(Long idCentroCusto) throws PersistenceException;

	public abstract CentroCusto getById(Long idCentroCusto) throws PersistenceException;

	public abstract Collection<CentroCusto> getAll() throws PersistenceException;

	public abstract boolean contais(long id);

}
