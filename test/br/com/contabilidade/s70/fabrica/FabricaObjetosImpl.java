package br.com.contabilidade.s70.fabrica;

import org.junit.Assert;

import br.com.contabilidade.s70.persistence.beans.Historico;
import br.com.contabilidade.s70.persistence.beans.Historico.HistoricoComplemento;

class FabricaObjetosImpl implements FabricaObjetos {

	// ERROS PADRAO
	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.contabilidade.s70.fabrica.FabricaObjetos#erroNaoEsperado()
	 */
	@Override
	public void erroNaoEsperado() {
		Assert.fail("Erro Não esperado.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.contabilidade.s70.fabrica.FabricaObjetos#erroNaoEsperado(java.lang.Exception)
	 */
	@Override
	public void erroNaoEsperado(final Exception e) {
		e.printStackTrace();
		this.erroNaoEsperado();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.contabilidade.s70.fabrica.FabricaObjetos#createHistorico(long, java.lang.String,
	 * br.com.contabilidade.s70.persistence.beans.Historico.HistoricoComplemento)
	 */
	@Override
	public Historico createHistorico(final long id, final String descricao, final HistoricoComplemento complemento) {
		return new HistoricoImplTest(id, descricao, complemento);
	}

}
