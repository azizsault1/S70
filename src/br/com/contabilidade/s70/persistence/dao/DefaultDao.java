package br.com.contabilidade.s70.persistence.dao;

import java.util.List;
import java.util.Map;

public interface DefaultDao<Chave, Implementacao> {

	public abstract void save(Implementacao entity);

	public abstract void delete(Implementacao entity);

	public abstract Implementacao find(final Chave entityID);

	public abstract Implementacao findReferenceOnly(final Chave entityID);

	public abstract List<Implementacao> findAll();

	public abstract Implementacao findOneResult(final String namedQuery, final Map<String, Object> parameters);

	public abstract List<Implementacao> findManyResults(final String namedQuery);

	public abstract void delete(String string, Chave id);

}