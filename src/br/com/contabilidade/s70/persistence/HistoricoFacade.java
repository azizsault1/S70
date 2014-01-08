package br.com.contabilidade.s70.persistence;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.contabilidade.s70.persistence.beans.Historico;
import br.com.contabilidade.s70.persistence.dao.historico.HistoricoDao;
import br.com.contabilidade.s70.persistence.exception.PersistenceException;
import br.com.contabilidade.s70.persistence.transactional.Transactional;

public interface HistoricoFacade {

	public class Factory {
		public static HistoricoFacade create(final Transactional transactional, final HistoricoDao dao) {
			return new HistoricoFacadeImpl(transactional, dao);
		}

		public static HistoricoFacade create() {
			final EntityManagerFactory emf = Persistence.createEntityManagerFactory("S70");
			final EntityManager em = emf.createEntityManager();
			final Transactional transactional = Transactional.Factory.create(em);
			final HistoricoDao dao = HistoricoDao.Factory.createDao(em);
			return new HistoricoFacadeImpl(transactional, dao);
		}

	}

	public abstract Historico save(final Historico s70t004) throws PersistenceException;

	public abstract void delete(Long idHistorico) throws PersistenceException;

	public abstract Collection<Historico> get() throws PersistenceException;

	public abstract Historico get(Long long1) throws PersistenceException;

}
