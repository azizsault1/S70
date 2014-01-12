package br.com.contabilidade.s70.persistence.transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

final class TransactionalImpl implements Transactional {

	private final EntityTransaction transaction;
	private final EntityManager em;

	public TransactionalImpl(final EntityManager entityManager) {
		this.em = entityManager;
		this.transaction = this.em.getTransaction();
	}

	@Override
	public final void beginTransaction() {
		this.transaction.begin();
	}

	@Override
	public final void commit() {
		this.transaction.commit();
	}

	@Override
	public final void rollback() {
		this.transaction.rollback();
	}

	@Override
	public void close() {
		this.em.close();
	}

}
