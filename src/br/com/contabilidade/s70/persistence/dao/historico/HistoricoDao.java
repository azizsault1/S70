package br.com.contabilidade.s70.persistence.dao.historico;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.contabilidade.s70.persistence.beans.Historico;
import br.com.contabilidade.s70.persistence.dao.DefaultDao;
import br.com.contabilidade.s70.persistence.dao.DefaultDaoImpl;
import br.com.contabilidade.s70.persistence.exception.PersistenceException;

public interface HistoricoDao {

	public void save(Historico s70t004);

	void delete(Long entity) throws PersistenceException;

	public List<Historico> getAll();

	public Historico getById(Long id);

	class Factory {
		public static HistoricoDao createDao(final EntityManager em) {
			final DefaultDao<Long, HistoricoImpl> defaultDao = new DefaultDaoImpl<Long, HistoricoImpl>(em, HistoricoImpl.class);
			return new HistoricoDaoImpl(defaultDao);
		}
	}

}
