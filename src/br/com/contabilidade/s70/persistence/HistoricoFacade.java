package br.com.contabilidade.s70.persistence;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.contabilidade.s70.persistence.beans.Historico;
import br.com.contabilidade.s70.persistence.dao.historico.HistoricoDao;
import br.com.contabilidade.s70.persistence.exception.PersistenceException;
import br.com.contabilidade.s70.persistence.transactional.Transactional;

public interface HistoricoFacade {

	public void create(final Historico s70t004);

	public Historico get(Long long1);

	public List<Historico> get();

	public void delete(Long idHistorico) throws PersistenceException;

	public class Factory {

		public static HistoricoFacade create() {
			final EntityManager em = DriverManager.INSTANCE.getEntityManager();
			final Transactional transactional = Transactional.Factory.create(em);
			final HistoricoDao dao = HistoricoDao.Factory.createDao(em);
			return new HistoricoFacadeImpl(transactional, dao);
		}
	}

}
