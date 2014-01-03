package br.com.contabilidade.s70.persistence.dao;

public interface Dao<Interface, Implementacao> {

	public abstract void save(Interface entity);

	public abstract void delete(Interface entity);

}