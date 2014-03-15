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

	private final DefaultDao<Long, S70t001> defaultDao;

	public CentroCustoDaoImpl(final DefaultDao<Long, S70t001> defaultDao) {
		this.defaultDao = defaultDao;
	}

	@Override
	public CentroCusto save(final CentroCusto entity) throws PersistenceException {
		final S70t001 centroCusto = this.interfaceParaImplementacao(entity);
		try {
			final S70t001 s70 = this.defaultDao.save(centroCusto);
			return this.implementacaoParaInterface(s70);
		} catch (final ChaveDuplicadaExcpetion e) {
			throw new PersistenceException(TypeError.CHAVE_DUPLICADA, CENTRO_CUSTO_DUPLICADO, e);
		} catch (final Exception e) {
			throw new PersistenceException(TypeError.SALVAR, CENTRO_CUSTO_SALVAR, e);
		}

	}

	private S70t001 interfaceParaImplementacao(final CentroCusto c) {
		return new S70t001(c);
	}

	private CentroCusto implementacaoParaInterface(final S70t001 s70t001) {
		return new CentroCustoImpl(s70t001);
	}

	@Override
	public CentroCusto update(final CentroCusto entity) throws PersistenceException {
		final S70t001 centroCusto = this.interfaceParaImplementacao(entity);
		try {
			final S70t001 s70 = this.defaultDao.update(centroCusto);
			return this.implementacaoParaInterface(s70);
		} catch (final Exception e) {
			throw new PersistenceException(TypeError.ALTERACAO, CENTRO_CUSTO_ALTERAR, e);
		}

	}

	@Override
	public void delete(final Long idCentroCusto) throws PersistenceException {
		try {
			final S70t001 s70 = this.defaultDao.find(idCentroCusto);

			this.defaultDao.delete(s70);
		} catch (final Exception e) {
			throw new PersistenceException(TypeError.REMOVE, CENTRO_CUSTO_REMOVER, e);
		}
	}

	@Override
	public CentroCusto getById(final Long id) throws PersistenceException {

		try {

			final S70t001 s70 = this.defaultDao.find(id);

			if (s70 == null) {
				throw new PersistenceException(TypeError.CONSULTA_VAZIA, CENTRO_CUSTO_CONSULTA_VAZIA);
			}

			return this.implementacaoParaInterface(s70);
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
			final List<S70t001> s70s = this.defaultDao.findManyResults("CentroCusto.findAll");
			final List<CentroCusto> centroCustos = new LinkedList<>();

			for (final S70t001 s70t001 : s70s) {
				centroCustos.add(this.implementacaoParaInterface(s70t001));
			}

			return Collections.unmodifiableCollection(centroCustos);
		} catch (final Exception e) {
			throw new PersistenceException(TypeError.CONSULTA, CENTRO_CUSTO_CONSULTAR, e);
		}
	}

	@Override
	public boolean contais(final long id) {

		try {
			final S70t001 result = this.defaultDao.find(id);

			if (result != null) {
				return Boolean.TRUE;
			}

		} catch (final Exception e) {
		}
		return Boolean.FALSE;
	}
}
