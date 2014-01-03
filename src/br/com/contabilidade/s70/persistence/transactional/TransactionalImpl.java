package br.com.contabilidade.s70.persistence.transactional;

import javax.persistence.EntityManager;

public class TransactionalImpl implements Transactional {

	private final EntityManager em;

	public TransactionalImpl(final EntityManager em) {
		this.em = em;
	}

	@Override
	public final void beginTransaction() {
		this.em.getTransaction().begin();
	}

	@Override
	public final void commit() {
		this.em.getTransaction().commit();
	}

	@Override
	public final void rollback() {
		this.em.getTransaction().rollback();
	}

	@Override
	public final void closeTransaction() {
		this.em.close();
	}

	@Override
	public final void commitAndCloseTransaction() {
		this.commit();
		this.closeTransaction();
	}

}
