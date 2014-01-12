package br.com.contabilidade.s70.persistence.transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TransactionalImpl<T> implements Transactional {

	private final EntityTransaction transaction;
	private final EntityManager em;

	public TransactionalImpl(final EntityManager entityManager) {
		this.em = entityManager;
		this.transaction = this.em.getTransaction();
	}

	@Override
	public final void beginTransaction() {
		System.out.println("TransactionalImpl.beginTransaction() INCIANDO A TRANSAÇÃO");
		this.transaction.begin();
	}

	@Override
	public final void commit() {
		System.out.println("TransactionalImpl.commit() COMMITANDO A TRANSAÇÃO");
		this.transaction.commit();
	}

	@Override
	public final void rollback() {
		System.out.println("TransactionalImpl.rollback() ROOLLBACK NA TRANSAÇÃO");
		this.transaction.rollback();
	}

	@Override
	public void close() {
		System.out.println("TransactionalImpl.close() FECHANDO A CONEXÃO");
		this.em.close();
	}

}
