package br.com.contabilidade.s70.persistence.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaQuery;

public abstract class DaoImp<Chave, Interface, Implementacao> implements Dao<Interface, Implementacao> {

	private final EntityManager em;
	private final Class<Implementacao> entityClass;

	public DaoImp(final EntityManager em, final Class<Implementacao> entityClass) {
		this.em = em;
		this.entityClass = entityClass;
	}

	protected abstract Implementacao interfaceParaImplementacao(Interface e);

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.s70.dao.Dao#save(Interface)
	 */
	@Override
	public final void save(final Interface entity) {
		this.em.persist(this.interfaceParaImplementacao(entity));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.s70.dao.Dao#delete(Interface)
	 */
	@Override
	public final void delete(final Interface entity) {
		this.em.remove(this.interfaceParaImplementacao(entity));
	}

	protected final Implementacao find(final Chave entityID) {
		return this.em.find(this.entityClass, entityID);
	}

	protected final Implementacao findReferenceOnly(final Chave entityID) {
		return this.em.getReference(this.entityClass, entityID);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List<Implementacao> findAll() {
		final CriteriaQuery cq = this.em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(this.entityClass));
		return this.em.createQuery(cq).getResultList();
	}

	@SuppressWarnings("unchecked")
	protected final Implementacao findOneResult(final String namedQuery, final Map<String, Object> parameters) {
		Implementacao result = null;

		try {
			final Query query = this.em.createNamedQuery(namedQuery);

			if ((parameters != null) && !parameters.isEmpty()) {
				this.populateQueryParameters(query, parameters);
			}

			result = (Implementacao) query.getSingleResult();

		} catch (final NoResultException e) {
			System.out.println("No result found for named query: " + namedQuery);
		} catch (final Exception e) {
			System.out.println("Error while running query: " + e.getMessage());
			e.printStackTrace();
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	protected List<Implementacao> findManyResults(final String namedQuery) {
		List<Implementacao> result = null;

		try {
			final Query query = this.em.createNamedQuery(namedQuery);

			result = query.getResultList();

		} catch (final NoResultException e) {
			System.out.println("No result found for named query: " + namedQuery);
		} catch (final Exception e) {
			System.out.println("Error while running query: " + e.getMessage());
			e.printStackTrace();
		}

		return result;
	}

	private void populateQueryParameters(final Query query, final Map<String, Object> parameters) {
		for (final Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
	}

}
