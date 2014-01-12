package br.com.contabilidade.s70.persistence.dao.historico;

import java.util.Collection;

import javax.persistence.EntityManager;

import br.com.contabilidade.s70.persistence.beans.Historico;
import br.com.contabilidade.s70.persistence.dao.DefaultDao;
import br.com.contabilidade.s70.persistence.dao.DefaultDaoImpl;
import br.com.contabilidade.s70.persistence.exception.PersistenceException;

public interface HistoricoDao {

	class Factory {
		public static HistoricoDao createDao(final EntityManager em) {
			final DefaultDao<Long, HistoricoImpl> defaultDao = new DefaultDaoImpl<>(em, HistoricoImpl.class);
			return new HistoricoDaoImpl(defaultDao);
		}
	}

	public abstract Historico save(Historico s70t004) throws PersistenceException;

	public abstract Historico update(Historico s70t004) throws PersistenceException;

	public abstract void delete(Long idHistorico) throws PersistenceException;

	public abstract Historico getById(Long id) throws PersistenceException;

	public abstract Collection<Historico> getAll() throws PersistenceException;

	public abstract boolean contais(long id);

}
