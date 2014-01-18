package br.com.contabilidade.s70.persistence.dao.centrocusto;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.LockTimeoutException;

import br.com.contabilidade.s70.persistence.beans.CentroCusto;
import br.com.contabilidade.s70.persistence.dao.DefaultDao;
import br.com.contabilidade.s70.persistence.exception.ChaveDuplicadaExcpetion;
import br.com.contabilidade.s70.persistence.exception.PersistenceException;
import br.com.contabilidade.s70.persistence.exception.PersistenceException.TypeError;

class CentroCustoDaoImpl implements CentroCustoDao {

	private static final String CENTRO_CUSTO_REMOVER = "Não foi possível remover o centro de custo. Contate o administrador do sistema.";
	private static final String CENTRO_CUSTO_SALVAR = "Não foi possível gravar o histórico. Contate o administrador do sistema.";
	private static final String CENTRO_CUSTO_ALTERAR = "Não foi possível alterar o histórico. Contate o administrador do sistema.";
	private static final String CENTRO_CUSTO_CONSULTAR = "Não foi possível consultar o histórico. Contate o administrador do sistema.";
	private static final String CENTRO_CUSTO_CONSULTA_VAZIA = "Não foi possível encontrar o histórico.";
	private static final String CENTRO_CUSTO_CONSTULTA_TIMEOUT = "A consulta demorou mais do que o esperado. Contate o administrador do sistema.";
	private static final String CENTRO_CUSTO_DUPLICADO = "Já existe um histórico com este código.";

	private final DefaultDao<Long, CentroCustoImpl> defaultDao;

	public CentroCustoDaoImpl(final DefaultDao<Long, CentroCustoImpl> defaultDao) {
		this.defaultDao = defaultDao;
	}

	@Override
	public CentroCusto save(final CentroCusto entity) throws PersistenceException {
		final CentroCustoImpl centroCusto = this.interfaceParaImplementacao(entity);
		try {
			return this.defaultDao.save(centroCusto);
		} catch (final ChaveDuplicadaExcpetion e) {
			throw new PersistenceException(TypeError.CHAVE_DUPLICADA, CENTRO_CUSTO_DUPLICADO, e);
		} catch (final Exception e) {
			throw new PersistenceException(TypeError.SALVAR, CENTRO_CUSTO_SALVAR, e);
		}

	}

	private CentroCustoImpl interfaceParaImplementacao(final CentroCusto e) {
		return null;
	}

	@Override
	public CentroCusto update(final CentroCusto entity) throws PersistenceException {
		final CentroCustoImpl centroCusto = this.interfaceParaImplementacao(entity);
		try {
			return this.defaultDao.update(centroCusto);
		} catch (final Exception e) {
			throw new PersistenceException(TypeError.ALTERACAO, CENTRO_CUSTO_ALTERAR, e);
		}

	}

	@Override
	public void delete(final Long idCentroCusto) throws PersistenceException {
		try {
			final CentroCusto centroCusto = this.defaultDao.find(idCentroCusto);

			this.defaultDao.delete(this.interfaceParaImplementacao(centroCusto));
		} catch (final Exception e) {
			throw new PersistenceException(TypeError.REMOVE, CENTRO_CUSTO_REMOVER, e);
		}
	}

	@Override
	public CentroCusto getById(final Long id) throws PersistenceException {

		try {

			final CentroCusto centroCusto = this.defaultDao.find(id);

			if (centroCusto == null) {
				throw new PersistenceException(TypeError.CONSULTA_VAZIA, CENTRO_CUSTO_CONSULTA_VAZIA);
			}

			return centroCusto;
		} catch (final LockTimeoutException e) {
			throw new PersistenceException(TypeError.CONSULTA, CENTRO_CUSTO_CONSTULTA_TIMEOUT, e);
		} catch (final PersistenceException e) {
			throw e;
		} catch (final Exception e) {
			throw new PersistenceException(TypeError.CONSULTA, CENTRO_CUSTO_CONSULTAR, e);
		}
	}

	@Override
	public Collection<CentroCusto> getAll() throws PersistenceException {
		try {
			final List<CentroCustoImpl> centroCustos = this.defaultDao.findManyResults("CentroCusto.findAll");

			return Collections.unmodifiableCollection(new LinkedList<CentroCusto>(centroCustos));
		} catch (final Exception e) {
			throw new PersistenceException(TypeError.CONSULTA, CENTRO_CUSTO_CONSULTAR, e);
		}
	}

	@Override
	public boolean contais(final long id) {

		try {
			final CentroCusto result = this.defaultDao.find(id);
			if (result != null) {
				return Boolean.TRUE;
			}

		} catch (final Exception e) {
		}
		return Boolean.FALSE;
	}
}
