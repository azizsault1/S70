package br.com.contabilidade.s70.persistence.dao.historico;

import java.util.Collection;

import javax.persistence.EntityManager;

import br.com.contabilidade.s70.persistence.beans.Historico;
import br.com.contabilidade.s70.persistence.dao.DefaultDao;
import br.com.contabilidade.s70.persistence.dao.DefaultDaoImpl;

public interface HistoricoDao {

	class Factory {
		public static HistoricoDao createDao(final EntityManager em) {
			final DefaultDao<Long, HistoricoImpl> defaultDao = new DefaultDaoImpl<>(em, HistoricoImpl.class);
			return new HistoricoDaoImpl(defaultDao);
		}
	}

	public abstract void save(Historico s70t004);

	public abstract void delete(Long idHistorico);

	public abstract Historico getById(Long id);

	public abstract Collection<Historico> getAll();

}
