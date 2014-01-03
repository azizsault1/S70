package br.com.contabilidade.s70.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.contabilidade.s70.persistence.exception.PersistenceException;
import br.com.contabilidade.s70.persistence.exception.PersistenceException.TypeError;

public enum DriverManager {
	INSTANCE;

	private EntityManagerFactory pool;

	public void createDataSource() throws PersistenceException {
		if ((this.pool != null) && this.pool.isOpen()) {
			this.pool.close();
		}
		try {
			this.pool = Persistence.createEntityManagerFactory("S70");
			this.pool.createEntityManager().close();
		} catch (final Exception e) {
			throw new PersistenceException(TypeError.DATA_CREATE, e);
		}
	}

	public void closeDataSource() {
		if ((this.pool != null) && this.pool.isOpen()) {
			this.pool.close();
		}
	}

	public EntityManager getEntityManager() {
		return this.pool.createEntityManager();
	}

}
