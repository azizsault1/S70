package br.com.contabilidade.s70.persistence.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public final class DefaultDaoImpl<Chave, Implementacao> implements DefaultDao<Chave, Implementacao> {

	private final EntityManager em;
	private final Class<Implementacao> entityClass;

	public DefaultDaoImpl(final EntityManager em, final Class<Implementacao> entityClass) {
		this.em = em;
		this.entityClass = entityClass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.s70.dao.Dao#save(Interface)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.contabilidade.s70.persistence.dao.DefaultDao#save(Implementacao)
	 */
	@Override
	public final void save(final Implementacao entity) {
		this.em.persist(entity);
		this.em.flush();
	}

	@Override
	public void update(final Implementacao entity) {
		this.em.merge(entity);
		this.em.flush();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.s70.dao.Dao#delete(Interface)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.contabilidade.s70.persistence.dao.DefaultDao#delete(Implementacao)
	 */
	@Override
	public final void delete(final Implementacao entity) {
		final Implementacao object = this.em.merge(entity);
		this.em.remove(object);
	}

	@Override
	public final Implementacao find(final Chave entityID) {
		return this.em.find(this.entityClass, entityID);
	}

	@Override
	public final Implementacao findReferenceOnly(final Chave entityID) {
		return this.em.getReference(this.entityClass, entityID);
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Implementacao> findAll() {
		final CriteriaQuery cq = this.em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(this.entityClass));
		return this.em.createQuery(cq).getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public final Implementacao findOneResult(final String namedQuery, final Map<String, Object> parameters) {

		final Query query = this.em.createNamedQuery(namedQuery);

		if ((parameters != null) && !parameters.isEmpty()) {
			this.populateQueryParameters(query, parameters);
		}

		return (Implementacao) query.getSingleResult();

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Implementacao> findManyResults(final String namedQuery) {
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

	@Override
	public void delete(final String namedQuery, final Chave id) {
		this.em.createNamedQuery(namedQuery).setParameter("id", id).executeUpdate();
		this.em.flush();
	}

}
