package br.com.contabilidade.s70.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.contabilidade.s70.persistence.DriverManager;
import br.com.contabilidade.s70.persistence.exception.PersistenceException;

public class InicializacaoSistema implements ServletContextListener {

	@Override
	public void contextDestroyed(final ServletContextEvent arg0) {
		DriverManager.INSTANCE.closeDataSource();

	}

	@Override
	public void contextInitialized(final ServletContextEvent arg0) {
		try {
			DriverManager.INSTANCE.createDataSource();
		} catch (final PersistenceException e) {
			e.printStackTrace();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

}
