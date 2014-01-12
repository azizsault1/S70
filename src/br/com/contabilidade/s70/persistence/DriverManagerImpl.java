package br.com.contabilidade.s70.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.contabilidade.s70.persistence.exception.PersistenceException;
import br.com.contabilidade.s70.persistence.exception.PersistenceException.TypeError;

public enum DriverManagerImpl implements DriveManager {
	INSTANCE;

	private EntityManagerFactory pool;

	/* (non-Javadoc)
	 * @see br.com.contabilidade.s70.persistence.DriveManager#createDataSource()
	 */
	@Override
	public void createDataSource() throws PersistenceException {
		if ((this.pool != null) && this.pool.isOpen()) {
			this.pool.close();
		}
		try {
			System.out.println("DriverManager.createDataSource() CRIANDO A CONEXÃO COM O BANCO");
			this.pool = Persistence.createEntityManagerFactory("S70");
			this.pool.createEntityManager().close();
		} catch (final Exception e) {
			throw new PersistenceException(TypeError.DATA_CREATE, e);
		}
	}

	/* (non-Javadoc)
	 * @see br.com.contabilidade.s70.persistence.DriveManager#closeDataSource()
	 */
	@Override
	public void closeDataSource() {
		if ((this.pool != null) && this.pool.isOpen()) {
			this.pool.close();
		}
	}

	/* (non-Javadoc)
	 * @see br.com.contabilidade.s70.persistence.DriveManager#getEntityManager()
	 */
	@Override
	public EntityManager getEntityManager() {
		return this.pool.createEntityManager();
	}

}
