package br.com.contabilidade.s70.persistence.dao.historico;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
	private static final String HISTORICO_DUPLICADO = "Já existe um histórico com este código.";

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
			throw new PersistenceException(TypeError.CHAVE_DUPLICADA, HISTORICO_DUPLICADO, e);
		} catch (final Exception e) {
			throw new PersistenceException(TypeError.SALVAR, HISTORICO_SALVAR, e);
		}

	}

	private HistoricoImpl interfaceParaImplementacao(final Historico e) {
		return new HistoricoImpl(e.getId(), e.getDescricao(), e.hasComplemento().toSave());
	}

	@Override
	public Historico update(final Historico entity) throws PersistenceException {
		final HistoricoImpl historico = this.interfaceParaImplementacao(entity);
		try {
			return this.defaultDao.update(historico);
		} catch (final Exception e) {
			throw new PersistenceException(TypeError.ALTERACAO, HISTORICO_ALTERAR, e);
		}

	}

	@Override
	public void delete(final Long idHistorico) throws PersistenceException {
		try {
			final Historico historico = this.defaultDao.find(idHistorico);

			this.defaultDao.delete(this.interfaceParaImplementacao(historico));
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
			final List<HistoricoImpl> historicos = this.defaultDao.findManyResults("S70t004.findAll");

			return Collections.unmodifiableCollection(new LinkedList<Historico>(historicos));
		} catch (final Exception e) {
			throw new PersistenceException(TypeError.CONSULTA, HISTORICO_CONSULTAR, e);
		}
	}

	@Override
	public boolean contais(final long id) {

		try {
			final Historico result = this.defaultDao.find(id);
			if (result != null) {
				return Boolean.TRUE;
			}

		} catch (final Exception e) {
		}
		return Boolean.FALSE;
	}
}
