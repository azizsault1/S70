package br.com.contabilidade.s70.persistence.dao.historico;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import javax.persistence.LockTimeoutException;

import br.com.contabilidade.s70.persistence.beans.Historico;
import br.com.contabilidade.s70.persistence.dao.DefaultDao;
import br.com.contabilidade.s70.persistence.exception.ChaveDuplicadaExcpetion;
import br.com.contabilidade.s70.persistence.exception.PersistenceException;
import br.com.contabilidade.s70.persistence.exception.PersistenceException.TypeError;

class HistoricoDaoImpl implements HistoricoDao {

	private static final String HISTORICO_REMOVER = "Não foi possível remover o histórico. Contate o administrador do sistema.";
	private static final String HISTORICO_SALVAR = "Não foi possível gravar o histórico. Contate o administrador do sistema.";
	private static final String HISTORICO_ALTERAR = "Não foi possível alterar o histórico. Contate o administrador do sistema.";
	private static final String HISTORICO_CONSULTAR = "Não foi possível consultar o histórico. Contate o administrador do sistema.";
	private static final String HISTOFICO_CONULTA_VAZIA = "Não foi possível encontrar o histórico.";
	private static final String HISTORICO_CONSTULTA_TIMEOUT = "A consulta demorou mais do que o esperado. Contate o administrador do sistema.";

	private final DefaultDao<Long, HistoricoImpl> defaultDao;

	public HistoricoDaoImpl(final DefaultDao<Long, HistoricoImpl> defaultDao) {
		this.defaultDao = defaultDao;
	}

	@Override
	public Historico save(final Historico entity) throws PersistenceException {
		final HistoricoImpl historico = this.interfaceParaImplementacao(entity);
		try {
			return this.defaultDao.save(historico);
		} catch (final ChaveDuplicadaExcpetion e) {
			return this.update(historico);
		} catch (final Exception e) {
			throw new PersistenceException(TypeError.SALVAR, HISTORICO_SALVAR, e);
		}

	}

	private HistoricoImpl interfaceParaImplementacao(final Historico e) {
		return new HistoricoImpl(e.getId(), e.getDescricao(), e.hasComplemento().toSave());
	}

	private Historico update(final HistoricoImpl entity) throws PersistenceException {
		try {
			return this.defaultDao.update(entity);
		} catch (final Exception e) {
			throw new PersistenceException(TypeError.ALTERACAO, HISTORICO_ALTERAR, e);
		}

	}

	@Override
	public void delete(final Long idHistorico) throws PersistenceException {
		try {
			this.defaultDao.delete("S70t004.remove", idHistorico);
		} catch (final Exception e) {
			throw new PersistenceException(TypeError.REMOVE, HISTORICO_REMOVER, e);
		}
	}

	@Override
	public Historico getById(final Long id) throws PersistenceException {

		try {

			final Historico historico = this.defaultDao.find(id);

			if (historico == null) {
				throw new PersistenceException(TypeError.CONSULTA_VAZIA, HISTOFICO_CONULTA_VAZIA);
			}

			return historico;
		} catch (final LockTimeoutException e) {
			throw new PersistenceException(TypeError.CONSULTA, HISTORICO_CONSTULTA_TIMEOUT, e);
		} catch (final PersistenceException e) {
			throw e;
		} catch (final Exception e) {
			throw new PersistenceException(TypeError.CONSULTA, HISTORICO_CONSULTAR, e);
		}
	}

	@Override
	public Collection<Historico> getAll() throws PersistenceException {
		try {
			return Collections.unmodifiableCollection(new LinkedList<Historico>(this.defaultDao.findAll()));
		} catch (final Exception e) {
			throw new PersistenceException(TypeError.CONSULTA, HISTORICO_CONSULTAR, e);
		}
	}

}
