package br.com.contabilidade.s70.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.contabilidade.s70.persistence.beans.Historico;
import br.com.contabilidade.s70.persistence.dao.historico.HistoricoDao;
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

	public void create(final Historico s70t004);

	public Historico get(Long long1);

	public List<Historico> get();

	public void delete(Long idHistorico);

}
