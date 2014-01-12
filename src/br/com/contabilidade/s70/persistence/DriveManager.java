package br.com.contabilidade.s70.persistence;

import javax.persistence.EntityManager;

import br.com.contabilidade.s70.persistence.exception.PersistenceException;

public interface DriveManager {

	public abstract void createDataSource() throws PersistenceException;

	public abstract void closeDataSource();

	public abstract EntityManager getEntityManager();

}