package br.com.contabilidade.s70.persistence.dao.historico;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.contabilidade.s70.persistence.beans.Historico;
import br.com.contabilidade.s70.persistence.dao.Dao;

public interface HistoricoDao extends Dao<Historico, HistoricoImpl> {

	public List<Historico> getAll();

	public Historico getById(Long id);

	class Factory {
		public static HistoricoDao createDao(final EntityManager em) {
			return new HistoricoDaoImpl(em);
		}
	}

}
