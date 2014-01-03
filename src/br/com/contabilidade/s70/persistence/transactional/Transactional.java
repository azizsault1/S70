package br.com.contabilidade.s70.persistence.transactional;

import javax.persistence.EntityManager;


public interface Transactional {

	public void beginTransaction();

	public void commit();

	public void rollback();

	public void closeTransaction();

	public void commitAndCloseTransaction();

	class Factory {
		public static Transactional create(final EntityManager em) {
			return new TransactionalImpl(em);
		}
	}

}
