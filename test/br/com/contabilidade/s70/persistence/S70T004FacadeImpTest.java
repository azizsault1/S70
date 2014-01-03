package br.com.contabilidade.s70.persistence;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.contabilidade.s70.persistence.beans.Historico;

public class S70T004FacadeImpTest {

	private HistoricoFacade facade;

	@Before
	public void setUp() throws Exception {
		this.facade = HistoricoFacade.Factory.create();
	}

	@Test
	public void testGet() {
		final List<Historico> lista = this.facade.get();

		for (final Historico s70t004 : lista) {
			System.out.println("S70T004FacadeImpTest.testGet()" + s70t004);
		}
	}
}
